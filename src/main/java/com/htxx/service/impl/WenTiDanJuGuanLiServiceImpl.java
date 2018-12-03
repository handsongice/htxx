package com.htxx.service.impl;

import com.htxx.common.BaseResultData;
import com.htxx.constent.Constent;
import com.htxx.entity.*;
import com.htxx.mapper.*;
import com.htxx.service.WenTiDanJuGuanLiService;
import com.htxx.util.PoiExcelExportUtil;
import com.htxx.util.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: SaHongzhi
 * @Date: 2018-10-30 15:15
 */
@Service
@Transactional
@Slf4j
public class WenTiDanJuGuanLiServiceImpl implements WenTiDanJuGuanLiService{
    @Autowired
    DlDianfeiMapper dianfeiMapper;

    @Autowired
    DlDianfeiDetailMapper dlDianfeiDetailMapper;

    @Autowired
    DlDfzypShlMapper dlDfzypShlMapper;

    @Autowired
    DlDfzypShLogMapper dlDfzypShLogMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    RoleMapper roleMapper;

    @Override
    public void export(HttpServletRequest request, HttpServletResponse response, String ids, Map map) {
//        try {
            //获取当前时间
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String now = df.format(new Date());
            //创建文件名称
            String fileName = "问题单据列表" + now + ".xls";
            //Servlet准备 设置兼容浏览器 的OutputStream流
            ServletUtil su = new ServletUtil(fileName, request, response);
            su.poiExcelServlet();
            //设置excel 标题，列的取值field
            String[] heads = {"ID", "该行数据唯一标识", "数据类型", "发票类型",
                    "年月", "电号、用户号","总含税金额", "导出条数", "用户名",
                    "用户税号", "用户地址", "用户银行信息", "记录",
                     "操作人id", "入库时间"};
            String[] cols = {"id", "identityField", "dataType", "djType", "monthYear",
                    "userNum", "totalMoneyIncludeTax","exportNnum", "userName", "userTaxNo",
                    "userAddr", "userBankinfo", "recordInfo", "operator","createTime"};
            // 通知单列表
            List<DlDianfei> dianfeis=null;
            if(StringUtils.isBlank(ids)){
                //通知单按条件导出
                //pageMsg对象  并设置实体 通知单的查询条件
                //获取通知单所有列表
                dianfeis = dianfeiMapper.selectByCondition(map);
            }
            else{
                //通知单checkbox批量导出
                String[] id = ids.split(",");
                dianfeis = dianfeiMapper.getDlDianfeiListById(id);
            }
            dianfeis.forEach(item ->{
                if("0".equals(item.getDataType())){
                    item.setDataType("正常未开票");
                }else if("1".equals(item.getDataType())){
                    item.setDataType("问题票");
                }

                if("0".equals(item.getDjType())){
                    item.setDjType("电费收入");
                }else {
                    item.setDjType("未知收入类型");
                }
            });
            log.info("问题单据导出列表内容:" + dianfeis);
            //将      DlDianfei   和   DlDianfeiDetail  组装成供excel导出使用的对象
            PoiExcelExportUtil<DlDianfei> pee =
                    new PoiExcelExportUtil(fileName, heads, cols, dianfeis, su.getOut());
            //excel导出 至浏览器
            pee.exportExcel();
//        } catch (Exception e) {
//            log.error("WenTiDanJuGuanLiServiceImpl.export捕获异常！" + e);
//        }
    }

