package com.example.threadexperiment.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadFactory;


public class GrabinThreadFactory implements ThreadFactory {

    private final static Logger LOG = LoggerFactory.getLogger(GrabinThreadFactory.class);
    private final String factoryName = "GrabinFactory";


    @Override
    public Thread newThread(Runnable runnable) {
        return new GrabinThread() {
            @Override
            protected void internalRun() {
                try {
                    GrabinThreadFactory.this.innerPrepareThread();
                    runnable.run();
                } catch (RuntimeException e) {
                    LOG.error("Error {}", e.getMessage());
                }
            }
        };
    }

    private void innerPrepareThread() {
        try {
            this.beforePrepareThread();
        } finally {
            this.afterPrepareThread();
        }
    }

    protected void afterPrepareThread() {
        LOG.info("After prepare");
    }

    protected void beforePrepareThread() {
        LOG.info("Before prepare");
    }
}
