package com.funny.combo.camel.register.impl;

import com.funny.combo.camel.register.RegisterCamelInstance;
import org.springframework.stereotype.Service;

@Service
public class RegisterCamelInstanceByZookeeper implements RegisterCamelInstance {
    @Override
    public void register() {
        System.out.println("zk注册成功");

    }
}
