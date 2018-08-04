package com.jiangjj.organizationservice.services;

import com.jiangjj.organizationservice.events.sources.SimpleSourceBean;
import com.jiangjj.organizationservice.models.Organization;
import com.jiangjj.organizationservice.repositories.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService{
    private OrganizationRepository organizationRepository;
    private SimpleSourceBean simpleSourceBean;

    @Override
    public Optional<Organization> getOrganizations(String organizationId) {
        return organizationRepository.findById(organizationId);
    }

    @Override
    public void saveOrganizations(Organization organization) {
        organization.setId(UUID.randomUUID().toString());
        organizationRepository.save(organization);
        simpleSourceBean.publishOrgChange("SAVE", organization.getId());
    }

    @Override
    public void updateOrganizations(Organization organization) {
        organizationRepository.save(organization);
        simpleSourceBean.publishOrgChange("UPDATE", organization.getId());
    }

    @Override
    public void deleteOrganizations(Organization organization) {
        organizationRepository.delete(organization);
        simpleSourceBean.publishOrgChange("DELETE", organization.getId());
    }
}
