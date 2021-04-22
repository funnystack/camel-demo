package com.funny.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author fangli
 */
@SpringBootApplication(scanBasePackages= "com.funny.admin")
public class CamelViewApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CamelViewApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(CamelViewApplication.class, args);
    }


}
