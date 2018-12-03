package com.htxx.controller.invoicemanage;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htxx.common.BaseResultData;
import com.htxx.controller.BaseController;
import com.htxx.entity.*;
import com.htxx.enums.CommonEnum;
import com.htxx.mapper.FppdRefMapper;
import com.htxx.mapper.StatisticsAnalysisMapper;
import com.htxx.mapper.YkfpMapper;
import com.htxx.pojo.*;
import com.htxx.service.FppdService;
import com.htxx.service.HighPowerYkfpMngService;
import com.htxx.service.InvoiceStockService;
import com.htxx.service.YkfpFpjxService;
import com.htxx.util.DateUtil;
import com.htxx.util.PoiExcelExportUtil;
import com.htxx.util.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * @author: Administrator
 * @date: 2018-11-06
 * @description:
 */
@Controller
@Slf4j
public class InvoiceManageController extends BaseController {

    @Autowired
    private FppdService fppdService;

    @Autowired
    private YkfpFpjxService ykfpFpjxService;

    @Autowired
    private StatisticsAnalysisMapper statisticsAnalysisMapper;

    @Autowired
    private FppdRefMapper fppdRefMapper;

    @Autowired
    private InvoiceStockService invoiceStockService;

    @Autowired
    private HighPowerYkfpMngService highPowerYkfpMngService;

    @Autowired
    private YkfpMapper ykfpMapper;

    /**
     * 查询发票盘点列表
     *
     * @param map
     * @return
     */
    @PostMapping("/noc/invoiceMana/getFppdList")
    @ResponseBody
    public Object getFppdList(@RequestParam Map<String, Object> map) {
        // 查询当前月份该开票机下所有开票、作废、冲红的数量
        map.put("v_enterpriseId", this.getLoginEnterprise().getId());
        List<TerminalAnalysis> analysisList = statisticsAnalysisMapper.getTerminalAnalysisList(map);

        List<FppdParams> fppdParamList = new ArrayList<>();
        Map<String, Object> tempMap = new HashMap<>(16);
        analysisList.forEach(item -> {
            tempMap.clear();
            tempMap.put("enterpriseId", this.getLoginEnterprise().getId());
            tempMap.put("pdMonth", item.getYearMonth());
            tempMap.put("kpjCode", item.getFjh());
            tempMap.put("fplx", item.getFpzl());
            String sykc = invoiceStockService.selectStockCountByParams(tempMap);
            // 查询发票盘点关联表
            tempMap.clear();
            tempMap.put("enterpriseId", this.getLoginEnterprise().getId());
            tempMap.put("fplx", item.getFpzl());
            tempMap.put("kpjCode", item.getFjh());
            tempMap.put("pdMonth", item.getYearMonth());
            int refCount = fppdRefMapper.selectCountByParams(tempMap);
            FppdParams fppdParams = new FppdParams();
            fppdParams.setPdMonth(item.getYearMonth());
            fppdParams.setFplx(item.getFpzl());
            fppdParams.setFplx(item.getFpzl());
            fppdParams.setKpjName(CommonEnum.common_type_0.getValue().equals(item.getFjh()) ? "主机" : "开票机" + item.getFjh());
            fppdParams.setKpjCode(item.getFjh());
            fppdParams.setKpAmount(item.getKpzs());
            fppdParams.setZfAmount(item.getZfs());
            fppdParams.setChAmount(item.getChs());
            fppdParams.setChAmount(item.getChs());
            fppdParams.setSykc(sykc == null ? "0" : sykc);
            fppdParams.setPdbz(refCount > 0 ? "2" : "1");
            fppdParamList.add(fppdParams);
        });

        return LayUiPageParams.defaultResult((long) fppdParamList.size(), fppdParamList);
    }

    /**
     * 发票盘点按钮
     *
     * @param paramsList
     * @return
     */
    @PostMapping("/noc/invoiceMana/fppdOperate")
    @ResponseBody
    public Object fppdOperate(@RequestBody List<FppdParams> paramsList) {
        for (FppdParams fppdParams : paramsList) {
            FppdRef fppdRef = new FppdRef();
            fppdRef.setFplx(fppdParams.getFplx());
            fppdRef.setPdMonth(fppdParams.getPdMonth());
            fppdRef.setKpjCode(fppdParams.getKpjCode());

            fppdRef.setEnterpriseId(this.getLoginEnterprise().getId());
            fppdRef.setCreateId(this.getLoginEmployee().getId());
            fppdRef.setCreateName(this.getLoginEmployee().getName());
            fppdRef.setCreateTime(new Date());
            fppdRefMapper.insert(fppdRef);
        }
        return BaseResultData.resultOK(null);
    }

