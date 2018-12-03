package com.htxx.controller.basic;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htxx.constent.Constent;
import com.htxx.dto.ResultMap;
import com.htxx.entity.*;
import com.htxx.mapper.InvoiceStockMapper;
import com.htxx.pojo.LayUiPageParams;
import com.htxx.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/11/2 13:51
 */
@Controller
public class EnterpriseController {
    @Autowired
    EnterpriseService enterpriseService;

    @Autowired
    KpjxxService kpjxxService;

    @Autowired
    MessageService messageService;

    @Autowired
    CommonService commonService;

    @Autowired
    private InvoiceStockMapper invoiceStockMapper;

    /**
     * 列表
     * @return
     */
    @RequestMapping(value = "/main/myEnterprise")
    public ModelAndView enterprise(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("role/enterprise");
        HttpSession session = request.getSession();
        Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
        Enterprise _enterprise = enterpriseService.getEnterpriseById(enterprise.getId());
        mv.addObject("enterprise", _enterprise);
        return mv;
    }

    /**
     * 列表
     * @return
     */
    @RequestMapping(value = "/main/enterprise")
    public String enterprise() {
        return "basic/enterprise";
    }

    /**
     * 消息列表
     * @return
     */
    @RequestMapping(value = "/message")
    public String message() {
        return "basic/message";
    }

