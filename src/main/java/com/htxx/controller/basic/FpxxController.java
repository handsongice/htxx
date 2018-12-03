package com.htxx.controller.basic;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htxx.constent.Constent;
import com.htxx.dto.ResultMap;
import com.htxx.entity.Enterprise;
import com.htxx.entity.Fplx;
import com.htxx.entity.Fpxe;
import com.htxx.pojo.LayUiPageParams;
import com.htxx.service.FplxService;
import com.htxx.service.FpxeService;
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
 * @Date: 2018/11/9 13:17
 */
@Controller
public class FpxxController {

    @Autowired
    FplxService fplxService;

    @Autowired
    FpxeService fpxeService;

    /**
     * 列表
     * @return
     */
    @RequestMapping(value = "/main/fpxx")
    public String fpxx() {
        return "system/fpxx";
    }

    /**
     * 新建发票类型
     * @return
     */
    @RequestMapping(value = "/main/addFplx")
    public String addFplx() {
        return "system/addFplx";
    }

    /**
     * 编辑发票类型
     * @param params
     * @return
     */
    @RequestMapping(value = "/main/editFplx")
    public ModelAndView editFplx(@RequestParam Map<String, Object> params) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/editFplx");
        Long _id = Long.parseLong(params.get("id").toString());
        Fplx fplx = fplxService.getFplxById(_id);
        mv.addObject("fplx", fplx);
        return mv;
    }

    /**
     * 分页数据
     * @param params
     * @param request
     * @return
     */
    @RequestMapping("/noc/pageFplxList")
    @ResponseBody
    public Object pageFplxList(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
        params.put("enterprise_id",enterprise.getId());
        int pageNum = params.get("page") == null ? 1 : Integer.parseInt(params.get("page").toString());
        int pageSize = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(pageNum, pageSize, true);
        try {
            List<Fplx> fplxes = fplxService.getFplxList(params);
            PageInfo<Fplx> pageInfo = new PageInfo<>(fplxes);

            LayUiPageParams<Fplx> pageParams = LayUiPageParams.defaultResult(pageInfo.getTotal(), fplxes);
            return pageParams;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 插入
     * @param request
     * @param fplx
     * @return
     */
    @RequestMapping(value = "/noc/insertFplx")
    @ResponseBody
    public ResultMap insertFplx(HttpServletRequest request, Fplx fplx) {
        try {
            HttpSession session = request.getSession();
            Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
            fplx.setEnterpriseId(enterprise.getId());
            return fplxService.addFplx(fplx);
        }catch (Exception e) {
            e.printStackTrace();
            return ResultMap.fail(e.getMessage());
        }
    }
    /**
     * 更新
     * @param request
     * @param fplx
     * @return
     */
    @RequestMapping(value = "/noc/updateFplx")
    @ResponseBody
    public ResultMap updateFplx(HttpServletRequest request, Fplx fplx) {
        HttpSession session = request.getSession();
        Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
        fplx.setEnterpriseId(enterprise.getId());
        ResultMap ret = null;
        try {
            ret = fplxService.updateFplx(fplx);
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
    @RequestMapping(value = "/noc/delFplx")
    @ResponseBody
    public ResultMap delFplx(@RequestParam Map<String, Object> params,HttpServletRequest request) {
        return fplxService.delFplx(Long.parseLong(params.get("id").toString()));
    }

    /**
     * 分页数据
     * @param params
     * @param request
     * @return
     */
    @RequestMapping("/noc/pageFpxeList")
    @ResponseBody
    public Object pageFpxeList(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
        params.put("enterprise_id",enterprise.getId());
        int pageNum = params.get("page") == null ? 1 : Integer.parseInt(params.get("page").toString());
        int pageSize = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(pageNum, pageSize, true);
        try {
            List<Fpxe> fpxes = fpxeService.getFpxeList(params);
            PageInfo<Fpxe> pageInfo = new PageInfo<>(fpxes);

            LayUiPageParams<Fpxe> pageParams = LayUiPageParams.defaultResult(pageInfo.getTotal(), fpxes);
            return pageParams;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 初始化
     * @param request
     * @param fpxe
     * @return
     */
    @RequestMapping(value = "/noc/initFpxe")
    @ResponseBody
    public ResultMap initFpxe(HttpServletRequest request, Fpxe fpxe) {
        try {
            HttpSession session = request.getSession();
            Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
            fpxe.setEnterpriseId(enterprise.getId());
            return fpxeService.addFpxe(fpxe);
        }catch (Exception e) {
            e.printStackTrace();
            return ResultMap.fail(e.getMessage());
        }
    }

    /**
     * 更新别名
     * @param request
     * @param fpxe
     * @return
     */
    @RequestMapping(value = "/noc/updateFpxe")
    @ResponseBody
    public ResultMap updateFpxe(HttpServletRequest request, Fpxe fpxe) {
        return fpxeService.updateFpxe(fpxe);
    }
}
