mQingdanArray = null;
mIsQdFlag = false;
var mCurrentQdListReady = 0;
/* 弹出清单 */
function qingdan(){
	console.info("into click modal");
	//$("#qingdan-list").modal('show');
	 
	qdwin.window('open'); 
	//清空旧清单数据
	$(".qiandan-tr").each(function(index,ele){
		$(this).remove();
	});
	// $('#qd_fpdm').html(infoTypeCode);
	// $('#qd_fphm').html(infoNumber);
	mCurrentLineNo = 1;
	mCurrentQdbz = 1;
	console.log(mCurrentQdbz);
	//当有清单的时候，发票是不能增行减行的
	mCurrentQdListReady = 0;
	var array= xxfpItemToQDfpxxItem();
	
	showQingdanItem(array);
};
/* 弹出折扣 */
$("#fpitem_zhekou").click(function(){
	//console.info("into click modal");
	$("#zhekou-list").modal('show');
});
//删除一行 发票页面
function xxfpdelline(){
	console.log("----------:"+mCurrentLineNo);
	if(mCurrentLineNo==-1){
		alert('已无商品信息');
		return;
	}
	//$("#xxfp_table_item").find(".qd_fpitem_radio")
	//$('input:radio[name=xxfp-line-radio]')[0].checked = true;
	$radio = $('input[name="xxfp-line-radio"]:checked ');
	if($radio.length==0){
		return;
	}

	$tr = $radio.parent().parent();
	/*//如果是折扣行被删除了 , 那么需要删除它本身商品行的只读属性
	if($tr.hasClass("zhekou")){
		delZhekouLineHappen($tr.prev());
	}
	//如果是有折扣行的商品行被删除 , 那么折扣行也被删除
	if($tr.hasClass("hasZhekou")){
		$tr.next().remove();
	}*/
	$tr.remove();
	mCurrentLineNo--;
}

	//添加销项发票明细
	function fpmxArrayPush(array,qdbz){
		//数据是否正确标识
		var dataRight = 1;
		// qdbz = 清单标识
		console.log("=========~");
        console.log("明细行数量"+$(".fpitem_line").length);
		if($(".fpitem_line").length>8|| qdbz==1){
			$(".qiandan-tr").each(function(index,element){
				$tr = $(this);
				var spmc = $tr.find(".qd_fpitem_mc").val();
				var je = Number($tr.find(".qd_fpitem_price").val()).toFixed(2);;
				var ggxh = $tr.find(".qd_fpitem_ggxh").val();
				var jldw = $tr.find(".qd_fpitem_jldw").val();
				var number = $tr.find(".qd_fpitem_number").val();
				var dj =  $tr.find(".qd_fpitem_dj").val();
				var price = $tr.find(".qd_fpitem_price").val();
				var slv = $tr.find(".qd_fpitem_slv").text();
				if(slv=="免税"){
					slv = 0;
				}else{
					slv = Number(slv.substr(0, slv.length-1))/100
				}
				var se = Number($tr.find(".qd_fpitem_shuie").val()).toFixed(2);
				var hsje = (Number(je)+Number(se)).toFixed(2);
				var spbm = $tr.find(".qd_fpitem_spbm").text();
				//商品编号
				var spbh = $tr.find(".qd_fpitem_spbh").text();
				//优惠政策
				var yhzc = $tr.find(".qd_fpitem_yhzc").text();
				//优惠政策类型
				var yhzclx = $tr.find(".qd_fpitem_yhzclx").text();
				//零税率标识
				var zerotax = $tr.find(".qd_fpitem_zerotax").text();
				//编码版本号
				var bmbbh = $tr.find(".qd_fpitem_bmbbh").text();
				var pricecard = "0";
				if(spmc=="" && dj=="" && price=="" && slv=="" && se=="" ){//当一行数据都为空时
					dataRight = 0;
					return false;
				}
			/*	//当数据不完整时ggxh=="" || jldw=="" ||
				if($tr.hasClass("zhekou")){
					spmc = "折扣（"+$("#zhekou_lv").val()+"%）";
					pricecard = "1";
				}
				else{
					if((spmc=="" ||  dj=="" || price=="" || slv=="" || se=="")){
					//数据不完整处理
					layer.msg('第'+(index+1)+'行  数据不完整,请补充');
					dataRight = 0;
					return dataRight;
					}
				}*/
				
				
				var xxfpJson = {
						"pfhh":index+1,	
						"xmje":Number(je),
						//"sl":Number(slv.substr(0, slv.length-1))/100,  slv
                    	"sl":slv,
						"se":Number(se),
						"xmmc":spmc,
						"ggxh":ggxh,
						"jldw":jldw,
						"xmsl":Number(number),
						"xmdj":Number(dj),
						"hsdj":Number(dj)*(1+slv),
						"hsje":hsje,
						"spbm":spbm,
						"spbh":spbh,
						"yhzc":yhzc,
					    "yhzclx":yhzclx,
					    "zerotax":zerotax,
					    "bmbbh":bmbbh,
					    "zkhsje":0,
					    "zkje":0,
					    "zkse":0,
					    "zkhhsje":hsje,
					    "zkhje":Number(je),
					    "zkhse":Number(se),
					    "sfyhzc":yhzc,
					    "yhzcnr":yhzclx,
					    "lslbs":zerotax,
					    "fphxz":"0"
					   // "pricecard":pricecard
					/*,
					"djmxxh":(index+1)+"",//单据明细序号
					"fpmxxh":(index+1)+"",//单据明细序号
	*/			};
				array.push(xxfpJson);
                console.log("array:"+array);
				
			/*	return array;*/
			});
            console.log("行数大于8行，有清单:"+dataRight);
			return dataRight;
		}else{
			$(".fpitem_line").each(function(index,element){
				$tr = $(this);
				var spmc = $tr.find(".fpitem_mc").val();
				var je = Number($tr.find(".fpitem_price").val()).toFixed(2);
				var ggxh = $tr.find(".fpitem_ggxh").val();
				var jldw = $tr.find(".fpitem_jldw").val();
				var number = $tr.find(".fpitem_number").val();
				var dj =  $tr.find(".fpitem_dj").val();
				var price = $tr.find(".fpitem_price").val();
				var slv = $tr.find(".fpitem_slv").text();
				if(slv=="免税"){
					slv = 0;
				}else{
					slv = Number(slv.substr(0, slv.length-1))/100
				}
				var se = Number($tr.find(".fpitem_shuie").val()).toFixed(2);
				var hsje = (Number(je)+Number(se)).toFixed(2);
				var pricecard = "0";
				var spbm = $tr.find(".fpitem_spbm").text();
				var spbh = $tr.find(".fpitem_spbh").text();
				var yhzc = $tr.find(".fpitem_yhzc").text();
				var yhzclx = $tr.find(".fpitem_yhzclx").text();
				var zerotax = $tr.find(".fpitem_zerotax").text();
				var bmbbh = $tr.find(".fpitem_bmbbh").text();
				if((spmc=="" ||  number=="" || dj=="" || price=="" )){//当一行数据都为空时
					console.log("aabb"+spmc+":"+number+":"+dj+":"+price+":"+slv+":"+se);
					dataRight = 0;
					return false;
				}
				//console.log(spmc+":"+number+":"+dj+":"+price+":"+slv+":"+se);
			/*	//当数据不完整时ggxh=="" || jldw=="" ||
				if($tr.hasClass("zhekou")){
					spmc = "折扣行数1（"+$("#zhekou_lv").val()+"%）";
					pricecard = "1";
					
				}
				else{
					if((spmc=="" ||  number=="" || dj=="" || price=="" || slv=="" || se=="")){
					//数据不完整处理
					alert('第'+(index+1)+'行  数据不完整,请补充');
					dataRight = 0;
					return dataRight;
					}
				}*/
				
				var xxfpJson = {
					"pfhh":index+1,	
/*					"fpdm":$("#xxfp_fpdm").val(),
					"fphm":$("#xxfp_fphm").val(),
					"qdbz":qdbz+"",*/
					"xmje":Number(je),
					"sl":slv,
					"se":Number(se),
					"xmmc":spmc,
					"ggxh":ggxh,
					"jldw":jldw,
					"xmsl":Number(number),
					"xmdj":Number(dj),
					"hsdj":Number(dj)*(1+slv),
					"hsje":hsje,
					"spbm":spbm,
					"spbh":spbh,
					"yhzc":yhzc,
				    "yhzclx":yhzclx,
				    "zerotax":zerotax,
				    "bmbbh":bmbbh,
				    "zkhsje":0,
				    "zkje":0,
				    "zkse":0,
				    "zkhhsje":hsje,
				    "zkhje":Number(je),
				    "zkhse":Number(se),
				    "sfyhzc":yhzc,
				    "yhzcnr":yhzclx,
				    "lslbs":zerotax,
				    "fphxz":"0"
				    //"pricecard":pricecard
					/*,
					"djmxxh":(index+1)+"",//单据明细序号
					"fpmxxh":(index+1)+"",//单据明细序号
	*/			};
				array.push(xxfpJson);
				console.log("行数小于8行:"+dataRight);
				
			/*	return array;*/
			});
			return dataRight;
		}
		
	}
	
	 /* 图案-商品编码 点击事件 */
	 function fpitemClickspanQd(elementThis){//fpitem_clickspan
		// $("#fpinfo_fpitem").modal('open');
		 win.window('open');  
		 form.form('clear');  
		 $mCurrentLineTr = $(elementThis).parent().parent().parent();
		 //modal_fpitem_content
	 };
	 /* 图案-商品编码 点击事件 */
	 function fpitemClickspan(elementThis){//fpitem_clickspan
		// $("#fpinfo_fpitem").modal('open');
		 win.window('open');  
		 form.form('clear');  
		 $mCurrentLineTr = $(elementThis).parent().parent();
		 //modal_fpitem_content
	 };

