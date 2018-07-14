package com.jiangjj.licensingservice.services;

import com.jiangjj.licensingservice.clients.OrganizationServiceClient;
import com.jiangjj.licensingservice.clients.OrganizationServiceTemplateClient;
import com.jiangjj.licensingservice.models.License;
import com.jiangjj.licensingservice.models.Organization;
import com.jiangjj.licensingservice.repositories.LicenseRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LicenseServiceImpl implements LicenseService{
    private LicenseRepository licenseRepository;

    private OrganizationServiceClient templateClient;

    @Override
    public License getLicense(String organizationId, String licenseId) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        Organization organization = templateClient.getOrganization(organizationId);
        license.setOrganizationName(organization.getName());
        return license;
    }

    @Override
    @HystrixCommand(fallbackMethod = "bulidFallbackLicenseList",
        threadPoolKey = "licenseByOrgThreadPool",
        threadPoolProperties =
                {@HystrixProperty(name = "coreSize", value = "30"),
                 @HystrixProperty(name = "maxQueueSize", value = "10")},
        commandProperties = {
                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
                @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
                @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
                @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5")
        })
    public List<License> getLicenseByOrg(String organizationId) {
        randomlyRunLong();
        return licenseRepository.findByOrganizationId(organizationId);
    }

    private void randomlyRunLong() {
        Random random = new Random();
        int randomNum = random.nextInt(3) + 1;
        if (randomNum == 3) {
            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private List<License> bulidFallbackLicenseList(String organizationId) {
        List<License> fallbackList = new ArrayList<>();
        License license = new License();
        license.setLicenseId("000000-00-00000");
        license.setOrganizationId(organizationId);
        license.setLicenseName("Sorry no licensing information currently available");
        fallbackList.add(license);
        return fallbackList;
    }
    @Override
    public void saveLicense(License license) {
        license.setLicenseId(UUID.randomUUID().toString());
        licenseRepository.save(license);
    }

    @Override
    public void updateLicense(License license) {
        licenseRepository.save(license);
    }

    @Override
    public void deleteLicense(License license) {
        licenseRepository.delete(license);
    }
}
