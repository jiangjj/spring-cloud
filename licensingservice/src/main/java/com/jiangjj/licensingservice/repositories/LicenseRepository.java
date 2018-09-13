package com.jiangjj.licensingservice.repositories;

import com.jiangjj.licensingservice.models.License;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LicenseRepository extends CrudRepository<License, Long> {
    License findByOrganizationIdAndLicenseId(Long organizationId, Long licenseId);

    List<License> findByOrganizationId(Long organizationId);
}
