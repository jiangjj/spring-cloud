package com.jiangjj.licensingservice.models;

import lombok.Data;

import java.io.Serializable;

//@Entity
//@Table(name = "organizations")
@Data
public class Organization implements Serializable{
    //    @Id
    private Long id;
    private String name;
}
