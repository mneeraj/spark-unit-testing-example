package com.relishcode.sample;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.relishcode.sample.test.config.TestConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={TestConfig.class, SpringSampleApplication.class})
public class SpringSampleApplicationTest {
	
	@Autowired
	SpringSampleApplication application;
	
	@Autowired
	private SparkSession sparkSession;
	
	private static boolean setupDone = false;

	@Before
	public void setup() {
		if (!setupDone) {
			StructType schema = new StructType().add(DataTypes.createStructField("name", DataTypes.StringType, true))
					.add(DataTypes.createStructField("score", DataTypes.IntegerType, true));
			
			List<Row> data = new ArrayList<>();
			data.add(RowFactory.create("jerry", new Integer(90)));
			data.add(RowFactory.create("tom", new Integer(50)));
			data.add(RowFactory.create("pikachu", new Integer(95)));
			
			Dataset<Row> df = sparkSession.createDataFrame(data, schema);
			df.show();
			df.write().mode(SaveMode.Overwrite).saveAsTable("scores");
			setupDone = true;
		}
	}
	
	@Test
	public void testSum() {
		assertEquals(95, application.process());
	}

}
