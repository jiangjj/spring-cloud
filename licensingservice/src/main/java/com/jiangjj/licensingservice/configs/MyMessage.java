package com.jiangjj.licensingservice.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
//@RefreshScope
@Data
@ConfigurationProperties(prefix = "my")
public class MyMessage{
    private String message;
    private String encryptMessage;
}
