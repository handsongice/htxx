package com.htxx.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htxx.constent.Constent;
import com.htxx.entity.*;
import com.htxx.pojo.LayUiPageParams;
import com.htxx.service.ActionService;
import com.htxx.service.CommonService;
import com.htxx.service.MessageService;
import com.htxx.util.PoiExcelExportUtil;
import com.htxx.util.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: SaHongzhi
 * @Date: 2018-10-18 15:14
 */
@Controller
@Slf4j
public class PageController extends BaseController{
    @Autowired
    private ActionService actionService;

    @Autowired
    private CommonService commonService;

    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/main")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        Employee employee = getLoginEmployee();
        if(null == employee || StringUtils.isEmpty(employee.getLoginCode())) {
            return mv;
        }
        try {
            List<Action> actions = actionService.leftMenus(employee);
            mv.addObject("actions", actions);
            return mv;
        } catch (Exception e) {
            e.printStackTrace();
            return mv;
        }
    }
    /**
     * 高可靠 -- 通知单页面
     * @return
     */
    @RequestMapping(value = "/main/highPower/notice/default")
    public ModelAndView notice(ModelAndView mv) {
        mv.setViewName("highPower/noticeManagement");
        return mv;
    }
    /**
     * 高可靠 -- 开票管理页面
     * @return
     */
    @RequestMapping(value = "/main/highPower/invoiceMng/default")
    public ModelAndView invoice(ModelAndView mv) {
        mv.setViewName("highPower/invoiceMng");
        return mv;
    }
    /**
     * 高可靠 -- 已开发票页面
     * @return
     */
    @RequestMapping(value = "/main/highPower/ykfpMng/default")
    public ModelAndView ykfp(ModelAndView mv) {
        //获取当前开票机信息列表
        List<Kpjxx> kpjxxList = commonService.findAllKpj(getLoginEmployee().getId(),getLoginEnterprise().getId());
        //获取当前发票类型列表（专票 普票 电子票 卷票）
        List<Fplx> fplxList = commonService.getCurrentFplx(getLoginEnterprise().getId());
        mv.setViewName("highPower/ykfpMng");
        mv.addObject("kpjxxList",kpjxxList);
        mv.addObject("fplxList",fplxList);
        return mv;
    }

    /**
     * 统计分析 - 发票使用分析
     * @param mv
     * @return
     */
    @RequestMapping(value = "/main/statistics/invoiceAnalysis/default")
    public ModelAndView invoiceAnalysis(ModelAndView mv){
        //获取当前开票机信息列表
        List<Kpjxx> kpjxxList = commonService.findAllKpj(getLoginEmployee().getId(),getLoginEnterprise().getId());
        //获取当前发票类型列表（专票 普票 电子票 卷票）
        List<Fplx> fplxList = commonService.getCurrentFplx(getLoginEnterprise().getId());
        mv.setViewName("statistics/invoiceAnalysis");
        mv.addObject("kpjxxList",kpjxxList);
        mv.addObject("fplxList",fplxList);
        return mv;
    }

    /**
     * 统计分析 - 开票机发票使用情况
     * @param mv
     * @return
     */
    @RequestMapping(value = "/main/statistics/terminalInvoiceAnalysis/default")
    public ModelAndView terminalInvoiceAnalysis(ModelAndView mv){
        //获取当前开票机信息列表
        List<Kpjxx> kpjxxList = commonService.findAllKpj(getLoginEmployee().getId(),getLoginEnterprise().getId());
        //获取当前发票类型列表（专票 普票 电子票 卷票）
        List<Fplx> fplxList = commonService.getCurrentFplx(getLoginEnterprise().getId());
        mv.setViewName("statistics/terminalInvoiceAnalysis");
        mv.addObject("kpjxxList",kpjxxList);
        mv.addObject("fplxList",fplxList);
        return mv;
    }

    /**
     * 统计分析 - 月度统计
     * @param mv
     * @return
     */
    @RequestMapping(value = "/main/statistics/monthStatistics/default")
    public ModelAndView monthStatistics(ModelAndView mv){
        mv.setViewName("statistics/monthStatistics");
        //获取操作员对应的企业信息
        Enterprise enterprise = getLoginEnterprise();
        //获取当前发票类型列表（专票 普票 电子票 卷票）
        List<Fplx> fplxList = commonService.getCurrentFplx(enterprise.getId());
        //默认票种
        mv.addObject("defaultType",fplxList.get(0).getCode()+"");
        fplxList.forEach(item ->{
            if(Constent.SIGN_STRING_0 .equals(item.getCode())){
                //专票
                mv.addObject("zyfp",Constent.SIGN_STRING_0);
            }else if(Constent.SIGN_STRING_2 .equals(item.getCode())){
                //普票
                mv.addObject("ptfp",Constent.SIGN_STRING_2);
            }else if(Constent.SIGN_STRING_51 .equals(item.getCode())){
                //电子票
                mv.addObject("dzfp",Constent.SIGN_STRING_51);
            }else if(Constent.SIGN_STRING_41 .equals(item.getCode())){
                //卷票
                mv.addObject("jsfp",Constent.SIGN_STRING_41);
            }
        });
        return mv;
    }

    @RequestMapping(value = "/home")
    public ModelAndView home(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home");
        HttpSession session = request.getSession();
        Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
        Map<String, Object> params = new HashMap<>();
        PageHelper.startPage(1, 10, true);
        params.put("enterprise_id",enterprise.getId());
        try {
            List<Message> messages = messageService.getMessageList(params);
            PageInfo<Message> pageInfo = new PageInfo<>(messages);
            LayUiPageParams<Message> pageParams = LayUiPageParams.defaultResult(pageInfo.getTotal(), messages);
            mv.addObject("messages", pageParams.getData());
        } catch (Exception e) {
            e.printStackTrace();
            return mv;
        }
        return mv;
    }

    @RequestMapping(value = "/form")
    public String form() {
        return "form";
    }

    @RequestMapping(value = "/form2")
    public String form2() {
        return "form2";
    }

    @RequestMapping(value = "/table")
    public String table() {
        return "table";
    }

    @RequestMapping(value = "popup-form")
    public String  popupform(ModelAndView mv, PathVariable data) {
        mv.addObject("data",data);
        return "popup-form";
    }

    @RequestMapping(value = "echarts")
    public String  echarts() {
        return "echarts";
    }

    @RequestMapping(value = "/data-abstract")
    public String data_abstract() {
        return "data-abstract";
    }

    @RequestMapping(value = "/loading")
    public String loading() {
        return "loading";
    }

//    @RequestMapping(value = "error")
//    public String  error() {
//        return "error";
//    }

}
