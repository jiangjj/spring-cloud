package com.jiangjj.organizationservice;

import com.jiangjj.organizationservice.utils.UserContextInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
@EnableResourceServer
@EnableBinding(Source.class)
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableCaching
@EnableSwagger2
public class OrganizationserviceApplication {

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

	/*@Bean
	public DataSource dataSource(ShardingJdbcConfiguration shardingJdbcConfiguration) {
		DataSource dataSource = null;
		Map<String, DataSource> dataSourceMap = new HashMap<>(shardingJdbcConfiguration.getDataSources());
		try {
			dataSource = MasterSlaveDataSourceFactory.createDataSource(dataSourceMap,
					shardingJdbcConfiguration.getMasterSlaveRule(),
					new LinkedHashMap<>(), null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataSource;
	}*/

	public static void main(String[] args) {
		SpringApplication.run(OrganizationserviceApplication.class, args);
	}
}
