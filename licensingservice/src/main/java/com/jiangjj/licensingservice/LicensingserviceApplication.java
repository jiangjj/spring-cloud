package com.jiangjj.licensingservice;

import com.jiangjj.licensingservice.clients.OrganizationServiceClient;
import com.jiangjj.licensingservice.clients.OrganizationServiceTemplateClient;
import com.jiangjj.licensingservice.utils.UserContextInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
@EnableCircuitBreaker
@EnableFeignClients
@EnableResourceServer
public class LicensingserviceApplication {

	@Primary
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
    /*@Bean
	public OAuth2RestTemplate restTemplate(UserInfoRestTemplateFactory factory) {
    	return factory.getUserInfoRestTemplate();
	}*/

	@Bean
	public OrganizationServiceClient organizationServiceClient(RestTemplate restTemplate) {
		return new OrganizationServiceTemplateClient(restTemplate);
	}

	public static void main(String[] args) {
		SpringApplication.run(LicensingserviceApplication.class, args);
	}

}
