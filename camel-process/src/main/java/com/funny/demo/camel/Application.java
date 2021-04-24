package com.funny.demo.camel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"com.funny.combo.camel","com.funny.demo.camel"})
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        InitRoute.init();
        SpringApplication.run(Application.class, args);
    }

}