/* 修改含税价格时, 含税单价和税额会有变动 */
function autoGetPriceBlur(fpitem_number){//fpitem_number
    $tr = $(fpitem_number).parent().parent();
    //$tr = $(this);
	//含税单价
    var dj = $tr.find(".fpitem_dj").val();
    //不含税金额
    var jine= $tr.find(".fpitem_price").val();
    //含税金额
    var hsje= $tr.find(".fpitem_hsje").val();
    //数量
    var num =$tr.find(".fpitem_number").val();
    var slv = $tr.find(".fpitem_slv").text();
    var slv_int = 0;
    if(slv=="免税"){
        slv_int = 0;
    }else{
        slv_int = Number(slv.substr(0, slv.length-1));
    }
    console.info("数量:"+num);
    console.info("税率:"+slv_int);
    console.info("不含税单价:"+dj);
    console.info("不含税金额:"+jine);
    if(num==""||num=="0"){
        num = 1;
        $(fpitem_number).val("1");
    }
    jine = (hsje/(1+slv_int/100)).toFixed(2);//不含税金额 = 含税金额/(1+税率)
	//不含税单价
    dj=(Number(hsje/(1+slv_int/100))/Number(num)).toFixed(6);
    $tr.find(".fpitem_dj").val(dj);
    //含税单价
	var hsdj=(Number(hsje)/Number(num)).toFixed(6);
    $tr.find(".fpitem_hsdj").val(hsdj);
    //var slv_int = Number(slv.substr(0, slv.length-1));
    var jine2 = Number(jine).toFixed(2);
    $tr.find(".fpitem_price").val(jine2);
    //税额
    var slvsb = (Number(hsje)-Number(jine2)).toFixed(2);
    $tr.find(".fpitem_shuie").val(slvsb);

    console.info("数量:"+num);
    console.info("含税单价:"+dj);
    console.info("含税金额:"+jine);
    //合计变动
    var sum=0;
    var jesum = 0;
    var shuiesum = 0;
    $(".fpitem_price").each(function(index ,element){
        sum += Number($(this).val());
        jesum += Number($(this).val());
    })
    $(".fpitem_shuie").each(function(index ,element){
        sum += Number($(this).val());
        shuiesum += Number($(this).val());
    })
    $("#xxfp_xx").val(sum.toFixed(2));
    $("#xxfp_jehj").html(jesum.toFixed(2));
    $("#xxfp_sehj").html(shuiesum.toFixed(2));
    $("#xxfp_dx").val(Arabia_to_Chinese(sum.toFixed(2)));
};
	 /* 修改数量时, 金额和税额会有变动 */
