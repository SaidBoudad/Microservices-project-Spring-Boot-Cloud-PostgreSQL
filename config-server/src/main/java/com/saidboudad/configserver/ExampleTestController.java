package com.saidboudad.configserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ExampleTestController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }

}
