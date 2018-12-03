package com.htxx.controller.dianFeiShouRu;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htxx.common.BaseResultData;
import com.htxx.constent.Constent;
import com.htxx.entity.*;
import com.htxx.mapper.*;
import com.htxx.pojo.LayUiPageParams;
import com.htxx.service.CommonService;
import com.htxx.service.KaiPiaoGuanLiService;
import com.htxx.service.WenTiDanJuGuanLiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: SaHongzhi
 * @Date: 2018-10-23 11:27
 */
@Controller
public class kaiPiaoGuanLiController {

    @Autowired
    DlFplxMapper dlFplxMapper;

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
    DlSpxxMapper dlSpxxMapper;

    @RequestMapping(value = "/main/kpgl/foToKaiPiaoGuanLi")
    public String foToDanJuDaoRu() {
        return "dianFeiShouRu/kaiPiaoGuanLi";
    }

    //根据条件查询所有单据状态为
    @RequestMapping(value = "/noc/kpgl/listAllDjStateIsZero")
    @ResponseBody
    public Object listAllDjStateIsZero(@RequestParam Map<String, Object> params,
                                      HttpSession session) {
        int pageNum = params.get("page") == null ? 1 : Integer.parseInt(params.get("page").toString());
        int pageSize = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(pageNum, pageSize, true);
        //获取企业ID
        Employee employee= (Employee) session.getAttribute(Constent.SESSION_EMPLOYEE);
        params.put("enterpriseId",employee.getEnterpriseId());
        //未开正常票
        params.put("dateType","0");
        List<DlDianfei> dianfeis=dianfeiMapper.selectByCondition(params);
        PageInfo<DlDianfei> pageInfo = new PageInfo<>(dianfeis);
        LayUiPageParams<DlDianfei> pageParams = LayUiPageParams.defaultResult(pageInfo.getTotal(), dianfeis);
        return pageParams;
    }

    //查询某条问题单据信息
    @RequestMapping(value = "/noc/kpgl/showDjMain")
    public ModelAndView showTroubleDjMain(@RequestParam(value = "id") Long id, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/dianFeiShouRu/faPiaoDetail.html");
        //查询单据信息
        DlDianfeiExample dlDianfeiExample = new DlDianfeiExample();
        DlDianfeiExample.Criteria criteria1 = dlDianfeiExample.createCriteria();
        criteria1.andIdEqualTo(id);
        DlDianfei dlDianfei = dianfeiMapper.selectByExample(dlDianfeiExample).get(0);
        Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);

