package com.jungahzzzang.musicalcommunity.api;

//@Slf4j
//@Component
public class BatchScheduler {

    //private static final Logger logger = LoggerFactory.getLogger(BatchScheduler.class);

//    @Value("${api.serviceKey}")
//    private String apiKey;
//    @Autowired
//    private MusicalRepository musicalRepository;
//    private OpenDataApi openDataApi;

    //@Scheduled(cron = "0 0 3 * * 6") //매주 ?��?��?�� ?���? 3?��?�� ?��?��?���? ?��?��?��?�� ?���?줄러
//    @Scheduled(fixedDelay = 1000)
//    public void scheduleTask() {
//        1~10 Page �??��?���?
//        for(int i=1;i<=10;i++){
//            String rowsPage = "10";
//            String pageNum = String.valueOf(i);
//
//            MusicalInfoResponse musicalInfos = openDataApi.createDetailInfo();
//            log.info("===================================================");
//            log.info("뮤�?�? ?��?�� ?���? Page");
//
//            //DB ???�� ?��?��
//          musicalInfos.getBody().getItems().forEach(item -> {
//            log.info("Current Item : {}", item.toString());
//            log.info("Save : {}",
//                    musicalRepository.save(Musical.builder().name(item.getmName()).build()).toString());
//        });
//
//            MusicalInfoResponse musicalInfos = openDataApi.createDetailInfo(Map<String, Object> musicalInfo);
//            musicalInfos.getBody().getItems().forEach(item -> {
//                log.info("Save : {}",
//                        musicalRepository.save(Musical.builder().prfnm(item.getPrfnm()).build()).toString()
//                );
//            });
//
//        }
    }