    @Override
    public BaseResultData saveOrEditWenTiDanJu(Integer isAdd, DlDianfeiDetail dlDianfeiDetail, DlDianfei dlDianfei, HttpSession session) {

        ;//1  为修改  0为增加
        if(isAdd==1){
            //更新DlDianfei单据状态为3---审核中
            DlDianfei dlDianfei1=new DlDianfei();
            long mainId=dlDianfei.getId();
            dlDianfei1.setId(mainId);
            dlDianfei1.setDataType("3");
            dianfeiMapper.updateByPrimaryKeySelective(dlDianfei1);

            //将修改后的单据暂时存在DlDianfeiTEMP和 DlDianfeiDetailTEMP 中

            dlDianfei.setIdentityField(String.valueOf(mainId));
            dianfeiMapper.insertSelectiveInToTempAndGetId(dlDianfei);
            long tempMainId=dlDianfei.getId();
            System.out.println(tempMainId);
            dlDianfeiDetail.setMainId(tempMainId);
            dlDianfeiDetailMapper.insertSelectiveIntoDetailTemp(dlDianfeiDetail);

            //获取电费自由票的审核流 根据 style=node 和当前企业ID 和parentid为0的获取第一个审核角色
            long parentId=0;
            DlDfzypShl dlDfzypShl=null;
            int count=0;

            //将发起人插入流程表
            Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
            long enterpriseId=enterprise.getId();
            String enterpriseName=enterprise.getName();
            Employee employee = (Employee) session.getAttribute(Constent.SESSION_EMPLOYEE);
            long employeeId=employee.getId();
            String employeeName=employee.getName();
            DlDfzypShLog dlDfzypShLog=new DlDfzypShLog();
            dlDfzypShLog.setCreateTime(new Date());
            dlDfzypShLog.setEnterpriseId(enterpriseId);
            dlDfzypShLog.setFpsgIdRef(mainId);
            dlDfzypShLog.setEmployeeId(employeeId);
            dlDfzypShLog.setEmployeeName(employeeName);
            dlDfzypShLog.setStatus(-1);
            dlDfzypShLogMapper.insertSelective(dlDfzypShLog);

            dlDfzypShLog.setEmployeeId(null);
            dlDfzypShLog.setEmployeeName(null);
            //将整个审核流程插入流程表
            do {
                DlDfzypShlExample dlDfzypShlExample=new DlDfzypShlExample();
                DlDfzypShlExample.Criteria criteria=dlDfzypShlExample.createCriteria();
                criteria.andEnterpriseIdEqualTo(enterpriseId);
                criteria.andStyleEqualTo("node");
                criteria.andParentIdEqualTo(parentId);
                dlDfzypShl=dlDfzypShlMapper.selectByExample(dlDfzypShlExample).get(0);

                //插入到审核日志当中去
                //DlDfzypShLog dlDfzypShLog=new DlDfzypShLog();
                dlDfzypShLog.setCreateTime(new Date());
                long deptId=dlDfzypShl.getDeptId();
                dlDfzypShLog.setDeptId(deptId);
                dlDfzypShLog.setDeptName(departmentMapper.selectByPrimaryKey(deptId).getName());
                long roleId=dlDfzypShl.getRoleId();
                dlDfzypShLog.setRoleId(roleId);
                dlDfzypShLog.setRoleName(roleMapper.selectByPrimaryKey(roleId).getName());
                dlDfzypShLog.setEnterpriseId(dlDianfei.getEnterpriseId());
                dlDfzypShLog.setFpsgIdRef(mainId);
                if(count==0){
                    //设置第一个审批人的审核状态为审批中
                    dlDfzypShLog.setStatus(1);
                }
                count++;
                dlDfzypShLogMapper.insertSelective(dlDfzypShLog);
                parentId=dlDfzypShl.getId();
                dlDfzypShLog.setStatus(0);
            }while (dlDfzypShl.getNextId() !=null );
            return BaseResultData.result("单据数据修改提交完成，等待审批中!","200",null);
        }else if(isAdd==0){
            long mainId=dianfeiMapper.insertSelectiveAndGetId(dlDianfei);
            dlDianfeiDetail.setMainId(mainId);
            dlDianfeiDetailMapper.insertSelective(dlDianfeiDetail);
            return BaseResultData.result("增加问题单据数据成功!","200",null);

        }else{
            return BaseResultData.resultError("未知操作",null);
        }

    }

//    @Override
//    public BaseResultData saveOrEditWenTiDanJu(Integer isAdd, DlDianfeiDetail dlDianfeiDetail, DlDianfei dlDianfei, HttpSession session) {
//
//        ;//1  为修改  0为增加
//        if(isAdd==1){
//            //更新DlDianfei单据状态为3---审核中
//            DlDianfei dlDianfei1=new DlDianfei();
//            long mainId=dlDianfei.getId();
//            dlDianfei1.setId(mainId);
//            dlDianfei1.setDataType("3");
//            dianfeiMapper.updateByPrimaryKeySelective(dlDianfei1);
//
//            //将修改后的单据暂时存在DlDianfeiTEMP和 DlDianfeiDetailTEMP 中
//
//            dlDianfei.setIdentityField(String.valueOf(mainId));
//            dianfeiMapper.insertSelectiveInToTempAndGetId(dlDianfei);
//            long tempMainId=dlDianfei.getId();
//            System.out.println(tempMainId);
//            dlDianfeiDetail.setMainId(tempMainId);
//            dlDianfeiDetailMapper.insertSelectiveIntoDetailTemp(dlDianfeiDetail);
//
//            //获取电费自由票的审核流 根据 style=node 和当前企业ID 和parentid为0的获取第一个审核角色
//            long parentId=0;
//            DlDfzypShl dlDfzypShl=null;
//            int count=0;
//
//            //将发起人插入流程表
//            Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
//            long enterpriseId=enterprise.getId();
//            String enterpriseName=enterprise.getName();
//            Employee employee = (Employee) session.getAttribute(Constent.SESSION_EMPLOYEE);
//            long employeeId=employee.getId();
//            String employeeName=employee.getName();
//            DlDfzypShLog dlDfzypShLog=new DlDfzypShLog();
//            dlDfzypShLog.setCreateTime(new Date());
//            dlDfzypShLog.setEnterpriseId(enterpriseId);
//            dlDfzypShLog.setFpsgIdRef(mainId);
//            dlDfzypShLog.setEmployeeId(employeeId);
//            dlDfzypShLog.setEmployeeName(employeeName);
//            dlDfzypShLog.setStatus(-1);
//            dlDfzypShLogMapper.insertSelective(dlDfzypShLog);
//
//            dlDfzypShLog.setEmployeeId(null);
//            dlDfzypShLog.setEmployeeName(null);
//            //将整个审核流程插入流程表
//            do {
//                DlDfzypShlExample dlDfzypShlExample=new DlDfzypShlExample();
//                DlDfzypShlExample.Criteria criteria=dlDfzypShlExample.createCriteria();
//                criteria.andEnterpriseIdEqualTo(enterpriseId);
//                criteria.andStyleEqualTo("node");
//                criteria.andParentIdEqualTo(parentId);
//                dlDfzypShl=dlDfzypShlMapper.selectByExample(dlDfzypShlExample).get(0);
//
//                //插入到审核日志当中去
//                //DlDfzypShLog dlDfzypShLog=new DlDfzypShLog();
//                dlDfzypShLog.setCreateTime(new Date());
//                long deptId=dlDfzypShl.getDeptId();
//                dlDfzypShLog.setDeptId(deptId);
//                dlDfzypShLog.setDeptName(departmentMapper.selectByPrimaryKey(deptId).getName());
//                long roleId=dlDfzypShl.getRoleId();
//                dlDfzypShLog.setRoleId(roleId);
//                dlDfzypShLog.setRoleName(roleMapper.selectByPrimaryKey(roleId).getName());
//                dlDfzypShLog.setEnterpriseId(dlDianfei.getEnterpriseId());
//                dlDfzypShLog.setFpsgIdRef(mainId);
//                if(count==0){
//                    //设置第一个审批人的审核状态为审批中
//                    dlDfzypShLog.setStatus(1);
//                }
//                count++;
//                dlDfzypShLogMapper.insertSelective(dlDfzypShLog);
//                parentId=dlDfzypShl.getId();
//                dlDfzypShLog.setStatus(0);
//            }while (dlDfzypShl.getNextId() !=null );
//
//
//            return BaseResultData.result("问题单据数据修改完成，等待审批结束!","200",null);
//        }else if(isAdd==0){
//            long mainId=dianfeiMapper.insertSelectiveAndGetId(dlDianfei);
//            dlDianfeiDetail.setMainId(mainId);
//            dlDianfeiDetailMapper.insertSelective(dlDianfeiDetail);
//            return BaseResultData.result("增加问题单据数据成功!","200",null);
//
//        }else{
//            return BaseResultData.resultError("未知操作",null);
//        }
//
//    }

