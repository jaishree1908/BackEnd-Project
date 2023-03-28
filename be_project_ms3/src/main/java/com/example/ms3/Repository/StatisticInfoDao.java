package com.example.ms3.Repository;

import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.ms3.Configuration.RedisMessageSubscriber;
import com.example.ms3.Controller.RedisController;
import com.example.ms3.Entity.*;
import com.example.ms3.Service.Substatsservice;

@Repository
public class StatisticInfoDao extends Substatsservice {
	
	    @Autowired 
	    private RedisTemplate<String, StatisticsData> redisTemplate1;
	    @Autowired 
	    private RedisTemplate<String, StatisticDatabyID> redisTemplate2;
	    
	    @Autowired 
	    private RedisTemplate<String, StatisticDatabyMethod> redisTemplate3;
	    @Autowired
	    com.fasterxml.jackson.databind.ObjectMapper objectMapper;
	    

	    public static final String HASH_KEY = "StatisticInfo";
	    public static final String HASH_KEYID="StatisticInfoID";
	    public static final String HASH_KEYMETHOD="StatisticDataMethod";
	    public StatisticDatabyID statisticsdatabyid=new StatisticDatabyID();
	    public StatisticDatabyMethod statisticsdatabymethod=new StatisticDatabyMethod();
	    
	    Logger logger= Logger.getLogger(StatisticInfoDao.class.getName());

	
	   
	   
   
	    public StatisticDatabyID updateStatisticsinfoid(StatisticInfo stsinfo1) throws IOException {
	    	logger.info("Entering updateStatisticsinfoid() "+stsinfo1);
	    	int id =stsinfo1.getId();
	    	statisticsdatabyid=updatestatitisticsdata(stsinfo1,id);
			logger.info("Exiting updateStatisticsinfoid() "+stsinfo1);
	        return statisticsdatabyid;
	    }

	    private void saveData(int id, StatisticDatabyID emp) {
	    	try {
	    		logger.info("Entering saveData () "+emp);
		    	String empAsString = objectMapper.writeValueAsString(emp);
		     	redisTemplate2.opsForHash().put(HASH_KEYID,id,empAsString);
		     	logger.info("Exiting saveData () "+emp);
	    	} catch (Exception e) {
	    		logger.error("Exception :: ",e);
	    	}
		}

		public StatisticDatabyID updatestatitisticsdata(StatisticInfo stsinfo2,int id) throws IOException
		{
			logger.info("Entering updatestatitisticsdata ()"+stsinfo2);
			StatisticDatabyID statisticsdatabyid1 = new StatisticDatabyID();

			if (redisTemplate1.opsForHash().get(HASH_KEYID, id) == null) {

				StatisticDatabyID emp = new StatisticDatabyID(id, "", 0, 0, 0, 0, 0, 9999, -1, 0, 0);
				HashMap<String, Integer> map = new HashMap<String, Integer>();
				emp.setMapcount(map);
				saveData(id, emp);

			} else {

				String temp = (String) redisTemplate2.opsForHash().get(HASH_KEYID, id);
				statisticsdatabyid1 = objectMapper.readValue(temp, StatisticDatabyID.class);
			}
			if (statisticsdatabyid1 != null) {

				statisticsdatabyid1.setId(id);
				statisticsdatabyid1.setName(stsinfo2.getName());

				String method=stsinfo2.getMethod();
				if (statisticsdatabyid1.getMapcount().containsKey(method)) {
					statisticsdatabyid1.getMapcount().replace(method, statisticsdatabyid1.getMapcount().get(method),
							statisticsdatabyid1.getMapcount().get(method) + 1);
				}

//				float min_diffintime = ChronoUnit.MILLIS.between(stsinfo2.getEntrydatetime(),
//						stsinfo2.getExitdatetime());
//				float minOldtime = statisticsdatabyid1.getMintime() * 1000;
//				if (min_diffintime < minOldtime) {
//					statisticsdatabyid1.setMintime(min_diffintime / 1000);
//				}
//
//				// set max time diff
//				float max_diffintime = ChronoUnit.MILLIS.between(stsinfo2.getEntrydatetime(),
//						stsinfo2.getExitdatetime());
//				float maxOldtime = statisticsdatabyid1.getMaxtime() * 1000;
//				if (max_diffintime > maxOldtime) {
//					statisticsdatabyid1.setMaxtime(max_diffintime / 1000);
//				}
//
//				float oldSumtime = statisticsdatabyid1.getAvgtime() * statisticsdatabyid1.getCount() * 1000;
//				statisticsdatabyid1.setCount(statisticsdatabyid1.getCount() + 1);
//				float timediff = ChronoUnit.MILLIS.between(stsinfo2.getEntrydatetime(), stsinfo2.getExitdatetime());
//				statisticsdatabyid1.setAvgtime(((oldSumtime + timediff) / statisticsdatabyid1.getCount()) / 1000);

				statisticsdatabyid1=setminstatisticdataid(stsinfo2,statisticsdatabyid1);
				statisticsdatabyid1=setmaxstatisticdataid(stsinfo2,statisticsdatabyid1);
				statisticsdatabyid1=setcountavgstatisticdataid(stsinfo2,statisticsdatabyid1);
				
				
				saveData(id, statisticsdatabyid1);
			}
			
			logger.info("Exiting updatestatitisticsdata() "+stsinfo2);
			return statisticsdatabyid;
	    }


