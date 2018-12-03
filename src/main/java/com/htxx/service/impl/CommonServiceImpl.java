package com.htxx.service.impl;

import com.htxx.common.BaseResultData;
import com.htxx.constent.Constent;
import com.htxx.entity.*;
import com.htxx.mapper.*;
import com.htxx.pojo.UpdateInvoiceStockParams;
import com.htxx.service.CommonService;
import com.htxx.service.InvoiceStockService;
import com.htxx.util.Base64Util;
import com.htxx.util.DateUtil;
import com.htxx.util.Dom4JToInvoiceBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ll on 2018-11-06
 * 公用方法serviceImpl
 */
@Slf4j
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private SpxxMapper spxxMapper;

    @Autowired
    private KpjxxMapper kpjxxMapper;

    @Autowired
    private FplxMapper fplxMapper;

    @Autowired
    private FpxeMapper fpxeMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private DepartmentEmployeeMapper departmentEmployeeMapper;

    @Autowired
    private DlDepartmentKpjMapper dlDepartmentKpjMapper;

    @Autowired
    private YkfpMapper ykfpMapper;

    @Autowired
    private NoticeMapper noticeMapper;

    @Autowired
    private InvoiceStockService invoiceStockService;

    @Override
    public List<Spxx> getSpxxListByYwlx(Map<String, Object> condition) {
        return spxxMapper.getSpxxListByYwlx(condition);
    }

    @Override
    public List<Kpjxx> getCurrentKpjxx(Long enterpriseId) {
        //设置查询条件 企业id
        Map<String, Object> map = new HashMap<>();
        map.put("enterprise_id", enterpriseId);
        return kpjxxMapper.selectByParams(map);
    }

    @Override
    public List<Fplx> getCurrentFplx(Long enterpriseId) {
        return fplxMapper.getFplxByEnterpriseId(enterpriseId);
    }

    @Override
    public double getCurrentFpxe(Long enterpriseId, int kpjCode, int fplxCode) {
        //设置查询条件 企业id 开票机号 发票类型代码
        Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("enterpriseId", enterpriseId);
        conditionMap.put("kpjCode", kpjCode);
        conditionMap.put("fplxCode", fplxCode);
        List<Fpxe> fpxeList = fpxeMapper.getFpxeByEnterpsIdAndKpjCodeAndFplxCode(conditionMap);
        if (0 == fpxeList.size()) {
            //若没有取到限额，即取默认值
            return Constent.KPXE_DEFAULT;
        } else {
            return fpxeList.get(0).getAmount();
        }
    }

    /**
     * 获取当前登录人对应的部门下的所有开票机
     *
     * @param employeeId
     * @param enterpriseId
     * @return
     */
    @Override
    public List<Kpjxx> findAllKpj(Long employeeId, Long enterpriseId) {
       /* //获取部门ID,一个人可属于多个部门
        List<Long> deptIds = departmentEmployeeMapper.selectByEmployeeId(employeeId, enterpriseId);
        //根据多个部门ID获取对应的多个开票机ID
        List<Long> kpjIds = dlDepartmentKpjMapper.selectByDeptId(deptIds);

        //根据kpjID获取kpj信息*/
        List<Kpjxx> kpjxxes = kpjxxMapper.selectEmpDeptKpjxxList(employeeId, enterpriseId);
        return kpjxxes;
    }

    @Override
    public BaseResultData gbkChange(String xml) {
        String encoded = Base64Util.getBase64(xml, "gbk");
        return BaseResultData.resultOK(encoded);
    }


    /**
     * 发票开具完成后，保存cong对应接口查询到的发票信息
     *
     * @param
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void dom4JToInvoiceBean(QryInvResult qryInvResult, HttpSession session) {
        //高可靠电费 开票成功 更新单据状态 3-已开票
        if (Constent.SIGN_STRING_2.equals(qryInvResult.getYwlx())) {
            String[] strs = {qryInvResult.getInfoBillNumber()};
            noticeMapper.updateInvoiceStatusByGzdh(strs);
        }
        Dom4JToInvoiceBean dom4JToInvoiceBean = new Dom4JToInvoiceBean();
        Ykfp ykfp = dom4JToInvoiceBean.getYkfpBean(qryInvResult.getInfo());
        ykfp.setFpdm(qryInvResult.getInfoTypeCode());
        ykfp.setFphm(qryInvResult.getInfoNumber());
        ykfp.setDjh(qryInvResult.getInfoBillNumber());
        ykfp.setHjje(qryInvResult.getInfoAmount());
        ykfp.setHjse(qryInvResult.getInfoTaxAmount());
        ykfp.setJshj(ykfp.getHjje() + ykfp.getHjse());
        ykfp.setFjh(qryInvResult.getMachineNo());
        ykfp.setFpzl(qryInvResult.getFpzl());
        ykfp.setKplx(qryInvResult.getKplx());
        ykfp.setKpsj(DateUtil.getTime());
        ykfp.setYwlx(qryInvResult.getYwlx());
        //获取原发票代码 原发票号码
        String yfpdm=qryInvResult.getYfpdm();
        String yfphm=qryInvResult.getYfphm();
        //获取冲红原因
        String chyy=qryInvResult.getChyy();
        if(StringUtils.isNotBlank(yfpdm) && StringUtils.isNotBlank(yfphm)){
            //如果yfpdm   yfphm不是空，则说明本次操作是开红票操作
            //更新原蓝票状态为已冲红
            ykfpMapper.updateKplxByFpdmhm(yfpdm, yfphm, Constent.SIGN_INT_3);
            ykfp.setYfpdm(yfpdm);
            ykfp.setYfphm(yfphm);
        } else {
            ykfp.setYfpdm("");
            ykfp.setYfphm("");
        }
        if(StringUtils.isNotBlank(chyy)){
            ykfp.setChyy(chyy);
        }else {
            ykfp.setChyy("");
        }
        //获取企业信息
        Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
        //获取登录人信息
        Employee employee = (Employee) session.getAttribute(Constent.SESSION_EMPLOYEE);
        ykfp.setEmployeeId(employee.getId());
        ykfp.setEnterpriseId(enterprise.getId());
        List<YkfpDel> list = ykfp.getYkfpDelList();
        list.forEach(item -> {
            int index = list.indexOf(item);
            item.setXh(index + 1);
            item.setDjh(ykfp.getDjh());
            item.setYwlx(2);
        });
        log.info("ykfp:" + ykfp);
        int result = ykfpMapper.insertYkfpMain(ykfp);
        int result1 = ykfpMapper.insertYkfpDel(ykfp.getYkfpDelList());

        log.info("result:" + result);
        log.info("result1:" + result1);

        //更新库存
        UpdateInvoiceStockParams updateInvoiceStockParams = new UpdateInvoiceStockParams();
        updateInvoiceStockParams.setFpdm(qryInvResult.getInfoTypeCode());
        updateInvoiceStockParams.setFphm(qryInvResult.getInfoNumber());
        BaseResultData baseResultData = invoiceStockService.updateInvoiceStock(updateInvoiceStockParams);
        if("200" == baseResultData.getStatus()){
            log.info("开票后更新库存成功！");
        }else{
            log.error("开票后更新库存出错！错误内容："+baseResultData.getMsg());
        }

    }

    /**
     * 获取当前登录人所属部门ids
     *
     * @param empId
     * @param enterpriseId
     * @return
     */
    @Override
    public List<Long> getDeptsByEmp(Long empId, Long enterpriseId) {
        return employeeMapper.selectDeptsByEmp(empId, enterpriseId);
    }

    @Override
    public List<Long> getRolesByEmp(Long empId, Long enterpriseId) {
        return employeeMapper.selectRolesByEmp(empId, enterpriseId);
    }

}
