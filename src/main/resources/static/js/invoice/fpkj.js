// private property
_keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";

/**
 * 开启金税盘
 * @returns {{state: boolean, data: {machineNo: null, taxCode: null, corpName: null}}}
 */
function openCard(func) {
    var result = {
        "state": false,
        "data": {
            "machineNo": null,
            "taxCode": null,
            "corpName": null
        }
    };
    console.log("openCard");
    /*console.log(this.checkSetup());
    console.log(this.isOpen);
    console.log(this.isSetup);
    console.log(this.kpdriver.RetCode);*/
    if (func) {
        this.checkSetup(func);
    } else {
        this.checkSetup();
    }
    if (this.isOpen) {
        console.log("金税盘已开启！");
        result.state = true;
        result.data.machineNo = this.kpdriver.MachineNo;
        result.data.taxCode = this.kpdriver.TaxCode;
        result.data.corpName = this.kpdriver.CorpName;
        console.log(JSON.stringify(result));
        return result;
    }
    if (!this.isSetup) {
        return result;
    }
    try {
        CollectGarbage();
        if (this.checkNew()) {
            //if(!this.setCertPassWord()) return false;
            //this.kpdriver.CertPassWord = this.CertPassWord;
        }
        this.kpdriver.OpenCard();
        if (this.kpdriver.RetCode == "1011") {
            this.isOpen = true;
            console.log("成功开启金税盘！");

            result.state = true;
            result.data.machineNo = this.kpdriver.MachineNo;
            result.data.taxCode = this.kpdriver.TaxCode;
            result.data.corpName = this.kpdriver.CorpName;
            console.log(JSON.stringify(result));
            return result;
        } else if (this.kpdriver.RetCode == "1007") {
            layer.alert('1.金税卡已经被占用，请退出所有可能使用金税卡的程序后重试\n2.若使用升级版开票系统请确认证书口令参数是否正确，重启浏览器后重试\n3.使用升级版开票系统，若重试2次后仍报错，请登录开票系统清零口令失败次数，以防证书锁死！', {'icon': 2,end:function () {
                    if (func) func();
                }});
            return result;
        } else if (this.kpdriver.RetCode == "1001") {
            layer.alert('开票信息初始化失败，请重启计算机或重装开票系统后重试！', {'icon': 2,end:function () {
                    if (func) func();
                }});
            return result;
        } else if (this.kpdriver.RetCode == "1005") {
            layer.alert('金税卡开启失败，错误代码1005：' + this.kpdriver.RetMsg, {'icon': 2,end:function () {
                    if (func) func();
                }});
            closeCard();
            return result;
        } else {
            layer.alert('金税卡开启失败，错误代码：' + this.kpdriver.RetCode + '\n,' + this.kpdriver.RetMsg, {'icon': 2,end:function () {
                    if (func) func();
                }});
            closeCard();
            return result;
        }
    } catch (e) {
        layer.alert("开票组件出错，错误信息为openCard()：" + e.description, {'icon': 2,end:function () {
                if (func) func();
            }});
        closeCard();
        return result;
    }
}

/**
 * 关闭金税盘
 */
function closeCard() {
    if (this.kpdriver != undefined && this.kpdriver != null) {
        try {
            this.kpdriver.CloseCard();
        } catch (e) {
        }
        finally {
            this.kpdriver = null;
            setTimeout(CollectGarbage, 100);
        }
    }
    this.isOpen = false;
    // window.close();
}

/**
 * 金税盘开启检测
 * @returns {boolean}
 */
function checkSetup(func) {
    try {
        if (this.kpdriver == undefined || this.kpdriver == null) {
            this.kpdriver = new ActiveXObject("TaxCardX.GoldTax");
        }
        this.isSetup = true;
    } catch (e) {
        if (e.description == "Automation 服务器不能创建对象") {
            layer.alert('您没有安装开票组件或组件被禁用，无法使用此功能。', {'icon': 2 ,end:function () {
                    if (func) func();
                }});
        } else {
            layer.alert('开票组件调用出错，请使用IE浏览器并且使用管理员权限打开！', {'icon': 2,end:function () {
                    if (func) func();
                }});
        }
        this.isSetup = false;
        this.closeCard();
        return false;
    }
    return true;
}