    /**
     * 编辑信息
     * @param params
     * @return
     */
    @RequestMapping(value = "/main/editEnterprise")
    public ModelAndView editEnterprise(@RequestParam Map<String, Object> params) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("basic/editEnterprise");
        Long _id = Long.parseLong(params.get("id").toString());
        Enterprise enterprise = enterpriseService.getEnterpriseById(_id);
        mv.addObject("enterprise", enterprise);
        return mv;
    }

    /**
     * 查看消息
     * @return
     */
    @RequestMapping(value = "/viewMessage")
    public ModelAndView viewMessage(@RequestParam Map<String, Object> params) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("basic/viewMessage");
        Long _id = Long.parseLong(params.get("id").toString());
        Message message = messageService.getMessageById(_id);
        if(null != message && null != message.getId()) {
            Message input = new Message();
            input.setId(message.getId());
            input.setIsRead(2);
            messageService.updateMessage(input);
        }
        mv.addObject("message", message);
        return mv;
    }

    /**
     * 开票机分页
     * @param params
     * @param request
     * @return
     */
    @RequestMapping("/noc/kpjxxList")
    @ResponseBody
    public Object kpjxxList(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        int pageNum = params.get("page") == null ? 1 : Integer.parseInt(params.get("page").toString());
        int pageSize = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(pageNum, pageSize, true);
        try {
            List<Kpjxx> kpjxxes = kpjxxService.getKpjxxList(params);
            PageInfo<Kpjxx> pageInfo = new PageInfo<>(kpjxxes);
            LayUiPageParams<Kpjxx> pageParams = LayUiPageParams.defaultResult(pageInfo.getTotal(), kpjxxes);
            return pageParams;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/noc/pageStockList")
    @ResponseBody
    public Object pageStockList(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        int pageNum = params.get("page") == null ? 1 : Integer.parseInt(params.get("page").toString());
        int pageSize = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(pageNum, pageSize, true);
        try {
            HttpSession session = request.getSession();
            Employee employee = (Employee) session.getAttribute(Constent.SESSION_EMPLOYEE);
            Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
            //发票类型
            List<Fplx> fplxes = commonService.getCurrentFplx(enterprise.getId());
            //开票机
            List<Kpjxx> kpjxxes = commonService.findAllKpj(employee.getId(), enterprise.getId());

            Map<String, Object> _params = new HashMap<>();
            List<Long> kpjIds = new ArrayList<>();
            for (int i = 0; i < kpjxxes.size(); i++) {
                kpjIds.add(kpjxxes.get(i).getId());
            }
            String[] fplxesArray = new String[fplxes.size()];
            for (int i = 0; i < fplxes.size(); i++) {
                fplxesArray[i] = String.valueOf(fplxes.get(i).getCode());
            }
            String fplxeStr = StringUtils.join(fplxesArray, ",");
            _params.put("enterpriseId", enterprise.getId());
            _params.put("kpjIds", kpjIds);
            _params.put("fplxStr", fplxeStr);
            List<InvoiceStock> invoiceStocks = invoiceStockMapper.selectByParams(_params);
            PageInfo<InvoiceStock> pageInfo = new PageInfo<>(invoiceStocks);
            LayUiPageParams<InvoiceStock> pageParams = LayUiPageParams.defaultResult(pageInfo.getTotal(), invoiceStocks);
            return pageParams;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 新建
     * @return
     */
    @RequestMapping(value = "/main/addEnterprise")
    public String addEnterprise() {
        return "basic/addEnterprise";
    }

    /**
     * 插入
     * @param request
     * @param enterprise
     * @return
     */
    @RequestMapping(value = "/noc/insertEnterprise")
    @ResponseBody
    public ResultMap insertEnterprise(HttpServletRequest request, Enterprise enterprise) {
        try {
            return enterpriseService.addEnterprise(enterprise);
        }catch (Exception e) {
            e.printStackTrace();
            return ResultMap.fail(e.getMessage());
        }
    }
    /**
     * 追加开票机
     * @param request
     * @param kpjxx
     * @return
     */
    @RequestMapping(value = "/noc/insertKpjxx")
    @ResponseBody
    public ResultMap insertKpjxx(HttpServletRequest request, Kpjxx kpjxx) {
        try {
            return kpjxxService.addKpjxx(kpjxx);
        }catch (Exception e) {
            e.printStackTrace();
            return ResultMap.fail(e.getMessage());
        }
    }
    /**
     * 更新别名
     * @param request
     * @param kpjxx
     * @return
     */
    @RequestMapping(value = "/noc/updateKpjxx")
    @ResponseBody
    public ResultMap updateKpjxx(HttpServletRequest request, Kpjxx kpjxx) {
        return kpjxxService.updateKpjxx(kpjxx);
    }
    /**
     * 公司列表
     * @param params
     * @param request
     * @return
     */
    @RequestMapping("/noc/pageEnterpriseList")
    @ResponseBody
    public Object pageEnterpriseList(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        int pageNum = params.get("page") == null ? 1 : Integer.parseInt(params.get("page").toString());
        int pageSize = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(pageNum, pageSize, true);
        try {
            List<Enterprise> actions = enterpriseService.getEnterpriseList(params);
            PageInfo<Enterprise> pageInfo = new PageInfo<>(actions);

            LayUiPageParams<Enterprise> pageParams = LayUiPageParams.defaultResult(pageInfo.getTotal(), actions);
            return pageParams;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 消息列表
     * @param params
     * @param request
     * @return
     */
    @RequestMapping("/noc/pageMessageList")
    @ResponseBody
    public Object pageMessageList(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        int pageNum = params.get("page") == null ? 1 : Integer.parseInt(params.get("page").toString());
        int pageSize = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(pageNum, pageSize, true);
        HttpSession session = request.getSession();
        Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
        params.put("enterprise_id",enterprise.getId());
        try {
            List<Message> messages = messageService.getMessageList(params);
            PageInfo<Message> pageInfo = new PageInfo<>(messages);

            LayUiPageParams<Message> pageParams = LayUiPageParams.defaultResult(pageInfo.getTotal(), messages);
            return pageParams;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 消息列表
     * @param params
     * @param request
     * @return
     */
    @RequestMapping("/noc/allMessage")
    @ResponseBody
    public Object allMessage(@RequestParam Map<String, Object> params, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute(Constent.SESSION_EMPLOYEE);
        params.put("to_id",employee.getId());
        params.put("is_read",1);
        try {
            List<Message> messages = messageService.getMessageList(params);
            return messages;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 更新
     * @param request
     * @param enterprise
     * @return
     */
    @RequestMapping(value = "/noc/updateEnterprise")
    @ResponseBody
    public ResultMap updateEnterprise(HttpServletRequest request, Enterprise enterprise) {
        ResultMap ret = enterpriseService.updateEnterprise(enterprise);
        Enterprise _enterprise = enterpriseService.getEnterpriseById(enterprise.getId());
        HttpSession session = request.getSession();
        session.setAttribute(Constent.SESSION_ENTERPRISE,_enterprise);
        return ret;
    }
    /**
     * 逻辑删除
     * @param params
     * @param request
     * @return
     */
    @RequestMapping(value = "/noc/delEnterprise")
    @ResponseBody
    public ResultMap delEnterprise(@RequestParam Map<String, Object> params,HttpServletRequest request) {
        return enterpriseService.delEnterprise(Long.parseLong(params.get("id").toString()));
    }
}
