package com.htxx.util;

import com.alibaba.fastjson.JSONObject;
import com.htxx.common.BaseResultData;
import com.htxx.entity.InvoiceStock;
import com.htxx.enums.CommonEnum;
import com.htxx.pojo.KpServiceParams;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author: hszhang
 * @date: 2018-11-13
 * @description: 金税盘发票分发工具类
 */
@Slf4j
public class KpServerUtil {

    public static final String kpServerAddr = PropertiesUtil.getKeyValue("kpServer.properties", "kpServer");

    /**
     * 终端发票库存查询接口
     */
    public static final String queryStockAddr = kpServerAddr + "/QueryStock";
    /**
     * 终端发票分配接口
     */
    public static final String distributeInvoiceAddr = kpServerAddr + "/DistributeInvoice";
    /**
     * 终端发票退回接口
     */
    public static final String returnInvoiceAddr = kpServerAddr + "/ReturnInvoice";


    private static String successCode = "0000";


    public static final String reqXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<kpserver> \n" +
            "  <Volumes> \n" +
            "    <Volume> \n" +
            "      <ID>1</ID>  \n" +
            "      <InvKind>0</InvKind>  \n" +
            "      <TypeCode>124565474123</TypeCode>  \n" +
            "      <StartNO>12145654</StartNO>  \n" +
            "      <Count>1000</Count>  \n" +
            "      <Remain>950</Remain>  \n" +
            "      <Machine>0</Machine>  \n" +
            "      <BuyDate>2018-12-12 11:00:09</BuyDate>  \n" +
            "      <Limit>99999</Limit> \n" +
            "    </Volume>  \n" +
            "    <Volume> \n" +
            "      <ID>2</ID>  \n" +
            "      <InvKind>0</InvKind>  \n" +
            "      <TypeCode>124565474123</TypeCode>  \n" +
            "      <StartNO>22145654</StartNO>  \n" +
            "      <Count>1000</Count>  \n" +
            "      <Remain>950</Remain>  \n" +
            "      <Machine>0</Machine>  \n" +
            "      <BuyDate>2018-12-12 11:00:09</BuyDate>  \n" +
            "      <Limit>99999</Limit> \n" +
            "    </Volume>  \n" +
            "    <Volume> \n" +
            "      <ID>3</ID>  \n" +
            "      <InvKind>0</InvKind>  \n" +
            "      <TypeCode>124565474123</TypeCode>  \n" +
            "      <StartNO>32145654</StartNO>  \n" +
            "      <Count>1000</Count>  \n" +
            "      <Remain>950</Remain>  \n" +
            "      <Machine>0</Machine>  \n" +
            "      <BuyDate>2018-12-12 11:00:09</BuyDate>  \n" +
            "      <Limit>99999</Limit> \n" +
            "    </Volume> \n" +
            "  </Volumes>  \n" +
            "  <errorinfo> \n" +
            "    <Code>0000</Code>  \n" +
            "    <Message>success</Message> \n" +
            "  </errorinfo> \n" +
            "</kpserver>";

    /**
     * 发票卷库存查询
     * 输入
     *
     * @param kpjNo
     * @return
     */
    public static String queryStockReqXml(String kpjNo) {
        StringBuffer reqBuf = new StringBuffer();
        reqBuf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        reqBuf.append("<kpserver>");
        reqBuf.append("<Input>");
        reqBuf.append("<Machine>");
        reqBuf.append(kpjNo);
        reqBuf.append("</Machine>");
        reqBuf.append("</Input>");
        reqBuf.append("</kpserver>");
        return reqBuf.toString();
    }

    /**
     * 发票卷库存查询
     * 输出
     *
     * @param repXmlStr
     * @return
     */
    public static BaseResultData queryStockRepXml(String repXmlStr) {
        BaseResultData result = repData(repXmlStr);
        if (!CommonEnum.msg_code_200.getValue().equals(result.getStatus())) {
            return result;
        }
        try {
            List<InvoiceStock> invoiceStocks = new ArrayList<>();
            Element root = (Element) result.getData();
            Element volumes = root.element("Volumes");
            // 通过element对象的elementIterator方法获取迭代器
            Iterator it = volumes.elementIterator();
            // 遍历迭代器
            while (it.hasNext()) {
                Element volume = (Element) it.next();
                InvoiceStock stock = new InvoiceStock();
                stock.setFpjxh(volume.element("ID").getText());
                stock.setFplx(volume.element("InvKind").getText());
                stock.setFpdm(StringUtils.leftPad(volume.element("TypeCode").getText(), 12, "0"));
                stock.setFphmq(StringUtils.leftPad(volume.element("StartNO").getText(), 8, "0"));
                long fphmz = Long.valueOf(volume.element("StartNO").getText()) + Long.valueOf(volume.element("Count").getText()) - 1;
                stock.setFphmq(StringUtils.leftPad(volume.element("StartNO").getText(), 8, "0"));
                stock.setFphmz(StringUtils.leftPad(String.valueOf(fphmz), 8, "0"));
                stock.setFpsl(Long.valueOf(volume.element("Count").getText()));
                stock.setFfsl(Long.valueOf(volume.element("Count").getText()) - Long.valueOf(volume.element("Remain").getText()));
                stock.setSysl(Long.valueOf(volume.element("Remain").getText()));
                stock.setKpjNo(volume.element("Machine").getText());
                stock.setGmrq(DateUtil.parseDate2Times(volume.element("BuyDate").getText()));
                stock.setKpxe(Double.valueOf(volume.element("Limit").getText()));
                invoiceStocks.add(stock);
            }
            if (invoiceStocks == null || invoiceStocks.size() == 0) {
                return BaseResultData.resultError("查询发票卷库存为空！", repXmlStr);
            }
            return BaseResultData.resultOK(invoiceStocks);
        } catch (Exception e) {
            log.info("解析返回结果失败！\n" + e.getMessage());
            return BaseResultData.resultError("解析返回结果失败！\n", repXmlStr);
        }
    }

