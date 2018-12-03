package com.htxx.controller.highPower;

import com.htxx.constent.Constent;
import com.htxx.controller.BaseController;
import com.htxx.dto.ResultMap;
import com.htxx.entity.PageMsg;
import com.htxx.entity.Ykfp;
import com.htxx.mapper.YkfpMapper;
import com.htxx.service.HighPowerYkfpMngService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by ll on 2018-11-01
 * 高可靠电费--已开发票
 */
@Controller
@Slf4j
public class YkfpMngController extends BaseController {
    @Autowired
    private HighPowerYkfpMngService highPowerYkfpMngService;
    @Autowired
    private YkfpMapper ykfpMapper;

    /**
     * 高可靠  已开发票  main 列表
     * @param pageMsg
     * @param ykfp
     * @return
     */
    @RequestMapping(value = "noc/highPower/ykfpMng/list")
    @ResponseBody
    public PageMsg<Ykfp> list(PageMsg<Ykfp> pageMsg, Ykfp ykfp) {
        //设置业务类型 高可靠电费  选取所有高可靠电费 已开发票
        ykfp.setYwlx(Constent.SIGN_STRING_2);
        return highPowerYkfpMngService.getList(pageMsg, ykfp);
    }

    /**
     * 已开发票按条件导出 or 已开发票checkbox批量导出
     * @param pageMsg
     * @param ykfp
     */
    @RequestMapping(value = "noc/highPower/ykfpMng/export")
    @ResponseBody
    public void export(PageMsg<Ykfp> pageMsg, Ykfp ykfp){
        //获取单据号集合
        String djhs = getRequest().getParameter("djhs");
        //设置业务类型 高可靠电费  选取所有高可靠电费 已开发票
        ykfp.setYwlx(Constent.SIGN_STRING_2);
        if(null == djhs){
            //已开发票按条件导出
            highPowerYkfpMngService.ykfpExport(getRequest(),getResponse(),pageMsg,ykfp,Constent.SIGN_INT_0);
        }else {
            //已开发票 checkbox批量导出/单条导出
            highPowerYkfpMngService.ykfpExport(getRequest(),getResponse(),pageMsg,ykfp,Constent.SIGN_INT_1);
        }
    }

    /**
     * 发票明细展示页面
     * @param mv
     * @return
     */
    @RequestMapping(value = "noc/highPower/ykfpMng/invoiceDel")
    public ModelAndView invoiceDel(ModelAndView mv){
        String djh = getRequest().getParameter("djh");
        String[] djhs = {djh};
        Ykfp ykfp = ykfpMapper.getYkfpMainAndDelListByDjh(djhs).get(0);
        mv.setViewName("highPower/invoiceDetail");
        mv.addObject("ykfp",ykfp);
        return mv;
    }

    /**
     * 发票作废  更新数据库 开票类型
     * @return
     */
    @RequestMapping(value = "noc/highPower/ykfpMng/invoiceCancel")
    @ResponseBody
    public ResultMap invoiceCancel(){
        try{
            String fpdm = getRequest().getParameter("fpdm");
            String fphm = getRequest().getParameter("fphm");
            //原开票类型
            int ykplx = Integer.parseInt(getRequest().getParameter("ykplx"));
            log.info("fpdm:"+fpdm);
            log.info("fpdm:"+fphm);
            log.info("ykplx:"+ykplx);
            int result = 0;
            if(Constent.SIGN_INT_2 == ykplx){
                //红票作废
                log.info("红票作废");
                result = ykfpMapper.updateKplxByFpdmhm(fpdm,fphm,Constent.SIGN_INT_5);
                //更新被冲红蓝票  被冲红-2  --> 正常-1
                String yfpdm = getRequest().getParameter("yfpdm");
                String yfphm = getRequest().getParameter("yfphm");
                result = ykfpMapper.updateKplxByFpdmhm(yfpdm,yfphm,Constent.SIGN_INT_1);
            }else{
                //蓝票作废
                log.info("蓝票作废");
                result = ykfpMapper.updateKplxByFpdmhm(fpdm,fphm,Constent.SIGN_INT_4);
            }
            if(result>0){
                return ResultMap.success(Constent.DB_UPDATE_KPLX_SUCCESS);
            }else{
                return ResultMap.fail(Constent.DB_UPDATE_KPLX_FAILURE);
            }
        }catch (Exception e){
            log.error("updateKplx捕获异常！"+e.toString());
            return ResultMap.fail(Constent.DB_UPDATE_KPLX_FAILURE+e.getMessage());
        }
    }

    /**
     * 发票冲红 获取该发票对应蓝票所有信息
     * @return
     */
    @RequestMapping(value = "noc/highPower/ykfpMng/getFpxx")
    @ResponseBody
    public ResultMap getFpxx(){
        try {
            String djh = getRequest().getParameter("djh");
            String[] djhs = {djh};
            List<Ykfp> list = ykfpMapper.getYkfpMainAndDelListByDjh(djhs);
            if(list.size() <= 0){
                return ResultMap.fail(Constent.DB_QUERY_YKFP_FAILURE);
            }else{
                Ykfp ykfp = list.get(0);
                return ResultMap.success(ykfp);
            }
        }catch (Exception e){
            log.error("getFpxx捕获异常！"+e.getMessage());
            return ResultMap.fail(Constent.DB_QUERY_YKFP_FAILURE);
        }
    }

    /**
     * 获取打印数据
     * @param pageMsg
     * @param ykfp
     * @return
     */
    @RequestMapping(value = "noc/highPower/ykfpMng/getPrintData")
    @ResponseBody
    public ResultMap getPrintData(PageMsg<Ykfp> pageMsg, Ykfp ykfp){
        //设置业务类型 高可靠电费  选取所有高可靠电费 已开发票
        ykfp.setYwlx(Constent.SIGN_STRING_2);
        pageMsg.setEntity(ykfp);
        //带条件查询
        List<Ykfp> all = ykfpMapper.getYkfpMainList(pageMsg);

        return ResultMap.success(all);
    }
}
