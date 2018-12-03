package com.htxx.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author: jason ji
 * @Date: 2018/10/26 15:41
 */
@Setter
@Getter
@ToString
public class LoginParams implements Serializable {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 验证码
     */
    private String code;
}
