package com.htxx.entity;

import com.htxx.util.BigDecimalUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.Alias;

import java.lang.reflect.Array;

/**
 * Created by ll on 2018-11-05
 */
@Alias("Spxx")
@Slf4j
public class Spxx {
    /**
     * 商品id
     */
    private Long id;
    /**
     * 业务类型  1-普通电费  2-高可靠电费 3-自由票
     */
    private int ywlx;
    /**
     * 商品名称
     */
    private String spmc;
    /**
     * 税收分类编码
     */
    private String shflbm;
    /**
     * 税率
     */
    private String sl;
    /**
     * 税率，百分制
     */
    private String slPercent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYwlx() {
        return ywlx;
    }

    public void setYwlx(int ywlx) {
        this.ywlx = ywlx;
    }

    public String getSpmc() {
        return spmc;
    }

    public void setSpmc(String spmc) {
        this.spmc = spmc;
    }

    public String getShflbm() {
        return shflbm;
    }

    public void setShflbm(String shflbm) {
        this.shflbm = shflbm;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public String getSlPercent() {
        return slPercent;
    }

    public void setSlPercent(String sl) {
        String[] strArray = sl.split(",");
        for (int i=0;i<strArray.length;i++){
            strArray[i] = BigDecimalUtil.decimal2percent(Double.parseDouble(strArray[i]));
        }
        this.slPercent = String.join(",", strArray);
    }

    @Override
    public String toString() {
        return "Spxx{" +
                "id=" + id +
                ", ywlx=" + ywlx +
                ", spmc='" + spmc + '\'' +
                ", shflbm='" + shflbm + '\'' +
                ", sl='" + sl + '\'' +
                ", slPercent='" + slPercent + '\'' +
                '}';
    }
}
