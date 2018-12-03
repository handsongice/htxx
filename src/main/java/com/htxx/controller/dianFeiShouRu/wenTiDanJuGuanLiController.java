package com.htxx.controller.dianFeiShouRu;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htxx.common.BaseResultData;
import com.htxx.common.LayUiResultData;
import com.htxx.constent.Constent;
import com.htxx.dto.ResultMap;
import com.htxx.entity.*;
import com.htxx.mapper.*;
import com.htxx.pojo.LayUiPageParams;
import com.htxx.service.WenTiDanJuGuanLiService;
import com.htxx.service.impl.DanJuDaoRuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: SaHongzhi
 * @Date: 2018-10-23 11:27
 */
@Controller
public class wenTiDanJuGuanLiController {

    @Autowired
    DlDianfeiMapper dianfeiMapper;

    @Autowired
    DlCompanyInfoMapper dlCompanyInfoMapper;

    @Autowired
    WenTiDanJuGuanLiService wenTiDanJuGuanLiService;

    @Autowired
    DlDianfeiDetailMapper dlDianfeiDetailMapper;

    @Autowired
    DlYkfpDelMapper dlYkfpDelMapper;

    @Autowired
    DlYkfpMainMapper dlYkfpMainMapper;

    @Autowired
    private YkfpMapper ykfpMapper;

    @RequestMapping(value = "/main/wtdj/foToWenTiDanJuGuanLi")
    public String foToWenTiDanJuGuanLi() {
        return "dianFeiShouRu/wenTiDanJuGuanLi";
    }


    //根据条件查询所有问题单据
//    @RequestMapping(value = "/noc/wtdj/listAllOrder")
//    @ResponseBody
//    public LayUiResultData listAllTroubleOrder(@RequestParam(value = "userName",required = false) String userName,
//                                               @RequestParam(value = "minMoneyIncludeTax",required = false) String minMoneyIncludeTax,
//                                               @RequestParam(value = "maxMoneyIncludeTax",required = false) String maxMoneyIncludeTax,
//                                               @RequestParam(value = "taxNum",required = false) String taxNum,
//                                               @RequestParam(value = "remark",required = false) String remark,
//                                               @RequestParam(value = "date",required = false) String date,
//                                               HttpSession session) {
//        List<DlDianfei> dianfeis=new ArrayList<>();
//        Map map=new HashMap();
//        map.put("userName",userName);
//        map.put("minMoneyIncludeTax",minMoneyIncludeTax);
//        map.put("maxMoneyIncludeTax",maxMoneyIncludeTax);
//        map.put("taxNum",taxNum);
//        map.put("remark",remark);
//        //问题票
//        map.put("dateType",1);
//        map.put("searchDate",date);
//        //获取企业ID
//        Employee employee= (Employee) session.getAttribute(Constent.SESSION_EMPLOYEE);
//        map.put("enterpriseId",employee.getEnterpriseId());
//        dianfeis=dianfeiMapper.selectByCondition(map);
//        System.out.println(dianfeis.toString());
//        return LayUiResultData.resultOK(dianfeis);
//    }

    //根据条件查询所有问题单据
    @RequestMapping(value = "/noc/wtdj/listAllOrder")
    @ResponseBody
    public Object listAllTroubleOrder(@RequestParam Map<String, Object> params,
                                               HttpSession session) {
        int pageNum = params.get("page") == null ? 1 : Integer.parseInt(params.get("page").toString());
        int pageSize = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(pageNum, pageSize, true);
        //获取企业ID
        Employee employee= (Employee) session.getAttribute(Constent.SESSION_EMPLOYEE);
        params.put("enterpriseId",employee.getEnterpriseId());
        //问题票
        params.put("dateType",1);
        List<DlDianfei> dianfeis=dianfeiMapper.selectByCondition(params);
        System.out.println(dianfeis.toString());

        PageInfo<DlDianfei> pageInfo = new PageInfo<>(dianfeis);
        LayUiPageParams<DlDianfei> pageParams = LayUiPageParams.defaultResult(pageInfo.getTotal(), dianfeis);
        return pageParams;
    }

