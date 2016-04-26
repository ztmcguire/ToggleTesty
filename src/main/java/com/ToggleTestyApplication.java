package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.manager.FeatureManagerBuilder;

import javax.sql.DataSource;

@SpringBootApplication
public class ToggleTestyApplication {

	@Autowired
	private DataSource ds;

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ToggleTestyApplication.class, args);
	}

	@Bean
	public FeatureManager getFeatureManager() {
		return new FeatureManagerBuilder()
				.featureEnum(Togglz.class)
				.togglzConfig(DatabaseToggleConfig.create(ds))
				.build();
	}

}
