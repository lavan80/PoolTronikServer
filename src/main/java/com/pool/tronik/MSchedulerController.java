package com.pool.tronik;

import com.pool.tronik.dataRequests.PTScheduleDate;
import com.pool.tronik.dataRequests.PTScheduleDateClient;
import com.pool.tronik.utils.DateTimeUtil;
import com.pool.tronik.utils.MapUtils;
import com.pool.tronik.utils.StaticVariables;
import org.joda.time.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by andreivasilevitsky on 14/06/2019.
 */
@RestController
@RequestMapping("/schedule")
public class MSchedulerController {

    @Autowired
    ThreadPoolTaskSchedulerImpl threadPoolTaskScheduler;


    //private PTScheduleDate ptScheduleDate = new PTScheduleDate();

    int count = 0;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Boolean schedule(@RequestBody PTScheduleDateClient ptScheduleDateClient) {

        if (ptScheduleDateClient.getNextDates().isEmpty()) {
            PTScheduleDate ptScheduleDate = MapUtils.mapToPTScheduleDate(ptScheduleDateClient,"");
            ptScheduleDate.setDuration(StaticVariables.DurationStatus.ITERATION.ordinal());
            ptScheduleDate.setIteration(1);
            if (ptScheduleDate != null)
                threadPoolTaskScheduler.scheduleOneTimeTask(ptScheduleDate);
        }
        else {
            for (String date : ptScheduleDateClient.getNextDates()) {
                PTScheduleDate ptScheduleDate = MapUtils.mapToPTScheduleDate(ptScheduleDateClient, "");
                if (ptScheduleDate != null) {
                    LocalDateTime start = DateTimeUtil.createLocalDateTime(date);
                    LocalDateTime next = start.plusWeeks(1);
                    ptScheduleDate.setStartDate(start.toString());
                    ptScheduleDate.setNextDate(next.toString());
                    threadPoolTaskScheduler.scheduleRunnableAtFixedRate(ptScheduleDate);
                }
            }
        }
        return true;
    }

    private void test2() {
        /*if (count == 0 ) {
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
        }*/
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
