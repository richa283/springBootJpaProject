package com.springBoot.jpa;

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
	@RequestMapping("/add")
	public int test() {
		int a = 20;
		int b = 32;
		return a+b;
	}
	
	@ResponseBody
	@RequestMapping("/login")
	public String testLogin( @RequestParam String username,@RequestParam String password) {
		
		String name = username;
		String pass = password;
		
		return "Hi "+ name;
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
