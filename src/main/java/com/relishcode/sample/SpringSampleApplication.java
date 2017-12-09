package com.relishcode.sample;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import static org.apache.spark.sql.functions.max;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpringSampleApplication {
	@Autowired
	private SparkSession sparkSession;
	
	public int process() {
		System.out.println("Processing started...");
		Dataset<Row> scoresDf = sparkSession.read().table("scores");
		int result = scoresDf.agg(max("score")).collectAsList().get(0).getInt(0);
		System.out.println("Result is: " + result);
		System.out.println("Processing completed...");
		return result;
	}
}
