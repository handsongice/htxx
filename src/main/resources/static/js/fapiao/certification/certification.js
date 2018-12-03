//----------------------------- 认证    ---------------------------------------
  $("#pageshow").show();
var ymbb = "3.0.12";
var count=1;
var cert="";
var token = "";
var thickPass = "1234567890,111111111111111111111111,222222222222222222222222,333333333333333333333333,444444444444444444444444,555555555555555555555555,666666666666666666666666,777777777777777777777777,888888888888888888888888,999999999999999999999999,000000000000000000000000";
var aa = false;
var type = "0"

//mode	
//	0:认证;1:查询
function firstLogin(ptPassword,fpdm,fphm,mode){
	//var ptPassword = "12345678";
	if(mode == "0" || mode == '1'){
		type = mode;
	}else{
		return "方式调用错误";
	}
	var result = "";
	CryptCtrl.OpenDeviceEx(ptPassword);
	var dwFlag = 0;
	var b = CryptCtrl.ClientHello(dwFlag);
	var clientHello=CryptCtrl.strResult;
	var param1={"type":"CLIENT-HELLO","clientHello":clientHello,"ymbb":ymbb};
	$.ajax({
		 type:"post",
		    url:"https://fpdk.qd-n-tax.gov.cn/SbsqWW/login.do",
		    data:param1,
		    async:false,
		    dataType:"jsonp",
		    contentType:"application/x-www-form-urlencoded;charset=utf-8;",
		    success:function(jsonData){
		      
		      var key1=jsonData.key1;

		      if(key1=="00"){

		           ("<div id='popup_message'>服务器调用身份认证失败！"+jsonData.key2+" 正在重试......</div>","提示");
		      }else if(key1=="01"){
		        serverPacket=jsonData.key2;
		        serverRandom=jsonData.key3;
		        CryptCtrl.ClientAuth(serverPacket);
		        var clientAuthCode = CryptCtrl.strResult;
		        CryptCtrl.GetCertInfo("",71);
		        cert = CryptCtrl.strResult;
		        var param2={};
		        if(param1.mmtype=="1"){
		          param2={
		            "type":"CLIENT-AUTH",
		            "clientAuthCode":clientAuthCode,
		            "serverRandom":serverRandom,
		            "password":ptPassword,
		            "cert":cert,
		            "ymbb":ymbb,
		            "mmtype":"1"
		          };
		        }else if(param1.mmtype=="2") {
		          param2 = {
		            "type": "CLIENT-AUTH",
		            "clientAuthCode": clientAuthCode,
		            "serverRandom": serverRandom,
		            "password": ptPassword,
		            "cert": cert,
		            "ymbb": ymbb,
		            "mmtype": "2",
		            "answer": param1.answer
		          };
		        }else{
		          param2={
		            "type":"CLIENT-AUTH",
		            "clientAuthCode":clientAuthCode,
		            "serverRandom":serverRandom,
		            "password":ptPassword,
		            "cert":cert,
		            "ymbb":ymbb
		          };
		        }
//		       第二步
		      result = secondLogin(param2,cert,fpdm,fphm);
		      return result;
//		       第二步 
		      }else{
		        ("<div id='popup_message'>系统异常，错误代码为:"+key1+"</div>","提示");
		      }
		    },error:function(data){
		      console.log("error");
		      if(data.responseText==""||data.responseText==undefined){
		        ("<div id='popup_message'>网络异常，正在重试......</div>","提示");
		      }else{
		        ("<div id='popup_message'>系统异常，正在重试......，统一受理系统报文为:"+data.responseText+"</div>","提示");
		      }      
//		      cb(data);
		      result = "登陆第一步出错";
		      return result;
		    }
	})
	return result;
};


