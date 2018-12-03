package com.htxx.service.impl;

import com.htxx.common.BaseResultData;
import com.htxx.entity.DlZiyoupiao;
import com.htxx.entity.DlZiyoupiaoDetail;
import com.htxx.entity.xxfp;
import com.htxx.mapper.DlZiyoupiaoDetailMapper;
import com.htxx.mapper.DlZiyoupiaoMapper;
import com.htxx.service.ZiYouPiaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * @Author: SaHongzhi
 * @Date: 2018-11-07 16:08
 */
@Service
@Slf4j
public class ZiYouPiaoServiceImpl implements ZiYouPiaoService{

    @Autowired
    DlZiyoupiaoMapper dlZiyoupiaoMapper;

    @Autowired
    DlZiyoupiaoDetailMapper dlZiyoupiaoDetailMapper;

    @Override
    public BaseResultData saveZiYouPiao(xxfp xxfp) {
        DlZiyoupiao dlZiyoupiao=xxfp.getDlZiyoupiao();
        dlZiyoupiaoMapper.insertAndGetId(dlZiyoupiao);
        long mainId=dlZiyoupiao.getId();
        long enterpriseId=dlZiyoupiao.getEnterpriseId();
        List<DlZiyoupiaoDetail> dlZiyoupiaoDetails=xxfp.getXxfpMxs();
        Iterator<DlZiyoupiaoDetail> dlZiyoupiaoDetailIterator=dlZiyoupiaoDetails.iterator();
        while (dlZiyoupiaoDetailIterator.hasNext()){
            DlZiyoupiaoDetail dlZiyoupiaoDetail=dlZiyoupiaoDetailIterator.next();
            dlZiyoupiaoDetail.setMainId(mainId);
            dlZiyoupiaoDetail.setEnterpriseId(enterpriseId);
            dlZiyoupiaoDetailMapper.insertSelective(dlZiyoupiaoDetail);
        }
        return BaseResultData.result("该条单据暂存成功,等待审核完成！", "200",mainId);
    }
}
