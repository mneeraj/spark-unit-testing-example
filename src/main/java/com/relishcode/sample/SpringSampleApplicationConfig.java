package com.relishcode.sample;

import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringSampleApplicationConfig {
	@Bean
	public SparkSession sparkSession() {
		return SparkSession
				.builder()
				.appName("SparkWithSpring")
				.master("yarn")
				.enableHiveSupport()
				.getOrCreate();
	}
}
