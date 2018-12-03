package com.htxx.controller.dianFeiShouRu;

import com.alibaba.fastjson.JSONArray;
import com.htxx.common.BaseResultData;
import com.htxx.constent.Constent;
import com.htxx.controller.BaseController;
import com.htxx.entity.*;
import com.htxx.mapper.*;
import com.htxx.service.CommonService;
import com.htxx.service.HighPowerYkfpMngService;
import com.htxx.service.YiKaiFaPiaoService;
import com.htxx.service.ZiYouPiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: SaHongzhi
 * @Date: 2018-10-23 11:27
 * <p>
 * 自由票
 */
@Controller
public class ziYouPiaoController extends BaseController {

    @Autowired
    CommonService commonService;

    @Autowired
    HighPowerYkfpMngService highPowerYkfpMngService;

    @Autowired
    YiKaiFaPiaoService yiKaiFaPiaoService;

    @Autowired
    DlFplxMapper dlFplxMapper;

    @Autowired
    DlGfxxMapper dlGfxxMapper;

    @Autowired
    ZiYouPiaoService ziYouPiaoService;

    @Autowired
    DlSpxxMapper dlSpxxMapper;

    @Autowired
    DlFpxeMapper dlFpxeMapper;

    @Autowired
    DlYkfpDelMapper dlYkfpDelMapper;

    @Autowired
    DlYkfpMainMapper dlYkfpMainMapper;