function secondLogin(param2,cert,fpdm,fphm){
	var result = "";
	console.log("secondLogin");
	 $.ajax({
         type:"post",
         url:"https://fpdk.qd-n-tax.gov.cn/SbsqWW/login.do",
         data:param2,
         async:false, 
         dataType:"jsonp",
         timeout:TIMEOUT,
         contentType:"application/x-www-form-urlencoded;charset=utf-8",
         jsonp:"callback",
         success:function(backData){
           var rezt=backData.key1;
           if(param2.mmtype=="2"){
             var key1=backData.key1;
             if(key1=="00"){
               ("<div id='popup_message'>答案错误！</div>","提示");
               return;
             }else if (key1=="01") {
               token=backData.key2;
               clearCookie("token");
               setCookie("token",token,seconds);
               ("<div id='popup_message'>平台密码已成功取消，如需要，请登录平台后重新设置平台密码！</div>","提示",function(r){
                 window.location.href = getDomainName();
               });

             }else if(key1=='09'){
               ("<div id='popup_message'>会话已超时，请重新登陆！</div>","提示",function(r) {
                 if(r){
                   window.location.href=getDomainName();
                 }
               });
             }else{
               ("<div id='popup_message'>系统异常，错误代码为:"+key1+"</div>","提示");
             }

             $('.theme-poptit2 .close').trigger('click');
           }
           if(rezt=="00"){
                 ("<div id='popup_message'>登录失败！"+backData.key2+" 正在重试......</div>","提示");
           }else if(rezt=="01") {
             if (param2.mmtype="1") {
               var key1=backData.key1;
               if(key1=="00"){
                 ("<div id='popup_message'>数据库异常！</div>","提示");
                 return;
               }else if (key1=="01") {
                 token=backData.key3;

                 clearCookie("token");
                 setCookie("token",token,seconds);

                 $('#question').val(decodeURI(backData.key2,"UTF-8"));

               }else if(key1=='09'){
                 ("<div id='popup_message'>会话已超时，请重新登陆！</div>","提示",function(r) {
                   if(r){
                    window.location.href=getDomainName();
                   }
                 });
               }else{
                ("<div id='popup_message'>系统异常，错误代码为:"+key1+"</div>","提示");
               }
             } else{
               var nsrmc = decodeURI(backData.key3, "UTF-8");
             var dqrq = backData.key4;
             if (dqrq == "") {
               dqrq = getDqrq();
             }
             setCookie("dqrq", dqrq, seconds);
             setCookie("nsrmc", nsrmc, seconds);
             clearCookie("token");
             setCookie("token", backData.key2, seconds);

             window.location.href = "config.bd0e3c5f.html";
           }
           }else if(rezt=="02"){
             var nsrsbh=backData.key2;
             ("<div id='popup_message'>纳税人档案（税号："+nsrsbh+"）信息不存在！<br/>请确认本企业是否属于取消认证政策的纳税人。<br/>如是，则请联系主管税务机关进行核实和补录相关档案信息！</div>","提示"); 
           }else if(rezt=="12"){//添加档案更新日志
             clearCookie("token");
             setCookie("token",backData.key2,seconds);
             var nsrsbh=backData.key3;
             var xyjb=backData.key5;
             if(xyjb==""||xyjb=="null"){
               xyjb ="未设置"
             }
             ("<div id='popup_message'>纳税人档案信息为（税号："+nsrsbh+"；信用等级："+xyjb+"）！<br/>请确认本企业是否属于取消认证政策的纳税人。<br/>如是，则请联系主管税务机关进行核实和修改相关档案信息！</div>","提示");
           }else if(rezt=="13"){
             var nsrsbh=backData.key2;
             ("<div id='popup_message'>纳税人档案信息为（税号："+nsrsbh+"）为特定企业！<br/>特定企业不允许进行网上发票认证！<br/>如有疑问，请联系主管税务机关进行核实和修改相关档案信息！</div>","提示");
           }else if(rezt=="03"){
         	 token=backData.key2;
             var nsrmc=decodeURI(backData.key3,"UTF-8");
             var dqrq=backData.key4;        
             setCookie("dqrq",dqrq,seconds);
             setCookie("nsrmc",nsrmc,seconds);
             setCookie("token",token,seconds);
             
             var param3 = {
            		 "cert":cert,
            		 "token":backData.key2,
            		 "ymbb":ymbb,
            		 "rznf":""
             }
            result = threeLogin(param3,fpdm,fphm);
            return result;
           }else if(rezt=="04"){
             token=backData.key2;
             setCookie("token",token,seconds);
             ("<div id='popup_message'>平台密码不正确！</div>","提示");
           }else if(rezt=="05"){
             token=backData.key2;
             setCookie("token",token,seconds);
             ("<div id='popup_message'>平台密码错误次数超过十次，请联系税务机关解锁或明天再试！</div>","提示");
           }else if(rezt=="08"){
             $('#ptmm').show();
             $('#ptmmTs').show();
           }else if(rezt=="21"){//添加档案更新日志
             clearCookie("token");
             setCookie("token",backData.key2,seconds)
             var xyjb=backData.key4;
             if(xyjb==""||xyjb=="null"){
               xyjb ="未设置"
             }
             ("<div id='popup_message'>纳税人档案信息为(税号："+backData.key3+")档案信息存在，当前信用级别为："+xyjb+",本平台启用状态为：未启用,无权登录此系统，请联系主管税务机关开通权限！</div>","提示");
           }else if(rezt=="98"){
             ("<div id='popup_message'>网络调用异常，请重新登录！</div>","提示");
           }else if(rezt=="99"){
             ("<div id='popup_message'>网络调用超时，请重新登录！</div>","提示");
           }else if(rezt=="101"){
             ("<div id='popup_message'>数据库连接失败,请重新登录！</div>","提示");
           }else{
             ("<div id='popup_message'>系统异常，错误代码为:"+rezt+"</div>","提示");
           }
         },error:function(data){
           if(data.responseText==""||data.responseText==undefined){
             ("<div id='popup_message'>网络异常，正在重试......</div>","提示");
           }else{
             ("<div id='popup_message'>网络异常，正在重试......,统一受理系统报文为:"+data.responseText+"</div>","提示");
           }
           result = "登陆第二步出错";
           return result;
         }
       });
}

