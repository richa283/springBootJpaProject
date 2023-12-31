package com.springBoot.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springBoot.jpa.dao.UserRepository;
import com.springBoot.jpa.entites.Users;

@Controller
public class TestControllerB {

	@Autowired
	JdbcTemplate jdbc;
	
	@ResponseBody
	@PostMapping("/createTables")
	public String CreateTables() {
		String sql1 = "CREATE TABLE Employees (EmployeeID int PRIMARY KEY, name varchar(50), department varchar(50))";
		jdbc.update(sql1);
		
		String sql2 = "CREATE TABLE Customers (CustomerID int PRIMARY KEY, name varchar(50), address varchar(50))";
		jdbc.update(sql2);
		
		String sql3 = "CREATE TABLE Products (ProductID int PRIMARY KEY, name varchar(50), price varchar(50))";
		jdbc.update(sql3);
		
		String sql4 = "CREATE TABLE Orders (OrderID int PRIMARY KEY, CustomerID int, totalamount varchar(50))";
		jdbc.update(sql4);
		
		String sql5 = "CREATE TABLE Logins (UserID int PRIMARY KEY, Username varchar(50), password varchar(50))";
		jdbc.update(sql5);
		
		return "tables created";
	}
	
	@ResponseBody
	@PostMapping("/alterTables")
	public String AlterTable() {
		String tablename1 = "Employees";
		String sql = "ALTER TABLE" +tablename1+ "ADD COLUMN position varchar(50)";
		jdbc.update(sql);
		
		String tablename2 = "Customers";
		String sql2 = "ALTER TABLE" +tablename2+ "ADD COLUMN contact_details varchar(50)";
		jdbc.update(sql2);
		
		String tablename3 = "Products";
		String sql3 = "ALTER TABLE" +tablename3+ "ADD COLUMN Description varchar(50)";
		jdbc.update(sql3);
		
		String tablename4 = "Orders";
		String sql4 = "ALTER TABLE" +tablename4+ "ADD COLUMN Order_date varchar(50)";
		jdbc.update(sql4);
		
		String tablename5 = "Logins";
		String sql5 = "ALTER TABLE" +tablename5+ "ADD COLUMN last_login_time varchar(50)";
		jdbc.update(sql5);
		
	
		return "Table altered";
	}
	
	@ResponseBody
	@PostMapping("/insertTables")
	public String InsertTables() {
		String tablename1 = "Employees";
		String sql1 = "INSERT INTO" +tablename1+ "(EmployeeID, name, department) VALUES (1, Vaishali, MECH)";
		jdbc.update(sql1);
		
		String tablename2 = "Customers";
		String sql2 = "INSERT INTO" +tablename2+ "(CustomerID, name, address) VALUES (48, Surbhi, Hyderabad)";
		jdbc.update(sql2);
		
		String tablename3 = "Products";
		String sql3 = "INSERT INTO" +tablename3+ "(ProductID, name, price) VALUES (201, Laptop, 23456)";
		jdbc.update(sql3);
		
		String tablename4 = "Orders";
		String sql4 = "INSERT INTO" +tablename4+ "(OrderID, CustomerID, totalamount) VALUES (13, 23, 897)";
		jdbc.update(sql4);
		
		String tablename5 = "Logins";
		String sql5 = "INSERT INTO" +tablename5+ "(UserID, Username, password) VALUES (2, RICHA, richa)";
		jdbc.update(sql5);
		return sql5;
		
	}	
		
	@ResponseBody
	@PostMapping("/addStrings")
	public String addStrings() {
		String str1 = "Hello";
		String str2 = "World";
		
		String result = str1+str2;
		return result;
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
