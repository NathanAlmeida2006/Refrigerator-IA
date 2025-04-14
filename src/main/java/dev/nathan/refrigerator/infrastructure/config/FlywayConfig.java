package dev.nathan.refrigerator.infrastructure.config;

import jakarta.annotation.PostConstruct;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


@Configuration
public class FlywayConfig {
    private final Environment env;

    public FlywayConfig(Environment env) {
        this.env = env;
    }

    @PostConstruct
    public void migrate() {
        Flyway flyway = Flyway.configure().dataSource(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"), env.getProperty("spring.datasource.password")).load();
        flyway.migrate();
    }
}