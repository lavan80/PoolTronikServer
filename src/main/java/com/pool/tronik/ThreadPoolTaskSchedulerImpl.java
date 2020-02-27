package com.pool.tronik;

import com.pool.tronik.dataRequests.PTScheduleDate;
import com.pool.tronik.database.PoolTronickRepository;
import com.pool.tronik.database.ScheduleEntity;
import com.pool.tronik.utils.DateTimeUtil;
import com.pool.tronik.utils.MapUtils;
import com.pool.tronik.utils.StaticVariables;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Component
public class ThreadPoolTaskSchedulerImpl {

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;
    @Autowired
    private PoolTronickRepository poolTronickRepository;

    private Map<ScheduledFuture, ScheduleEntity> scheduleDateMap;

    public ThreadPoolTaskSchedulerImpl() {
        scheduleDateMap = new HashMap<>();
    }

    public void scheduleRunnableWithCronTrigger(PTScheduleDate ptScheduleDate) {

        ScheduleEntity scheduleEntity = MapUtils.mapToScheduleEntity(ptScheduleDate);
        if (isExists(scheduleEntity))
            return;
        Map<ScheduledFuture, ScheduleEntity> map = getScheduledItem(scheduleEntity);
        if (!map.isEmpty()) {
            ScheduledFuture scheduledFuture = map.keySet().iterator().next();
            if (scheduleEntity.getStatus() == StaticVariables.ScheduleStatus.REMOVE.ordinal()) {
                ScheduleEntity value = map.values().iterator().next();
                scheduleDateMap.remove(scheduledFuture);
                scheduledFuture.cancel(true);
                poolTronickRepository.delete(value);
            }
            else {
                ScheduleEntity tmp = getEntity(scheduleEntity);
                if (tmp != null) {
                    ScheduleEntity entity = poolTronickRepository.save(tmp);
                    scheduleDateMap.put(scheduledFuture, entity);
                }
            }
        }
        else {
            LocalDateTime from = DateTimeUtil.createLocalDateTime(scheduleEntity.getStartDate());
            LocalDateTime next = DateTimeUtil.createLocalDateTime(scheduleEntity.getNextDate());
            System.out.println("ThreadPoolTaskSchedulerImpl from = "+from.toString()+"; next = "+next.toString());
            System.out.println("ThreadPoolTaskSchedulerImpl duration = "+DateTimeUtil.getDurationMillisBetweenTwoDate(from, next));
            ScheduleEntity entity = poolTronickRepository.save(scheduleEntity);
            ScheduledFuture scheduledFuture = taskScheduler.scheduleAtFixedRate(new RunnableTask(entity),
                    from.toDate(), DateTimeUtil.getDurationMillisBetweenTwoDate(from, next));
            scheduleDateMap.put(scheduledFuture, entity);
        }
    }

    @PostConstruct
    public void init() {
        List<ScheduleEntity> dateList = poolTronickRepository.findAll();
        restoreScheduled(dateList);
    }

    public ScheduleEntity getEntity(ScheduleEntity scheduleEntity) {
        ScheduleEntity entity = null;
        for (Map.Entry<ScheduledFuture, ScheduleEntity> item : scheduleDateMap.entrySet()) {
            ScheduleEntity value = item.getValue();
            if (scheduleEntity.getRelay() == value.getRelay() &&
            scheduleEntity.getStartDate().equalsIgnoreCase(value.getStartDate()) &&
            scheduleEntity.getNextDate().equalsIgnoreCase(value.getNextDate())) {
                entity = value;
                break;
            }
        }
        return entity;
    }

    public void restoreScheduled(List<ScheduleEntity> dateList) {
        for (ScheduleEntity entity : dateList) {
            LocalDateTime from = DateTimeUtil.createLocalDateTime(entity.getStartDate());
            LocalDateTime next = DateTimeUtil.createLocalDateTime(entity.getNextDate());
            ScheduledFuture scheduledFuture = taskScheduler.scheduleAtFixedRate(new RunnableTask(entity),
                    from.toDate(), DateTimeUtil.getDurationMillisBetweenTwoDate(from, next));
            scheduleDateMap.put(scheduledFuture, entity);
        }
    }

    public void removeScheduledTask(ScheduleEntity entity) {
        for (Map.Entry<ScheduledFuture, ScheduleEntity> item : scheduleDateMap.entrySet()) {
            ScheduledFuture key = item.getKey();
            ScheduleEntity value = item.getValue();
            if (value.getId() == entity.getId()) {
                scheduleDateMap.remove(key);
                key.cancel(true);
                poolTronickRepository.delete(value);
                break;
            }
        }

    }

    public Map<ScheduledFuture, ScheduleEntity> getScheduledItem(ScheduleEntity scheduleEntity) {
        Map<ScheduledFuture, ScheduleEntity> map = new HashMap<>();
        for (Map.Entry<ScheduledFuture, ScheduleEntity> entry : scheduleDateMap.entrySet()) {
            ScheduledFuture key = entry.getKey();
            ScheduleEntity value = entry.getValue();
            if (value.getRelay() == scheduleEntity.getRelay() &&
                    value.getStartDate().equals(scheduleEntity.getStartDate()) &&
            value.getNextDate().equals(scheduleEntity.getNextDate())) {
                map.put(key, value);
                break;
            }
        }
        return map;
    }

    public boolean isExists(ScheduleEntity scheduleEntity) {
        for (ScheduleEntity scheduled: scheduleDateMap.values()){
            if (scheduled.equals(scheduleEntity))
                return true;
        }
        return false;
    }
    class RunnableTask implements Runnable {

        private ScheduleEntity scheduleEntity;

        public RunnableTask(ScheduleEntity scheduleEntity) {
            this.scheduleEntity = scheduleEntity;
        }
        @Override
        public void run() {
            if (scheduleEntity.getDuration() == StaticVariables.DurationStatus.ITERATION.ordinal()) {
                if (scheduleEntity.getIteration() >= scheduleEntity.getIterated()) {
                    removeScheduledTask(scheduleEntity);
                    return;
                }
                scheduleEntity.setIterated(scheduleEntity.getIterated()+1);
                poolTronickRepository.save(scheduleEntity);
            }
            System.out.println(LocalDateTime.now().toString());
            ScheduleEntity s = poolTronickRepository.getOne(scheduleEntity.getId());
            System.out.println("ScheduleEntity id = "+s.toString());
        }
    }

    private void test() {
        //taskScheduler.schedule(new RunnableTask("Current Date"), new Date());
        System.out.println("pool size = "+taskScheduler.getPoolSize()+"; prefName="+taskScheduler.getThreadNamePrefix());
        System.out.println("hashCode = "+taskScheduler.hashCode());

        ScheduledFuture scheduledFuture = taskScheduler.scheduleWithFixedDelay(new RunnableTask(null), 5000);
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
        ScheduledFuture scheduledFuture2 = taskScheduler.scheduleAtFixedRate(new RunnableTask(null), 2000);
        System.out.println("scheduledFuture2 hash = "+scheduledFuture2.hashCode());
        /*taskScheduler.scheduleWithFixedDelay(new RunnableTask("Current Date Fixed 1 second Delay"), new Date(), 1000);
        taskScheduler.scheduleAtFixedRate(new RunnableTask("Fixed Rate of 2 seconds"), new Date(), 2000);
        taskScheduler.scheduleAtFixedRate(new RunnableTask("Fixed Rate of 2 seconds"), 2000);
        taskScheduler.schedule(new RunnableTask("Cron Trigger"), cronTrigger);
        taskScheduler.schedule(new RunnableTask("Periodic Trigger"), periodicTrigger);*/
    }
}