/**
 * 金税盘开启检测
 * @returns {boolean}
 */
function checkNew() {
    if (this.isNew) {
        return true;
    }
    try {
        var shell = new ActiveXObject("WScript.Shell");
        var key = shell.RegRead("HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\App Paths\\fwkp.exe\\Version");
        if (key != null) {
            this.isNew = true;
            return true;
        }
    } catch (e) {
        this.isNew = false;
        return false;
    }
    return false;
}

/**
 * 查询库存发票
 * @param infoKind
 * @returns {{state: boolean, msg: string, data: {infoTypeCode: null, infoNumber: null, invStock: null, taxClock: null}}}
 */
function getStoreInfo(infoKind) {// 0是专票，2是普票
    var result = {
        "state": false,
        "msg": "error",
        "data": {
            "infoTypeCode": null,
            "infoNumber": null,
            "invStock": null,
            "taxClock": null
        }
    };
    this.kpdriver.InfoKind = infoKind;
    this.kpdriver.GetInfo();
    if (this.kpdriver.RetCode != '3011') {//失败
        result.msg = this.kpdriver.RetMsg;
        return result;
    }
    result.state = true;
    result.data.infoTypeCode = this.kpdriver.InfoTypeCode;
    result.data.infoNumber = pad(this.kpdriver.InfoNumber, 8);
    result.msg = "getStoreInfo()方法调用成功";
    result.data.invStock = this.kpdriver.InvStock;
    result.data.taxClock = this.kpdriver.TaxClock;
    return result;
}

/**
 * 开发票
 * @param main
 * @param del
 * @param type
 * @param gfmcNow
 * @param gfshNow
 * @param gfdzdhNow
 * @param gfyhzhNow
 * @param bz
 * @returns {*}
 */


