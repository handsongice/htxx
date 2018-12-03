mQingdanArray = null;
mIsQdFlag = false;
var mCurrentQdListReady = 0;
/* 弹出清单 */
/* 弹出清单 */
function qingdan(){
    console.info("into click modal");
    //$("#qingdan-list").modal('show');

    qdwin.window('open');
    //清空旧清单数据
    $(".qiandan-tr").each(function(index,ele){
        $(this).remove();
    });
    $('#qd_fpdm').html(infoTypeCode);
    $('#qd_fphm').html(infoNumber);
    mCurrentLineNo = 1;
    mCurrentQdbz = 1;
    //当有清单的时候，发票是不能增行减行的
    mCurrentQdListReady = 0;
    var array= xxfpItemToQDfpxxItem();

    showQingdanItem(array);
};
$("#fpitem_qingdan").click(function(){
	//console.info("into click modal");
	$("#qingdan-list").modal('show');
	//清空旧清单数据
	$(".qiandan-tr").each(function(index,ele){
		$(this).remove();
	});
	$('#fpdm').text(infoTypeCode);
	$('#fphm').text(infoNumber);
	mCurrentLineNo = 0;
	mCurrentQdbz = 1;
	//当有清单的时候，发票是不能增行减行的
	mCurrentQdListReady = 0;
	var array= xxfpItemToQDfpxxItem();
	showQingdanItem(array);
});
/* 弹出折扣 */
$("#fpitem_zhekou").click(function(){
	//console.info("into click modal");
	$("#zhekou-list").modal('show');
});
//删除一行
function xxfpdelline(){
	/*if(mCurrentLineNo==0){
		alert('已无商品信息');
		return;
	}*/
	//$("#xxfp_table_item").find(".qd_fpitem_radio")
	//$('input:radio[name=xxfp-line-radio]')[0].checked = true;
	$radio = $('input[name="xxfp-line-radio"]:checked ');
	if($radio.length==0){
		return;
	}
	$tr = $radio.parent().parent();
	console.info($tr);
	//如果是折扣行被删除了 , 那么需要删除它本身商品行的只读属性
	if($tr.hasClass("zhekou")){
		delZhekouLineHappen($tr.prev());
	}
	//如果是有折扣行的商品行被删除 , 那么折扣行也被删除
	if($tr.hasClass("hasZhekou")){
		$tr.next().remove();
	}
	$tr.remove();
	mCurrentLineNo--;
}

	//添加销项发票明细
	function fpmxArrayPush(array,qdbz){
		var dataRight = 1;
		// qdbz = 清单标识
		console.log("=========~");
		/*if($(".fpitem_line").length>8|| qdbz==1){
			$(".qiandan-tr").each(function(index,element){
				$tr = $(this);
				var spmc = $tr.find(".qd_fpitem_mc").val();
				var je = $tr.find(".qd_fpitem_price").val();
				var ggxh = $tr.find(".qd_fpitem_ggxh").val();
				var jldw = $tr.find(".qd_fpitem_jldw").val();
				var number = $tr.find(".qd_fpitem_number").val();
				var dj =  $tr.find(".qd_fpitem_dj").val();
				var price = $tr.find(".qd_fpitem_price").val();
				var slv = $tr.find(".qd_fpitem_slv").val().trim();
				var se = $tr.find(".qd_fpitem_shuie").val();
				var hsje = Number(je)+Number(se);
				var pricecard = "0";
				var zkflag = false;
				var sfyhzc = $tr.find(".qd_fpitem_sfyhzc").val();
				var yhzcnr = $tr.find(".qd_fpitem_yhzcnr").val();
				var lslbs = $tr.find(".qd_fpitem_lslbs").val();
				var bmbbh = $tr.find(".qd_fpitem_bmbbh").val();
				var ssflbm = $tr.find(".qd_fpitem_ssflbm").val();
				if(spmc=="" && ggxh=="" && jldw=="" && number=="" && dj=="" && price=="" && slv=="" && se=="" ){//当一行数据都为空时
					dataRight = 0;
					return false;
				}
				//当数据不完整时ggxh=="" || jldw=="" ||
				if($tr.hasClass("zhekou")){
					spmc = "折扣（"+$("#zhekou_lv").val()+"%）";
					pricecard = "1";
					zkflag = true;
				}
				else{
					if((spmc=="" ||  dj=="" || price=="" || slv=="" || se=="")){
					//数据不完整处理
					layer.msg('第'+(index+1)+'行  数据不完整,请补充');
					dataRight = 0;
					return dataRight;
					}
				}
				
				
				var xxfpJson = {
						"fpdm":$("#xxfpzp_detail_fpdm").val(),
						"fphm":$("#xxfpzp_detail_fphm").val(),
						"fpmxxh":index+1,
						"je":Number(je),
						"slv":Number(slv.substr(0, slv.length-1)),
						"se":Number(se),
						"spmc":spmc,
						"ggxh":ggxh,
						"jldw":jldw,
						"sl":number,
						"dj":dj,
						"hsje":hsje,
						"sfyhzc":sfyhzc,
						"yhzcnr":yhzcnr,
						"lslbs":lslbs,
						"bmbbh":bmbbh,
						"ssflbm":ssflbm
					,
					"djmxxh":(index+1)+"",//单据明细序号
					"fpmxxh":(index+1)+"",//单据明细序号
				};
				array.push(xxfpJson);
				console.log("=========xxfpJson:"+xxfpJson.sfyhzc);
			});
			return dataRight;
		}
		else{*/
			$(".fpitem_line").each(function(index,element){
				$tr = $(this);
				var spmc = $tr.find(".fpitem_mc").val();
				var je = Number($tr.find(".fpitem_price").val()).toFixed(2);
				var ggxh = $tr.find(".fpitem_ggxh").val();
				var jldw = $tr.find(".fpitem_jldw").val();
				var number = $tr.find(".fpitem_number").val();
				var dj =  $tr.find(".fpitem_dj").val();
				var price = $tr.find(".fpitem_price").val();
				var slv = $tr.find(".fpitem_slv").val();
				var se = Number($tr.find(".fpitem_shuie").val()).toFixed(2);
				var hsje = Number(je)+Number(se);
				var pricecard = "0";
				var zkflag = false;
				var sfyhzc = $tr.find(".fpitem_sfyhzc").val();
				var yhzcnr = $tr.find(".fpitem_yhzcnr").val();
				var lslbs = $tr.find(".fpitem_lslbs").val();
				var bmbbh = $tr.find(".fpitem_bmbbh").val();
				var ssflbm = $tr.find(".fpitem_ssflbm").val();
				if(spmc=="" && ggxh=="" && jldw=="" && number=="" && dj=="" && price=="" && slv=="" && se=="" ){//当一行数据都为空时
					dataRight = 0;
					return false;
				}
				//当数据不完整时ggxh=="" || jldw=="" ||
				if($tr.hasClass("zhekou")){
					spmc = "折扣行数1（"+$("#zhekou_lv").val()+"%）";
					pricecard = "1";
					zkflag = true;
					
				}else{
					/*if((spmc=="" ||  number=="" || dj=="" || price=="" || slv=="" || se=="")){
						//数据不完整处理
						layer.msg('第'+(index+1)+'行  数据不完整,请补充');
						dataRight = 0;
						return dataRight;
					}*/
				}
				
				var xxfpJson = {
					"fpdm":$("#xxfpzp_detail_fpdm").val(),
					"fphm":$("#xxfpzp_detail_fphm").val(),
					"fpmxxh":index+1,
					"je":Number(je),
					"slv":Number(slv.substr(0, slv.length-1)),
					"se":Number(se),
					"spmc":spmc,
					"ggxh":ggxh,
					"jldw":jldw,
					"sl":number,
					"dj":dj,
					"hsje":hsje,
					"sfyhzc":sfyhzc,
					"yhzcnr":yhzcnr,
					"lslbs":lslbs,
					"bmbbh":bmbbh,
					"ssflbm":ssflbm
					/*,
					"djmxxh":(index+1)+"",//单据明细序号
					"fpmxxh":(index+1)+"",//单据明细序号
	*/			};
				array.push(xxfpJson);
				console.log("=========xxfpJson:"+xxfpJson);
				
			/*	return array;*/
			});
			return dataRight;
		/*}*/
		
	}
	
	 /* 图案-商品编码 点击事件 */
	 function fpitemClickspan(elementThis){//fpitem_clickspan
		 $("#fpinfo_fpitem").modal('show');
		 $mCurrentLineTr = $(elementThis).parent().parent();
		 //modal_fpitem_content
	 };
	 
	 /* 修改数量时, 金额和税额会有变动 */
