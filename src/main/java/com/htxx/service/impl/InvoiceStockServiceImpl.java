package com.htxx.service.impl;

import com.htxx.common.BaseResultData;
import com.htxx.entity.InvoiceStock;
import com.htxx.entity.InvoiceStockLog;
import com.htxx.entity.Kpjxx;
import com.htxx.enums.CommonEnum;
import com.htxx.mapper.InvoiceStockLogMapper;
import com.htxx.mapper.InvoiceStockMapper;
import com.htxx.mapper.KpjxxMapper;
import com.htxx.pojo.DistributeParams;
import com.htxx.pojo.InvoiceStockCountParams;
import com.htxx.pojo.KpServiceParams;
import com.htxx.pojo.UpdateInvoiceStockParams;
import com.htxx.service.InvoiceStockService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Administrator
 * @date: 2018-11-06
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InvoiceStockServiceImpl implements InvoiceStockService {

    @Autowired
    private InvoiceStockMapper invoiceStockMapper;

    @Autowired
    private InvoiceStockLogMapper invoiceStockLogMapper;

    @Autowired
    private KpjxxMapper kpjxxMapper;

    @Override
    public BaseResultData insert(InvoiceStock record, boolean isRoot) {
        record.setQrbz(1);
        record.setCreateTime(new Date());
        record.setIsDel(0);
        int count = invoiceStockMapper.insert(record);
        this.addStockLog(record);
        return BaseResultData.resultOK(count);
    }

    @Override
    public BaseResultData updateByParams(InvoiceStock record, String type) {
        int count = invoiceStockMapper.updateByPrimaryKeySelective(record);
        if ("rk".equals(type)) {
            this.addStockLog(record);
        } else if ("ff".equals(type)) {
            this.removeStockLog(record);
        }
        return BaseResultData.resultOK(count);
    }

    @Override
    public BaseResultData delByPk(Long id) {
        int count = invoiceStockMapper.deleteByPrimaryKey(id);
        return BaseResultData.resultOK(count);
    }

    @Override
    public BaseResultData getByPk(Long id) {
        InvoiceStock stock = invoiceStockMapper.selectByPrimaryKey(id);
        return BaseResultData.resultOK(stock);
    }

    @Override
    public BaseResultData selectByParams(Map<String, Object> map) {
        List<InvoiceStock> invoiceStocks = invoiceStockMapper.selectByParams(map);
        return BaseResultData.resultOK(invoiceStocks);
    }

    /**
     * 发票分发
     *
     * @param params
     * @return
     */
    @Override
    public BaseResultData saveDistributeInvoice(DistributeParams params) {
        if (params.getRkId() == null || params.getRkId() == 0) {
            return BaseResultData.resultError("缺少关键参数！", null);
        }
        // 查询入库信息
        InvoiceStock stock = invoiceStockMapper.selectByPrimaryKey(params.getRkId());
        if (stock == null) {
            return BaseResultData.resultError("入库信息不存在！", null);
        }
        // 生成请求参数
        KpServiceParams kpServiceParams = new KpServiceParams();
        kpServiceParams.setTypeCode(stock.getFpdm());
        kpServiceParams.setInvoiceNO(params.getFphmq());
        kpServiceParams.setInvoiceCount(params.getFfsl());
        kpServiceParams.setInvoiceType(stock.getFplx());
        kpServiceParams.setClientNO(params.getKpjId());

        // 请求开票服务器(暂未部署)
            /*String kpServerRep = HttpUtil.doPost(KpServerUtil.distributeInvoiceAddr, KpServerUtil.reqXml(kpServiceParams), HttpUtil.TEXT_XML);
            if (kpServerRep == null) {
                return BaseResultData.resultError("获取开票服务器结果失败！", null);
            }
            BaseResultData resultData = KpServerUtil.repData(kpServerRep);
            if (resultData.getStatus().intValue() != 200) {
                return resultData;
            }*/
        Long newFphm = Long.valueOf(stock.getFphmq()) + params.getFfsl();
        // 插入分发记录表中
        InvoiceStockLog invoiceStockLog = new InvoiceStockLog();
        BeanUtils.copyProperties(stock, invoiceStockLog);
        invoiceStockLog.setId(null);
        invoiceStockLog.setKpjId(params.getKpjId());
        invoiceStockLog.setKpjName(params.getKpjName());
        invoiceStockLog.setStockRefId(stock.getId());
        invoiceStockLog.setFpsl(params.getFfsl());
        invoiceStockLog.setFphmz(StringUtils.leftPad(String.valueOf(newFphm - 1), 8, "0"));
        invoiceStockLog.setType("ff");
        invoiceStockLog.setStatus("1");
        invoiceStockLog.setCreateId(params.getCreateId());
        invoiceStockLog.setCreateName(params.getCreateName());
        invoiceStockLog.setCreateTime(new Date());
        invoiceStockLog.setIsDel(0);
        invoiceStockLogMapper.insert(invoiceStockLog);
        // 更新库存表
        stock.setFfsl(stock.getFfsl() + params.getFfsl());
        stock.setSysl(stock.getFpsl() - stock.getFfsl());
        stock.setFphmq(StringUtils.leftPad(String.valueOf(newFphm), 8, "0"));
        invoiceStockMapper.updateByPrimaryKeySelective(stock);
        // 插入到新分发的记录到库存表
        Kpjxx kpjxx = kpjxxMapper.selectByPrimaryKey(params.getKpjId());
        InvoiceStock tempStock = new InvoiceStock();
        BeanUtils.copyProperties(stock, tempStock);
        tempStock.setId(null);
        tempStock.setKpjId(kpjxx.getId());
        tempStock.setKpjCode(kpjxx.getCode());
        tempStock.setKpjName(kpjxx.getName());
        tempStock.setFpsl(params.getFfsl());
        tempStock.setFfsl(params.getFfsl());
        tempStock.setSysl(params.getFfsl());
        tempStock.setFromId(stock.getId());
        tempStock.setFphmq(params.getFphmq());
        tempStock.setFphmz(StringUtils.leftPad(String.valueOf(newFphm - 1), 8, "0"));
        tempStock.setQrbz(1);
        tempStock.setStatus(1);
        invoiceStockMapper.insert(tempStock);

        return BaseResultData.resultOK(null);
    }

    /**
     * 发票退回
     *
     * @param params
     * @return
     */
    @Override
    public BaseResultData saveReturnInvoice(DistributeParams params) {
        // 查询库存信息
        InvoiceStock stock = invoiceStockMapper.selectByPrimaryKey(params.getRkId());
        if (stock == null) {
            return BaseResultData.resultError("库存信息不存在！", null);
        }
        // 查询主机库存
        InvoiceStock parentStock = invoiceStockMapper.selectByPrimaryKey(stock.getFromId());
        if (stock == null) {
            return BaseResultData.resultError("主机库存信息不存在！", null);
        }
        // 生成请求参数
        KpServiceParams kpServiceParams = new KpServiceParams();
        kpServiceParams.setTypeCode(parentStock.getFpdm());
        kpServiceParams.setInvoiceNO(parentStock.getFphmq());
        kpServiceParams.setInvoiceCount(parentStock.getFfsl());
        kpServiceParams.setInvoiceType(parentStock.getFplx());
        kpServiceParams.setClientNO(parentStock.getKpjId());
        // 请求开票服务器(暂未部署)
        /*String kpServerRep = HttpUtil.doPost(KpServerUtil.returnInvoiceAddr, KpServerUtil.reqXml(kpServiceParams), HttpUtil.TEXT_XML);
        if (kpServerRep == null) {
            return BaseResultData.resultError("获取开票服务器结果失败！", null);
        }
        BaseResultData resultData = KpServerUtil.repData(kpServerRep);
        if (resultData.getStatus().intValue() != 200) {
            return resultData;
        }*/
        // 计算发票号码
        Long newFphm = Long.valueOf(stock.getFphmq()) - stock.getFfsl();
        //  新增库存信息
        InvoiceStock tempStock = new InvoiceStock();
        BeanUtils.copyProperties(stock, tempStock);
        tempStock.setId(null);
        tempStock.setKpjId(parentStock.getId());
        tempStock.setKpjCode(parentStock.getKpjCode());
        tempStock.setKpjName(parentStock.getKpjName());
        tempStock.setFpsl(stock.getSysl());
        tempStock.setFfsl((long) 0);
        tempStock.setSysl(stock.getSysl());
        tempStock.setFphmq(stock.getFphmq());
        tempStock.setFphmz(stock.getFphmz());
        tempStock.setStatus(null);
        tempStock.setQrbz(1);
        tempStock.setFromId((long) 0);
        tempStock.setCreateTime(new Date());
        invoiceStockMapper.insert(tempStock);
        // 插入分发记录表中
        InvoiceStockLog invoiceStockLog = new InvoiceStockLog();
        BeanUtils.copyProperties(tempStock, invoiceStockLog);
        invoiceStockLog.setId(null);
        invoiceStockLog.setStockRefId(tempStock.getId());
        invoiceStockLog.setType("rk");
        invoiceStockLog.setStatus("1");
        invoiceStockLog.setCreateTime(new Date());
        invoiceStockLog.setIsDel(0);
        invoiceStockLogMapper.insert(invoiceStockLog);
        // 更新状态为已退回
        stock.setStatus(2);
        invoiceStockMapper.updateByPrimaryKey(stock);

        return BaseResultData.resultOK(null);
    }

    /**
     * 发票更新库存检查（更新）
     *
     * @param params
     * @return
     */
    @Override
    public BaseResultData getInvoiceStockCheck(UpdateInvoiceStockParams params) {
        Map<String, Object> tempParams = new HashMap<>(16);
        tempParams.put("enterpriseId", params.getEnterpriseId());
        // tempParams.put("kpjCode", params.getKpjCode());
        tempParams.put("fpdm", params.getFpdm());
        tempParams.put("fphmq", params.getFphm());
        List<InvoiceStock> invoiceStocks = invoiceStockMapper.selectByParams(tempParams);
        if (invoiceStocks == null || invoiceStocks.size() == 0) {
            return BaseResultData.resultOK("未查询到相关的库存信息！");
        }
        return BaseResultData.resultOK(invoiceStocks.get(0));
    }

    /**
     * 发票更新库存（发票开具）
     *
     * @param params
     * @return
     */
    @Override
    public BaseResultData updateInvoiceStock(UpdateInvoiceStockParams params) {
        BaseResultData resultData = this.getInvoiceStockCheck(params);
        if (!CommonEnum.msg_code_200.getValue().equals(this.getInvoiceStockCheck(params).getStatus())) {
            InvoiceStock stock = resultData.getData() == null ? new InvoiceStock() : (InvoiceStock) resultData.getData();

            stock.setSysl(stock.getSysl() - 1);
            stock.setFphmq(StringUtils.leftPad(String.valueOf(Long.valueOf(stock.getFphmq()) + 1), 8, "0"));
            stock.setUpdateTime(new Date());

            invoiceStockMapper.updateByPrimaryKeySelective(stock);
            return BaseResultData.resultOK(null);
        } else {
            return BaseResultData.resultOK("未查询到相关的库存信息！");
        }
    }

    @Override
    public InvoiceStockCountParams selectStockCount(String beginDateStr, String endDateStr, String fplx) {
        InvoiceStockCountParams params = invoiceStockMapper.selectStockCount(beginDateStr, endDateStr, fplx);
        return params == null ? new InvoiceStockCountParams() : params;
    }

    @Override
    public String selectStockCountByParams(Map<String, Object> map) {
        return invoiceStockMapper.selectStockCountByParams(map);
    }

    private int addStockLog(InvoiceStock invoiceStock) {
        InvoiceStockLog log = new InvoiceStockLog();
        BeanUtils.copyProperties(invoiceStock, log);
        log.setId(null);
        log.setType("rk");
        log.setStatus("1");
        log.setStockRefId(invoiceStock.getId());
        log.setCreateTime(new Date());
        return invoiceStockLogMapper.insert(log);
    }

    private int removeStockLog(InvoiceStock invoiceStock) {
        InvoiceStockLog log = new InvoiceStockLog();
        BeanUtils.copyProperties(invoiceStock, log);
        log.setId(null);
        log.setType("ff");
        log.setStatus("1");
        log.setStockRefId(invoiceStock.getId());
        log.setCreateTime(new Date());
        log.setIsDel(0);
        return invoiceStockLogMapper.insert(log);
    }
}
