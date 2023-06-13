package com.saidboudad.school.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Bean //I add this bean in case I use RESTTemplate for calling another RESTful service
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean  //Responsible for transforming an object to DTO in my app
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Value("${studentservice.base.url}")
    private String studentServiceBaseUrl;

    @Bean  //I use webclient for calling another RESTful service to beneficent from advantages of reactive programming
    public WebClient webClient(){
        return WebClient
                .builder()
                .baseUrl(studentServiceBaseUrl)
                .build();
    }
}