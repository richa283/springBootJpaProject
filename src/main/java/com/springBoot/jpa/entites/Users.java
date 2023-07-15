package com.springBoot.jpa.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Users")
public class Users {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name = "id")
	public int id;
	
	@Column
	public String name;
	
	@Column
	public String city;
	
	@Override
	public String toString() {
		return "users [id=" + id + ", name=" + name + ", city=" + city + ", status=" + status + "]";
	}

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Users(int id, String name, String city, String status) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.status = status;
	}

	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}	
	


}