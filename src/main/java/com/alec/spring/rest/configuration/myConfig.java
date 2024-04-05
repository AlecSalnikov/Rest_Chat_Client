package com.alec.spring.rest.configuration;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("com.alec.spring.rest")

public class myConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();

    }



}