    //查询某条问题单据信息
    @RequestMapping(value = "/noc/wtdj/showTroubleDjMain")
    public ModelAndView showTroubleDjMain(@RequestParam(value = "id") Long id ,
                                          HttpSession session) {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/dianFeiShouRu/wenTiDjDetail.html");
        //查询单据信息
        DlDianfeiExample dlDianfeiExample=new DlDianfeiExample();
        DlDianfeiExample.Criteria criteria1=dlDianfeiExample.createCriteria();
        criteria1.andIdEqualTo(id);
        DlDianfei dlDianfei=dianfeiMapper.selectByExample(dlDianfeiExample).get(0);
        Enterprise enterprise =(Enterprise)session.getAttribute(Constent.SESSION_ENTERPRISE);

        DlDianfeiDetailExample dlDianfeiDetailExample=new DlDianfeiDetailExample();
        DlDianfeiDetailExample.Criteria criteria3=dlDianfeiDetailExample.createCriteria();
        criteria3.andMainIdEqualTo(dlDianfei.getId());
        DlDianfeiDetail dlDianfeiDetail=dlDianfeiDetailMapper.selectByExample(dlDianfeiDetailExample).get(0);
        modelAndView.addObject("buyer",dlDianfei);
        modelAndView.addObject("goods",dlDianfeiDetail);
        modelAndView.addObject("seller",enterprise);
        return modelAndView;
    }


    @RequestMapping(value = "/noc/wtdj/export")
    public void export(@RequestParam(value = "userName",required = false) String userName,
                                  @RequestParam(value = "minMoneyIncludeTax",required = false) String minMoneyIncludeTax,
                                  @RequestParam(value = "maxMoneyIncludeTax",required = false) String maxMoneyIncludeTax,
                                  @RequestParam(value = "taxNum",required = false) String taxNum,
                                  @RequestParam(value = "remark",required = false) String remark,
                                  @RequestParam(value = "date",required = false) String date,
                                  @RequestParam(value = "dateType",required = false) String dateType,
                                  @RequestParam(value = "ids",required = false) String ids,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        Map map=new HashMap();
        map.put("userName",userName);
        map.put("minMoneyIncludeTax",minMoneyIncludeTax);
        map.put("maxMoneyIncludeTax",maxMoneyIncludeTax);
        map.put("taxNum",taxNum);
        map.put("remark",remark);
        map.put("searchDate",date);
        map.put("dateType",dateType);
        wenTiDanJuGuanLiService.export(request,response,ids,map);
    }

