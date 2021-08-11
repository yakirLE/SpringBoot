package com.continuity.springboot.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfiguration {
	
	// not working for some reason
	@Bean
	public DataSource dataSource() {
		DataSourceBuilder<?> builder = DataSourceBuilder.create();
		builder.url("jdbc:postgresql://localhost:5432/conference_app");
		builder.username("postgres");
		builder.password("Password1!");
		System.out.println("My custom datasource bean has been initialized and set");
		return builder.build();
	}
}
