package com.masivian.beans;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories(basePackages = "com.masivian.repositories")
public class RedisBean {

	@Bean
	JedisConnectionFactory jedisConnectionFactory() throws URISyntaxException {
		URI uri = new URI("redis://redistogo:ee5f0a78000babb31ad9bd776e8f4329@spinyfin.redistogo.com:11580/");
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(uri.getHost(), uri.getPort());
	    redisStandaloneConfiguration.setPassword(RedisPassword.of("ee5f0a78000babb31ad9bd776e8f4329"));
	    return new JedisConnectionFactory(redisStandaloneConfiguration);
	}
	 
	@Bean
	public RedisTemplate<String, Object> redisTemplate() throws URISyntaxException {
	    RedisTemplate<String, Object> template = new RedisTemplate<>();
	    template.setConnectionFactory(jedisConnectionFactory());
	    return template;
	}
		
}
