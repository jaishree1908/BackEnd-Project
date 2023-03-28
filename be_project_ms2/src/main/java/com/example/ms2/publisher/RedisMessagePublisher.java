package com.example.ms2.publisher;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;
import com.example.ms1.Service.RandomEmpApiCall;
import com.example.ms1.dto.Employee;
import com.example.ms3.Entity.StatisticInfo;

@Component
public class RedisMessagePublisher implements MessagePublisher {

    @Autowired
    private RedisTemplate<String,StatisticInfo> redisTemplate;

    @Autowired
    private ChannelTopic topic;
    
	Logger logger= Logger.getLogger(RedisMessagePublisher.class.getName());


    public RedisMessagePublisher(RedisTemplate<String, StatisticInfo> redisTemplate, ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
        this.topic = topic;
    }

    public RedisMessagePublisher() {
    }

	public void publish(StatisticInfo stsinfo) {
		logger.info("Entering publish method");
		redisTemplate.convertAndSend(topic.getTopic(),stsinfo);
		logger.info("Entering publish method");
	}

}
