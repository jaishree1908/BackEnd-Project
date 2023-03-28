package com.example.ms3.Configuration;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import com.example.ms3.Entity.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Component
@Configuration
@EnableCaching
public class RedisConfiguration {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory redisConnectionFactory, MessageListenerAdapter messageListenerAdapter){
           RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
           redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
           redisMessageListenerContainer.addMessageListener(messageListenerAdapter,topic());
           redisMessageListenerContainer.setTopicSerializer(null);
           return redisMessageListenerContainer;
    }

    //to listen to the message
    @Bean
    public MessageListenerAdapter listenerAdapter(RedisMessageSubscriber consumer) {
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(consumer);
        messageListenerAdapter.setSerializer(new Jackson2JsonRedisSerializer<>(StatisticInfo.class));
        return messageListenerAdapter;
    }

    
    //to create a channel between publish and subscribe
    @Bean
    public ChannelTopic topic() {
        return new ChannelTopic("TECHBEPROJECT");
    }

   
    @Bean
    public ObjectMapper objectMapper() {
        return Jackson2ObjectMapperBuilder.json()
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) //ISODate
                .build();
    }
    
    @Bean
    public RedisTemplate<String, StatisticInfo> redisTemplate(ObjectMapper objectMapper, RedisConnectionFactory redisConnectionFactory){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer(objectMapper));
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
    
    
    @Bean
    public RedisTemplate<String, StatisticsData> redisTemplate1(ObjectMapper objectMapper, RedisConnectionFactory redisConnectionFactory){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer(objectMapper));
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
    
    @Bean
    public RedisTemplate<String, StatisticDatabyID> redisTemplate2(ObjectMapper objectMapper, RedisConnectionFactory redisConnectionFactory){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer(objectMapper));
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
    
    @Bean
    public RedisTemplate<String, StatisticDatabyMethod> redisTemplate3(ObjectMapper objectMapper, RedisConnectionFactory redisConnectionFactory){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer(objectMapper));
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

}

