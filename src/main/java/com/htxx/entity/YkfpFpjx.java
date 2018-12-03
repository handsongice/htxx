package com.htxx.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("YkfpFpjx")
public class YkfpFpjx extends BaseModel {

    private Long id;

    private Long ykfpIdRef;

    private String sjbz;

    private String jxbz;
}