function invoice(main, del, type, gfmcNow, gfshNow, gfdzdhNow, gfyhzhNow, success, failure) {
    // alert(1);
    console.log("invoice:......");
    console.log("main:" + main);
    console.log("del:" + del);
    main = eval('(' + main + ')');
    del = eval('(' + del + ')');
    // console.log("main:" + main);
    // console.log("del:" + del);
    // console.log(del[0]);

    var ssflbm = del[0]['GoodsTaxNo'];
    console.log(ssflbm);
    try {
        this.kpdriver.InvInfoInit();
        //主表
        if (main.InfoClientName != null && main.InfoClientName != "") {
            this.kpdriver.InfoClientName = main.InfoClientName;
        }
        if (main.InfoClientTaxCode != null && main.InfoClientTaxCode != "") {
            this.kpdriver.InfoClientTaxCode = main.InfoClientTaxCode;
        }
        if (main.InfoClientAddressPhone != null && main.InfoClientAddressPhone != "") {
            this.kpdriver.InfoClientBankAccount = main.InfoClientAddressPhone;
        }
        if (main.InfoClientAddressPhone != null && main.InfoClientAddressPhone != "") {
            this.kpdriver.InfoClientAddressPhone = main.InfoClientAddressPhone;
        }
        //alert(main['XFYHZH']);
        if (main.InfoSellerBankAccount != null) {
            this.kpdriver.InfoSellerBankAccount = main.InfoSellerBankAccount;
        }
        if (main.InfoSellerAddressPhone != null) {
            this.kpdriver.InfoSellerAddressPhone = main.InfoSellerAddressPhone;
        }
        if (main.InfoNotes != null) {
            console.log("bz:" + main.InfoNotes);
            this.kpdriver.InfoNotes = main.InfoNotes;
        }
        if (main.InfoInvoicer != null) {
            this.kpdriver.InfoInvoicer = main.InfoInvoicer;
        }
        if (main.InfoChecker != null) {
            this.kpdriver.InfoChecker = main.InfoChecker;
        }
        if (main.InfoCashier != null) {
            this.kpdriver.InfoCashier = main.InfoCashier;
        }
        //if (invoiceInfo['infolistname']!=null){
        //	this.kpdriver.InfoListName = invoiceInfo['infolistname'];
        //}
        if (main.id != null) {//ID主键
            this.kpdriver.InfoBillNumber = main.id;
        }
        if (main.InfoKind != null) {
            this.kpdriver.InfoKind = main.InfoKind;
        }


        // console.log('this.kpdriver.InfoClientName=' + this.kpdriver.InfoClientName + '\nthis.kpdriver.InfoClientTaxCode=' + this.kpdriver.InfoClientTaxCode + '\nthis.kpdriver.InfoClientBankAccount=' + this.kpdriver.InfoClientBankAccount
        //     + '\nthis.kpdriver.InfoClientAddressPhone=' + this.kpdriver.InfoClientAddressPhone + '\nthis.kpdriver.InfoSellerBankAccount=' + this.kpdriver.InfoSellerBankAccount + '\nthis.kpdriver.InfoSellerAddressPhone=' + this.kpdriver.InfoSellerAddressPhone
        //     + '\nthis.kpdriver.InfoTaxRate=' + this.kpdriver.InfoTaxRate + '\nthis.kpdriver.InfoNotes=' + this.kpdriver.InfoNotes + '\nthis.kpdriver.InfoInvoicer=' + this.kpdriver.InfoInvoicer
        //     + '\nthis.kpdriver.InfoChecker=' + this.kpdriver.InfoChecker + '\nthis.kpdriver.InfoCashier=' + this.kpdriver.InfoCashier + '\nthis.kpdriver.InfoListName=' + this.kpdriver.InfoListName);

        this.kpdriver.InvListInit();
        this.kpdriver.ClearInvList();
        //console.log(del);
        //var del=eval('(' + del + ')');
        //console.log(del);
        //商品详情
        for (var i in del) {
            //alert("开始循环!!!!!!!!!!!!");
            //保存商品分类编码
            // bmbbh 	编码版本号
            // ssflbm  	税收分类编码
            // sfxsyhzc	是否享受税收优惠政策 0：不享受，1：享受-
            // yhzcnr		享受税收优惠政策内容
            // lsvbs		零税率标识空：非零税率，0：出口退税，1：免税，2：不征收，3 普通零税率
            // qyzbm		企业自编码
            var bmbbh = main['BMBBH'];
            var ssflbm = del[i]['GoodsTaxNo'];
            var sfxsyhzc = del[i]['TaxPre'];
            var yhzcnr = del[i]['TaxPreCon'];
            var lsvbs = del[i]['ZeroTax'];
            var qyzbm = '12345321';
            if (saveSpflbm(bmbbh, ssflbm, sfxsyhzc, yhzcnr, lsvbs, qyzbm) == false) {
                return false;
            }
            if (del[i]['SL'] != null) {
                console.log("SL :"+parseFloat(del[i]['SL']) * 100);
                this.kpdriver.InfoTaxRate = parseFloat(del[i]['SL']) * 100;
            }
            if (del[i]['ListGoodsName'] != null) {
                this.kpdriver.ListGoodsName = del[i]['ListGoodsName'];
            }
            if (del[i]['GoodsTaxNo'] != null) {
                this.kpdriver.ListTaxItem = del[i]['GoodsTaxNo'];
            }
            if (del[i]['ListStandard'] != null) {
                this.kpdriver.ListStandard = del[i]['ListStandard'];
            }
            if (del[i]['ListUnit'] != null) {
                this.kpdriver.ListUnit = del[i]['ListUnit'];
            }
            if (del[i]['ListNumber'] != null) {
                this.kpdriver.ListNumber = del[i]['ListNumber'];
            }
            // if (del[i]['ListPrice'] != null) {
            //     this.kpdriver.ListPrice = del[i]['ListPrice'];
            // }
            if (del[i]['ListAmount'] != null) {
                this.kpdriver.ListAmount = del[i]['ListAmount'];
            }
            if (del[i]['ListPriceKind'] != null) {//含税价标志，单价和金额的种类， 0为不含税价，1为含税价
                this.kpdriver.ListPriceKind = del[i]['ListPriceKind'];
            }
            //this.kpdriver.ListPriceKind = 0;
            if (del[i]['SE'] != null) {
                this.kpdriver.ListTaxAmount = del[i]['SE'];
            }
            /*
            alert('this.kpdriver.ListGoodsName='+this.kpdriver.ListGoodsName+'\nthis.kpdriver.ListTaxItem='+this.kpdriver.ListTaxItem+'\nthis.kpdriver.ListStandard='+this.kpdriver.ListStandard
                    +'\nthis.kpdriver.ListUnit='+this.kpdriver.ListUnit+'\nthis.kpdriver.ListNumber='+this.kpdriver.ListNumber+'\nthis.kpdriver.ListPrice='+this.kpdriver.ListPrice
                    +'\nthis.kpdriver.ListAmount='+this.kpdriver.ListAmount+'\nthis.kpdriver.ListPriceKind='+this.kpdriver.ListPriceKind+'\nthis.kpdriver.ListTaxAmount='+this.kpdriver.ListTaxAmount);
            */
            this.kpdriver.AddInvList();
            this.kpdriver.ListStandard = '';
            this.kpdriver.ListUnit = '';
            // this.kpdriver.ListPrice = 0;
            this.kpdriver.ListNumber = 0;
            this.kpdriver.ListAmount = 0;
        }
        //alert("开始开发票啦！！！！！！！！！！！");
        this.kpdriver.Invoice();
        var result = {};
        /*这里必须将ActiveX对象里的值转成字符串才能正确的在参数中传递*/
        result['infoAmount'] = this.kpdriver.InfoAmount + '';
        result['infoTaxAmount'] = this.kpdriver.InfoTaxAmount + '';
        //result['infoDate'] = new Date(this.kpdriver.InfoDate+'').Format('yyyy-MM-dd hh:mm:ss');
        result['infMonth'] = this.kpdriver.InfMonth + '';
        result['typeCode'] = this.kpdriver.InfoTypeCode + '';//发票十位代码
        result['number'] = this.kpdriver.InfoNumber + '';//发票号码
        result['goodsListFlag'] = this.kpdriver.GoodsListFlag + '';
        result['retCode'] = this.kpdriver.RetCode + '';
        result['machineNo'] = this.kpdriver.MachineNo + '';
        result['retMsg'] = this.kpdriver.RetMsg + '';
        //result['RetCode'] = 4011+'';
        // console.log("开票结果:" + JSON.stringify(result));
        //console.log(result['retCode']);
        // if(result['retCode']=='4011'){
        console.log("result['retCode']:" + result['retCode']);
        console.log("result['retMsg']:" + result['retMsg']);
        if (result['retCode'] == '4011') {
            success(result);
        } else {
            failure(result.retMsg);
        }
        // }
        // return result;
    } catch (e) {
        //alert('开票失败,错误信息:' + e.description);
        // layer.alert('开票失败,错误信息:' + e.description, {'icon': 2}, function (index) {
        //     layer.close(index);
        // });
        // return false;
        console.log("捕捉异常！" + e.description);
        failure(e.description);
    }
}