    private DlDianfeiForExcel getDlDianfeiForExcel(DlDianfei   dlDianfei,   DlDianfeiDetail dlDianfeiDetail){
          DlDianfeiForExcel dlDianfeiForExcel=new DlDianfeiForExcel();
//        dlDianfeiForExcel.setCreateTime();
//        dlDianfeiForExcel.setDataType();
//        dlDianfeiForExcel.setDjType();
//        dlDianfeiForExcel.setExportNnum();
//        dlDianfeiForExcel.setGoodsAmount();
//        dlDianfeiForExcel.setGoodsMode();
//        dlDianfeiForExcel.setGoodsName();
//        dlDianfeiForExcel.setGoodsUnit();
//        dlDianfeiForExcel.setId();
//        dlDianfeiForExcel.setIdentityField();
//        dlDianfeiForExcel.setMoneyIncludeTax();
//        dlDianfeiForExcel.setMonthYear();
//        dlDianfeiForExcel.setOperator();
//        dlDianfeiForExcel.setRecordInfo();
//        dlDianfeiForExcel.setTaxRate();
//        dlDianfeiForExcel.setTotalMoneyIncludeTax();
//        dlDianfeiForExcel.setDataType();
//        dlDianfeiForExcel.setDataType();
//        dlDianfeiForExcel.setDataType();
//        dlDianfeiForExcel.setDataType();
//        dlDianfeiForExcel.setDataType();
//        dlDianfeiForExcel.setDataType();
//        dlDianfeiForExcel.setDataType();
//        dlDianfeiForExcel.setDataType();
        return dlDianfeiForExcel;
    }

