package com.htxx.mapper;

import com.htxx.entity.PageMsg;
import com.htxx.entity.Ykfp;
import com.htxx.entity.YkfpDel;
import com.htxx.pojo.FpcxParams;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ll on 2018-11-01
 */
@Component
public interface YkfpMapper {
    /**
     * 获取页面已开发票 main 列表
     *
     * @param pageMsg
     * @return
     */
    List<Ykfp> getYkfpMainList(PageMsg<Ykfp> pageMsg);

    /**
     * 通过查询条件 -- 获取已开发票 全票面信息  main + del
     *
     * @param pageMsg
     * @return
     */
    List<Ykfp> getYkfpMainAndDelListByCondition(PageMsg<Ykfp> pageMsg);

    /**
     * 通过单据号字符串 -- 获取已开发票 全票面信息  main + del
     *
     * @param djhs 单据号字符串 数组
     * @return
     */
    List<Ykfp> getYkfpMainAndDelListByDjh(String[] djhs);

    /**
     * 插入发票明细
     *
     * @param ykfpDelList
     * @return
     */
    int insertYkfpDel(List<YkfpDel> ykfpDelList);

    /**
     * 插入一条发票头
     *
     * @param ykfp
     * @return
     */
    int insertYkfpMain(Ykfp ykfp);

    /**
     * 根据发票代码 发票号码 更新 开票类型
     *
     * @param fpdm
     * @param fphm
     * @param kplx
     * @return
     */
    int updateKplxByFpdmhm(@Param("fpdm") String fpdm, @Param("fphm") String fphm, @Param("kplx") int kplx);

    /**
     * 根据条件查询发票主表和发票详情
     *
     * @param fpcxParams
     * @return
     */
    List<YkfpDel> getYkfpInfoMain(FpcxParams fpcxParams);

}
