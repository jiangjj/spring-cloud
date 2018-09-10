package com.jiangjj.licensingservice;

import com.jiangjj.licensingservice.configs.MyMessage;
import com.jiangjj.licensingservice.utils.ProtobufRedisSerializer;
import com.jiangjj.licensingservice.utils.UserContext;
import com.jiangjj.licensingservice.utils.UserContextInterceptor;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableFeignClients
@EnableResourceServer
@RestController
public class LicensingserviceApplication {

	@Primary
    @LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate template = new RestTemplate();
		List interceptors = template.getInterceptors();
		if (interceptors == null) {
			template.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
		} else {
			interceptors.add(new UserContextInterceptor());
			template.setInterceptors(interceptors);
		}
		return template;
	}

	@Bean
	public ProtobufHttpMessageConverter protobufHttpMessageConverter() {
		return new ProtobufHttpMessageConverter();
	}

	@Bean
	public RequestInterceptor requestTokenBearerInterceptor() {
		return requestTemplate -> requestTemplate.header(UserContext.AUTH_TOKEN, UserContext.getAuthToken());
	}

	@Bean(name = "orgProtoCacheManager")
	public RedisCacheManager redisCacheManager(RedisConnectionFactory connectionFactory) {
		RedisCacheManager cm = RedisCacheManager.builder(connectionFactory)
				.cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(new ProtobufRedisSerializer()))
				.build();
		return cm;
	}
	public static void main(String[] args) {
		SpringApplication.run(LicensingserviceApplication.class, args);
	}

	@Autowired
	private Environment environment;
	@Autowired
	private MyMessage myMessage;
	@GetMapping(value = "/testMessage")
	public MyMessage testMessage() {
		System.out.println(environment.getProperty("my.message"));
		System.out.println(environment.getProperty("my.encryptMessage"));
		return myMessage;
	}
}
