package com.springBoot.jpa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springBoot.jpa.dao.UserRepository;
import com.springBoot.jpa.entites.Users;

@Controller
public class TestController {

	@Autowired
	JdbcTemplate jdbc;
	
	
	@RequestMapping("/Login")
	public String testLogin() {
		
		return "welcome";
	}
	
	@ResponseBody
	@RequestMapping("/LoginServlet")
	public String testLogin( @RequestParam String email,@RequestParam String password) {
		
		String emailid = email;
		String pass = password;
		
		return "Login successful by "+ emailid;
	}
	

	@RequestMapping("/signUp")
	public String signUpHandlerMethod() 
	{
		return "signUp";
	}
	
	@ResponseBody
	@RequestMapping("/signUpHandlerMethod")
	public String signUpHandlerMethod(@RequestParam String username, @RequestParam String fathername, @RequestParam String mothername,
			@RequestParam String mobile, @RequestParam String address, @RequestParam String email)
	throws ClassNotFoundException, SQLException
	{
		saveData(username,fathername,mothername,mobile,address,email);
		
		String name = username;
		String fname = fathername;
		String mname = mothername;
		String mob = mobile;
		String add = address;
		String mail = email;
		return "Sign up successful by " +name+" !";
	}

	public static void saveData(String username,String fathername,String mothername,String mobile,String address,String email) throws ClassNotFoundException, SQLException
	{
		//1.Load driver class
		Class.forName("com.mysql.jdbc.Driver");
		
		//2.Create Connection
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdata", "root", "root");
		System.out.println("Connection established");
		//3. Create query
		PreparedStatement stms = con.prepareStatement("insert into signupdata(username,fathername,mothername,mobile,address,email) values(?,?,?,?,?,?)");
		stms.setString(1, username);
		stms.setString(2, fathername);
		stms.setString(3, mothername);
		stms.setString(4, mobile);
		stms.setString(5, address);
		stms.setString(6, email);
		
		//4.Execute query
		int i = stms.executeUpdate();
	}
	
	@RequestMapping("/")
	public String test3() {
		return "welcome";
	}

}