    @RequestMapping(value = "/main/zyp/foToZiYouPiao")
    public ModelAndView foToZiYouPiao(HttpSession session) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/dianFeiShouRu/ziYouPiaoList.html");
        //开票机信息
        List<Kpjxx> kpjxxList = commonService.getCurrentKpjxx(((Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE)).getId());
        modelAndView.addObject("kpjxxList", kpjxxList);
        //该企业对应的发票类型
        List<Fplx> fplxList = commonService.getCurrentFplx(((Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE)).getId());
        modelAndView.addObject("fplxList", fplxList);
        return modelAndView;
    }

    @RequestMapping(value = "/noc/zyp/list")
    @ResponseBody
    public PageMsg<Ykfp> list(
            PageMsg<Ykfp> pageMsg, Ykfp ykfp
    ) {
        //设置业务类型 自由票

        ykfp.setYwlx(Constent.SIGN_STRING_3);
        return highPowerYkfpMngService.getList(pageMsg, ykfp);
    }

    /**
     * 已开发票按条件导出 or 已开发票checkbox批量导出
     *
     * @param request
     * @param response
     * @param pageMsg
     * @param ykfp
     */
    @RequestMapping(value = "/noc/zyp/export")
    public void export(HttpServletRequest request, HttpServletResponse response, PageMsg<Ykfp> pageMsg, Ykfp ykfp) {
        //获取单据号集合
        String djhs = request.getParameter("djhs");
        //设置业务类型 电费收入  选取所有电费收入 已开发票
        ykfp.setYwlx(Constent.SIGN_STRING_3);
        if (null == djhs) {
            //已开发票按条件导出
            yiKaiFaPiaoService.ykfpExport(request, response, pageMsg, ykfp, Constent.SIGN_INT_0);
        } else {
            //已开发票 checkbox批量导出/单条导出
            yiKaiFaPiaoService.ykfpExport(request, response, pageMsg, ykfp, Constent.SIGN_INT_1);
        }
    }

    //获取该企业的可用发票类型
    @RequestMapping(value = "/noc/zyp/getAllInvTypeByEnterprise")
    public ModelAndView getAllInvTypeByEnterprise(@RequestParam(value = "ids", required = false) String ids,
                                                  HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/dianFeiShouRu/xuanZeFaPiaoZhongLei.html");
        Employee employee = (Employee) session.getAttribute(Constent.SESSION_EMPLOYEE);
        DlFplxExample dlFplxExample = new DlFplxExample();
        DlFplxExample.Criteria criteria = dlFplxExample.createCriteria();
        criteria.andEnterpriseIdEqualTo(employee.getEnterpriseId());
        //发票类型
        modelAndView.addObject("type", dlFplxMapper.selectByExample(dlFplxExample));
        return modelAndView;
    }

    /**
     * 跳转到发票填开页
     *
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/noc/zyp/foToNewZiYouPiao")
    public ModelAndView foToNewZiYouPiao(HttpSession session, HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/dianFeiShouRu/ziYouPiaoKj.html");
        //获取企业信息
        Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
        modelAndView.addObject("defaultType", request.getParameter("invTypeId"));
        //获取当前发票类型列表（专票 普票 电子票 卷票）
        List<Fplx> fplxList = commonService.getCurrentFplx(enterprise.getId());
        fplxList.forEach(item -> {
            if (Constent.SIGN_STRING_0.equals(item.getCode())) {
                //专票
                modelAndView.addObject("zyfp", Constent.SIGN_STRING_0);
            } else if (Constent.SIGN_STRING_2.equals(item.getCode())) {
                //普票
                modelAndView.addObject("ptfp", Constent.SIGN_STRING_2);
            } else if (Constent.SIGN_STRING_51.equals(item.getCode())) {
                //电子票
                modelAndView.addObject("dzfp", Constent.SIGN_STRING_51);
            } else if (Constent.SIGN_STRING_41.equals(item.getCode())) {
                //卷票
                modelAndView.addObject("jsfp", Constent.SIGN_STRING_41);
            }
        });
        //获取开票人信息
        Employee employee = (Employee) session.getAttribute(Constent.SESSION_EMPLOYEE);
        modelAndView.addObject("operatorName", employee.getName());

        modelAndView.addObject("enterprise", enterprise);
        // 获取当前登录人对应的部门下的所有开票机
        List<Kpjxx> kpjxxes= commonService.findAllKpj(employee.getId(),enterprise.getId());
        modelAndView.addObject("kpj",kpjxxes.get(0));
        return modelAndView;
    }

    /**
     * 获取所有购方信息表
     */
    @RequestMapping(value = "/noc/zyp/findAllGfInfos")
    @ResponseBody
    public BaseResultData findAllGfInfos(HttpSession session) {
        //获取企业信息
        Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
        DlGfxx dlGfxx = new DlGfxx();
        dlGfxx.setEnterpriseId(enterprise.getId());
        List<DlGfxx> dlGfxxes = dlGfxxMapper.selectByCondition(dlGfxx);
        JSONArray jarray = new JSONArray();
        jarray.addAll(dlGfxxes);
        return BaseResultData.resultOK(jarray);
    }

    /**
     * 获取所有购方信息表
     */
    @RequestMapping(value = "/noc/zyp/findAllGoodsInfo")
    @ResponseBody
    public BaseResultData findAllGoodsInfo(HttpSession session) {

        DlSpxxExample dlSpxxExample = new DlSpxxExample();
        DlSpxxExample.Criteria criteria = dlSpxxExample.createCriteria();
        List<DlSpxx> dlSpxxes = dlSpxxMapper.selectByExample(dlSpxxExample);
        JSONArray jarray = new JSONArray();
        jarray.addAll(dlSpxxes);
        return BaseResultData.resultOK(jarray);
    }

    /**
     * 展示商品选择页面
     * @param mv
     * @return
     */
    @RequestMapping(value = "noc/zyp/selectSpxx")
    public ModelAndView selectSpxx(ModelAndView mv){
        mv.setViewName("highPower/selectSpxx");
        //高可靠电费类型
        mv.addObject("ywlx",Constent.SIGN_STRING_3);
        return mv;
    }

    /**
     * 获取开票机下的某种发票种类的最大限额
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/noc/zyp/findKpjMaxMoney")
    @ResponseBody
    public BaseResultData findKpjMaxMoney(@RequestParam(value = "fpzl") int fpzl,
                                          @RequestParam(value = "kpjId") int kpjId,
                                          HttpSession session) {

//        DlFpxeExample dlFpxeExample=new DlFpxeExample();
//        DlFpxeExample.Criteria cr=dlFpxeExample.createCriteria();
//        cr.andKpjIdEqualTo(Long.valueOf(kpjId));
//        cr.andFplxIdEqualTo(Long.valueOf(fpzl));
//        List<DlFpxe> dlFpxes=dlFpxeMapper.selectByExample(dlFpxeExample);
//        if(dlFpxes.size()<=0){
//            return BaseResultData.result("选择的发票机对应的发票种类的限额查询不到!",500,null);
//        }
        //获取企业信息
        Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
        double money = commonService.getCurrentFpxe(enterprise.getId(), kpjId, fpzl);
        return BaseResultData.resultOK(money);
    }

    /**
     * 保存单据信息到白名单表
     *
     * @param xxfp
     * @param session
     * @return
     */
    @RequestMapping(value = "/noc/zyp/saveInvTemp")
    @ResponseBody
    public BaseResultData saveInvTemp(@RequestBody xxfp xxfp, HttpSession session) {
        if (xxfp.getDlZiyoupiao().getTotalMoneyIncludeTax().compareTo(BigDecimal.valueOf(100.00)) == 1) {
            return BaseResultData.result("该张发票开具的金额大于开票机允许的最大限额！", "500", null);
        }
        //获取企业信息
        Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
        //获取登录人信息
        Employee employee = (Employee) session.getAttribute(Constent.SESSION_EMPLOYEE);
        xxfp.getDlZiyoupiao().setOperator(employee.getId());
        xxfp.getDlZiyoupiao().setEnterpriseId(enterprise.getId());
        System.out.println(xxfp.toString());
        return ziYouPiaoService.saveZiYouPiao(xxfp);
    }

    /**
     * 税收分类编码后台gbk转义
     *
     * @param xml
     * @return
     */
    @RequestMapping(value = "/noc/zyp/gbkChange")
    @ResponseBody
    public BaseResultData gbkChange(String xml) {
        return commonService.gbkChange(xml);
    }

    /**
     * 保存已开发票信息到发票表,同时更新自由票表内单据状态
     *
     * @param xxfp
     * @param session
     * @return
     */
    @RequestMapping(value = "/noc/zyp/saveYkFp")
    @ResponseBody
    public BaseResultData saveYkFp(@RequestBody xxfp xxfp, @RequestBody KpResult kpResult, HttpSession session) {
        System.out.println(xxfp.toString());
        System.out.println(kpResult.toString());
        //获取企业信息
        Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
        //获取登录人信息
        Employee employee = (Employee) session.getAttribute(Constent.SESSION_EMPLOYEE);
        xxfp.getDlZiyoupiao().setOperator(employee.getId());
        xxfp.getDlZiyoupiao().setEnterpriseId(enterprise.getId());
        System.out.println(xxfp.toString());
        return ziYouPiaoService.saveZiYouPiao(xxfp);
    }

    //获取已开发票的主、子表信息
    @RequestMapping(value = "/noc/zyp/getDjDetail")
    @ResponseBody
    public BaseResultData showTroubleDjMain(@RequestParam(value = "djh") String djh, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/dianFeiShouRu/faPiaoDetail.html");
        //查询单据主表信息信息
        DlYkfpMainExample dlYkfpMainExample = new DlYkfpMainExample();
        DlYkfpMainExample.Criteria criteria = dlYkfpMainExample.createCriteria();
        criteria.andDjhEqualTo(djh);
        DlYkfpMain dlYkfpMain = dlYkfpMainMapper.selectByExample(dlYkfpMainExample).get(0);
        //查询单据zi表信息信息
        DlYkfpDelExample dlYkfpDelExample = new DlYkfpDelExample();
        DlYkfpDelExample.Criteria criteria2 = dlYkfpDelExample.createCriteria();
        criteria2.andDjhEqualTo(djh);
        List<DlYkfpDel> dlYkfpDels = dlYkfpDelMapper.selectByExample(dlYkfpDelExample);
        for (DlYkfpDel detail : dlYkfpDels) {
            detail.setXmsl(detail.getXmsl().negate());
            detail.setXmje(detail.getXmje().negate());
            detail.setSe(detail.getSe().negate());
            detail.setHsje(detail.getHsje().negate());
            detail.setXmje(detail.getXmje().negate());
        }
        DlYkfp dlYkfp = new DlYkfp();
        dlYkfp.setDlYkfpMain(dlYkfpMain);
        dlYkfp.setDlYkfpDels(dlYkfpDels);
        return BaseResultData.resultOK(dlYkfp);
    }


//    //获取已开发票的主、子表信息
//    @RequestMapping(value = "/noc/zyp/saveFpInfo2Ykfp")
//    @ResponseBody
//    public BaseResultData saveFpInfo2Ykfp(@RequestParam(value = "main") String main ,
//                                          @RequestParam(value = "del") String del ,
//                                          HttpSession session) {
////        System.out.println(main);
////        System.out.println(JSONObject.parseObject(main,));
//        return BaseResultData.resultOK(null);
//    }

    //发票开具完成后，保存cong对应接口查询到的发票信息
    @RequestMapping(value = "/noc/zyp/saveFpInfo2Ykfp")
    @ResponseBody
    public BaseResultData saveFpInfo2Ykfp(@RequestBody QryInvResult qryInvResult, HttpSession session) {
        //commonService.dom4JToInvoiceBean("xxx");
        System.out.println(qryInvResult.toString());
        commonService.dom4JToInvoiceBean(qryInvResult, session);
        return BaseResultData.resultOK(null);
    }


}
