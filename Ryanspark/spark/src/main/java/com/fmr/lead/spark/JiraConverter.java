package com.fmr.lead.spark;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.codehaus.jackson.map.ObjectMapper;

public class JiraConverter {
	public static void main(String[] args) {
		String filePath = "C:/Lead/jiraData";
		String resultPath = "C:/Lead/output";

		SparkConf conf = new SparkConf().setAppName("Jira Converter Application");
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaRDD<String> jiraData = sc.textFile(filePath).cache();

		JavaRDD<Issue> result = jiraData.mapPartitions(new ParseJson());
		result.foreachPartition(new VoidFunction<Iterator<Issue>>() {
			public void call(Iterator<Issue> issueIter) throws Exception {
				
				 try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection connection = null;
		                 connection = DriverManager.getConnection(
		                 	"jdbc:oracle:thin:@10.168.104.92:1521:Orcl","ryan","ryan123");
		                 Statement statement = connection.createStatement();
		                 while (issueIter.hasNext()) {
		 					Issue issue = issueIter.next();
		 					String query = "insert into sys.jira_issues (issue_id) values('"
		 							+ issue.getIssueId()  + "')";
		 					statement.addBatch(query);
		 					//System.out.println(issue);
		 				}
		                 statement.executeBatch();
		                 statement.close();
		                 connection.close();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				
			}
		});
		sc.close();
		 //result.saveAsTextFile(resultPath);

	}
}
