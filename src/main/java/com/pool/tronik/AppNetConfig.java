package com.pool.tronik;

import com.pool.tronik.client.RestClient;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("com.pool.tronik")
@EnableAutoConfiguration
public class AppNetConfig {

    @Bean
    public RestTemplate restTemplate() {
        System.out.println("RestTemplate restTemplate");
        return new RestTemplate(clientHttpRequestFactory());
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(30 * 1000);
        factory.setConnectTimeout(3 * 1000);
        return factory;
    }

    @Bean
    public RestClient restClient() {
        return new RestClient();
    }
}
