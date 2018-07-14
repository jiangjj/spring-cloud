package com.jiangjj.licensingservice.clients;

import com.jiangjj.licensingservice.models.Organization;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient("organizationservice")
public interface OrgainzationServiceFeignClient extends OrganizationServiceClient{
    /*@Override
    @GetMapping(value = "/v1/organizations/{organizationId}", consumes = "application/json")
    Organization getOrganization(@PathVariable("organizationId") String organizationId);*/
}
