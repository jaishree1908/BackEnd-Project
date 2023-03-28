package com.example.ms3.Controller;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.ms3.Configuration.RedisMessageSubscriber;
import com.example.ms3.Entity.StatisticInfo;
import com.example.ms3.Service.StatisticsService;


@RestController
public class RedisController {


	 @Autowired
	 private StatisticsService redisservice;
	 
	 
	 Logger logger= Logger.getLogger(RedisController.class.getName());
	 
	 @RequestMapping(value = "fetchbyid/{id}", method = RequestMethod.GET)
	 public String fetchById(@PathVariable int id) throws IOException
	 {
		 logger.info("Entering fetchById");
		 return redisservice.getbyId(id);
		 
	 }
	 
	 
	 @RequestMapping(value = "fetchbymethod/{method}", method = RequestMethod.GET)
	 public String fetchByMethod(@PathVariable String method) throws IOException
	 {
		 logger.info("Entering fetchByMethod");
		 return redisservice.getbyMethod(method);
	 }
	 
	 @RequestMapping(value = "statisticalData", method = RequestMethod.GET, produces="application/json")
	 public String fetchstatisticalData()
	 {
		 logger.info("Entering fetchstatisticalData");
		 return redisservice.getStatisticalData();
	 }


}
