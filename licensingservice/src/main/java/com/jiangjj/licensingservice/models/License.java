package com.jiangjj.licensingservice.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "licenses")
@Data
public class License implements Serializable{
    @Id
    @Column(name = "license_id", nullable = false)
    private Long licenseId;
    @Column(name = "license_name", nullable = false)
    private String licenseName;
    @Column(name = "organization_id", nullable = false)
    private Long organizationId;
    @Transient
    private String organizationName;
}
