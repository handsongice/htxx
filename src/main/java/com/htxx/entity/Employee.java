package com.htxx.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Alias("Employee")
public class Employee {

    private Long id;

    private String loginCode;

    private Long enterpriseId;

    private Long departmentId;

    private Integer type;

    private Long roleId;

    private String name;

    private String phone;

    private String password;

    private Integer sex;

    private Date birthday;

    private String idcard;

    private String bankcard;

    private String qq;

    private String email;

    private Integer workType;

    private String address;

    private Date inDate;

    private Date regularDate;

    private Integer isAdmin;

    private String desc;

    private Integer isDel;

    private Integer status;

    private String createTime;

    private String updateTime;

    private String departments;

    private String roles;

    private String pic;

    private List<Department> departmentList;

    private String oldPassword;

}