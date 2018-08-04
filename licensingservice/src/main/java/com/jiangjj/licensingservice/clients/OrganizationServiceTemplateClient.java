package com.jiangjj.licensingservice.clients;

import com.jiangjj.licensingservice.models.Organization;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
public class OrganizationServiceTemplateClient implements OrganizationServiceClient{
    private static final Logger logger = LoggerFactory.getLogger(OrganizationServiceTemplateClient.class);
    private RestTemplate restTemplate;

    @Override
    public Organization getOrganization(String organizationId) {
        logger.info("getOrganization by id:" + organizationId);
        ResponseEntity<Organization> restExchange = restTemplate.exchange("http://api-gateway:9000/organizationservice/v1/organizations/{organizationId}",
                HttpMethod.GET, null, Organization.class, organizationId);
        return restExchange.getBody();
    }
}
