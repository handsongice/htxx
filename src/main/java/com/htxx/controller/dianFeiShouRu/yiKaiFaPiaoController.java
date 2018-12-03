package com.htxx.controller.dianFeiShouRu;

import com.htxx.constent.Constent;
import com.htxx.entity.*;
import com.htxx.mapper.*;
import com.htxx.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: SaHongzhi
 * @Date: 2018-10-23 11:27
 */

@Controller
public class yiKaiFaPiaoController {

    @Autowired
    DlFplxMapper dlFplxMapper;

    @Autowired
    YkfpMapper ykfpMapper;

    @Autowired
    KaiPiaoGuanLiService kaiPiaoGuanLiService;


    @Autowired
    DlDianfeiMapper dianfeiMapper;

    @Autowired
    DlCompanyInfoMapper dlCompanyInfoMapper;

    @Autowired
    WenTiDanJuGuanLiService wenTiDanJuGuanLiService;

    @Autowired
    DlDianfeiDetailMapper dlDianfeiDetailMapper;

    @Autowired
    CommonService commonService;

    @Autowired
    HighPowerYkfpMngService highPowerYkfpMngService;

    @Autowired
    YiKaiFaPiaoService yiKaiFaPiaoService;

    @Autowired
    DlYkfpDelMapper dlYkfpDelMapper;

    @Autowired
    DlYkfpMainMapper dlYkfpMainMapper;

    @Autowired
    EnterpriseMapper enterpriseMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @RequestMapping(value = "/main/ykfp/foToYiKaiFaPiao")
    public ModelAndView foToDanJuDaoRu(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/dianFeiShouRu/yiKaiFaPiao.html");
        //开票机信息
        List<Kpjxx> kpjxxList = commonService.getCurrentKpjxx(((Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE)).getId());
        modelAndView.addObject("kpjxxList", kpjxxList);
        //该企业对应的发票类型
        List<Fplx> fplxList = commonService.getCurrentFplx(((Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE)).getId());
        modelAndView.addObject("fplxList", fplxList);
        return modelAndView;
    }

