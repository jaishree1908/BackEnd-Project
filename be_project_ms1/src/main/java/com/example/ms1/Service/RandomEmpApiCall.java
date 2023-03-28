package com.example.ms1.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.ms1.dto.Employee;
@Service
public class RandomEmpApiCall {

	@Autowired 
	private ListEmployeeApi listEmployeeApi;
	
	@Autowired
	public RestTemplate restTemplate;
	
	Logger logger= Logger.getLogger(RandomEmpApiCall.class.getName());
	
	public void randomempapicall()
	{
		logger.info("Entering randomempapicall method");
		ArrayList<Employee> employeeList = listEmployeeApi.employeelist();
	    Random rand = new Random();
	    Employee randomemployee = employeeList.get(rand.nextInt(employeeList.size()));
	    ArrayList<String> apiList = listEmployeeApi.apilist();
	    Random rand1 = new Random();
	    String randomapi= apiList.get(rand1.nextInt(apiList.size()));
	    String url=randomapi;
		HttpHeaders headers=new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Employee> entity=new HttpEntity<>(randomemployee,headers);
		ResponseEntity<Employee> responseEntity = restTemplate.exchange(
			    url, 
			    HttpMethod.POST, 
			    entity, 
			    Employee.class );
		logger.info("Exiting randomempapicall method");
	}
}
