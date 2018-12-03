package com.htxx.service.impl;

import com.htxx.common.BaseResultData;
import com.htxx.entity.Ykfp;
import com.htxx.entity.YkfpFpjx;
import com.htxx.enums.CommonEnum;
import com.htxx.mapper.YkfpFpjxMapper;
import com.htxx.pojo.FpjxParams;
import com.htxx.service.YkfpFpjxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: Administrator
 * @date: 2018-11-24
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class YkfpFpjxServiceImpl implements YkfpFpjxService {

    @Autowired
    private YkfpFpjxMapper ykfpFpjxMapper;

    private static final String type_sj = "sj";
    private static final String type_jx = "jx";

    @Override
    public List<Ykfp> selectListByParams(Map<String, Object> map) {
        return ykfpFpjxMapper.selectListByParams(map);
    }

    @Override
    public BaseResultData updateFpsj(FpjxParams fpjxParams) {
        String[] fpIds = fpjxParams.getFpIds().split(",");
        for (String fpId : fpIds) {

            if (type_sj.equals(fpjxParams.getType())) {
                int count = ykfpFpjxMapper.selectCountByFpId(Long.valueOf(fpId));
                YkfpFpjx ykfpFpjx = new YkfpFpjx();
                if (count > 0) {
                    ykfpFpjx = ykfpFpjxMapper.selectByPrimaryKey(Long.valueOf(fpId));
                    if (CommonEnum.common_type_1.getValue().equals(ykfpFpjx.getSjbz())) {
                        continue;
                    }
                    ykfpFpjx.setJxbz(CommonEnum.common_type_1.getValue());
                    ykfpFpjxMapper.updateByPrimaryKey(ykfpFpjx);
                } else {
                    ykfpFpjx.setYkfpIdRef(Long.valueOf(fpId));
                    ykfpFpjx.setSjbz(CommonEnum.common_type_1.getValue());
                    ykfpFpjx.setJxbz(CommonEnum.common_type_0.getValue());
                    ykfpFpjx.setCreateId(fpjxParams.getCreateId());
                    ykfpFpjx.setCreateName(fpjxParams.getCreateName());
                    ykfpFpjx.setCreateTime(new Date());
                    ykfpFpjxMapper.insert(ykfpFpjx);
                }
            } else if (type_jx.equals(fpjxParams.getType())) {
                YkfpFpjx ykfpFpjx = ykfpFpjxMapper.selectByPrimaryKey(Long.valueOf(fpId));
                if (ykfpFpjx == null || !CommonEnum.common_type_1.getValue().equals(ykfpFpjx.getSjbz())) {
                    continue;
                }
                ykfpFpjx.setJxbz(CommonEnum.common_type_1.getValue());
                ykfpFpjxMapper.updateByPrimaryKey(ykfpFpjx);
            }
        }
        return BaseResultData.resultOK(null);
    }
}