/**
 * 作废发票
 * @param infoKind
 * @param infoTypeCode
 * @param infoNumber
 * @returns {{}}
 */
function cancelInv(infoKind, infoTypeCode, infoNumber, func) {
    console.log("作废发票");
    var result = {};
    try {
        this.kpdriver.InfoKind = infoKind;//纸票类型
        this.kpdriver.InfoTypeCode = infoTypeCode;//发票代码
        this.kpdriver.InfoNumber = infoNumber;//发票号码
        this.kpdriver.CancelInv();
        result.RetCode = this.kpdriver.RetCode + '';
        result.RetMsg = this.kpdriver.RetMsg + '';
        if (func) {
            func(result);
        } else return result;
    } catch (e) {
        result.RetCode = '6012';
        result.RetMsg = e.description;
        if (func) {
            func(result);
        } else {
            layer.alert('作废失败,失败原因:' + e.description, {'icon': 2});
        }
        ;
    }
}

/**
 * 发票打印
 * @param infoKind
 * @param infoTypeCode
 * @param infoNumber
 * @param goodsListFlag
 * @param infoShowPrtDlg
 * @returns {*}
 */
function printInv(infoKind, infoTypeCode, infoNumber, goodsListFlag, infoShowPrtDlg, func) {
    var result = {};
    try {
        this.kpdriver.InfoKind = infoKind;
        this.kpdriver.InfoTypeCode = infoTypeCode;//发票代码
        this.kpdriver.InfoNumber = infoNumber;//发票号码
        this.kpdriver.GoodsListFlag = goodsListFlag;//0 打发票  1打清单
        this.kpdriver.InfoShowPrtDlg = infoShowPrtDlg;//是否显示边距对话框0 否  1  是
        this.kpdriver.PrintInv();
        result.RetMsg = this.kpdriver.RetMsg;
        result.RetCode = this.kpdriver.RetCode;
        if (func) {
            func(result);
        } else {
            //alert(this.kpdriver.RetMsg);
            return this.kpdriver.RetMsg;
        }
    } catch (e) {
        if (func) {
            result.RetMsg = e.description;
            result.RetCode = '5013';
            func(result);
        } else {
            layer.alert('打印失败,失败原因:' + e.description, {'icon': 2})
        }
    }
}