/*function numberBlur(fpitem_number){//fpitem_number
	 $tr = $(fpitem_number).parent().parent();
	 //$tr = $(this);
	 var dj = $tr.find(".fpitem_dj").val();
	 var num = $(fpitem_number).val();
	 console.info(num);
	 if(num==""||num=="0"){
		 num = 1;
		 $(fpitem_number).val("1");
	 }
	 var jine = num*dj;//金额 = 数量*单价
	 var slv = $tr.find(".fpitem_slv").val();
	 var slv_int = Number(slv.substr(0, slv.length-1));
	 var jine2 = Number(jine).toFixed(2);
	 $tr.find(".fpitem_price").val(jine2);
	 var slvsb = Number(jine*slv_int*0.01).toFixed(2);
	 $tr.find(".fpitem_shuie").val(slvsb);
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
	 $("#xxfp_jehj").val(jesum.toFixed(2));
	 $("#xxfp_sehj").val(shuiesum.toFixed(2));
	 $("#xxfp_dx").val(Arabia_to_Chinese(sum.toFixed(2)));
 };*/
	 function numberBlur(fpitem_number){//fpitem_number
		 $tr = $(fpitem_number).parent().parent();
		 //$tr = $(this);
		 var dj = $tr.find(".fpitem_dj").val();
		 var num = $(fpitem_number).val();
		 console.info(num);
		 /*if(num==""){
			 num = 1;
			 $(fpitem_number).val("1");
		 }*/
		 var slv = $tr.find(".fpitem_slv").val();
		 var slv_int = Number(slv.substr(0, slv.length-1));
		 var jine = num*dj*(1+slv_int*0.01);//含税金额 = 数量*单价*（1+税率）
		 var jine2 = Number(jine).toFixed(2);
		 $tr.find(".fpitem_price").val(jine2);
		 var slvsb = Number(jine*slv_int*0.01/(1+slv_int*0.01)).toFixed(2);
		 $tr.find(".fpitem_shuie").val(slvsb);
		 
		//计算含税开票金额
		 var sumPrice = 0;
		 $.each($tr.parent().find(".fpitem_price"),function(){
			 sumPrice = (Number($(this).val())+Number(sumPrice)).toFixed(2);
		 })
		 $('#xxfp_jehj').val(sumPrice);
		 
		//计算开票税额
		 var sumShuie = 0;
		 $.each($tr.parent().find(".fpitem_shuie"),function(){
			 sumShuie = (Number($(this).val())+Number(sumShuie)).toFixed(2);
		 })
		 $('#xxfp_sehj').val(sumShuie);
		 
		 var sumheji = Number(sumPrice);
		 $('#xxfp_xx').val(sumheji);
		 $("#xxfp_dx").val(Arabia_to_Chinese(sumheji.toFixed(2)));
		 //计算单价
		 var amount = $tr.find(".fpitem_price").val();
		 if(dj == ""){
			 if(amount != ""){
				 if(num!=null && num!=""){
					 dj = (amount/num)/(1+slv_int*0.01);
					 }
				 var dj2 = Number(dj).toFixed(10);
				 $tr.find(".fpitem_dj").val(dj2);
			 }
		 }

	 };	 
 
 /* 修改单价时, 金额和税额会有变动 */
 /* function djBlur(elementThis){//fpitem_dj
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
	 var slv = $tr.find(".fpitem_slv").val();
	 var slv_int = Number(slv.substr(0, slv.length-1));
	 var jine2 = Number(jine).toFixed(2);
	 $tr.find(".fpitem_price").val(jine2);
	 var slvsb = Number(jine*slv_int*0.01).toFixed(2);
	 $tr.find(".fpitem_shuie").val(slvsb);
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
	 $("#xxfp_jehj").val(jesum.toFixed(2));
	 $("#xxfp_sehj").val(shuiesum.toFixed(2));
	 $("#xxfp_dx").val(Arabia_to_Chinese(sum.toFixed(2)));
 };*/
	 function djBlur(elementThis){//fpitem_dj
		 //console.info("into dj blur");
		 $tr = $(elementThis).parent().parent();
		 //$tr = $(this);
		 var dj = $tr.find(".fpitem_dj").val();
		 //TODO 在这里少些一个判断小数点后超过2位时发生的事情
//		 if(dj.indexOf(".")!=-1 && (dj.split(".")[1].length>2 || dj.split(".").length>2 || dj.split(".")[0].length<=0)){
//			 alert('输入的格式错误,请检查');
//			 return;
//		 }
		 var num = $tr.find(".fpitem_number").val();
		 /*if(num == ""){
			 num = 1;
			 $tr.find(".fpitem_number").val("1");
		 }*/
		 var slv = $tr.find(".fpitem_slv").val();
		 var slv_int = Number(slv.substr(0, slv.length-1));
		 var jine = num*dj*(1+slv_int*0.01);//合计金额 = 数量*单价*（1+税率）
		 var jine2 = Number(jine).toFixed(2);
		 $tr.find(".fpitem_price").val(jine2);
		 var slvsb = Number(jine*slv_int*0.01/(1+slv_int*0.01)).toFixed(2);
		 $tr.find(".fpitem_shuie").val(slvsb);
		 //计算开票含税金额
		 var sumPrice = 0;
		 $.each($tr.parent().find(".fpitem_price"),function(){
			 sumPrice = (Number($(this).val())+Number(sumPrice)).toFixed(2);
		 })
		 $('#xxfp_jehj').val(sumPrice);
		 
		//计算开票税额
		 var sumShuie = 0;
		 $.each($tr.parent().find(".fpitem_shuie"),function(){
			 sumShuie = (Number($(this).val())+Number(sumShuie)).toFixed(2);
		 })
		 $('#xxfp_sehj').val(sumShuie);
		 
		 var sumheji = Number(sumPrice);
		 $('#xxfp_xx').val(sumheji);
		 $("#xxfp_dx").val(Arabia_to_Chinese(sumheji.toFixed(2)));
		 
		 //计算数量
		 var amount = $tr.find(".fpitem_price").val();
		 if(dj != ""){
			 if(amount != "" && amount !=0.00){
				 if(num!="" && num!=0.00){
					 num = (amount-slvsb)/dj;
					 }
				 var num2 = Number(num).toFixed(2);
				 $tr.find(".fpitem_number").val(num2);
			 }
		 }

	 };
