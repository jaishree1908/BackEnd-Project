package com.example.ms3.Service;

import java.io.IOException;
import java.time.temporal.ChronoUnit;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.example.ms3.Configuration.RedisMessageSubscriber;
import com.example.ms3.Controller.RedisController;
import com.example.ms3.Entity.*;
import com.example.ms3.Repository.StatisticDataDao;
import com.example.ms3.Repository.StatisticInfoDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class StatisticsService {

	@Autowired
	private StatisticInfoDao Statisticinfodao;
	
	@Autowired
	private RedisMessageSubscriber redismsgsubscriber; 
	
	@Autowired
	private StatisticDataDao statisticdatadao;
	
	@Autowired
    com.fasterxml.jackson.databind.ObjectMapper objectMapper;
	
	@Autowired 
    private RedisTemplate<String, StatisticsData> redisTemplate1;
	@Autowired 
    private RedisTemplate<String, StatisticDatabyID> redisTemplate2;
	
	@Autowired 
    private RedisTemplate<String, StatisticDatabyMethod> redisTemplate3;
	
	public static final String HASH_KEY="StatisticData";
	
	public static final String HASH_KEYID="StatisticInfoID";
	
	public static final String HASH_KEYMETHOD="StatisticDataMethod";
	
	Logger logger= Logger.getLogger(StatisticsService.class.getName());
	
	
	public String getbyId(int id) throws IOException
	{	
		try {
		String temp= (String)redisTemplate2.opsForHash().get(HASH_KEYID,id);
		StatisticDatabyID statisticDatabyID = objectMapper.readValue(temp, StatisticDatabyID.class);
		logger.info("Entering getbyId() "+statisticDatabyID);
//	 return ("The name is "+id+".\nThe total number of process is "+Statisticinfodao.statisticsdatabyid.getCount()+
//			 ".\nThe login method count is "+Statisticinfodao.statisticsdatabyid.mapcount.get("login")+
//			 ".\nThe search method count is  "+Statisticinfodao.statisticsdatabyid.mapcount.get("search")+
//			 ".\nThe request method count is "+Statisticinfodao.statisticsdatabyid.mapcount.get("request")+
//			 ".\nThe logout method count is "+Statisticinfodao.statisticsdatabyid.mapcount.get("logout")
//			 +".\nThe error method count is "+Statisticinfodao.statisticsdatabyid.mapcount.get("error")+
//			 ".\nThe minimum time taken is " +Statisticinfodao.statisticsdatabyid.getMintime()+" seconds.\nThe"
//				+ " maximum time taken is "+Statisticinfodao.statisticsdatabyid.getMaxtime()+" seconds.\nThe average time taken is "+Statisticinfodao.statisticsdatabyid.getAvgtime())+" seconds.";
//	
		 return ("The name is "+statisticDatabyID.getName()+".\nThe total number of process is "+statisticDatabyID.getCount()+
				 ".\nThe login method count is "+statisticDatabyID.mapcount.get("login")+
				 ".\nThe search method count is  "+statisticDatabyID.mapcount.get("search")+
				 ".\nThe request method count is "+statisticDatabyID.mapcount.get("request")+
				 ".\nThe logout method count is "+statisticDatabyID.mapcount.get("logout")
				 +".\nThe error method count is "+statisticDatabyID.mapcount.get("error")+
				 ".\nThe minimum time taken is " +statisticDatabyID.getMintime()+" seconds.\nThe"
					+ " maximum time taken is "+statisticDatabyID.getMaxtime()+" seconds.\nThe average time taken is "+statisticDatabyID.getAvgtime())+" seconds.";
		}
		catch(IllegalArgumentException iae)
		{
			logger.error("Exception :: ",iae);
			return "The id : "+id+" has no process going on";
		}
	}
	 
	
	public String getbyMethod(String method) throws IOException
	{

		try {
			String temp = (String) redisTemplate3.opsForHash().get(HASH_KEYMETHOD, method);
			StatisticDatabyMethod statisticdatabymethod = objectMapper.readValue(temp, StatisticDatabyMethod.class);
			logger.info("Entering getbyMethod() "+statisticdatabymethod);
			String datacount = "";
			for (int i = 0; i < 15; i++) {
				if (statisticdatabymethod.getMethodcount().get(i) != 0)
					datacount += "The id of " + i + " : " + statisticdatabymethod.getMethodcount().get(i) + "\n";
			}
//			return (datacount + "The method is " + method + ".\nThe total count of process is "
//					+ Statisticinfodao.statisticsdatabymethod.getCount() + ".\nThe minimum time taken is "
//					+ Statisticinfodao.statisticsdatabymethod.getMintime() + " seconds.\nThe" + " maximum time taken is "
//					+ Statisticinfodao.statisticsdatabymethod.getMaxtime() + " seconds.\nThe average time taken is "
//					+ Statisticinfodao.statisticsdatabymethod.getAvgtime() + " seconds.");
			
			return (datacount + "The method is " + method + ".\nThe total count of process is "
					+ statisticdatabymethod.getCount() + ".\nThe minimum time taken is "
					+ statisticdatabymethod.getMintime() + " seconds.\nThe" + " maximum time taken is "
					+ statisticdatabymethod.getMaxtime() + " seconds.\nThe average time taken is "
					+ statisticdatabymethod.getAvgtime() + " seconds.");
		} catch (IllegalArgumentException iae) {
			logger.error("Exception :: ",iae);
			return "The method : " + method + " has no process going on";
		}
		catch(Exception e)
		{
			logger.error("Exception :: ",e);
			return "The method : " + method + " has no process going on";
		}
		
	}
	
	public String getStatisticalData() {
		logger.info("Entering getStatisticalData()");
			String temp1 = (String) redisTemplate1.opsForHash().get(HASH_KEY, 0);
			
			try {
				
				StatisticsData statsdata = objectMapper.readValue(temp1, StatisticsData.class);
				System.out.println("statisticaldata1 : "+statsdata);
				if(statsdata.getCount()==0)
				{   logger.info("Exiting getStatisticalData()");
					return "Please wait for a while to generate statistical data report";
					
				}
				else 	
				{ 
					logger.info("Exiting getStatisticalData()");
					return ("The total number of process is "+statsdata.getCount()+".\nThe minimum time taken is " +statsdata.getMintime()+" seconds.\nThe"
						+ " maximum time taken is "+statsdata.getMaxtime()+" seconds.\nThe average time taken is "+statsdata.getAvgtime())+" seconds.";
				}
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				logger.error("Exception :: ",e);
				return "Please wait for a while to generate statistical data report";
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				logger.error("Exception :: ",e);
				return "Please wait for a while to generate statistical data report";
			}
			catch(IllegalArgumentException iae)
			{
				logger.error("Exception :: ",iae);
				return "Please wait for a while to generate statistical data report";
			}
			catch(Exception e)
			{
				logger.error("Exception :: ",e);
				return "Please wait for a while to generate statistical data report";
			}
		
	}

}
