$(document).ready(function(){
	initBilling();
});

function initBilling(){
	$("#billingTable").datagrid({  
        striped: true,  // 是否隔行变色
        collapsible: true,  // 是否可折叠的 
        fit: true,  // datagrid 窗口自动大小 
        singleSelect: false,  // 是否单选 
        selectOnCheck: true,  // 如果设置为true，单击复选框将始终选择行。如果为假，选择行不会选中复选框
        checkOnSelect: true,  // 如果是true，复选框被选中/未选中时，行选中状态与复选框一致
        pagination: true, // 设置分页
        pageList: [10, 20, 50],  
        height:'400',
        url: "billing/getBillingInfo.do", 
        columns: [[  
				{field:'ck',checkbox:true,width:'20'},
				{field:'orderNo',title:'订单号',align:'center',width:'13%',editor:'text'},
				{field:'billDate',title:'开票时间',align:'center',width:'15%',editor:'datebox'},
				{field:'billCompanyName',title:'开票单位',align:'center',width:'10%',editor:'text'},
				{field:'invoiceType',title:'开票类型',align:'center',width:'5%',editor:'text'},
				{field:'amount',title:'数量',align:'center',width:'5%',editor:'text'},
				{field:'unit',title:'单位',align:'center',width:'5%',editor:'text'},
				{field:'billMoney',title:'金额',align:'center',width:'10%',editor:'text'},
				{field:'invoiceNum',title:'发票号码',align:'center',width:'10%',editor:'text'},
				{field:'projectName',title:'工程名称',align:'center',width:'18%',formatter:showHiddenInfo,editor:'text'},
				{field:'contractNo',title:'合同编号',align:'center',width:'8%',editor:'text'},
				{field:'deliveryMethod',title:'运送方式',align:'center',width:'6%',editor:'text'},
				{field:'address',title:'邮寄地址',align:'center',width:'15%',editor:'text'},
				{field:'contacts',title:'联系人',align:'center',width:'5%',editor:'text'},
				{field:'telephone',title:'电话',align:'center',width:'7%',editor:'text'},
				{field:'expressNo',title:'快递号',align:'center',width:'9%',editor:'text'},
				{field:'mailDate',title:'邮寄时间',align:'center',width:'12%',editor:'datebox'}
        ]]
    });  
}

$(function() {
	//查询
	$("#searchBtn").on('click',function(){
		var orderNo = $("#orderNo").val();
		var salesman = $("#salesman").val();
		var region = $("#region").val();
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		$("#billingTable").datagrid("load",{
			'orderNo': orderNo,
			'salesman': salesman,
			'region': region,
			'startDate': startDate,
			'endDate': endDate
		});
	});
	
	//重置
	$("#resetBtn").on('click',function(){
		$("#orderNo").val('');
		$("#salesman").val('');
		$("#region").val('');
	});
	
	
	$("#paymentForBtn").on('click',function(){
		var row = $("#billingTable").datagrid("getChecked");
		if(row.length<1){
			$.messager.alert("编辑提示", "请至少选择一行");
			return;
		}
		$("#paymentModal").show();
		$(".dialog_content").show();
	})
	
	$(".closePaymentModal").on('click',function(){
		$("#paymentModal").hide();
		$(".dialog_content").hide();
		$("#paymentDate").val('');
		$("#paymentMoney").val('');
		$("#paymentRate").val('');
	})
	
	$("#savePaymentInfoBtn").on('click',function(){
		$(".dialog_content").hide();
		var selections = $("#billingTable").datagrid("getChecked");
		
		var paymentArray = [];
		if(selections.length > 0){
			var flag = undefined;
			$.each(selections,function(index,item){
				var paymentInfo = {};
				paymentInfo.orderNo = selections[0].orderNo;
				if($.trim($("#paymentDate").val())){
					paymentInfo.paymentDate = $("#paymentDate").val();
				}else{
					$.messager.alert("编辑提示", "回款时间不能为空");
					flag=1;
				}
				if($.trim($("#paymentMoney").val())){
					paymentInfo.paymentMoney = $("#paymentMoney").val();
				}else{
					$.messager.alert("编辑提示", "回款金额不能为空");
					return;
				}
				if($.trim($("#paymentRate").val())){
					paymentInfo.paymentRate = $("#paymentRate").val();
				}else{
					$.messager.alert("编辑提示", "回款率不能为空");
					return;
				}
				paymentArray.push(paymentInfo);
			})
			if(flag == 1){
				
			}
		}
		
		$.post(
			"delivery/addPaymentInfo.do",
			{'paymentArray':JSON.stringify(paymentArray)},
			function(data){
				if("SUCCESS" == data){
					$("#paymentModal").hide();
					$.messager.alert("编辑提示", "回款信息保存成功");
				}else{
					$.messager.alert("编辑提示", "回款信息保存失败");
				}
			}
		);
	})
	
});

