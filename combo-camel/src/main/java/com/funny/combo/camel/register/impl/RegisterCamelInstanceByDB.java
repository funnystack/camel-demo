package com.funny.combo.camel.register.impl;

import com.funny.combo.camel.register.RegisterCamelInstance;
import org.springframework.stereotype.Service;

@Service
public class RegisterCamelInstanceByDB implements RegisterCamelInstance {
    @Override
    public void register() {
        System.out.println("db注册成功");
    }
}
