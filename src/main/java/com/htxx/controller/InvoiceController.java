package com.htxx.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htxx.common.BaseResultData;
import com.htxx.entity.Fpsg;
import com.htxx.mapper.FpsgMapper;
import com.htxx.pojo.LayUiPageParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private FpsgMapper fpsgMapper;

    @GetMapping("/getSgtzList")
    @ResponseBody
    public Object getSgtzList(@RequestParam Map<String, Object> params) {

        int pageNum = params.get("page") == null ? 1 : Integer.parseInt(params.get("page").toString());
        int pageSize = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(pageNum, pageSize, true);

        List<Fpsg> list = fpsgMapper.selectByParams(params);
        PageInfo<Fpsg> pageInfo = new PageInfo<>(list);

        LayUiPageParams<Fpsg> pageParams = LayUiPageParams.defaultResult(pageInfo.getTotal(), list);
        return pageParams;
    }

    @RequestMapping("/addOrUpdateSgtz")
    @ResponseBody
    public Object addOrUpdateSgtz(Fpsg fpsg) {
        fpsg.setEnterpriseId((long)1);
        if (fpsg.getId() != null && fpsg.getId() != 0) {
            fpsgMapper.updateByPrimaryKeySelective(fpsg);
        } else {
            fpsg.setCreateTime(new Date());
            fpsg.setStatus("0");
            fpsgMapper.insert(fpsg);
        }
        return BaseResultData.resultOK(fpsg);
    }

    /**
     * 根据id获取审核记录
     *
     * @param id
     * @return
     */
    @RequestMapping("/getReviewLogById/{id}")
    public ModelAndView getReviewLogById(@PathVariable Integer id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("invoiceModule/pop_sgsh_log");
        return mv;
    }

    /**
     * 根据id获取审核详情
     *
     * @param id
     * @return
     */
    @RequestMapping("/getReviewDetailById/{id}")
    public ModelAndView getReviewDetailById(@PathVariable Integer id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("invoiceModule/pop_sgsh_detail");
        return mv;
    }

    /**
     * 入库
     *
     * @param id
     * @return
     */
    @RequestMapping("/getStoreDetailById/{id}")
    public ModelAndView getStoreDetailById(@PathVariable Integer id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("invoiceModule/pop_rkff_store");
        return mv;
    }

    /**
     * 分发
     *
     * @param id
     * @return
     */
    @RequestMapping("/getDistributeDetailById/{id}")
    public ModelAndView getDistributeDetailById(@PathVariable Integer id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("invoiceModule/pop_rkff_distribute");
        return mv;
    }

    /**
     * 分发详情
     *
     * @param id
     * @return
     */
    @RequestMapping("/getDistributeInputById/{id}")
    public ModelAndView getDistributeInputById(@PathVariable Integer id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("invoiceModule/pop_rkff_distribute_detail");
        return mv;
    }
}
