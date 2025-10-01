package com.eonis.demo.rest.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "cors.allowed-origin")
public class CorsProperties {
    private String allowedOrigins;
}