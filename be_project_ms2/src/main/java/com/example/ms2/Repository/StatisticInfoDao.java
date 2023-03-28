package com.example.ms2.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.ms2.publisher.RedisMessagePublisher;
import com.example.ms3.Entity.*;
@Repository
public class StatisticInfoDao {

	 public static final String HASH_KEY = "StatisticInfo";
	 public static final String HASH_KEYID = "StatisticInfoID";
	    
	 @Autowired 
	 private RedisTemplate<String, StatisticInfo> redisTemplate;
	    
	 @Autowired
	 private ObjectMapper objectMapper;

	 Logger logger= Logger.getLogger(StatisticInfoDao.class.getName());
	 
	 
	  	public StatisticInfo save(StatisticInfo employee) throws JsonProcessingException
	    {
	  		logger.info("Entering StatisticInfo method");
	     	StatisticInfo emp = new StatisticInfo(employee.getId(),employee.getName(),employee.getMethod(),employee.getEntrydatetime(),employee.getExitdatetime());
	     	String empAsString = objectMapper.writeValueAsString(emp);
	     	redisTemplate.opsForHash().put(HASH_KEY,employee.getId(),empAsString);
	     	logger.info("Exiting StatisticInfo method");
	        return employee;
	     }
     
}