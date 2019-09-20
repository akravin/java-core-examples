package com.akravin.multithreadconsumer.utils;

import java.util.concurrent.CountDownLatch;

public abstract class AbstractLatchWorker implements Runnable {

    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;

    public AbstractLatchWorker(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }

    protected abstract void doWork();

    @Override
    public void run() {
        try {
            startSignal.await();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        try {
            doWork();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        doneSignal.countDown();
    }

}