    @Override
    public BaseResultData wenTiDanJuHeBing(String ids, String userName, String minMoneyIncludeTax, String maxMoneyIncludeTax, String taxNum, String remark, String date,String dateType){
        if(StringUtils.isBlank(ids)){
            //如果ID为空，则需要合并查询出的 全部问题单据
            List<DlDianfei> dianfeis=new ArrayList<>();
            Map map=new HashMap();
            map.put("userName",userName);
            map.put("minMoneyIncludeTax",minMoneyIncludeTax);
            map.put("maxMoneyIncludeTax",maxMoneyIncludeTax);
            map.put("taxNum",taxNum);
            map.put("remark",remark);
            map.put("searchDate",date);
            map.put("dateType",dateType);
            dianfeis=dianfeiMapper.selectByCondition(map);
            Iterator<DlDianfei> iterator=dianfeis.iterator();
            while (iterator.hasNext()){
                DlDianfei dlDianfei=iterator.next();
                if("1".equals(dlDianfei.getDataType())){
                    //只有是未在修改审核流的问题单据才能合并
                    wenTiDanJuHeBingOneByOne(dlDianfei.getId());
                }
            }
        }else{
            //如果ID不是空，那么按照checkbox批量合并问题单据
            String[] idArr = ids.split(",");
            for(int i=0;i<idArr.length;i++){
                wenTiDanJuHeBingOneByOne(Long.valueOf(idArr[i]));
            }

        }
        return BaseResultData.result("问题单据和对应的发票合并处理成功","200",null);
    }
    //合并一张问题单据
    public  void wenTiDanJuHeBingOneByOne(long id){
        //合并数据=问题单据-原单据；合并后的单据 单据状态为0。
        // 如果再进来一张问题单据  并 再次合并的话  新合并数据=问题单据-原单据，并且 新合并数据单据状态为0，并覆盖旧的合并数据
        // 如果合并数据金额<=0不可开票，必须和同一用电号的其他月份数据一起合并开票

        //1----根据id获取 问题单据

        DlDianfei troubleDlDianfei=dianfeiMapper.selectByPrimaryKey(id);
        DlDianfeiDetailExample dlDianfeiDetailExample=new DlDianfeiDetailExample();
        DlDianfeiDetailExample.Criteria criteria=dlDianfeiDetailExample.createCriteria();
        criteria.andMainIdEqualTo(id);
        DlDianfeiDetail troubleDlDianfeiDetail=dlDianfeiDetailMapper.
                selectByExample(dlDianfeiDetailExample).get(0);


        //2----根据identityId获取 已开票单据
        DlDianfeiExample dlDianfeiExample=new DlDianfeiExample();
        DlDianfeiExample.Criteria criteria2=dlDianfeiExample.createCriteria();
        //查询条件为 IdentityField相同  data_type 为2
        criteria2.andIdentityFieldEqualTo(troubleDlDianfei.getIdentityField());
        criteria2.andDataTypeEqualTo(Byte.valueOf(DanJuDaoRuServiceImpl.DATA_TYPE_2));
        DlDianfei alreadyOpenDlDianfei=dianfeiMapper.selectByExample(dlDianfeiExample).get(0);

        DlDianfeiDetailExample dlDianfeiDetailExample2=new DlDianfeiDetailExample();
        DlDianfeiDetailExample.Criteria criteria3=dlDianfeiDetailExample2.createCriteria();
        criteria3.andMainIdEqualTo(alreadyOpenDlDianfei.getId());
        DlDianfeiDetail alreadyOpenDlDianfeiDetail=dlDianfeiDetailMapper.
                selectByExample(dlDianfeiDetailExample2).get(0);

        //3----子表 含税金额、数量相减
        BigDecimal newGoodsAmount= troubleDlDianfeiDetail.getGoodsAmount().subtract(alreadyOpenDlDianfeiDetail.getGoodsAmount());
        BigDecimal newMoneyIncludeTax=troubleDlDianfeiDetail.getMoneyIncludeTax().subtract(alreadyOpenDlDianfeiDetail.getMoneyIncludeTax());
        troubleDlDianfeiDetail.setGoodsAmount(newGoodsAmount);
        troubleDlDianfeiDetail.setMoneyIncludeTax(newMoneyIncludeTax);
        //主表 总的含税金额也要变
        troubleDlDianfei.setTotalMoneyIncludeTax(newMoneyIncludeTax);

        //4----判断相同的identityField 但是单据状态为正常的是否存在，如果存在则删除，然后插入
        Map<String,String> paramMap=new HashMap<>();
        paramMap.put("identityField",troubleDlDianfei.getIdentityField());
        paramMap.put("dataType","0");
        List<DlDianfei>dlDianfeis=dianfeiMapper.selectByMap(paramMap);

        if(dlDianfeis.size()>0){
            dianfeiMapper.deleteByMap(paramMap);
            dlDianfeiDetailMapper.deleteByMainId(dlDianfeis.get(0).getId());
        }

        troubleDlDianfei.setDataType("0");
        dianfeiMapper.updateByPrimaryKeySelective(troubleDlDianfei);
        dlDianfeiDetailMapper.updateByPrimaryKeySelective(troubleDlDianfeiDetail);
    }


}
