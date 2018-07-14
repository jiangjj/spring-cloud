package com.jiangjj.organizationservice.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "organizations")
@Data
public class Organization implements Serializable{
    @Id
    private String id;
    private String name;
}