function threeLogin(param3,fpdm,fphm){
	var result = "";
	$.ajax({
        type:"post",
        url:"https://fpdk.qd-n-tax.gov.cn/SbsqWW/qrgycx.do",
        data:param3,
        async:false, 
        dataType:"jsonp",
        contentType:"application/x-www-form-urlencoded;charset=utf-8",
        jsonp:"callback",
        success:function(backData){
        	token = backData.key4;
        	clearCookie("token");
            setCookie("token",backData.key2,seconds);
            result = fpQuery(fpdm,fphm);
            return result;
        },error:function(data){
        	console.log("threeLogin error");
        	result = "登陆第三步出错";
        	return result;
        }
      });
//	return result;
}

function fpQuery(fpdm,fphm){
	var result = "";
	var aoData = "[{'name':'sEcho','value':1},{'name':'iColumns','value':14},{'name':'sColumns','value':',,,,,,,,,,,,,'},{'name':'iDisplayStart','value':0},{'name':'iDisplayLength','value':50},{'name':'mDataProp_0','value':0},{'name':'mDataProp_1','value':1},{'name':'mDataProp_2','value':2},{'name':'mDataProp_3','value':3},{'name':'mDataProp_4','value':4},{'name':'mDataProp_5','value':5},{'name':'mDataProp_6','value':6},{'name':'mDataProp_7','value':7},{'name':'mDataProp_8','value':8},{'name':'mDataProp_9','value':9},{'name':'mDataProp_10','value':10},{'name':'mDataProp_11','value':11},{'name':'mDataProp_12','value':12},{'name':'mDataProp_13','value':13}]";
	var query_param = {
			"fpdm":"",
			"fphm":"",
			"rq_q":"2017-11-01",
			"rq_z":"2017-11-07",
			"xfsbh":"",
			"rzzt":"0",
			"gxzt":"-1",
			"rzfs":"-1",
			"fpzt":"0",
			"fplx":"-1",
			"cert":cert,
			"token":token,
			"ymbb":ymbb,
			"aoData":aoData
		}
	$.ajax({
        type:"post",
        url:"https://fpdk.qd-n-tax.gov.cn/SbsqWW/gxcx.do",
        data:query_param,
        async:false, 
        dataType:"jsonp",
        contentType:"application/x-www-form-urlencoded;charset=utf-8",
        jsonp:"callback",
        success:function(backData){
        	token = backData.key3;
        	if(type == "1"){
        		console.log("type == 1");
        		console.log(backData.key2);
        		result = backData.key2.aaData+"";
        		console.log(result)
        		$.ajax({
        			url:"http://localhost:8080/LQXT/sasa",
        			type:"post",
        			data:{"result":result},
        			dataType:"json",
        			success:function(){
        				console.log("sa suu");
        			},
        			error:function(){
        				console.log("sa err");
        			}
        		})
        	}else{
        		console.log("type == 0");
//        		result = fpCheck(fpdm,fphm);
        		return result;
        	}
        },error:function(data){
        	console.log("fpQuery error");
        	result = "查询出错";
        	return result;
        }
      });
	return result;
}
function fpQuery1(fpdm,fphm,rq_q,rq_z,flag,basePath){
    var result = "[[\"0=0=0=0\",\"3702143160\",\"00225874\",\"2017-11-07\",\"青岛市市北国家税务局\",\"12994.17\",\"389.83\",\"0\",\"0\",null,\"0\",null,\"0\",null,\"37020500DK00213\"],[\"0=0=0=0\",\"3100173130\",\"10253137\",\"2017-11-07\",\"上海亚润酒店管理有限公司\",\"345.28\",\"20.72\",\"0\",\"0\",null,\"0\",null,\"0\",null,\"91310108684009849G\"],[\"0=0=0=0\",\"3702144160\",\"00907102\",\"2017-11-07\",\"青岛市李沧国家税务局\",\"1739.81\",\"52.19\",\"0\",\"0\",null,\"0\",null,\"0\",null,\"37020600DK01216\"],[\"0=0=0=0\",\"3702163160\",\"00292210\",\"2017-11-07\",\"青岛市市南国家税务局代开发票自助机3号\",\"9514.56\",\"285.44\",\"0\",\"0\",null,\"0\",null,\"0\",null,\"37020200DK00483\"],[\"0=0=0=0\",\"3702163130\",\"12474034\",\"2017-11-06\",\"青岛瑞升达信息技术有限公司\",\"2852.83\",\"171.17\",\"0\",\"0\",null,\"0\",null,\"0\",null,\"91370203MA3CE8E6XL\"],[\"0=0=0=0\",\"3702164130\",\"00286103\",\"2017-11-06\",\"青岛银硕电子支付有限公司\",\"35823.69\",\"1074.71\",\"0\",\"0\",null,\"0\",null,\"0\",null,\"91370214MA3C9D4A51\"],[\"0=0=0=0\",\"3702163160\",\"00235172\",\"2017-11-06\",\"胶南自助代开机2\",\"2609.71\",\"78.29\",\"0\",\"0\",null,\"0\",null,\"0\",null,\"37028400DK00440\"],[\"0=0=0=0\",\"3702163130\",\"12300329\",\"2017-11-06\",\"青岛众联卓越商贸有限公司\",\"9611.65\",\"288.35\",\"0\",\"0\",null,\"0\",null,\"0\",null,\"91370203MA3C8CW38E\"],[\"0=0=0=0\",\"3702163130\",\"12300328\",\"2017-11-06\",\"青岛众联卓越商贸有限公司\",\"9611.65\",\"288.35\",\"0\",\"0\",null,\"0\",null,\"0\",null,\"91370203MA3C8CW38E\"],[\"0=0=0=0\",\"3702163130\",\"12300327\",\"2017-11-06\",\"青岛众联卓越商贸有限公司\",\"9611.65\",\"288.35\",\"0\",\"0\",null,\"0\",null,\"0\",null,\"91370203MA3C8CW38E\"],[\"0=0=0=0\",\"3702163130\",\"12300326\",\"2017-11-06\",\"青岛众联卓越商贸有限公司\",\"9611.65\",\"288.35\",\"0\",\"0\",null,\"0\",null,\"0\",null,\"91370203MA3C8CW38E\"],[\"0=0=0=0\",\"3702163130\",\"12300330\",\"2017-11-06\",\"青岛众联卓越商贸有限公司\",\"4793.01\",\"143.79\",\"0\",\"0\",null,\"0\",null,\"0\",null,\"91370203MA3C8CW38E\"],[\"0=0=0=0\",\"3702163130\",\"01515425\",\"2017-11-05\",\"青岛赛晟嘉悦工程科技有限公司\",\"978.64\",\"29.36\",\"0\",\"0\",null,\"0\",null,\"0\",null,\"91370203325942557B\"],[\"0=0=0=0\",\"3702163160\",\"00235147\",\"2017-11-03\",\"胶南自助代开机2\",\"3207.77\",\"96.23\",\"0\",\"0\",null,\"0\",null,\"0\",null,\"37028400DK00440\"]]";
    // var aoData = "[{'name':'sEcho','value':1},{'name':'iColumns','value':14},{'name':'sColumns','value':',,,,,,,,,,,,,'},{'name':'iDisplayStart','value':0},{'name':'iDisplayLength','value':50},{'name':'mDataProp_0','value':0},{'name':'mDataProp_1','value':1},{'name':'mDataProp_2','value':2},{'name':'mDataProp_3','value':3},{'name':'mDataProp_4','value':4},{'name':'mDataProp_5','value':5},{'name':'mDataProp_6','value':6},{'name':'mDataProp_7','value':7},{'name':'mDataProp_8','value':8},{'name':'mDataProp_9','value':9},{'name':'mDataProp_10','value':10},{'name':'mDataProp_11','value':11},{'name':'mDataProp_12','value':12},{'name':'mDataProp_13','value':13}]";
    // var query_param = {
    //     "fpdm":fpdm,
    //     "fphm":fphm,
    //     "rq_q":rq_q,
    //     "rq_z":rq_z,
    //     "xfsbh":"",
    //     "rzzt":"0",
    //     "gxzt":"-1",
    //     "rzfs":"-1",
    //     "fpzt":"0",
    //     "fplx":"-1",
    //     "cert":cert,
    //     "token":token,
    //     "ymbb":ymbb,
    //     "aoData":aoData
    // }
    // $.ajax({
    //     type:"post",
    //     url:"https://fpdk.qd-n-tax.gov.cn/SbsqWW/gxcx.do",
    //     data:query_param,
    //     async:false,
    //     dataType:"jsonp",
    //     contentType:"application/x-www-form-urlencoded;charset=utf-8",
    //     jsonp:"callback",
    //     success:function(backData){
    //         token = backData.key3;
    //         if(type == "1"){
    //             console.log("type == 1");
    //             console.log(backData.key2);
    //             result = backData.key2.aaData+"";
                console.log(result);
                $.ajax({
                    url:basePath+"cf/updateInvoiceState",
                    type:"post",
                    data:{"result":result,"flag":flag},
                    dataType:"json",
                    async:false,
                    success:function(){
                        alert("查询更新操作完成");
                    },
                    error:function(){
                        alert("查询更新操作异常");
                    }
                });
//             }else{
//                 console.log("type == 0");
// //        		result = fpCheck(fpdm,fphm);
//                 return result;
//             }
//         },error:function(data){
//             console.log("fpQuery error");
//             result = "查询出错";
//             return result;
//         }
//     });
    // return result;
}

