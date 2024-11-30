package com.huce.library.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@EnableConfigurationProperties(VnPayConfig.class)
@ConfigurationProperties(prefix = "vnp")
@Component
public class VnPayConfig {
    private String version;
    private String command;
    private String hashSecret;
    private String url;
    private String returnUrl;
    private String ipAddr;
    private String currCode;
    private String tmnCode;
    private String locale;
    private String orderType;
}