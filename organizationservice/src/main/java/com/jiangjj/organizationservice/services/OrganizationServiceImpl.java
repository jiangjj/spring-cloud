package com.jiangjj.organizationservice.services;

import com.jiangjj.organizationservice.events.sources.SimpleSourceBean;
import com.jiangjj.organizationservice.models.Organization;
import com.jiangjj.organizationservice.models.OrganizationProto;
import com.jiangjj.organizationservice.repositories.OrganizationRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService{
    private OrganizationRepository organizationRepository;
    private SimpleSourceBean simpleSourceBean;

    @Override
    @HystrixCommand
    public OrganizationProto.Organization getOrganizations(Long organizationId) {
        Optional<Organization> organization = organizationRepository.findById(organizationId);
        OrganizationProto.Organization organizationProto = OrganizationProto.Organization.getDefaultInstance();
        if (organization.isPresent()) {
            simpleSourceBean.publishOrgChange("GET", organization.get().getId());
            organizationProto = OrganizationProto.Organization.newBuilder().setId(organization.get().getId()).setName(organization.get().getName()).build();
        }
        return organizationProto;
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