    /**
     * 生成请求开票服务器的xml
     *
     * @param kpServiceParams
     * @return
     */
    public static String reqXml(KpServiceParams kpServiceParams) {
        StringBuffer reqBuf = new StringBuffer();
        reqBuf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        reqBuf.append("<kpserver>");
        reqBuf.append("<Input>");
        reqBuf.append("<TypeCode>");
        reqBuf.append(kpServiceParams.getTypeCode());
        reqBuf.append("</TypeCode>");
        reqBuf.append("<InvoiceNO>");
        reqBuf.append(kpServiceParams.getInvoiceNO());
        reqBuf.append("</InvoiceNO>");
        reqBuf.append("<InvoiceCount>");
        reqBuf.append(kpServiceParams.getInvoiceCount());
        reqBuf.append("</InvoiceCount>");
        reqBuf.append("<InvoiceType>");
        reqBuf.append(kpServiceParams.getInvoiceType());
        reqBuf.append("</InvoiceType>");
        reqBuf.append("<ClientNO>");
        reqBuf.append(kpServiceParams.getClientNO());
        reqBuf.append("</ClientNO>");
        reqBuf.append("</Input>");
        reqBuf.append("</kpserver>");
        return reqBuf.toString();
    }

    /**
     * 返回开票服务器处理结果
     *
     * @param repXmlStr
     * @return
     */
    public static BaseResultData repData(String repXmlStr) {
        try {
            Document dom = DocumentHelper.parseText(repXmlStr);
            Element root = dom.getRootElement();
            Element errorInfo = root.element("errorinfo");
            String code = errorInfo.element("Code").getText();
            String msg = errorInfo.element("Message").getText();
            if (successCode.equals(code)) {
                return BaseResultData.resultOK(root);
            } else {
                return BaseResultData.resultError(msg, repXmlStr);
            }
        } catch (Exception e) {
            log.info("解析返回结果失败！\n" + e.getMessage());
            return BaseResultData.resultError("解析返回结果失败！\n", repXmlStr);
        }
    }

    public static void main(String[] args) {
        String xmlStr = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<kpserver> \n" +
                "  <Volumes> \n" +
                "    <Volume> \n" +
                "      <ID>1</ID>  \n" +
                "      <InvKind>0</InvKind>  \n" +
                "      <TypeCode>124565474123</TypeCode>  \n" +
                "      <StartNO>12145654</StartNO>  \n" +
                "      <Count>1000</Count>  \n" +
                "      <Remain>950</Remain>  \n" +
                "      <Machine>0</Machine>  \n" +
                "      <BuyDate>2018-12-12 11:00:09</BuyDate>  \n" +
                "      <Limit>99999</Limit> \n" +
                "    </Volume>  \n" +
                "    <Volume> \n" +
                "      <ID>2</ID>  \n" +
                "      <InvKind>0</InvKind>  \n" +
                "      <TypeCode>124565474123</TypeCode>  \n" +
                "      <StartNO>22145654</StartNO>  \n" +
                "      <Count>1000</Count>  \n" +
                "      <Remain>950</Remain>  \n" +
                "      <Machine>0</Machine>  \n" +
                "      <BuyDate>2018-12-12 11:00:09</BuyDate>  \n" +
                "      <Limit>99999</Limit> \n" +
                "    </Volume>  \n" +
                "    <Volume> \n" +
                "      <ID>3</ID>  \n" +
                "      <InvKind>0</InvKind>  \n" +
                "      <TypeCode>124565474123</TypeCode>  \n" +
                "      <StartNO>32145654</StartNO>  \n" +
                "      <Count>1000</Count>  \n" +
                "      <Remain>950</Remain>  \n" +
                "      <Machine>0</Machine>  \n" +
                "      <BuyDate>2018-12-12 11:00:09</BuyDate>  \n" +
                "      <Limit>99999</Limit> \n" +
                "    </Volume> \n" +
                "  </Volumes>  \n" +
                "  <errorinfo> \n" +
                "    <Code>0000</Code>  \n" +
                "    <Message>success</Message> \n" +
                "  </errorinfo> \n" +
                "</kpserver>";

        System.out.println(JSONObject.toJSONString(queryStockRepXml(xmlStr)));
    }

}
