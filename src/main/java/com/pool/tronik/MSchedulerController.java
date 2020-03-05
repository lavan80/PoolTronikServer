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

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Boolean schedule(@RequestBody PTScheduleDateClient ptScheduleDateClient) {

        try {
            if (ptScheduleDateClient.getNextDates().isEmpty()) {
                PTScheduleDate ptScheduleDate = MapUtils.mapToPTScheduleDate(ptScheduleDateClient, "");
                ptScheduleDate.setDuration(StaticVariables.DurationStatus.ITERATION.ordinal());
                ptScheduleDate.setIteration(1);
                if (ptScheduleDate != null)
                    threadPoolTaskScheduler.scheduleOneTimeTask(ptScheduleDate);
            } else {
                LocalDateTime localDateTime = DateTimeUtil.createLocalDateTime(ptScheduleDateClient.getStartDate());
                localDateTime = localDateTime.plusWeeks(1);
                PTScheduleDate ptScheduleDate = MapUtils.mapToPTScheduleDate(ptScheduleDateClient, localDateTime.toString());
                threadPoolTaskScheduler.scheduleRunnableAtFixedRate(ptScheduleDate);

                for (String date : ptScheduleDateClient.getNextDates()) {
                    ptScheduleDate = MapUtils.mapToPTScheduleDate(ptScheduleDateClient, "");
                    if (ptScheduleDate != null) {
                        createPTScheduleDate(ptScheduleDate, date, 1);
                        threadPoolTaskScheduler.scheduleRunnableAtFixedRate(ptScheduleDate);
                    }
                }
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public void createPTScheduleDate(PTScheduleDate ptScheduleDate, String startDate, int additionalWeek) {
        LocalDateTime start = DateTimeUtil.createLocalDateTime(startDate);
        LocalDateTime next = start.plusWeeks(additionalWeek);
        ptScheduleDate.setStartDate(start.toString());
        ptScheduleDate.setNextDate(next.toString());
    }
}
