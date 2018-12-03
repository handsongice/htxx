package com.htxx.service.impl;

import com.htxx.common.BaseResultData;
import com.htxx.constent.Constent;
import com.htxx.entity.*;
import com.htxx.mapper.*;
import com.htxx.service.DanJuXiuGaiShenHeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: SaHongzhi
 * @Date: 2018-11-28 11:09
 */
@Slf4j
@Transactional
@Service
public class DanJuXiuGaiShenHeServiceImpl implements DanJuXiuGaiShenHeService {

    @Autowired
    DlDfzypShlMapper dlDfzypShlMapper;

    @Autowired
    DlDfzypShLogMapper dlDfzypShLogMapper;

    @Autowired
    DlDianfeiMapper dlDianfeiMapper;

    @Autowired
    DlDianfeiDetailMapper dlDianfeiDetailMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    DepartmentEmployeeMapper departmentEmployeeMapper;

    @Override
    public BaseResultData submitSuggestion(int shyj, String suggestion, long mainId, HttpSession session) {

        //获取电费自由票的审核流 根据 style=node 和当前企业ID 和parentid为0的获取第一个审核角色
        Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
        long enterpriseId=enterprise.getId();
        String enterpriseName=enterprise.getName();
        Employee employee = (Employee) session.getAttribute(Constent.SESSION_EMPLOYEE);
        long employeeId=employee.getId();
        String employeeName=employee.getName();

        //查看当前登录人是否已经对该单据做过审核
        // 1.mainid employId endflag==null,即：该单据在审核中的审批流，该人已经做过审核
        DlDfzypShLogExample dlDfzypShLogExample3=new DlDfzypShLogExample();
        DlDfzypShLogExample.Criteria criteria8=dlDfzypShLogExample3.createCriteria();
        criteria8.andEnterpriseIdEqualTo(enterpriseId);
        criteria8.andEndFlagIsNull();
        criteria8.andEmployeeIdEqualTo(employeeId);
        criteria8.andFpsgIdRefEqualTo(mainId);
        List<DlDfzypShLog> dlDfzypShLogs=dlDfzypShLogMapper.selectByExample(dlDfzypShLogExample3);
        //2.或者审核流全部都已经结束  即：EndFlag不为空
        DlDfzypShLogExample dlDfzypShLogExample4=new DlDfzypShLogExample();
        DlDfzypShLogExample.Criteria criteria9=dlDfzypShLogExample4.createCriteria();
        criteria9.andEnterpriseIdEqualTo(enterpriseId);
        criteria9.andEndFlagIsNull();
        criteria9.andFpsgIdRefEqualTo(mainId);
        List<DlDfzypShLog> dlDfzypShLogs2=dlDfzypShLogMapper.selectByExample(dlDfzypShLogExample4);
        if(dlDfzypShLogs.size()>0 || dlDfzypShLogs2.size()<=0){
            return BaseResultData.resultError("请勿重复审核!",null);
        }

        DlDfzypShLogExample dlDfzypShLogExample=new DlDfzypShLogExample();
        DlDfzypShLogExample.Criteria criteria1=dlDfzypShLogExample.createCriteria();
        //更新条件 企业ID  单据ID 审核未结束 在审核中
        criteria1.andEnterpriseIdEqualTo(enterpriseId);
        criteria1.andEndFlagIsNull();
        criteria1.andFpsgIdRefEqualTo(mainId);
        criteria1.andStatusEqualTo(1);

        //更新内容
        DlDfzypShLog dlDfzypShLog=new DlDfzypShLog();
        dlDfzypShLog.setEmployeeId(employeeId);
        dlDfzypShLog.setEmployeeName(employeeName);
        dlDfzypShLog.setStatus(shyj);
        dlDfzypShLog.setContent(suggestion);
        dlDfzypShLog.setUpdateTime(new Date());
        log.info("当前登录人:"+employeeName+"对问题单据:"+mainId+"的审核结果为:"+shyj+"意见为:"+suggestion);
        //更新当前审核人审批结果的log
        dlDfzypShLogMapper.updateByExampleSelective(dlDfzypShLog,dlDfzypShLogExample);


        DlDianfeiExample dlDianfeiExample2=new DlDianfeiExample();
        DlDianfeiExample.Criteria criteria4=dlDianfeiExample2.createCriteria();
        criteria4.andIdEqualTo(mainId);
        criteria4.andDataTypeEqualTo(Byte.valueOf("3"));
        DlDianfei dlDianfei=dlDianfeiMapper.selectByExample(dlDianfeiExample2).get(0);

        // 查出临时表的主表内容
        DlDianfeiExample dlDianfeiExample=new DlDianfeiExample();
        DlDianfeiExample.Criteria criteria7=dlDianfeiExample.createCriteria();
        criteria7.andIdentityFieldEqualTo(String.valueOf(mainId));
        DlDianfei dlDianfeiTemp=dlDianfeiMapper.selectFromDlDianfeiTemp(dlDianfeiExample).get(0);

        if(shyj==3){
            //当前登录人审核同意
            //查当前单据正在进行 即：endflag 是null 的审批流的下一位status为0的审批人
            DlDfzypShLogExample dlDfzypShLogExample1=new DlDfzypShLogExample();
            DlDfzypShLogExample.Criteria criteria2=dlDfzypShLogExample1.createCriteria();
            criteria2.andEnterpriseIdEqualTo(enterpriseId);
            criteria2.andEndFlagIsNull();
            criteria2.andFpsgIdRefEqualTo(mainId);
            criteria2.andStatusEqualTo(0);
            dlDfzypShLogs=dlDfzypShLogMapper.selectByExample(dlDfzypShLogExample1);
            //如果同意，则检查是否还有下一位审批人
//            DlDfzypShlExample dlDfzypShlExample=new DlDfzypShlExample();
//            DlDfzypShlExample.Criteria criteria=dlDfzypShlExample.createCriteria();
//            criteria.andEnterpriseIdEqualTo(enterpriseId);
//            criteria.andStyleEqualTo("node");
//            criteria.andParentIdEqualTo(departmentId);
//            List<DlDfzypShl> dlDfzypShls=dlDfzypShlMapper.selectByExample(dlDfzypShlExample);
            if(dlDfzypShLogs.size()==0){
                log.info("问题单据:"+mainId+"审批流结束");
                // 1.没有下一位审批人整个审批流结束
                DlDfzypShLogExample dlDfzypShLogExample2=new DlDfzypShLogExample();
                DlDfzypShLogExample.Criteria criteria6=dlDfzypShLogExample2.createCriteria();
                criteria6.andEnterpriseIdEqualTo(enterpriseId);
                criteria6.andEndFlagIsNull();
                criteria6.andFpsgIdRefEqualTo(mainId);
                DlDfzypShLog record=new DlDfzypShLog();
                record.setEndFlag(1);
                dlDfzypShLogMapper.updateByExampleSelective( record,dlDfzypShLogExample2);


                //根据临时表内的数据  更新原单据的主表和子表
//                //1.1 查出临时表的内容
//                DlDianfeiExample dlDianfeiExample=new DlDianfeiExample();
//                DlDianfeiExample.Criteria criteria7=dlDianfeiExample.createCriteria();
//                criteria7.andIdentityFieldEqualTo(String.valueOf(mainId));
//                DlDianfei dlDianfeiTemp=dlDianfeiMapper.selectFromDlDianfeiTemp(dlDianfeiExample).get(0);
                DlDianfeiDetailExample dlDianfeiDetailExample=new DlDianfeiDetailExample();
                DlDianfeiDetailExample.Criteria criteria3=dlDianfeiDetailExample.createCriteria();
                criteria3.andMainIdEqualTo(dlDianfeiTemp.getId());
                DlDianfeiDetail dlDianfeiDetailTemp=dlDianfeiDetailMapper.selectByExampleFromTemp(dlDianfeiDetailExample).get(0);

                //1.2查出正式表的内容
                DlDianfeiDetailExample dlDianfeiDetailExample1=new DlDianfeiDetailExample();
                DlDianfeiDetailExample.Criteria criteria5=dlDianfeiDetailExample1.createCriteria();
                criteria5.andMainIdEqualTo(dlDianfei.getId());
                DlDianfeiDetail dlDianfeiDetail=dlDianfeiDetailMapper.selectByExample(dlDianfeiDetailExample1).get(0);

                //将正式表的内容更新成临时表的内容
                dlDianfei.setUserName(dlDianfeiTemp.getUserName());
                dlDianfei.setUserTaxNo(dlDianfeiTemp.getUserTaxNo());
                dlDianfei.setUserAddr(dlDianfeiTemp.getUserAddr());
                dlDianfei.setUserBankinfo(dlDianfeiTemp.getUserBankinfo());
                dlDianfei.setDataType(dlDianfeiTemp.getDataType());

                dlDianfeiDetail.setGoodsAmount(dlDianfeiDetailTemp.getGoodsAmount());
                dlDianfeiDetail.setGoodsName(dlDianfeiDetailTemp.getGoodsName());
                dlDianfeiDetail.setGoodsUnit(dlDianfeiDetailTemp.getGoodsUnit());
                dlDianfeiDetail.setTaxRate(dlDianfeiDetailTemp.getTaxRate());
                dlDianfeiDetail.setMoneyIncludeTax(dlDianfeiDetailTemp.getMoneyIncludeTax());


                dlDianfeiMapper.updateByPrimaryKeySelective(dlDianfei);
                dlDianfeiDetailMapper.updateByPrimaryKeySelective(dlDianfeiDetail);
            }else{
                //2.有下一位审批人,更新下一位的审批人dl_dfzyp_sh_log为审批中
               // DlDfzypShl dlDfzypShl=dlDfzypShls.get(0);

                DlDfzypShLog nextDlDfzypShLog=dlDfzypShLogs.get(0);
                nextDlDfzypShLog.setStatus(1);
                long deptId=nextDlDfzypShLog.getDeptId();
                String departmentName=nextDlDfzypShLog.getDeptName();
                long roleId=nextDlDfzypShLog.getRoleId();
                String roleName=nextDlDfzypShLog.getRoleName();
                log.info("问题单据:"+mainId+"审批流,有下一位审批人,企业名称:"+enterpriseName+"部门名称"+departmentName+"角色名称"+roleName);
                dlDfzypShLogMapper.updateByPrimaryKey(nextDlDfzypShLog);
            }
        }else{
            //不同意，当前审核流结束
            //将dl_dianfei的单据状态改为实际单据状态，即：从审核状态中恢复到其原来的状态
            dlDianfei.setDataType(dlDianfeiTemp.getDataType());
            dlDianfeiMapper.updateByPrimaryKeySelective(dlDianfei);

            DlDfzypShLogExample dlDfzypShLogExample2=new DlDfzypShLogExample();
            DlDfzypShLogExample.Criteria criteria6=dlDfzypShLogExample2.createCriteria();
            criteria6.andEnterpriseIdEqualTo(enterpriseId);
            criteria6.andEndFlagIsNull();
            criteria6.andFpsgIdRefEqualTo(mainId);
            DlDfzypShLog record=new DlDfzypShLog();
            record.setEndFlag(1);
            dlDfzypShLogMapper.updateByExampleSelective( record,dlDfzypShLogExample2);
        }

        return BaseResultData.resultOK(null);


    }
}
