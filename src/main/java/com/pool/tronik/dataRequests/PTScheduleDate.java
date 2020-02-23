package com.pool.tronik.dataRequests;

import java.io.Serializable;

/**
 * Created by andreivasilevitsky on 16/06/2019.
 */
public class PTScheduleDate implements Serializable{
    private int relay;
    private int status;// off / on
    private String startDate;
    private String nextDate;
    private boolean isAlways;
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

    public boolean isAlways() {
        return isAlways;
    }

    public void setAlways(boolean always) {
        isAlways = always;
    }

    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }
}
