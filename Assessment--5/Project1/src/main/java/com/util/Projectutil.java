package com.util;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

public class Projectutil {
	public static Connection createConnection()
	{
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/white","root","");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	return conn;
	}
}
