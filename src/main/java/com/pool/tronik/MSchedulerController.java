package com.pool.tronik;

import com.pool.tronik.dataRequests.PTScheduleDate;
import com.pool.tronik.utils.DateTimeUtil;
import org.joda.time.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by andreivasilevitsky on 14/06/2019.
 */
@RestController
@RequestMapping("/schedule")
public class MSchedulerController {

    @Autowired
    ThreadPoolTaskSchedulerImpl threadPoolTaskScheduler;


    private PTScheduleDate ptScheduleDate = new PTScheduleDate();

    int count = 0;

    @GetMapping
    public Boolean schedule(/*MScheduleData mScheduleData*/) {

        if (count == 0 ) {
            LocalDateTime start = LocalDateTime.now();
            start = start.plusSeconds(30);
            LocalDateTime next = new LocalDateTime(start);
            next = next.plusSeconds(120);
            ptScheduleDate.setStartDate(start.toString());
            ptScheduleDate.setNextDate(next.toString());
            count++;
        }
        else {
            PTScheduleDate tmp = new PTScheduleDate();
            tmp.setStatus(2);
            tmp.setStartDate(ptScheduleDate.getStartDate());
            tmp.setNextDate(ptScheduleDate.getNextDate());
            ptScheduleDate = tmp;
        }
        threadPoolTaskScheduler.scheduleRunnableWithCronTrigger(ptScheduleDate);
        return true;
    }

    private void test() {
        LocalDateTime currentDateAndTime = LocalDateTime.now();
        System.out.println("LocalDateTime Date = "+currentDateAndTime.toString());
        //LocalTime currentTime = currentDateAndTime.toLocalTime();//LocalTime.now();
        //System.out.println("LocalTime Date = "+currentTime.toString());
        //LocalDate currentDate = LocalDate.now();
        //System.out.println("LocalDate Date = "+currentDate.toString());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LocalDateTime currentDateAndTime2 = LocalDateTime.now();
                System.out.println("LocalDateTime2 Date = "+currentDateAndTime2.toString());
                long duration = DateTimeUtil.getDurationMillisBetweenTwoDate(currentDateAndTime2, currentDateAndTime);
                System.out.println("Duration Date = "+duration);
                duration = DateTimeUtil.getPeriodMillisBetweenTwoDate(currentDateAndTime2,currentDateAndTime);
                System.out.println("Period Date = "+duration);
            }
        });
        thread.start();
    }
}
