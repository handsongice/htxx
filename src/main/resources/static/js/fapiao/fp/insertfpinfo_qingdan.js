	 /* 修改数量时, 金额和税额会有变动 */
function qdnumberBlur(fpitem_number){//fpitem_number
	
	 $tr = $(fpitem_number).parent().parent();
	 //$tr = $(this);
	 //var dj = $tr.find(".qd_fpitem_dj").val();
     var hsdj = $tr.find(".qd_fpitem_hsdj").val();
	 var num = $(fpitem_number).val();
	 //如果输入0,那么警告他并修改成1
	 if("0"==num){
		 alert('醒醒同志,输入0是要做什么?',{time:1500});
		 $(fpitem_number).val("1");
		 return;
	 }

	 var slv = $tr.find(".qd_fpitem_slv").text();
	 var slv_int = Number(slv.substr(0, slv.length-1));
    //var jine = num*dj;//金额 = 数量*单价
    //金额 = 数量*含税单价/（1+税率）
    var jine = num*hsdj/(1+slv_int*0.01);
	 var jine2 = Number(jine).toFixed(2);
	 $tr.find(".qd_fpitem_price").val(jine2);
	 var slvsb = Number(jine*slv_int*0.01).toFixed(2);
	 $tr.find(".qd_fpitem_shuie").val(slvsb);
    //*************含税金额发生变化
    console.log(slvsb);
    console.log(jine2);
    var hsje = Number(Number(slvsb)+Number(jine2)).toFixed(2);
    console.log(hsje);
    $tr.find(".qd_fpitem_hsje").val(hsje);
    //单价
    var dj =Number(hsdj/(1+slv_int*0.01)).toFixed(6) ;
    $tr.find(".qd_fpitem_dj").val(dj);
 };
 
 /* 修改单价时, 金额和税额会有变动 */
  function qddjBlur(elementThis){//fpitem_dj
	 //console.info("into dj blur");
	 $tr = $(elementThis).parent().parent();
	 //$tr = $(this);
	 var dj = $tr.find(".qd_fpitem_dj").val();
	 //TODO 在这里少些一个判断小数点后超过2位时发生的事情
//	 if(dj.indexOf(".")!=-1 && (dj.split(".")[1].length>2 || dj.split(".").length>2 || dj.split(".")[0].length<=0)){
//		 alert('输入的格式错误,请检查');
//		 return;
//	 }
	 var num = $tr.find(".qd_fpitem_number").val();
	 var jine = num*dj;//金额 = 数量*单价
	 var slv = $tr.find(".qd_fpitem_slv").text();
	 var slv_int = Number(slv.substr(0, slv.length-1));
	 var jine2 = Number(jine).toFixed(2);
	 $tr.find(".qd_fpitem_price").val(jine2);
	 var slvsb = Number(jine*slv_int*0.01).toFixed(2);
	 $tr.find(".qd_fpitem_shuie").val(slvsb);

      //*************含税金额发生变化
      console.log(slvsb);
      console.log(jine2);
      var hsje = Number(Number(slvsb)+Number(jine2)).toFixed(2);
      console.log(hsje);
      $tr.find(".qd_fpitem_hsje").val(hsje);
	
 };

     /* 修改单价时, 金额和税额会有变动 */
     function qdhsdjBlur(elementThis){//fpitem_dj
         //console.info("into dj blur");
         $tr = $(elementThis).parent().parent();
         //$tr = $(this);
         var hsdj = $tr.find(".qd_fpitem_hsdj").val();
         //TODO 在这里少些一个判断小数点后超过2位时发生的事情
//	 if(dj.indexOf(".")!=-1 && (dj.split(".")[1].length>2 || dj.split(".").length>2 || dj.split(".")[0].length<=0)){
//		 alert('输入的格式错误,请检查');
//		 return;
//	 }
         var num = $tr.find(".qd_fpitem_number").val();
         var slv = $tr.find(".qd_fpitem_slv").text();
         var slv_int = Number(slv.substr(0, slv.length-1));
         //金额 = 数量*含税单价/（1+税率）
         var jine = num*hsdj/(1+slv_int*0.01);
         var jine2 = Number(jine).toFixed(2);
         $tr.find(".qd_fpitem_price").val(jine2);
         var slvsb = Number(jine*slv_int*0.01).toFixed(2);
         $tr.find(".qd_fpitem_shuie").val(slvsb);

         //*************含税金额发生变化
         console.log(slvsb);
         console.log(jine2);
         var hsje = Number(Number(slvsb)+Number(jine2)).toFixed(2);
         console.log(hsje);
         $tr.find(".qd_fpitem_hsje").val(hsje);
         //单价
         var dj =Number(hsdj/(1+slv_int*0.01)).toFixed(6) ;
         $tr.find(".qd_fpitem_dj").val(dj);

     };

	 /* 修改税率时, 金额和税额会有变动 */
	 function qdshuilvBlur(slvElement){
		 
		 $tr = $(slvElement).parent().parent();
		 //$tr = $(this);
		 var slv = $tr.find(".qd_fpitem_slv").text();
		 //如果不是以 %结尾, alert提示
		 var dj = $tr.find(".qd_fpitem_dj").val();
		 var num = $tr.find(".qd_fpitem_number").val();
		 var jine = num*dj;//金额 = 数量*单价
		 var slv_int = Number(slv.substr(0, slv.length-1));
		 var jine2 = Number(jine).toFixed(2);
		 $tr.find(".qd_fpitem_price").val(jine2);
		 var slvsb = Number(jine*slv_int*0.01).toFixed(2);
		 $tr.find(".qd_fpitem_shuie").val(slvsb);
	
		
	 };
	 
	 /* 修改单价时, 金额和税额会有变动 */
	 function qdjeBlur(elementThis){//fpitem_dj
		 //console.info("into dj blur");
		 $tr = $(elementThis).parent().parent();
		 //$tr = $(this);
		 //var dj = $tr.find(".fpitem_dj").val();
		 //TODO 在这里少些一个判断小数点后超过2位时发生的事情
//		 if(dj.indexOf(".")!=-1 && (dj.split(".")[1].length>2 || dj.split(".").length>2 || dj.split(".")[0].length<=0)){
//			 alert('输入的格式错误,请检查');
//			 return;
//		 }
		 var numStr = $tr.find(".qd_fpitem_number").val();
		 var jine = $tr.find(".qd_fpitem_price").val();
		 var dj;
		 if(numStr==""){
			num = 1;
			$tr.find(".qd_fpitem_number").val("1");
			dj = jine;
		 }
		 else{
			dj = Number(jine)/Number(numStr);
		 }
		 var slv = $tr.find(".qd_fpitem_slv").text();
		 var slv_int = Number(slv.substr(0, slv.length-1));
		 var dj2 = Number(dj).toFixed(2);
		 $tr.find(".qd_fpitem_dj").val(dj2);
		 var slvsb = Number(jine*slv_int*0.01).toFixed(2);
		 $tr.find(".qd_fpitem_shuie").val(slvsb);
		 //合计变动
		 var sum=0;
		 var jesum = 0;
		 var shuiesum = 0;
		 $(".qd_fpitem_price").each(function(index ,element){
			 sum += Number($(this).val());
			 jesum += Number($(this).val());
		 })
		  $(".qd_fpitem_shuie").each(function(index ,element){
			 sum += Number($(this).val());
			 shuiesum += Number($(this).val());
		 })
		 $("#xxfp_xx").val(sum.toFixed(2));
		 $("#xxfp_jehj").val(jesum.toFixed(2));
		 $("#xxfp_sehj").val(shuiesum.toFixed(2));
		 $("#xxfp_dx").val(Arabia_to_Chinese(sum.toFixed(2)));
	};

     /* 修改含税价格时, 含税单价和税额会有变动 */
     function qd_autoGetPriceBlur(fpitem_number){
         $tr = $(fpitem_number).parent().parent();
         //$tr = $(this);
         //含税单价
         var dj = $tr.find(".qd_fpitem_dj").val();
         //不含税金额
         var jine= $tr.find(".qd_fpitem_price").val();
         //含税金额
         var hsje= $tr.find(".qd_fpitem_hsje").val();
         //数量
         var num =$tr.find(".qd_fpitem_number").val();
         var slv = $tr.find(".qd_fpitem_slv").text();
         var slv_int = 0;
         if(slv=="免税"){
             slv_int = 0;
         }else{
             slv_int = Number(slv.substr(0, slv.length-1));
         }
         console.info("数量:"+num);
         console.info("税率:"+slv_int);
         console.info("含税单价:"+dj);
         console.info("不含税金额:"+jine);
         if(num==""||num=="0"){
             num = 1;
             $(fpitem_number).val("1");
         }
         jine = (hsje/(1+slv_int/100)).toFixed(2);//不含税金额 = 含税金额/(1+税率)
         //不含税单价
         dj=(Number(hsje/(1+slv_int/100))/Number(num)).toFixed(6);
         $tr.find(".qd_fpitem_dj").val(dj);
         //含税单价
         var hsdj=(Number(hsje)/Number(num)).toFixed(6);
         $tr.find(".qd_fpitem_hsdj").val(hsdj);
         //var slv_int = Number(slv.substr(0, slv.length-1));
         var jine2 = Number(jine).toFixed(2);
         $tr.find(".qd_fpitem_price").val(jine2);
         var slvsb =  (Number(hsje)-Number(jine2)).toFixed(2);
         $tr.find(".qd_fpitem_shuie").val(slvsb);

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