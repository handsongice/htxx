	 /* 折扣率,0~100,折扣率变化时,折扣金额变化 */
function qdzhekoulvBlur(input){//fpitem_number
	 var zklv = $(input).val();
	 if(zklv>100 || zklv<0){
		 //alert('折扣率只能在0~100之间');
		 return;
	 }
	 //合计变动
	 var spje = $("#qdzhekou_spprice").val();
	 var zkjeNumber = Number(spje)*Number(zklv)/100;
	 $("#qdzhekou_jine").val(zkjeNumber.toFixed(2));
	
 };
 //输入折扣金额,失去焦点后
 function qdzhekoujeBlur(input){//fpitem_number
	 var zkje = Number($(input).val());
	 var spje = Number($("#qdzhekou_spprice").val());
	 if(spje < zkje){
		 alert('折扣金额不能大于商品金额');
		 return;
	 }
	 //合计变动
	 
	 var zklv = zkje/spje*100;
	 $("#qdzhekou_lv").val(zklv.toFixed(2));
	
 };
 
	 
	 //提交，折扣
function qdcommitZheKouModal(ele){
	//1、读取 点击折扣行的信息 , 名称- 
	$radio = $('input[name="qdxxfp-line-radio"]:checked ');
	//1.2、 获取选中行
	$tr = $radio.parent().parent();
	qdaddLineToHasZhekou($tr);
	//$tr.find("button").attr("hidden","hidden");
	var mc = $tr.find(".qd_fpitem_mc").val();
	var trQdHtml = ("<tr align='center' class='qiandan-tr border-input zhekou' >"
				+"<td > <input type='radio' name='qdxxfp-line-radio' class='qd_fpitem_radio' value='option1' >选中 </td>"
		        +"<td class='input-group'>"
		        +"<input type='text' class='form-control special-transparent qd_fpitem_mc' maxlength='100' placeholder='从商品编码表一个一个选' readonly='readonly'>"
		        +  "<span class='input-group-btn qd_fpitem_clickspan' style='visibility:hidden;'> "
			    +"         <button type='button' class='btn btn-info'  ><i class='fa fa-bars fa-line-height' ></i></button>"
			    +"   </span>"
		        +"</td>"
		        +"<td><input type='text' class='form-control special-transparent qd_fpitem_ggxh' maxlength='40' placeholder='' readonly='readonly'></td>"
		        +"<td><input type='text' class='form-control special-transparent qd_fpitem_jldw' maxlength='32' placeholder='' readonly='readonly'></td>"
		        +"<td><input type='text' class='form-control special-transparent qd_fpitem_number'    placeholder='' readonly='readonly'></td>"
		        +" <td><input type='text' class='form-control special-transparent qd_fpitem_dj'    placeholder='' readonly='readonly' ></td>"
		        +"<td><input type='text' class='form-control special-transparent qd_fpitem_price' placeholder=''  disabled='disabled' ></td>"
		        +"<td width='5%'><input type='text' class='form-control special-transparent qd_fpitem_slv'  placeholder='' readonly='readonly' ></td>"
		        +"<td><input type='text' class='form-control special-transparent qd_fpitem_shuie' placeholder=''  disabled='disabled'></td>"
		        +"</tr>");
		  	addTr('qingdan-list', $tr.index()+1, trQdHtml);
	
	//2、 写入金额,税额
	$nextTr = $tr.next();
	$nextTr.find(".qd_fpitem_mc").val(mc);
	var zhekoue = Number($("#qdzhekou_jine").val());
	console.log("zhekou金额:"+zhekoue);
	$nextTr.find(".qd_fpitem_price").val("-"+zhekoue);
	var slv = $tr.find(".qd_fpitem_slv").val();
	if(slv=="不扣税" || slv=="免税"){
		$nextTr.find(".qd_fpitem_shuie").val(0);
	}
	else{
		var slv_int = Number(slv.substr(0, slv.length-1))/100;
		//var shuie = slv_int*jine;
		var shuie = -Number($("#qdzhekou_spprice").val())*slv_int*Number($("#qdzhekou_lv").val())/100;
		$nextTr.find(".qd_fpitem_shuie").val(shuie.toFixed(2));
	}
	$("#qdzhekou-list").modal('hide');
	//给折扣数据清0
	$("#qdzhekou_jine").val("");
	$("#qdzhekou_lv").val("");
	//3、mCurrentLineNo++
	mCurrentLineNo++;
}

//取消,
function qdcancelZheKouModal(ele){
	$("#qdzhekou_jine").val("");
	$("#qdzhekou_lv").val("");
	$("#qdzhekou_lineno").val("1");
	$("#qdzhekou-list").modal('hide');
}

//处理把当前行 -> 有折扣的行  ： 1.点击折扣不能再添加。 2.本行只读
function qdaddLineToHasZhekou(ele){
	$tr2 = ele;

	//1.3、 选中行也要全变成只读
	$tr2.find("input").attr("readonly","readonly");
	//不允许再选商品了
	$tr2.find(".qd_fpitem_clickspan").removeAttr("onclick");
	$tr2.addClass("hasZhekou");
	
}
/**
 * 
 * @param ele 折扣行所属商品行的tr
 */
function qddelZhekouLineHappen(ele){
	$tr2 = ele;
	//1.3、 选中行也要全变成只读
	$tr2.find("input").removeAttr("readonly");
	//不允许再选商品了
	$tr2.find(".qd_fpitem_clickspan").attr("onclick","fpitemClickspan(this)");
	$tr2.removeClass("hasZhekou");
}