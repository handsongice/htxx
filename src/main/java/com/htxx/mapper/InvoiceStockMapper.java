package com.htxx.mapper;

import com.htxx.entity.InvoiceStock;
import com.htxx.pojo.InvoiceStockCountParams;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface InvoiceStockMapper {

    int deleteByPrimaryKey(Long id);

    int insert(InvoiceStock record);

    InvoiceStock selectByPrimaryKey(Long id);

    List<InvoiceStock> selectByParams(Map<String, Object> map);

    int updateByPrimaryKeySelective(InvoiceStock record);

    int updateByPrimaryKey(InvoiceStock record);

    InvoiceStockCountParams selectStockCount(@Param("beginDateStr") String beginDateStr, @Param("endDateStr") String endDateStr, @Param("fplx") String fplx);

    String selectStockCountByParams(Map<String, Object> map);
}