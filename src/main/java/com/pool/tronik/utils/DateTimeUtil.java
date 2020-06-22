package com.pool.tronik.utils;

import org.joda.time.Duration;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;

import java.util.ArrayList;
import java.util.List;

public class DateTimeUtil {

    /**
     *
     * @param timeFirst
     * @param timeSecond
     * @return Time in millisecond between two arguments. Don't take into account summer/winter time
     */
    public static long getDurationMillisBetweenTwoDate(LocalDateTime timeFirst, LocalDateTime timeSecond) {
        long difference;
        Duration duration = new Duration(timeSecond.toDateTime().getMillis(), timeFirst.toDateTime().getMillis());
        difference = Math.abs(duration.getMillis());
        return difference;
    }

    /**
     *
     * @param timeFirst
     * @param timeSecond
     * @return Time in millisecond between two arguments. Take into account summer/winter time
     */
    public static long getPeriodMillisBetweenTwoDate(LocalDateTime timeFirst, LocalDateTime timeSecond) {
        long difference;
        Period period = new Period(timeFirst,timeSecond);
        difference = period.toStandardSeconds().getSeconds()*1000;
        return difference;
    }

    public static LocalDateTime createLocalDateTime(String date) {
        LocalDateTime localDateTime = new LocalDateTime(date);
        //DateTime customDateTimeFromString = new DateTime("2018-05-05T10:11:12.123");
        return localDateTime;
    }

    public static List<String> makeNextDatesFromDateAndRepetition(LocalDateTime startDate, List<Integer> repetitionList) {
        List<String> nextDateList = new ArrayList<>();
        int startDay = startDate.getDayOfWeek();
        for (Integer day : repetitionList) {
            if (startDay > day) {
                int step = (7 - startDay) + day;
                String date = startDate.plusDays(step).toString();
                nextDateList.add(date);
            }
            else if (startDay < day) {
                int step = day - startDay ;
                String date = startDate.plusDays(step).toString();
                nextDateList.add(date);
            }
            else {
                String date = startDate.plusDays(7).toString();
                nextDateList.add(date);
            }
        }
        return nextDateList;
    }
    public static boolean isBeforeNow(String localDateTime) {
        return isBeforeNow(new LocalDateTime(localDateTime));
    }

    public static boolean isBeforeNow(LocalDateTime localDateTime) {
        LocalDateTime now = LocalDateTime.now();
        return localDateTime.isBefore(now);
    }
}
