package com.pool.tronik.utils;

import com.pool.tronik.dataRequests.PTScheduleDate;
import com.pool.tronik.dataRequests.PTScheduleDateClient;
import com.pool.tronik.database.ScheduleEntity;

import java.util.ArrayList;
import java.util.List;

public class MapUtils {

    public static ScheduleEntity mapToScheduleEntity(PTScheduleDate ptScheduleDate) {
        ScheduleEntity scheduleEntity = new ScheduleEntity();
        scheduleEntity.setDuration(ptScheduleDate.getDuration());
        scheduleEntity.setIteration(ptScheduleDate.getIteration());
        scheduleEntity.setNextDate(ptScheduleDate.getNextDate());
        scheduleEntity.setStartDate(ptScheduleDate.getStartDate());
        scheduleEntity.setRelay(ptScheduleDate.getRelay());
        scheduleEntity.setStatus(ptScheduleDate.getStatus());
        return scheduleEntity;
    }

    public static PTScheduleDate mapToPTScheduleDate(ScheduleEntity scheduleEntity) {
        PTScheduleDate ptScheduleDate = new PTScheduleDate();
        ptScheduleDate.setStatus(scheduleEntity.getStatus());
        ptScheduleDate.setNextDate(scheduleEntity.getNextDate());
        ptScheduleDate.setStartDate(scheduleEntity.getStartDate());
        ptScheduleDate.setDuration(scheduleEntity.getDuration());
        ptScheduleDate.setIteration(scheduleEntity.getIteration());
        ptScheduleDate.setRelay(scheduleEntity.getRelay());
        return ptScheduleDate;
    }

    public static List<PTScheduleDate> mapToPTScheduleDate(List<ScheduleEntity> scheduleEntityList) {
        List<PTScheduleDate> dateList = new ArrayList<>();
        for (ScheduleEntity entity : scheduleEntityList) {
            dateList.add(mapToPTScheduleDate(entity));
        }
        return dateList;
    }

    public static PTScheduleDate mapToPTScheduleDate(PTScheduleDateClient ptScheduleDateClient){
        try {
            PTScheduleDate ptScheduleDate = new PTScheduleDate();
            ptScheduleDate.setRelay(ptScheduleDateClient.getRelay());
            ptScheduleDate.setIteration(ptScheduleDateClient.getIteration());
            ptScheduleDate.setDuration(ptScheduleDateClient.getDuration());
            ptScheduleDate.setStartDate(ptScheduleDateClient.getStartDate());
            ptScheduleDate.setNextDate(ptScheduleDateClient.getNextDates().get(0));
            ptScheduleDate.setStatus(ptScheduleDateClient.getStatus());
            return ptScheduleDate;
        } catch (Exception e){}

        return null;
    }

    public static PTScheduleDate mapToPTScheduleDate(PTScheduleDateClient ptScheduleDateClient, String localDateTime){
        try {
            PTScheduleDate ptScheduleDate = new PTScheduleDate();
            ptScheduleDate.setRelay(ptScheduleDateClient.getRelay());
            ptScheduleDate.setIteration(ptScheduleDateClient.getIteration());
            ptScheduleDate.setDuration(ptScheduleDateClient.getDuration());
            ptScheduleDate.setStartDate(ptScheduleDateClient.getStartDate());
            ptScheduleDate.setNextDate(localDateTime);
            ptScheduleDate.setStatus(ptScheduleDateClient.getStatus());
            return ptScheduleDate;
        } catch (Exception e){}

        return null;
    }
}