//获取异常发票信息
function getUnNormalInvoice(tjyf,basePath){
    var result = "";
    // var check_param = {
    //     "cert":cert,
		// "token":token,
    //     "tjyf":tjyf,
    //     "oper":"tj",
    //     "ymbb":ymbb
    // }
    // $.ajax({
    //     type:"post",
    //     url:"https://fpdk.qd-n-tax.gov.cn/SbsqWW/dktj.do",
    //     data:check_param,
    //     async:false,
    //     dataType:"jsonp",
    //     contentType:"application/x-www-form-urlencoded;charset=utf-8",
    //     jsonp:"callback",
    //     success:function(backData){
    //         console.log(backData);
    //         token = backData.key3;
		// 	if(backData.key1 == "20"){
				//result = backData.key5+"";
            	console.log(result);
				$.ajax({
					url:basePath+"cf/updateAllIdentityInvoice",
					type:"post",
					data:{"result":result},
					dataType:"json",
					success:function(){
						console.log(tjyf+"的异常查询更新操作完成");
					},
					error:function(){
                        console.log(tjyf+"的异常查询更新操作异常");
					}
				});
//             }else{
// //        		result = fpCheck(fpdm,fphm);
//                 return result;
//             }
//         },error:function(data){
//             alert("获取发票信息异常");
//             result = "勾选出错出错";
//         }
//     });
//     return result;
}


