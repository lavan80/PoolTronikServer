package com.pool.tronik.dataRequests;

import java.io.Serializable;

/**
 * Created by andreivasilevitsky on 16/06/2019.
 */
public class MScheduleData implements Serializable{
    private int relay;
    private int status;
    private String date;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
