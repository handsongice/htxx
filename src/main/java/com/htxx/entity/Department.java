package com.htxx.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Getter
@Setter
@ToString
@Alias("Department")
public class Department {

    private Long id;

    private Long parentId;

    private String name;

    private String desc;

    private Long enterpriseId;

    private String leader;

    private String phone;

    private String treeCode;

    private Integer sort;

    private String createTime;

    private String updateTime;

    private Integer isDel;

    private boolean checked = false;

    private boolean updateParent = false;

    private String nodes;

    private String kpjxx;

    private List<Employee> employeeList;
}