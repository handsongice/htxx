package com.htxx.controller.basic;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htxx.common.BaseResultData;
import com.htxx.constent.Constent;
import com.htxx.dto.ResultMap;
import com.htxx.entity.Action;
import com.htxx.entity.Employee;
import com.htxx.entity.Enterprise;
import com.htxx.entity.Role;
import com.htxx.pojo.LayUiPageParams;
import com.htxx.service.ActionService;
import com.htxx.service.EmployeeService;
import com.htxx.service.EnterpriseService;
import com.htxx.service.RoleService;
import com.htxx.util.MD5;
import org.apache.commons.lang3.StringUtils;
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
 * @Date: 2018/10/26 15:30
 */
@Controller
public class RoleController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ActionService actionService;

    @Autowired
    EnterpriseService enterpriseService;

    @Autowired
    RoleService roleService;

    /**
     * 登录
     * @param params
     * @param request
     * @return
     */
    @RequestMapping("dologin")
    @ResponseBody
    public Object doLogin(@RequestParam Map<String, Object> params, HttpServletRequest request) {

        //输入检查
        String username = params.get("username").toString();
        if (null == username || "".equalsIgnoreCase(username)) {
            return BaseResultData.resultError("1", null);
        }
        String password = params.get("password").toString();
        if (null == password || "".equalsIgnoreCase(password)) {
            return BaseResultData.resultError("2", null);
        }
        String code = params.get("code").toString();
        if (null == code || "".equalsIgnoreCase(code)) {
            return BaseResultData.resultError("3", null);
        }
        String sessionCode = request.getSession().getAttribute("code").toString();
        if(null == sessionCode || "".equalsIgnoreCase(sessionCode)) {
            return BaseResultData.resultError("4", null);
        }
        //验证码校验
        if(!code.equalsIgnoreCase(sessionCode)) {
            return BaseResultData.resultError("4", null);
        }
        Employee employee;
        try {
            //用户名验证
            employee = employeeService.getEmployee(username);
            if(null == employee || StringUtils.isEmpty(employee.getLoginCode())) {
                return BaseResultData.resultError("5", null);
            }
            //密码验证
            if(!MD5.md5(password).equals(employee.getPassword())) {
                return BaseResultData.resultError("6", null);
            }
            //企业验证
            Enterprise enterprise = enterpriseService.getEnterpriseById(employee.getEnterpriseId());
            if(null == enterprise || enterprise.getIsDel().equals(1)) {
                return BaseResultData.resultError("7", null);
            }
            //登录成功
            HttpSession session = request.getSession();
            session.setAttribute(Constent.SESSION_EMPLOYEE, employee);
            session.setAttribute(Constent.SESSION_ENTERPRISE, enterprise);
        }catch (Exception e) {
            return BaseResultData.resultError("0", null);
        }
        return BaseResultData.resultOK(employee);
    }

    /**
     * 登出
     * @param params
     * @param request
     * @return
     */
    @RequestMapping("dologout")
    @ResponseBody
    public Object doLogout(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(Constent.SESSION_EMPLOYEE);
        session.invalidate();
        return BaseResultData.resultOK(null);
    }

    /**
     * 左侧导航
     * @param request
     * @return
     */
    @RequestMapping("left_menu")
    @ResponseBody
    public Object leftMenu (HttpServletRequest request) {
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute(Constent.SESSION_EMPLOYEE);
        if(null == employee || StringUtils.isEmpty(employee.getLoginCode())) {
            return BaseResultData.resultError("0", null);
        }
        try {
            List<Action> actions = actionService.leftMenus(employee);
            return BaseResultData.resultOK(actions);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResultData.resultError(e.getMessage(), null);
        }
    }

    /**
     * 编辑信息
     * @param params
     * @return
     */
    @RequestMapping(value = "/main/editRole")
    public ModelAndView editRole(@RequestParam Map<String, Object> params) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("role/editRole");
        Long _id = Long.parseLong(params.get("id").toString());
        Role role = roleService.getRoleById(_id);
        mv.addObject("role", role);
        return mv;
    }

    /**
     * 列表
     * @return
     */
    @RequestMapping(value = "/main/role")
    public String role() {
        return "role/role";
    }

    /**
     * 新建
     * @return
     */
    @RequestMapping(value = "/main/addRole")
    public String addRole() {
        return "role/addRole";
    }

    /**
     * 分页数据
     * @param params
     * @param request
     * @return
     */
    @RequestMapping("/noc/pageRoleList")
    @ResponseBody
    public Object pageRoleList(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
        params.put("enterprise_id",enterprise.getId());
        int pageNum = params.get("page") == null ? 1 : Integer.parseInt(params.get("page").toString());
        int pageSize = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(pageNum, pageSize, true);
        try {
            List<Role> roles = roleService.getRoleList(params);
            PageInfo<Role> pageInfo = new PageInfo<>(roles);

            LayUiPageParams<Role> pageParams = LayUiPageParams.defaultResult(pageInfo.getTotal(), roles);
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
    @RequestMapping("/noc/allRoleList")
    @ResponseBody
    public Object allRoleList(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        try {
            List<Role> roles = roleService.getRoleList(params);
            return roles;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 插入
     * @param request
     * @param role
     * @return
     */
    @RequestMapping(value = "/noc/insertRole")
    @ResponseBody
    public ResultMap insertRole(HttpServletRequest request, Role role) {
        try {
            HttpSession session = request.getSession();
            Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
            role.setEnterpriseId(enterprise.getId());
            return roleService.addRole(role);
        }catch (Exception e) {
            e.printStackTrace();
            return ResultMap.fail(e.getMessage());
        }
    }

    /**
     * 更新
     * @param request
     * @param role
     * @return
     */
    @RequestMapping(value = "/noc/updateRole")
    @ResponseBody
    public ResultMap updateRole(HttpServletRequest request, Role role) {
        HttpSession session = request.getSession();
        Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
        role.setEnterpriseId(enterprise.getId());
        ResultMap ret = null;
        try {
            ret = roleService.updateRole(role);
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
    @RequestMapping(value = "/noc/delRole")
    @ResponseBody
    public ResultMap delRole(@RequestParam Map<String, Object> params,HttpServletRequest request) {
        return roleService.delRole(Long.parseLong(params.get("id").toString()));
    }
}
