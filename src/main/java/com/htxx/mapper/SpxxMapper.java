package com.htxx.mapper;

import com.htxx.entity.Spxx;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by ll on 2018-11-05
 * 商品信息
 */
@Component
public interface SpxxMapper {
    /**
     * 根据业务类型 获取商品列表
     * @param condition 类型 1-普通电费 2-高可靠电费 3-自由票
     * @return
     */
    List<Spxx> getSpxxListByYwlx(Map<String, Object> condition);
}
