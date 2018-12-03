package com.htxx.controller.dianFeiShouRu;

import com.htxx.common.BaseResultData;
import com.htxx.common.LayUiResultData;
import com.htxx.entity.DlDianfei;
import com.htxx.entity.InvoiceUploadResult;
import com.htxx.mapper.DlDianfeiMapper;
import com.htxx.service.DanJuDaoRuService;
import com.htxx.util.ReadTxt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: SaHongzhi
 * @Date: 2018-10-23 11:27
 */
@Controller
public class danJuDaoRuController {

    @Autowired
    DanJuDaoRuService danJuDaoRuService;
    @Autowired
    DlDianfeiMapper dianfeiMapper;

    @RequestMapping(value = "/main/djdr/foToDanJuDaoRu")
    public String foToDanJuDaoRu() {
        return "dianFeiShouRu/danJuDaoRu";
    }

//    @RequestMapping(value = "initTable")
//    @ResponseBody
//    public LayUiResultData initTable(@RequestParam(value = "searchDate") String searchDate) {
//        //申明集合
//        List<DlDianfei> dianfeis=new ArrayList<>();
//        Map map=new HashMap();
//        map.put("searchDate",searchDate);
//        dianfeis=dianfeiMapper.selectByCondition(map);
//        return LayUiResultData.resultOK(dianfeis);
//    }

    @RequestMapping(value = "/noc/djdr/uploadFile")
    @ResponseBody
    public LayUiResultData uploadFile(@RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
        System.out.println(file);
        List list=new ArrayList();
        InvoiceUploadResult invoiceUploadResult=danJuDaoRuService.uploadFile(file,session);
        list.add(invoiceUploadResult);
        return LayUiResultData.resultOK(list);
    }
//    @RequestMapping(value = "fotoWenTiDjDetail/id/{id}")
//    public ModelAndView fotoWenTiDjDetail(@PathVariable(value = "id") String id) {
//        //申明集合
//        System.out.println("1"+id);
//        ModelAndView mav=new ModelAndView();
//        mav.setViewName("dianFeiShouRu/wenTiDjDetail3.html");
//        mav.addObject("id",id);
//        return mav;
//    }

//    @RequestMapping(value = "listAllTroubleOrder")
//    @ResponseBody
//    public LayUiResultData listAllTroubleOrder(@RequestParam(value = "id") String id) {
//        System.out.println("2"+id);
//        //申明集合
//        List<TroubleOrderBean> allUser=new ArrayList<>();
//        //循环存储数据
//        for (int i = 0; i < 7; i++) {
//            TroubleOrderBean u=new TroubleOrderBean(i,"1000"+i, "海尔", "税号00002"+i,100+i+"",1650+i+"","10","20");
//            allUser.add(u);
//        }
//        return LayUiResultData.resultOK(allUser);
//    }

}
