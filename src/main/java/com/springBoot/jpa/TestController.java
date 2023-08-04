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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springBoot.Bean.SignUpBean;
import com.springBoot.Model.SignUpModel;
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
			@RequestParam String password,
			Model model
			) throws ClassNotFoundException, SQLException
	{
		String emailid = email;
		String pass = password;
		
		//pending code to validate login
		
		
		boolean validUser = saveData(emailid, pass);
		if(validUser){
			return "homepage";
		}else {
			model.addAttribute("validateUser", "User Does not Exist!! Please SignUp");
			return "welcome";	
		}
	}
			
	public static boolean saveData(String email, String pass) throws ClassNotFoundException, SQLException
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdata", "root", "root");
				System.out.println("Connection established");
				//3. Create query
				String query = "Select * FROM signupdata WHERE email=? AND pass=?";
				PreparedStatement stms = con.prepareStatement(query);
				stms.setString(1, email);
				stms.setString(2, pass);
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
	public String signUpHandlerMethod(SignUpBean Bean)throws ClassNotFoundException, SQLException
	{
		
		//Validating
		if(!isValidName(Bean.getUsername()))
		{
			return "Invalid username. Name should not contain any digits";
		}
		if(!isValidfname(Bean.getFathername()))
		{
			return "This field should not contain any digits";
		}
		if(!isValidmname(Bean.getMothername()))
		{
			return "This field should not contain any digits";
		}
		if(!isValidMobile(Bean.getMobile()))
		{
			return "Invalid mobile number. Mobile number should be 10 digit number";
		}
		if(!isValidAddress(Bean.getAddress()))
		{
			return "Invalid address format";
		}
		if(!isValidEmail(Bean.getEmail()))
		{
			return "Invalid email format";
		}
		if(!isValidPass(Bean.getPass()))
		{
			return "Atleast 1 capital,1 small, 1 digit and 1 alphanumeric is required";
		}
		if(!isValidCpass(Bean.getPass(),Bean.getCpass()))
		{
			return "Password and confirm password do not match";
		}
		
		//Save data to database
		SignUpModel.saveData(Bean.getUsername(),Bean.getFathername(),Bean.getMothername(),Bean.getMobile(),Bean.getAddress(),Bean.getEmail(),Bean.getPass(),Bean.getCpass());
		
		return "SignUp successful!";
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
