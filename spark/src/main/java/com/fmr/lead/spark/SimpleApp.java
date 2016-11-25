package com.fmr.lead.spark;

import org.apache.spark.api.java.*;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function;

/**
 * First app
 *
 */
public class SimpleApp {
	public static void main(String[] args) {
		//String logFile = "C:/Spark/spark-2.0.1-bin-hadoop2.7/README.md"; // Should be some file on
		//String logFile = "hdfs://10.96.47.170:9000/user/a498333/input/bbb.txt";
		//String logFile = "hdfs://vc2crtpa109379n.fmr.com:9000/user/a498333/input/bbb.txt";
		String logFile = "hdfs://10.135.14.33:8020/user/oozie/share/lib/lib_201610222114/sharelib.properties";
														// your system
		SparkConf conf = new SparkConf().setAppName("Simple Application");
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaRDD<String> logData = sc.textFile(logFile).cache();

		long numAs = logData.filter(new Function<String, Boolean>() {
			public Boolean call(String s) {
				return s.contains("a");
			}
		}).count();

		long numBs = logData.filter(new Function<String, Boolean>() {
			public Boolean call(String s) {
				return s.contains("b");
			}
		}).count();

		System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);
	}
}