function hsdjBlur(elementThis){//fpitem_dj
    //console.info("into dj blur");
    $tr = $(elementThis).parent().parent();
    //$tr = $(this);
    var hsdj = $tr.find(".fpitem_hsdj").val();
    //TODO 在这里少些一个判断小数点后超过2位时发生的事情
//		 if(dj.indexOf(".")!=-1 && (dj.split(".")[1].length>2 || dj.split(".").length>2 || dj.split(".")[0].length<=0)){
//			 alert('输入的格式错误,请检查');
//			 return;
//		 }
    var num = $tr.find(".fpitem_number").val();
    /*if(num == ""){
        num = 1;
        $tr.find(".fpitem_number").val("1");
    }*/
    var slv = $tr.find(".fpitem_slv").val();
    var slv_int = Number(slv.substr(0, slv.length-1));
    //金额 = 数量*含税单价/（1+税率）
    var jine = num*hsdj/(1+slv_int*0.01);
    var jine2 = Number(jine).toFixed(2);
    $tr.find(".fpitem_price").val(jine2);
    //税额
    var slvsb = Number(jine*slv_int*0.01/(1+slv_int*0.01)).toFixed(2);
    $tr.find(".fpitem_shuie").val(slvsb);
    //含税金额=数量*含税单价
	var hsje=Number(num*hsdj).toFixed(2);

    //计算开票含税金额
    var sumPrice = 0;
    $.each($tr.parent().find(".fpitem_price"),function(){
        sumPrice = (Number($(this).val())+Number(sumPrice)).toFixed(2);
    })
    $('#xxfp_jehj').val(sumPrice);

    //计算开票税额
    var sumShuie = 0;
    $.each($tr.parent().find(".fpitem_shuie"),function(){
        sumShuie = (Number($(this).val())+Number(sumShuie)).toFixed(2);
    })
    $('#xxfp_sehj').val(sumShuie);

    var sumheji = Number(sumPrice);
    $('#xxfp_xx').val(sumheji);
    $("#xxfp_dx").val(Arabia_to_Chinese(sumheji.toFixed(2)));

    //计算数量
    // var amount = $tr.find(".fpitem_price").val();
    // if(dj != ""){
    //     if(amount != "" && amount !=0.00){
    //         if(num!="" && num!=0.00){
    //             num = (amount-slvsb)/dj;
    //         }
    //         var num2 = Number(num).toFixed(2);
    //         $tr.find(".fpitem_number").val(num2);
    //     }
    // }

};

