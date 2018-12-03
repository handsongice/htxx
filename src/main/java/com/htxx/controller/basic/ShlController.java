package com.htxx.controller.basic;

import com.alibaba.fastjson.JSONArray;
import com.htxx.constent.Constent;
import com.htxx.dto.ResultMap;
import com.htxx.entity.*;
import com.htxx.service.DfdjxgShlService;
import com.htxx.service.DfzypShlService;
import com.htxx.service.FpsgShlService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/11/12 13:09
 */
@Controller
public class ShlController {

    @Autowired
    FpsgShlService fpsgShlService;

    @Autowired
    DfzypShlService dfzypShlService;

    @Autowired
    DfdjxgShlService dfdjxgShlService;

    /**
     * 列表
     * @return
     */
    @RequestMapping(value = "/main/fpsgShl")
    public String fpsgShl() {
        return "system/fpsgShl";
    }

    @RequestMapping(value = "/main/dfzypShl")
    public String dfzypShl() {
        return "system/dfzypShl";
    }

    @RequestMapping(value = "/main/dfdjxgShl")
    public String dfdjxgShl() {
        return "system/dfdjxgShl";
    }
    /**
     * 数据
     * @param params
     * @param request
     * @return
     */
    @RequestMapping("/noc/fpsgShlList")
    @ResponseBody
    public Object fpsgShlList(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
        params.put("enterprise_id",enterprise.getId());
        try {
            List<FpsgShl> fpsgShls = fpsgShlService.getFpsgShlList(params);
            Flow flow = new Flow();
            Map<String, Object> nodes = new HashMap<>();
            Map<String, Object> lines = new HashMap<>();
            for (FpsgShl fpsgShl:fpsgShls) {
                FlowNodes flowNodes = new FlowNodes();
                if("node".equals(fpsgShl.getStyle())) {
                    flowNodes.setId(fpsgShl.getId());
                    flowNodes.setName(fpsgShl.getName());
                    flowNodes.setLeft(fpsgShl.getLeft());
                    flowNodes.setTop(fpsgShl.getTop());
                    flowNodes.setType(fpsgShl.getType());
                    flowNodes.setWidth(fpsgShl.getWidth());
                    flowNodes.setHeight(fpsgShl.getHeight());
                    flowNodes.setAlt(fpsgShl.getAlt());
                    nodes.put("demo_node_"+fpsgShl.getId().toString(),flowNodes);
                } else if ("line".equals(fpsgShl.getStyle())) {
                    flowNodes.setId(fpsgShl.getId());
                    flowNodes.setType(fpsgShl.getType());
                    flowNodes.setFrom(fpsgShl.getFrom());
                    flowNodes.setTo(fpsgShl.getTo());
                    lines.put("demo_line_"+fpsgShl.getId().toString(),flowNodes);
                }
            }
            flow.setNodes(nodes);
            flow.setLines(lines);
            return flow;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/noc/dfzypShlList")
    @ResponseBody
    public Object dfzypShlList(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
        params.put("enterprise_id",enterprise.getId());
        try {
            List<DfzypShl> fpsgShls = dfzypShlService.getDfzypShlList(params);
            Flow flow = new Flow();
            Map<String, Object> nodes = new HashMap<>();
            Map<String, Object> lines = new HashMap<>();
            for (DfzypShl fpsgShl:fpsgShls) {
                FlowNodes flowNodes = new FlowNodes();
                if("node".equals(fpsgShl.getStyle())) {
                    flowNodes.setId(fpsgShl.getId());
                    flowNodes.setName(fpsgShl.getName());
                    flowNodes.setLeft(fpsgShl.getLeft());
                    flowNodes.setTop(fpsgShl.getTop());
                    flowNodes.setType(fpsgShl.getType());
                    flowNodes.setWidth(fpsgShl.getWidth());
                    flowNodes.setHeight(fpsgShl.getHeight());
                    flowNodes.setAlt(fpsgShl.getAlt());
                    nodes.put("demo_node_"+fpsgShl.getId().toString(),flowNodes);
                } else if ("line".equals(fpsgShl.getStyle())) {
                    flowNodes.setId(fpsgShl.getId());
                    flowNodes.setType(fpsgShl.getType());
                    flowNodes.setFrom(fpsgShl.getFrom());
                    flowNodes.setTo(fpsgShl.getTo());
                    lines.put("demo_line_"+fpsgShl.getId().toString(),flowNodes);
                }
            }
            flow.setNodes(nodes);
            flow.setLines(lines);
            return flow;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/noc/dfdjxgShlList")
    @ResponseBody
    public Object dfdjxgShlList(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
        params.put("enterprise_id",enterprise.getId());
        try {
            List<DfdjxgShl> fpsgShls = dfdjxgShlService.getDfdjxgShlList(params);
            Flow flow = new Flow();
            Map<String, Object> nodes = new HashMap<>();
            Map<String, Object> lines = new HashMap<>();
            for (DfdjxgShl fpsgShl:fpsgShls) {
                FlowNodes flowNodes = new FlowNodes();
                if("node".equals(fpsgShl.getStyle())) {
                    flowNodes.setId(fpsgShl.getId());
                    flowNodes.setName(fpsgShl.getName());
                    flowNodes.setLeft(fpsgShl.getLeft());
                    flowNodes.setTop(fpsgShl.getTop());
                    flowNodes.setType(fpsgShl.getType());
                    flowNodes.setWidth(fpsgShl.getWidth());
                    flowNodes.setHeight(fpsgShl.getHeight());
                    flowNodes.setAlt(fpsgShl.getAlt());
                    nodes.put("demo_node_"+fpsgShl.getId().toString(),flowNodes);
                } else if ("line".equals(fpsgShl.getStyle())) {
                    flowNodes.setId(fpsgShl.getId());
                    flowNodes.setType(fpsgShl.getType());
                    flowNodes.setFrom(fpsgShl.getFrom());
                    flowNodes.setTo(fpsgShl.getTo());
                    lines.put("demo_line_"+fpsgShl.getId().toString(),flowNodes);
                }
            }
            flow.setNodes(nodes);
            flow.setLines(lines);
            return flow;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 插入
     * @param request
     * @param fpsgShl
     * @return
     */
    @RequestMapping(value = "/noc/insertFpsgShl")
    @ResponseBody
    public ResultMap insertFpsgShl(HttpServletRequest request, FpsgShl fpsgShl) {
        try {
            HttpSession session = request.getSession();
            Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
            fpsgShl.setEnterpriseId(enterprise.getId());
            return fpsgShlService.addFpsgShl(fpsgShl);
        }catch (Exception e) {
            e.printStackTrace();
            return ResultMap.fail(e.getMessage());
        }
    }
    @RequestMapping(value = "/noc/insertDfzypShl")
    @ResponseBody
    public ResultMap insertDfzypShl(HttpServletRequest request, DfzypShl fpsgShl) {
        try {
            HttpSession session = request.getSession();
            Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
            fpsgShl.setEnterpriseId(enterprise.getId());
            return dfzypShlService.addDfzypShl(fpsgShl);
        }catch (Exception e) {
            e.printStackTrace();
            return ResultMap.fail(e.getMessage());
        }
    }
    @RequestMapping(value = "/noc/insertDfdjxgShl")
    @ResponseBody
    public ResultMap insertDfdjxgShl(HttpServletRequest request, DfdjxgShl fpsgShl) {
        try {
            HttpSession session = request.getSession();
            Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
            fpsgShl.setEnterpriseId(enterprise.getId());
            return dfdjxgShlService.addDfdjxgShl(fpsgShl);
        }catch (Exception e) {
            e.printStackTrace();
            return ResultMap.fail(e.getMessage());
        }
    }
    /**
     * 删除
     * @param params
     * @param request
     * @return
     */
    @RequestMapping(value = "/noc/delFpsgShl")
    @ResponseBody
    public ResultMap delFpsgShl(@RequestParam Map<String, Object> params,HttpServletRequest request) {
        try {
            return fpsgShlService.delFpsgShl(Long.parseLong(params.get("id").toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMap.fail(Constent.DB_DELETE_FAILURE);
        }
    }
    @RequestMapping(value = "/noc/delDfzypShl")
    @ResponseBody
    public ResultMap delDfzypShl(@RequestParam Map<String, Object> params,HttpServletRequest request) {
        try {
            return dfzypShlService.delDfzypShl(Long.parseLong(params.get("id").toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMap.fail(Constent.DB_DELETE_FAILURE);
        }
    }
    @RequestMapping(value = "/noc/delDfdjxgShl")
    @ResponseBody
    public ResultMap delDfdjxgShl(@RequestParam Map<String, Object> params,HttpServletRequest request) {
        try {
            return dfdjxgShlService.delDfdjxgShl(Long.parseLong(params.get("id").toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMap.fail(Constent.DB_DELETE_FAILURE);
        }
    }
    /**
     * 编辑信息
     * @param params
     * @return
     */
    @RequestMapping(value = "/main/drShl")
    public ModelAndView drShl(@RequestParam Map<String, Object> params) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/drShl");
        FpsgShl fpsgShl = fpsgShlService.getFpsgShlById(Long.parseLong(params.get("id").toString()));
        mv.addObject("fpsgShl", fpsgShl);
        return mv;
    }
    @RequestMapping(value = "/main/drZypShl")
    public ModelAndView drZypShl(@RequestParam Map<String, Object> params) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/drZypShl");
        DfzypShl fpsgShl = dfzypShlService.getDfzypShlById(Long.parseLong(params.get("id").toString()));
        mv.addObject("fpsgShl", fpsgShl);
        return mv;
    }
    @RequestMapping(value = "/main/drDjxgShl")
    public ModelAndView drDjxgShl(@RequestParam Map<String, Object> params) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/drDjxgShl");
        DfdjxgShl fpsgShl = dfdjxgShlService.getDfdjxgShlById(Long.parseLong(params.get("id").toString()));
        mv.addObject("fpsgShl", fpsgShl);
        return mv;
    }
    /**
     * 更新
     * @param request
     * @param fpsgShl
     * @return
     */
    @RequestMapping(value = "/noc/updateFpsgShl")
    @ResponseBody
    public ResultMap updateFpsgShl(HttpServletRequest request,FpsgShl fpsgShl) {
        ResultMap ret = null;
        try {
            ret = fpsgShlService.updateFpsgShl(fpsgShl);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMap.fail(e.getMessage());
        }
        return ret;
    }
    @RequestMapping(value = "/noc/updateDfzypShl")
    @ResponseBody
    public ResultMap updateDfzypShl(HttpServletRequest request,DfzypShl fpsgShl) {
        ResultMap ret = null;
        try {
            ret = dfzypShlService.updateDfzypShl(fpsgShl);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMap.fail(e.getMessage());
        }
        return ret;
    }
    @RequestMapping(value = "/noc/updateDfdjxgShl")
    @ResponseBody
    public ResultMap updateDfdjxgShl(HttpServletRequest request,DfdjxgShl fpsgShl) {
        ResultMap ret = null;
        try {
            ret = dfdjxgShlService.updateDfdjxgShl(fpsgShl);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMap.fail(e.getMessage());
        }
        return ret;
    }
}
