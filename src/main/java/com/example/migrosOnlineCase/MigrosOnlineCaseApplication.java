package com.example.migrosOnlineCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {ElasticsearchRestClientAutoConfiguration.class, ElasticsearchDataAutoConfiguration.class, ElasticsearchRepositoriesAutoConfiguration.class, ActiveMQAutoConfiguration.class, ArtemisAutoConfiguration.class})
@EnableJpaRepositories(basePackages = "com.example.migrosOnlineCase.repository")
@EnableScheduling
@EnableCaching
public class MigrosOnlineCaseApplication extends SpringBootServletInitializer {

	private static final Logger log = LoggerFactory.getLogger(MigrosOnlineCaseApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(MigrosOnlineCaseApplication.class, args);
	}

}



