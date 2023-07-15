package com.springBoot.jpa.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.springBoot.jpa.entites.Users;

public interface UserRepository extends CrudRepository<Users, Integer>{
	public List<Users> findByName(String name);

}
