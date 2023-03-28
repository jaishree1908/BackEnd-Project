package com.example.ms2.controller;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.ms3.Controller.RedisController;
import com.example.ms3.Entity.StatisticInfo;
import com.example.ms1.dto.Employee;
import com.example.ms2.Repository.StatisticInfoDao;
import com.example.ms2.Service.service;
import com.example.ms2.publisher.MessagePublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class MethodController {

	
	 
	 public static List<StatisticInfo> statisicinfofull=new ArrayList<StatisticInfo>();

	@Autowired
	private service services;
	
	@PostMapping("/login")
	@ResponseBody
	public StatisticInfo loginpage(@RequestBody Employee employee) throws InterruptedException, JsonProcessingException
	{
		return services.servicecall(employee.getId(), employee.getName(), "login");
		
	}
	
	@PostMapping("/search")
	@ResponseBody
	public StatisticInfo searchpage(@RequestBody Employee employee) throws InterruptedException, JsonProcessingException
	{
		return services.servicecall(employee.getId(), employee.getName(), "search");
	}
	
	@PostMapping("/request")
	@ResponseBody
	public StatisticInfo requestpage(@RequestBody Employee employee) throws InterruptedException, JsonProcessingException
	{
		return services.servicecall(employee.getId(), employee.getName(), "request");
	}
	
	@PostMapping("/logout")
	@ResponseBody
	public StatisticInfo logoutpage(@RequestBody Employee employee) throws InterruptedException, JsonProcessingException
	{
		return services.servicecall(employee.getId(), employee.getName(), "logout");
	}

	@PostMapping("/error")
	@ResponseBody
	public StatisticInfo errorpage(@RequestBody Employee employee) throws InterruptedException, JsonProcessingException
	{
		return services.servicecall(employee.getId(), employee.getName(), "error");
	}
	
	
}