    /**
     * 导出发票盘点列表
     *
     * @param paramMap
     */
    @PostMapping("/noc/invoiceMana/exportFppdExcel")
    @ResponseBody
    public void exportFppdExcel(@RequestParam Map<String, Object> paramMap) {
        // 查询当前月份该开票机下所有开票、作废、冲红的数量
        paramMap.put("v_enterpriseId", this.getLoginEnterprise().getId());
        List<TerminalAnalysis> analysisList = statisticsAnalysisMapper.getTerminalAnalysisList(paramMap);

        List<FppdParams> fppdParamList = new ArrayList<>();
        Map<String, Object> tempMap = new HashMap<>(16);
        analysisList.forEach(item -> {
            tempMap.clear();
            tempMap.put("enterpriseId", this.getLoginEnterprise().getId());
            tempMap.put("pdMonth", item.getYearMonth());
            tempMap.put("kpjCode", item.getFjh());
            tempMap.put("fplx", item.getFpzl());
            String sykc = invoiceStockService.selectStockCountByParams(tempMap);
            // 查询发票盘点关联表
            tempMap.clear();
            tempMap.put("enterpriseId", this.getLoginEnterprise().getId());
            tempMap.put("fplx", item.getFpzl());
            tempMap.put("kpjCode", item.getFjh());
            tempMap.put("pdMonth", item.getYearMonth());
            int refCount = fppdRefMapper.selectCountByParams(tempMap);
            FppdParams fppdParams = new FppdParams();
            fppdParams.setPdMonth(item.getYearMonth());
            fppdParams.setFplx(item.getFpzl());
            fppdParams.setFplx(item.getFpzl());
            fppdParams.setKpjName(CommonEnum.common_type_0.getValue().equals(item.getFjh()) ? "主机" : "开票机" + item.getFjh());
            fppdParams.setKpjCode(item.getFjh());
            fppdParams.setKpAmount(item.getKpzs());
            fppdParams.setZfAmount(item.getZfs());
            fppdParams.setChAmount(item.getChs());
            fppdParams.setChAmount(item.getChs());
            fppdParams.setSykc(sykc == null ? "0" : sykc);
            fppdParams.setPdbz(refCount > 0 ? "2" : "1");
            fppdParamList.add(fppdParams);
        });
        try {
            //创建文件名称
            String fileName = "发票盘点列表-" + DateUtil.getTimes() + ".xls";
            //Servlet准备 设置兼容浏览器 的OutputStream流
            ServletUtil su = new ServletUtil(fileName, this.getRequest(), this.getResponse());
            su.poiExcelServlet();
            //设置excel 标题，列的取值field
            String[] heads = {"开票机", "发票类型", "月份", "开票数量", "作废数量", "冲红数量", "剩余库存", "状态"};
            String[] cols = {"kpjName", "fplx", "pdMonth", "kpAmount", "zfAmount", "chAmount", "sykc", "pdbz"};

            fppdParamList.forEach(item -> {
                // 发票类型（普，专，电）
                if (CommonEnum.invoice_type_2.getValue().equals(item.getFplx())) {
                    item.setFplx("增值税普通发票");
                } else if (CommonEnum.invoice_type_0.getValue().equals(item.getFplx())) {
                    item.setFplx("增值税专用发票");
                } else if (CommonEnum.invoice_type_51.getValue().equals(item.getFplx())) {
                    item.setFplx("增值税普通电子发票");
                }
                // 发票状态（正常，作废，冲红）
                if (CommonEnum.common_type_1.getValue().equals(item.getPdbz())) {
                    item.setPdbz("未盘点");
                } else if (CommonEnum.common_type_2.getValue().equals(item.getPdbz())) {
                    item.setPdbz("已盘点");
                }
            });

            //构造excel导出类
            PoiExcelExportUtil<Fpsg> pee = new PoiExcelExportUtil(fileName, heads, cols, fppdParamList, su.getOut());
            //excel导出 至浏览器
            pee.exportExcel();
        } catch (Exception e) {
            log.error("Export捕获异常！" + e);
            e.printStackTrace();
        }
    }

