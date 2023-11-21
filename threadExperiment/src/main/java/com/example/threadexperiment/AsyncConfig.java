package com.example.threadexperiment;

import com.example.threadexperiment.factory.GrabinThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadFactory;


@Configuration
public class AsyncConfig {

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        final ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

//        taskExecutor.setThreadFactory(threadFactory());
        taskExecutor.setThreadNamePrefix("custom-executor-");
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setQueueCapacity(10);
        taskExecutor.initialize();

        return taskExecutor;
    }

    @Bean
    public ThreadFactory threadFactory() {
        GrabinThreadFactory grabinThreadFactory = new GrabinThreadFactory();


        return grabinThreadFactory;
    }
}
