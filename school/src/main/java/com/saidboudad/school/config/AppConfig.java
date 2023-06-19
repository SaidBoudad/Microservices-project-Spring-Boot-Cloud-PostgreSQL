package com.saidboudad.school.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {
    @LoadBalanced //By this, the call by RESTTemplate will load balanced between instances of the service that it will call
    @Bean //This bean in case I use RESTTemplate for calling another RESTful service
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean  //Responsible for transforming an object to DTO in my app
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    //This value to retrieve the url from application.yml file
    @Value("${studentService.base.url}")
    private String studentServiceBaseUrl;

    @Bean  //I use webclient for calling another RESTFul service to beneficent from advantages of reactive programming
    public WebClient webClient() {
        return WebClient
                .builder()
                .baseUrl(studentServiceBaseUrl)
                .build();
    }
}