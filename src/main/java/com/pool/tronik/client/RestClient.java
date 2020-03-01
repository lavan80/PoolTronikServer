package com.pool.tronik.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by andreivasilevitsky on 14/06/2019.
 */

public class RestClient {

    private final String BASE_URL = "http://192.168.0.199/";

    @Autowired
    private RestTemplate restTemplate;

    public void changeStatus(String relay) {
        String uri = BASE_URL + relay;
        HttpHeaders headers = new HttpHeaders();
       // headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String result = responseEntity.getBody();//restTemplate.getForObject(uri, String.class);
            System.out.print(result);
        }
    }
}
