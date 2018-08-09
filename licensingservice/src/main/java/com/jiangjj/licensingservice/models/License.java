package com.jiangjj.licensingservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "licenses")
@Data
public class License implements Serializable{
    @Id
    @Column(name = "license_id", nullable = false)
    private String licenseId;
    @Column(name = "license_name", nullable = false)
    private String licenseName;
    @Column(name = "organization_id", nullable = false)
    private String organizationId;
    @Transient
    private String organizationName;
}
