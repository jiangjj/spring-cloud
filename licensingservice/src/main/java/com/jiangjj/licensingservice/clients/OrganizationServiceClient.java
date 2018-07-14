package com.jiangjj.licensingservice.clients;

import com.jiangjj.licensingservice.models.Organization;

public interface OrganizationServiceClient {
    Organization getOrganization(String organizationId);
}
