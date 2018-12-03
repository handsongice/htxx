package com.htxx.controller.basic;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htxx.constent.Constent;
import com.htxx.dto.ResultMap;
import com.htxx.entity.Department;
import com.htxx.entity.Enterprise;
import com.htxx.entity.Kpjxx;
import com.htxx.pojo.LayUiPageParams;
import com.htxx.service.DepartmentService;
import com.htxx.service.KpjxxService;
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
 * @Date: 2018/11/6 9:59
 */
@Controller
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @Autowired
    KpjxxService kpjxxService;

    /**
     * 列表
     * @return
     */
    @RequestMapping(value = "/main/department")
    public String department() {
        return "role/department";
    }

    /**
     * 新建
     * @return
     */
    @RequestMapping(value = "/main/addDepartment")
    public String addDepartment() {
        return "role/addDepartment";
    }

    /**
     * 编辑信息
     * @param params
     * @return
     */
    @RequestMapping(value = "/main/editDepartment")
    public ModelAndView editDepartment(@RequestParam Map<String, Object> params) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("role/editDepartment");
        Long _id = Long.parseLong(params.get("id").toString());
        Department department = departmentService.getDepartmentById(_id);
        mv.addObject("department", department);
        return mv;
    }

    /**
     * 分页数据
     * @param params
     * @param request
     * @return
     */
    @RequestMapping("/noc/pageDepartmentList")
    @ResponseBody
    public Object pageDepartmentList(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
        params.put("enterprise_id",enterprise.getId());
        int pageNum = params.get("page") == null ? 1 : Integer.parseInt(params.get("page").toString());
        int pageSize = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(pageNum, pageSize, true);
        try {
            List<Department> departments = departmentService.getDepartmentList(params);
            PageInfo<Department> pageInfo = new PageInfo<>(departments);

            LayUiPageParams<Department> pageParams = LayUiPageParams.defaultResult(pageInfo.getTotal(), departments);
            return pageParams;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 菜单树
     * @param params
     * @param request
     * @return
     */
    @RequestMapping("/noc/allDepartmentList")
    @ResponseBody
    public Object allDepartmentList(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
            params.put("enterprise_id",enterprise.getId());
            List<Department> departments = departmentService.getDepartmentList(params);
            for (Department department: departments) {
                if(null != params.get("parent_id") && !"".equals(params.get("parent_id").toString())
                        && department.getId() == Long.parseLong(params.get("parent_id").toString())) {
                    department.setChecked(true);
                }
            }
            return departments;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 菜单树
     * @param params
     * @param request
     * @return
     */
    @RequestMapping("/noc/allKpjList")
    @ResponseBody
    public Object allKpjList(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
            params.put("enterprise_id",enterprise.getId());
            List<Kpjxx> kpjxxes = kpjxxService.getKpjxxList(params);
            return kpjxxes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 插入
     * @param request
     * @param department
     * @return
     */
    @RequestMapping(value = "/noc/insertDepartment")
    @ResponseBody
    public ResultMap insertDepartment(HttpServletRequest request, Department department) {
        try {
            HttpSession session = request.getSession();
            Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
            department.setEnterpriseId(enterprise.getId());
            return departmentService.addDepartment(department);
        }catch (Exception e) {
            e.printStackTrace();
            return ResultMap.fail(e.getMessage());
        }
    }
    /**
     * 更新
     * @param request
     * @param department
     * @return
     */
    @RequestMapping(value = "/noc/updateDepartment")
    @ResponseBody
    public ResultMap updateDepartment(HttpServletRequest request, Department department) {
        HttpSession session = request.getSession();
        Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
        department.setEnterpriseId(enterprise.getId());
        ResultMap ret = null;
        try {
            ret = departmentService.updateDepartment(department);
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
    @RequestMapping(value = "/noc/delDepartment")
    @ResponseBody
    public ResultMap delDepartment(@RequestParam Map<String, Object> params,HttpServletRequest request) {
        return departmentService.delDepartment(Long.parseLong(params.get("id").toString()));
    }
}
