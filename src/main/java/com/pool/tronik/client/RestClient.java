package com.pool.tronik.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by andreivasilevitsky on 14/06/2019.
 */
@Component
public class RestClient {
    @Autowired
    private RestTemplate restTemplate;

    public void changeStatus(String baseUrl, String relay) {
        if (baseUrl.trim().isEmpty())
            return;
        String uri = baseUrl + relay;
        //HttpHeaders headers = new HttpHeaders();
       // headers.setContentType(MediaType.APPLICATION_JSON);
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                String result = responseEntity.getBody();//restTemplate.getForObject(uri, String.class);
                System.out.print(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