	     public StatisticDatabyMethod updateStatisticsinfomethod(StatisticInfo stsinfo1) throws IOException {

	    	 	logger.info("Entering updateStatisticsinfomethod ()"+stsinfo1);
		    	String method =stsinfo1.getMethod();
		    	statisticsdatabymethod=updatestatitisticsdatamethod(stsinfo1,method);
				logger.info("Exiting updateStatisticsinfoid() "+stsinfo1);
		        return statisticsdatabymethod;
			}	     	     

	     
	     private void saveDatamethod(String method, StatisticDatabyMethod emp) {
		    	try {
		    		logger.info("Entering saveDatamethod() "+emp);
			    	String empAsString = objectMapper.writeValueAsString(emp);
			     	redisTemplate2.opsForHash().put(HASH_KEYMETHOD,method,empAsString);
			     	logger.info("Exiting saveDatamethod() "+emp);
		    	} catch (Exception e) {
		    		logger.error("Exception :: ",e);
		    	}
			}
	     
	     public StatisticDatabyMethod updatestatitisticsdatamethod(StatisticInfo stsinfo2,String method) throws IOException
		    {
	    	 
	    	 	logger.info("Entering updatestatitisticsdatamethod() "+stsinfo2);
				StatisticDatabyMethod statisticsdatabymethod = new StatisticDatabyMethod();

				if (redisTemplate1.opsForHash().get(HASH_KEYMETHOD, method) == null) {
					StatisticDatabyMethod emp = new StatisticDatabyMethod(method, new HashMap<Integer, Integer>(),
							9999999, -1, 0, 0);
					saveDatamethod(method, emp);
				} else {
					String temp = (String) redisTemplate3.opsForHash().get(HASH_KEYMETHOD, method);
					statisticsdatabymethod = objectMapper.readValue(temp, StatisticDatabyMethod.class);
				}
				if (statisticsdatabymethod != null) {
					statisticsdatabymethod.setMethod(method);

					if (statisticsdatabymethod.getMethodcount().containsKey(stsinfo2.getId())) {
						statisticsdatabymethod.getMethodcount().replace(stsinfo2.getId(),
								statisticsdatabymethod.getMethodcount().get(stsinfo2.getId()),
								statisticsdatabymethod.getMethodcount().get(stsinfo2.getId()) + 1);
					}

					statisticsdatabymethod=setminstatisticdatamethod(stsinfo2,statisticsdatabymethod);
					statisticsdatabymethod=setmaxstatisticdatamethod(stsinfo2,statisticsdatabymethod);
					statisticsdatabymethod=setcountavgstatisticdatamethod(stsinfo2,statisticsdatabymethod);
					
					saveDatamethod(method, statisticsdatabymethod);
				}
				logger.info("Exiting updatestatitisticsdata() "+stsinfo2);
				return statisticsdatabymethod;
		    }
			

	
		
}