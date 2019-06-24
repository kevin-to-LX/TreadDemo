package com.kevin.demo.dto;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

import static java.lang.Thread.sleep;


@Data
public class JobTest implements Runnable {

    private ConcurrentHashMap<String, Future> futureMap;
    private int count = 0;
    private String jobId;

    public JobTest(String jobId, ConcurrentHashMap<String, Future> futureMap) {
        this.jobId = jobId;
        this.futureMap = futureMap;
    }

    @Override
    public void run() {
        count++;
        System.out.println("this is " + count);
        try {
            System.out.println("执行业务");
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (count > 4) {
            try {
                Future future = futureMap.remove(jobId);
                future.cancel(true);
            } finally {
                System.out.println("jobId " + jobId + "had cancel");
            }
        }

    }

    public ConcurrentHashMap<String, Future> getFutureMap() {
        return futureMap;
    }

    public void setFutureMap(ConcurrentHashMap<String, Future> futureMap) {
        this.futureMap = futureMap;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }


//    @Override
//    public void run() {
//        System.out.println("执行业务");
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        if (count < 1) {
//
//        }
//        count--;
//        System.out.println("count:" + count);
//        try {
//            sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("sleep-执行业务");
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//    }
}
