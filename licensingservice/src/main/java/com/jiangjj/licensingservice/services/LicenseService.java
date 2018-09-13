package com.jiangjj.licensingservice.services;

import com.jiangjj.licensingservice.models.License;

import java.util.List;

public interface LicenseService {
    License getLicense(Long organizationId, Long licenseId);

    List<License> getLicenseByOrg(Long organizationId);

    void saveLicense(License license);

    void updateLicense(License license);

    void deleteLicense(License license);
}
