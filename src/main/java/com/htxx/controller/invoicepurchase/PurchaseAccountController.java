package com.htxx.controller.invoicepurchase;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htxx.common.BaseResultData;
import com.htxx.controller.BaseController;
import com.htxx.entity.*;
import com.htxx.enums.CommonEnum;
import com.htxx.mapper.FpsgMapper;
import com.htxx.mapper.FpsgShLogEntityMapper;
import com.htxx.mapper.FpsgShlMapper;
import com.htxx.pojo.InvoiceReviewParams;
import com.htxx.pojo.LayUiPageParams;
import com.htxx.service.CommonService;
import com.htxx.service.DepartmentService;
import com.htxx.service.RoleService;
import com.htxx.util.DateUtil;
import com.htxx.util.PoiExcelExportUtil;
import com.htxx.util.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 申购台账
 *
 * @author zhanghs
 * @date 2018/11/01
 */
@Controller
@Slf4j
public class PurchaseAccountController extends BaseController {

    @Autowired
    private FpsgMapper fpsgMapper;

    @Autowired
    private FpsgShLogEntityMapper fpsgShLogEntityMapper;

    @Autowired
    private FpsgShlMapper fpsgShlMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private CommonService commonService;

    /**
     * 获取申购台账列表
     *
     * @param params
     * @return
     */
    @GetMapping("/noc/purchaseAccount/getList")
    @ResponseBody
    public Object getAccountList(@RequestParam Map<String, Object> params) {
        //params = getLoginSearchParams(params);
        params.put("enterpriseId", this.getLoginEnterprise().getId());

        int pageNum = params.get("page") == null ? 1 : Integer.parseInt(params.get("page").toString());
        int pageSize = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(pageNum, pageSize, true);

        List<Fpsg> list = fpsgMapper.selectByParams(params);
        PageInfo<Fpsg> pageInfo = new PageInfo<>(list);
        LayUiPageParams<Fpsg> pageParams = LayUiPageParams.defaultResult(pageInfo.getTotal(), list);
        return pageParams;
    }

    /**
     * 获取申购台账详情
     *
     * @param id
     * @return
     */
    @RequestMapping("/noc/purchaseAccount/getDetail")
    public ModelAndView getDetail(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView("invoiceModule/pop_sgtz_addEdit");
        Fpsg fpsg = new Fpsg();
        if (id != null && id > 0) {
            fpsg.setEnterpriseId(this.getLoginEnterprise().getId());
            fpsg.setId(id);
            fpsg = fpsgMapper.selectByPrimaryKey(fpsg);
        }
        mv.addObject("fpsg", fpsg);
        mv.addObject("fplxList", this.getCurrentFplx());
        return mv;
    }

    /**
     * 新增或更新申购台账
     *
     * @param fpsg
     * @return
     */
    @RequestMapping("/noc/purchaseAccount/addOrUpdate")
    @ResponseBody
    public Object addOrUpdate(Fpsg fpsg) {
        Enterprise enterprise = this.getLoginEnterprise();
        Employee employee = this.getLoginEmployee();
        // 查询一级审核流
        Map<String, Object> tempMap = new HashMap<>(16);
        tempMap.put("enterpriseId", enterprise.getId());
        tempMap.put("style", "node");
        tempMap.put("parentId", "0");
        List<FpsgShl> fpsgShlLists = fpsgShlMapper.selectByParams(tempMap);
        if (fpsgShlLists.size() == 0 || fpsgShlLists == null) {
            return BaseResultData.resultError("当前企业未设置申购审核流，无法添加数据！", null);
        }
        FpsgShl fpsgShl = fpsgShlLists.get(0);
        // 设置fpsg参数
        fpsg.setEnterpriseId(enterprise.getId());
        fpsg.setDeptId(fpsgShl.getDeptId());
        fpsg.setRoleId(fpsgShl.getRoleId());
        fpsg.setShlIdNext(fpsgShl.getNextId());
        fpsg.setOperator(employee.getName());
        fpsg.setUpdateTime(new Date());
        if (fpsg.getId() != null && fpsg.getId() != 0) {
            fpsgMapper.updateByPrimaryKeySelective(fpsg);
        } else {
            fpsg.setShStatusNow(0);
            fpsg.setCreateTime(new Date());
            fpsg.setIsDel(0);
            fpsg.setStatus(CommonEnum.common_type_0.getValue());
            fpsgMapper.insert(fpsg);
        }
        return BaseResultData.resultOK(fpsg);
    }

