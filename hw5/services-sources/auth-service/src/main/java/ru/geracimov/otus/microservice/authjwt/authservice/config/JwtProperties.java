package ru.geracimov.otus.microservice.authjwt.authservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:application.yaml")
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
        private String secret;
        private int expiration;
}
