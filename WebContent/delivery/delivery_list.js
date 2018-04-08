$(document).ready(function(){
	initDelivery();
});

function initDelivery(){
	$("#deliveryTable").datagrid({  
        striped: true,  // 是否隔行变色
        collapsible: true,  // 是否可折叠的 
        fit: true,  // datagrid 窗口自动大小 
     //   singleSelect: true,  // 是否单选 
        selectOnCheck: true,  // 如果设置为true，单击复选框将始终选择行。如果为假，选择行不会选中复选框
        checkOnSelect: false,  // 如果是true，复选框被选中/未选中时，行选中状态与复选框一致
        pagination: true, // 设置分页
        pageList: [10, 20, 50],  
        height:'400',
        url: "delivery/getDeliveryInfo.do", 
        columns: [[  
				{field:'ck',checkbox:true,width:'20'},
			//	{field:'editorBill',title:'编辑开票',width:'8%',formatter:showBillingModal},
				{field:'orderNo',title:'订单号',align:'center',width:'13%',editor:'text'},
				{field:'deliveryDate',title:'发货日期',align:'center',width:'15%',editor:'datebox'},
				{field:'deliveryNum',title:'发货数量',align:'center',width:'10%',editor:'text'},
				{field:'deliveryMoney',title:'发货金额',align:'center',width:'10%',editor:'text'},
				{field:'arrivalDate',title:'到货日期',align:'center',width:'15%',editor:'datebox'},
				{field:'receiptDate',title:'签收单返回日期',align:'center',width:'15%',editor:'datebox'},
				{field:'returnInfo',title:'换货、样品退回',align:'center',width:'32%',formatter:showHiddenInfo,editor:'text'},
				{field:'unitPrice',title:'单价',align:'center',width:'5%',hidden:true},
				{field:'customerFullName',title:'客户全称',align:'center',width:'5%',hidden:false}
				/*{field:'billDate',title:'开票时间',align:'center',width:'15%'},
				{field:'billCompanyName',title:'开票单位',align:'center',width:'10%'},
				{field:'invoiceType',title:'开票类型',align:'center',width:'10%'},
				{field:'unitPrice',title:'单价',align:'center',width:'5%',hidden:false},
				{field:'customerFullName',title:'客户全称',align:'center',width:'5%',hidden:false},
				{field:'amount',title:'数量',align:'center',width:'5%'},
				{field:'unit',title:'单位',align:'center',width:'5%'},
				{field:'billMoney',title:'金额',align:'center',width:'10%'},
				{field:'invoiceNum',title:'发票号码',align:'center',width:'10%'},
				{field:'projectName',title:'工程名称',align:'center',width:'18%',formatter:showHiddenInfo},
				{field:'contractNo',title:'合同编号',align:'center',width:'8%'},
				{field:'deliveryMethod',title:'运送方式',align:'center',width:'6%'},
				{field:'address',title:'邮寄地址',align:'center',width:'15%'},
				{field:'contacts',title:'联系人',align:'center',width:'5%'},
				{field:'telephone',title:'电话',align:'center',width:'7%'},
				{field:'expressNo',title:'快递号',align:'center',width:'9%'},
				{field:'mailDate',title:'邮寄时间',align:'center',width:'12%'}*/
        ]],
/*	onClickRow: function(index, data) {
	//第一次单击选中,第二次单击取消选中
	$(this).datagrid('selectRow', index);


	}*/
	
       /* onClickRow: function (rowIndex, data) {
			 $(this).datagrid('unselectRow', rowIndex);
       
},*/
		/*onClickCell:function(rowIndex, field, value){
			if(field == 'editorBill'){
				$(this).datagrid('selectRow', rowIndex);
				clickBilling(rowIndex);
			}else{
				$(this).datagrid('unselectRow', rowIndex);
			}
		}*/
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
		$("#deliveryTable").datagrid("load",{
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
	
	$("#saveBillingInfoBtn").on('click',function(){
		var rows = $("#deliveryTable").datagrid("getChecked");
		$(".dialog_content").hide();
		var orderNo = rows[0].orderNo;
		var number = $("#number").val();
		var billMoney = $("#billMoney").val();
		var billDate = $("#billDate").val();
		var invoiceType = $("#invoiceType").val();
		var invoiceNum = $("#invoiceNum").val();
		var deliveryMethod = $("#deliveryMethod").val();
		var mailAddress = $("#mailAddress").val();
		var contacts = $("#contacts").val();
		var telephone = $("#telephone").val();
		var expressNo = $("#expressNo").val();
		var mailDate = $("#mailDate").val();
		var billCompanyName = $("#billCompanyName").val();
		
		$.post(
				"delivery/addBillingInfo.do",
				{"orderNo":orderNo,"number":number,"billMoney":billMoney,"billDate":billDate,"invoiceType":invoiceType,
					"invoiceNum":invoiceNum,"deliveryMethod":deliveryMethod,"mailAddress":mailAddress,"contacts":contacts,
					"telephone":telephone,"expressNo":expressNo,"mailDate":mailDate,"billCompanyName":billCompanyName},
				function(data){
					if("SUCCESS"==data){
						$("#viewBillingModal").hide();
						$("#deliveryTable").datagrid("reload");
						$.messager.alert("编辑提示", "保存成功");
					}else{
						$.messager.alert("编辑提示", "保存失败");
					}
				}
			);
		$("#addBillForm").form("clear");
		newOrderNo = undefined;
	});
	
	$(".closeBillingModal").on('click',function(){
		$(".dialog_content").hide();
		$("#viewBillingModal").hide();
		$("#addBillForm").form("clear");
	});
	
	//点击回款
	$("#paymentBtn").on('click',function(){
		//要判断是否是“款到发货方式”，回款金额是否小于剩余回款金额？？？？？？？？？？？？？
		var rows = $("#deliveryTable").datagrid("getChecked");
		if(rows.length<1){
			$.messager.alert("编辑提示", "请至少选择一行");
			return;
		}else if(rows.length==1){
			$("#paymentModal").show();
			$(".dialog_content").show();
		}else{
			var companyFlag=flase;
			for(var i=0;i<rows.length;i++){
				if(rows[i+1].customerFullName !=rows[i].customerFullName){
					companyFlag=true;
					break;
				}
			}
			if(companyFlag){
				$.messager.alert("编辑提示", "请选择同一客户下的发货明细");
				return;
			}
			$("#addPaymentForm .modal-item:first").hidden();
			$("#paymentModal").show();
			$(".dialog_content").show();
		}
		
	});
	
	$(".closePaymentModal").on('click',function(){
		$("#paymentModal").hide();
		$(".dialog_content").hide();
	})
	
	//回款保存按钮
	$("#savePaymentInfoBtn").on('click',function(){
		
		var selections = $("#deliveryTable").datagrid("getChecked");
		
		var paymentArray = [];
		if(selections.length==1){
			if($("#advancePayment").val()>selections[0].deliveryMoney){
				$.messager.alert("编辑提示", "预收回款不能大于本次发货金额");
				return;
			}
			if($("#advancePaymentDate").val()==null || $("#advancePaymentDate").val()==''){
				$.messager.alert("编辑提示", "预收回款日期不能为空");
				return;
			}
			var paymentInfo = {};
			paymentInfo.orderNo = selections[0].orderNo;
			paymentInfo.advancePaymentDate = $("#advancePaymentDate").val();
			paymentInfo.advancePayment = $("#advancePayment").val();
			paymentInfo.advancePaymentRate = $("#advancePayment").val()/selections[0].deliveryMoney;
			paymentArray.push(paymentInfo);
		}
		if(selections.length > 1){
			if($("#advancePaymentRate").val()>1){
				$.messager.alert("编辑提示", "预收回款比例不能大于100%");
				return;
			}
			if($("#advancePaymentDate").val()==null || $("#advancePaymentDate").val()==''){
				$.messager.alert("编辑提示", "预收回款日期不能为空");
				return;
			}
			$.each(selections,function(index,item){
				var paymentInfo = {};
				paymentInfo.orderNo = item.orderNo;
				paymentInfo.advancePaymentDate = $("#advancePaymentDate").val();
				paymentInfo.advancePaymentRate = $("#advancePaymentRate").val();
				paymentInfo.advancePayment = item.deliveryMoney*$("#advancePaymentRate").val();
				paymentArray.push(paymentInfo);
			})
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
		$(".dialog_content").hide();
	})
	
	$("#number").mouseleave(function(){
		var rows = $("#deliveryTable").datagrid("getChecked");
		var singlePrice = rows[0].unitPrice;
		var number = $("#number").val();
		if(number>rows[0].deliveryNum){
			$.messager.alert("编辑提示", "开票数量大于发货数量");
			$("#number").val(rows[0].deliveryNum);
			var moneyValue1 = singlePrice*rows[0].deliveryNum;
			$("#billMoney").val(moneyValue1);
			return;
		}
		var moneyValue = singlePrice*number;
		$("#billMoney").val(moneyValue);
		
	})
	
});

//单元格信息太长格式化
function showHiddenInfo(val){
	var value = val || '';
	return '<p class="hiddenLongInfo" title="'+value+'">'+value+'</p>';
}

function showBillingModal(){
	return '<a class="billingEditor" style="width:20px;height: 20px;background: url(\'../images/billing.png\') 0 0 no-repeat;" >编辑</a>';
}

var unitPrice='';
var newOrderNo = undefined;
function clickBilling(rowIndex){
	var selections = $("#deliveryTable").datagrid("getChecked");
	if(selections.length < 1) {
		$.messager.alert("提示", "请至少选择一行！");
		return;
	}else if(selections.length == 1){
		$(".dialog_content").show();
		
		var curRow = selections[0];
		$("#billCompanyName").val(curRow.customerFullName);
		$("#number").val(curRow.deliveryNum);
		$("#billMoney").val(curRow.deliveryNum*curRow.unitPrice);
		$("#viewBillingModal").show();
		/*if(curRow.payMethod == null || curRow.payMethod == ''){
			$.messager.alert("提示", "改订单未设置付款方式，请重新选择");
			return;
		}
		if(curRow.remainAmount == 0){
			$.messager.alert("提示", "该订单已发货完毕，请重新选择");
			return;
		}*/
		
		
		
	}else{
		for(var i=0;i < selections.length;i++){
			if(selections[i].payMethod == null || selections[i].payMethod == ''){
				$.messager.alert("提示", "订单"+selections[i].orderNo+"未设置付款方式，请重新选择");
				flag = false;
				return;
			}
			if(selections[i].remainAmount == 0){
				$.messager.alert("提示", "订单"+selections[i].orderNo+"已发货完毕，请重新选择");
				flag = false;
				return;
			}
			if(company == ''){
				company = selections[i].customerFullName;
			}else{
				if(company != selections[i].customerFullName){
					$.messager.alert("提示", "订单不属于同一个客户，请重新选择");
					flag = false;
					return;
				}
			}
			
			/*if(selections[i+1].customerFullName != selections[i].customerFullName){
				$.messager.alert("提示", "订单不属于同一个客户，请重新选择");
				flag = false;
				return;
			}*/
			
		}
	}
	/*$("#deliveryTable").datagrid("selectRow",rowIndex);
	var row = $("#deliveryTable").datagrid("getSelected");
	unitPrice = row.unitPrice;
	newOrderNo  = row.orderNo;
	$("#viewBillingModal").show();
	$("#viewBillingModal").css("position","absolute");
	$("#orderSpan").val(row.orderNo);
	$("#billCompanyName").val(row.customerFullName);*/
}

function getmoneyValue(){
	var rows = $("#deliveryTable").datagrid("getChecked");
	var singlePrice = rows[0].unitPrice;
	var number = $("#number").val();
	if(number>rows[0].deliveryNum){
		$.messager.alert("编辑提示", "开票数量大于发货数量");
		$("#number").val(rows[0].deliveryNum);
		var moneyValue1 = singlePrice*rows[0].deliveryNum;
		$("#billMoney").val(moneyValue1);
		return;
	}
	var moneyValue = singlePrice*number;
	$("#billMoney").val(moneyValue);
	
}


var editIndex = undefined;
//表格增加一行
function append(){
	if (endEditing()) {
        $('#deliveryTable').datagrid('appendRow', {  });
        editIndex = $('#deliveryTable').datagrid('getRows').length - 1;
        $('#deliveryTable').datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);
    }
}

function endEditing(){
	if (editIndex == undefined){
		return true;
	}
	if ($('#deliveryTable').datagrid('validateRow', editIndex)){
		$('#deliveryTable').datagrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}

//保存行信息
function saveDeliveryInfo(){
	if(editIndex){
		$('#deliveryTable').datagrid('endEdit', editIndex);
		var editRow = $("#deliveryTable").datagrid("getRows")[editIndex];
		if(editRow){
			var deliveryInfo = {};
			if($.trim(editRow.orderNo)){
				deliveryInfo.orderNo = $.trim(editRow.orderNo);
			}else{
				$.messager.alert("编辑提示", "订单编号不能为空");
				$('#deliveryTable').datagrid('beginEdit', editIndex);
				return;
			}
			if(editRow.deliveryDate == null || $.trim(editRow.deliveryDate) == ''){
				$.messager.alert("编辑提示", "发货日期不能为空");
				$('#deliveryTable').datagrid('beginEdit', editIndex);
				return;
			}else if(dateCheck(editRow.deliveryDate)){
				deliveryInfo.deliveryDate = $.trim(editRow.deliveryDate);
			}else{
				$.messager.alert("编辑提示", "发货日期格式不规范");
				$('#deliveryTable').datagrid('beginEdit', editIndex);
				return;
			}
			if($.trim(editRow.deliveryNum)){
				deliveryInfo.deliveryNum = $.trim(editRow.deliveryNum);
			}else{
				$.messager.alert("编辑提示", "发货数量不能为空");
				$('#deliveryTable').datagrid('beginEdit', editIndex);
				return;
			}
			if(editRow.arrivalDate == null || $.trim(editRow.arrivalDate) == ''){
				$.messager.alert("编辑提示", "到货日期不能为空");
				$('#deliveryTable').datagrid('beginEdit', editIndex);
				return;
			}else if(dateCheck(editRow.arrivalDate)){
				deliveryInfo.arrivalDate = $.trim(editRow.arrivalDate);
			}else{
				$.messager.alert("编辑提示", "到货日期格式不规范");
				$('#deliveryTable').datagrid('beginEdit', editIndex);
				return;
			}
			if($.trim(editRow.receiptDate) == "" || dateCheck(editRow.receiptDate)){
				deliveryInfo.receiptDate = $.trim(editRow.receiptDate);
			}else{
				$.messager.alert("编辑提示", "签收单返回日期格式不规范");
				$('#deliveryTable').datagrid('beginEdit', editIndex);
				return;
			}
			
			deliveryInfo.returnInfo = editRow.returnInfo;
			$.post(
					"delivery/addDeliveryInfo.do",
					{'deliveryInfo':JSON.stringify(deliveryInfo)},
					function(data){
						if("SUCCESS" == data){
							$("#paymentModal").hide();
							$.messager.alert("编辑提示", "发货信息保存成功");
						}else{
							$.messager.alert("编辑提示", "发货信息保存失败");
						}
					}
				);
		}
	}
		
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