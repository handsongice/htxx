package com.htxx.controller.highPower;

import com.htxx.common.BaseResultData;
import com.htxx.constent.Constent;
import com.htxx.controller.BaseController;
import com.htxx.dto.ResultMap;
import com.htxx.entity.*;
import com.htxx.mapper.GfxxMapper;
import com.htxx.mapper.NoticeMapper;
import com.htxx.pojo.UpdateInvoiceStockParams;
import com.htxx.service.CommonService;
import com.htxx.service.HighPowerInvoiceMngService;
import com.htxx.service.HighPowerNoticeService;
import com.htxx.service.InvoiceStockService;
import com.htxx.util.BigDecimalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ll on 2018-10-30
 * 高可靠电费 -- 开票管理
 */
@Controller
@Slf4j
public class InvoiceMngController extends BaseController {
    @Autowired
    private HighPowerInvoiceMngService highPowerInvoiceMngService;
    @Autowired
    private HighPowerNoticeService highPowerNoticeService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private GfxxMapper gfxxMapper;
    @Autowired
    private InvoiceStockService invoiceStockService;

    /**
     * 已提交的通知单列表页面
     * @param pageMsg
     * @param notice
     * @return
     */
    @RequestMapping(value = "noc/highPower/invoiceMng/list")
    @ResponseBody
    public PageMsg<Notice> list(PageMsg<Notice> pageMsg, Notice notice) {
        //设置 已提交  状态标识
        notice.setStatus(Constent.SIGN_STRING_2);
        return highPowerNoticeService.getlist(pageMsg,notice);
    }

    /**
     * 已提交的 通知单按条件导出 or 通知单checkbox批量导出
     * @param pageMsg
     * @param notice
     */
    @RequestMapping(value = "noc/highPower/invoiceMng/export")
    @ResponseBody
    public void export(PageMsg<Notice> pageMsg, Notice notice) {
        String ids = getRequest().getParameter("ids");
        if(null == ids){
            //设置 已提交  状态标识
            notice.setStatus(Constent.SIGN_STRING_2);
            //通知单按条件导出
            highPowerNoticeService.noticesExport(getRequest(),getResponse(),pageMsg,notice, Constent.SIGN_INT_0);
        }else{
            //通知单 checkbox批量导出/单条导出
            highPowerNoticeService.noticesExport(getRequest(),getResponse(),pageMsg,notice, Constent.SIGN_INT_1);
        }
    }

    /**
     * 开票页面
     * @param mv
     * @return
     */
    @RequestMapping(value = "noc/highPower/invoiceMng/invoice")
    public ModelAndView invoice(ModelAndView mv){
        //获取工作单号
        String gzdh = getRequest().getParameter("gzdh");
        Notice notice = noticeMapper.getNoticeByGzdh(gzdh);
        notice.getNoticeDelList().get(0).setSlStr(BigDecimalUtil.decimal2percent(notice.getNoticeDelList().get(0).getSl()));
        //获取开票机列表
        List<Kpjxx> fjhs = commonService.findAllKpj(this.getLoginEmployee().getId(), this.getLoginEnterprise().getId());
        log.info("开票机信息："+fjhs);
        mv.setViewName("highPower/bgdkp-main");
        //通知单单据内容 main+detail
        mv.addObject("notice",notice);
        //开票机列表
        if(fjhs.size() == 0){
            //若开票机列表为空  则添加默认选项   以兼容ie浏览器
            Kpjxx kpjxx = new Kpjxx();
            kpjxx.setCode("");
            kpjxx.setName("无开票机选项");
            fjhs.add(kpjxx);
        }
        mv.addObject("fjhs",fjhs);
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

    /**
     * 展示商品选择页面
     * @param mv
     * @return
     */
    @RequestMapping(value = "noc/highPower/invoiceMng/selectSpxx")
    public ModelAndView selectSpxx(ModelAndView mv){
        mv.setViewName("highPower/selectSpxx");
        //高可靠电费类型
        mv.addObject("ywlx",Constent.SIGN_STRING_2);
        return mv;
    }

    /**
     * 获取商品信息列表数据
     * @param pageMsg
     * @param spxx
     * @return
     */
    @RequestMapping(value = "noc/highPower/invoiceMng/spxxList")
    @ResponseBody
    public PageMsg<Spxx> spxxList(PageMsg<Spxx> pageMsg,Spxx spxx){
        //获取业务类型 1-普通电费 2-高可靠 3-自由票
        String ywlx = getRequest().getParameter("ywlx");
        return highPowerInvoiceMngService.spxxList(pageMsg,spxx,ywlx);
    }

    /**
     * 获取购方信息下拉列表 数据
     * @return
     */
    @RequestMapping(value = "noc/highPower/invoiceMng/gfxxDropDownList")
    @ResponseBody
    public Map<String, Object> gfxxDropDownList(){
        String keywords = getRequest().getParameter("keywords");
        Map<String,Object> map = new HashMap<>();
        map.put("gfmc",keywords);
        List<Gfxx> list = gfxxMapper.selectByParams(map);
        Map<String,Object> result = new HashMap<>();
        result.put("type","success");
        result.put("code",0);
        result.put("content",list);
        return result;
    }

    /**
     * 展示购方信息选择页面
     * @param mv
     * @return
     */
    @RequestMapping(value = "noc/highPower/invoiceMng/selectGfxx")
    public ModelAndView selectGfxx(ModelAndView mv){
        mv.setViewName("highPower/selectGfxx");
        return mv;
    }

    /**
     * 获取购方信息列表数据
     * @param pageMsg
     * @param gfxx
     * @return
     */
    @RequestMapping(value = "noc/highPower/invoiceMng/gfxxList")
    @ResponseBody
    public PageMsg<Gfxx> gfxxList(PageMsg<Gfxx> pageMsg,Gfxx gfxx){
        //获取搜索框的关键字
        String keyword = getRequest().getParameter("keyword");
        return highPowerInvoiceMngService.gfxxList(pageMsg,gfxx,keyword);
    }

    /**
     * 获取当前发票限额
     * @return
     */
    @RequestMapping(value = "noc/highPower/invoiceMng/getFpxe")
    @ResponseBody
    public ResultMap getFpxe(){
        //获取分机号
        String fjh = getRequest().getParameter("fjh");
        //获取发票种类
        String fpzl = getRequest().getParameter("fpzl");
        Enterprise enterprise = (Enterprise) getSession().getAttribute(Constent.SESSION_ENTERPRISE);
        double fpxe = commonService.getCurrentFpxe(enterprise.getId(),Integer.parseInt(fjh),Integer.parseInt(fpzl));
        return ResultMap.success(fpxe);
    }

    /**
     * check 当前发票代码号码是否在当前库存中
     * @return
     */
    @RequestMapping(value = "noc/highPower/invoiceMng/checkFpdmhm")
    @ResponseBody
    public BaseResultData checkFpdmhm(){
        String fpdm = getRequest().getParameter("fpdm");
        String fphm = getRequest().getParameter("fphm");
        log.info("fpdm:"+fpdm);
        log.info("fphm:"+fphm);
        UpdateInvoiceStockParams updateInvoiceStockParams = new UpdateInvoiceStockParams();
        updateInvoiceStockParams.setFphm(fphm);
        updateInvoiceStockParams.setFpdm(fpdm);
        BaseResultData baseResultData = invoiceStockService.getInvoiceStockCheck(updateInvoiceStockParams);
        log.info(baseResultData.toString());
        return baseResultData;
    }
}
