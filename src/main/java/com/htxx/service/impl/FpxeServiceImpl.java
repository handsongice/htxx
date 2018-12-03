package com.htxx.service.impl;

import com.htxx.constent.Constent;
import com.htxx.dto.ResultMap;
import com.htxx.entity.Fplx;
import com.htxx.entity.Fpxe;
import com.htxx.entity.Kpjxx;
import com.htxx.mapper.FplxMapper;
import com.htxx.mapper.FpxeMapper;
import com.htxx.mapper.KpjxxMapper;
import com.htxx.service.FpxeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/11/9 14:47
 */
@Service
@Transactional
public class FpxeServiceImpl implements FpxeService {
    @Autowired
    FpxeMapper fpxeMapper;

    @Autowired
    KpjxxMapper kpjxxMapper;

    @Autowired
    FplxMapper fplxMapper;

    @Override
    public List<Fpxe> getFpxeList(Map<String, Object> params) throws Exception {
        List<Fpxe> fpxes = fpxeMapper.selectByParams(params);
        return fpxes;
    }

    @Override
    public ResultMap addFpxe(Fpxe fpxe) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("enterprise_id",fpxe.getEnterpriseId());
        //开票机
        List<Kpjxx> kpjxxes = kpjxxMapper.selectByParams(params);
        if(null == kpjxxes || kpjxxes.size() <=0) {
            throw new RuntimeException("请先维护开票机信息！");
        }
        //发票类型
        List<Fplx> fplxes = fplxMapper.selectByParams(params);
        if(null == fplxes || fplxes.size() <=0) {
            throw new RuntimeException("请先维护发票类型！");
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Kpjxx kpjxx:kpjxxes) {
            for (Fplx fplx:fplxes) {
                Fpxe _fpxe = new Fpxe();
                _fpxe.setEnterpriseId(fpxe.getEnterpriseId());
                _fpxe.setKpjId(kpjxx.getId());
                _fpxe.setFplxId(fplx.getId());
                Fpxe __fpxe = fpxeMapper.findByParams(_fpxe);
                Fpxe _addFpxe = new Fpxe();
                if(__fpxe == null || __fpxe.getId() == null) {
                    //插入
                    _addFpxe.setEnterpriseId(fpxe.getEnterpriseId());
                    _addFpxe.setKpjId(kpjxx.getId());
                    _addFpxe.setKpj(kpjxx.getName());
                    _addFpxe.setFplxId(fplx.getId());
                    _addFpxe.setFplx(fplx.getName());
                    //设置创建时间
                    _addFpxe.setCreateTime(df.format(new Date()));
                    _addFpxe.setAmount(Constent.KPXE_DEFAULT);
                    int dbResult = fpxeMapper.insertSelective(_addFpxe);
                    if(dbResult <=0){
                        throw new RuntimeException(Constent.DB_INSERT_FAILURE);
                    }
                } else {
                    //更新
                    _addFpxe.setId(__fpxe.getId());
                    _addFpxe.setKpj(kpjxx.getName());
                    _addFpxe.setFplx(fplx.getName());
                    _addFpxe.setUpdateTime(df.format(new Date()));
                    int dbResult = fpxeMapper.updateByPrimaryKeySelective(_addFpxe);
                    if(dbResult <=0){
                        throw new RuntimeException(Constent.DB_UPDATE_FAILURE);
                    }
                }
            }
        }
        return ResultMap.success("初始化成功");
    }

    @Override
    public ResultMap updateFpxe(Fpxe fpxe) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //设置创建时间
            fpxe.setUpdateTime(df.format(new Date()));
            int dbResult = fpxeMapper.updateByPrimaryKeySelective(fpxe);
            if(dbResult >0){
                return ResultMap.success(Constent.DB_UPDATE_SUCCESS);
            }else{
                return ResultMap.fail(Constent.DB_UPDATE_FAILURE);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultMap.fail(Constent.DB_UPDATE_FAILURE);
        }
    }
}
