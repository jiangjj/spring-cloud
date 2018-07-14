package com.jiangjj.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableResourceServer
@EnableAuthorizationServer
@RestController
public class Oauth2Application {

	@RequestMapping(value = "/user", produces = "application/json")
	public Map<String, Object> user(OAuth2Authentication oAuth2Authentication) {
		Map<String, Object> userInfo = new HashMap<>();
		userInfo.put("user", oAuth2Authentication.getUserAuthentication().getPrincipal());
		userInfo.put("authorities", AuthorityUtils.authorityListToSet(
				oAuth2Authentication.getUserAuthentication().getAuthorities()
		));
		return userInfo;
	}

	public static void main(String[] args) {
		SpringApplication.run(Oauth2Application.class, args);
	}
}
