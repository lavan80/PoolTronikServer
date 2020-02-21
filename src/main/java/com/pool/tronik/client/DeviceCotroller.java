package com.pool.tronik.client;

import com.pool.tronik.utils.RelayOn;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

/**
 * Created by andreivasilevitsky on 14/06/2019.
 */
public class DeviceCotroller {

    public void changeStatus() {
        String uri = "http://192.168.1.199/"+ RelayOn.RELAY1;
        RestTemplate restTemplate = new RestTemplate();

        Object result = restTemplate.getForObject(uri, Objects.class);

        System.out.print(result);
    }
}
