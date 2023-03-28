package com.example.ms1.dto;

import org.springframework.data.annotation.Id;
import jakarta.persistence.Entity;

//@Entity
public class Employee {

	@Id
	private int id;
	private String name;
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
	public Employee(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Employee() {}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + "]";
	}
	
}
