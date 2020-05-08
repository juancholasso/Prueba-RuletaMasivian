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

	/**
	 * Return RedisConnection
	 * @return
	 * @throws URISyntaxException
	 */
	@Bean
	JedisConnectionFactory jedisConnectionFactory() throws URISyntaxException {
		URI uri = new URI("redis://redistogo:251737ec85ca7351c8553d05cda906b6@crestfish.redistogo.com:9660");
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(uri.getHost(), uri.getPort());
	    redisStandaloneConfiguration.setPassword(RedisPassword.of("251737ec85ca7351c8553d05cda906b6"));
	    return new JedisConnectionFactory(redisStandaloneConfiguration);
	}
	 
	/**
	 * Return template of RedisConnection
	 * @return
	 * @throws URISyntaxException
	 */
	@Bean
	public RedisTemplate<String, Object> redisTemplate() throws URISyntaxException {
	    RedisTemplate<String, Object> template = new RedisTemplate<>();
	    template.setConnectionFactory(jedisConnectionFactory());
	    return template;
	}
		
}
