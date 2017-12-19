package com.revature.util;

import oracle.jdbc.driver.OracleDriver;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	public static Connection getConnection() throws SQLException, IOException {
		Properties prop = new Properties();
		InputStream in = new FileInputStream(new File("./connection.properties"));
		prop.load(in);
		
    DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

		String url = prop.getProperty("url");
		url = "jdbc:oracle:thin:@" + url + ":1521/ORCL";
		String wholeUrl = "jdbc:oracle:thin:demo/p4ssw0rd@" + url + ":1521:ORCL"; 
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
		
		return DriverManager.getConnection(url, user, password);
	}
}
