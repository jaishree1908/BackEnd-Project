package com.example.ms2.Service;

import java.time.LocalDateTime;
import java.util.Random;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ms2.Repository.StatisticInfoDao;
import com.example.ms2.publisher.MessagePublisher;
import com.example.ms3.Entity.StatisticInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
@Service
public class service {

	@Autowired
	private MessagePublisher messagePublisher;
	
	@Autowired
	private StatisticInfoDao statisticinfodao; 
	
	Logger logger= Logger.getLogger(service.class.getName());
	
	public StatisticInfo servicecall(int id, String name, String method) throws InterruptedException, JsonProcessingException
	{
		logger.info("Entering "+method+" servicecall method");
		Random random=new Random();
		LocalDateTime startdatetime=LocalDateTime.now();
		Thread.sleep(random.nextInt(3,30)*1000);
		LocalDateTime enddatetime=LocalDateTime.now();
		StatisticInfo stsinfo=new StatisticInfo(id,name,method,startdatetime,enddatetime);
		messagePublisher.publish(stsinfo);
		statisticinfodao.save(stsinfo);
		System.out.println(stsinfo);
		logger.info("Exiting "+method+" servicecall method");
		return stsinfo;
	}
}
