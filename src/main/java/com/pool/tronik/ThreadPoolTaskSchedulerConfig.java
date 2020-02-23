package com.pool.tronik;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@ComponentScan(
        basePackages="com.pool.tronik",
        basePackageClasses={ThreadPoolTaskSchedulerImpl.class})
public class ThreadPoolTaskSchedulerConfig {

    public ThreadPoolTaskSchedulerConfig() {
        System.out.println("Ctr "+ThreadPoolTaskSchedulerConfig.class.getSimpleName());
    }

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(5);
        threadPoolTaskScheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
        System.out.println("ThreadPoolTaskSchedulerConfig  #@#@ threadPoolTaskScheduler hash="+threadPoolTaskScheduler.hashCode());
        System.out.println("ThreadPoolTaskSchedulerConfig  #@#@ size ="+threadPoolTaskScheduler.getPoolSize());
        return threadPoolTaskScheduler;
    }
}
