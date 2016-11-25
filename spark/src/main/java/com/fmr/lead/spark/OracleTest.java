package com.fmr.lead.spark;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleTest {

	public static void main(String[] args) {
		String query = "select * from sys.jira_issues " ;
		 try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection connection = null;
              connection = DriverManager.getConnection(
              	"jdbc:oracle:thin:@10.168.104.92:1521:Orcl","ryan","ryan123");
              Statement stmt = connection.createStatement();
              ResultSet rs = stmt.executeQuery(query);
              while (rs.next()) {
//                  String coffeeName = rs.getString("COF_NAME");
//                  int supplierID = rs.getInt("SUP_ID");
//                  float price = rs.getFloat("PRICE");
//                  int sales = rs.getInt("SALES");
//                  int total = rs.getInt("TOTAL");
//                  System.out.println(coffeeName + "\t" + supplierID +
//                                     "\t" + price + "\t" + sales +
//                                     "\t" + total);
              }
              connection.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

}
