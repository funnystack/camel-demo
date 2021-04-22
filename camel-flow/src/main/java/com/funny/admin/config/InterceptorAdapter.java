package com.funny.admin.config;

import com.funny.admin.interceptor.SignInteceptor;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Map;

@Component
public class InterceptorAdapter implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        SignInteceptor signInteceptor = new SignInteceptor();
        Map<String,String> nmap = Maps.newHashMap();
        nmap.put("mall","mall");
        nmap.put("finance","finance");
        signInteceptor.setAppMap(nmap);
        registry.addInterceptor(signInteceptor)
                .addPathPatterns("/**");
    }
}
