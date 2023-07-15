package com.springBoot.jpa;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.sql.DataSource;
import javax.swing.text.html.HTMLDocument.Iterator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.springBoot.jpa.dao.UserRepository;
import com.springBoot.jpa.entites.Users;

@SpringBootApplication
public class SpringBootJpaProjectApplication {

	static ConfigurableApplicationContext context;
	
	public static void main(String[] args) {
		
		 context= SpringApplication.run(SpringBootJpaProjectApplication.class, args);
		UserRepository userRepository= context.getBean(UserRepository.class);
		
		//jpaMethodTest(userRepository);
	//	SpringApplication.run(SpringBootJpaProjectApplication.class, args);
	}
	
	@Bean
	public DataSource datasource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		dataSource.setUrl("jdbc:mysql://localhost:3306/gjschool2");
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource());
		return jdbcTemplate;
	}

	
	public static void jpaMethodTest(UserRepository userRepository) {
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
		//here we are saving multiple objects by using saveAll. And traversing it by using Iterable.
		Iterable<Users> resultIterator = userRepository.saveAll(result);
			resultIterator.forEach(users ->{
				System.out.println(resultIterator);
		});
	}
	
	
	}
