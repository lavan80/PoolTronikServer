package com.pool.tronik.dataRequests;

import java.io.Serializable;

/**
 * Created by andreivasilevitsky on 08/05/2019.
 */
public class PushEntity implements Serializable{
    private String uniqId;
    private String token;
    private String platform;

    public String getUniqId() {
        return uniqId;
    }

    public void setUniqId(String uniqId) {
        this.uniqId = uniqId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
