package com.example.ms2.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import com.example.ms3.Entity.StatisticInfo;
import com.example.ms3.Entity.StatisticsData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@EnableCaching
@Configuration
public class Config {

	 @Bean
     public ObjectMapper objectMapper() {
         return Jackson2ObjectMapperBuilder.json()
                 .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) //ISODate
                 .build();
     }

     @Bean
     @Primary
     public RedisTemplate<String, StatisticInfo> redisTemplate(ObjectMapper objectMapper, RedisConnectionFactory redisConnectionFactory){
         RedisTemplate redisTemplate = new RedisTemplate();
         redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer(objectMapper));
         redisTemplate.setConnectionFactory(redisConnectionFactory);
         return redisTemplate;
     }
     
     //to create a channel between publish and subscribe
     @Bean
     public ChannelTopic topic() {
         return new ChannelTopic("TECHBEPROJECT");
     }
}
