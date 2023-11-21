package com.example.threadexperiment.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GrabinThread extends Thread {

    private final static Logger LOG = LoggerFactory.getLogger(GrabinThread.class);


    @Override
    public void run() {
        try {
            this.internalRun();
        } finally {
            logEnd();
        }
    }


    private void logEnd() {
        LOG.info("Thread ended.");
    }

    protected void internalRun() {
        LOG.info("Internal run...");
        super.run();
    }
}
