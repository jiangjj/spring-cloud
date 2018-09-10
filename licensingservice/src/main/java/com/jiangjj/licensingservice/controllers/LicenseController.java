package com.jiangjj.licensingservice.controllers;

import com.jiangjj.licensingservice.configs.MyMessage;
import com.jiangjj.licensingservice.models.License;
import com.jiangjj.licensingservice.services.LicenseService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/organizations/{organizationId}/licenses")
@AllArgsConstructor
public class LicenseController {

    private LicenseService licenseService;

    private MyMessage myMessage;

    private Environment environment;

    private static final Logger logger = LoggerFactory.getLogger(LicenseController.class);

    @GetMapping
    public List<License> getLicenses(@PathVariable("organizationId") String organizationId) {
        return licenseService.getLicenseByOrg(organizationId);
    }

    @GetMapping(value = "/{licenseId}")
    public License getLicense(@PathVariable("organizationId") String organizationId,
    @PathVariable("licenseId") String licenseId) {
        logger.info("getLicense by orgainzationId:" + organizationId + ", licenseId:" + licenseId);
        return licenseService.getLicense(organizationId, licenseId);
    }

    @PostMapping
    public void saveLicenses(@RequestBody License license) {
        licenseService.saveLicense(license);
    }

    @PutMapping(value = "/{licenseId}")
    public void updateLicenses(@PathVariable("licenseId") String licenseId, @RequestBody License license) {
        licenseService.updateLicense(license);
    }

    @DeleteMapping(value = "/{licenseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLicenses(@PathVariable("licenseId") String licenseId, @RequestBody License license) {
        licenseService.deleteLicense(license);
    }




}
