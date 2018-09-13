package com.jiangjj.organizationservice.controllers;

import com.jiangjj.organizationservice.models.Organization;
import com.jiangjj.organizationservice.models.OrganizationProto;
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

    @GetMapping(value = "/{organizationId}", consumes = "application/x-protobuf", produces = "application/x-protobuf")
    public OrganizationProto.Organization getOrganizations(@PathVariable("organizationId") Long organizationId) {
        Optional<Organization> organization = organizationService.getOrganizations(organizationId);
        OrganizationProto.Organization organizationProto = OrganizationProto.Organization.getDefaultInstance();
        if (organization.isPresent()) {
            organizationProto = OrganizationProto.Organization.newBuilder()
                    .setId(organization.get().getId())
                    .setName(organization.get().getName())
                    .build();
        }
        return organizationProto;
    }

    @GetMapping(value = "/{organizationId}", consumes = "application/json", produces = "application/json")
    public Optional<Organization> getOrganizationsJson(@PathVariable("organizationId") Long organizationId) {
        Optional<Organization> organization = organizationService.getOrganizations(organizationId);
        return organization;
    }

    @PostMapping
    public void saveOrganizations(@RequestBody Organization organization) {
        organizationService.saveOrganizations(organization);
    }

    @PutMapping(value = "/{organizationId}")
    public void updateOrganizations(@PathVariable("organizationId") Long organizationId,
                                    @RequestBody Organization organization) {
        organization.setId(organizationId);
        organizationService.updateOrganizations(organization);
    }

    @DeleteMapping(value = "/{organizationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganizations(@PathVariable("organizationId") Long organizationId,
                                    @RequestBody Organization organization) {
        organization.setId(organizationId);
        organizationService.deleteOrganizations(organization);
    }
}