/**
 * 保存税收分类编码
 * @param bmbbh
 * @param ssflbm
 * @param sfxsyhzc
 * @param yhzcnr
 * @param lsvbs
 * @param qyzbm
 */
function saveSpflbm(bmbbh, ssflbm, sfxsyhzc, yhzcnr, lsvbs, qyzbm) {
    var result = false;
    //this.kpdriver = new ActiveXObject("TaxCardX.GoldTax");
    var goodsNoData = "<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
        "            <FPXT><INPUT>\n" +
        "            <GoodsNo>\n" +
        "            <GoodsNoVer>" + bmbbh + "</GoodsNoVer>\n" +
        "            <GoodsTaxNo>" + ssflbm + "</GoodsTaxNo >\n" +
        "            <TaxPre>" + sfxsyhzc + "</TaxPre >\n" +
        "            <TaxPreCon>" + yhzcnr + "</TaxPreCon>\n" +
        "            <ZeroTax>" + lsvbs + "</ZeroTax >\n" +
        "            <CropGoodsNo>" + qyzbm + "</CropGoodsNo>\n" +
        "            <TaxDeduction>" + "" + "</TaxDeduction>\n" +
        "            </GoodsNo>\n" +
        "            </INPUT>\n" +
        "            </FPXT>";
    console.log(goodsNoData);
    $.ajax({
        url: "/noc/zyp/gbkChange",
        type: "post",
        data: {"xml": goodsNoData},
        dataType: "json",
        async: false,
        success: function (strBase64) {
            //console.log("gbk编码："+strBase64.msg);
            if (strBase64.status == 200) {
                //发送数据
                var recieveData = uploadSsflbm(strBase64.data);
                if (recieveData.toString().indexOf("><CODE>0000</CODE><") == -1) {
                    //如果上传税收分类编码失败，返回false
                } else {
                    result = true;
                }
            } else {
                layer.alert("uploadSsflbm()方法所需要的gbk编码生成失败", {icon: 2, title: '温馨提示'});
            }
        },
        error: function () {
            layer.alert('saveSpflbm()前后台数据交互异常', {icon: 2, title: '温馨提示'});
        }
    });
    return result;
}

