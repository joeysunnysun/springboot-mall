package com.jospehsun.springbootmall;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${spring.datasource.password}")
    String pwd;

    @GetMapping("/test")
    public String test() {
        System.out.println("    pwd: " + pwd);
        return "hello";
    }
}
