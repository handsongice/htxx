package com.htxx.controller.basic;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htxx.constent.Constent;
import com.htxx.dto.ResultMap;
import com.htxx.entity.Employee;
import com.htxx.entity.Enterprise;
import com.htxx.pojo.LayUiPageParams;
import com.htxx.service.EmployeeService;
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
 * @Date: 2018/11/8 10:32
 */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    /**
     * 列表
     * @return
     */
    @RequestMapping(value = "/main/employee")
    public String employee() {
        return "role/employee";
    }

    /**
     * 新建
     * @return
     */
    @RequestMapping(value = "/main/addEmployee")
    public String addEmployee() {
        return "role/addEmployee";
    }

    /**
     * 修改个人信息
     * @return
     */
    @RequestMapping(value = "/user-information")
    public ModelAndView user_information(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user-information");
        HttpSession session = request.getSession();
        Employee _employee = (Employee) session.getAttribute(Constent.SESSION_EMPLOYEE);
        Employee employee = employeeService.getEmployeeById(_employee.getId());
        mv.addObject("employee", employee);
        return mv;
    }

    /**
     * 修改密码
     * @return
     */
    @RequestMapping(value = "/change-password")
    public ModelAndView change_password(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("change-password");
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute(Constent.SESSION_EMPLOYEE);
        mv.addObject("employee", employee);
        return mv;
    }
    /**
     * 编辑信息
     * @param params
     * @return
     */
    @RequestMapping(value = "/main/editEmployee")
    public ModelAndView editEmployee(@RequestParam Map<String, Object> params) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("role/editEmployee");
        Long _id = Long.parseLong(params.get("id").toString());
        Employee employee = employeeService.getEmployeeById(_id);
        mv.addObject("employee", employee);
        return mv;
    }

    /**
     * 分页数据
     * @param params
     * @param request
     * @return
     */
    @RequestMapping("/noc/pageEmployeeList")
    @ResponseBody
    public Object pageEmployeeList(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
        params.put("enterprise_id",enterprise.getId());
        int pageNum = params.get("page") == null ? 1 : Integer.parseInt(params.get("page").toString());
        int pageSize = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(pageNum, pageSize, true);
        try {
            List<Employee> employees = employeeService.getEmployeeList(params);
            PageInfo<Employee> pageInfo = new PageInfo<>(employees);

            LayUiPageParams<Employee> pageParams = LayUiPageParams.defaultResult(pageInfo.getTotal(), employees);
            return pageParams;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 插入
     * @param request
     * @param employee
     * @return
     */
    @RequestMapping(value = "/noc/insertEmployee")
    @ResponseBody
    public ResultMap insertEmployee(HttpServletRequest request, Employee employee) {
        try {
            HttpSession session = request.getSession();
            Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
            employee.setEnterpriseId(enterprise.getId());
            return employeeService.addEmployee(employee);
        }catch (Exception e) {
            e.printStackTrace();
            return ResultMap.fail(e.getMessage());
        }
    }

    /**
     * 更新
     * @param request
     * @param employee
     * @return
     */
    @RequestMapping(value = "/noc/updateEmployee")
    @ResponseBody
    public ResultMap updateEmployee(HttpServletRequest request, Employee employee) {
        HttpSession session = request.getSession();
        Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
        employee.setEnterpriseId(enterprise.getId());
        ResultMap ret = null;
        try {
            ret = employeeService.updateEmployee(employee);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMap.fail(e.getMessage());
        }
        return ret;
    }

    /**
     * 更新
     * @param request
     * @param employee
     * @return
     */
    @RequestMapping(value = "/noc/updateMyInfo")
    @ResponseBody
    public ResultMap updateMyInfo(HttpServletRequest request, Employee employee) {
        ResultMap ret = null;
        try {
            ret = employeeService.updateMyInfo(employee);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMap.fail(e.getMessage());
        }
        return ret;
    }

    /**
     * 更新密码
     * @param request
     * @param employee
     * @return
     */
    @RequestMapping(value = "/noc/updateMyPassword")
    @ResponseBody
    public ResultMap updateMyPassword(HttpServletRequest request, Employee employee) {
        ResultMap ret = null;
        try {
            ret = employeeService.updateMyPassword(employee);
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
    @RequestMapping(value = "/noc/delEmployee")
    @ResponseBody
    public ResultMap delEmployee(@RequestParam Map<String, Object> params,HttpServletRequest request) {
        return employeeService.delEmployee(Long.parseLong(params.get("id").toString()));
    }
}
