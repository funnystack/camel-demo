package com.funny.combo.camel.register.impl;

import com.funny.combo.camel.CamelvContextStarter;
import com.funny.combo.camel.register.RegisterCamelInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RegisterCamelInstanceByDB implements RegisterCamelInstance {
    private static final Logger logger = LoggerFactory.getLogger(CamelvContextStarter.class);

    @Override
    public void register() {
        logger.info("register camel instance ok...");
    }
}
