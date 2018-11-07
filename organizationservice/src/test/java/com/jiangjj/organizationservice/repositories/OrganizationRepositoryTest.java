package com.jiangjj.organizationservice.repositories;

import com.jiangjj.organizationservice.models.Organization;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrganizationRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Test
    public void testGetById() throws Exception{
        Long id = 1L;
        String name = "orgName";
        Organization organization = new Organization();
        organization.setId(id);
        organization.setName(name);
        entityManager.persist(organization);
        Optional<Organization> retOrg = organizationRepository.findById(id);
        assertTrue(retOrg.get().equals(organization));
    }

    @Test
    public void TestUpdateById() throws Exception{

    }
}