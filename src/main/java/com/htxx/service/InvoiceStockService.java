package com.htxx.service;

import com.htxx.common.BaseResultData;
import com.htxx.entity.InvoiceStock;
import com.htxx.pojo.DistributeParams;
import com.htxx.pojo.InvoiceStockCountParams;
import com.htxx.pojo.UpdateInvoiceStockParams;

import java.util.Map;

public interface InvoiceStockService {
    /**
     * 新增
     *
     * @param record
     * @return
     */
    BaseResultData insert(InvoiceStock record, boolean isRoot);

    /**
     * 根据条件更新
     *
     * @param record
     * @return
     */
    BaseResultData updateByParams(InvoiceStock record, String type);

    /**
     * 根据主键删除
     *
     * @param id
     * @return
     */
    BaseResultData delByPk(Long id);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    BaseResultData getByPk(Long id);

    /**
     * 根据条件查询列表
     *
     * @param map
     * @return
     */
    BaseResultData selectByParams(Map<String, Object> map);

    /**
     * 发票分发
     *
     * @param params
     * @return
     */
    BaseResultData saveDistributeInvoice(DistributeParams params);

    /**
     * 发票退回
     *
     * @param params
     * @return
     */
    BaseResultData saveReturnInvoice(DistributeParams params);

    /**
     * 发票更新库存检查
     *
     * @param params
     * @return
     */
    BaseResultData getInvoiceStockCheck(UpdateInvoiceStockParams params);

    /**
     * 发票更新库存（发票开具）
     *
     * @param params
     * @return
     */
    BaseResultData updateInvoiceStock(UpdateInvoiceStockParams params);

    /**
     * 发票库存统计
     *
     * @param beginDateStr
     * @param endDateStr
     * @return
     */
    InvoiceStockCountParams selectStockCount(String beginDateStr, String endDateStr, String fplx);

    /**
     * 根据条件查询剩余库存（发票盘点时使用）
     * @param map
     * @return
     */
    String selectStockCountByParams(Map<String, Object> map);
}