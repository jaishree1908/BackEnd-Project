package com.example.ms3.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import com.example.ms3.Configuration.RedisMessageSubscriber;
import com.example.ms3.Entity.StatisticsData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class StatisticDataDao {

	 public static final String HASH_KEY = "StatisticData";
	 public static final String HASH_KEYID="StatisticsIndoID";
	    
	    @Autowired 
	    private RedisTemplate<String, StatisticsData> redisTemplate1;
	    
	    @Autowired
	    private ObjectMapper objectMapper;
	    
	    Logger logger= Logger.getLogger(StatisticDataDao.class.getName());
	    
	    public List<StatisticsData> getStatisticsData(){
	    	
	    	logger.info("Entering getStatisticsData() ");
	    	
	    	List<StatisticsData> resultSet = new ArrayList<>();
	    	List<Object> objLst = redisTemplate1.opsForHash().values(HASH_KEY);
	    	if (objLst != null && !objLst.isEmpty()) {
	    		objLst.stream().forEach(obj-> {
	    			StatisticsData statisticdata = null;
					try {
						statisticdata = objectMapper.readValue((String)obj, StatisticsData.class);
						logger.info("Inside getStatisticsData() statisticdata : "+statisticdata);
					} catch (Exception e) {}
					if (statisticdata != null) {
						resultSet.add(statisticdata);
					}
	    		});
	    	}
	    	logger.info("Exiting getStatisticsData() ");
	    	return resultSet;
	     }
	    
	    public StatisticsData save(StatisticsData employee) throws JsonProcessingException
	    {
	    	logger.info("Entering save() "+employee);
	    	Random rand=new Random();
	    	StatisticsData emp = new StatisticsData(0,employee.getMintime(),employee.getMaxtime(),employee.getCount(),employee.getAvgtime());
	     	String empAsString = objectMapper.writeValueAsString(emp);
	     	redisTemplate1.opsForHash().put(HASH_KEY,0,empAsString);
	     	
	     	logger.info("Exiting save() "+employee);
	        return employee;
	     }
	    
	    public StatisticsData savebyid(StatisticsData employee,int id) throws JsonProcessingException
	    {
	    	logger.info("Entering savebyid() "+employee);
	    	
	    	Random rand=new Random();
	    	StatisticsData emp = new StatisticsData(id,employee.getMintime(),employee.getMaxtime(),employee.getCount(),employee.getAvgtime());
	     	String empAsString = objectMapper.writeValueAsString(emp);
	     	redisTemplate1.opsForHash().put(HASH_KEYID,id,empAsString);
	     	logger.info("Exiting savebyid() "+employee);
	     	
	        return employee;
	     }
}
