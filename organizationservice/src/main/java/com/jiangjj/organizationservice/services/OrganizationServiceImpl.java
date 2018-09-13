package com.jiangjj.organizationservice.services;

import com.jiangjj.organizationservice.events.sources.SimpleSourceBean;
import com.jiangjj.organizationservice.models.Organization;
import com.jiangjj.organizationservice.repositories.OrganizationRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService{
    private OrganizationRepository organizationRepository;
    private SimpleSourceBean simpleSourceBean;

    @Override
    @HystrixCommand
    public Optional<Organization> getOrganizations(Long organizationId) {
        Optional<Organization> organization = organizationRepository.findById(organizationId);
        simpleSourceBean.publishOrgChange("GET", organizationId);
        return organization;
    }


    @Override
    @HystrixCommand
    public void saveOrganizations(Organization organization) {
        organizationRepository.save(organization);
        simpleSourceBean.publishOrgChange("SAVE", organization.getId());
    }

    @Override
    @HystrixCommand
    public void updateOrganizations(Organization organization) {
        organizationRepository.save(organization);
        simpleSourceBean.publishOrgChange("UPDATE", organization.getId());
    }

    @Override
    @HystrixCommand
    public void deleteOrganizations(Organization organization) {
        organizationRepository.delete(organization);
        simpleSourceBean.publishOrgChange("DELETE", organization.getId());
    }
}
