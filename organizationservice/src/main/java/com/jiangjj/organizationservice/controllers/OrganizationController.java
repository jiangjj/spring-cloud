package com.jiangjj.organizationservice.controllers;

import com.jiangjj.organizationservice.models.Organization;
import com.jiangjj.organizationservice.services.OrganizationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/v1/organizations")
@AllArgsConstructor
public class OrganizationController {
    private OrganizationService organizationService;
    private static final Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    @GetMapping(value = "/{organizationId}")
    public Optional<Organization> getOrganizations(@PathVariable("organizationId") String organizationId) {
        logger.info("getOrganization by id:" + organizationId);
        return organizationService.getOrganizations(organizationId);
    }

    @PostMapping
    public void saveOrganizations(@RequestBody Organization organization) {
        organizationService.saveOrganizations(organization);
    }

    @PutMapping(value = "/{organizationId}")
    public void updateOrganizations(@PathVariable("organizationId") String organizationId,
                                    @RequestBody Organization organization) {
        organizationService.updateOrganizations(organization);
    }

    @DeleteMapping(value = "/{organizationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganizations(@PathVariable("organizationId") String organizationId,
                                    @RequestBody Organization organization) {
        organizationService.deleteOrganizations(organization);
    }
}
