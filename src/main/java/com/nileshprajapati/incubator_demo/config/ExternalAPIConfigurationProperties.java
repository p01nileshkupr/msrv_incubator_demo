package com.nileshprajapati.incubator_demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "external.api.config")
public class ExternalAPIConfigurationProperties {
    private String newsUrl;
    private String newsKey;
    private String cityUrl;
    private String cityKey;
}
