//开启金税盘开票
function openJinShuiPan(type,main,del,gfmcNow,gfshNow,gfdzdhNow,gfyhzhNow){
	if(openCard()){
		//openpagebygetjson( 'pages/shougongkaipiao');
		//开启金税盘成功，查询金税盘发票数量
        var result=getInfo(type);
        console.log(result);
        if(result.invStock<=0){
        	alert("金税盘发票余量不足，无法开票");
        	return false;
        }
        if(result.invStock<=20){
            alert("金税盘发票余量"+result.invStock+"张,即将不足，请及时补充发票");
            return false;
            //return false;
        }
        alert("请确认现在要开具的发票代码:"+result.infoTypeCode +"\n"+
			"发票号码:"+result.infoNumber );
        //发票数量大于0 ，可以开票
        return invoice(main,del,gfmcNow,gfshNow,gfdzdhNow,gfyhzhNow);

	}else{
		alert("暂时不能开票，请联系管理员");
		return false;
	}
}
//冲红开票
function openJinShuiPanByChongHong(){
	openCard();
	if(openCard()){
		openpagebygetjson('web/xxfp/openxxfpchonghongstep1');
	}else{
		alert("暂时不能开具红票，请联系管理员");
	}
}
//作废发票
function openJinShuiPanByZuoFei(){
	openCard();
	if(openCard()){
		openpagebygetjson('web/xxfp/openzffp');
	}else{
		alert("暂时不能作废发票，请联系管理员");
	}
}
function openCard(){
	console.log("openCard");
    // console.log(this.checkSetup());
    // console.log(this.isOpen);
    // console.log(this.isSetup);
    // console.log(this.kpdriver.RetCode);
	this.checkSetup();
	if (this.isOpen){
		return true;
	}
	if (!this.isSetup){
		return false;
	}
	try{
		CollectGarbage();
		if(this.checkNew()){
			//if(!this.setCertPassWord()) return false;
			//this.kpdriver.CertPassWord = this.CertPassWord;
		}
		this.kpdriver.OpenCard();
		if(this.kpdriver.RetCode=="1011"){
		  this.isOpen = true;
		  //alert("成功开启金税盘！");
		  return true;
	    }else if (this.kpdriver.RetCode=="1007"){
	      alert("1.金税卡已经被占用，请退出所有可能使用金税卡的程序后重试\n2.若使用升级版开票系统请确认证书口令参数是否正确，重启浏览器后重试\n3.使用升级版开票系统，若重试2次后仍报错，请登录开票系统清零口令失败次数，以防证书锁死");
	      return false;
	    }else if (this.kpdriver.RetCode=="1001"){
	      alert("开票信息初始化失败，请重启计算机或重装开票系统后重试");
	      return false;
	    }else if (this.kpdriver.RetCode=="1005"){
	    	alert("金税卡开启失败，错误代码1005："+this.kpdriver.RetCode);
			closeCard();
			return false;
	    }else {
	      alert("金税卡开启失败，错误代码："+this.kpdriver.RetCode);
	      alert(this.kpdriver.RetMsg);
	      closeCard();
	      return false;
	    }
	    
	}catch(e) {
		alert("开票组件出错，错误信息为openCard()："+e.description);
		closeCard();
		return false;
	}
}
//金税盘开启检测
//this.kpdriver = new ActiveXObject("TaxCardX.GoldTax");
//this.kpdriver=null;
function checkSetup(){
	try{
		if (this.kpdriver==undefined || this.kpdriver==null){
			this.kpdriver = new ActiveXObject("TaxCardX.GoldTax");
		}
		this.isSetup = true;
	}catch(e) {
		if (e.description=="Automation 服务器不能创建对象"){
		  alert("您没有安装开票组件或组件被禁用，无法使用此功能。");
		} else {
		  alert("开票组件出错，错误信息在checkSetup():"+e.description);
		}
		this.isSetup = false;
		this.closeCard();
		return false;
	}
	return true;
}
//金税盘开启检测
 function checkNew(){
		if (this.isNew){
			return true;
		}
		try{
			var shell = new ActiveXObject("WScript.Shell");
			var key = shell.RegRead("HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\App Paths\\fwkp.exe\\Version");
			if(key!=null){
				this.isNew = true;
				return true;
			}
		}catch(e) {
			this.isNew = false;
			return false;
		}
		return false;
	};
	
	//关闭金税盘
		function closeCard(){
		   if (this.kpdriver!=undefined && this.kpdriver!=null){
		   		try{
		   		  this.kpdriver.CloseCard();
		   		}catch(e){}
		   		finally{
		   		  this.kpdriver = null;
		   		  setTimeout(CollectGarbage, 100);
		   		}
		   }
		   this.isOpen = false;
		  // window.close();
		}
		
		//查询库存发票
		function getInfo(infoKind){//2是普票  0是专票
			this.kpdriver.InfoKind = infoKind;
			this.kpdriver.GetInfo();
			var result = {};
			result.infoTypeCode = this.kpdriver.InfoTypeCode;
			result.infoNumber = this.kpdriver.InfoNumber;
			result.invStock = this.kpdriver.InvStock;
			result.taxClock = this.kpdriver.TaxClock;
			return result;
		}
		//发票开具
		//开发票
		var main=null;
		function invoice(main,del,gfmcNow,gfshNow,gfdzdhNow,gfyhzhNow){
            alert(main);
			main=eval('(' + main + ')');
				alert(main['GFMC']);
            	//console.log(main);
            	//console.log(main['GFMC']);
			    //console.log(main.GFMC);
				try{
					this.kpdriver.InvInfoInit();
					//主表
					if (gfmcNow !=null  && gfmcNow !=""){
						this.kpdriver.InfoClientName = gfmcNow;
					}
					if (gfshNow !=null  && gfshNow !=""){
						this.kpdriver.InfoClientTaxCode = gfshNow;
					}
					if (gfdzdhNow !=null  && gfdzdhNow !=""){
						this.kpdriver.InfoClientBankAccount = gfdzdhNow;
					}
					if (gfyhzhNow !=null  && gfyhzhNow !=""){
						this.kpdriver.InfoClientAddressPhone = gfyhzhNow;
					}
					//alert(main['XFYHZH']);
					if (main['XFYHZH']!=null){
						this.kpdriver.InfoSellerBankAccount = main['XFYHZH'];
					}
					if (main['XFDZDH']!=null){
						this.kpdriver.InfoSellerAddressPhone = main['XFDZDH'];
					}
					if (main['BZ']!=null){
						this.kpdriver.InfoNotes = main['BZ'];
					}
					if (main['KPR']!=null){
						this.kpdriver.InfoInvoicer = main['KPR'];
					}
					if (main['FHR']!=null){
						this.kpdriver.InfoChecker = main['FHR'];
					}
					if (main['SKR']!=null){
						this.kpdriver.InfoCashier = main['SKR'];
					}
					//if (invoiceInfo['infolistname']!=null){
					//	this.kpdriver.InfoListName = invoiceInfo['infolistname'];
					//}
					if (main['ID']!=null){//ID主键
						this.kpdriver.InfoBillNumber = main['ID'];
					}

	/*
	alert('this.kpdriver.InfoClientName='+this.kpdriver.InfoClientName+'\nthis.kpdriver.InfoClientTaxCode='+this.kpdriver.InfoClientTaxCode+'\nthis.kpdriver.InfoClientBankAccount='+this.kpdriver.InfoClientBankAccount
			+'\nthis.kpdriver.InfoClientAddressPhone='+this.kpdriver.InfoClientAddressPhone+'\nthis.kpdriver.InfoSellerBankAccount='+this.kpdriver.InfoSellerBankAccount+'\nthis.kpdriver.InfoSellerAddressPhone='+this.kpdriver.InfoSellerAddressPhone
			+'\nthis.kpdriver.InfoTaxRate='+this.kpdriver.InfoTaxRate+'\nthis.kpdriver.InfoNotes='+this.kpdriver.InfoNotes+'\nthis.kpdriver.InfoInvoicer='+this.kpdriver.InfoInvoicer
			+'\nthis.kpdriver.InfoChecker='+this.kpdriver.InfoChecker+'\nthis.kpdriver.InfoCashier='+this.kpdriver.InfoCashier+'\nthis.kpdriver.InfoListName='+this.kpdriver.InfoListName);
			*/
					this.kpdriver.InvListInit();
					this.kpdriver.ClearInvList();
                    //console.log(del);
					var del=eval('(' + del + ')');
					//console.log(del);
					//商品详情
					for (var i in del){
					//alert("开始循环!!!!!!!!!!!!");
						//保存商品分类编码
                        // bmbbh 	编码版本号
                        // ssflbm  	税收分类编码
                        // sfxsyhzc	是否享受税收优惠政策 0：不享受，1：享受-
                        // yhzcnr		享受税收优惠政策内容
                        // lsvbs		零税率标识空：非零税率，0：出口退税，1：免税，2：不征收，3 普通零税率
                        // qyzbm		企业自编码
						var bmbbh=main['BMBBH'];
                        var ssflbm=del[i]['SPBM'];
                        alert("税收分类编码"+ssflbm);
                        var sfxsyhzc=del[i]['SFYHZC'];
                        var yhzcnr=del[i]['YHZCNR'];
                        var lsvbs=del[i]['LSLBS'];
                        var qyzbm='12345321';
                        saveSpflbm(bmbbh,ssflbm,sfxsyhzc,yhzcnr,lsvbs,qyzbm);
						if (del[i]['SL']!=null){
							this.kpdriver.InfoTaxRate = del[i]['SL']*100;
						}
						if (del[i]['XMMC']!=null){
							this.kpdriver.ListGoodsName = del[i]['XMMC'];
						}
						if (del[i]['SPBM']!=null){
							this.kpdriver.ListTaxItem = del[i]['SPBM'];
						}
						if (del[i]['GGXH']!=null){
							this.kpdriver.ListStandard = del[i]['GGXH'];
						}
						if (del[i]['JLDW']!=null){
							this.kpdriver.ListUnit = del[i]['JLDW'];
						}
						if (del[i]['XMSL']!=null){
							this.kpdriver.ListNumber = del[i]['XMSL'];
						}
						if (del[i]['XMDJ']!=null){
							this.kpdriver.ListPrice = del[i]['XMDJ'];
						}
						if (del[i]['XMJE']!=null){
							this.kpdriver.ListAmount = del[i]['XMJE'];
						}
						//if (del[i]['listpricekind']!=null){//含税价标志，单价和金额的种类， 0为不含税价，1为含税价
						//	this.kpdriver.ListPriceKind = del[i]['listpricekind'];
						//}
                        this.kpdriver.ListPriceKind = 0;
						if (del[i]['SE']!=null){
							this.kpdriver.ListTaxAmount = del[i]['SE'];
						}
	/*
	alert('this.kpdriver.ListGoodsName='+this.kpdriver.ListGoodsName+'\nthis.kpdriver.ListTaxItem='+this.kpdriver.ListTaxItem+'\nthis.kpdriver.ListStandard='+this.kpdriver.ListStandard
			+'\nthis.kpdriver.ListUnit='+this.kpdriver.ListUnit+'\nthis.kpdriver.ListNumber='+this.kpdriver.ListNumber+'\nthis.kpdriver.ListPrice='+this.kpdriver.ListPrice
			+'\nthis.kpdriver.ListAmount='+this.kpdriver.ListAmount+'\nthis.kpdriver.ListPriceKind='+this.kpdriver.ListPriceKind+'\nthis.kpdriver.ListTaxAmount='+this.kpdriver.ListTaxAmount);
	*/
						this.kpdriver.AddInvList();
						this.kpdriver.ListStandard ='';
						this.kpdriver.ListUnit ='';
						this.kpdriver.ListPrice=0;
						this.kpdriver.ListNumber=0;
						this.kpdriver.ListAmount=0;
					}
					//alert("开始开发票啦！！！！！！！！！！！");
					this.kpdriver.Invoice();
					var result = {};
					/*这里必须将ActiveX对象里的值转成字符串才能正确的在参数中传递*/
					result['infoAmount'] = this.kpdriver.InfoAmount+'';
					result['infoTaxAmount'] = this.kpdriver.InfoTaxAmount+'';
					//result['infoDate'] = new Date(this.kpdriver.InfoDate+'').Format('yyyy-MM-dd hh:mm:ss');
					result['infMonth'] = this.kpdriver.InfMonth+'';
					result['TypeCode'] = this.kpdriver.InfoTypeCode+'';//发票十位代码
					result['Number'] = this.kpdriver.InfoNumber+'';//发票号码
                    //result['TypeCode'] = 110+'';//发票十位代码
                    //result['Number'] = 120+'';//发票号码
					result['goodsListFlag'] = this.kpdriver.GoodsListFlag+'';
					result['retCode'] = this.kpdriver.RetCode+'';
					result['machineNo'] = this.kpdriver.MachineNo+'';
					result['RetCode'] = this.kpdriver.RetCode+'';
                    result['RetMsg'] = this.kpdriver.RetMsg+'';
                    //result['RetCode'] = 4011+'';
					console.log(result);
					//alert(result['retCode']);
					return result;
				}catch(e){
					alert('开票失败,错误信息:'+e.description);
                    return false;
				}		
			}
		
		//作废发票
		function cancelInv(infoKind,infoTypeCode,infoNumber){
			try{
				this.kpdriver.InfoKind = infoKind;
				this.kpdriver.InfoTypeCode = infoTypeCode;
				this.kpdriver.InfoNumber = infoNumber;
				this.kpdriver.CancelInv();
				var result = {};
				result['RetCode'] = this.kpdriver.RetCode+'';
				result['RetMsg'] = this.kpdriver.RetMsg+''; 
				return result;
			}catch(e){
				console.log(e);
				alert('作废失败'+e.description);
			}
		}
		
		//金税盘打印
		function printInv(infoKind,infoTypeCode,infoNumber,goodsListFlag,infoShowPrtDlg){
			try{
				this.kpdriver.InfoKind = infoKind;
				this.kpdriver.InfoTypeCode = infoTypeCode;//发票代码
				this.kpdriver.InfoNumber = infoNumber;//发票号码
				this.kpdriver.GoodsListFlag = goodsListFlag;//0 打发票  1打清单
				this.kpdriver.InfoShowPrtDlg = infoShowPrtDlg;
				this.kpdriver.PrintInv();
				alert(this.kpdriver.RetMsg);
				return this.kpdriver.RetCode;
			}catch(e){
				alert('打印失败');

			}
		}
		function saveSpflbm(bmbbh,ssflbm,sfxsyhzc,yhzcnr,lsvbs,qyzbm){
            //this.kpdriver = new ActiveXObject("TaxCardX.GoldTax");
            var goodsNoData = "<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
                "            <FPXT><INPUT>\n" +
                "            <GoodsNo>\n" +
                "            <GoodsNoVer>"+bmbbh+"</GoodsNoVer>\n" +
                "            <GoodsTaxNo>"+ssflbm+"</GoodsTaxNo >\n" +
                "            <TaxPre>"+sfxsyhzc+"</TaxPre >\n" +
                "            <TaxPreCon>"+yhzcnr+"</TaxPreCon>\n" +
                "            <ZeroTax>"+lsvbs+"</ZeroTax >\n" +
                "            <CropGoodsNo>"+qyzbm+"</CropGoodsNo>\n" +
                "            <TaxDeduction>"+""+"</TaxDeduction>\n" +
                "            </GoodsNo>\n" +
                "            </INPUT>\n" +
                "            </FPXT>";
			console.log(goodsNoData);
            var strBase64 = base64Encode(goodsNoData);
            //发送数据
            var strSendData = "<?xml version=\"1.0\" encoding=\"GBK\"?><FPXT_COM_INPUT><ID>1100</ID><DATA>" + strBase64 +
                "</DATA></FPXT_COM_INPUT>";
            //调用传入
            var recieveData = this.kpdriver.BatchUpload(strSendData);
            console.log("保存商品编码返回内容:"+recieveData);
            return recieveData;
		}

			// private property
			_keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";

			// public method for encoding
			 function base64Encode(input) {
				var output = "";
				var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
				var i = 0;
				input = _utf8_encode(input);
				while (i < input.length) {
					chr1 = input.charCodeAt(i++);
					chr2 = input.charCodeAt(i++);
					chr3 = input.charCodeAt(i++);
					enc1 = chr1 >> 2;
					enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
					enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
					enc4 = chr3 & 63;
					if (isNaN(chr2)) {
						enc3 = enc4 = 64;
					} else if (isNaN(chr3)) {
						enc4 = 64;
					}
					output = output +
						_keyStr.charAt(enc1) + _keyStr.charAt(enc2) +
						_keyStr.charAt(enc3) + _keyStr.charAt(enc4);
				}
				return output;
			}

		// public method for decoding
		function base64Decode(input) {
			var output = "";
			var chr1, chr2, chr3;
			var enc1, enc2, enc3, enc4;
			var i = 0;
			input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
			while (i < input.length) {
				enc1 = _keyStr.indexOf(input.charAt(i++));
				enc2 = _keyStr.indexOf(input.charAt(i++));
				enc3 = _keyStr.indexOf(input.charAt(i++));
				enc4 = _keyStr.indexOf(input.charAt(i++));
				chr1 = (enc1 << 2) | (enc2 >> 4);
				chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
				chr3 = ((enc3 & 3) << 6) | enc4;
				output = output + String.fromCharCode(chr1);
				if (enc3 != 64) {
					output = output + String.fromCharCode(chr2);
				}
				if (enc4 != 64) {
					output = output + String.fromCharCode(chr3);
				}
			}
			output = _utf8_decode(output);
			return output;
		}

		// private method for UTF-8 encoding
		 function _utf8_encode(string) {
			string = string.replace(/\r\n/g,"\n");
			var utftext = "";
			for (var n = 0; n < string.length; n++) {
				var c = string.charCodeAt(n);
				if (c < 128) {
					utftext += String.fromCharCode(c);
				} else if((c > 127) && (c < 2048)) {
					utftext += String.fromCharCode((c >> 6) | 192);
					utftext += String.fromCharCode((c & 63) | 128);
				} else {
					utftext += String.fromCharCode((c >> 12) | 224);
					utftext += String.fromCharCode(((c >> 6) & 63) | 128);
					utftext += String.fromCharCode((c & 63) | 128);
				}

			}
			return utftext;
		}

		// private method for UTF-8 decoding
		 function _utf8_decode(utftext) {
			var string = "";
			var i = 0;
			var c = c1 = c2 = 0;
			while ( i < utftext.length ) {
				c = utftext.charCodeAt(i);
				if (c < 128) {
					string += String.fromCharCode(c);
					i++;
				} else if((c > 191) && (c < 224)) {
					c2 = utftext.charCodeAt(i+1);
					string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
					i += 2;
				} else {
					c2 = utftext.charCodeAt(i+1);
					c3 = utftext.charCodeAt(i+2);
					string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
					i += 3;
				}
			}
			return string;
		}