/**
 * 上传税收分类编码
 * @param strBase64
 * @returns {*}
 */
function uploadSsflbm(strBase64) {
    //发送数据
    var strSendData = "<?xml version=\"1.0\" encoding=\"GBK\"?><FPXT_COM_INPUT><ID>1100</ID><DATA>" + strBase64 +
        "</DATA></FPXT_COM_INPUT>";
    //调用传入
    var recieveData = this.kpdriver.BatchUpload(strSendData);
    console.log("保存商品编码返回内容:" + recieveData);
    return recieveData;
}

//开启金税盘开票
function openJinShuiPan(main, del, gfmcNow, gfshNow, gfdzdhNow, gfyhzhNow, type, success, failure) {
    if (openCard()) {
        //openpagebygetjson( 'pages/shougongkaipiao');
        //开启金税盘成功，查询金税盘发票数量
        console.log("发票类型:" + type);
        var result = getStoreInfo(type);
        console.log(result);
        if (result.state == false) {
            //读取金税盘发票失败
            layer.alert(result.msg, {icon: 2, title: '温馨提示'});
            return false;
        }
        var fpdm = result.data.infoTypeCode;
        var fphm = result.data.infoNumber;
        var invStock = result.data.invStock;
        if (invStock <= 0) {
            layer.alert("金税盘发票余量不足，无法开票", {icon: 2, title: '温馨提示'});

            return false;
        }
        if (invStock <= 20) {

            layer.confirm("金税盘发票余量" + invStock + "张,即将不足，请及时补充发票", {
                btn: ['知道了,继续开', '等等再开'] //可以无限个按钮
            }, function (index, layero) {  //确认
                layer.close(index);
                // layer.confirm("请确认现在要开具的发票代码:" + fpdm + "\n" +
                //     "发票号码:" + fphm, {
                //     btn: ['确认开具', '取消开具'] //可以无限个按钮
                // }, function (index, layero) {  //确认
                //     layer.close(index);
                invoice(main, del, type, gfmcNow, gfshNow, gfdzdhNow, gfyhzhNow, success, failure);
                //
                // }, function (index) {//取消
                //     layer.close(index);
                //     return false;
                // });
                layer.close(index);

            }, function (index) {//取消
                layer.close(index);
                return false;
            });
        } else {

            // alert("请确认现在要开具的发票代码:" + fpdm + "\n" +
            //     "发票号码:" + fphm);
            // return invoice(main, del, type, gfmcNow, gfshNow, gfdzdhNow, gfyhzhNow);

            // layer.confirm("请确认现在要开具的发票代码:" + fpdm + "\n" +
            //     "发票号码:" + fphm, {
            //     btn: ['确认开具', '取消开具'] //可以无限个按钮
            // }, function (index, layero) {  //确认
            //     layer.close(index);
            invoice(main, del, type, gfmcNow, gfshNow, gfdzdhNow, gfyhzhNow, success, failure);

            // }, function (index) {//取消
            //     layer.close(index);
            //     return false;
            // });
        }

//开纸票之前，先保存每行商品的税收分类编码
//changetoxml(main,del);
//发票数量大于0 ，可以开票
    } else {
        layer.alert("暂时不能开票，请联系管理员", {icon: 2, title: '温馨提示'});
        //alert("暂时不能开票，请联系管理员");
        return false;
    }
}

