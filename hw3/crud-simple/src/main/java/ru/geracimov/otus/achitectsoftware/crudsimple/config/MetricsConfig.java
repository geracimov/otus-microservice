package ru.geracimov.otus.achitectsoftware.crudsimple.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Configuration
public class MetricsConfig {
    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    MeterRegistryCustomizer<MeterRegistry> configurer() {
        return (registry) -> registry.config()
                .namingConvention((name, type, baseUnit) -> applicationName + "_" +
                        Arrays.stream(name.split("\\."))
                                .filter(Objects::nonNull)
                                .collect(Collectors.joining("_")));
    }
}