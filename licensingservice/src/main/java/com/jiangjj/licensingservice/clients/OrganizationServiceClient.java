package com.jiangjj.licensingservice.clients;

import com.jiangjj.organizationservice.models.OrganizationProto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("organizationservice")
public interface OrganizationServiceClient {

    @Cacheable(value = "organizations", cacheManager = "orgProtoCacheManager")
    @GetMapping(value = "/v1/organizations/{organizationId}", consumes = "application/json")
    OrganizationProto.Organization getOrganization(@PathVariable("organizationId") String organizationId);
}
