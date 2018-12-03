package com.htxx.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class xxfp {
    private DlZiyoupiao dlZiyoupiao;
    private List<DlZiyoupiaoDetail> xxfpMxs;
}