package com.lyloou.chapter5.withjava;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Computation {

    private ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

    void postponeComputation(int delay, Runnable runnable) {

        service.schedule(runnable, delay, TimeUnit.SECONDS);
    }
}