//带提示的开启金税盘开票
function openJinShuiPan1(main, del, gfmcNow, gfshNow, gfdzdhNow, gfyhzhNow, type, success, failure) {
    if (openCard()) {
        //openpagebygetjson( 'pages/shougongkaipiao');
        //开启金税盘成功，查询金税盘发票数量
        console.log("发票类型:" + type);
        var result = getStoreInfo(type);
        console.log(result);
        if (!result.state) {
            //读取金税盘发票失败
            //failure(result.msg);
            parent.layer.alert(result.msg, {icon: 2, title: '温馨提示'});
            return false;
        }
        var fpdm = result.data.infoTypeCode;
        var fphm = result.data.infoNumber;
        var invStock = result.data.invStock;
        //var invStock = 0;
        if (invStock <= 0) {
            parent.layer.alert("金税盘发票余量不足，无法开票", {icon: 2, title: '温馨提示'});
            return false;
        }
        if (invStock <= 20) {
            // parent.layer.confirm("金税盘发票余量" + invStock + "张,即将不足，请及时补充发票", {
            //     btn: ['知道了,继续开', '等等再开'] //可以无限个按钮
            // }, function (index, layero) {  //确认
            //     layer.close(index);
            //     parent.layer.confirm("请确认现在要开具的发票代码:" + fpdm + "\n" +
            //         "发票号码:" + fphm, {
            //         btn: ['确认开具', '取消开具'] //可以无限个按钮
            //     }, function (index, layero) {  //确认
            //         layer.close(index);
                    invoice(main, del, type, gfmcNow, gfshNow, gfdzdhNow, gfyhzhNow, success, failure);

            //     }, function (index) {//取消
            //         layer.close(index);
            //         return false;
            //     });
            //     layer.close(index);
            //
            // }, function (index) {//取消
            //     layer.close(index);
            //     return false;
            // });
        } else {
            // parent.layer.confirm("请确认现在要开具的发票代码:" + fpdm + "\n" +
            //     "发票号码:" + fphm, {
            //     btn: ['确认开具', '取消开具'] //可以无限个按钮
            // }, function (index, layero) {  //确认
            //     layer.close(index);
                invoice(main, del, type, gfmcNow, gfshNow, gfdzdhNow, gfyhzhNow, success, failure);

            // }, function (index) {//取消
            //     layer.close(index);
            //     return false;
            // });
        }
    } else {
        layer.alert("暂时不能开票，请联系管理员", {icon: 2, title: '温馨提示'});
        //alert("暂时不能开票，请联系管理员");
        return false;
    }
}

/* 质朴长存法  by lifesinger */
function pad(num, n) {
    var len = num.toString().length;
    while (len < n) {
        num = "0" + num;
        len++;
    }
    return num;
}


//发票查询接口
function QryInv(fpzl, fpdm, fphm, success, failure) {
    console.log("发票查询接口");
    //this.kpdriver.InfoBillNumber = "";
    this.kpdriver.InfoTypeCode = fpdm;//发票代码
    this.kpdriver.InfoNumber = fphm;//发票号码
    this.kpdriver.InfoKind = fpzl;
    this.kpdriver.QryInv();
    var result = {};
    result.RetCode = this.kpdriver.RetCode;
    result.RetMsg = this.kpdriver.RetMsg;
    result.InfoKind = this.kpdriver.InfoKind;
    result.InfoTypeCode = this.kpdriver.InfoTypeCode;
    result.InfoNumber = this.kpdriver.InfoNumber;
    result.InfoBillNumber = this.kpdriver.InfoBillNumber;
    result.InfoAmount = this.kpdriver.InfoAmount;
    result.InfoTaxAmount = this.kpdriver.InfoTaxAmount;
    result.InfoInvDate = this.kpdriver.InfoInvDate;
    result.PrintFlag = this.kpdriver.PrintFlag;
    result.UploadFlag = this.kpdriver.UploadFlag;
    result.CancelFlag = this.kpdriver.CancelFlag;
    result.Info = this.kpdriver.Info;
    if (this.kpdriver.RetCode == '7011') {
        console.log(JSON.stringify(this.kpdriver));
        console.log(JSON.stringify(result));
        console.log(result);
        //查询成功
        success(result);
    } else {
        failure(this.kpdriver.RetMsg);
    }
}


