package com.jiangjj.organizationservice.events.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrganizationChangeModel {
    private String type;
    private String action;
    private Long organizationId;
    private String correlationId;
}
