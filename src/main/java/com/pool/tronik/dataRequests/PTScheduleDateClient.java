package com.pool.tronik.dataRequests;

import java.util.List;

public class PTScheduleDateClient {

    private int relay;
    private int status;// off, on, remove
    private String startDate;
    private List<String> nextDates;
    private int duration = 0;
    private int iteration;
    private List<Integer> repeatList;

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

    public List<String> getNextDates() {
        return nextDates;
    }

    public void setNextDates(List<String> nextDates) {
        this.nextDates = nextDates;
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

    public List<Integer> getRepeatList() {
        return repeatList;
    }

    public void setRepeatList(List<Integer> repeatList) {
        this.repeatList = repeatList;
    }

    public void addNextDate(String localDateTime) {
        nextDates.add(localDateTime);
    }
}