function numberBlur(fpitem_number){//fpitem_number
	 $tr = $(fpitem_number).parent().parent();
	 //$tr = $(this);
	 //var dj = $tr.find(".fpitem_dj").val();
     var hsdj = $tr.find(".fpitem_hsdj").val();
	 var num = $(fpitem_number).val();
	 console.info(num);
	 if(num==""||num=="0"){
		 num = 1;
		 $(fpitem_number).val("1");
	 }
	 var slv = $tr.find(".fpitem_slv").text();
	 var slv_int = 0;
	 if(slv=="免税"){
		 slv_int = 0;
	 }else{
		  slv_int = Number(slv.substr(0, slv.length-1));
	 }
    //金额 = 数量*含税单价/（1+税率）
    var jine = num*hsdj/(1+slv_int*0.01);
	 //var slv_int = Number(slv.substr(0, slv.length-1));
	 var jine2 = Number(jine).toFixed(2);
	 $tr.find(".fpitem_price").val(jine2);
	 var slvsb = Number(jine*slv_int*0.01).toFixed(2);
	 $tr.find(".fpitem_shuie").val(slvsb);
    //*************含税金额发生变化
    console.log(slvsb);
    console.log(jine2);
    var hsje = Number(Number(slvsb)+Number(jine2)).toFixed(2);
    console.log(hsje);
    $tr.find(".fpitem_hsje").val(hsje);
    //单价
    var dj =Number(hsdj/(1+slv_int*0.01)).toFixed(6) ;
    $tr.find(".fpitem_dj").val(dj);

	 //合计变动
	 var sum=0;
	 var jesum = 0;
	 var shuiesum = 0;
	 $(".fpitem_price").each(function(index ,element){
		 sum += Number($(this).val());
		 jesum += Number($(this).val());
	 })
	  $(".fpitem_shuie").each(function(index ,element){
		 sum += Number($(this).val());
		 shuiesum += Number($(this).val());
	 })
	 $("#xxfp_xx").val(sum.toFixed(2));
	 $("#xxfp_jehj").html(jesum.toFixed(2));
	 $("#xxfp_sehj").html(shuiesum.toFixed(2));
	 $("#xxfp_dx").val(Arabia_to_Chinese(sum.toFixed(2)));
 };
 
 /* 修改单价时, 金额和税额会有变动 */
  function djBlur(elementThis){//fpitem_dj
	 //console.info("into dj blur");
	 $tr = $(elementThis).parent().parent();
	 //$tr = $(this);
	 var dj = $tr.find(".fpitem_dj").val();
	 //TODO 在这里少些一个判断小数点后超过2位时发生的事情
//	 if(dj.indexOf(".")!=-1 && (dj.split(".")[1].length>2 || dj.split(".").length>2 || dj.split(".")[0].length<=0)){
//		 alert('输入的格式错误,请检查');
//		 return;
//	 }
	 var num = $tr.find(".fpitem_number").val();
	 if(num == ""){
		 num = 1;
		 $tr.find(".fpitem_number").val("1");
	 }
	 var jine = num*dj;//金额 = 数量*单价
	 var slv = $tr.find(".fpitem_slv").text();
	 var slv_int = Number(slv.substr(0, slv.length-1));
	 console.log(slv_int);
	 var jine2 = Number(jine).toFixed(2);
	 $tr.find(".fpitem_price").val(jine2);
	 var slvsb = Number(jine*slv_int*0.01).toFixed(2);
	 $tr.find(".fpitem_shuie").val(slvsb);
      //*************含税金额发生变化
      console.log(slvsb);
      console.log(jine2);
      var hsje = Number(Number(slvsb)+Number(jine2)).toFixed(2);
      console.log(hsje);
      $tr.find(".fpitem_hsje").val(hsje);
	 //合计变动
	 var sum=0;
	 var jesum = 0;
	 var shuiesum = 0;
	 $(".fpitem_price").each(function(index ,element){
		 sum += Number($(this).val());
		 jesum += Number($(this).val());
	 })
	  $(".fpitem_shuie").each(function(index ,element){
		 sum += Number($(this).val());
		 shuiesum += Number($(this).val());
	 })
	 $("#xxfp_xx").val(sum.toFixed(2));
	 $("#xxfp_jehj").html(jesum.toFixed(2));
	 $("#xxfp_sehj").html(shuiesum.toFixed(2));
	 $("#xxfp_dx").val(Arabia_to_Chinese(sum.toFixed(2)));
 };

/* 修改含税单价时, 含税金额和金额和税额会有变动 */
function hsdjBlur(elementThis){//fpitem_dj
    //console.info("into dj blur");
    $tr = $(elementThis).parent().parent();
    //$tr = $(this);
    var hsdj = $tr.find(".fpitem_hsdj").val();
    //TODO 在这里少些一个判断小数点后超过2位时发生的事情
//	 if(dj.indexOf(".")!=-1 && (dj.split(".")[1].length>2 || dj.split(".").length>2 || dj.split(".")[0].length<=0)){
//		 alert('输入的格式错误,请检查');
//		 return;
//	 }
    var num = $tr.find(".fpitem_number").val();
    if(num == ""){
        num = 1;
        $tr.find(".fpitem_number").val("1");
    }

    var slv = $tr.find(".fpitem_slv").text();
    var slv_int = Number(slv.substr(0, slv.length-1));
    console.log(slv_int);
    //金额 = 数量*含税单价/（1+税率）
    var jine = num*hsdj/(1+slv_int*0.01);
    var jine2 = Number(jine).toFixed(2);
    $tr.find(".fpitem_price").val(jine2);
    var slvsb = Number(jine*slv_int*0.01).toFixed(2);
    $tr.find(".fpitem_shuie").val(slvsb);
    //*************含税金额发生变化
    console.log(slvsb);
    console.log(jine2);
    //含税金额
    var hsje = Number(Number(slvsb)+Number(jine2)).toFixed(2);
    console.log(hsje);
    $tr.find(".fpitem_hsje").val(hsje);
    //单价
    var dj =Number(hsdj/(1+slv_int*0.01)).toFixed(6) ;
    $tr.find(".fpitem_dj").val(dj);
    //合计变动
    var sum=0;
    var jesum = 0;
    var shuiesum = 0;
    $(".fpitem_price").each(function(index ,element){
        sum += Number($(this).val());
        jesum += Number($(this).val());
    })
    $(".fpitem_shuie").each(function(index ,element){
        sum += Number($(this).val());
        shuiesum += Number($(this).val());
    })
    $("#xxfp_xx").val(sum.toFixed(2));
    $("#xxfp_jehj").html(jesum.toFixed(2));
    $("#xxfp_sehj").html(shuiesum.toFixed(2));
    $("#xxfp_dx").val(Arabia_to_Chinese(sum.toFixed(2)));
};





