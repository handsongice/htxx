package com.htxx.controller.highPower;

import com.htxx.constent.Constent;
import com.htxx.controller.BaseController;
import com.htxx.dto.ResultMap;
import com.htxx.entity.Notice;
import com.htxx.entity.PageMsg;
import com.htxx.mapper.NoticeMapper;
import com.htxx.service.HighPowerNoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: ll
 * @Date: 2018-10-24
 * 高可靠电费 -- 通知单管理
 */
@Controller
@Slf4j
public class HighPowerNoticeController extends BaseController {
    @Autowired
    private HighPowerNoticeService highPowerNoticeService;
    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * 通知单列表获取数据
     * @param pageMsg
     * @param notice
     * @return
     */
    @RequestMapping(value = "noc/highPower/notice/list")
    @ResponseBody
    public PageMsg<Notice> list(PageMsg<Notice> pageMsg, Notice notice) {
        return highPowerNoticeService.getlist(pageMsg,notice);
    }

    /**
     * 通知单按条件导出 or 通知单checkbox批量导出
     * @param pageMsg
     * @param notice
     */
    @RequestMapping(value = "noc/highPower/notice/export")
    @ResponseBody
    public void export(PageMsg<Notice> pageMsg, Notice notice) {
        String ids = getRequest().getParameter("ids");
        if(null == ids){
            //通知单按条件导出
            highPowerNoticeService.noticesExport(getRequest(),getResponse(),pageMsg,notice, Constent.SIGN_INT_0);
        }else{
            //通知单 checkbox批量导出/单条导出
            highPowerNoticeService.noticesExport(getRequest(),getResponse(),pageMsg,notice, Constent.SIGN_INT_1);
        }
    }
    /**
     * 通知单删除
     */
    @RequestMapping(value = "noc/highPower/notice/delete")
    @ResponseBody
    public ResultMap delete() {
        try{
            return  highPowerNoticeService.deleteNotices(getRequest());
        }catch (Exception e){
            e.printStackTrace();
            return ResultMap.fail(e.getMessage());
        }
    }

    /**
     * 通知单提交
     * @param pageMsg
     * @param notice
     */
    @RequestMapping(value = "noc/highPower/notice/submit")
    @ResponseBody
    public ResultMap submit(PageMsg<Notice> pageMsg, Notice notice) {
        try {
            String ids = getRequest().getParameter("ids");
            if(null == ids){
                //通知单 按条件提交
                return highPowerNoticeService.noticesSubmit(getRequest(),pageMsg,notice, Constent.SIGN_INT_0);
            }else{
                //通知单 checkbox批量提交/单条提交
                return highPowerNoticeService.noticesSubmit(getRequest(),pageMsg,notice, Constent.SIGN_INT_1);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultMap.fail(e.getMessage());
        }
    }

    /**
     * 加载 添加或者编辑 页面
     * @param notice
     * @param mv
     * @return
     */
    @RequestMapping(value = "noc/highPower/notice/addOrEdit")
    public ModelAndView addOrEdit(Notice notice, ModelAndView mv) {
        mv.setViewName("highPower/noticeAddOrEdit");
        if(null != getRequest().getParameter("id")){
            //编辑
            log.info("编辑");
            String id= getRequest().getParameter("id");
            String[] ids = {id};
            Notice notice1 = noticeMapper.getNoticeListById(ids).get(0);
            log.info(notice1.toString());
            mv.addObject("id",notice1.getId());
            mv.addObject("hm",notice1.getHm());
            mv.addObject("zh",notice1.getZh());
            mv.addObject("gzdh",notice1.getGzdh());
            mv.addObject("je",notice1.getJe());
            mv.addObject("jfbz",notice1.getJfbz());
        }
        return mv;
    }

    /**
     * 添加或者编辑 的保存
     * @param notice
     * @return
     */
    @RequestMapping(value = "noc/highPower/notice/save")
    @ResponseBody
    public ResultMap save(Notice notice) {
        try{
            if(Constent.SIGN_STRING_0.equals(getRequest().getParameter("isAdd"))){
                //编辑
                return highPowerNoticeService.updateNotice(notice);
            }else{
                //添加
                return highPowerNoticeService.addNotice(getRequest(),notice);
            }
        }catch (Exception e){
            // 工作单号 字段唯一 错误判断
            int index =e.getMessage().indexOf("Duplicate");
            if(index >= 0){
                return ResultMap.fail(Constent.DB_UNIQUE_GZDH_FAILURE);
            }else {
                e.printStackTrace();
                return ResultMap.fail(e.getMessage());
            }
        }
    }
}
