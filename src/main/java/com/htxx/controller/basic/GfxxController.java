package com.htxx.controller.basic;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htxx.constent.Constent;
import com.htxx.dto.ResultMap;
import com.htxx.entity.Enterprise;
import com.htxx.entity.Gfxx;
import com.htxx.pojo.LayUiPageParams;
import com.htxx.service.GfxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/11/9 9:04
 */
@Controller
public class GfxxController {

    @Autowired
    GfxxService gfxxService;

    /**
     * 列表
     * @return
     */
    @RequestMapping(value = "/main/gfxx")
    public String gfxx() {
        return "system/gfxx";
    }

    /**
     * 新建
     * @return
     */
    @RequestMapping(value = "/main/addGfxx")
    public String addGfxx() {
        return "system/addGfxx";
    }

    /**
     * 编辑信息
     * @param params
     * @return
     */
    @RequestMapping(value = "/main/editGfxx")
    public ModelAndView editGfxx(@RequestParam Map<String, Object> params) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/editGfxx");
        Long _id = Long.parseLong(params.get("id").toString());
        Gfxx gfxx = gfxxService.getGfxxById(_id);
        mv.addObject("gfxx", gfxx);
        return mv;
    }
    /**
     * 分页数据
     * @param params
     * @param request
     * @return
     */
    @RequestMapping("/noc/pageGfxxList")
    @ResponseBody
    public Object pageGfxxList(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
        params.put("enterprise_id",enterprise.getId());
        int pageNum = params.get("page") == null ? 1 : Integer.parseInt(params.get("page").toString());
        int pageSize = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(pageNum, pageSize, true);
        try {
            List<Gfxx> gfxxes = gfxxService.getGfxxList(params);
            PageInfo<Gfxx> pageInfo = new PageInfo<>(gfxxes);

            LayUiPageParams<Gfxx> pageParams = LayUiPageParams.defaultResult(pageInfo.getTotal(), gfxxes);
            return pageParams;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 插入
     * @param request
     * @param gfxx
     * @return
     */
    @RequestMapping(value = "/noc/insertGfxx")
    @ResponseBody
    public ResultMap insertGfxx(HttpServletRequest request, Gfxx gfxx) {
        try {
            HttpSession session = request.getSession();
            Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
            gfxx.setEnterpriseId(enterprise.getId());
            return gfxxService.addGfxx(gfxx);
        }catch (Exception e) {
            e.printStackTrace();
            return ResultMap.fail(e.getMessage());
        }
    }

    /**
     * 更新
     * @param request
     * @param gfxx
     * @return
     */
    @RequestMapping(value = "/noc/updateGfxx")
    @ResponseBody
    public ResultMap updateGfxx(HttpServletRequest request, Gfxx gfxx) {
        HttpSession session = request.getSession();
        Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
        gfxx.setEnterpriseId(enterprise.getId());
        ResultMap ret = null;
        try {
            ret = gfxxService.updateGfxx(gfxx);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMap.fail(e.getMessage());
        }
        return ret;
    }

    /**
     * 逻辑删除
     * @param params
     * @param request
     * @return
     */
    @RequestMapping(value = "/noc/delGfxx")
    @ResponseBody
    public ResultMap delGfxx(@RequestParam Map<String, Object> params,HttpServletRequest request) {
        return gfxxService.delGfxx(Long.parseLong(params.get("id").toString()));
    }
}
