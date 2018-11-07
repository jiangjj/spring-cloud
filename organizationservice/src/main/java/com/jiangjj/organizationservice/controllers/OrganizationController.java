package com.jiangjj.organizationservice.controllers;

import com.jiangjj.organizationservice.models.Organization;
import com.jiangjj.organizationservice.services.OrganizationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/v1/organizations")
@AllArgsConstructor
@Slf4j
public class OrganizationController {
    private OrganizationService organizationService;

    @GetMapping(value = "/{organizationId}", produces = "application/json")
    public ResponseEntity<Organization> getOrganizationsJson(@PathVariable("organizationId") Long organizationId) {
        Optional<Organization> organization = organizationService.getOrganizations(organizationId);
        return ResponseEntity.ok().body(organization.get());
    }

    @GetMapping(value = "/{organizationId}", produces = "application/x-protobuf")
    public ResponseEntity<Organization> getOrganizationsProtoStuff(@PathVariable("organizationId") Long organizationId) {
        Optional<Organization> organization = organizationService.getOrganizations(organizationId);
        return ResponseEntity.ok().body(organization.get());
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
