package com.relishcode.sample.test.config;

import org.apache.spark.sql.SparkSession;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class TestConfig {
	@Bean
	public SparkSession sparkSession() {
		return SparkSession
				.builder()
				.config("spark.ui.port", "4041")
				.appName("SparkWithSpringTest")
				.master("local")
				.enableHiveSupport()
				.getOrCreate();
	}
}
