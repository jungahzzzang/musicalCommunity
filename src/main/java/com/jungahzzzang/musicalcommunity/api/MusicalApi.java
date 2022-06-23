package com.jungahzzzang.musicalcommunity.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jungahzzzang.musicalcommunity.domain.Musical;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.json.XML;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class MusicalApi {

    @Value("${api.serviceKey}")
    private String apiKey;

    private final MusicalRepository musicalRepository;

    public void callMusicalApiJson(){

        StringBuffer result = new StringBuffer();
        String jsonPrintStr = null;

        try {
            //3개월 기간 설정
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date currentDate = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(currentDate);
            cal.add(Calendar.MONTH,3);
            String stdate = formatter.format(currentDate);
            String eddate = formatter.format(cal.getTime());

            String urlStr = "http://www.kopis.or.kr/openApi/restful/pblprfr?service="+
                    apiKey+
                    "&stdate="+stdate+
                    "&eddate="+eddate+
                    "shcate=AAAB"+
                    "&signgucode=11"+
                    "&rows=10"+
                    "&cpage=1";

            URL url = new URL(urlStr);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();

            /* XML to JSON 파싱 */
            BufferedInputStream bis = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(bis,"UTF-8"));
            String returnLine;
            while ((returnLine=br.readLine())!=null){
                result.append(returnLine);
            }
            JSONObject jsonObject = XML.toJSONObject(result.toString());
            jsonPrintStr = jsonObject.toString();

            System.out.println(jsonPrintStr);

            /* DB 저장 */
            JSONParser jsonParser = new JSONParser();   //JSON Parser 객체 생성
            Object obj = jsonParser.parse(jsonPrintStr);    //Parser로 문자열 데이터를 객체로 변환
            org.json.simple.JSONObject _jsonObject = (org.json.simple.JSONObject) obj;  //파싱한 obj를 JSONObject 객체로 변환

            //차근차근 파싱하기
            org.json.simple.JSONObject parseResult = (org.json.simple.JSONObject) _jsonObject.get("dbs");
            ObjectMapper objectMapper = new ObjectMapper();
            //ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            JSONArray parseMusicalList = (JSONArray) parseResult.get("db");
            for(int i=0;i<parseMusicalList.size();i++){
                org.json.simple.JSONObject dailyMusical = (org.json.simple.JSONObject) parseMusicalList.get(i);
                //JSON object -> Java Object(Entity) 변환
                Musical musical = objectMapper.readValue(dailyMusical.toString(),Musical.class);
                //insert
                musical.setMt20id("mt20id");
                musical.setFcltynm("fcltynm");
                musical.setPrfnm("prfnm");
                musical.setPoster("poster");
                musicalRepository.save(musical);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
