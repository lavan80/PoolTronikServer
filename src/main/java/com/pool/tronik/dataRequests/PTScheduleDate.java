package com.pool.tronik.dataRequests;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by andreivasilevitsky on 16/06/2019.
 */
public class PTScheduleDate implements Serializable{
    private int relay;
    private int status;// off, on, remove
    private String startDate;
    private String nextDate;
    private int duration;
    private int iteration;

    public int getRelay() {
        return relay;
    }

    public void setRelay(int relay) {
        this.relay = relay;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getNextDate() {
        return nextDate;
    }

    public void setNextDate(String nextDate) {
        this.nextDate = nextDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PTScheduleDate that = (PTScheduleDate) o;
        return relay == that.relay &&
                status == that.status &&
                duration == that.duration &&
                iteration == that.iteration &&
                startDate.equals(that.startDate) &&
                nextDate.equals(that.nextDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(relay, status, startDate, nextDate, duration, iteration);
    }
}