//单元格信息太长格式化
function showHiddenInfo(val){
	var value = val || '';
	return '<p class="hiddenLongInfo" title="'+value+'">'+value+'</p>';
}

var editIndex = undefined;
//表格增加一行
function appendBillInfo(){
	if (endEditing()) {
      $('#billingTable').datagrid('appendRow', {  });
      editIndex = $('#billingTable').datagrid('getRows').length - 1;
      $('#billingTable').datagrid('beginEdit', editIndex);
  }
}

function endEditing(){
	if (editIndex == undefined){
		return true;
	}
	if ($('#billingTable').datagrid('validateRow', editIndex)){
		$('#billingTable').datagrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}

//保存行信息
function saveBillInfo(){
	if(editIndex && editIndex != undefined){
		$('#billingTable').datagrid('endEdit', editIndex);
		var editRow = $("#billingTable").datagrid("getRows")[editIndex];
		if(editRow){
			var billInfo = {};
			if($.trim(editRow.orderNo)){
				billInfo.orderNo = $.trim(editRow.orderNo);
			}else{
				billInfo.orderNo='';
			}
			if(editRow.billDate == null || $.trim(editRow.billDate) == ''){
				$.messager.alert("编辑提示", "开票时间不能为空");
				$('#billingTable').datagrid('beginEdit', editIndex);
				return;
			}else if(dateCheck(editRow.billDate)){
				billInfo.billDate = $.trim(editRow.billDate);
			}else{
				$.messager.alert("编辑提示", "开票时间格式不规范");
				$('#billingTable').datagrid('beginEdit', editIndex);
				return;
			}
			if($.trim(editRow.billCompanyName)){
				billInfo.billCompanyName = $.trim(editRow.billCompanyName);
			}else{
				$.messager.alert("编辑提示", "开票单位不能为空");
				$('#billingTable').datagrid('beginEdit', editIndex);
				return;
			}
			if($.trim(editRow.invoiceType)){
				billInfo.invoiceType = $.trim(editRow.invoiceType);
			}else{
				$.messager.alert("编辑提示", "开票类型不能为空");
				$('#billingTable').datagrid('beginEdit', editIndex);
				return;
			}
			if($.trim(editRow.amount)){
				billInfo.amount = $.trim(editRow.amount);
			}else{
				$.messager.alert("编辑提示", "数量不能为空");
				$('#billingTable').datagrid('beginEdit', editIndex);
				return;
			}
			if($.trim(editRow.unit)){
				billInfo.unit = $.trim(editRow.unit);
			}else{
				$.messager.alert("编辑提示", "单位不能为空");
				$('#billingTable').datagrid('beginEdit', editIndex);
				return;
			}
			if($.trim(editRow.billMoney)){
				billInfo.billMoney = $.trim(editRow.billMoney);
			}else{
				$.messager.alert("编辑提示", "金额不能为空");
				$('#billingTable').datagrid('beginEdit', editIndex);
				return;
			}
			if($.trim(editRow.invoiceNum)){
				billInfo.invoiceNum = $.trim(editRow.invoiceNum);
			}else{
				$.messager.alert("编辑提示", "发票号码不能为空");
				$('#billingTable').datagrid('beginEdit', editIndex);
				return;
			}
			billInfo.deliveryMethod=$.trim(editRow.deliveryMethod);
			billInfo.address=$.trim(editRow.address);
			billInfo.contacts=$.trim(editRow.contacts);
			billInfo.telephone=$.trim(editRow.telephone);
			billInfo.expressNo=$.trim(editRow.expressNo);
			billInfo.mailDate=$.trim(editRow.mailDate);
			$.post(
					"billing/addBillingInfo.do",
					{'billInfo':JSON.stringify(billInfo)},
					function(data){
						if("SUCCESS" == data){
							$("#paymentModal").hide();
							$.messager.alert("编辑提示", "开票信息保存成功");
						}else{
							$.messager.alert("编辑提示", "开票信息保存失败");
						}
					}
				);
		}
	}
	editIndex = undefined;
}

//日期验证
function dateCheck(dateValue) {
  var result = dateValue.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
  if (result == null){
  	return false;
  }
  var d = new Date(result[1], result[3] - 1, result[4]);
  return (d.getFullYear() == result[1] && (d.getMonth() + 1) == result[3] && d.getDate() == result[4]);

}