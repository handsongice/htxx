package com.htxx.controller.dianFeiShouRu;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htxx.common.BaseResultData;
import com.htxx.constent.Constent;
import com.htxx.controller.BaseController;
import com.htxx.entity.*;
import com.htxx.enums.CommonEnum;
import com.htxx.mapper.*;
import com.htxx.pojo.LayUiPageParams;
import com.htxx.service.DanJuXiuGaiShenHeService;
import com.htxx.service.HighPowerYkfpMngService;
import com.htxx.util.DateUtil;
import com.htxx.util.PoiExcelExportUtil;
import com.htxx.util.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author: SaHongzhi
 * @Date: 2018-10-23 11:27
 */
@Controller
@Slf4j
public class danJuXiuGaiShenHeController  {
    @Autowired
    HighPowerYkfpMngService highPowerYkfpMngService;

    @Autowired
    DanJuXiuGaiShenHeService danJuXiuGaiShenHeService;

    @Autowired
    DlDfzypShLogMapper dlDfzypShLogMapper;

    @Autowired
    DlDianfeiMapper dianfeiMapper;

    @Autowired
    DlDianfeiDetailMapper dlDianfeiDetailMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    DlRoleEmployeeMapper dlRoleEmployeeMapper;


    @RequestMapping(value = "/main/djxgsh/foToDanJuXiuGaiShenHe")
    public ModelAndView foToZiYouPiao(HttpSession session) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/dianFeiShouRu/danJuXiuGaiShenHe.html");
        return modelAndView;
    }

    //根据条件查询所有问题单据
    @RequestMapping(value = "/noc/djxgsh/list")
    @ResponseBody
    public Object listAllTroubleOrder(@RequestParam Map<String, Object> params,
                                      HttpSession session) {
        int pageNum = params.get("page") == null ? 1 : Integer.parseInt(params.get("page").toString());
        int pageSize = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(pageNum, pageSize, true);
        List<DlDianfei> dianfeis = new ArrayList<>();
        //获取企业ID
        Employee employee = (Employee) session.getAttribute(Constent.SESSION_EMPLOYEE);
        //获取自己角色(多个)可以管理的审核
        DlRoleEmployeeExample dlRoleEmployeeExample = new DlRoleEmployeeExample();
        DlRoleEmployeeExample.Criteria criteria = dlRoleEmployeeExample.createCriteria();
        criteria.andEmployeeIdEqualTo(employee.getId());
        List<DlRoleEmployee> dlRoleEmployees = dlRoleEmployeeMapper.selectByExample(dlRoleEmployeeExample);
        Iterator<DlRoleEmployee> dlRoleEmployeeIterator = dlRoleEmployees.iterator();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("(");
        while (dlRoleEmployeeIterator.hasNext()) {
            stringBuffer.append(dlRoleEmployeeIterator.next().getRoleId() + ",");
        }
        stringBuffer.append(")");
        if (stringBuffer.lastIndexOf(",") > 0) {
            stringBuffer.deleteCharAt(stringBuffer.lastIndexOf(","));
        }
        System.out.println(stringBuffer);
        params.put("roleId", stringBuffer.toString());
        params.put("enterpriseId", employee.getEnterpriseId());
        dianfeis = dlDfzypShLogMapper.getDlDianFeiLogList(params);
        //先查出在临时表内存在的所有修改申请记录，
        //System.out.println(dianfeis.get(0).toString());

        PageInfo<DlDianfei> pageInfo = new PageInfo<>(dianfeis);
        LayUiPageParams<DlDianfei> pageParams = LayUiPageParams.defaultResult(pageInfo.getTotal(), dianfeis);
        return pageParams;
    }

    /**
     *导出问题单据审核列表
     * @param params
     * @param session
     * @return
     */
    @RequestMapping(value = "/noc/djxgsh/exportExcel")
    public void exportExcel(@RequestParam Map<String, Object> params,
                            HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        //获取企业ID
        Employee employee = (Employee) session.getAttribute(Constent.SESSION_EMPLOYEE);
        //获取自己角色(多个)可以管理的审核
        DlRoleEmployeeExample dlRoleEmployeeExample = new DlRoleEmployeeExample();
        DlRoleEmployeeExample.Criteria criteria = dlRoleEmployeeExample.createCriteria();
        criteria.andEmployeeIdEqualTo(employee.getId());
        List<DlRoleEmployee> dlRoleEmployees = dlRoleEmployeeMapper.selectByExample(dlRoleEmployeeExample);
        Iterator<DlRoleEmployee> dlRoleEmployeeIterator = dlRoleEmployees.iterator();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("(");
        while (dlRoleEmployeeIterator.hasNext()) {
            stringBuffer.append(dlRoleEmployeeIterator.next().getRoleId() + ",");
        }
        stringBuffer.append(")");
        if (stringBuffer.lastIndexOf(",") > 0) {
            stringBuffer.deleteCharAt(stringBuffer.lastIndexOf(","));
        }
        System.out.println(stringBuffer);
        params.put("roleId", stringBuffer.toString());
        params.put("enterpriseId", employee.getEnterpriseId());
        List<DlDianfei> list  = dlDfzypShLogMapper.getDlDianFeiLogList(params);
        //先查出在临时表内存在的所有修改申请记录，

        //创建文件名称
        String fileName = "问题单据审核列表-" + DateUtil.getTimes() + ".xls";
        //Servlet准备 设置兼容浏览器 的OutputStream流
        ServletUtil su = new ServletUtil(fileName, request,response );
        su.poiExcelServlet();
        //设置excel 标题，列的取值field
        String[] heads = {"ID", "当月唯一标识", "户名","户号", "用户地址电话","用户银行账号","月份", "购方税号", "金额"};
        String[] cols = {"fplxStr", "identityField", "userName","userNum" ,"userAddr","userBankinfo" ,"monthYear", "userTaxNo", "totalMoneyIncludeTax"};

        // 导出时 修改字段显示
        list.forEach(item -> {
        });
        log.info("list:" + list);
        //构造excel导出类
        PoiExcelExportUtil<Fpsg> pee = new PoiExcelExportUtil(fileName, heads, cols, list, su.getOut());
        //excel导出 至浏览器
        pee.exportExcel();
    }

    //查询出新旧单据的购方信息
    @RequestMapping(value = "/noc/djxgsh/newAndOldDjDel")
    public ModelAndView showTroubleDjMain(@RequestParam(value = "id") Long id,
                                          HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/dianFeiShouRu/newAndOldDjDel.html");
        //查询出原单据信息
        DlDianfei oldDlDianfei = dianfeiMapper.selectByPrimaryKey(id);
        DlDianfeiDetailExample dlDianfeiDetailExample = new DlDianfeiDetailExample();
        DlDianfeiDetailExample.Criteria criteria = dlDianfeiDetailExample.createCriteria();
        criteria.andMainIdEqualTo(oldDlDianfei.getId());
        DlDianfeiDetail oldDlDianfeiDetails = dlDianfeiDetailMapper.selectByExample(dlDianfeiDetailExample).get(0);


        //查询出问题单据信息
        DlDianfeiExample dlDianfeiExample = new DlDianfeiExample();
        DlDianfeiExample.Criteria criteria1 = dlDianfeiExample.createCriteria();
        criteria1.andIdentityFieldEqualTo(String.valueOf(id));
        dlDianfeiExample.setOrderByClause("id desc");
        DlDianfei newDlDianfei = dianfeiMapper.selectFromDlDianfeiTemp(dlDianfeiExample).get(0);
        DlDianfeiDetailExample dlDianfeiDetailExample1 = new DlDianfeiDetailExample();
        DlDianfeiDetailExample.Criteria criteria2 = dlDianfeiDetailExample1.createCriteria();
        criteria2.andMainIdEqualTo(newDlDianfei.getId());
        dlDianfeiDetailExample1.setOrderByClause("id desc");
        DlDianfeiDetail newDlDianfeiDetail = dlDianfeiDetailMapper.selectByExampleFromTemp(dlDianfeiDetailExample1).get(0);
        modelAndView.addObject("newBuyer", newDlDianfei);
        modelAndView.addObject("newGoods", newDlDianfeiDetail);
        modelAndView.addObject("oldBuyer", oldDlDianfei);
        modelAndView.addObject("oldGoods", oldDlDianfeiDetails);
        return modelAndView;
    }

    /**
     * 提交审核人的审核意见
     *
     * @param id
     * @param suggestion
     * @param session
     * @return
     */
    @RequestMapping(value = "/noc/djxgsh/submitSuggestion")
    @ResponseBody
    public BaseResultData submitSuggestion(@RequestParam(value = "shyj") int shyj,
                                           @RequestParam(value = "suggestion") String suggestion,
                                           @RequestParam(value = "mainId") long mainId,
                                           HttpSession session) {


        return danJuXiuGaiShenHeService.submitSuggestion(shyj, suggestion, mainId, session);
    }


    //审核历史记录
    @RequestMapping(value = "/noc/djxgsh/shenHeLiShiJiLu")
    public ModelAndView shenHeLiShiJiLu(@RequestParam(value = "id") Long id,
                                        HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("dianFeiShouRu/shenHeLiShiJiLu.html");
        DlDfzypShLogExample dlDfzypShLogExample3 = new DlDfzypShLogExample();
        DlDfzypShLogExample.Criteria criteria8 = dlDfzypShLogExample3.createCriteria();
        criteria8.andFpsgIdRefEqualTo(id);
        criteria8.andStatusNotEqualTo(0);
        List<DlDfzypShLog> dlDfzypShLogs = dlDfzypShLogMapper.selectByExample(dlDfzypShLogExample3);
        modelAndView.addObject("logs", dlDfzypShLogs);
        return modelAndView;
    }

}