    @RequestMapping(value = "/noc/ykfp/list")
    @ResponseBody
    public PageMsg<Ykfp> list(
//                                @RequestParam(value = "fpdm",required = false) String fpdm ,
//                              @RequestParam(value = "fphm",required = false) String fphm ,
//                              @RequestParam(value = "gfmc",required = false) String gfmc ,
//                              @RequestParam(value = "gfsh",required = false) String gfsh ,
//                              @RequestParam(value = "bz",required = false) String bz ,
//                              @RequestParam(value = "fjh",required = false) String fjh ,
//                              @RequestParam(value = "fpzl",required = false) String fpzl ,
//                              @RequestParam(value = "kplx",required = false) String kplx ,
//                              @RequestParam(value = "jshjMin",required = false) String jshjMin ,
//                              @RequestParam(value = "jshjMax",required = false) String jshjMax ,
//                              @RequestParam(value = "kpsjMin",required = false) String kpsjMin ,
//                              @RequestParam(value = "kpsjMax",required = false) String kpsjMax
            PageMsg<Ykfp> pageMsg, Ykfp ykfp
    ) {
        //设置业务类型 电费收入  选取所有电费收入 已开发票

        ykfp.setYwlx(Constent.SIGN_STRING_1);
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
    @RequestMapping(value = "/noc/ykfp/export")
    public void export(HttpServletRequest request, HttpServletResponse response, PageMsg<Ykfp> pageMsg, Ykfp ykfp) {
        //获取单据号集合
        String djhs = request.getParameter("djhs");
        //设置业务类型 电费收入  选取所有电费收入 已开发票
        ykfp.setYwlx(Constent.SIGN_STRING_1);
        if (null == djhs) {
            //已开发票按条件导出
            yiKaiFaPiaoService.ykfpExport(request, response, pageMsg, ykfp, Constent.SIGN_INT_0);
        } else {
            //已开发票 checkbox批量导出/单条导出
            yiKaiFaPiaoService.ykfpExport(request, response, pageMsg, ykfp, Constent.SIGN_INT_1);
        }
    }

    //查询发票详细信息并跳转到详情页
    @RequestMapping(value = "/noc/ykfp/showDjDetail")
    public ModelAndView showTroubleDjMain(@RequestParam(value = "djh") String djh , HttpSession session) {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/dianFeiShouRu/faPiaoDetail.html");
        //查询单据主表信息信息

        DlYkfpMainPage dlYkfpMainPage= dlYkfpMainMapper.getDlYkfpMainPage(djh);
        //查询企业名称
        if(StringUtils.isBlank(dlYkfpMainPage.getEnterpriseName())){
            dlYkfpMainPage.setEnterpriseName("未知");
        }else{
            Enterprise enterprise=enterpriseMapper.selectByPrimaryKey(Long.valueOf(dlYkfpMainPage.getEnterpriseName()));
            dlYkfpMainPage.setEnterpriseName(enterprise.getName());
        }

        //查询操作人信息
        if(StringUtils.isBlank(dlYkfpMainPage.getEmployeeName())){
            dlYkfpMainPage.setEmployeeName("未知");
        }else{
            Employee employee=employeeMapper.selectByPrimaryKey(Long.valueOf(dlYkfpMainPage.getEmployeeName()));
            dlYkfpMainPage.setEmployeeName(employee.getName());
        }
        //查询单据zi表信息信息
        DlYkfpDelExample dlYkfpDelExample=new DlYkfpDelExample();
        DlYkfpDelExample.Criteria criteria2=dlYkfpDelExample.createCriteria();
        criteria2.andDjhEqualTo(djh);
        List<DlYkfpDel> dlYkfpDels= dlYkfpDelMapper.selectByExample(dlYkfpDelExample);

        modelAndView.addObject("dlYkfpMain",dlYkfpMainPage);
        modelAndView.addObject("dlYkfpDels",dlYkfpDels);
        return modelAndView;
    }

//    /**
//     * //查询发票详细信息并跳转到详情页
//     *
//     * @param mv
//     * @return
//     */
//    @RequestMapping(value = "/noc/ykfp/showDjDetail")
//    public ModelAndView showDjDetail(ModelAndView mv, HttpSession session, HttpServletRequest request) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("/dianFeiShouRu/ziYouPiaoKj.html");
//        //获取企业信息
//        Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
//        modelAndView.addObject("defaultType", request.getParameter("invTypeId"));
//        //获取当前发票类型列表（专票 普票 电子票 卷票）
//        List<Fplx> fplxList = commonService.getCurrentFplx(enterprise.getId());
//        fplxList.forEach(item -> {
//            if (Constent.SIGN_STRING_0.equals(item.getCode())) {
//                //专票
//                modelAndView.addObject("zyfp", Constent.SIGN_STRING_0);
//            } else if (Constent.SIGN_STRING_2.equals(item.getCode())) {
//                //普票
//                modelAndView.addObject("ptfp", Constent.SIGN_STRING_2);
//            } else if (Constent.SIGN_STRING_51.equals(item.getCode())) {
//                //电子票
//                modelAndView.addObject("dzfp", Constent.SIGN_STRING_51);
//            } else if (Constent.SIGN_STRING_41.equals(item.getCode())) {
//                //卷票
//                modelAndView.addObject("jsfp", Constent.SIGN_STRING_41);
//            }
//        });
//        //获取开票人信息
//        Employee employee = (Employee) session.getAttribute(Constent.SESSION_EMPLOYEE);
//        modelAndView.addObject("operatorName", employee.getName());
//        modelAndView.addObject("enterprise", enterprise);
//        // 获取当前登录人对应的部门下的所有开票机
//        List<Kpjxx> kpjxxes = commonService.findAllKpj(employee.getId(), enterprise.getId());
//        modelAndView.addObject("kpj", kpjxxes.get(0));
//        String djh = request.getParameter("djh");
//        //查询单据主表信息信息
//        DlYkfpMainPage dlYkfpMainPage = dlYkfpMainMapper.getDlYkfpMainPage(djh);
//        Ykfp ykfp = new Ykfp();
//        //查询单据zi表信息信息
//        DlYkfpDelExample dlYkfpDelExample = new DlYkfpDelExample();
//        DlYkfpDelExample.Criteria criteria2 = dlYkfpDelExample.createCriteria();
//        criteria2.andDjhEqualTo(djh);
//        List<DlYkfpDel> dlYkfpDels = dlYkfpDelMapper.selectByExample(dlYkfpDelExample);
//        //将 dlYkfpMainPage
//        modelAndView.addObject("ykfp", ykfp);
//        return mv;
//    }


}
