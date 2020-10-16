package eu.vrtime.sampleui.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import eu.vrtime.sampleui.domain.Customer;

@Configuration
@Import(PersistenceConfig.class)
@ComponentScan({ "eu.vrtime.sampleui.infrastructure", "eu.vrtime.sampleui.service" })
public class SpringConfig {
	
	

}