    /**
     * 根据id删除申购台账记录
     *
     * @param id
     * @return
     */
    @RequestMapping("/noc/purchaseAccount/deletById/{id}")
    @ResponseBody
    public Object deletById(@PathVariable Long id, Fpsg key) {
        key.setId(id);
        key.setEnterpriseId(this.getLoginEnterprise().getId());
        fpsgMapper.deleteByPrimaryKey(key);
        return BaseResultData.resultOK(null);
    }

    /**
     * 根据条件导出记录
     *
     * @param params
     */
    @RequestMapping("/noc/purchaseAccount/exportExcel")
    @ResponseBody
    public void exportExcel(@RequestParam Map<String, Object> params) {
        try {
            //创建文件名称
            String fileName = "申购台账列表-" + DateUtil.getTimes() + ".xls";
            //Servlet准备 设置兼容浏览器 的OutputStream流
            ServletUtil su = new ServletUtil(fileName, this.getRequest(), this.getResponse());
            su.poiExcelServlet();
            //设置excel 标题，列的取值field
            String[] heads = {"发票类型", "发票类型代码", "申领数量（张）", "经办员", "领票方式", "操作人", "状态"};
            String[] cols = {"fplxStr", "fpdm", "slsl", "jby", "lpfsStr", "operator", "status"};
            // 数据列表
            params.put("enterpriseId", this.getLoginEnterprise().getId());
            if(params.containsKey("isOwn")){
                params = getLoginSearchParams(params);
            }

            List<Fpsg> list = fpsgMapper.selectByParams(params);

            // 导出时 修改字段显示
            list.forEach(item -> {
                if ("2".equals(item.getFplx())) {
                    item.setFplxStr("增值税普通发票");
                } else if ("0".equals(item.getFplx())) {
                    item.setFplxStr("增值税专用发票");
                } else if ("51".equals(item.getFplx())) {
                    item.setFplxStr("增值税电子普通发票");
                }

                if (0 == item.getLpfs()) {
                    item.setLpfsStr("自行领取");
                } else if (1 == item.getLpfs()) {
                    item.setLpfsStr("快递配送");
                }

                if (CommonEnum.common_type_0.getValue().equals(item.getStatus())) {
                    item.setStatus("待提交");
                } else if (CommonEnum.common_type_1.getValue().equals(item.getStatus())) {
                    item.setStatus("待审核");
                } else if (CommonEnum.common_type_2.getValue().equals(item.getStatus())) {
                    item.setStatus("审核完成");
                } else if (CommonEnum.common_type_3.getValue().equals(item.getStatus())) {
                    item.setStatus("审核拒绝");
                }
            });
            log.info("list:" + list);
            //构造excel导出类
            PoiExcelExportUtil<Fpsg> pee = new PoiExcelExportUtil(fileName, heads, cols, list, su.getOut());
            //excel导出 至浏览器
            pee.exportExcel();
        } catch (Exception e) {
            log.error("Export捕获异常！" + e);
            e.printStackTrace();
        }
    }

    /**
     * 提交审核
     *
     * @param id
     * @param key
     * @return
     */
    @PostMapping("/noc/purchaseAccount/submitReview/{id}")
    @ResponseBody
    public Object submitReview(@PathVariable Long id, Fpsg key) {
        key.setId(id);
        key.setEnterpriseId(this.getLoginEnterprise().getId());
        Fpsg fpsg = fpsgMapper.selectByPrimaryKey(key);
        if (CommonEnum.common_type_1.getValue().equals(fpsg.getStatus())) {
            return BaseResultData.resultError("该台账已被审核!", fpsg);
        }
        fpsg.setStatus(CommonEnum.common_type_1.getValue());
        fpsgMapper.updateByPrimaryKeySelective(fpsg);
        return BaseResultData.resultOK(null);
    }

