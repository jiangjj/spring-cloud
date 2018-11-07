package com.jiangjj.licensingservice.services;

import com.jiangjj.licensingservice.clients.OrganizationServiceClient;
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

@Service
@AllArgsConstructor
public class LicenseServiceImpl implements LicenseService{
    private LicenseRepository licenseRepository;

    private OrganizationServiceClient templateClient;

    @Override
    @HystrixCommand
    public License getLicense(Long organizationId, Long licenseId) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        Organization organization = templateClient.getOrganizationProtobuf(organizationId);
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
    public List<License> getLicenseByOrg(Long organizationId) {
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

    private List<License> bulidFallbackLicenseList(Long organizationId) {
        List<License> fallbackList = new ArrayList<>();
        License license = new License();
        license.setLicenseId(null);
        license.setOrganizationId(organizationId);
        license.setLicenseName("Sorry no licensing information currently available");
        fallbackList.add(license);
        return fallbackList;
    }
    @Override
    @HystrixCommand
    public void saveLicense(License license) {
        licenseRepository.save(license);
    }

    @Override
    @HystrixCommand
    public void updateLicense(License license) {
        licenseRepository.save(license);
    }

    @Override
    @HystrixCommand
    public void deleteLicense(License license) {
        licenseRepository.delete(license);
    }
}
