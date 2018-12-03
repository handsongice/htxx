package com.htxx.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("DepartmentEmployee")
public class DepartmentEmployee {

    private Long id;

    private Long deptId;

    private Long employeeId;
}