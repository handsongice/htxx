package com.htxx.controller;

import com.htxx.entity.Employee;
import com.htxx.entity.Enterprise;
import com.htxx.entity.Fplx;
import com.htxx.entity.Kpjxx;
import com.htxx.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.htxx.constent.Constent.SESSION_EMPLOYEE;
import static com.htxx.constent.Constent.SESSION_ENTERPRISE;

/**
 * @author: zhanghongshun
 * @date: 2018-11-06
 * @description: 所有controller的基类
 */
public abstract class BaseController {

    @Autowired
    private CommonService commonService;

    /**
     * 获取当前登录的企业信息
     *
     * @return
     */
    public Enterprise getLoginEnterprise() {
        Enterprise enterprise = (Enterprise) this.getSession().getAttribute(SESSION_ENTERPRISE);
        if (enterprise != null) {
            return enterprise;
        }
        return null;
    }

    /**
     * 获取当前登录的用户信息
     *
     * @return
     */
    public Employee getLoginEmployee() {
        Employee employee = (Employee) this.getSession().getAttribute(SESSION_EMPLOYEE);
        if (employee != null) {
            return employee;
        }
        return null;
    }

    public List<Fplx> getCurrentFplx() {
        return commonService.getCurrentFplx(this.getLoginEnterprise().getId());
    }

    public List<Kpjxx> getCurrentKpjxx() {
        return commonService.findAllKpj(this.getLoginEmployee().getId(), this.getLoginEnterprise().getId());
    }

    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public HttpSession getSession() {
        return getRequest().getSession();
    }

    public HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

}