        DlDianfeiDetailExample dlDianfeiDetailExample = new DlDianfeiDetailExample();
        DlDianfeiDetailExample.Criteria criteria3 = dlDianfeiDetailExample.createCriteria();
        criteria3.andMainIdEqualTo(dlDianfei.getId());
        DlDianfeiDetail dlDianfeiDetail = dlDianfeiDetailMapper.selectByExample(dlDianfeiDetailExample).get(0);
        modelAndView.addObject("buyer", dlDianfei);
        modelAndView.addObject("goods", dlDianfeiDetail);
        modelAndView.addObject("seller", enterprise);
        return modelAndView;
    }

    //获取该企业的可用发票类型
    @RequestMapping(value = "/noc/kpgl/getAllInvTypeByEnterprise")
    public ModelAndView getAllInvTypeByEnterprise(@RequestParam(value = "ids", required = false) String ids,
                                                  HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/dianFeiShouRu/xuanZeFaPiaoZhongLei.html");
        Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
        List<Fplx> fplxList = commonService.getCurrentFplx(enterprise.getId());
        modelAndView.addObject("type", fplxList);
        modelAndView.addObject("ids", ids);
        return modelAndView;
    }

    //根据单据ID获取电费单据信息
    @RequestMapping(value = "/noc/kpgl/getDjInfoById")
    @ResponseBody
    public BaseResultData getDjInfoById(@RequestParam(value = "ids") String ids,
                                        HttpSession session) {
        String[] idArr = ids.split(",");
        List<DlDianfeiDetail> dlDianfeiDetailAll = new ArrayList<>();
        //因为是同一种商品的合并，最终只合并成一条数据
        for (int i = 0; i < idArr.length; i++) {
            DlDianfeiDetailExample dlDianfeiDetailExample = new DlDianfeiDetailExample();
            DlDianfeiDetailExample.Criteria criteria = dlDianfeiDetailExample.createCriteria();
            criteria.andMainIdEqualTo(Long.valueOf(idArr[i]));
            List<DlDianfeiDetail> dlDianfeiDetails = dlDianfeiDetailMapper.selectByExample(dlDianfeiDetailExample);
            dlDianfeiDetailAll.addAll(dlDianfeiDetails);
        }
        //查出所有明细后，含税金额累加,数量累加
        BigDecimal hsjeSum = BigDecimal.ZERO;
        BigDecimal goodsAmountSum = BigDecimal.ZERO;
        for (int i = 0; i < dlDianfeiDetailAll.size(); i++) {
            BigDecimal hsje = dlDianfeiDetailAll.get(i).getMoneyIncludeTax();
            hsjeSum = hsjeSum.add(hsje);
            goodsAmountSum = goodsAmountSum.add(dlDianfeiDetailAll.get(i).getGoodsAmount());
        }
        //将合并后的值放在dlDianfeiDetailAll第一行的单据内
        dlDianfeiDetailAll.get(0).setGoodsAmount(goodsAmountSum);
        dlDianfeiDetailAll.get(0).setMoneyIncludeTax(hsjeSum);


        DlDianfei dlDianfei = dianfeiMapper.selectByPrimaryKey(Long.valueOf(idArr[0]));
        Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
        Employee employee = (Employee) session.getAttribute(Constent.SESSION_EMPLOYEE);
//        List<String> ssflbm = new ArrayList<>();
//        for (int i = 0; i < dlDianfeiDetails.size(); i++) {
        DlSpxxExample dlSpxxExample = new DlSpxxExample();
        DlSpxxExample.Criteria criteria1 = dlSpxxExample.createCriteria();
        criteria1.andSpmcEqualTo(dlDianfeiDetailAll.get(0).getGoodsName());
        String ssflbm = dlSpxxMapper.selectByExample(dlSpxxExample).get(0).getShflbm();
//        }
        DlDianfeiObj dlDianfeiObj = new DlDianfeiObj();
        dlDianfeiObj.setId(dlDianfei.getId());
        dlDianfeiObj.setAddr(enterprise.getAddr());
        dlDianfeiObj.setBankInfo(enterprise.getBankInfo());
        dlDianfeiObj.setBmbbh("33");
        dlDianfeiObj.setBz(dlDianfei.getRecordInfo());
        dlDianfeiObj.setFhr(employee.getName());
        dlDianfeiObj.setSkr(employee.getName());
        dlDianfeiObj.setIdentityField(dlDianfei.getIdentityField());
        dlDianfeiObj.setKpr(employee.getName());
        dlDianfeiObj.setUserAddr(dlDianfei.getUserAddr());
        dlDianfeiObj.setUserBankinfo(dlDianfei.getUserBankinfo());
        dlDianfeiObj.setUserName(dlDianfei.getUserName());
        dlDianfeiObj.setUserTaxNo(dlDianfei.getUserTaxNo());
        dlDianfeiObj.setDlDianfeiDetail(dlDianfeiDetailAll.subList(0,1));
        dlDianfeiObj.setSsflbm(ssflbm);
        System.out.println(dlDianfeiObj.toString());
        return BaseResultData.resultOK(dlDianfeiObj);
        //return BaseResultData.resultError("error",null);
    }

    //根据单据ID获取电费单据信息
    @RequestMapping(value = "/noc/kpgl/updateDjState")
    @ResponseBody
    public BaseResultData updateDjState(@RequestParam(value = "ids") String ids,
                                        @RequestParam(value = "dataType") String dataType,
                                        HttpSession session) {
        String[] idArr = ids.split(",");
        DlDianfei dlDianfei = new DlDianfei();
        for (int i = 0; i < idArr.length; i++) {
            dlDianfei.setId(Long.valueOf(idArr[i]));
            dlDianfei.setDataType(dataType);
            dianfeiMapper.updateByPrimaryKeySelective(dlDianfei);
        }
//        return BaseResultData.resultOK(null);
        return BaseResultData.result("开票成功，保存数据完成!","200",null);
    }

    //根据单据ID获取电费单据信息
    @RequestMapping(value = "/noc/kpgl/test")
    @ResponseBody
    public BaseResultData updateDjState(HttpSession session) {

        return BaseResultData.result("开票成功，保存数据完成!","200",null);
    }
}
