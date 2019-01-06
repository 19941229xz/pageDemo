package com.example.demo.model;

import com.example.demo.common.base.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
public class User extends BaseModel{

    private String id;

    private String username;

    private String password;
}
