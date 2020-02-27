package com.pool.tronik.utils;

import org.joda.time.Duration;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;

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
}
