	 /* 折扣率,0~100,折扣率变化时,折扣金额变化 */
function zhekoulvBlur(input){//fpitem_number
	 var zklv = $(input).val();
	 if(zklv>100 || zklv<0){
		 //alert('折扣率只能在0~100之间');
		 return;
	 }
	 //合计变动
	 var spje = $("#zhekou_spprice").val();
	 var zkjeNumber = Number(spje)*Number(zklv)/100;
	 $("#zhekou_jine").val(zkjeNumber.toFixed(2));
	
 };
 //输入折扣金额,失去焦点后
 function zhekoujeBlur(input){//fpitem_number
	 var zkje = Number($(input).val());
	 var spje = Number($("#zhekou_spprice").val());
	 if(spje < zkje){
		 alert('折扣金额不能大于商品金额');
		 return;
	 }
	 //合计变动
	 
	 var zklv = zkje/spje*100;
	 $("#zhekou_lv").val(zklv.toFixed(2));
	
 };
 
	 
	 //提交，折扣
function commitZheKouModal(ele){
	//1、读取 点击折扣行的信息 , 名称- 
	$radio = $('input[name="xxfp-line-radio"]:checked ');
	//1.2、 获取选中行
	$tr = $radio.parent().parent();
	addLineToHasZhekou($tr);
	//$tr.find("button").attr("hidden","hidden");
	var mc = $tr.find(".fpitem_mc").val();
	var yslv = $tr.find(".fpitem_slv").val();
	var trZhekouHtml = ("<tr align='center' class='fpitem_line border-input zhekou' >"
			+"<td > <input type='radio' name='xxfp-line-radio' id='optionsRadios1' value='option1' >&nbsp;选中 </td>"		
	        +"<td class='input-group'>"
	        +"<input type='text' class='form-control special-transparent fpitem_mc' maxlength='100' placeholder='从商品编码表一个一个选' readonly='readonly' >"
	        +  "<span class='input-group-btn fpitem_clickspan'  onclick='fpitemClickspan(this)' > "
		    +"         <button type='button' class='btn btn-info'  ><i class='fa fa-bars fa-line-height'></i></button>"
		    +"   </span>"
	        +"</td>"
	        +"<td><input type='text' class='form-control special-transparent fpitem_ggxh' maxlength='40' placeholder='' readonly='readonly'></td>"
	        +"<td><input type='text' class='form-control special-transparent fpitem_jldw' maxlength='32' placeholder='' readonly='readonly'></td>"
	        +"<td><input type='text' class='form-control special-transparent fpitem_number'  placeholder='' readonly='readonly'></td>"
	        +" <td><input type='text' class='form-control special-transparent fpitem_dj'    placeholder='' readonly='readonly'></td>"
	        +"<td><input type='text' class='form-control special-transparent fpitem_price' placeholder=''  disabled='disabled'></td>"
	        +"<td width='5%'><input type='text' class='form-control special-transparent fpitem_slv'   placeholder='' readonly='readonly' ></td>"
	        +"<td><input type='text' class='form-control special-transparent fpitem_shuie' placeholder=''  disabled='disabled'></td>"
	        +"</tr>");
			if(mCurrentLineNo>=8){
		        	alert('已超出发票详情打印上限');
		        	return;
		    }
		  	addTr('xxfp_table_item', $tr.index(), trZhekouHtml);
	
	//2、 写入金额,税额
	$nextTr = $tr.next();
	//var mc = "折扣（"+$("#zhekou_lv").val()+"%）";
	$nextTr.find(".fpitem_mc").val(mc);
	var zhekoue = Number($("#zhekou_jine").val());
	$nextTr.find(".fpitem_price").val("-"+zhekoue);
	var slv = $tr.find(".fpitem_slv").val();
	if(slv=="不扣税" || slv=="免税"){
		$nextTr.find(".fpitem_slv").val(yslv);
		
	}
	else{
		var slv_int = Number(slv.substr(0, slv.length-1))/100;
		console.log("---------------"+$("#zhekou_spprice").val());
		var shuie = -Number($("#zhekou_spprice").val())*slv_int*Number($("#zhekou_lv").val())/100;
		console.log("----------------shuie:"+shuie);
		//var shuie = slv_int*zhekoue;
		$nextTr.find(".fpitem_shuie").val(shuie.toFixed(2));
		$nextTr.find(".fpitem_slv").val(yslv);
	}
	$("#zhekou-list").modal('hide');
	//3、mCurrentLineNo++
	mCurrentLineNo++;
}

//取消,
function cancelZheKouModal(ele){
	$("#zhekou_jine").val("");
	$("#zhekou_lv").val("");
	$("#zhekou_lineno").val("1");
	$("#zhekou-list").modal('hide');
}

//处理把当前行 -> 有折扣的行  ： 1.点击折扣不能再添加。 2.本行只读
function addLineToHasZhekou(ele){
	$tr3 = ele;

	//1.3、 选中行也要全变成只读
	$tr3.find("input").attr("readonly","readonly");
	//不允许再选商品了
	$tr3.find(".fpitem_clickspan").removeAttr("onclick");
	$tr3.addClass("hasZhekou");
	
}
/**
 * 
 * @param ele 折扣行所属商品行的tr
 */
function delZhekouLineHappen(ele){
	$tr3 = ele;
	//1.3、 选中行也要全变成只读
	$tr3.find("input").removeAttr("readonly");
	//不允许再选商品了
	$tr3.find(".fpitem_clickspan").attr("onclick","fpitemClickspan(this)");
	$tr3.removeClass("hasZhekou");
}