/* 修改单价时, 金额和税额会有变动 */
 /*function jeBlur(elementThis){//fpitem_dj
	 //console.info("into dj blur");
	 $tr = $(elementThis).parent().parent();
	 //$tr = $(this);
	 //var dj = $tr.find(".fpitem_dj").val();
	 //TODO 在这里少些一个判断小数点后超过2位时发生的事情
//	 if(dj.indexOf(".")!=-1 && (dj.split(".")[1].length>2 || dj.split(".").length>2 || dj.split(".")[0].length<=0)){
//		 alert('输入的格式错误,请检查');
//		 return;
//	 }
	 var numStr = $tr.find(".fpitem_number").val();
	 var jine = $tr.find(".fpitem_price").val();
	 var dj;
	 if(numStr==""){
		num = 1;
		$tr.find(".fpitem_number").val("1");
		dj = jine;
	 }
	 else{
		dj = Number(jine)/Number(numStr);
	 }
	 var slv = $tr.find(".fpitem_slv").val();
	 var slv_int = Number(slv.substr(0, slv.length-1));
	 var dj2 = Number(dj).toFixed(2);
	 $tr.find(".fpitem_dj").val(dj2);
	 var slvsb = Number(jine*slv_int*0.01).toFixed(2);
	 $tr.find(".fpitem_shuie").val(slvsb);
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
	 $("#xxfp_jehj").val(jesum.toFixed(2));
	 $("#xxfp_sehj").val(shuiesum.toFixed(2));
	 $("#xxfp_dx").val(Arabia_to_Chinese(sum.toFixed(2)));
};
*/
	 function jeBlur(elementThis){//fpitem_dj
		 //console.info("into dj blur");
		 $tr = $(elementThis).parent().parent();
		 //$tr = $(this);
		 //var dj = $tr.find(".fpitem_dj").val();
		 //TODO 在这里少些一个判断小数点后超过2位时发生的事情
//		 if(dj.indexOf(".")!=-1 && (dj.split(".")[1].length>2 || dj.split(".").length>2 || dj.split(".")[0].length<=0)){
//			 alert('输入的格式错误,请检查');
//			 return;
//		 }
		 var numStr = $tr.find(".fpitem_number").val();
		 var jine = $tr.find(".fpitem_price").val();
		 var dj;
		/* if(numStr==""){
			num = 1;
			$tr.find(".fpitem_number").val("1");
			dj = jine;
		 }*/
		 
		 //计算税额
		 var slv = $tr.find(".fpitem_slv").val();
		 var slv_int = Number(slv.substr(0, slv.length-1));
		 var slvsb = Number(jine*slv_int*0.01/(1+slv_int*0.01)).toFixed(2);
		 $tr.find(".fpitem_shuie").val(slvsb);
		 
		 //计算单价
		 if(numStr!="" && numStr!=null){
			temp = Number(numStr)*(1+slv_int*0.01);
			dj = Number(jine)/temp;
			var dj2 = Number(dj).toFixed(10);
		    $tr.find(".fpitem_dj").val(dj2);
		 }
		 

		 //计算数量
		 var unitPrice = $tr.find(".fpitem_dj").val();
		 if(jine != ""){
			 if(unitPrice != ""){
				 if(numStr==""){
					 temp = Number(unitPrice)*(1+slv_int*0.01);
					 numStr = jine/temp;
					 }
				 var numStr2 = Number(numStr).toFixed(2);
				 $tr.find(".fpitem_number").val(numStr2);
			 }
		 }
		 
		 
		 //计算开票含税金额
		 var sumPrice = 0;
		 $.each($tr.parent().find(".fpitem_price"),function(){
			 sumPrice = (Number($(this).val())+Number(sumPrice)).toFixed(2);
		 })
		 $('#xxfp_jehj').val(sumPrice);
		//计算开票税额
		 var sumShuie = 0;
		 $.each($tr.parent().find(".fpitem_shuie"),function(){
			 sumShuie = (Number($(this).val())+Number(sumShuie)).toFixed(2);
		 })
		 $('#xxfp_sehj').val(sumShuie);
		 
		 var sumheji = Number(sumPrice);
		 $('#xxfp_xx').val(sumheji);
		 $("#xxfp_dx").val(Arabia_to_Chinese(sumheji.toFixed(2)));

	};

	 /* 修改税率时, 金额和税额会有变动 */
	 /*function shuilvBlur(slvElement){
		 
		 $tr = $(slvElement).parent().parent();
		 //$tr = $(this);
		 var slv = $tr.find(".fpitem_slv").val();
		 //如果不是以 %结尾, alert提示
		
		
		 var dj = $tr.find(".fpitem_dj").val();
		 var num = $tr.find(".fpitem_number").val();
		 var jine = num*dj;//金额 = 数量*单价
		 var slv_int = Number(slv.substr(0, slv.length-1));
		 var jine2 = Number(jine).toFixed(2);
		 $tr.find(".fpitem_price").val(jine2);
		 var slvsb = Number(jine*slv_int*0.01).toFixed(2);
		 $tr.find(".fpitem_shuie").val(slvsb);
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
		 $("#xxfp_jehj").val(jesum.toFixed(2));
		 $("#xxfp_sehj").val(shuiesum.toFixed(2));
		 $("#xxfp_dx").val(Arabia_to_Chinese(sum.toFixed(2)));
	 };*/
	
	function shuilvBlur(slvElement){
		 
		 $tr = $(slvElement).parent().parent();
		 //$tr = $(this);
		 var slv = $tr.find(".fpitem_slv").val();
		 var slv_int = Number(slv.substr(0, slv.length-1));
		 //如果不是以 %结尾, alert提示
		
		
		 var dj = $tr.find(".fpitem_dj").val();
		 var num = $tr.find(".fpitem_number").val();
		 var jine = num*dj*(1+slv_int*0.01);//合计金额 = 数量*单价*（1+税率）
		 var jine2 = Number(jine).toFixed(2);
		 $tr.find(".fpitem_price").val(jine2);
		 var slvsb = Number(dj*num*slv_int*0.01).toFixed(2);
		 $tr.find(".fpitem_shuie").val(slvsb);
		 
		//计算开票含税金额
		 var sumPrice = 0;
		 $.each($tr.parent().find(".fpitem_price"),function(){
			 sumPrice = (Number($(this).val())).toFixed(2);
		 })
		 $('#xxfp_jehj').val(sumPrice);
		//计算开票税额
		 var sumShuie = 0;
		 $.each($tr.parent().find(".fpitem_shuie"),function(){
			 sumShuie = (Number($(this).val())+Number(sumShuie)).toFixed(2);
		 })
		 
		 var sumheji = Number(sumPrice);
		 $('#xxfp_xx').val(sumheji);
		 $("#xxfp_dx").val(Arabia_to_Chinese(sumheji.toFixed(2)));
		
	 };
	 
	 /* 初始化autoComplete数组  */
		function initAutoCompleteGouxiao(objs){
			var mGfmc = new Array();
			var mGfsh = new Array();
			var mGfdzdh = new Array();
			var mGfyhzh = new Array();
			for (var i = 0; i < objs.length; i++) {
				var gfmc = objs[i].name;
				var gfsh = objs[i].shuihao;
				//mShuishouBmArray.push(bm+"--"+mc);
				mGfmc.push(gfmc);
				mGfsh.push(gfsh);
				mGfdzdh.push(objs[i].address);
				mGfyhzh.push(objs[i].banknum);
			}
			//console.info("init ---");
			//console.info(mGfyhzh);
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
			    		if(mc == objs[j].name){
			    			var gfmc = objs[j].name;
			    			var gfsh = objs[j].shuihao;
					    	var gfdzdh = objs[j].address;
					    	var gfyhzh = objs[j].banknum;
					    	$("#xxfp_gfmc").val(gfmc);
					    	$("#xxfp_gfsh").val(gfsh);
					    	$("#xxfp_gfdzdhg").val(gfdzdh);
					    	$("#xxfp_gfyhzh").val(gfyhzh);
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
			    		if(gfsh == objs[j].shuihao){
			    			var gfmc = objs[j].name;
			    			var gfsh = objs[j].shuihao;
					    	var gfdzdh = objs[j].address;
					    	var gfyhzh = objs[j].banknum;
					    	$("#xxfp_gfmc").val(gfmc);
					    	$("#xxfp_gfsh").val(gfsh);
					    	$("#xxfp_gfdzdhg").val(gfdzdh);
					    	$("#xxfp_gfyhzh").val(gfyhzh);
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
			    		if(dzdh == objs[j].address){
			    			var gfmc = objs[j].name;
			    			var gfsh = objs[j].shuihao;
					    	var gfdzdh = objs[j].address;
					    	var gfyhzh = objs[j].banknum;
					    	$("#xxfp_gfmc").val(gfmc);
					    	$("#xxfp_gfsh").val(gfsh);
					    	$("#xxfp_gfdzdhg").val(gfdzdh);
					    	$("#xxfp_gfyhzh").val(gfyhzh);
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
			    		if(kaihuhang == objs[j].banknum){
			    			var gfmc = objs[j].name;
			    			var gfsh = objs[j].shuihao;
					    	var gfdzdh = objs[j].address;
					    	var gfyhzh = objs[j].banknum;
					    	$("#xxfp_gfmc").val(gfmc);
					    	$("#xxfp_gfsh").val(gfsh);
					    	$("#xxfp_gfdzdhg").val(gfdzdh);
					    	$("#xxfp_gfyhzh").val(gfyhzh);
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
	var mCurrentLineNo = 1;
	function addXxfpItemLineHtmlData(){
		if(mCurrentQdListReady==1){
			layer.msg('已经有清单了,不能增行了!',{time:2000});
			return;
		}
		var trHtml = ("<tr align='center' class='fpitem_line border-input '>"
		+"<td > <input type='radio' name='xxfp-line-radio' id='optionsRadios1' value='option1' >&nbsp;选中 </td>"		
        +"<td class='input-group'>"
        +"<input type='text' class='form-control special-transparent fpitem_mc fapiao-left' maxlength='100' placeholder='从商品编码表一个一个选'>"
        +  "<span class='input-group-btn fpitem_clickspan'  onclick='fpitemClickspan(this)' > "
	    +"         <button type='button' class='btn btn-info'  ><i class='fa fa-bars fa-line-height'></i></button>"
	    +"   </span>"
        +"</td>"
        +"<td><input type='text' class='form-control special-transparent fpitem_ggxh' maxlength='40' placeholder=''></td>"
        +"<td><input type='text' class='form-control special-transparent fpitem_jldw' maxlength='32' placeholder=''></td>"
        +"<td><input type='text' class='form-control special-transparent fpitem_number' onblur='numberBlur(this)' placeholder='' ></td>"
        +" <td><input type='text' class='form-control special-transparent fpitem_dj'  onblur='djBlur(this)'  placeholder=''></td>"
        +"<td><input type='text' class='form-control special-transparent fpitem_price' placeholder='' onblur='jeBlur(this)'></td>"
        +"<td width='5%'><input type='text' class='form-control special-transparent fpitem_slv' onblur='shuilvBlur(this)' placeholder=''  ></td>"
        +"<td><input type='text' class='form-control special-transparent fpitem_shuie' placeholder=''  disabled='disabled'></td>"
        +"</tr>");
        //parent
        //$("#xxfp_table_item").append(htmlLine);
		if(mCurrentLineNo>=8){
	        	alert('已超出发票详情打印上限');
	        	return;
	    }
        addTr('xxfp_table_item', mCurrentLineNo, trHtml);
        mCurrentLineNo++;
        //xxfpItemToQDfpxxItem();
	};
	//显示清单里的详情数据
	function showQingdanItem(array){
		//如果没数据  , 不读取
		if(array==null || array.length==0){
			return;
		}
		//$("#xxfp_qingdan_item_table")
		var trQdHtml = ("<tr align='center' class='qiandan-tr border-input '>"
				+"<td > <input type='radio' name='qdxxfp-line-radio' class='qd_fpitem_radio' value='option1' >选中 </td>"
		        +"<td class='input-group'>"
		        +"<input type='text' class='form-control special-transparent qd_fpitem_mc' maxlength='100' placeholder='从商品编码表一个一个选'>"
		        +  "<span class='input-group-btn qd_fpitem_clickspan'  onclick='fpitemClickspan(this)' > "
			    +"         <button type='button' class='btn btn-info'  ><i class='fa fa-bars fa-line-height'></i></button>"
			    +"   </span>"
		        +"</td>"
		        +"<td><input type='text' class='form-control special-transparent qd_fpitem_ggxh' maxlength='40' placeholder=''></td>"
		        +"<td><input type='text' class='form-control special-transparent qd_fpitem_jldw' maxlength='32' placeholder=''></td>"
		        +"<td><input type='text' class='form-control special-transparent qd_fpitem_number'  onblur='qdnumberBlur(this)' placeholder='' ></td>"
		        +" <td><input type='text' class='form-control special-transparent qd_fpitem_dj'   onblur='qddjBlur(this)' placeholder=''></td>"
		        +"<td><input type='text' class='form-control special-transparent qd_fpitem_price' placeholder='' onblur='qdjeBlur(this)'></td>"
		        +"<td width='5%'><input type='text' class='form-control special-transparent qd_fpitem_slv' onblur='qdshuilvBlur(this)' placeholder=''  ></td>"
		        +"<td><input type='text' class='form-control special-transparent qd_fpitem_shuie' placeholder=''  disabled='disabled'></td>"
		        +"<td hidden><input type='text' class='form-control special-transparent fpitem_sfyhzc' placeholder='' disabled='disabled'></td>"
		        +"<td hidden><input type='text' class='form-control special-transparent fpitem_yhzcnr' placeholder=''  disabled='disabled' ></td>"
		        +"<td hidden><input type='text' class='form-control special-transparent fpitem_lslbs' placeholder=''  disabled='disabled' ></td>"
		        +"<td hidden><input type='text' class='form-control special-transparent fpitem_bmbbh' placeholder=''  disabled='disabled' ></td>"
		        +"<td hidden><input type='text' class='form-control special-transparent fpitem_ssflbm' placeholder=''  disabled='disabled' ></td>"
		        +"</tr>");
		 $.each(array,function(index,value){
			 console.info("index=="+index + ",value=="+value);
			 addTr('xxfp_qingdan_item_table', mCurrentLineNo, trQdHtml);
			 mCurrentLineNo++;
			 console.info("mCurrentLineNo=="+mCurrentLineNo);
		 });
		 $(".qiandan-tr").each(function(index,element){
			 $qdTr = $(this);
			 $qdTr.find(".qd_fpitem_mc").val(array[index].mc);
			 $qdTr.find(".qd_fpitem_ggxh").val(array[index].ggxh);
			 $qdTr.find(".qd_fpitem_jldw").val(array[index].jldw);
			 $qdTr.find(".qd_fpitem_number").val(array[index].number);
			 $qdTr.find(".qd_fpitem_dj").val(array[index].dj);
			 $qdTr.find(".qd_fpitem_price").val(array[index].price);
			 $qdTr.find(".qd_fpitem_slv").val(array[index].slv);
			 $qdTr.find(".qd_fpitem_shuie").val(array[index].shuie);
		 })
		 
	}
	/* 清空所有详情信息 */
	function clearAllLine(){
		 $(".fpitem_line").each(function(index ,element){
			$(this).remove(); 
		 });
		 mCurrentLineNo = 0;
	}
	
	//发票页 "折扣"按钮
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
	}
		
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
			//税率
			var slv = $(element).find(".fpitem_slv").val();
			//税额
			var shuie = $(element).find(".fpitem_shuie").val();
			
			var sfyhzc = $(element).find(".fpitem_sfyhzc").val();
			
			var yhzcnr = $(element).find(".fpitem_yhzcnr").val();
			
			var lslbs = $(element).find(".fpitem_lslbs").val();
			
			var bmbbh = $(element).find(".fpitem_bmbbh").val();
			
			var ssflbm = $(element).find(".fpitem_ssflbm").val();
			
			var obj = new Object();
			obj.mc = mc;
			obj.ggxh = ggxh;
			obj.jldw = jldw;
			obj.number = number;
			obj.dj = dj;
			obj.price = price;
			obj.slv = slv;
			obj.shuie = shuie;
			obj.sfyhzc = sfyhzc;
			obj.yhzcnr = yhzcnr;
			obj.lslbs = lslbs;
			obj.bmbbh = bmbbh;
			obj.ssflbm = ssflbm;
			xxfpItemArray.push(obj);
			/* var json = JSON.stringify({
					"mc":mc,
					"ggxh":ggxh,
					"jldw":jldw,
					"number":number,
					"dj":dj,
					"price":price,
					"slv":slv,
					"shuie":shuie
				})*/
			})
			return xxfpItemArray;
		}
		
	};
	//从清单点击转发票时，数据处理
	function qdfpItemToxxfpItem(jsonArray,isQdFlag){
		//console.info(typeof(JSON.parse('{"name":"cpf","age":"23"}')));
		clearAllLine();//先清空旧数据,归0 mCurrentLineNo
		//存一个全局变量
		mQingdanArray = jsonArray;
		mIsQdFlag = isQdFlag;
		//当清单长度大于8的时候，只打印一行，详情见清单
		if(jsonArray.length>8||isQdFlag){
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
			$tr.find(".fpitem_price").val(price);
			$.each(jsonArray,function(index,value){
				if(jsonArray[index].shuie!="" &&jsonArray[index].shuie!="NaN"){
					shuie+= Number(jsonArray[index].shuie);
				}
			});
			shuie = shuie.toFixed(2);
			$tr.find(".fpitem_shuie").val(shuie);
			$("#xxfp_xx").val(Number(price+shuie).toFixed(2));
			$("#xxfp_jehj").val(Number(price.toFixed(2)));
			$("#xxfp_sehj").val(Number(shuie));
			$("#xxfp_dx").val(Arabia_to_Chinese(Number(price+shuie).toFixed(2)));
			return;
		}
		else{//当清单长度<=8
			console.info("qd-xxfp Array length长度 = "+jsonArray.length);
			$.each(jsonArray,function(index,value){
				//当从清单回到页面的时候，重置的时候
				addXxfpItemLineHtmlData();
				
			});
			 $(".fpitem_line").each(function(index,element){
				 	jsonObj = jsonArray[index];
					$tr = $(this);
					$tr.find(".fpitem_mc").val(jsonObj.mc);
					$tr.find(".fpitem_ggxh").val(jsonObj.ggxh);
					$tr.find(".fpitem_jldw").val(jsonObj.jldw);
					$tr.find(".fpitem_number").val(jsonObj.number);
					$tr.find(".fpitem_dj").val(jsonObj.dj);
					$tr.find(".fpitem_price").val(jsonObj.price);
					$tr.find(".fpitem_slv").val(jsonObj.slv);
					$tr.find(".fpitem_shuie").val(jsonObj.shuie);
			 })
		}
	}
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
	}
	//清单 - 添加一行
	function qingdanAddLine(){
		var trQdHtml = ("<tr align='center' class='qiandan-tr border-input '>"
				+"<td > <input type='radio' name='qdxxfp-line-radio' class='qd_fpitem_radio' value='option1' >选中 </td>"
		        +"<td class='input-group'>"
		        +"<input type='text' class='form-control special-transparent qd_fpitem_mc' maxlength='100' placeholder='从商品编码表一个一个选'>"
		        +  "<span class='input-group-btn qd_fpitem_clickspan'  onclick='fpitemClickspan(this)' > "
			    +"         <button type='button' class='btn btn-info'  ><i class='fa fa-bars fa-line-height'></i></button>"
			    +"   </span>"
		        +"</td>"
		        +"<td><input type='text' class='form-control special-transparent qd_fpitem_ggxh' maxlength='40' placeholder=''></td>"
		        +"<td><input type='text' class='form-control special-transparent qd_fpitem_jldw' maxlength='32' placeholder=''></td>"
		        +"<td><input type='text' class='form-control special-transparent qd_fpitem_number'  onblur='qdnumberBlur(this)' placeholder='' ></td>"
		        +" <td><input type='text' class='form-control special-transparent qd_fpitem_dj'   onblur='qddjBlur(this)' placeholder=''></td>"
		        +"<td><input type='text' class='form-control special-transparent qd_fpitem_price' placeholder='' onblur='qdjeBlur(this)'></td>"
		        +"<td width='5%'><input type='text' class='form-control special-transparent qd_fpitem_slv' onblur='qdshuilvBlur(this)' placeholder=''  ></td>"
		        +"<td><input type='text' class='form-control special-transparent qd_fpitem_shuie' placeholder=''  disabled='disabled'></td>"
		        +"</tr>");
		  	//***addTr('qingdan-list', mCurrentLineNo+1, trQdHtml);
            addQdTr('xxfp_qingdan_item_table', mCurrentLineNo, trQdHtml);
	        mCurrentLineNo++;
	}
	//清单 - 减少一行
	/**
	 * 
	 */
	function qingdanDelLine(ele){
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
		//如果是折扣行被删除了 , 那么需要删除它本身商品行的只读属性
		if($tr.hasClass("zhekou")){
			qddelZhekouLineHappen($tr.prev());
		}
		//如果是有折扣行的商品行被删除 , 那么折扣行也被删除
		if($tr.hasClass("hasZhekou")){
			$tr.next().remove();
		}
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
			qdfpItemToxxfpItem(qdxxfpItemArray,true);
		}
		mCurrentQdListReady = 1;
		//clearAllLine();
		$("#qingdan-list").modal('hide');
	}
	//清单 - 打印
	function qingdanPrint(ele){
		var retCode = printInv(infoKind,infoTypeCode,infoNumber,'1',"1");
	    if(retCode ==5011){
	    	alert("打印成功！！");
	    }
	}