package com.example.threadexperiment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.CompletableFuture;


@SpringBootApplication
@EnableAsync
public class ThreadExperimentApplication implements CommandLineRunner {

    private final static Logger LOG = LoggerFactory.getLogger(ThreadExperimentApplication.class);

    private final AsyncService asyncService;

    public ThreadExperimentApplication(AsyncService asyncService) {
        this.asyncService = asyncService;
    }


    public static void main(String[] args) {
        SpringApplication.run(ThreadExperimentApplication.class, args);
    }

    @Override
    public void run(String... args) {
        CompletableFuture<String> r1 = asyncService.run("A");
        CompletableFuture<String> r2 = asyncService.run("B");
        CompletableFuture<String> r3 = asyncService.run("C");
        CompletableFuture<String> r4 = asyncService.run("D");
        CompletableFuture<String> r5 = asyncService.run("E");
        CompletableFuture<String> r6 = asyncService.run("F");

        r1.thenAccept(s -> LOG.info("Done A for {} ms", s));
        r2.thenAccept(s -> LOG.info("Done B for {} ms", s));
        r3.thenAccept(s -> LOG.info("Done C for {} ms", s));
        r4.thenAccept(s -> LOG.info("Done D for {} ms", s));
        r5.thenAccept(s -> LOG.info("Done E for {} ms", s));
        r6.thenAccept(s -> LOG.info("Done F for {} ms", s));

        CompletableFuture.allOf(r1, r2, r3, r4, r5, r6).join();

        LOG.info("All done..");
        System.exit(0);
    }
}