    /**
     * 添加或者编辑 的保存
     * @param request
     * @param notice
     * @return
     */
    @RequestMapping(value = "/noc/wtdj/saveOrEditWenTiDanJu")
    @ResponseBody
    public BaseResultData saveOrEditWenTiDanJu(@RequestParam(value = "isAdd") Integer isAdd,
                                               @RequestParam(value = "taxRate") BigDecimal taxRate,
                                               @RequestParam(value = "moneyIncludeTax") BigDecimal moneyIncludeTax,
                                               @RequestParam(value = "goodsAmount") BigDecimal goodsAmount,
                                               @RequestParam(value = "goodsMode") String goodsMode,
                                               @RequestParam(value = "goodsUnit") String goodsUnit,
                                               @RequestParam(value = "goodsName") String goodsName,
                                               @RequestParam(value = "mainId") long mainId,
                                               @RequestParam(value = "userName") String userName,
                                               @RequestParam(value = "userTaxNo") String userTaxNo,
                                               @RequestParam(value = "userAddr") String userAddr,
                                               @RequestParam(value = "userBankinfo") String userBankinfo,
                                               @RequestParam(value = "totalMoneyIncludeTax") BigDecimal totalMoneyIncludeTax,
                                               @RequestParam(value = "oldDataType") String oldDataType,
                                               HttpSession session) {



        DlDianfeiDetail dlDianfeiDetail=new DlDianfeiDetail();
        dlDianfeiDetail.setGoodsUnit(goodsUnit);
        dlDianfeiDetail.setTaxRate(taxRate);
        dlDianfeiDetail.setMoneyIncludeTax(moneyIncludeTax);
        dlDianfeiDetail.setGoodsAmount(goodsAmount);
        dlDianfeiDetail.setGoodsMode(goodsMode);
        dlDianfeiDetail.setGoodsName(goodsName);
        dlDianfeiDetail.setMainId(mainId);
        DlDianfei dlDianfei=new DlDianfei();
        dlDianfei.setId(mainId);
        dlDianfei.setTotalMoneyIncludeTax(totalMoneyIncludeTax);
        dlDianfei.setUserBankinfo(userBankinfo);
        dlDianfei.setUserAddr(userAddr);
        dlDianfei.setUserTaxNo(userTaxNo);
        dlDianfei.setUserName(userName);
        dlDianfei.setDataType(oldDataType);
        Enterprise enterprise= (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
        dlDianfei.setEnterpriseId(enterprise.getId());

        return wenTiDanJuGuanLiService.saveOrEditWenTiDanJu(isAdd,dlDianfeiDetail,dlDianfei,session);
    }

    /**
     * 问题单据合并
     * @param ids
     * @param userName
     * @param minMoneyIncludeTax
     * @param maxMoneyIncludeTax
     * @param taxNum
     * @param remark
     * @param date
     * @param dateType
     * @return
     */
    @RequestMapping(value = "/noc/wtdj/wenTiDanJuHeBing")
    @ResponseBody
    public BaseResultData wenTiDanJuHeBing(@RequestParam(value = "ids",required = false) String ids,
                                           @RequestParam(value = "userName",required = false) String userName,
                                           @RequestParam(value = "minMoneyIncludeTax",required = false) String minMoneyIncludeTax,
                                           @RequestParam(value = "maxMoneyIncludeTax",required = false) String maxMoneyIncludeTax,
                                           @RequestParam(value = "taxNum",required = false) String taxNum,
                                           @RequestParam(value = "remark",required = false) String remark,
                                           @RequestParam(value = "date",required = false) String date,
                                           @RequestParam(value = "dateType",required = false) String dateType) {
        return wenTiDanJuGuanLiService.wenTiDanJuHeBing(ids,userName,minMoneyIncludeTax,maxMoneyIncludeTax,
                taxNum,remark,date,dateType);
    }

    //查询已开发票
    @RequestMapping(value = "/noc/wtdj/searchAlreadyOpenInv")
    public ModelAndView searchAlreadyOpenInv(@RequestParam(value = "identityField") String identityField,HttpSession session) {
        ModelAndView modelAndView=new ModelAndView();
        //查询单据信息  相同单据号  DataType==2
        DlDianfeiExample dlDianfeiExample=new DlDianfeiExample();
        DlDianfeiExample.Criteria criteria1=dlDianfeiExample.createCriteria();
        criteria1.andIdentityFieldEqualTo(identityField);
        criteria1.andDataTypeEqualTo(Byte.valueOf(DanJuDaoRuServiceImpl.DATA_TYPE_2));
        String mainId=String.valueOf(dianfeiMapper.selectByExample(dlDianfeiExample).get(0).getId());

        String[] djhs = {mainId};
        Ykfp ykfp = ykfpMapper.getYkfpMainAndDelListByDjh(djhs).get(0);
        modelAndView.setViewName("highPower/invoiceDetail");
        modelAndView.addObject("ykfp",ykfp);
        return modelAndView;
    }

    @RequestMapping(value = "/noc/wtdj/chongHongOrZuoFei")
    @ResponseBody
    public BaseResultData chongHongOrZuoFei(@RequestParam(value = "id",required = false) String id) {
        return BaseResultData.resultOK(null);
    }



}
