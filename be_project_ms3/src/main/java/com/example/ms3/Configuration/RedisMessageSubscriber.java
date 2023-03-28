package com.example.ms3.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.example.ms3.Entity.StatisticDatabyID;
import com.example.ms3.Entity.StatisticDatabyMethod;
import com.example.ms3.Entity.StatisticInfo;
import com.example.ms3.Entity.StatisticsData;
import com.example.ms3.Repository.StatisticDataDao;
import com.example.ms3.Repository.StatisticInfoDao;
import com.example.ms3.Service.Substatsservice;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.log4j.Logger;

@Service
public class RedisMessageSubscriber extends Substatsservice implements MessageListener {

	public static final String HASH_KEY="StatisticData";
	public static final String HASH_KEYID="StatisticInfoID";
	public static final String HASH_KEYMETHOD="StatisticDataMethod";
    public StatisticsData statisticaldata=new StatisticsData();
	
	@Autowired
    com.fasterxml.jackson.databind.ObjectMapper objectMapper;
	
	@Autowired
	private StatisticInfoDao statisticInfoDao;
    
    @Autowired
    private StatisticDataDao statisticdatadao;
    
    @Autowired
    private RedisTemplate<String, StatisticsData> redisTemplate1; 
    
    private final static Logger logger= Logger.getLogger(RedisMessageSubscriber.class.getName());

    
		@Override
		public void onMessage(Message message, byte[] pattern) {

			
			byte[] body = message.getBody();
			StatisticInfo stsinfo2 = null;
			try {
				stsinfo2 = objectMapper.readValue(body, StatisticInfo.class);
				logger.info("Entering onMessage method "+stsinfo2);
				statisticInfoDao.updateStatisticsinfoid(stsinfo2);
				statisticInfoDao.updateStatisticsinfomethod(stsinfo2);
			} 
			catch (IOException e) {
				logger.error("Exception :: ",e);
			}
			try {
				
				if (redisTemplate1.opsForHash().get(HASH_KEY, 0) == null) {
			
					StatisticsData emp = new StatisticsData(0, 9999,-1,0,0);
					savestatisticsData(0, emp);

				} else {
					String temp1 = (String) redisTemplate1.opsForHash().get(HASH_KEY, 0);
					statisticaldata = objectMapper.readValue(temp1, StatisticsData.class);
					
				}
				
				updateStatisticsinfo(stsinfo2);
			}
			catch (JsonProcessingException e) {
				logger.error("Exception :: ",e);
			} 
			catch (IOException e) {
				logger.error("Exception :: ",e);
			}

			
			logger.info("Exiting onMessage method " + stsinfo2);
		}

		private void updateStatisticsinfo(StatisticInfo stsinfo2) throws IOException { 
			logger.info("Entering updateStatisticsinfo method "+stsinfo2);
			
			statisticaldata=setminstatisticdata(stsinfo2,statisticaldata);
			statisticaldata=setmaxstatisticdata(stsinfo2,statisticaldata);
			statisticaldata=setcountavgstatisticdata(stsinfo2,statisticaldata);
			
			logger.info("Exiting updateStatisticsinfo method "+stsinfo2);
		}
		
		  private void savestatisticsData(int id, StatisticsData emp) {
		    	try {
		    		logger.info("Entering savestatisticsData method "+emp);
			    	String empAsString = objectMapper.writeValueAsString(emp);
			     	redisTemplate1.opsForHash().put(HASH_KEY,id,empAsString);
			     	logger.info("Exiting savestatisticsData method "+emp);
		    	} catch (Exception e) {
		    		logger.error("Exception :: ",e);
		    	}
			}
	
		
    
}