/* 初始化autoComplete数组  */
		function initAutoCompleteGouxiao(objs){
			var mGfmc = new Array();
			var mGfsh = new Array();
			var mGfdzdh = new Array();
			var mGfyhzh = new Array();
			for (var i = 0; i < objs.length; i++) {
				var khmc = objs[i].khmc;
				var khsh = objs[i].khsh;
				mGfmc.push(khmc);
				mGfsh.push(khsh);
				mGfdzdh.push(objs[i].khdzdh);
				mGfyhzh.push(objs[i].yhzh);
			}
			/*console.info("init ---");
			console.info(mGfmc);*/
			/** 购方名称模糊搜索 */
			$('#xxfp_gfmc').autocomplete({
			   source: mGfmc,
			    width: 200,
			    //height: 30,
			    minChars:1,
			    autoFill:true,
			    scrollHeight:100,
			    select:function(event ,ui){
			    	var mc = ui.item.value;
			    	for (var j = 0; j < objs.length; j++) {
			    		if(mc == objs[j].khmc){
			    			var gfmc = objs[j].khmc;
			    			var khsh = objs[j].khsh;
					    	var khdzdh = objs[j].khdzdh;
					    	var yhzh = objs[j].yhzh;
					    	$("#xxfp_gfmc").val(khmc);
					    	$("#xxfp_gfsh").val(khsh);
					    	$("#xxfp_gfdzdhg").val(khdzdh);
					    	$("#xxfp_gfyhzh").val(yhzh);
					    	break;
			    		}
					}
			    },
			    
			});
			/* 根据 税 号   模糊搜索 */
			$('#xxfp_gfsh').autocomplete({
			    source: mGfsh,
			    width: 200,
			    minChars:1,
			    autoFill:true,
			    scrollHeight:100,
			    select:function(event ,ui){
			    	var gfsh = ui.item.value;
			    	for (var j = 0; j < objs.length; j++) {
			    		if(gfsh == objs[j].khsh){
			    			var khmc = objs[j].khmc;
			    			var khsh = objs[j].khsh;
					    	var khdzdh = objs[j].khdzdh;
					    	var yhzh = objs[j].yhzh;
					    	$("#xxfp_gfmc").val(khmc);
					    	$("#xxfp_gfsh").val(khsh);
					    	$("#xxfp_gfdzdhg").val(khdzdh);
					    	$("#xxfp_gfyhzh").val(yhzh);
					    	break;
			    		}
					}
			    },
			});
			/* 根据 地址、电话 模糊搜索 */
			$('#xxfp_gfdzdhg').autocomplete({
			    source: mGfdzdh,
			    width: 200,
			    minChars:1,
			    autoFill:true,
			    scrollHeight:100,
			    select:function(event ,ui){
			    	var dzdh = ui.item.value;
			    	for (var j = 0; j < objs.length; j++) {
			    		if(dzdh == objs[j].khdzdh){
			    			var khmc = objs[j].khmc;
			    			var khsh = objs[j].khsh;
					    	var khdzdh = objs[j].khdzdh;
					    	var yhzh = objs[j].yhzh;
					    	$("#xxfp_gfmc").val(khmc);
					    	$("#xxfp_gfsh").val(khsh);
					    	$("#xxfp_gfdzdhg").val(khdzdh);
					    	$("#xxfp_gfyhzh").val(yhzh);
					    	break;
			    		}
					}
			    },
			});
			/* 根据开户行、银行卡号模糊搜索 */
			$('#xxfp_gfyhzh').autocomplete({
			    source: mGfyhzh,
			    width: 200,
			    minChars:1,
			    autoFill:true,
			    scrollHeight:100,
			    select:function(event ,ui){
			    	var kaihuhang = ui.item.value;
			    	
			    	for (var j = 0; j < objs.length; j++) {
			    		if(kaihuhang == null){
			    			continue;
			    		}
			    		////console.info(kaihuhang);
			    		if(kaihuhang == objs[j].yhzh){
			    			var khmc = objs[j].khmc;
			    			var khsh = objs[j].khsh;
					    	var khdzdh = objs[j].khdzdh;
					    	var yhzh = objs[j].yhzh;
					    	$("#xxfp_gfmc").val(khmc);
					    	$("#xxfp_gfsh").val(khsh);
					    	$("#xxfp_gfdzdhg").val(khdzdh);
					    	$("#xxfp_gfyhzh").val(yhzh);
					    	break;
			    		}
					}
			    },
			});
		}
	 /* 点击一个 fpitem_line 调用商品编码表的信息 */
	 
	 
	
	/* 动态添加一行 xxfp_item 
		var html = "htmlcode";
		parent.append(html);
	*/
	
	/* 定义一个变量，一直存储当前行数 */
	/**
	 * 发票页面添加一行
	 */
	var mCurrentLineNo = 0;
	function addXxfpItemLineHtmlData(){
		console.log("当前行数:"+mCurrentLineNo);
		if(mCurrentQdListReady==1){
			alert('已经有清单了,不能增行了!',{time:2000});
			return;
		}
		var trHtml = ("<tr class='fpitem_line'>"
            +"<td width='9%' height='30' align='center' class='bian'>"
            +"<input type='radio' name='xxfp-line-radio' id='radio' value='radio' class='fpitem_radio'/>选中</td>"
            +"<td width='23%' align='center' class='bian2'><input class='fpitem_mc'  style='text-align:center;width: 100%; height: 100%;' placeholder='从商品编码表中一个一个的选';disabled='disabled' readonly='readonly'/></td>"
            +"<td width='3%' class='bian fpitem_a'><a href='javascript:void(0)' onclick='fpitemClickspan(this)'><img src="+picUrl+" width='26' height='23' /></a></td>"
            +"<td width='8%' class='bian'><input class='fpitem_ggxh' style='text-align:center;width: 100%; height: 100%;'/></td>"
			+"<td width='8%' class='bian'><input class='fpitem_jldw' style='text-align:center;width: 100%; height: 100%;'/></td>"
			+"<td width='10%' class='bian'><input class='fpitem_number' onblur='numberBlur(this)' style='text-align:center;width: 100%; height: 100%;'/></td>"
			+"<td width='0%' class='bian'><input class='fpitem_dj' onblur='djBlur(this)' style='text-align:center;width: 100%; height: 100%;''/></td>"
            +"<td width='10%' class='bian'><input class='fpitem_hsdj' onblur='hsdjBlur(this)' style='text-align:center;width: 100%; height: 100%;''/></td>"
            +"<td width=\"0%\" class=\"bian\"><input class=\"fpitem_price\"  style=\"display:none;text-align:center;width: 100%; height: 100%;\" placeholder=\"\";disabled=\"disabled\" readonly=\"readonly\"/></td>"
			+" <td width=\"11%\" class=\"bian\"><input class=\"fpitem_hsje\"  style=\"text-align:center;width: 100%; height: 100%;\" placeholder=\"\";disabled=\"disabled\" onblur='autoGetPriceBlur(this);'/></td>"
			+"<td width='6%' class='bian fpitem_slv' align='center'>&nbsp;</td>"
			+"<td width='12%' class='bian2'><input class='fpitem_shuie'  style='text-align:center;width: 100%; height: 100%;' placeholder='';/></td>"
			+"<td style='display:none' class='fpitem_spbm'>&nbsp;</td>"
			+"<td style='display:none' class='fpitem_spbh'>&nbsp;</td>"
			+"<td style='display:none' class='fpitem_yhzc'>&nbsp;</td>"
			+"<td style='display:none' class='fpitem_yhzclx'>&nbsp;</td>"
			+"<td style='display:none' class='fpitem_zerotax'>&nbsp;</td>"
			+"<td style='display:none' class='fpitem_bmbbh'>&nbsp;</td>"
			+"</tr>");
        //parent
        //$("#xxfp_table_item").append(htmlLine);
		if(mCurrentLineNo>=7 && mCurrentQdListReady ==1){
	        	alert('已超出发票详情打印上限');
	        	return;
	    }
        addTr('xxfp_table_item', mCurrentLineNo, trHtml);
        mCurrentLineNo++;
        //xxfpItemToQDfpxxItem();
	};
	//显示清单里的详情数据
	function showQingdanItem(array){
		mCurrentLineNo = mCurrentLineNo-1;
		console.log("----:"+mCurrentLineNo);
		console.log(array);
		//如果没数据  , 不读取
		if(array==null || array.length==0){
			return;
		}
		//$("#xxfp_qingdan_item_table")
		var trQdHtml = ("<tr class='qiandan-tr'>"
				+"<td width='9%' height='30' align='center' class='abian'><input type='radio' name='qdxxfp-line-radio' id='radio' value='radio' class='qd_fpitem_radio' />选中</td>"
				+"<td width='23%' align='center' class='abian2'><input class='qd_fpitem_mc' style='text-align:center;width: 100%; height: 100%;' placeholder='从商品编码表中一个一个的选'/></td>"
				+"<td width='3%' class='abian'><div style='text-align:center;' class='qd_fpitem_a'><a href='javascript:void(0)' onclick='fpitemClickspanQd(this)'><img src="+picUrl+" width='26' height='23' /></a></div></td>"
				+"<td width='8%' class='abian'><input class='qd_fpitem_ggxh' style='text-align:center;width: 100%; height: 100%;'/></td>"
				+"<td width='8%' class='abian'><input class='qd_fpitem_jldw' style='text-align:center;width: 100%; height: 100%;'/></td>"
				+"<td width='10%' class='abian'><input class='qd_fpitem_number' onblur='qdnumberBlur(this)' style='text-align:center;width: 100%; height: 100%;'/></td>"
				+"<td width='0%' class='abian'><input class='qd_fpitem_dj' onblur='qddjBlur(this)' style='text-align:center;width: 100%; height: 100%;'/></td>"
            +"<td width='10%' class='abian'><input class='qd_fpitem_hsdj' onblur='qdhsdjBlur(this)' style='text-align:center;width: 100%; height: 100%;'/></td>"
            +"<td width='0%' class='abian'><input class='qd_fpitem_price'  style='text-align:center;width: 100%; height: 100%;' placeholder='' readonly=\"readonly\"/></td>"
				+"<td width=\"11%\" class=\"bian\"><input class=\"qd_fpitem_hsje\"  style=\"text-align:center;width: 100%; height: 100%;\" placeholder=\"\";disabled=\"disabled\" onblur='qd_autoGetPriceBlur(this);'/></td>"
				+"<td width='6%' class='abian qd_fpitem_slv' align='center'>&nbsp;</td>"
				+"<td width='12%' class='abian2'><input class='qd_fpitem_shuie'  style='text-align:center;width: 100%; height: 100%;' placeholder='';disabled='disabled' readonly=\"readonly\"/></td>"
				+"<td style='display:none' class='qd_fpitem_spbm'>&nbsp;</td>"
				+"<td style='display:none' class='qd_fpitem_spbh'>&nbsp;</td>"
				+"<td style='display:none' class='qd_fpitem_yhzc'>&nbsp;</td>"
				+"<td style='display:none' class='qd_fpitem_yhzclx'>&nbsp;</td>"
				+"<td style='display:none' class='qd_fpitem_zerotax'>&nbsp;</td>"
				+"<td style='display:none' class='qd_fpitem_bmbbh'>&nbsp;</td>"
				+"</tr>");
		 $.each(array,function(index,value){
			 console.info("index=="+index + ",value=="+value);
			 //addTr('xxfp_qingdan_item_table', mCurrentLineNo, trQdHtml);
			 
			 addQdTr('xxfp_qingdan_item_table', mCurrentLineNo, trQdHtml);
			 mCurrentLineNo++;
			 console.info("mCurrentLineNo=="+mCurrentLineNo);
		 });
		 $(".qiandan-tr").each(function(index,element){
			 $qdTr = $(this);
			 $qdTr.find(".qd_fpitem_mc").val(array[index].mc);
			 //console.log(array[index].mc);
			 if(array[index].mc!=""){
				 $qdTr.find(".qd_fpitem_a").html("");
			 }
			 $qdTr.find(".qd_fpitem_ggxh").val(array[index].ggxh);
			 $qdTr.find(".qd_fpitem_jldw").val(array[index].jldw);
			 $qdTr.find(".qd_fpitem_number").val(array[index].number);
			 $qdTr.find(".qd_fpitem_dj").val(array[index].dj);
			 $qdTr.find(".qd_fpitem_price").val(array[index].price);
			 $qdTr.find(".qd_fpitem_slv").text(array[index].slv);
			 $qdTr.find(".qd_fpitem_shuie").val(array[index].shuie);
			 $qdTr.find(".qd_fpitem_spbm").html(array[index].spbm);
			 $qdTr.find(".qd_fpitem_spbh").html(array[index].spbh);
			 $qdTr.find(".qd_fpitem_yhzc").html(array[index].yhzc);
			 $qdTr.find(".qd_fpitem_yhzclx").html(array[index].yhzclx);
			 $qdTr.find(".qd_fpitem_zerotax").html(array[index].zerotax);
			 $qdTr.find(".qd_fpitem_bmbbh").html(array[index].bmbbh);
		 })
		 
	}
	/* 清空所有详情信息 */
	function clearAllLine(){
		 $(".fpitem_line").each(function(index ,element){
			$(this).remove(); 
		 });
		 mCurrentLineNo = 0;
	}
	
