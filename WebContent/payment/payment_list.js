$(document).ready(function(){
	initPayment();
});

function initPayment(){
	$("#paymentTable").datagrid({  
        striped: true,  // 是否隔行变色
        collapsible: true,  // 是否可折叠的 
        fit: true,  // datagrid 窗口自动大小 
        singleSelect: false,  // 是否单选 
        selectOnCheck: true,  // 如果设置为true，单击复选框将始终选择行。如果为假，选择行不会选中复选框
        checkOnSelect: true,  // 如果是true，复选框被选中/未选中时，行选中状态与复选框一致
        pagination: true, // 设置分页
        pageList: [10, 20, 50],  
        height:'400',
        url: "payment/getPaymentInfo.do", 
        columns: [[  
				{field:'ck',checkbox:true,width:'20'},
				{field:'orderNo',title:'订单号',align:'center',width:'13%',editor:'text'},
				{field:'advancePaymentDate',title:'预收回款日期',align:'center',width:'15%',editor:'datebox'},
				{field:'advancePayment',title:'预收回款',align:'center',width:'5%',editor:'text'},
				{field:'advancePaymentRate',title:'预收回款比例',align:'center',width:'5%',editor:'text'},
				{field:'arrivalPaymentDate',title:'应收回款日期',align:'center',width:'15%',editor:'datebox'},
				{field:'arrivalPayment',title:'应收回款',align:'center',width:'5%',editor:'text'},
				{field:'arrivalPaymentRate',title:'应收回款比例',align:'center',width:'5%',editor:'text'},
				{field:'warGoldPaymentDate',title:'质保金回款日期',align:'center',width:'15%',editor:'datebox'},
				{field:'warGoldPayment',title:'质保金回款',align:'center',width:'5%',editor:'text'},
				{field:'warGoldPaymentRate',title:'质保金回款比例',align:'center',width:'5%',editor:'text'}
			/*	{field:'balance',title:'余额',align:'center',width:'5%'},
				{field:'payMethod',title:'付款方式',align:'center',width:'10%'},
				{field:'payCondition',title:'付款条件',align:'center',width:'10%'},
				{field:'prepaidDate',title:'预付日期',align:'center',width:'15%',formatter:showHiddenInfo},
				{field:'prepaidMoney',title:'预付金额',align:'center',width:'8%'},
				{field:'lastTime',title:'截至时间',align:'center',width:'6%'},
				{field:'arrivalMoney',title:'到货金额',align:'center',width:'15%'},
				{field:'warranty',title:'质保金额',align:'center',width:'5%'},
				{field:'warrantyPeriod',title:'质保期限',align:'center',width:'7%'},
				{field:'balanceProofread',title:'余额校对',align:'center',width:'9%'},
				{field:'proofResult',title:'准确控制（校对结果）',align:'center',width:'12%'}*/
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
		$("#paymentTable").datagrid("load",{
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
	
});

//单元格信息太长格式化
function showHiddenInfo(val){
	var value = val || '';
	return '<p class="hiddenLongInfo" title="'+value+'">'+value+'</p>';
}

var editIndex = undefined;
//表格增加一行
function appendPaymentInfo(){
	if (endEditing()) {
    $('#paymentTable').datagrid('appendRow', {  });
    editIndex = $('#paymentTable').datagrid('getRows').length - 1;
    $('#paymentTable').datagrid('beginEdit', editIndex);
}
}

function endEditing(){
	if (editIndex == undefined){
		return true;
	}
	if ($('#paymentTable').datagrid('validateRow', editIndex)){
		$('#paymentTable').datagrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}

//保存行信息
function savePaymentInfo(){
	if(editIndex && editIndex != undefined){
		$('#paymentTable').datagrid('endEdit', editIndex);
		var editRow = $("#paymentTable").datagrid("getRows")[editIndex];
		if(editRow){
			var paymentInfo = {};
			if($.trim(editRow.orderNo)){
				paymentInfo.orderNo = $.trim(editRow.orderNo);
			}else{
				paymentInfo.orderNo='';
			}
			if(editRow.paymentDate == null || $.trim(editRow.paymentDate) == ''){
				$.messager.alert("编辑提示", "回款时间不能为空");
				$('#paymentTable').datagrid('beginEdit', editIndex);
				return;
			}else if(dateCheck(editRow.paymentDate)){
				paymentInfo.paymentDate = $.trim(editRow.paymentDate);
			}else{
				$.messager.alert("编辑提示", "回款时间格式不规范");
				$('#paymentTable').datagrid('beginEdit', editIndex);
				return;
			}
			if($.trim(editRow.paymentMoney)){
				paymentInfo.paymentMoney = $.trim(editRow.paymentMoney);
			}else{
				$.messager.alert("编辑提示", "回款金额不能为空");
				$('#paymentTable').datagrid('beginEdit', editIndex);
				return;
			}
			if($.trim(editRow.paymentRate)){
				paymentInfo.paymentRate = $.trim(editRow.paymentRate);
			}else{
				$.messager.alert("编辑提示", "回款率不能为空");
				$('#paymentTable').datagrid('beginEdit', editIndex);
				return;
			}
			$.post(
					"payment/addPaymentInfo.do",
					{'paymentInfo':JSON.stringify(paymentInfo)},
					function(data){
						if("SUCCESS" == data){
							$("#paymentModal").hide();
							$.messager.alert("编辑提示", "回款信息保存成功");
							$('#paymentTable').datagrid("reload",{});
							
						}else{
							$.messager.alert("编辑提示", "回款信息保存失败");
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