    /**
     * 根据id审核台账
     *
     * @param params
     * @return
     */
    @PostMapping("/noc/purchaseAccount/reviewById")
    @ResponseBody
    public Object reviewById(InvoiceReviewParams params) {
        Enterprise enterprise = this.getLoginEnterprise();
        if (params.getId() == null || params.getId() == 0) {
            return BaseResultData.resultError("缺少必要参数!", null);
        }

        Fpsg queryFpsg = new Fpsg();
        queryFpsg.setId(params.getId());
        queryFpsg.setEnterpriseId(enterprise.getId());
        Fpsg fpsg = fpsgMapper.selectByPrimaryKey(queryFpsg);

        if (fpsg.getShStatusNow() == 1 || fpsg.getShlIdNext() == null) {
            return BaseResultData.resultError("该台账已审核完成!", null);
        }

        // 审核通过或拒绝
        if (CommonEnum.review_type_pass.getValue().equals(params.getType())) {
            return this.reviewPass(params, fpsg);
        } else if (CommonEnum.review_type_reject.getValue().equals(params.getType())) {
            return this.reviewReject(params, fpsg);
        } else {
            return BaseResultData.resultError("系统异常！", null);
        }
    }

    /**
     * 审核通过
     *
     * @param reviewParams
     * @param fpsg
     * @return
     */
    private Object reviewPass(InvoiceReviewParams reviewParams, Fpsg fpsg) {
        // 查询审核流list
        Map<String, Object> tempMap = new HashMap<>(16);
        tempMap.put("enterpriseId", fpsg.getEnterpriseId());
        tempMap.put("id", fpsg.getShlIdNext());
        List<FpsgShl> fpsgShlLists = fpsgShlMapper.selectByParams(tempMap);
        if (fpsgShlLists == null || fpsgShlLists.size() == 0) {
            fpsg.setShNameNext(null);
            fpsg.setShlIdNext(null);
            fpsg.setShStatusNow(1);
            fpsg.setStatus(CommonEnum.common_type_2.getValue());
            fpsgMapper.updateCheck(fpsg);
        } else {
            FpsgShl fpsgShl = fpsgShlLists.get(0);
            fpsg.setDeptId(fpsgShl.getDeptId());
            fpsg.setRoleId(fpsgShl.getRoleId());
            fpsg.setEmployeeId(fpsgShl.getEmployeeId());
            fpsg.setShlIdNext(fpsgShl.getNextId());
            fpsgMapper.updateCheck(fpsg);
        }
        // 添加审核日志
        FpsgShLogEntity logEntity = new FpsgShLogEntity();
        logEntity.setEnterpriseId(fpsg.getEnterpriseId());
        logEntity.setFpsgIdRef(fpsg.getId());
        logEntity.setDeptId(fpsg.getDeptId());
        logEntity.setDeptName(getNameById("dept", fpsg.getDeptId()));
        logEntity.setRoleId(fpsg.getRoleId());
        logEntity.setRoleName(getNameById("role", fpsg.getRoleId()));
        logEntity.setEmployeeId(this.getLoginEmployee().getId());
        logEntity.setEmployeeName(this.getLoginEmployee().getName());
        logEntity.setCreateTime(new Date());
        logEntity.setStatus(1);
        logEntity.setContent(StringUtils.isEmpty(reviewParams.getContent()) ? "审核通过" : reviewParams.getContent());
        fpsgShLogEntityMapper.insert(logEntity);

        return BaseResultData.resultOK(null);
    }