/*	//发票页 "折扣"按钮
	function fpZhekou(ele){
		//如果没有选中行,那么不允许点击
		$radio = $('input[name="xxfp-line-radio"]:checked ');
		console.info($radio);
		if($radio.length==0){
			alert('请先选中一行');
			return;
		}
		$tr = $radio.parent().parent();
		//如果已经有折扣了 , 那么不允许再添加折扣了
		if($tr.hasClass("hasZhekou") || $tr.hasClass("zhekou")){
			alert('当前行为折扣行或已有折扣,无法再添加折扣');
			return;
		}
		var priceStr = $tr.find(".fpitem_price").val();
		var price = Number(priceStr);
		if(price=="NaN"){
			alert('请先输入数量和单价,计算出金额');
		}
		
		$("#zhekou-list").modal('show');
		//zhekou_lineno 折扣行数
		var hangshu = $tr.index();
		$("#zhekou_lineno").val(hangshu);
		//zhekou_spprice 商品金额
		$("#zhekou_spprice").val(price);
		//zhekou_lv 折扣率 0~100 计算的或输入的
		
		//zhekou_jine 折扣金额 	计算的或输入的
	}*/
		
	/* xxfp_item 数据转移到清单中    */
	function xxfpItemToQDfpxxItem(){
		//当清单的array全局变量.length大于8的时候，不能转化，因为只有清单一行了。
		if(mQingdanArray!=null && mQingdanArray.length>8||mIsQdFlag){
			return mQingdanArray;
		}
		
		else{
			//定义数组
			var xxfpItemArray = new Array();
			//循环
			 $(".fpitem_line").each(function(index ,element){
			//名称
			var mc = $(element).find(".fpitem_mc").val();
			//规格型号
			var ggxh = $(element).find(".fpitem_ggxh").val();
			//计量单位
			var jldw = $(element).find(".fpitem_jldw").val();
			//数量
			var number = $(element).find(".fpitem_number").val();
			//单价
			var dj = $(element).find(".fpitem_dj").val();
			//金额
			var price = $(element).find(".fpitem_price").val();
			 //金额
			 var hsje = $(element).find(".fpitem_hsje").val();
			//税率
			var slv = $(element).find(".fpitem_slv").text();
			//税额
			var shuie = $(element).find(".fpitem_shuie").val();
			//商品编码
			var spbm = $(element).find(".fpitem_spbm").text();
			//商品编号
			var spbh = $(element).find(".fpitem_spbh").val();
			//优惠政策
			var yhzc = $(element).find(".fpitem_yhzc").val();
			//优惠政策类型
			var yhzclx = $(element).find(".fpitem_yhzclx").val();
			//零税率标识
			var zerotax = $(element).find(".fpitem_zerotax").val();
			//编码版本号
			var bmbbh = $(element).find(".fpitem_bmbbh").val();
			var obj = new Object();
			obj.mc = mc;
			obj.ggxh = ggxh;
			obj.jldw = jldw;
			obj.number = number;
			obj.dj = dj;
			obj.price = price;
			obj.hsje = hsje;
			obj.slv = slv;
			obj.shuie = shuie;
			obj.spbm = spbm;
			obj.spbh = spbh;
			obj.yhzc = yhzc;
			obj.yhzclx = yhzclx;
			obj.zerotax = zerotax;
			obj.bmbbh = bmbbh;
			xxfpItemArray.push(obj);
			})
			return xxfpItemArray;
		}
		
	};
	//从清单点击转发票时，数据处理
	function qdfpItemToxxfpItem(jsonArray,isQdFlag){
		//console.info(typeof(JSON.parse('{"name":"cpf","age":"23"}')));
		clearAllLine();//先清空旧数据,归0 mCurrentLineNo
		//存一个全局变量
        console.info("isQdFlag:"+isQdFlag);
		mQingdanArray = jsonArray;
		mIsQdFlag = isQdFlag;
		//当清单长度大于8的时候，只打印一行，详情见清单
		if(jsonArray.length>8||isQdFlag){
            //console.info("当清单长度大于8的时候:"+jsonArray.length);
			addXxfpItemLineHtmlData();
			$tr = $(".fpitem_line");
			$tr.find(".fpitem_mc").val("(详见销货清单)");
			//所有都只读
			$tr.find("input").each(function(){
				//$(this).attr("disabled","disabled");
				$(this).attr("readonly","readonly");
			});
			$tr.find(".fpitem_clickspan").removeAttr("onclick");
			//$tr.find().attr("disable","disable");
			var price=0;
			$.each(jsonArray,function(index,value){
				if(jsonArray[index].price!="" && jsonArray[index].shuie!="NaN"){
					price+= Number(jsonArray[index].price);
				}
			});
			var shuie=0;
			$tr.find(".fpitem_price").val(Number(price.toFixed(2)));
			$.each(jsonArray,function(index,value){
				if(jsonArray[index].shuie!="" &&jsonArray[index].shuie!="NaN"){
					shuie+= Number(jsonArray[index].shuie);
				}
			});
			shuie = shuie.toFixed(2);
			$tr.find(".fpitem_shuie").val(shuie);
			////**************含税金额统计
            var hsje=0;
            //$tr.find(".fpitem_price").val(Number(price.toFixed(2)));
            $.each(jsonArray,function(index,value){
                if(jsonArray[index].hsje!="" &&jsonArray[index].hsje!="NaN"){
                    hsje+= Number(jsonArray[index].hsje);
                }
            });
            hsje = hsje.toFixed(2);
            $tr.find(".fpitem_hsje").val(hsje);

			$tr.find(".fpitem_a").html("");
			$("#xxfp_xx").val((Number(price)+Number(shuie)).toFixed(2));
			$("#xxfp_jehj").html(Number(price.toFixed(2)));
			$("#xxfp_sehj").html(Number(shuie));
			$("#xxfp_dx").val(Arabia_to_Chinese((Number(price)+Number(shuie)).toFixed(2)));
			return;
		}
		else{//当清单长度<=8
			console.info("当清单长度<=8  qd-xxfp Array length长度 = "+jsonArray.length);
			$.each(jsonArray,function(index,value){
				//当从清单回到页面的时候，重置的时候
				addXxfpItemLineHtmlData();
			});
			 $(".fpitem_line").each(function(index,element){
				 	jsonObj = jsonArray[index];
					$tr = $(this);
					$tr.find(".fpitem_mc").val(jsonObj.mc);
					if(jsonObj.mc!=""||jsonObj.mc!=null){
						$tr.find(".fpitem_a").html("");
					}
					$tr.find(".fpitem_ggxh").val(jsonObj.ggxh);
					$tr.find(".fpitem_jldw").val(jsonObj.jldw);
					$tr.find(".fpitem_number").val(jsonObj.number);
					$tr.find(".fpitem_dj").val(jsonObj.dj);
					$tr.find(".fpitem_price").val(jsonObj.price);
                    $tr.find(".fpitem_hsje").val(jsonObj.hsje);
					$tr.find(".fpitem_slv").html(jsonObj.slv);
					$tr.find(".fpitem_shuie").val(jsonObj.shuie);
					$tr.find(".fpitem_spbm").html(jsonObj.spbm);
					$tr.find(".fpitem_spbh").html(jsonObj.spbh);
					$tr.find(".fpitem_yhzc").html(jsonObj.yhzc);
					$tr.find(".fpitem_yhzclx").html(jsonObj.yhzclx);
					$tr.find(".fpitem_zerotax").html(jsonObj.zerotax);
					$tr.find(".fpitem_bmbbh").html(jsonObj.bmbbh)
			 });


            // $tr = $(".fpitem_line");
            // //所有都只读
            // $tr.find("input").each(function(){
            //     //$(this).attr("disabled","disabled");
            //     $(this).attr("readonly","readonly");
            // });
            // $tr.find(".fpitem_clickspan").removeAttr("onclick");
            // //$tr.find().attr("disable","disable");
            // var price=0;
            // $.each(jsonArray,function(index,value){
            //     if(jsonArray[index].price!="" && jsonArray[index].shuie!="NaN"){
            //         price+= Number(jsonArray[index].price);
            //     }
            // });
            // var shuie=0;
            // $tr.find(".fpitem_price").val(Number(price.toFixed(2)));
            // $.each(jsonArray,function(index,value){
            //     if(jsonArray[index].shuie!="" &&jsonArray[index].shuie!="NaN"){
            //         shuie+= Number(jsonArray[index].shuie);
            //     }
            // });
            // shuie = shuie.toFixed(2);
            // $tr.find(".fpitem_shuie").val(shuie);
            // ////**************含税金额统计
            // var hsje=0;
            // //$tr.find(".fpitem_price").val(Number(price.toFixed(2)));
            // $.each(jsonArray,function(index,value){
            //     if(jsonArray[index].hsje!="" &&jsonArray[index].hsje!="NaN"){
            //         hsje+= Number(jsonArray[index].hsje);
            //     }
            // });
            // hsje = hsje.toFixed(2);
            // $tr.find(".fpitem_hsje").val(hsje);
            //
            // $tr.find(".fpitem_a").html("");
            // $("#xxfp_xx").val((Number(price)+Number(shuie)).toFixed(2));
            // $("#xxfp_jehj").html(Number(price.toFixed(2)));
            // $("#xxfp_sehj").html(Number(shuie));
            // $("#xxfp_dx").val(Arabia_to_Chinese((Number(price)+Number(shuie)).toFixed(2)));
            // return;
		}
	}
	/*
	//清单"转发票"按钮
	function qingdanZhuanFp(ele){
		var qdxxfpItemArray = new Array();
		if($(".qingdan-tr")==null){
			
		}
		
		else{
			//循环
			 $(".qiandan-tr").each(function(index ,element){
			//名称
			var mc = $(element).find(".qd_fpitem_mc").val();
			//规格型号
			var ggxh = $(element).find(".qd_fpitem_ggxh").val();
			//计量单位
			var jldw = $(element).find(".qd_fpitem_jldw").val();
			//数量
			var number = $(element).find(".qd_fpitem_number").val();
			//单价
			var dj = $(element).find(".qd_fpitem_dj").val();
			//金额
			var price = $(element).find(".qd_fpitem_price").val();
			//税率
			var slv = $(element).find(".qd_fpitem_slv").val();
			//税额
			var shuie = $(element).find(".qd_fpitem_shuie").val();
			var obj = new Object();
			obj.mc = mc;
			obj.ggxh = ggxh;
			obj.jldw = jldw;
			obj.number = number;
			obj.dj = dj;
			obj.price = price;
			obj.slv = slv;
			obj.shuie = shuie;
			qdxxfpItemArray.push(obj);
			})
			if(qdxxfpItemArray.length>8){
				alert('发票详情数大于8,无法转化为发票');
				mCurrentQdbz = 1;
				return;
			}
			mCurrentQdbz = 0;
			mCurrentQdListReady = 0;
			qdfpItemToxxfpItem(qdxxfpItemArray,false);
		
		}
		//clearAllLine();
		$("#qingdan-list").modal('hide');
	}
	//清单"折扣"按钮
	function qingdanZhekou(ele){
		//如果没有选中行,那么不允许点击
		$radio = $('input[name="qdxxfp-line-radio"]:checked ');
		console.info($radio);
		if($radio.length==0){
			alert('请先选中一行');
			return;
		}
		$tr = $radio.parent().parent();
		//如果已经有折扣了 , 那么不允许再添加折扣了
		if($tr.hasClass("hasZhekou") || $tr.hasClass("zhekou")){
			alert('当前行为折扣行或已有折扣,无法再添加折扣');
			return;
		}
		var priceStr = $tr.find(".qd_fpitem_price").val();
		var price = Number(priceStr);
		if(price=="NaN"){
			alert('请先输入数量和单价,计算出金额');
		}
		
		$("#qdzhekou-list").modal('show');
		//zhekou_lineno 折扣行数
		var hangshu = $tr.index();
		$("#qdzhekou_lineno").val(hangshu);
		//zhekou_spprice 商品金额
		$("#qdzhekou_spprice").val(price);
		//zhekou_lv 折扣率 0~100 计算的或输入的
		
		//zhekou_jine 折扣金额 	计算的或输入的
	}*/
	//清单 - 添加一行
	function qingdanAddLine(){
		var trQdHtml = ("<tr class='qiandan-tr '>"
				+"<td width='9%' height='30' align='center' class='abian'><input type='radio' name='qdxxfp-line-radio' id='radio' value='radio' class='qd_fpitem_radio' />选中</td>"
				+"<td width='23%' align='center' class='abian2'><input class='qd_fpitem_mc' style='text-align:center;width: 100%; height: 100%;' placeholder='从商品编码表中一个一个的选'/></td>"
				+"<td width='3%' class='abian'><div style='text-align: center;' class='qd_fpitem_a'><a href='javascript:void(0)' onclick='fpitemClickspanQd(this)'><img src="+picUrl+" width='26' height='23' /></a></div></td>"
				+"<td width='8%' class='abian'><input class='qd_fpitem_ggxh' style='text-align:center;width: 100%; height: 100%;'/></td>"
				+"<td width='8%' class='abian'><input class='qd_fpitem_jldw' style='text-align:center;width: 100%; height: 100%;'/></td>"
				+"<td width='10%' class='abian'><input class='qd_fpitem_number' onblur='qdnumberBlur(this)' style='text-align:center;width: 100%; height: 100%;'/></td>"
				+"<td width='0%' class='abian'><input class='qd_fpitem_dj' onblur='qddjBlur(this)' style='text-align:center;width: 100%; height: 100%;'/></td>"
            +"<td width='10%' class='abian'><input class='qd_fpitem_hsdj' onblur='qdhsdjBlur(this)' style='text-align:center;width: 100%; height: 100%;'/></td>"

            +"<td width='0%' class='abian'><input class='qd_fpitem_price'  style='text-align:center;width: 100%; height: 100%;' placeholder='';  readonly='readonly';/></td>"
				+"<td width=\"11%\" class=\"bian\"><input class=\"qd_fpitem_hsje\"  style=\"text-align:center;width: 100%; height: 100%;\" placeholder=\"\";disabled=\"disabled\" onblur='qd_autoGetPriceBlur(this);'/></td>"
				+"<td width='6%' class='abian qd_fpitem_slv'  align='center'>&nbsp;</td>"
				+"<td width='12%' class='abian2'><input class='qd_fpitem_shuie'  style='text-align:center;width: 100%; height: 100%;' placeholder=''; readonly=\"readonly\" /></td>"
				+"<td style='display:none' class='qd_fpitem_spbm'>&nbsp;</td>"
				+"<td style='display:none' class='qd_fpitem_spbh'>&nbsp;</td>"
				+"<td style='display:none' class='qd_fpitem_yhzc'>&nbsp;</td>"
				+"<td style='display:none' class='qd_fpitem_yhzclx'>&nbsp;</td>"
				+"<td style='display:none' class='qd_fpitem_zerotax'>&nbsp;</td>"
				+"<td style='display:none' class='qd_fpitem_bmbbh'>&nbsp;</td>"
				+"</tr>");
		  	//addTr('qingdan-list', mCurrentLineNo+1, trQdHtml);
			addQdTr('xxfp_qingdan_item_table', mCurrentLineNo, trQdHtml);
			mCurrentLineNo++;
	}
	//清单 - 减少一行
	/**
	 * 
	 */
	function qingdanDelLine(ele){
		console.log("-----------:"+mCurrentLineNo);
		if(mCurrentLineNo==0){
			alert('已无商品信息');
			return;
		}
		$radio = $('input[name="qdxxfp-line-radio"]:checked ');
		if($radio.length==0){
			return;
		}
		$tr = $radio.parent().parent();
		console.info($tr);
		/*//如果是折扣行被删除了 , 那么需要删除它本身商品行的只读属性
		if($tr.hasClass("zhekou")){
			qddelZhekouLineHappen($tr.prev());
		}
		//如果是有折扣行的商品行被删除 , 那么折扣行也被删除
		if($tr.hasClass("hasZhekou")){
			$tr.next().remove();
		}*/
		$tr.remove();
		mCurrentLineNo--;
	}
	//清单 - 完成
	function qingdanFinish(ele){
		var qdxxfpItemArray = new Array();
		if($(".qingdan-tr")==null){
			
		}
		else{
			//循环
			 $(".qiandan-tr").each(function(index ,element){
			//名称
			var mc = $(element).find(".qd_fpitem_mc").val();
		
			//规格型号
			var ggxh = $(element).find(".qd_fpitem_ggxh").val();
			//计量单位
			var jldw = $(element).find(".qd_fpitem_jldw").val();
			//数量
			var number = $(element).find(".qd_fpitem_number").val();
			//单价
			var dj = $(element).find(".qd_fpitem_dj").val();
			//金额
			var price = $(element).find(".qd_fpitem_price").val();
			//含税金额
			var hsje = $(element).find(".qd_fpitem_hsje").val();
			//税率
			var slv = $(element).find(".qd_fpitem_slv").text();
			//税额
			var shuie = $(element).find(".qd_fpitem_shuie").val();
			//商品编码
			var spbm = $(element).find(".qd_fpitem_spbm").text();
			//商品编号
			var spbh = $(element).find(".qd_fpitem_spbh").text();
			//优惠政策
			var yhzc = $(element).find(".qd_fpitem_yhzc").text();
			//优惠政策类型
			var yhzclx = $(element).find(".qd_fpitem_yhzclx").text();
			//零税率标识
			var zerotax = $(element).find(".qd_fpitem_zerotax").text();
			//编码版本号
			var bmbbh = $(element).find(".qd_fpitem_bmbbh").text();
			var obj = new Object();
			obj.mc = mc;
			obj.ggxh = ggxh;
			obj.jldw = jldw;
			obj.number = number;
			obj.dj = dj;
			obj.price = price;
            obj.hsje = hsje;
			obj.slv = slv;
			obj.shuie = shuie;
			obj.spbm = spbm;
			obj.spbh = spbh;
			obj.yhzc = yhzc;
			obj.yhzclx = yhzclx;
			obj.zerotax = zerotax;
			obj.bmbbh = bmbbh;
			qdxxfpItemArray.push(obj);
			})
			console.log("清单发票明细转换为发票明细:"+qdxxfpItemArray);
			qdfpItemToxxfpItem(qdxxfpItemArray,true);
		}
		mCurrentQdListReady = 1;
		//clearAllLine();
		//$("#qingdan-list").modal('hide');
		qdwin.window('close');
	}
	//清单 - 打印
	function qingdanPrint(ele){
		var retCode = printInv(infoKind,infoTypeCode,infoNumber,'1',"1");
	    if(retCode ==5011){
	    	alert("打印成功！！");
	    }
	}