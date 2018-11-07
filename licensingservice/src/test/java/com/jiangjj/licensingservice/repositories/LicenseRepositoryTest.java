package com.jiangjj.licensingservice.repositories;

import com.jiangjj.licensingservice.models.License;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LicenseRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LicenseRepository licenseRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByOrganizationIdAndLicenseId() {
        Long id = 1L;
        String name = "licName";
        Long organizationId = 1L;
        String organizationName = "orgName";
        License license = new License();
        license.setLicenseId(id);
        license.setLicenseName(name);
        license.setOrganizationId(organizationId);
        license.setOrganizationName(organizationName);
        entityManager.persist(license);
        License ret = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, license.getLicenseId());
        assertTrue(ret.equals(license));
    }

    @Test
    public void findByOrganizationId() {
        Long id = 1L;
        String name = "licName";
        Long organizationId = 1L;
        String organizationName = "orgName";
        License license = new License();
        license.setLicenseId(id);
        license.setLicenseName(name);
        license.setOrganizationId(organizationId);
        license.setOrganizationName(organizationName);
        entityManager.persist(license);
        List<License> licenses = licenseRepository.findByOrganizationId(organizationId);
        assertEquals(licenses.size(), 1L);

        License license1 = new License();
        license1.setLicenseId(id+1);
        license1.setLicenseName(name);
        license1.setOrganizationId(organizationId);
        license1.setOrganizationName(organizationName);
        entityManager.persist(license1);
        licenses = licenseRepository.findByOrganizationId(organizationId);
        assertEquals(licenses.size(), 2L);
    }
}