function fpCheck(fpdm,fphm){
	var result = "";
	var check_param = {
			"fpdm":fpdm,
			"fphm":fphm,
			"kprq":"20171107",
			"zt":"1=0",
			"cert":cert,
			"token":token,
			"ymbb":ymbb
		}
	$.ajax({
        type:"post",
        url:"https://fpdk.qd-n-tax.gov.cn/SbsqWW/gxtj.do",
        data:check_param,
        async:false, 
        dataType:"jsonp",
        contentType:"application/x-www-form-urlencoded;charset=utf-8",
        jsonp:"callback",
        success:function(backData){
        	console.log("fpCheck success");
        	token = backData.key2;
        	result = auth_one()
        },error:function(data){
        	console.log("fpCheck error");
        	result = "勾选出错出错";
        }
      });
	return result;
}

  function auth_one(){
	  var result = "";
	  var one_param = {
		  "id":"queryqrjg",
		  "qrzt":"1",
		  "key1":cert,
		  "key2":token,
		  "ymbb":ymbb,
		  "aoData":'[{"name":"sEcho","value":2},{"name":"iColumns","value":11},{"name":"sColumns","value":",,,,,,,,,,"},{"name":"iDisplayStart","value":0},{"name":"iDisplayLength","value":50},{"name":"mDataProp_0","value":0},{"name":"mDataProp_1","value":1},{"name":"mDataProp_2","value":2},{"name":"mDataProp_3","value":3},{"name":"mDataProp_4","value":4},{"name":"mDataProp_5","value":5},{"name":"mDataProp_6","value":6},{"name":"mDataProp_7","value":7},{"name":"mDataProp_8","value":8},{"name":"mDataProp_9","value":9},{"name":"mDataProp_10","value":10}]'
	  }
	  $.ajax({
	        type:"post",
	        url:"https://fpdk.qd-n-tax.gov.cn/SbsqWW/qrgx.do",
	        data:one_param,
	        async:false, 
	        dataType:"jsonp",
	        contentType:"application/x-www-form-urlencoded;charset=utf-8",
	        jsonp:"callback",
	        success:function(backData){
	        	console.log("auth_one success");
	        	console.log(backData.key1);
	        	token = backData.token;
	        	result = auth_two()
	        },error:function(data){
	        	console.log("auth_one error");
	        	result = "认证第一步出错";
	        }
	      });
	  return result;
  }
 
  function auth_two(){
	  var result = "";
	  var two_param = {
			  "id":"queryqrhz",
			  "key1":cert,
			  "key2":token,
			  "ymbb":ymbb
	  }
	  $.ajax({
	        type:"post",
	        url:"https://fpdk.qd-n-tax.gov.cn/SbsqWW/qrgx.do",
	        data:two_param,
	        async:false, 
	        dataType:"jsonp",
	        contentType:"application/x-www-form-urlencoded;charset=utf-8",
	        jsonp:"callback",
	        success:function(backData){
	        	console.log("two success");
	        	var signature = backData.key2.split("*")[2];
	        	token = backData.key3;
	        	result = auth_three(signature)
	        },error:function(data){
	        	console.log("two error");
	        	result = "认证第二步出错";
	        }
	      });
	  return result;
  }
  
  function auth_three(signature){
	  var result = "";
	  var three_param = {
			  "id":"commitqrxx",
			  "key1":cert,
			  "key2":token,
			  "signature":signature,
			  "ymbb":ymbb
	  }
	  $.ajax({
	        type:"post",
	        url:"https://fpdk.qd-n-tax.gov.cn/SbsqWW/qrgx.do",
	        data:three_param,
	        async:false, 
	        dataType:"jsonp",
	        contentType:"application/x-www-form-urlencoded;charset=utf-8",
	        jsonp:"callback",
	        success:function(backData){
	        	console.log("three success");
	        	result = "认证成功";
	        },error:function(data){
	        	console.log("three success");
	        	result = "认证失败";
	        }
	      });
	  return result;
  }
  