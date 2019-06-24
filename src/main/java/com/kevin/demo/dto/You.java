package com.kevin.demo.dto;

import lombok.Data;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Data
public class You {

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(11);

    public static void main(String[] args) {
//        System.out.println("执行时间小于定时时间");
//        You you = new You();
//        you.scheduledExecutorService.scheduleAtFixedRate(new JobTest()
//            ,3000, 300, TimeUnit.MILLISECONDS);
        ConcurrentHashMap<String, Future> futureMap = new ConcurrentHashMap<>();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
        JobTest job_1 = new JobTest("job_1", futureMap);
        Future future = scheduler.scheduleAtFixedRate(job_1, 1, 300, TimeUnit.MILLISECONDS);
        futureMap.put(job_1.getJobId(), future);
    }
}
