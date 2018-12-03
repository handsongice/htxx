package com.htxx.service;

import com.htxx.common.BaseResultData;
import com.htxx.entity.Fplx;
import com.htxx.entity.Kpjxx;
import com.htxx.entity.QryInvResult;
import com.htxx.entity.Spxx;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by ll on 2018-11-06
 * 公用方法service
 */
public interface CommonService {
    /**
     * 通过业务类型 获取商品信息列表
     *
     * @param condition 业务类型 1-普通电费 2-高可靠电费 3-自由票
     * @return
     */
    List<Spxx> getSpxxListByYwlx(Map<String, Object> condition);

    /**
     * 获取当前企业对应的开票机信息
     *
     * @return
     */
    List<Kpjxx> getCurrentKpjxx(Long enterpriseId);

    /**
     * 根据 企业id 获取当前的发票类型
     *
     * @param enterpriseId 企业id
     * @return
     */
    List<Fplx> getCurrentFplx(Long enterpriseId);

    /**
     * 根据 企业id、开票机号（分机号）、发票类型 获取当前企业的 开票限额
     *
     * @param enterpriseId 企业id
     * @param kpjCode      开票机号（分机号）0-主机  其他为分机
     * @param fplxCode     发票类型（0-专票 2-普票 51-电子票 41-卷票）
     * @return 若数据库查询结果为空 则输出默认开票限额 9999999.99
     */
    double getCurrentFpxe(Long enterpriseId, int kpjCode, int fplxCode);

    /**
     * 获取当前登录人对应的部门下的所有开票机
     *
     * @param employeeId
     * @param enterpriseId
     * @return
     */
    List<Kpjxx> findAllKpj(Long employeeId, Long enterpriseId);

    /**
     * 税收分类编码后台gbk转义
     *
     * @param xml
     * @return
     */
    BaseResultData gbkChange(String xml);

    /**
     * 发票开具完成后，保存cong对应接口查询到的发票信息
     *
     * @param
     */
    void dom4JToInvoiceBean(QryInvResult qryInvResult, HttpSession session);

    /**
     * 获取当前登录人所属部门ids
     *
     * @param empId
     * @param enterpriseId
     * @return
     */
    List<Long> getDeptsByEmp(Long empId, Long enterpriseId);

    /**
     * 获取当前登录人所属岗位ids
     *
     * @param empId
     * @param enterpriseId
     * @return
     */
    List<Long> getRolesByEmp(Long empId, Long enterpriseId);
}
