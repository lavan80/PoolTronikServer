package com.pool.tronik;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

@Component
public class ThreadPoolTaskSchedulerImpl {

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    public void scheduleRunnableWithCronTrigger() {

        //taskScheduler.schedule(new RunnableTask("Current Date"), new Date());
        System.out.println("pool size = "+taskScheduler.getPoolSize()+"; prefName="+taskScheduler.getThreadNamePrefix());
        System.out.println("hashCode = "+taskScheduler.hashCode());

        ScheduledFuture scheduledFuture = taskScheduler.scheduleWithFixedDelay(new RunnableTask("Fixed 1 second Delay"), 5000);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread pool size = "+taskScheduler.getPoolSize()+"; prefName="+taskScheduler.getThreadNamePrefix());
                scheduledFuture.cancel(true);
                System.out.println("scheduledFuture hash = "+scheduledFuture.hashCode());
            }
        });
        thread.start();
        ScheduledFuture scheduledFuture2 = taskScheduler.scheduleAtFixedRate(new RunnableTask("Fixed Rate of 2 seconds"), 2000);
        System.out.println("scheduledFuture2 hash = "+scheduledFuture2.hashCode());
        /*taskScheduler.scheduleWithFixedDelay(new RunnableTask("Current Date Fixed 1 second Delay"), new Date(), 1000);
        taskScheduler.scheduleAtFixedRate(new RunnableTask("Fixed Rate of 2 seconds"), new Date(), 2000);
        taskScheduler.scheduleAtFixedRate(new RunnableTask("Fixed Rate of 2 seconds"), 2000);
        taskScheduler.schedule(new RunnableTask("Cron Trigger"), cronTrigger);
        taskScheduler.schedule(new RunnableTask("Periodic Trigger"), periodicTrigger);*/
    }

    class RunnableTask implements Runnable {

        private String message;

        public RunnableTask(String message) {
            this.message = message;
        }
        @Override
        public void run() {
            System.out.println("Runnable Task with " + message + " on thread " + Thread.currentThread().getName());
        }
    }
}
