package com.tempest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableTransactionManagement
@EnableConfigurationProperties
@RestController
@EnableCaching
@EnableScheduling
@EnableJpaRepositories(basePackages = "com.tempest.repository")  // repository 所在的包
@EntityScan(basePackages = "com.tempest.entity")
public class PassKeeper2Application {

	public static void main(String[] args) {
		SpringApplication.run(PassKeeper2Application.class, args);
	}

}
