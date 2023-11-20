package com.braindatawire.collegemanagement.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection 
{
	public static Connection mkConnection() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JDBC_College_Management_Project_Practice2", "root", "root");
		return con;
	}
}
