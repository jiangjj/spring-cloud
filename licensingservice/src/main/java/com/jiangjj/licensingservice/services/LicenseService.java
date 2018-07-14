package com.jiangjj.licensingservice.services;

import com.jiangjj.licensingservice.models.License;

import java.util.List;

public interface LicenseService {
    License getLicense(String organizationId, String licenseId);

    List<License> getLicenseByOrg(String organizationId);

    void saveLicense(License license);

    void updateLicense(License license);

    void deleteLicense(License license);
}
