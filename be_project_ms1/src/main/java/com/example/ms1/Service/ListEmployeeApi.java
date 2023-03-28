package com.example.ms1.Service;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.example.ms1.Controller.MainController;
import com.example.ms1.dto.Employee;

@Service
public class ListEmployeeApi {
	
	public ArrayList<Employee> employeelist()
	{
			ArrayList<Employee> randomemp=new ArrayList<Employee>();
			randomemp.add(new Employee(0,"Jaishu"));
			randomemp.add(new Employee(1,"Sai"));
			randomemp.add(new Employee(2,"MJS"));
			randomemp.add(new Employee(3,"Nithya"));
			randomemp.add(new Employee(4,"Manju"));
			randomemp.add(new Employee(5,"Chris"));
			randomemp.add(new Employee(6,"Hari"));
			randomemp.add(new Employee(7,"Srikanth"));
			randomemp.add(new Employee(8,"Rachna"));
			randomemp.add(new Employee(9,"Nandy"));
			randomemp.add(new Employee(10,"Apj"));
			randomemp.add(new Employee(11,"Swetha"));
			randomemp.add(new Employee(12,"Raghu"));
			randomemp.add(new Employee(13,"Swarna"));
			randomemp.add(new Employee(14,"Guru"));
			return randomemp;
	}
	
	public ArrayList<String> apilist()
	{
		ArrayList<String> randomapi=new ArrayList<String>();
		randomapi.add("http://localhost:9002/login");
		randomapi.add("http://localhost:9002/search");
		randomapi.add("http://localhost:9002/request");
		randomapi.add("http://localhost:9002/logout");
		randomapi.add("http://localhost:9002/error");
		return randomapi;
	}
	
}