    /**
     * 跳转发票盘点详情
     *
     * @param kpjCode
     * @param pdMonth
     * @param fplx
     * @return
     */
    @GetMapping("/noc/invoiceMana/toInvList/{type}/{kpjCode}/{pdMonth}/{fplx}")
    public ModelAndView toInvList(@PathVariable(value = "type") String type, @PathVariable(value = "kpjCode") String kpjCode, @PathVariable(value = "pdMonth") String pdMonth, @PathVariable(value = "fplx") String fplx) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("invoiceModule/pop_inv_list");
        mv.addObject("type", type);
        mv.addObject("kpjCode", kpjCode);
        mv.addObject("pdMonth", pdMonth);
        mv.addObject("fplx", fplx);
        return mv;
    }

    /**
     * 展示发票盘点发票列表
     *
     * @param pageMsg
     * @param params
     * @return
     */
    @PostMapping("/noc/invoiceMana/getFppdInvList")
    @ResponseBody
    public Object getFppdInvList(PageMsg<Ykfp> pageMsg, FppdInvParams params) {
        Ykfp ykfp = new Ykfp();
        if ("kp".equals(params.getPdType())) {
            /*ykfp.setKplx(CommonEnum.common_type_1.getValue());*/
        } else if ("zf".equals(params.getPdType())) {
            ykfp.setKplx(CommonEnum.common_type_4.getValue());
        } else if ("ch".equals(params.getPdType())) {
            ykfp.setKplx(CommonEnum.common_type_2.getValue());
        } else if ("kc".equals(params.getPdType())) {
            Map<String, Object> map = new HashMap<>(16);
            map.put("enterpriseId", this.getLoginEnterprise().getId());
            map.put("kpjCode", params.getKpjCode());
            map.put("fplx", params.getFplx());
            map.put("gmrqYearMonth", params.getPdMonth());
            PageHelper.startPage(pageMsg.getPage(), pageMsg.getLimit(), true);
            List<InvoiceStock> invoiceStockList = (List<InvoiceStock>) invoiceStockService.selectByParams(map).getData();
            PageInfo<InvoiceStock> pageInfo = new PageInfo<>(invoiceStockList);
            return LayUiPageParams.defaultResult(pageInfo.getTotal(), invoiceStockList);
        }
        ykfp.setEnterpriseId(this.getLoginEnterprise().getId());
        ykfp.setFpzl(params.getFplx());
        ykfp.setFjh(params.getKpjCode());
        ykfp.setKpsjYearMonth(params.getPdMonth());
        return highPowerYkfpMngService.getList(pageMsg, ykfp);
    }

    /**
     * 导出发票盘点详情中发票列表和库存列表
     *
     * @param pageMsg
     * @param params
     */
    @PostMapping("/noc/invoiceMana/exportFppdInvListExcel")
    @ResponseBody
    public void exportFppdInvListExcel(PageMsg<Ykfp> pageMsg, FppdInvParams params) {
        String[] fpHeads = {"发票代码", "发票号码", "开票机", "发票类型", "购方信息", "纳税人识别号", "开票时间", "开票金额", "开票人"};
        String[] fpCols = {"fpdm", "fphm", "fjh", "fpzl", "gfmc", "gfsh", "kpsj", "jshj", "kpr"};

        String[] kcHeads = {"公司", "部门", "开票机名", "发票类型", "发票代码", "发票起始号", "发票数量", "剩余数量", "购买日期"};
        String[] kcCols = {"enterpriseName", "deptName", "kpjName", "fplx", "fpdm", "fphmq", "fpsl", "sysl", "gmrq"};

        if ("kp".equals(params.getPdType()) || "zf".equals(params.getPdType()) || "ch".equals(params.getPdType())) {
            Ykfp ykfp = new Ykfp();
            if ("kp".equals(params.getPdType())) {
            } else if ("zf".equals(params.getPdType())) {
                ykfp.setKplx(CommonEnum.common_type_4.getValue());
            } else if ("ch".equals(params.getPdType())) {
                ykfp.setKplx(CommonEnum.common_type_2.getValue());
            }
            ykfp.setEnterpriseId(this.getLoginEnterprise().getId());
            ykfp.setFpzl(params.getFplx());
            ykfp.setFjh(params.getKpjCode());
            ykfp.setKpsjYearMonth(params.getPdMonth());
            pageMsg.setEntity(ykfp);
            List<Ykfp> ykfpList = ykfpMapper.getYkfpMainList(pageMsg);
            this.exportExcel("fp", ykfpList, fpHeads, fpCols);
        } else if ("kc".equals(params.getPdType())) {
            Map<String, Object> map = new HashMap<>(16);
            map.put("enterpriseId", this.getLoginEnterprise().getId());
            map.put("kpjCode", params.getKpjCode());
            map.put("fplx", params.getFplx());
            map.put("gmrqYearMonth", params.getPdMonth());
            List<InvoiceStock> invoiceStockList = (List<InvoiceStock>) invoiceStockService.selectByParams(map).getData();
            this.exportExcel("fp", invoiceStockList, kcHeads, kcCols);
        }
    }

    private void exportExcel(String type, List dataList, String[] heads, String[] cols) {
        try {
            List excelList = new ArrayList();
            String fileName = DateUtil.getTimes() + ".xls";
            if ("fp".equals(type)) {
                List<Ykfp> fpList = (List<Ykfp>) dataList;
                fileName = "发票列表-" + fileName;
                // 导出时修改字段
                fpList.forEach(item -> {
                    // 发票类型（普，专，电）
                    if (CommonEnum.invoice_type_2.getValue().equals(item.getFpzl())) {
                        item.setFpzl("增值税普通发票");
                    } else if (CommonEnum.invoice_type_0.getValue().equals(item.getFpzl())) {
                        item.setFpzl("增值税专用发票");
                    } else if (CommonEnum.invoice_type_51.getValue().equals(item.getFpzl())) {
                        item.setFpzl("增值税普通电子发票");
                    }
                    item.setFjh(CommonEnum.common_type_0.getValue().equals(item.getFjh()) ? "主机" : "开票机" + item.getFjh());
                });
                excelList = fpList;
            } else if ("kc".equals(type)) {
                List<InvoiceStock> kcList = (List<InvoiceStock>) dataList;
                fileName = "库存列表-" + fileName;
                kcList.forEach(item -> {
                    // 发票类型（普，专，电）
                    if (CommonEnum.invoice_type_2.getValue().equals(item.getFplx())) {
                        item.setFplx("增值税普通发票");
                    } else if (CommonEnum.invoice_type_0.getValue().equals(item.getFplx())) {
                        item.setFplx("增值税专用发票");
                    } else if (CommonEnum.invoice_type_51.getValue().equals(item.getFplx())) {
                        item.setFplx("增值税普通电子发票");
                    }
                });
                excelList = kcList;
            }
            //Servlet准备 设置兼容浏览器 的OutputStream流
            ServletUtil su = new ServletUtil(fileName, this.getRequest(), this.getResponse());
            su.poiExcelServlet();
            //构造excel导出类
            PoiExcelExportUtil pee = new PoiExcelExportUtil(fileName, heads, cols, excelList, su.getOut());
            //excel导出 至浏览器
            pee.exportExcel();
        } catch (Exception e) {
            log.error("Export捕获异常！" + e);
            e.printStackTrace();
        }
    }

    /**
     * 获取发票上缴和缴销列表
     *
     * @param paramMap
     * @return
     */
    @PostMapping("/noc/invoiceMana/getFpsjList")
    @ResponseBody
    public Object getFpsjList(@RequestParam Map<String, Object> paramMap) {
        Enterprise enterprise = this.getLoginEnterprise();
        List<String> fjhList = new ArrayList<>();

        int pageNum = paramMap.get("page") == null ? 1 : Integer.parseInt(paramMap.get("page").toString());
        int pageSize = paramMap.get("limit") == null ? 10 : Integer.parseInt(paramMap.get("limit").toString());

        paramMap.put("enterpriseId", enterprise.getId());
        if (!paramMap.containsKey("fjh")) {
            for (Kpjxx kpjxx : this.getCurrentKpjxx()) {
                fjhList.add(kpjxx.getCode());
            }
        } else {
            fjhList.add(paramMap.get("fjh").toString());
        }
        paramMap.put("fjhList", fjhList);
        PageHelper.startPage(pageNum, pageSize, true);
        List<Ykfp> ykfps = ykfpFpjxService.selectListByParams(paramMap);
        PageInfo<Ykfp> pageInfo = new PageInfo<>(ykfps);
        LayUiPageParams<Ykfp> pageParams = LayUiPageParams.defaultResult(pageInfo.getTotal(), ykfps);
        return pageParams;
    }

    @PostMapping("/noc/invoiceMana/updateFpStatus")
    @ResponseBody
    public Object updateFpStatus(FpjxParams fpjxParams) {
        Employee employee = this.getLoginEmployee();
        if (StringUtils.isEmpty(fpjxParams.getFpIds())) {
            return BaseResultData.resultError("输入的参数有误！", null);
        }
        fpjxParams.setCreateId(employee.getId());
        fpjxParams.setCreateName(employee.getName());
        return ykfpFpjxService.updateFpsj(fpjxParams);
    }

    /**
     * 导出发票上缴记录
     *
     * @param params
     */
    @RequestMapping("/noc/invoice/exportFpsjExcel")
    @ResponseBody
    public void exportFpsjExcel(@RequestParam Map<String, Object> params) {
        try {
            //创建文件名称
            String fileName = "发票上缴列表-" + DateUtil.getTimes() + ".xls";
            //Servlet准备 设置兼容浏览器 的OutputStream流
            ServletUtil su = new ServletUtil(fileName, this.getRequest(), this.getResponse());
            su.poiExcelServlet();
            //设置excel 标题，列的取值field
            String[] heads = {"发票代码", "发票号码", "发票类型", "开票时间", "发票状态", "开票机", "开票金额", "状态"};
            String[] cols = {"fpdm", "fphm", "fpzl", "kpsj", "kplx", "fjh", "jshj", "sjjxbzStr"};
            // 数据列表
            params.put("enterpriseId", this.getLoginEnterprise().getId());
            List<Ykfp> ykfpFpjxes = ykfpFpjxService.selectListByParams(params);

            // 导出时修改字段
            ykfpFpjxes.forEach(item -> {
                // 发票类型（普，专，电）
                if (CommonEnum.invoice_type_2.getValue().equals(item.getFpzl())) {
                    item.setFpzl("增值税普通发票");
                } else if (CommonEnum.invoice_type_0.getValue().equals(item.getFpzl())) {
                    item.setFpzl("增值税专用发票");
                } else if (CommonEnum.invoice_type_51.getValue().equals(item.getFpzl())) {
                    item.setFpzl("增值税普通电子发票");
                }
                // 发票状态（正常，作废，冲红）
                if (CommonEnum.common_type_1.getValue().equals(item.getKplx())) {
                    item.setKplx("正常");
                } else if (CommonEnum.common_type_2.getValue().equals(item.getKplx())) {
                    item.setKplx("红票");
                } else if (CommonEnum.common_type_3.getValue().equals(item.getKplx())) {
                    item.setKplx("废票");
                } else {
                    item.setKplx("----");
                }
                // 上缴状态
                if (item.getYkfpFpjx() == null) {
                    item.setSjjxbzStr("待上缴");
                } else {
                    if (CommonEnum.common_type_0.getValue().equals(item.getYkfpFpjx().getSjbz()) && CommonEnum.common_type_0.getValue().equals(item.getYkfpFpjx().getJxbz())) {
                        item.setSjjxbzStr("待上缴");
                    } else if (CommonEnum.common_type_1.getValue().equals(item.getYkfpFpjx().getSjbz()) && CommonEnum.common_type_0.getValue().equals(item.getYkfpFpjx().getJxbz())) {
                        item.setSjjxbzStr("已上缴，待缴销");
                    } else if (CommonEnum.common_type_1.getValue().equals(item.getYkfpFpjx().getSjbz()) && CommonEnum.common_type_1.getValue().equals(item.getYkfpFpjx().getJxbz())) {
                        item.setSjjxbzStr("已上缴，已缴销");
                    }
                }
            });
            log.info("list:" + ykfpFpjxes);
            //构造excel导出类
            PoiExcelExportUtil<Fpsg> pee = new PoiExcelExportUtil(fileName, heads, cols, ykfpFpjxes, su.getOut());
            //excel导出 至浏览器
            pee.exportExcel();
        } catch (Exception e) {
            log.error("Export捕获异常！" + e);
            e.printStackTrace();
        }
    }

    /**
     * 查询发票列表
     *
     * @param pageMsg
     * @param fpcxParams
     * @return
     */
    @PostMapping("/noc/invoiceMana/getFpcxList")
    @ResponseBody
    public Object getFpcxList(PageMsg pageMsg, FpcxParams fpcxParams) {
        // 设置配置的开票机
        List<String> kpjList = new ArrayList<>();
        if (StringUtils.isEmpty(fpcxParams.getKpjCode())) {
            for (Kpjxx kpjxx : this.getCurrentKpjxx()) {
                kpjList.add(kpjxx.getCode());
            }
            fpcxParams.setKpjCodeList(kpjList);
        }
        fpcxParams.setEnterpriseId(this.getLoginEnterprise().getId());
        //传入起始页与页数大小
        PageHelper.startPage(pageMsg.getPage(), pageMsg.getLimit());
        //带条件查询
        List<YkfpDel> all = ykfpMapper.getYkfpInfoMain(fpcxParams);
        //将用户数据封装到PageInfo 中
        PageInfo<YkfpDel> page = new PageInfo(all);
        //设置成功代码
        pageMsg.setCode("0");
        //设置查询数据
        pageMsg.setData(all);
        //设置数据数量 return result;
        pageMsg.setCount(page.getTotal());
        return pageMsg;
    }

    /**
     * 导出发票查询excel
     *
     * @param fpcxParams
     */
    @PostMapping("/noc/invoiceMana/exportFpcxExcel")
    @ResponseBody
    public void exportFpcxExcel(FpcxParams fpcxParams) {
        try {
            //创建文件名称
            String fileName = "发票查询列表-" + DateUtil.getTimes() + ".xls";
            //Servlet准备 设置兼容浏览器 的OutputStream流
            ServletUtil su = new ServletUtil(fileName, this.getRequest(), this.getResponse());
            su.poiExcelServlet();
            //设置excel 标题，列的取值field
            String[] heads = {"发票类型", "发票代码", "发票号码", "开票机", "购方税号", "购方名称", "开票时间", "商品名称", "开票人", "税率", "税额", "含税合计", "所属月份", "备注"};
            String[] cols = {"fplxName", "fpdm", "fphm", "kpjName", "gfsh", "gfmc", "kpsj", "xmmc", "kpr", "sl", "se", "hsje", "ssyf", "bz"};
            // 数据列表
            // 设置配置的开票机
            List<String> kpjList = new ArrayList<>();
            if (StringUtils.isEmpty(fpcxParams.getKpjCode())) {
                for (Kpjxx kpjxx : this.getCurrentKpjxx()) {
                    kpjList.add(kpjxx.getCode());
                }
                fpcxParams.setKpjCodeList(kpjList);
            }
            fpcxParams.setEnterpriseId(this.getLoginEnterprise().getId());
            List<YkfpDel> ykfpDelList = ykfpMapper.getYkfpInfoMain(fpcxParams);

            // 导出时修改字段
            ykfpDelList.forEach(item -> {
                // 发票类型（普，专，电）
                if (CommonEnum.invoice_type_2.getValue().equals(item.getYkfp().getFpzl())) {
                    item.setFplxName("增值税普通发票");
                } else if (CommonEnum.invoice_type_0.getValue().equals(item.getYkfp().getFpzl())) {
                    item.setFplxName("增值税专用发票");
                } else if (CommonEnum.invoice_type_51.getValue().equals(item.getYkfp().getFpzl())) {
                    item.setFplxName("增值税普通电子发票");
                }

                item.setFpdm(item.getYkfp().getFpdm());
                item.setFphm(item.getYkfp().getFphm());
                item.setKpjName(CommonEnum.common_type_0.getValue().equals(item.getYkfp().getFjh()) ? "主机" : "开票机" + item.getYkfp().getFjh());
                item.setGfsh(item.getYkfp().getGfsh());
                item.setGfmc(item.getYkfp().getGfmc());
                item.setKpsj(item.getYkfp().getKpsj());
                item.setKpr(item.getYkfp().getKpr());
                item.setSsyf(item.getYkfp().getKpsj().substring(0, 7));
                item.setBz(item.getYkfp().getBz());
            });
            log.info("list:" + ykfpDelList);
            //构造excel导出类
            PoiExcelExportUtil<Fpsg> pee = new PoiExcelExportUtil(fileName, heads, cols, ykfpDelList, su.getOut());
            //excel导出 至浏览器
            pee.exportExcel();
        } catch (Exception e) {
            log.error("Export捕获异常！" + e);
            e.printStackTrace();
        }
    }

}
