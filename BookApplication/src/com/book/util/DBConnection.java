package com.book.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

	public static Connection getConnection() throws IOException, SQLException, ClassNotFoundException
{
	try {
		FileInputStream fis=new FileInputStream("resources/db.properties");
		Properties p=new Properties();
		p.load(fis);
		String driver=p.getProperty("driver");
		String url=p.getProperty("url");
		String username=p.getProperty("username");
		String password = p.getProperty("password");
		Class.forName(driver);
		Connection con=DriverManager.getConnection(url,username,password);
		return con;
		
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;

	
}
}
