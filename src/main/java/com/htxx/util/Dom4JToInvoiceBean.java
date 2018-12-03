package com.htxx.util;

import com.htxx.entity.Ykfp;
import com.htxx.entity.YkfpDel;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ll on 2018-11-16
 * Info XML 解析 为 发票bean
 */
@Slf4j
public class Dom4JToInvoiceBean {
    public Ykfp getYkfpBean(String XMLStr) {
        Ykfp ykfp = new Ykfp();
        try {
            Document document = DocumentHelper.parseText(XMLStr);
            // 通过document对象获取根节点
            Element Info = document.getRootElement();
            ykfp.setGfmc(Info.elementText("GFMC"));
            ykfp.setGfsh(Info.elementText("GFSH"));
            ykfp.setGfdzdh(Info.elementText("GFDZDH"));
            ykfp.setGfyhzh(Info.elementText("GFYHZH"));
            ykfp.setXfmc(Info.elementText("XFMC"));
            ykfp.setXfsh(Info.elementText("XFSH"));
            ykfp.setXfdzdh(Info.elementText("XFDZDH"));
            ykfp.setXfyhzh(Info.elementText("XFYHZH"));
            ykfp.setBz(Info.elementText("BZ"));
            ykfp.setKpr(Info.elementText("KPR"));
            ykfp.setSkr(Info.elementText("SKR"));
            ykfp.setFhr(Info.elementText("FHR"));
//            ykfp.setK(Info.elementText("KPSXH"));
//            ykfp.setGfmc(Info.elementText("DZSYH"));
            ykfp.setQdbz(Integer.parseInt(Info.elementText("QDBZ")));
            ykfp.setBmbbh(Info.elementText("BMBBBH"));
            Element MXS = Info.element("MXS");
            List<YkfpDel> ykfpDelList = new ArrayList<>();
            // 通过element对象的elementIterator方法获取迭代器
            Iterator it = MXS.elementIterator();
            // 遍历迭代器，获取根节点中的信息
            while (it.hasNext()) {
                YkfpDel ykfpDel = new YkfpDel();
                Element MX = (Element) it.next();
                // 获取book的属性名以及 属性值
                ykfpDel.setXmmc(MX.elementText("SPMC"));
                ykfpDel.setGgxh(MX.elementText("GGXH"));
                ykfpDel.setJldw(MX.elementText("JLDW"));
                ykfpDel.setXmsl(Double.parseDouble(MX.elementText("SL")));
                ykfpDel.setHsdj(Double.parseDouble(MX.elementText("DJ")));
                ykfpDel.setHsbz(Integer.parseInt(MX.elementText("HSJBZ")));
                ykfpDel.setFphxz(Integer.parseInt(MX.elementText("FPHXZ")));
                ykfpDel.setXmje(Double.parseDouble(MX.elementText("JE"))); //不含税金额
                ykfpDel.setHsje(Double.parseDouble(MX.elementText("JE"))+Double.parseDouble(MX.elementText("SE"))); //含税金额
                ykfpDel.setSl(Double.parseDouble(MX.elementText("SLV")));
                ykfpDel.setSe(Double.parseDouble(MX.elementText("SE")));
                ykfpDel.setShflbm(MX.elementText("FLBM"));
                if("" == MX.elementText("LSLVBS")){
                }else {
                    ykfpDel.setLslbs(Integer.parseInt(MX.elementText("LSLVBS")));
                }
                ykfpDel.setYhzcbs(Integer.parseInt(MX.elementText("XSYH")));
                ykfpDel.setYhzcnr(MX.elementText("YHSM"));
                ykfpDelList.add(ykfpDel);
            }
            ykfp.setYkfpDelList(ykfpDelList);
            return ykfp;
        } catch (Exception e) {
            log.error("异常！"+e);
            return null;
        }

    }
}
