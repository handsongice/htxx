package com.htxx.entity.exception;

/**
 * Created by 123 on 2018-08-08
 */
public enum ResultEnum {
    UNKONW_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),
    ERROR(1, "失败"),
    INTERFACE_CODE_ERROR(2, "接口编码不匹配"),
    PARAM_SHOULD_NOT_ALL_EMPTY(3, "请求参数不符合标准"),
    OPERATE_ZERO_DATA(4, "操作了0条数据"),
    CANNOT_FIND_THIS_METHOD(5, "请求方法不存在"),
    QUERY_RESULT_IS_EMPTY(6,"查询结果为空"),
    INTERNAL_CALLED_EXCEPTION(7,"内部调用异常");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
