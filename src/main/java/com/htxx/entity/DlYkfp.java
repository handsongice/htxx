package com.htxx.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Author: SaHongzhi
 * @Date: 2018-11-19 13:29
 */

@Getter
@Setter
@ToString
public class DlYkfp {
    private DlYkfpMain dlYkfpMain;
    private List<DlYkfpDel> dlYkfpDels;
}
