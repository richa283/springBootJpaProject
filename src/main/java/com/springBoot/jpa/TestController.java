package com.springBoot.jpa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServlet;

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
	
	
	@RequestMapping("/LoginHandlerMethod")
	public String userLogin(
			@RequestParam String email,
			@RequestParam String password
			) throws ClassNotFoundException, SQLException
	{
		String emailid = email;
		String pass = password;
		
		//pending code to validate login
		
	return "homepage";
	}
			
	public static boolean saveData(String email, String pass) throws ClassNotFoundException, SQLException
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdata", "root", "root");
				System.out.println("Connection established");
				//3. Create query
				String query = "Select * FROM signupdata WHERE email=? AND pass=?";
				PreparedStatement stms = con.prepareStatement(query);
				stms.setString(6, email);
				stms.setString(7, pass);
				ResultSet rst = stms.executeQuery();

				// 5. Check if user exists and if the provided password matches the hashed password
		        boolean loginSuccessful = rst.next();

		        // 6. Close resources
		        rst.close();
		        stms.close();
		        con.close();

		        return loginSuccessful;
			}

			
			
			
	@RequestMapping("/signUp")
	public String signUpHandlerMethod() 
	{
		return "signUp";
	}
	
	@ResponseBody
	@RequestMapping("/signUpHandlerMethod")
	public String signUpHandlerMethod(
			@RequestParam String username, 
			@RequestParam String fathername, 
			@RequestParam String mothername, 
			@RequestParam String mobile, 
			@RequestParam String address, 
			@RequestParam String email,
			@RequestParam String pass,
			@RequestParam String cpass
			)throws ClassNotFoundException, SQLException
	{
		
		//Validating
		if(!isValidName(username))
		{
			return "Invalid username. Name should not contain any digits";
		}
		if(!isValidfname(fathername))
		{
			return "This field should not contain any digits";
		}
		if(!isValidmname(mothername))
		{
			return "This field should not contain any digits";
		}
		if(!isValidMobile(mobile))
		{
			return "Invalid mobile number. Mobile number should be 10 digit number";
		}
		if(!isValidAddress(address))
		{
			return "Invalid address format";
		}
		if(!isValidEmail(email))
		{
			return "Invalid email format";
		}
		if(!isValidPass(pass))
		{
			return "Atleast 1 capital,1 small, 1 digit and 1 alphanumeric is required";
		}
		if(!isValidCpass(pass,cpass))
		{
			return "Password and confirm password do not match";
		}
		
		//Save data to database
		saveData(username,fathername,mothername,mobile,address,email,pass,cpass);
		
		String name = username;
		String fname = fathername;
		String mname = mothername;
		String mob = mobile;
		String add = address;
		String mail = email;
		String ps = pass;
		String cps = cpass;
		return "Sign up successful by " +name+" !";
	}

	//Helper method to check if the name is valid (does not contain any digits)
	private boolean isValidName(String name)
	{
		return !Pattern.compile("[0-9]").matcher(name).find();
	}
	private boolean isValidfname(String fathername)
	{
		return !Pattern.compile("[0-9]").matcher(fathername).find();
	}
	private boolean isValidmname(String mothername)
	{
		return !Pattern.compile("[0-9]").matcher(mothername).find();
	}
	private boolean isValidMobile(String mobile)
	{
		return Pattern.compile("\\d{10}").matcher(mobile).matches();
	}
	private boolean isValidAddress(String address)
	{
		return Pattern.compile("[A-Za-z0-9'\\.\\-\\s\\,]+").matcher(address).matches();
	}
	private boolean isValidEmail(String email)
	{
		return Pattern.compile("^[A-Za-z0-9._]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$").matcher(email).matches();
	}
	private boolean isValidPass(String pass)
	{
		return Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$").matcher(pass).matches();
	}
	private boolean isValidCpass(String pass, String cpass)
	{
		return pass.equals(cpass); 
	}
	
	
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
	
	@RequestMapping("/")
	public String test3() {
		return "welcome";
	}
	
	@ResponseBody
	@RequestMapping("/FetchUser")
	public String userFetch() throws ClassNotFoundException, SQLException
	{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdata", "root", "root");
				System.out.println("Connection established");
				String sqlquery = "Select * from signupdata where email=?";
				
				PreparedStatement stms = con.prepareStatement(sqlquery);
				stms.setString(1, "samuel@gmail.com");
				ResultSet rst = stms.executeQuery();
				
				String result = "";
				
				while(rst.next()) 
				{			
					result=result+rst.getString(2)+" "+rst.getString(5)+" "+rst.getString(7);

		            System.out.print(rst.getString(2));
		            System.out.print("\t\t"+rst.getString(5));
		            System.out.print("\t\t"+rst.getString(7));
				}
				return result;
	}

}