    /**
     * 审核拒绝
     *
     * @param reviewParams
     * @param fpsg
     * @return
     */
    public Object reviewReject(InvoiceReviewParams reviewParams, Fpsg fpsg) {
        // 修改状态数据审核拒绝
        fpsg.setShNameNext(null);
        fpsg.setShlIdNext(null);
        fpsg.setShStatusNow(null);
        fpsg.setStatus(CommonEnum.common_type_3.getValue());
        fpsgMapper.updateCheck(fpsg);
        // 添加操作日志
        FpsgShLogEntity logEntity = new FpsgShLogEntity();
        logEntity.setEnterpriseId(fpsg.getEnterpriseId());
        logEntity.setFpsgIdRef(fpsg.getId());
        logEntity.setDeptId(fpsg.getDeptId());
        logEntity.setDeptName(getNameById("dept", fpsg.getDeptId()));
        logEntity.setRoleId(fpsg.getRoleId());
        logEntity.setRoleName(getNameById("role", fpsg.getRoleId()));
        logEntity.setEmployeeId(this.getLoginEmployee().getId());
        logEntity.setEmployeeName(this.getLoginEmployee().getName());
        logEntity.setCreateTime(new Date());
        logEntity.setStatus(0);
        logEntity.setContent(reviewParams.getContent());
        fpsgShLogEntityMapper.insert(logEntity);

        return BaseResultData.resultOK(null);
    }

    /**
     * 查看审核记录
     *
     * @param fpsgId
     * @param paramMap
     * @return
     */
    @GetMapping("/noc/purchaseAccount/getReviewLogById/{id}")
    @ResponseBody
    public ModelAndView getReviewLogById(@PathVariable(value = "id") Long fpsgId, Map<String, Object> paramMap) {
        ModelAndView mv = new ModelAndView("invoiceModule/pop_sgsh_log");
        paramMap.put("enterpriseId", this.getLoginEnterprise().getId());
        paramMap.put("fpsgIdRef", fpsgId);
        List<FpsgShLogEntity> logEntityList = fpsgShLogEntityMapper.selectByParams(paramMap);
        mv.addObject("logEntityList", logEntityList);
        return mv;
    }

    /**
     * 查看申购审核列表
     *
     * @param params
     * @return
     */
    @GetMapping("/noc/purchaseReview/getList")
    @ResponseBody
    public Object getReviewList(@RequestParam Map<String, Object> params) {
        int pageNum = params.get("page") == null ? 1 : Integer.parseInt(params.get("page").toString());
        int pageSize = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(pageNum, pageSize, true);
        // 待审核为1
        params.put("status", "1");
        List<Fpsg> list = fpsgMapper.selectByParams(getLoginSearchParams(params));
        PageInfo<Fpsg> pageInfo = new PageInfo<>(list);

        LayUiPageParams<Fpsg> pageParams = LayUiPageParams.defaultResult(pageInfo.getTotal(), list);
        return pageParams;
    }

    /**
     * 审核时查看详情
     *
     * @param id
     * @return
     */
    @RequestMapping("/noc/purchaseAccount/getReviewDetail/{id}")
    public ModelAndView getReviewDetail(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("invoiceModule/pop_sgsh_detail");
        Fpsg fpsg = new Fpsg();
        if (id != null && id > 0) {
            fpsg.setEnterpriseId(this.getLoginEnterprise().getId());
            fpsg.setId(id);
            fpsg = fpsgMapper.selectByPrimaryKey(fpsg);
        }
        mv.addObject("fpsg", fpsg);
        return mv;
    }

    /**
     * 根据id获取名称
     *
     * @param type
     * @param id
     * @return
     */
    private String getNameById(String type, Long id) {
        String name = null;
        if ("dept".equals(type)) {
            Department department = departmentService.getDepartmentById(id);
            if (department != null) {
                name = department.getName();
            }
        } else if ("role".equals(type)) {
            Role role = roleService.getRoleById(id);
            if (role != null) {
                name = role.getName();
            }
        }
        return name;
    }

    /**
     * 设置查询参数
     *
     * @param tempMap
     * @return
     */
    private Map<String, Object> getLoginSearchParams(Map<String, Object> tempMap) {
        Enterprise enterprise = this.getLoginEnterprise();
        Employee employee = this.getLoginEmployee();
        tempMap.put("enterpriseId", enterprise.getId());
        tempMap.put("deptIds", commonService.getDeptsByEmp(employee.getId(), enterprise.getId()));
        tempMap.put("roleIds", commonService.getRolesByEmp(employee.getId(), enterprise.getId()));
        return tempMap;
    }
}
