package com.htxx.pojo;

import com.htxx.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Getter
@Setter
public class FppdParams {

    private Long id;

    private Long enterpriseId;

    private String kpjCode;

    private String kpjName;

    private String fplx;

    private String pdMonth;

    private String kpAmount;

    private String zfAmount;

    private String chAmount;

    private String sykc;

    private String pdbz;
}