//保存发票信息到YKFP内
function saveFpInfo2Ykfp(res, mainId, defaultType, kplx, ywlx, machineNo, yfpdm, yfphm, chyy, func) {
    var QryInvResult = {};
    QryInvResult.retCode = res.RetCode;
    QryInvResult.retMsg = res.RetMsg;
    QryInvResult.infoKind = res.InfoKind;
    QryInvResult.infoTypeCode = res.InfoTypeCode;
    QryInvResult.infoNumber = pad(res.InfoNumber, 8);
    //QryInvResult.infoBillNumber=res.InfoBillNumber;
    QryInvResult.infoBillNumber = mainId;
    QryInvResult.infoAmount = res.InfoAmount;
    QryInvResult.infoTaxAmount = res.InfoTaxAmount;
    QryInvResult.infoInvDate = res.InfoInvDate;
    QryInvResult.printFlag = res.PrintFlag;
    QryInvResult.uploadFlag = res.UploadFlag;
    QryInvResult.cancelFlag = res.CancelFlag;
    QryInvResult.info = res.Info;
    QryInvResult.fpzl = defaultType;
    QryInvResult.kplx = kplx;//开票类型1-蓝字发票；2-红字发票；3-被冲红；4-作废
    QryInvResult.ywlx = ywlx;//业务类型 1-普通电费 2-高可靠电费 3-自由票
    QryInvResult.machineNo = machineNo;//分机号
    if (yfpdm) QryInvResult.yfpdm = yfpdm;//原发票代码
    if (yfphm) QryInvResult.yfphm = yfphm;//原发票号码
    if (chyy) QryInvResult.chyy = chyy;//冲红原因
    $.ajax({
        type: 'POST',
        url: '/noc/zyp/saveFpInfo2Ykfp',
        cache: false,
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify(QryInvResult),
        dataType: "json",
        async: false,
        success: function (data) {
            //console.info(data);
            if (data.status == 200) {
                result = data.data;
                layer.msg("保存数据成功", {icon: 1, time: 500, title: '温馨提示'});
                if (func) func();
            } else {
                console.log(data.msg);
                // layer.msg(data.msg, {icon: 2, title: '温馨提示'});
            }
        },
        error: function (error) {
            console.info(error);
            // layer.msg('前后台数据交互异常', {icon: 2, title: '温馨提示'});
        }

    });

}


//发票头结构
var invoiceMain = {
    InfoKind: "",//发票类型
    InfoClientName: "",//购方名称
    InfoClientTaxCode: "",//购方税号
    InfoClientAddressPhone: "",//购方地址电话
    InfoClientBankAccount: "",//购方银行账号
    InfoSellerBankAccount: "",//销方银行账号
    InfoSellerAddressPhone: "",//销方地址电话
    InfoTaxRate: "",//税率
    InfoNotes: "",//备注
    InfoInvoicer: "",//开票人
    InfoChecker: "",//复核人，可为空
    InfoCashier: "",//收款人，可为空
    InfoListName: "",//如不为空 则填“详见货物清单”
    InfoBillNumber: "",//销售单据编号，可为空
    BMBBH: "",//税收分类编码版本号
    SellerAddress: ""//见文档
}
//发票明细结构
var invoiceDel = {
    ListGoodsName: "",//商品或劳务名称
    ListTaxItem: "",//税目 4位数字 商品所属类别
    ListStandard: "",//规格型号
    ListUnit: "",//计量单位
    ListNumber: "",//数量
    ListPrice: "",//单价
    ListAmount: "",//金额
    ListPriceKind: "",//含税标志 0-不含税 1-含税
    ListTaxAmount: "",//税额 可以不传（为0） 如传入则应符合计算关系
    GoodsTaxNo: "",//税收分类编码
    TaxPre: "",//是否享受税收优惠政策 0-不享受 1-享受
    TaxPreCon: "",//税收优惠政策内容
    ZeroTax: "",//零税率标识 空：非零税率 0；出口退税 1；免税 2；不征收 3；普通零税率
    CropGoodsNo: "",//企业自编码
    TaxDeduction: "",//扣除额 ，可为空
    SL: ""//税率
}



