package com.htxx.controller.invoicemanage;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htxx.common.BaseResultData;
import com.htxx.controller.BaseController;
import com.htxx.entity.*;
import com.htxx.enums.CommonEnum;
import com.htxx.pojo.DistributeParams;
import com.htxx.pojo.LayUiPageParams;
import com.htxx.pojo.UpdateInvoiceStockParams;
import com.htxx.service.*;
import com.htxx.util.DateUtil;
import com.htxx.util.KpServerUtil;
import com.htxx.util.PoiExcelExportUtil;
import com.htxx.util.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 库存管理controller
 *
 * @author zhanghongshun
 * @date 2018/11/6
 */
@Slf4j
@Controller
public class StoreDistributeController extends BaseController {

    @Autowired
    private CommonService commonService;

    @Autowired
    private InvoiceStockService invoiceStockService;

    @Autowired
    private InvoiceStockLogService invoiceStockLogService;

    @Autowired
    private InvoiceDistributeLogService invoiceDistributeLogService;

    @Autowired
    private KpjxxService kpjxxService;

    @Autowired
    private DepartmentEmployeeService departmentEmployeeService;

    /**
     * 获取入库列表展示
     *
     * @param paramMap
     * @return
     * @throws Exception
     */
    @PostMapping("/noc/invoice/getStoreList")
    @ResponseBody
    public Object getStoreList(@RequestParam Map<String, Object> paramMap) throws Exception {
        Enterprise enterprise = this.getLoginEnterprise();
        Employee employee = this.getLoginEmployee();

        List<Kpjxx> kpjxxes = commonService.findAllKpj(employee.getId(), enterprise.getId());
        if (kpjxxes == null || kpjxxes.size() == 0) {
            return BaseResultData.resultError("当前用户没有配置开票机！", null);
        }
        List<Long> kpjIds = new ArrayList<>();
        for (Kpjxx kpjxx : kpjxxes) {
            kpjIds.add(kpjxx.getId());
        }
        if (!paramMap.containsKey("kpjId")) {
            paramMap.put("kpjIds", kpjIds);
        }
        paramMap.put("enterpriseId", enterprise.getId());
        paramMap.put("isReturn", "1");
        int pageNum = paramMap.get("page") == null ? 1 : Integer.parseInt(paramMap.get("page").toString());
        int pageSize = paramMap.get("limit") == null ? 10 : Integer.parseInt(paramMap.get("limit").toString());
        PageHelper.startPage(pageNum, pageSize, true);
        List<InvoiceStock> invoiceStockList = (List<InvoiceStock>) invoiceStockService.selectByParams(paramMap).getData();
        PageInfo<InvoiceStock> pageInfo = new PageInfo<>(invoiceStockList);
        return LayUiPageParams.defaultResult(pageInfo.getTotal(), invoiceStockList);
    }

    /**
     * 确认入库操作
     *
     * @return
     */
    @PostMapping("/noc/invoice/confirmStore/{stockId}")
    @ResponseBody
    public Object confirmStore(@PathVariable Long stockId) {
        InvoiceStock stock = (InvoiceStock) invoiceStockService.getByPk(stockId).getData();
        if (stock == null) {
            return BaseResultData.resultError("入库信息不存在！", null);
        }
        stock.setQrbz(2);
        invoiceStockService.updateByParams(stock, "rk");
        return BaseResultData.resultOK(null);
    }

