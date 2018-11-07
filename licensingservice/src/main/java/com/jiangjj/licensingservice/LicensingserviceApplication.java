package com.jiangjj.licensingservice;

import com.jiangjj.licensingservice.utils.UserContext;
import com.jiangjj.licensingservice.utils.UserContextInterceptor;
import feign.RequestInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableFeignClients
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


	public static void main(String[] args) {
		SpringApplication.run(LicensingserviceApplication.class, args);
	}

	/*@Autowired
	private Environment environment;
	@Autowired
	private MyMessage myMessage;
	@GetMapping(value = "/testMessage")
	public MyMessage testMessage() {
		System.out.println(environment.getProperty("my.message"));
		System.out.println(environment.getProperty("my.encryptMessage"));
		return myMessage;
	}*/
}
