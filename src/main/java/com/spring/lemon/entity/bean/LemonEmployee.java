package com.spring.lemon.entity.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class LemonEmployee
{
    @Id  //没有这个的会报错
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "employee_phone")
    private String employeePhone;

    @Column(name = "create_user")
    private Integer createUser;

    @Column(name = "create_uname")
    private String createUname;

    @Column(name = "update_user")
    private Integer updateUser;

    @Column(name = "update_uname")
    private String updateUname;

}
