package com.htxx.controller.dianFeiShouRu;

import com.htxx.common.BaseResultData;
import com.htxx.common.LayUiResultData;
import com.htxx.constent.Constent;
import com.htxx.entity.DlGfxx;
import com.htxx.entity.Employee;
import com.htxx.entity.Enterprise;
import com.htxx.mapper.DlGfxxMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @Author: SaHongzhi
 * @Date: 2018-10-23 11:27
 */
@Controller
@Slf4j
public class ziYouPiaoBaiMingDanController {

    @Autowired
    DlGfxxMapper dlGfxxMapper;

    @RequestMapping(value = "/main/zypbmd/foToZiYouPiaoBaiMingDan")
    public String foToWenTiDanJuGuanLi() {
        return "dianFeiShouRu/ziYouPiaoBaiMingDan";
    }

    //根据条件查询所有购方信息
    @RequestMapping(value = "/noc/zypbmd/listAllOrder")
    @ResponseBody
    public LayUiResultData listAllOrder(@RequestParam(value = "gfmc", required = false) String gfmc,
                                        @RequestParam(value = "gfsh", required = false) String gfsh,
                                        @RequestParam(value = "dzdh", required = false) String dzdh,
                                        @RequestParam(value = "yhzh", required = false) String yhzh,
                                        @RequestParam(value = "zyp_white_list", required = false) String zyp_white_list,
                                        HttpSession session) {
        DlGfxx dlGfxx = new DlGfxx();
        if (StringUtils.isNotBlank(zyp_white_list)) {
            dlGfxx.setZypWhiteList(Byte.valueOf(zyp_white_list));
        }
        dlGfxx.setYhzh(yhzh);
        dlGfxx.setGfsh(gfsh);
        dlGfxx.setGfmc(gfmc);
        dlGfxx.setDzdh(dzdh);
        //获取企业ID
        Employee employee = (Employee) session.getAttribute(Constent.SESSION_EMPLOYEE);
        dlGfxx.setEnterpriseId(employee.getEnterpriseId());
        List<DlGfxx> dlGfxxes = dlGfxxMapper.selectByCondition(dlGfxx);
        return LayUiResultData.resultOK(dlGfxxes);
    }

    /**
     * 展示购方信息
     *
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value = "/noc/zypbmd/showCustomerInfo")
    @ResponseBody
    public ModelAndView showCustomerInfo(@RequestParam(value = "id") Long id,
                                         HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/dianFeiShouRu/gfInfoDetail.html");
        DlGfxx dlGfxx = new DlGfxx();
        if (id != 0) {
            dlGfxx = dlGfxxMapper.selectByPrimaryKey(id);
        }
        modelAndView.addObject("dlGfxx", dlGfxx);
        return modelAndView;
    }

    /**
     * 添加或者编辑 的保存
     *
     * @param request
     * @param notice
     * @return
     */
    @RequestMapping(value = "/noc/zypbmd/saveOrEditGfInfo")
    @ResponseBody
    public BaseResultData saveOrEditWenTiDanJu(@RequestParam(value = "id", required = false) String id,
                                               @RequestParam(value = "gfmc") String gfmc,
                                               @RequestParam(value = "gfsh") String gfsh,
                                               @RequestParam(value = "dzdh") String dzdh,
                                               @RequestParam(value = "yhzh") String yhzh,
                                               @RequestParam(value = "zyp_white_list") byte zyp_white_list,
                                               HttpSession session) {


        DlGfxx dlGfxx = new DlGfxx();
        int result = 0;
        if (StringUtils.isBlank(id)) {
            //增加
            dlGfxx.setDzdh(dzdh);
            dlGfxx.setGfmc(gfmc);
            dlGfxx.setGfsh(gfsh);
            dlGfxx.setYhzh(yhzh);
            dlGfxx.setZypWhiteList(zyp_white_list);
            dlGfxx.setCreateTime(new Date());
            Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
            dlGfxx.setEnterpriseId(enterprise.getId());
            result = dlGfxxMapper.insertSelective(dlGfxx);

            if (result > 0) {
                return BaseResultData.result(Constent.DB_INSERT_SUCCESS, "200", null);
            } else {
                return BaseResultData.result(Constent.DB_INSERT_FAILURE, "400", null);
            }
        } else {
            //修改
            dlGfxx.setId(Long.valueOf(id));
            dlGfxx.setDzdh(dzdh);
            dlGfxx.setGfmc(gfmc);
            dlGfxx.setGfsh(gfsh);
            dlGfxx.setYhzh(yhzh);
            dlGfxx.setZypWhiteList(zyp_white_list);
            result = dlGfxxMapper.updateByPrimaryKeySelective(dlGfxx);
            if (result > 0) {
                return BaseResultData.result(Constent.DB_UPDATE_SUCCESS, "200", null);
            } else {
                return BaseResultData.result(Constent.DB_UPDATE_FAILURE, "400", null);
            }
        }


    }

    @RequestMapping(value = "/noc/zypbmd/batchDelete")
    @ResponseBody
    public BaseResultData batchDelete(@RequestParam(value = "ids", required = false) String ids,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {
        //通知单checkbox批量导出
        String[] id = ids.split(",");
        log.info("*******删除的客户信息有*******:" + ids);
        dlGfxxMapper.batchDelete(id);
        return BaseResultData.result(Constent.DB_DELETE_SUCCESS, "200", null);
    }


}