    /**
     * 入库操作
     *
     * @return
     */
    @PostMapping("/noc/invoice/addOrUpdateStore")
    @ResponseBody
    public Object addOrUpdateStore() {
        Enterprise enterprise = this.getLoginEnterprise();
        Employee employee = this.getLoginEmployee();
        BaseResultData baseResultData = BaseResultData.resultOK(null);
        Map<String, Object> tempMap = new HashMap<>(16);
        Kpjxx tempKpjxx = new Kpjxx();

        List<Kpjxx> kpjxxes = commonService.findAllKpj(this.getLoginEmployee().getId(), this.getLoginEnterprise().getId());
        if (kpjxxes == null || kpjxxes.size() == 0) {
            return BaseResultData.resultError("当前用户没有配置开票机！", null);
        }
        // 遍历开票机信息
        for (Kpjxx kpjxx : kpjxxes) {
            if (CommonEnum.common_type_0.getValue().equals(kpjxx.getCode())) {
                tempKpjxx = kpjxx;
            }
        }
        if (tempKpjxx == null) {
            return BaseResultData.resultError("当前用户没有配置开票主机，无法入库！", null);
        }
        // 若当前部门有0号机，则从开票服务器获取数据并插入或更新数据库
        if (tempKpjxx != null && CommonEnum.common_type_0.getValue().equals(tempKpjxx.getCode())) {
            // 发送请求给开票服务器
            /*String reqStr = HttpUtil.doPost(KpServerUtil.queryStockAddr, KpServerUtil.queryStockReqXml(CommonEnum.common_type_0.getValue()), HttpUtil.TEXT_XML);
            if(StringUtils.isEmpty(reqStr) || StringUtils.isBlank(reqStr)){
                pageParams.setMsg("查询开票服务器失败!");
                return pageParams;
            }
            baseResultData = KpServerUtil.queryStockRepXml(reqStr);*/
            baseResultData = KpServerUtil.queryStockRepXml(KpServerUtil.reqXml);
            if (!CommonEnum.msg_code_200.getValue().equals(baseResultData.getStatus())) {
                return BaseResultData.resultError(baseResultData.getMsg(), null);
            }
            List<InvoiceStock> stocks = (List<InvoiceStock>) baseResultData.getData();
            for (InvoiceStock stock : stocks) {
                tempMap.clear();
                tempMap.put("enterpriseId", enterprise.getId());
                tempMap.put("fplx", stock.getFplx());
                tempMap.put("fpjxh", stock.getFpjxh());
                List<InvoiceStock> tempStocks = (List<InvoiceStock>) invoiceStockService.selectByParams(tempMap).getData();
                if (tempStocks == null || tempStocks.size() == 0) {
                    stock.setEnterpriseId(enterprise.getId());
                    stock.setEnterpriseName(enterprise.getName());
                    stock.setDeptId(tempKpjxx.getDeptId());
                    stock.setDeptName(tempKpjxx.getDeptName());
                    stock.setKpjId(tempKpjxx.getId());
                    stock.setKpjCode(tempKpjxx.getCode());
                    stock.setKpjName(tempKpjxx.getName());
                    stock.setStatus(0);
                    stock.setCreateId(employee.getId());
                    stock.setCreateName(employee.getName());
                    invoiceStockService.insert(stock, false);
                } else {
                    // 若确认标志为1，则更新改数据
                    InvoiceStock invoiceStock = tempStocks.get(0);
                    if (invoiceStock.getQrbz().intValue() == 1) {
                        invoiceStock.setEnterpriseId(enterprise.getId());
                        invoiceStock.setEnterpriseName(enterprise.getName());
                        invoiceStock.setDeptId(tempKpjxx.getDeptId());
                        invoiceStock.setDeptName(tempKpjxx.getDeptName());
                        invoiceStock.setKpjId(tempKpjxx.getId());
                        invoiceStock.setKpjCode(tempKpjxx.getCode());
                        invoiceStock.setKpjName(tempKpjxx.getName());
                        invoiceStock.setCreateId(employee.getId());
                        invoiceStock.setCreateName(employee.getName());
                        invoiceStockService.updateByParams(invoiceStock, "rk");
                    }
                }
            }
        }
        return BaseResultData.resultOK(null);
    }

    /**
     * 跳转入库记录页面
     *
     * @param stockId
     * @return
     */
    @GetMapping("/noc/invoice/toStockLogPage/{stockId}")
    public ModelAndView toStoreLogPage(@PathVariable(value = "stockId") Long stockId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("invoiceModule/pop_kcgl_store");
        mv.addObject("stockId", stockId);
        return mv;
    }

    /**
     * 查询入库记录
     *
     * @param map
     * @return
     */
    @PostMapping("/noc/invoice/getStockLog")
    @ResponseBody
    public Object getStoreLog(@RequestParam Map<String, Object> map) {
        int pageNum = map.get("page") == null ? 1 : Integer.parseInt(map.get("page").toString());
        int pageSize = map.get("limit") == null ? 10 : Integer.parseInt(map.get("limit").toString());
        InvoiceStock stock = (InvoiceStock) invoiceStockService.getByPk((long) Integer.valueOf(map.get("stockRefId").toString())).getData();
        PageHelper.startPage(pageNum, pageSize, true);
        map.put("enterpriseId", this.getLoginEnterprise().getId());
        map.put("fplx", stock.getFplx());
        map.put("type", "rk");
        List<InvoiceStockLog> logList = invoiceStockLogService.selectByParams(map);
        PageInfo<InvoiceStockLog> pageInfo = new PageInfo<>(logList);
        LayUiPageParams<Fpsg> pageParams = LayUiPageParams.defaultResult(pageInfo.getTotal(), logList);
        return pageParams;
    }

