package com.pool.tronik.dataRequests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by andreivasilevitsky on 14/06/2019.
 */
public class KmtConfig {
    private String kmtIp;
    private String kmtRelays;
    private List<String> kmtRelayList;

    public KmtConfig() {
        kmtRelayList = new ArrayList<>();
    }

    public String getKmtIp() {
        return kmtIp;
    }

    public void setKmtIp(String kmtIp) {
        this.kmtIp = kmtIp;
    }

    public List<String> getKmtRelayList() {
        if (!kmtRelays.isEmpty()) {
            String[] sArr = kmtRelays.split(",");
            kmtRelayList = Arrays.asList(sArr);
        }
        return kmtRelayList;
    }

    public void setKmtRelayList(List<String> kmtRelayList) {
        this.kmtRelayList = kmtRelayList;
    }

    public String getKmtRelays() {
        return kmtRelays;
    }

    public void setKmtRelays(String kmtRelays) {
        this.kmtRelays = kmtRelays;
    }
}
