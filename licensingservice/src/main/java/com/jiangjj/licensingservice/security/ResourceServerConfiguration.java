package com.jiangjj.licensingservice.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter{
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers("/css/**", "/js/**", "/webjars/**", "/index", "/hystrix*/**", "/proxy.stream", "/favicon.ico").permitAll()
                .antMatchers(HttpMethod.DELETE, "/v1/organizations/**")
                .hasRole("ADMIN")
                .antMatchers("v1/**").authenticated()
                .anyRequest().permitAll();
//                .authenticated();
    }
}
