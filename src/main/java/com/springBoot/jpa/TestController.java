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

	@ResponseBody
	@RequestMapping("/add")
	public int test() {
		int a = 20;
		int b = 32;
		return a+b;
	}
	
	@ResponseBody
	@RequestMapping("/subtract")
	public int test4() {
		int c = 52;
		int d = 12;
		return c-d;
	}
	@ResponseBody
	@RequestMapping("/Multiply")
	public int test5() {
		int e = 45;
		int f = 11;
		return e*f;
	}
	@ResponseBody
	@GetMapping("/Devide")
	public int test6() {
		int g = 500;
		int h = 2;
		return g/h;
	}
	
	@ResponseBody
	@PostMapping("/table1")
	public String demo1() {
		String sql = "CREATE TABLE Customers(id int, name String, city String)";
		jdbc.update(sql);
		return "data added successfully!";
	}
	
	
	@RequestMapping("/")
	public String test3() {
		return "welcome";
	}
	
	@ResponseBody
	@RequestMapping("/jdbc")
	public String test2() {
		String sql = "insert into users(id, name) values ('20','test')";
		jdbc.update(sql);
		return "Jdbc Template is working";
	}
	
	@ResponseBody
	@RequestMapping("/dbInsert")
	public String test1() {

		UserRepository user =   SpringBootJpaProjectApplication.context.getBean(UserRepository.class);
			
		Users user2 = new Users();
		user2.setName("Asmit");
		user2.setCity("Manglore");
		user2.setStatus("A java programmer");
		
		Users user1 = new Users();
		user1.setName("Anand");
		user1.setCity("Mysore");
		user1.setStatus("A Python programmer");
		
		Users user3 = new Users();
		user3.setName("Aman");
		user3.setCity("Pune");
		user3.setStatus("A Frontend developer");
		
		List<Users> result =List.of(user1,user2,user3);
		
		user.saveAll(result);
		return "Database Operation";
	}
	
}
