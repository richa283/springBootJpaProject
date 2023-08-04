package com.springBoot.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpModel {

	public static void saveData
	(String username,String fathername,String mothername,
	String mobile,String address,String email, String pass, String cpass) 
			throws ClassNotFoundException, SQLException
	{
		//1.Load driver class
		Class.forName("com.mysql.jdbc.Driver");
		
		//2.Create Connection
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdata", "root", "root");
		System.out.println("Connection established");
		//3. Create query
		PreparedStatement stms = con.prepareStatement("insert into signupdata(username,fathername,mothername,mobile,address,email,pass,cpass) values(?,?,?,?,?,?,?,?)");
		stms.setString(1, username);
		stms.setString(2, fathername);
		stms.setString(3, mothername);
		stms.setString(4, mobile);
		stms.setString(5, address);
		stms.setString(6, email);
		stms.setString(7, pass);
		stms.setString(8, cpass);
		
		//4.Execute query
		int i = stms.executeUpdate();
	}
}
