package com.example.threadexperiment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.CompletableFuture;


@Service
public class AsyncService {

    private final static Logger LOG = LoggerFactory.getLogger(AsyncService.class);


    @Async
    public CompletableFuture<String> run(String runnerName) {
        int i = new Random().nextInt(3000, 5000);
        LOG.info("{} sleeping for {} ms", runnerName, i);

        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            LOG.error("Error");
        }

        return CompletableFuture.completedFuture(String.valueOf(i));
    }
}