    /**
     * 导出入库记录
     *
     * @param map
     */
    @PostMapping("/noc/invoice/exportStoreLog")
    @ResponseBody
    public void exportStoreLog(@RequestParam Map<String, Object> map) {
        try {
            //创建文件名称
            String fileName = "入库记录-" + DateUtil.getTimes() + ".xls";
            //Servlet准备 设置兼容浏览器 的OutputStream流
            ServletUtil su = new ServletUtil(fileName, this.getRequest(), this.getResponse());
            su.poiExcelServlet();
            //设置excel 标题，列的取值field
            String[] heads = {"发票代码", "发票号码（起始）", "发票张数", "剩余张数", "入库时间", "操作人"};
            String[] cols = {"fpdm", "fphmq", "fpsl", "sysl", "cjsj", "cjr"};

            InvoiceStock stock = (InvoiceStock) invoiceStockService.getByPk((long) Integer.valueOf(map.get("stockRefId").toString())).getData();
            map.put("enterpriseId", this.getLoginEnterprise().getId());
            map.put("fplx", stock.getFplx());
            // 数据列表
            List<InvoiceStockLog> list = invoiceStockLogService.selectByParams(map);
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
     * 获取待分发的库存列表展示
     *
     * @param stockId
     * @return
     * @throws Exception
     */
    @GetMapping("/noc/invoice/getStoreListForFf/{stockId}")
    @ResponseBody
    public Object getStoreListForFf(@PathVariable Long stockId) throws Exception {
        Enterprise enterprise = this.getLoginEnterprise();
        Map<String, Object> paramMap = new HashMap<>(16);
        paramMap.put("enterpriseId", enterprise.getId());
        paramMap.put("id", stockId);
        List<InvoiceStock> invoiceStocks = (List<InvoiceStock>) invoiceStockService.selectByParams(paramMap).getData();
        LayUiPageParams<Fpsg> pageParams = LayUiPageParams.defaultResult((long) invoiceStocks.size(), invoiceStocks);
        return pageParams;
    }

    /**
     * 跳转分发页面
     *
     * @param id
     * @return
     */
    @RequestMapping("/noc/invoice/toDistributePage/{fplx}/{id}")
    public ModelAndView getDistributeDetailById(@PathVariable(value = "fplx") String fplx, @PathVariable(value = "id") Long id) {
        ModelAndView mv = new ModelAndView("/invoiceModule/pop_kcgl_distribute");
        mv.addObject("fplx", fplx);
        mv.addObject("stockId", id);
        return mv;
    }

    /**
     * 历史分发列表
     *
     * @param paramMap
     * @return
     */
    @PostMapping("/noc/invoice/getDistributeLogList")
    @ResponseBody
    public Object getDistributeLogList(@RequestParam Map<String, Object> paramMap) {
        Enterprise enterprise = this.getLoginEnterprise();

        int pageNum = paramMap.get("page") == null ? 1 : Integer.parseInt(paramMap.get("page").toString());
        int pageSize = paramMap.get("limit") == null ? 10 : Integer.parseInt(paramMap.get("limit").toString());

        PageHelper.startPage(pageNum, pageSize, true);
        paramMap.put("enterpriseId", enterprise.getId());
        paramMap.put("statuses", "1,2");
        List<InvoiceStock> invoiceStocks = (List<InvoiceStock>) invoiceStockService.selectByParams(paramMap).getData();

        PageInfo<InvoiceStock> pageInfo = new PageInfo<>(invoiceStocks);
        LayUiPageParams<InvoiceStock> pageParams = LayUiPageParams.defaultResult(pageInfo.getTotal(), invoiceStocks);
        return pageParams;
    }

    /**
     * 编辑分发内容
     *
     * @param fplx
     * @param id
     * @return
     */
    @RequestMapping("/noc/invoice/toDistributeOperatePage/{fplx}/{id}")
    public ModelAndView getDistributeInputById(@PathVariable(value = "fplx") String fplx, @PathVariable(value = "id") Long id) {
        ModelAndView mv = new ModelAndView("/invoiceModule/pop_kcgl_distribute_detail");
        mv.addObject("fplx", fplx);
        mv.addObject("stockId", id);
        mv.addObject("kpjList", this.getCurrentKpjxx());

        InvoiceStock stock = (InvoiceStock) invoiceStockService.getByPk(id).getData();
        mv.addObject("stock", stock);
        return mv;
    }

    /**
     * 分发操作
     *
     * @param params
     * @return
     */
    @PostMapping("/noc/invoice/addDistribute")
    @ResponseBody
    public Object addDistribute(DistributeParams params) {
        if (params.getRkId() == null || params.getRkId() == 0) {
            return BaseResultData.resultError("缺少关键参数！", null);
        }
        Employee employee = this.getLoginEmployee();
        params.setCreateId(employee.getId());
        params.setCreateName(employee.getName());
        return invoiceStockService.saveDistributeInvoice(params);
    }

    /**
     * 退回操作
     *
     * @param stockId
     * @return
     */
    @PostMapping("/noc/invoice/returnDistribute/{stockId}")
    @ResponseBody
    public Object returnDistribute(@PathVariable(value = "stockId") Long stockId) {
        if (stockId == null || stockId == 0) {
            return BaseResultData.resultError("缺少关键参数！", null);
        }

        Employee employee = this.getLoginEmployee();
        DistributeParams distributeParams = new DistributeParams();
        distributeParams.setRkId(stockId);
        distributeParams.setCreateId(employee.getId());
        distributeParams.setCreateName(employee.getName());

        return invoiceStockService.saveReturnInvoice(distributeParams);
    }

    /**
     * 查询库存统计数据
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    @PostMapping("/noc/invoice/getInvoiceStockCount")
    @ResponseBody
    public Object getInvoiceStockCount(@RequestParam String beginDate, @RequestParam String endDate, @RequestParam String fplx) {
        return invoiceStockService.selectStockCount(beginDate, endDate, fplx);
    }

    /**
     * 开票前查询待开数据是否在数据库中
     *
     * @param params
     * @return
     */
    @PostMapping("/noc/invoice/getInvoiceStockCheck")
    @ResponseBody
    public Object getInvoiceStockCheck(UpdateInvoiceStockParams params) {
        /*params.setCreateId(this.getLoginEmployee().getId());
        params.setCreateName(this.getLoginEmployee().getName());
        params.setEnterpriseId(this.getLoginEnterprise().getId());

        params.setFpdm("124565474123");
        params.setFphm("12145864");*/
        return invoiceStockService.getInvoiceStockCheck(params);
    }

    /**
     * 发票开具后更新库存
     *
     * @param params
     * @return
     */
    @PostMapping("/noc/invoice/updateInvoiceStock")
    @ResponseBody
    public Object updateInvoiceStock(UpdateInvoiceStockParams params) {
        return invoiceStockService.updateInvoiceStock(params);
    }

    /**
     * 导出分发记录
     *
     * @param params
     */
    @RequestMapping("/noc/invoice/exportFfLogExcel")
    @ResponseBody
    public void exportFfLogExcel(@RequestParam Map<String, Object> params) {
        try {
            //创建文件名称
            String fileName = "历史分发列表-" + DateUtil.getTimes() + ".xls";
            //Servlet准备 设置兼容浏览器 的OutputStream流
            ServletUtil su = new ServletUtil(fileName, this.getRequest(), this.getResponse());
            su.poiExcelServlet();
            //设置excel 标题，列的取值field
            String[] heads = {"开票机", "发票类型", "发票代码", "发票号码（起）", "发票号码（止）", "分发份数", "剩余份数", "分发时间", "状态"};
            String[] cols = {"kpjName", "fplxName", "fpdm", "fphmq", "fphmz", "ffsl", "sysl", "createTimeStr", "statusStr"};
            // 数据列表
            params.put("enterpriseId", this.getLoginEnterprise().getId());
            List<FfLogEntity> ffEntities = invoiceDistributeLogService.selectByParams(params);

            // 导出时修改字段
            ffEntities.forEach(item -> {
                if ("2".equals(item.getFplx())) {
                    item.setFplxName("增值税普通发票");
                } else if ("0".equals(item.getFplx())) {
                    item.setFplxName("增值税专用发票");
                } else if ("51".equals(item.getFplx())) {
                    item.setFplxName("增值税普通电子发票");
                }

                if (1 == item.getStatus().intValue()) {
                    item.setStatusStr("已分发");
                } else if (2 == item.getStatus().intValue()) {
                    item.setStatusStr("已退回");
                }
            });
            log.info("list:" + ffEntities);
            //构造excel导出类
            PoiExcelExportUtil<Fpsg> pee = new PoiExcelExportUtil(fileName, heads, cols, ffEntities, su.getOut());
            //excel导出 至浏览器
            pee.exportExcel();
        } catch (Exception e) {
            log.error("Export捕获异常！" + e);
            e.printStackTrace();
        }
    }
}
