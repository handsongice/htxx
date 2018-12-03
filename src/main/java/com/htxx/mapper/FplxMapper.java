package com.htxx.mapper;

import com.htxx.entity.Fplx;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by ll on 2018-11-06
 * 发票类型（专票  普票  电子票  卷票）
 */
@Component
public interface FplxMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Fplx record);

    int insertSelective(Fplx record);

    Fplx selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Fplx record);

    int updateByPrimaryKey(Fplx record);

    List<Fplx> selectByParams(Map<String, Object> map);

    Fplx findByParams(Fplx record);

    int delFplx(Long id);
    /**
     * 通过企业id 查询 发票类型（普票 专票 电子票 卷票）
     * @param enterpriseId 企业id
     * @return
     */
    List<Fplx> getFplxByEnterpriseId(Long enterpriseId);

    List<Fplx> selectByParams(Fplx fplx);
}
