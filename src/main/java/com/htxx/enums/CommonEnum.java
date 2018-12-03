package com.htxx.enums;

/**
 * @author: zhanghs
 * @date: 2018-11-19
 * @description: 枚举类
 */
public enum CommonEnum {
    /**
     * session
     */
    session_enterprise("enterprise", "企业"),
    session_department("department", "部门"),
    session_role("role", "岗位"),
    session_employee("employee", "员工"),
    /**
     * msg
     */
    msg_code_200("200", "成功"),
    msg_code_300("300", "接口异常"),
    msg_code_500("500", "失败"),
    /**
     * common
     */
    common_type_0("0", ""),
    common_type_1("1", ""),
    common_type_2("2", ""),
    common_type_3("3", ""),
    common_type_4("4", ""),
    /**
     * invoice
     */
    invoice_type_0("0", "增值税专用发票"),
    invoice_type_2("2", "增值税普通发票"),
    invoice_type_51("51", "增值税电子普通发票"),

    /**
     * review
     */
    review_type_pass("pass", "审核通过"),
    review_type_reject("reject", "审核拒绝");

    CommonEnum(String value, String info) {
        this.value = value;
        this.info = info;
    }

    private String value;

    private String info;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
