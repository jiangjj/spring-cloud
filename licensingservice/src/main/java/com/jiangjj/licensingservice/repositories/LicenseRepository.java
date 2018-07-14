package com.jiangjj.licensingservice.repositories;

import com.jiangjj.licensingservice.models.License;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LicenseRepository extends CrudRepository<License, String>{
    License findByOrganizationIdAndLicenseId(String organizationId, String licenseId);
    List<License> findByOrganizationId(String organizationId);
}
