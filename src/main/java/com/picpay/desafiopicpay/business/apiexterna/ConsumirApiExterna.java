package com.picpay.desafiopicpay.business.apiexterna;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConsumirApiExterna {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
