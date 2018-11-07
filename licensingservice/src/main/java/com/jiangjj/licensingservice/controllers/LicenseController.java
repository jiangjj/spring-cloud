package com.jiangjj.licensingservice.controllers;

import com.jiangjj.licensingservice.models.License;
import com.jiangjj.licensingservice.services.LicenseService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/organizations/{organizationId}/licenses")
@AllArgsConstructor
public class LicenseController {

    private LicenseService licenseService;

    private static final Logger logger = LoggerFactory.getLogger(LicenseController.class);

    @GetMapping
    public ResponseEntity<List<License>> getLicenses(@PathVariable("organizationId") Long organizationId) {
        return ResponseEntity.ok().body(licenseService.getLicenseByOrg(organizationId));
    }

    @GetMapping(value = "/{licenseId}")
    public ResponseEntity<License> getLicense(@PathVariable("organizationId") Long organizationId,
                              @PathVariable("licenseId") Long licenseId) {
        logger.info("getLicense by orgainzationId:" + organizationId + ", licenseId:" + licenseId);
        return ResponseEntity.ok().body(licenseService.getLicense(organizationId, licenseId));
    }

    @PostMapping
    public void saveLicenses(@RequestBody License license) {
        licenseService.saveLicense(license);
    }

    @PutMapping(value = "/{licenseId}")
    public void updateLicenses(@PathVariable("licenseId") Long licenseId, @RequestBody License license) {
        license.setLicenseId(licenseId);
        licenseService.updateLicense(license);
    }

    @DeleteMapping(value = "/{licenseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLicenses(@PathVariable("licenseId") Long licenseId, @RequestBody License license) {
        license.setLicenseId(licenseId);
        licenseService.deleteLicense(license);
    }




}
