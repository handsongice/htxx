package com.htxx.service.impl;


import com.htxx.constent.Constent;
import com.htxx.entity.*;
import com.htxx.mapper.DlDianfeiDetailMapper;
import com.htxx.mapper.DlDianfeiMapper;
import com.htxx.service.DanJuDaoRuService;
import com.htxx.util.DateUtil;
import com.htxx.util.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
@Slf4j
public class DanJuDaoRuServiceImpl implements DanJuDaoRuService {
    public static final String GBK_CHARSET = "GBK";
    public static final String DATA_TYPE_2 = "2";
    public static final String DATA_TYPE_1 = "1";
    public static final String DATA_TYPE_0 = "0";
    public static final String IS_DEL_0 = "0";//删除状态 0   未删除
    public static final String IS_DEL_1 = "1";//删除状态 1  已删除

    @Autowired
    DlDianfeiMapper dianfeiMapper;

    @Autowired
    DlDianfeiDetailMapper dlDianfeiDetailMapper;

    @Override
    public InvoiceUploadResult uploadFile(MultipartFile file ,HttpSession session) throws IOException {
        String content=new String(file.getBytes(),GBK_CHARSET);
        System.out.println(content);
        //以逗号为分隔符，每条记录都有16个字段
        String[] columns=content.replaceAll("\"","").
                replaceAll(" ","").
                replaceAll("\r|\n","").
                split(",");
        List dianFeis=new ArrayList();
        System.out.println(columns.length);
        Date importTime=new Date();
        String yearMonth="";
        Employee employee=(Employee)session.getAttribute(Constent.SESSION_EMPLOYEE);
        System.out.println(employee.toString());
        for(int index=0;index<columns.length-13;index+=14){
            DlDianfei dianfei=new DlDianfei();
            DlDianfeiDetail dlDianfeiDetail=new DlDianfeiDetail();
            yearMonth=columns[index].substring(10,16);
            String userNum=columns[index].substring(0,10);
            Long id= MathUtil.getId();
            dianfei.setId(id);
            dianfei.setIdentityField(columns[index]);
            dianfei.setMonthYear(yearMonth);
            dianfei.setUserNum(userNum);
            dianfei.setExportNnum(columns[index+1]);
            dianfei.setUserName(columns[index+2]);
            dianfei.setUserTaxNo(columns[index+3]);
            dianfei.setUserAddr(columns[index+4]);
            dianfei.setUserBankinfo(columns[index+5]);
            dianfei.setRecordInfo(columns[index+6]);
            dianfei.setTotalMoneyIncludeTax(BigDecimal.valueOf(Double.valueOf(columns[index+11])));
            dlDianfeiDetail.setGoodsName(columns[index+7]);
            dlDianfeiDetail.setGoodsUnit(columns[index+8]);
            dlDianfeiDetail.setGoodsMode(columns[index+9]);
            dlDianfeiDetail.setGoodsAmount(BigDecimal.valueOf(Double.valueOf(columns[index+10])));
            dlDianfeiDetail.setMoneyIncludeTax(BigDecimal.valueOf(Double.valueOf(columns[index+11])));
            dlDianfeiDetail.setTaxRate(BigDecimal.valueOf(Double.valueOf(columns[index+12])));
            dlDianfeiDetail.setMainId(id);
            long operatorId=employee.getId();
            long enterpriseId=employee.getEnterpriseId();
            dlDianfeiDetail.setEnterpriseId(enterpriseId);
            dianfei.setEnterpriseId(enterpriseId);
            dianfei.setOperator(operatorId);
            dianfei.setCreateTime(DateUtil.getTime());
            List<DlDianfeiDetail> dlDianfeiDetails=new ArrayList<>();
            dlDianfeiDetails.add(dlDianfeiDetail);
            dianfei.setDlDianfeiDetails(dlDianfeiDetails);
            dianFeis.add(dianfei);
        }
        //如果系统中有同唯一标识数据(0490659160201807/1)并且该数据未开票，新导入的数据覆盖原数据；
        // 如果没有同唯一标识数据时新增数据；
        // 如果系统中有同唯一标识数据并且该数据已开票，新导入数据变为问题单据
        int normalNum=0;//正常单据数量
        int errorNum=0;//异常数据数量
        Iterator<DlDianfei> dianfeiIterator=dianFeis.iterator();
        while (dianfeiIterator.hasNext()){
            DlDianfei dianfei=dianfeiIterator.next();
            String identityField=dianfei.getIdentityField();
            Map paramMap=new HashMap();
            paramMap.put("identityField",identityField);
            paramMap.put("dataType",DATA_TYPE_2);
            int countNum=dianfeiMapper.countByMap(paramMap);
            log.info("***********:"+identityField+"是否开票查验结果:"+countNum);
            int result=0;//操作数据库数据条数
            if(countNum>0){
                //数据存储了，而且已经开票

                //判断问题单据是否已经存在
                paramMap.put("dataType",DATA_TYPE_1);
//                countNum=dianfeiMapper.countByMap(paramMap);
//                log.info("***********:"+identityField+"确定已经开票，查验问题单据是否已经存在结果:"+countNum);
//                if(countNum>0){
//                    dianfeiMapper.deleteByMap(paramMap);
//                }

                DlDianfeiExample dlDianfeiExample=new DlDianfeiExample();
                DlDianfeiExample.Criteria criteria=dlDianfeiExample.createCriteria();
                criteria.andDataTypeEqualTo(Byte.valueOf(DATA_TYPE_1));
                criteria.andIdentityFieldEqualTo(identityField);
                List<DlDianfei> dlDianfeis=dianfeiMapper.selectByExample(dlDianfeiExample);
                log.info("***********:"+identityField+"确定已经开票，查验问题单据是否已经存在结果:"+dlDianfeis.size());
                if(dlDianfeis.size()>0){
                    //删除旧的问题单据主表内容
                    dianfeiMapper.deleteByMap(paramMap);
                    //删除旧的问题单据子表内容
                    DlDianfeiDetailExample dlDianfeiDetailExample=new DlDianfeiDetailExample();
                    DlDianfeiDetailExample.Criteria criteria2=dlDianfeiDetailExample.createCriteria();
                    criteria2.andMainIdEqualTo(dlDianfeis.get(0).getId());
                    result=dlDianfeiDetailMapper.deleteByExample(dlDianfeiDetailExample);
                }

                //插入新的问题单据主表内容
                dianfei.setDataType("1");
                result=dianfeiMapper.insertSelective(dianfei);
                ++errorNum;
                log.info("***********:"+identityField+"作为问题单据插入新的问题单据主表结果:"+result);
                //插入新的问题单据子表内容
                result=dlDianfeiDetailMapper.insertSelective(dianfei.getDlDianfeiDetails().get(0));
                log.info("***********:"+identityField+"作为问题单据插入新的问题单据子表结果:"+result);
                continue;
            }
            //数据库存储了，但是没开
            paramMap.put("dataType",DATA_TYPE_0);
            DlDianfeiExample dlDianfeiExample=new DlDianfeiExample();
            DlDianfeiExample.Criteria criteria=dlDianfeiExample.createCriteria();
            criteria.andDataTypeEqualTo(Byte.valueOf(DATA_TYPE_0));
            criteria.andIdentityFieldEqualTo(identityField);
            List<DlDianfei> dlDianfeis=dianfeiMapper.selectByExample(dlDianfeiExample);
            log.info("***********:"+identityField+"数据库存储了，但是没开|查验结果:"+dlDianfeis.size());
            if(dlDianfeis.size()>0){
                //数据库存储该行数据,但是未开票，先删除,再插入
                //删除旧的问题单据主表内容
                dianfeiMapper.deleteByMap(paramMap);
                //删除旧的问题单据子表内容
                DlDianfeiDetailExample dlDianfeiDetailExample=new DlDianfeiDetailExample();
                DlDianfeiDetailExample.Criteria criteria2=dlDianfeiDetailExample.createCriteria();
                criteria2.andMainIdEqualTo(dlDianfeis.get(0).getId());
                result=dlDianfeiDetailMapper.deleteByExample(dlDianfeiDetailExample);

                //插入新的问题单据主表内容
                dianfei.setDataType("0");
                result=dianfeiMapper.insertSelective(dianfei);
                ++normalNum;
                log.info("***********:"+identityField+"覆盖插入新单据主表结果:"+result);
                //插入新的问题单据子表内容
                result=dlDianfeiDetailMapper.insertSelective(dianfei.getDlDianfeiDetails().get(0));
                log.info("***********:"+identityField+"覆盖插入新单据子表结果:"+result);
                continue;
            }
            paramMap.remove("dataType");
            countNum=dianfeiMapper.countByMap(paramMap);
            log.info("***********:"+identityField+"数据库是否存储|查验结果:"+countNum);
            if(countNum<=0){
                //数据库没有存储该行数据
                dianfei.setDataType("0");
                //插入新单据主表内容
                result=dianfeiMapper.insertSelective(dianfei);
                ++normalNum;
                log.info("***********:"+identityField+"直接插入单据主表结果:"+result);
                //插入新单据子表内容
                result=dlDianfeiDetailMapper.insertSelective(dianfei.getDlDianfeiDetails().get(0));
                log.info("***********:"+identityField+"直接插入单据子表结果:"+result);
            }
        }
        Map<String,String> map=new HashMap<>();
        InvoiceUploadResult invoiceUploadResult=new InvoiceUploadResult();
        invoiceUploadResult.setErrorNum(errorNum);
        invoiceUploadResult.setNormalNum(normalNum);
        invoiceUploadResult.setTotalNum(dianFeis.size());
        invoiceUploadResult.setYearMonth(yearMonth);
        invoiceUploadResult.setImportDate(new Date());
        invoiceUploadResult.setOperator(employee.getName());
        return invoiceUploadResult;
    }

