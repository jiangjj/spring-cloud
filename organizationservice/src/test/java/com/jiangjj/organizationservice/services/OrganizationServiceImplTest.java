package com.jiangjj.organizationservice.services;

import com.jiangjj.organizationservice.events.sources.SimpleSourceBean;
import com.jiangjj.organizationservice.models.Organization;
import com.jiangjj.organizationservice.repositories.OrganizationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
@RunWith(SpringRunner.class)
//@SpringBootTest
//@Import(OrganizationServiceImplTest.Configuration.class)
public class OrganizationServiceImplTest {

    @TestConfiguration
    static class Configuration{

        @Bean
        public OrganizationService organizationService(OrganizationRepository organizationRepository,
                                                       SimpleSourceBean simpleSourceBean) {
            return new OrganizationServiceImpl(organizationRepository, simpleSourceBean);
        }
    }

    @MockBean
    private OrganizationRepository organizationRepository;
    @MockBean
    private SimpleSourceBean simpleSourceBean;
    @Autowired
    private OrganizationService organizationService;

    @Before
    public void setUp() {
        organizationService = new OrganizationServiceImpl(organizationRepository, simpleSourceBean);
    }

    @Test
    public void getOrganizations() {
        Organization organization = new Organization();
        Long orgId = 1L;
        String orgName = "orgName";
        organization.setId(orgId);
        organization.setName(orgName);
        given(this.organizationRepository.findById(orgId)).willReturn(Optional.ofNullable(organization));
//        given(this.simpleSourceBean.publishOrgChange("GET", orgId)).willReturn();)
        Optional<Organization> retOrg = organizationService.getOrganizations(orgId);
        assertThat(organization).isEqualTo(retOrg.get());
    }

    @Test
    public void saveOrganizations() {
    }

    @Test
    public void updateOrganizations() {
    }

    @Test
    public void deleteOrganizations() {
    }
}