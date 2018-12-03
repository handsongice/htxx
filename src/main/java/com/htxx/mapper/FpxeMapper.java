package com.htxx.mapper;

import com.htxx.entity.Fpxe;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by ll on 2018-11-06
 * 发票限额
 */
@Component
public interface FpxeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Fpxe record);

    int insertSelective(Fpxe record);

    Fpxe selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Fpxe record);

    int updateByPrimaryKey(Fpxe record);

    List<Fpxe> selectByParams(Map<String, Object> map);

    Fpxe findByParams(Fpxe record);

    /**
     * 根据企业id 和 开票机code 和 发票类型code 获取发票限额信息条目
     * @param conditionMap {"enterpriseId":"xxx","kpjCode":"xxx","fplxCode":"xxx"}
     * @return
     */
    List<Fpxe> getFpxeByEnterpsIdAndKpjCodeAndFplxCode(Map<String,Object> conditionMap);
}
