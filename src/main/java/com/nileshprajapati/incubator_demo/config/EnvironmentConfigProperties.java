package com.nileshprajapati.incubator_demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "environment.config")
public class EnvironmentConfigProperties {
    private String serverHost;
    private String serverType;
    private String devName;
    private String devEmail;
    private String apiTitle;
    private String apiDescription;
    private String apiVersion;
}