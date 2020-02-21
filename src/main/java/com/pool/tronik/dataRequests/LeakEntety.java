package com.pool.tronik.dataRequests;

import java.io.Serializable;

/**
 * Created by andreivasilevitsky on 03/05/2019.
 */
public class LeakEntety implements Serializable{
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
