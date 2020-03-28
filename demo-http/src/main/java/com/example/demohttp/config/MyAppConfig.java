package com.example.demohttp.config;

import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyAppConfig {

  @Autowired
  CamelContext camelContext;

//  @Bean
//  MyService myService() {
//    return new DefaultMyService(camelContext);
//  }

}