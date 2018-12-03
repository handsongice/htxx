
function openCard(){
	var result={};
	this.checkSetup();
	if (this.isOpen){
		result.flag= true;
		//return true;
	}
	if (!this.isSetup){
		result.flag= false;
		//return false;
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
		  result.nsrsbh = this.kpdriver.TaxCode;
		  result.fjh = this.kpdriver.MachineNo;
		  result.flag= true;
		  alert("成功开启金税盘！");
		  //return true;
	    }else if (this.kpdriver.RetCode=="1007"){
	      alert("1.金税卡已经被占用，请退出所有可能使用金税卡的程序后重试\n2.若使用升级版开票系统请确认证书口令参数是否正确，重启浏览器后重试\n3.使用升级版开票系统，若重试2次后仍报错，请登录开票系统清零口令失败次数，以防证书锁死");
	      result.flag= false;
	      //return false;
	    }else if (this.kpdriver.RetCode=="1001"){
	      alert("开票信息初始化失败，请重启计算机或重装开票系统后重试");
	      result.flag= false;
	      //return false;
	    }else if (this.kpdriver.RetCode=="1005"){
	    	alert("金税卡开启失败，错误代码1005："+this.kpdriver.RetCode);
			closeCard();
			result.flag= false;
			//return false;
	    }else {
	      alert("金税卡开启失败，错误代码："+this.kpdriver.RetCode);
	      alert(this.kpdriver.RetMsg);
	      closeCard();
	      result.flag= false;
	      // return false;
	    }
	    
	}catch(e) {
		alert("开票组件出错，错误信息为openCard()："+e.description);
		closeCard();
		result.flag= false;
		//return false;
	}
	return result;
}
//金税盘开启检测
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
		//查询税号和开票机号
		function getTax(){
			this.kpdriver.OpenCard();
			this.isOpen = true;
			var result = {};
			result.nsrsbh = this.kpdriver.TaxCode;
			result.fjh = this.kpdriver.MachineNo;
			result.isopen = true;
			return result;
		}
		
		//查询库存发票
		function getInfo(infoKind){
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
		function invoice(invoiceInfo){
			
				try{
					this.kpdriver.InvInfoInit();
					if (invoiceInfo['infoclientname']!=null){
						this.kpdriver.InfoClientName = invoiceInfo['infoclientname'];
					}
					if (invoiceInfo['infoclienttaxcode']!=null){
						this.kpdriver.InfoClientTaxCode = invoiceInfo['infoclienttaxcode'];
					}
					if (invoiceInfo['infoclientbankaccount']!=null){
						this.kpdriver.InfoClientBankAccount = invoiceInfo['infoclientbankaccount'];
					}
					if (invoiceInfo['infoclientaddressphone']!=null){
						this.kpdriver.InfoClientAddressPhone = invoiceInfo['infoclientaddressphone'];
					}
					if (invoiceInfo['infosellerbankaccount']!=null){
						this.kpdriver.InfoSellerBankAccount = invoiceInfo['infosellerbankaccount'];
					}
					if (invoiceInfo['infoselleraddressphone']!=null){
						this.kpdriver.InfoSellerAddressPhone = invoiceInfo['infoselleraddressphone'];
					}
					if (invoiceInfo['infonotes']!=null){
						this.kpdriver.InfoNotes = invoiceInfo['infonotes'];
					}
					if (invoiceInfo['infoinvoicer']!=null){
						this.kpdriver.InfoInvoicer = invoiceInfo['infoinvoicer'];
					}
					if (invoiceInfo['infochecker']!=null){
						this.kpdriver.InfoChecker = invoiceInfo['infochecker'];
					}
					if (invoiceInfo['infocashier']!=null){
						this.kpdriver.InfoCashier = invoiceInfo['infocashier'];
					}
					//if (invoiceInfo['infolistname']!=null){
					//	this.kpdriver.InfoListName = invoiceInfo['infolistname'];
					//}
					if (invoiceInfo['infobillnumber']!=null){
						this.kpdriver.InfoBillNumber = invoiceInfo['infobillnumber'];
					}
					if (invoiceInfo['infoKind']!=null){
						this.kpdriver.InfoKind = invoiceInfo['infoKind'];
					}
					
	/*
	alert('this.kpdriver.InfoClientName='+this.kpdriver.InfoClientName+'\nthis.kpdriver.InfoClientTaxCode='+this.kpdriver.InfoClientTaxCode+'\nthis.kpdriver.InfoClientBankAccount='+this.kpdriver.InfoClientBankAccount
			+'\nthis.kpdriver.InfoClientAddressPhone='+this.kpdriver.InfoClientAddressPhone+'\nthis.kpdriver.InfoSellerBankAccount='+this.kpdriver.InfoSellerBankAccount+'\nthis.kpdriver.InfoSellerAddressPhone='+this.kpdriver.InfoSellerAddressPhone
			+'\nthis.kpdriver.InfoTaxRate='+this.kpdriver.InfoTaxRate+'\nthis.kpdriver.InfoNotes='+this.kpdriver.InfoNotes+'\nthis.kpdriver.InfoInvoicer='+this.kpdriver.InfoInvoicer
			+'\nthis.kpdriver.InfoChecker='+this.kpdriver.InfoChecker+'\nthis.kpdriver.InfoCashier='+this.kpdriver.InfoCashier+'\nthis.kpdriver.InfoListName='+this.kpdriver.InfoListName);
			*/
					this.kpdriver.InvListInit();
					this.kpdriver.ClearInvList();
					var list = invoiceInfo['infolistname'];
					for (var i in list){
						//alert(11111);
						if(list[i]['zkflag']){
							console.log("折扣行");
							this.kpdriver.InvListInit();
							if (list[i]['listgoodsname']!=null){
								this.kpdriver.ListGoodsName = list[i]['listgoodsname'];
							}
							if (list[i]['listamount']!=null){
								this.kpdriver.ListAmount = list[i]['listamount'];
							}
							if (list[i]['listpricekind']!=null){
								this.kpdriver.ListPriceKind = list[i]['listpricekind'];
							}
							if (list[i]['listtaxamount']!=null){
								this.kpdriver.ListTaxAmount = list[i]['listtaxamount'];
							}
							this.kpdriver.AddInvList();
						}else{
							console.log("非折扣行");
							var saveSpflbmflag = saveSpflbm(list[i]['bmbbh'],list[i]['ssflbm'],list[i]['sfxsyhzc'],list[i]['yhzcnr'],list[i]['lsvbs'],list[i]['qyzbm']);
							console.log("保存flag:"+saveSpflbmflag);
							if (list[i]['taxrate']!=null){
								this.kpdriver.InfoTaxRate = list[i]['taxrate'];
							}
							if (list[i]['listgoodsname']!=null){
								this.kpdriver.ListGoodsName = list[i]['listgoodsname'];
							}
							if (list[i]['listtaxitem']!=null){
								this.kpdriver.ListTaxItem = list[i]['listtaxitem'];
							}
							if (list[i]['liststandard']!=null){
								this.kpdriver.ListStandard = list[i]['liststandard'];
							}
							if (list[i]['listunit']!=null){
								this.kpdriver.ListUnit = list[i]['listunit'];
							}
							if (list[i]['listnumber']!=null){
								this.kpdriver.ListNumber = list[i]['listnumber'];
							}
							if (list[i]['listprice']!=null){
								this.kpdriver.ListPrice = list[i]['listprice'];
							}
							if (list[i]['listamount']!=null){
								this.kpdriver.ListAmount = list[i]['listamount'];
							}
							if (list[i]['listpricekind']!=null){
								this.kpdriver.ListPriceKind = list[i]['listpricekind'];
							}
							if (list[i]['listtaxamount']!=null){
								this.kpdriver.ListTaxAmount = list[i]['listtaxamount'];
							}
						
							this.kpdriver.AddInvList();
						}
						
	/*
	alert('this.kpdriver.ListGoodsName='+this.kpdriver.ListGoodsName+'\nthis.kpdriver.ListTaxItem='+this.kpdriver.ListTaxItem+'\nthis.kpdriver.ListStandard='+this.kpdriver.ListStandard
			+'\nthis.kpdriver.ListUnit='+this.kpdriver.ListUnit+'\nthis.kpdriver.ListNumber='+this.kpdriver.ListNumber+'\nthis.kpdriver.ListPrice='+this.kpdriver.ListPrice
			+'\nthis.kpdriver.ListAmount='+this.kpdriver.ListAmount+'\nthis.kpdriver.ListPriceKind='+this.kpdriver.ListPriceKind+'\nthis.kpdriver.ListTaxAmount='+this.kpdriver.ListTaxAmount);
	*/
						
						/*this.kpdriver.ListStandard ='';
						this.kpdriver.ListUnit ='';
						this.kpdriver.ListPrice=0;
						this.kpdriver.ListNumber=0;
						this.kpdriver.ListAmount=0;*/
					}
					//alert("开始打印呢发票啦！！！！！！！！！！！");
					if(saveSpflbmflag){
						this.kpdriver.Invoice();
					
					//this.kpdriver.Invoice();
					var result = {};
					/*这里必须将ActiveX对象里的值转成字符串才能正确的在参数中传递*/
					result['infoAmount'] = this.kpdriver.InfoAmount+'';
					result['infoTaxAmount'] = this.kpdriver.InfoTaxAmount+'';
					//result['infoDate'] = new Date(this.kpdriver.InfoDate+'').Format('yyyy-MM-dd hh:mm:ss');
					result['infMonth'] = this.kpdriver.InfMonth+'';
					result['TypeCode'] = this.kpdriver.InfoTypeCode+'';
					result['Number'] = this.kpdriver.InfoNumber+'';
					result['goodsListFlag'] = this.kpdriver.GoodsListFlag+'';
					result['retCode'] = this.kpdriver.RetCode+'';
					result['machineNo'] = this.kpdriver.MachineNo+'';
					result['RetCode'] = this.kpdriver.RetCode+'';
					result['RetMsg'] = this.kpdriver.RetMsg+'';
					return result;
					}else{
						return "开票失败";
					}
				}catch(e){
					alert('开票失败,错误信息:'+e.description);
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
				this.kpdriver.GoodsListFlag = goodsListFlag;
				this.kpdriver.InfoShowPrtDlg = infoShowPrtDlg;
				console.log("打印发票："+infoKind+"::"+infoTypeCode+"::"+infoNumber+"::"+goodsListFlag);
				this.kpdriver.PrintInv();
				alert(this.kpdriver.RetMsg);
				return this.kpdriver.RetCode;
			}catch(e){
				alert('打印失败');
			}
		}
		//税盘商品分类编码
		function saveSpflbm(bmbbh,ssflbm,sfxsyhzc,yhzcnr,lsvbs,qyzbm){
            //this.kpdriver = new ActiveXObject("TaxCardX.GoldTax");
			var flag = false;
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
           // var strBase64 = base64Encode(goodsNoData);  
            
			var strBase64;
            $.ajax({
        		type : 'POST',
        		url : basePathUrl+'business/xxfp/saveSpflbm',
        		dataType:"json",
        		async: false, 
        		data:{bmbbh:bmbbh,ssflbm:ssflbm,sfxsyhzc:sfxsyhzc,yhzcnr:yhzcnr,lsvbs:lsvbs,qyzbm:qyzbm},
        		success : function(data) {
        			console.info(data);
        			if(data.flag){
	        			strBase64 = data.obj;
	        			BatchUpload(strBase64);
	        			flag =  true;
        			}else{
	    				alert(data.message);
	    				flag =  false;
    			}
        		},
        		error : function(error) {
        			console.info(error);
        			alert("税收分类编码保存失败");
        			flag =  false;
        		}
        		
        	});
           return flag;
            //发送数据
            /*var strSendData = "<?xml version=\"1.0\" encoding=\"GBK\"?><FPXT_COM_INPUT><ID>1100</ID><DATA>" + strBase64 +
                "</DATA></FPXT_COM_INPUT>";
            //调用传入
            console.log(strSendData);
            var recieveData = this.kpdriver.BatchUpload(strSendData);
            console.log("保存商品编码返回内容:"+recieveData);
            return recieveData;*/
		}
		function BatchUpload(strBase64){
			 console.log("----------:"+strBase64);
	            var strSendData = "<?xml version=\"1.0\" encoding=\"GBK\"?><FPXT_COM_INPUT><ID>1100</ID><DATA>" + strBase64 +
	            "</DATA></FPXT_COM_INPUT>";
		        //调用传入
		        console.log(strSendData);
		        var recieveData = this.kpdriver.BatchUpload(strSendData);
		        console.log("保存商品编码返回内容:"+recieveData);
		        return true;
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
	 
		 