package com.htxx.entity;

public class DepartmentKpj {
    private Long id;

    private Long deptId;

    private Long kpjId;

    private String kpjName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getKpjId() {
        return kpjId;
    }

    public void setKpjId(Long kpjId) {
        this.kpjId = kpjId;
    }

    public String getKpjName() {
        return kpjName;
    }

    public void setKpjName(String kpjName) {
        this.kpjName = kpjName;
    }
}