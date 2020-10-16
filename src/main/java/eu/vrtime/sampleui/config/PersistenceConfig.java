package eu.vrtime.sampleui.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = { "eu.vrtime.sampleui.infrastructure" })
@EntityScan(basePackages = { "eu.vrtime.sampleui.domain" })
public class PersistenceConfig {

}