    public static void main(String[] args) {
        String str="\"0490659160201807/1\",\"1\",\"青岛崇元塑料有限公司\",\"913702817403927168\",\"青岛胶州市阜安第二工业园83233567\",\"农行胶州市支行38140101040015841\",\"HH:0490659160NY:201807抄表册:QDJZ07017YHZH:38140101040015841\",\" \"\n" +
                "\"电费\",\"千瓦时\",\"0.00000\",\"789510\",\"565921.79\",\"0.16\",\"3010\",\" \"\n" +
                "\"0697615424201807/1\",\"1\",\"山东欧克斯岩棉制造有限公司\",\"913702815720961835\",\"青岛胶州市胶莱镇工业园0532-88220577\",\"青岛农村商业银行胶州市胶莱支行9020102703842050000455\",\"HH:0697615424NY:201807抄表册:QDJZ06020YHZH:9020102703842050000455\",\" \"\n" +
                "\"电费\",\"千瓦时\",\"0.00000\",\"696524\",\"489680.69\",\"0.16\",\"3010\",\" \"\n" +
                "\"0572701233201807/1\",\"1\",\"青岛邦和压铸有限公司\",\"91370281750404733E\",\"胶州市马店工业园83226008\",\"农行胶州市支行38-140101040017441\",\"HH:0572701233NY:201807抄表册:QDJZ07018YHZH:38-140101040017441\",\" \"\n" +
                "\"电费\",\"千瓦时\",\"0.00000\",\"592515\",\"416591.01\",\"0.16\",\"3010\",\" \"";
        System.out.println(str.replaceAll("\"","").replaceAll(" ","").replaceAll("\r|\n*",""));
    }


}
