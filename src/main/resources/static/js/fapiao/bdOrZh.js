$(function(){
	win_bd = $('#bd_window').window({  
	    closed:true
	});
	form_bd = win_bd.find('form_bd');
	
	//combobox单击空白部分展开
	$('.combo').click(function(){
		$(this).prev().combobox('showPanel');
	})
	
});

//毙单 
function bd(basePath,bdszt,msg){
	var row = $('#list').datagrid('getSelected'); //获取选中的行
	if (row){
		if(row.sjzt!=bdszt){
			msgShow("系统提示",msg,"warning");
			return;
		}
		win_bd.window('open');
	    form_bd.form('clear');
		$('#bdyy').combobox({
	        url:'reason/allBd', 
	        editable:false,
	        multiple:false,
	        valueField:'id',   
          	textField:'bdyy',
			onSelect : function () {
				var bdyy = $('#bdyy').combobox('getValue');
				$.messager.confirm("系统提示","确定毙单吗？",function(e){
					if(e){
						$.ajax({
							url:basePath+"sjxx/bdOrZh",
							type:"post",
							data:{"id":row.id,"bdyy":bdyy,"bdszt":bdszt},
							dataType:"json",
							success:function(rst){
								if(rst.state==1){
									closeWindow();
									msgShow("系统提示",rst.msg,"info");
									$('#list').datagrid('reload');
								}else{
									msgShow("系统提示",rst.msg,"info");
								}
							},
							error:function(){
								alert("系统错误，毙单失败");
							}
						});
					}
				});
			},
        });
		/* $('.combo').click(function(){
			$(this).prev().combobox('showPanel');
		}) */
	}else{
		msgShow("系统提示","请单击列表行，选择一条记录","info");
	}
}
//追回
function zh(basePath){
	var row = $('#list').datagrid('getSelected'); //获取选中的行
	if (row){
		if(row.sjzt!="-1"){
			msgShow("系统提示","只有已毙单商机允许追回","warning");
			return;
		}
		//追回提醒
		var msg = "";
		if(row.ss>1){
			msg = "已存在相同商机，";
		}
		$.messager.confirm("系统提示",msg+"确定追回该商机吗？",function(e){
			if(e){
				var id = row.id; //获取选中行的id
				$.ajax({
					url:basePath+"sjxx/bdOrZh",
					type:"post",
					data:{"id":id,"sjzt":row.bdszt},
					dataType:"json",
					success:function(rst){
						if(rst.state==1){ 
							msgShow("系统提示",rst.msg,"info");
							
							$('#list').datagrid('reload');
						}else{
							msgShow("系统提示",rst.msg,"info");
						}
					},
					error:function(){
						alert("系统错误，追回失败");
					}
				});
			}
		});
	}else{
		msgShow("系统提示","请单击列表行，选择一条记录","info");
	}
}