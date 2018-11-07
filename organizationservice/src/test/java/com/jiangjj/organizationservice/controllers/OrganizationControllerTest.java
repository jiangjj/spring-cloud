package com.jiangjj.organizationservice.controllers;

import com.jiangjj.organizationservice.models.Organization;
import com.jiangjj.organizationservice.services.OrganizationService;
import com.jiangjj.organizationservice.utils.ProtoStuffUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@WebMvcTest(OrganizationController.class)
@ActiveProfiles("test")
public class OrganizationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrganizationService organizationService;

    @Test
    @WithMockUser(username = "jiangjj", password = "123456")
    public void getOrganizationsJson() throws Exception{
        Long id = 1L;
        String name = "orgName";
        Organization organization = new Organization();
        organization.setId(id);
        organization.setName(name);
        given(organizationService.getOrganizations(id)).willReturn(Optional.of(organization));
        mockMvc.perform(get("/v1/organizations/"+id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().json(new ObjectMapper().writeValueAsString(organization)));
    }

    @Test
    @WithMockUser(username = "jiangjj", password = "123456")
    public void getOrganizationsProtoStuff() throws Exception{
        Long id = 1L;
        String name = "orgName";
        Organization organization = new Organization();
        organization.setId(id);
        organization.setName(name);
        given(organizationService.getOrganizations(id)).willReturn(Optional.of(organization));
        mockMvc.perform(get("/v1/organizations/"+id).accept("application/x-protobuf"))
                .andExpect(status().isOk()).andExpect(content().bytes(ProtoStuffUtil.serialize(organization)));
    }

    @Test
    @WithMockUser(username = "jiangjj", password = "123456")
    public void saveOrganizations() throws Exception{
        Long id = 1L;
        String name = "orgName";
        Organization organization = new Organization();
        organization.setId(id);
        organization.setName(name);
        doNothing().when(organizationService).saveOrganizations(organization);
        String requestBody = new ObjectMapper().writeValueAsString(organization);
        mockMvc.perform(post("/v1/organizations").with(csrf())
                .contentType(MediaType.APPLICATION_JSON).content(requestBody).characterEncoding("utf-8"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "jiangjj", password = "123456")
    public void updateOrganizations() throws Exception{
        Long id = 1L;
        String name = "orgName";
        Organization organization = new Organization();
        organization.setId(id);
        organization.setName(name);
        doNothing().when(organizationService).updateOrganizations(organization);
        String requestBody = new ObjectMapper().writeValueAsString(organization);
        mockMvc.perform(put("/v1/organizations/"+id).with(csrf()).param("organizationId", String.valueOf(id))
                .contentType(MediaType.APPLICATION_JSON).content(requestBody).characterEncoding("utf-8"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "jiangjj", password = "123456", roles = {"ADMIN"})
    public void deleteOrganizations() throws Exception{
        Long id = 1L;
        String name = "orgName";
        Organization organization = new Organization();
        organization.setId(id);
        organization.setName(name);
        doNothing().when(organizationService).deleteOrganizations(organization);
        String requestBody = new ObjectMapper().writeValueAsString(organization);
        mockMvc.perform(delete("/v1/organizations/"+id).with(csrf()).param("organizationId", String.valueOf(id))
                .contentType(MediaType.APPLICATION_JSON).content(requestBody).characterEncoding("utf-8"))
                .andExpect(status().isNoContent());
    }
}