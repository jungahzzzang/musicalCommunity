package com.jungahzzzang.musicalcommunity.config;

import com.jungahzzzang.musicalcommunity.service.MusicalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final MusicalService musicalService;

    @Bean
    public Job testJob(){
        return jobBuilderFactory.get("testJob")
                .start(testStep(null))
                .build();
    }

    @Bean
    @JobScope
    public Step testStep(@Value("#{jobParameters[testParameter]}") String testParameter){
        return stepBuilderFactory.get("testStep")
                .tasklet((contribution, chunkContext) -> {
                    musicalService.insertMusical();
                    return RepeatStatus.FINISHED;
                }).build();
    }
}