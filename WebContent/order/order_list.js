
var filterLength = undefined;
$(document).ready(function(){
	console.log('这是测试这是测试!!!');
	//初始化订单表
	initOrder();
	filterLength = $(".search .item").length;
	/*$(".payUl_tab li").on('click',function(){
		$(".payUl_tab li").removeClass("active");
		$(this).addClass("active");
		var index = $(this).index();
		$(".content").eq(index).addClass("show");
		$(".content").eq(index).siblings().removeClass("show");
	});*/
	
	/*$("#changePayMethod").change(function () {
		var checkRows = $("#orderTable").datagrid("getChecked");
		if(checkRows.length < 1){
			$.messager.alert("编辑提示", "请先选择一行");
			return;
		}
		 if ($(this).children('option:selected').val()== "2")  {
			 $.messager.alert("编辑提示", "确定选择票到付款吗？");
		 }
			
        if ($(this).children('option:selected').val()== "3") {
        	viewPayMethod();
        }
        	
    });*/
    
    $(":radio").on('click',function(){
    	var v = $(this).val();
    	if(v == '2'){
    		$("#goodsMethod").show();
    	}else{
    		$("#goodsMethod").hide();
    	}
    });
})

function initOrder(){
	var editIndex = '';
	$("#orderTable").datagrid({  
        striped: true,  // 是否隔行变色
        collapsible: true,  // 是否可折叠的 
        fit: true,  // datagrid 窗口自动大小 
        onClickCell: onClickCell,  
        onAfterEdit:onAfterEdit,  
        singleSelect: false,  // 是否单选 
        selectOnCheck: true,  // 如果设置为true，单击复选框将始终选择行。如果为假，选择行不会选中复选框
        checkOnSelect: false,  // 如果是true，复选框被选中/未选中时，行选中状态与复选框一致
        pagination: true, // 设置分页
        pageList: [10, 20, 50],
        height:'400',
        url: "order/getAllOrders.do",  
        columns: [[  
				{field:'ck',checkbox:true,width:'20'},
				{field:'jobNumber',title:'工号',align:'center',width:'13%'},
				{field:'orderNo',title:'订单号',align:'center',width:'13%'},
				{field:'orderDate',title:'订单日期',align:'center',width:'8%'},
				{field:'documentName',title:'单据名称',align:'center',width:'7%'},
				{field:'payMethod',title:'付款方式',align:'center',width:'10%'},
				{field:'payFlag',title:'是否可修改付款方式',align:'center',width:'13%',hidden:true},
				//{field:'prepaidMoney',title:'预付金额',align:'center',width:'6%'},
				{field:'advanceTotalPayment',title:'预收回款',align:'center',width:'6%'},
				//{field:'arrivalMoney',title:'应收金额',align:'center',width:'6%'},
				{field:'arrivalTotalPayment',title:'应收回款',align:'center',width:'6%'},
				//{field:'warrantyGold',title:'质保金额',align:'center',width:'6%'},
				{field:'warGoldTotalPayment',title:'质保金回款',align:'center',width:'6%'},
				{field:'warrantyPeriod',title:'质保期限(年)',align:'center',width:'7%'},
				{field:'region',title:'地区',align:'center',width:'5%'},
				/*{field:'salesman',title:'业务员',align:'center',width:'5%',formatter:function(value){
					return '<input onfocus="checkGetfocus(this)" onblur="checkValue(this)" class="editInput" type="text" value="'+value+'">';
				}},*/
				{field:'salesman',title:'业务员',align:'center',width:'5%'},//,editor:'text'
				{field:'customerFullName',title:'客户全称',align:'center',width:'12%',formatter:showHiddenInfo},
				{field:'projectName',title:'工程名称',align:'center',width:'14%',formatter:showHiddenInfo},
				{field:'productName',title:'产品名称',align:'center',width:'8%'},
				{field:'productNo',title:'品号',align:'center',width:'8%'},
				{field:'contractNo',title:'合同编号',align:'center',width:'7%'},
				{field:'model',title:'规格',align:'center',width:'12%',formatter:showHiddenInfo},
				{field:'amount',title:'数量',align:'center',width:'4%'},
				{field:'unit',title:'单位',align:'center',width:'4%'},
				{field:'unitPrice',title:'单价(元)',align:'center',width:'5%'},
				{field:'totalPrice',title:'总价(元)',align:'center',width:'5%'},
				{field:'deliveryTotalPrice',title:'发货总金额',align:'center',width:'5%'},
				{field:'remainAmount',title:'剩余数量',align:'center',width:'5%'},
				//{field:'isDelivery',title:'是否可发货',align:'center',width:'5%',formatter:changeInfo},
				{field:'remainInvoicesNum',title:'剩余开票数量',align:'center',width:'5%'},
				{field:'billingTotalPrice',title:'开票总金额',align:'center',width:'5%'},
				{field:'paymentTotalPrice',title:'回款总金额',align:'center',width:'5%'},
				{field:'totalBalance',title:'订单总余额',align:'center',width:'5%'}
        ]],
       /* onDblClickCell:function(rowIndex, field, value){
			if(field == 'salesman'){
				if(editIndex != '' && editIndex != rowIndex){
					$("#orderTable").datagrid('cancelEdit',rowIndex);
				}
				editIndex = rowIndex;
				$("#orderTable").datagrid('beginEdit',rowIndex);
				var ed = $("#orderTable").datagrid(rowIndex,field);
			}
		},
		onClickCell:function(rowIndex, field, value){
			var ed = $("#orderTable").datagrid(rowIndex,field);
			if(!ed){
				$("#orderTable").datagrid('endEdit',editIndex);
				updateOrderInfo();
			}
		}*/
    });  
  
};

function updateOrderInfo(){
	
}

$(function() {
	
	//打开添加过滤弹框
	$("#addFilter").on('click',function(){
		$("#filterInfoModal").show();
		$(".dialog_content").show();
	})
	
	//过滤弹框--确认
	$("#confirmFilter").on('click',function(){
		$(".search>.item").eq(filterLength-1).nextAll('.item').remove();
		var filterHtml = '';
		$("input[name='chkItem']:checked").each(function(k,v){
			var inputId = $(this).val();
			var filterName = $(this).parent().text();
			filterHtml += '<div class="item"><label class="allFilterLabel">'+filterName+':</label><input id="'+inputId+'"></div>';
		
		});
		$(".search>.item").eq(filterLength-1).after(filterHtml);
		$("#filterInfoModal").hide();
		$(".dialog_content").hide();
	})
	
	//过滤弹框--关闭
	$(".closeFilterModal").on('click',function(){
		$("#filterInfoModal").hide();
		$(".dialog_content").hide();
	})
	//查询
	$("#searchBtn").on('click',function(){
		var orderNo = $("#orderNo").val();
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		var documentName = $("#documentName").val();
		var customerFullName = $("#customerFullName").val();
		var projectName = $("#projectName").val();
		var billCompanyName = $("#billCompanyName").val();
		var contractNo = $("#contractNo").val();
		var specialPriceApplication = $("#specialPriceApplication").val();
		var productCategory = $("#productCategory").val();
		var inquiryNo = $("#inquiryNo").val();
		
		var salesman = $("#salesman").val();
		var region = $("#region").val();
		var customerNum = $("#customerNum").val();
		var customerNo = $("#customerNo").val();
		var orderAttribute = $("#orderAttribute").val();
		var productName = $("#productName").val();
		var model = $("#model").val();
		var amount = $("#amount").val();
		var unit = $("#unit").val();
		var totalPrice = $("#totalPrice").val();
		var deliveryAddress1 = $("#deliveryAddress1").val();
		var deliveryAddress2 = $("#deliveryAddress2").val();
		var originalOrder = $("#originalOrder").val();
		var referProgramNo = $("#referProgramNo").val();
		var referOrderNo = $("#referOrderNo").val();
		
		if((startDate == '' && endDate != '') || (startDate != '' && endDate == '')){
			$.messager.alert("提示", "按时间段过滤，起始或结束时间必填！");
			return;
		}
		
		$("#orderTable").datagrid("load",{
			'orderNo': orderNo,
			'startDate': startDate,
			'endDate': endDate,
			'documentName': documentName,
			'customerFullName': customerFullName,
			'projectName': projectName,
			'billCompanyName': billCompanyName,
			'specialPriceApplication': specialPriceApplication,
			'productCategory': productCategory,
			'inquiryNo': inquiryNo,
			
			'salesman': salesman,
			'region': region,
			'customerNum': customerNum,
			'customerNo': customerNo,
			'orderAttribute': orderAttribute,
			'productName': productName,
			'model': model,
			'amount': amount,
			'unit': unit,
			'totalPrice': totalPrice,
			'deliveryAddress1': deliveryAddress1,
			'deliveryAddress2': deliveryAddress2,
			'originalOrder': originalOrder,
			'referProgramNo': referProgramNo,
			'referOrderNo': referOrderNo
		});
	});
	
	//重置
	$("#resetBtn").on('click',function(){
		$("#orderNo").val('');
		$("#startDate").val('');
		$("#endDate").val('');
		$("#documentName").val('');
		$("#customerFullName").val('');
		$("#projectName").val('');
		$("#billCompanyName").val('');
		$("#contractNo").val('');
		$("#specialPriceApplication").val('');
		$("#productCategory").val('');
		$("#inquiryNo").val('');
		$(".search input:gt(10)").parent().remove();
		$("input[name='chkItem']:checked").each(function(k,v){
			v.checked = false;
		});
	});
	
	//点击发货
	$("#startGoods").on('click',function(){
		
		// 清空表单数据
		$("#addGoodsForm").form("clear");
		
		var selections = $("#orderTable").datagrid("getChecked");
		var flag = true;
		var company = '';
		if(selections.length < 1) {
			$.messager.alert("提示", "请至少选择一行！");
			return;
		}else if(selections.length == 1){
			var curRow = selections[0];
			if(curRow.payMethod == null || curRow.payMethod == ''){
				$.messager.alert("提示", "改订单未设置付款方式，请重新选择");
				return;
			}
			if(curRow.remainAmount == 0){
				$.messager.alert("提示", "该订单已发货完毕，请重新选择");
				return;
			}
			
			$("#goodsModal").show();
			$(".dialog_content").show();
			$("#deliveryNum").val(curRow.remainAmount);
			
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
			if(flag){
				$("#goodsModal").show();
				$("#deliveryNum").parent().hide();
			}
		}
		
		/*// 清空表单数据
		$("#addGoodsForm").form("clear");
		$("#goodsModal").show();*/
		
		
	});
	
	//发货信息保存按钮事件
	$('#saveDeliveryBtn').on('click',function(){
		$(".dialog_content").hide();
		var selections = $("#orderTable").datagrid("getChecked");
		
		var total = '';
		var orderArray = [];
		if(selections.length == 1){
			var orderInfo = {};
			orderInfo.orderNo = selections[0].orderNo;
			orderInfo.deliveryDate = $("#deliveryDate").val();
			orderInfo.deliveryNum = $("#deliveryNum").val();
			orderInfo.deliveryMoney = $("#deliveryNum").val()*selections[0].unitPrice;
			orderInfo.arrivalDate = $("#arrivalDate").val();
			total = selections[0].remainAmount;
			orderInfo.remainAmount = total - orderInfo.deliveryNum;
			orderArray.push(orderInfo);
		}
		if(selections.length > 1){
			
			$.each(selections,function(index,item){
				var orderInfo = {};
			    orderInfo.orderNo = item.orderNo;
			    orderInfo.deliveryNum = item.remainAmount;
			    orderInfo.deliveryMoney = item.remainAmount*item.unitPrice;
			    orderInfo.deliveryDate = $("#deliveryDate").val();
			    orderInfo.arrivalDate = $("#arrivalDate").val();
			    orderInfo.remainAmount = 0;
				orderArray.push(orderInfo);
				
			})
		}
		/*var orderNo = $("#orderSpan").text();
		var delivDate = $("#deliveryDate").val();
		var deliveryNum = $("#deliveryNum").val();
		var arrivalDate = $("#arrivalDate").val();*/
		
		
		var selections = $("#orderTable").datagrid("getSelections");
		$.post(
			"order/addDeliveryInfo.do",
			/*{'orderNo':orderNo, 'delivDate':delivDate,
			'deliveryNum':deliveryNum, 'arrivalDate':arrivalDate
			},*/
			{'orderArray':JSON.stringify(orderArray)},
			function(data){
				if("SUCCESS" == data){
					$("#goodsModal").hide();
					$.messager.alert("编辑提示", "发货信息保存成功");
					$("#orderTable").datagrid("reload");
				}else{
					$.messager.alert("编辑提示", "发货信息保存失败");
				}
			}
		);
		
    });
	
	//发货信息取消按钮事件
	$('.cancelGoodsBtn').on('click',function(){
		$("#goodsModal").hide();
		$(".dialog_content").hide();
    });
	
	//点击付款方式
	$("#paymentMethodBtn").on('click',function(){
		$(".dialog_content").show();
		var selections = $("#orderTable").datagrid("getChecked");
		if(selections.length < 1) {
			$.messager.alert("温馨提示", "请至少选择一行！");
			return;
		} 
		
		var sign = true;
		$.each(selections,function(index,item){
			if(item.payFlag == '否'){
				$.messager.alert("温馨提示", "订单"+item.orderNo+"存在付款方式，且不可修改");
				sign = false;
			}
		})
		
		if(sign){
			// 清空表单数据
			$("#sdffsf").form("clear");
			$("#payMethodModal").show();
		}
		
		/*prepaymentRate = $("#prepaymentRate").val('');
	    prepaidTime = $("#prepaidTime").val('');
	    payMentDay = $("#payMentDay").val('');
	    arrivalAfterRate = $("#arrivalAfterRate").val('');
	    warrantyRate = $("#warrantyRate").val('');
	    warrantyPeriod = $("#warrantyPeriod").val('');*/
		
	});
	
	//保存回款方式
	$('#savePayMethodBtn').on('click',function(){
		$(".dialog_content").hide();
		var prepaymentRate = '';
		var prepaidTime = '';
		var payMentDay = '';
		var arrivalAfterRate = '';
		var warrantyRate = '';
		var warrantyPeriod = '';
		var payMethod = '';
		var checkRadio = $('input:radio[name="moneyAction"]:checked').val();
		if(checkRadio == '2'){
			 prepaymentRate = $("#prepaymentRate").val();
			 prepaidTime = $("#prepaidTime").val();
			 payMentDay = $("#payMentDay").val();
			 arrivalAfterRate = $("#arrivalAfterRate").val();
			 warrantyRate = $("#warrantyRate").val();
			 warrantyPeriod = $("#warrantyPeriod").val();
			 payMethod = '预付率'+prepaymentRate+'%，货到'+payMentDay+'天，付'+arrivalAfterRate+'%,质保金'+warrantyRate+'%,质保期限'+warrantyPeriod+'年';
			
		}else if(checkRadio == '1'){
			prepaymentRate = 100;
			payMethod = '预付率'+'100%';
		}else{
			$.messager.alert("编辑提示", "编辑付款方式异常");
			return;
		}
		
		var selections = $("#orderTable").datagrid("getChecked");
		var orderNos = [];
		$.each(selections,function(index,item){
			orderNos.push(item.orderNo);
		})
		$.post(
			"order/addPayMethodInfo.do",
			{'orderNos':orderNos.toString(), 'prepaymentRate':prepaymentRate,
			'prepaidTime':prepaidTime, 'payMentDay':payMentDay,
			'arrivalAfterRate':arrivalAfterRate, 'warrantyRate':warrantyRate,
			'warrantyPeriod':warrantyPeriod,'payMethod':payMethod
			},
			function(data){
				if("SUCCESS"==data){
					$("#payMethodModal").hide();
					$.messager.alert("编辑提示", "付款方式保存成功");
					$("#orderTable").datagrid("reload");
				}else{
					$.messager.alert("编辑提示", "付款方式保存失败");
				}
			}
		);
		
    });
	
	//付款方式取消按钮事件
	$('.cancelPayMethodBtn').on('click',function(){
		$(".dialog_content").hide();
		$("#payMethodModal").hide();
    });
	
	
	//点击开票按钮
	$("#startBilling").on('click',function(){
		
		// 清空表单数据
		$("#addGoodsForm").form("clear");
		
		var selections = $("#orderTable").datagrid("getChecked");
		var flag = true;
		var company = '';
		if(selections.length < 1) {
			$.messager.alert("提示", "请至少选择一行！");
			return;
		}else if(selections.length == 1){
			var curRow = selections[0];
			if(curRow.payMethod == null || curRow.payMethod == ''){
				$.messager.alert("提示", "改订单未设置付款方式，请重新选择");
				return;
			}
			if(curRow.remainInvoicesNum == 0){
				$.messager.alert("提示", "该订单已开票完毕，请重新选择");
				return;
			}
			
			$("#billingModal").show();
			$(".dialog_content").show();
			$("#billingNum").val(curRow.remainInvoicesNum);
			$("#billMoney").val(curRow.unitPrice*($("#billingNum").val()));
			
		}else{
			for(var i=0;i < selections.length;i++){
				if(selections[i].payMethod == null || selections[i].payMethod == ''){
					$.messager.alert("提示", "订单"+selections[i].orderNo+"未设置付款方式，请重新选择");
					flag = false;
					return;
				}
				if(selections[i].remainInvoicesNum == 0){
					$.messager.alert("提示", "订单"+selections[i].orderNo+"该订单已开票完毕，请重新选择");
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
			if(flag){
				$("#billingModal").show();
				$("#billingNum").parent().hide();
				$("#billMoney").parent().hide();
			}
		}
		$("#modalbillCompanyName").val(selections[0].customerFullName);
	});
	
	//保存开票信息
	$('#saveBillingInfoBtn').on('click',function(){
		
		$(".dialog_content").hide();
		$("#billingModal").hide();
		var selections = $("#orderTable").datagrid("getChecked");
		
		var total = '';
		var billArray = [];
		if(selections.length == 1){
			if($("#billingNum").val() > selections[0].remainInvoicesNum){
				$.messager.alert("提示", "开票数量大于剩余数量，请重新编辑");
				return;
			}
			var billInfo = {};
			billInfo.orderNo = selections[0].orderNo;
		
			billInfo.billingNum = $("#billingNum").val();
			billInfo.billDate = $("#billDate").val();
			billInfo.billCompanyName = $("#modalbillCompanyName").val();
			billInfo.invoiceType = $("#invoiceType").val();
			billInfo.invoiceNum = $("#invoiceNum").val();
			billInfo.billMoney = $("#billMoney").val();
			billInfo.deliveryMethod = $("#deliveryMethod").val();
			billInfo.address = $("#billingToAddress").val();
			billInfo.contacts = $("#contacts").val();
			billInfo.telephone = $("#telephone").val();
			billInfo.expressNo = $("#expressNo").val();
			billInfo.mailDate = $("#mailDate").val();
			total = selections[0].remainInvoicesNum;
			billInfo.remainInvoicesNum = total - $("#billingNum").val();
			billArray.push(billInfo);
		}
		if(selections.length > 1){
			
			$.each(selections,function(index,item){
				var billInfo = {};
				billInfo.orderNo = item.orderNo;
				billInfo.deliveryNum = item.remainAmount;
				billInfo.billDate = $("#billDate").val();
				billInfo.billCompanyName = $("#modalbillCompanyName").val();
				billInfo.invoiceType = $("#invoiceType").val();
				billInfo.invoiceNum = $("#invoiceNum").val();
				billInfo.billMoney = item.unitPrice*item.remainInvoicesNum;
				billInfo.deliveryMethod = $("#deliveryMethod").val();
				billInfo.address = $("#billingToAddress").val();
				billInfo.contacts = $("#contacts").val();
				billInfo.telephone = $("#telephone").val();
				billInfo.expressNo = $("#expressNo").val();
				billInfo.mailDate = $("#mailDate").val();
				billInfo.remainInvoicesNum = 0;
				billArray.push(billInfo);
				
			})
		}
		
		$.post(
				"order/addBillInfo.do",
				/*{'orderNo':orderNo, 'delivDate':delivDate,
				'deliveryNum':deliveryNum, 'arrivalDate':arrivalDate
				},*/
				{'billArray':JSON.stringify(billArray)},
				function(data){
					if("SUCCESS" == data){
						$("#billingModal").hide();
						$.messager.alert("编辑提示", "开票信息保存成功");
						$("#orderTable").datagrid("reload");
					}else{
						$.messager.alert("编辑提示", "开票信息保存失败");
					}
				}
			);
    });
	
	//关闭开票弹框
	$('.closeBillingModal').on('click',function(){
		$("#billingModal").hide();
		$(".dialog_content").hide();
    });
	
});


function viewPayMethod(){
	$("#payMethodModal").show();
}

//单元格信息太长格式化
function showHiddenInfo(val){
	var value = val || '';
	return '<p class="hiddenLongInfo" title="'+value+'">'+value+'</p>';
}

function changeInfo(val){
	if(val == 0){
		return '<p style="color:red" title="否">否</p>';
	}else if(val == 1){
		return '<p style="color:blue" title="否">是</p>';
	}else{
		return '';
	}
	
}

/* 验证规则 */
/* 新增帐号验证 */
function addValidate() {
	$("#deliveryNum").validatebox({
		required: true,
		// length[3, 12],  字符长度在 3 - 12之间
		// equalParamToServer  自定义的 Ajax 验证 param1: ajax 请求的地址，    param2：ajax请求时带的字段名称，字段对应的值是本文本框输入的值
		validType: ["length[3, 12]", "equalParamToServer['account/account_validate.do', 'account']"],
		missingMessage:  "发货数量为必填项"  // 当文本框为空的时候提示信息
	});
	
}

function checkGetfocus(obj){
	$(this).removeClass('editInput');
	$(this).addClass('focusInput');
	
}

function checkValue(obj){
	if(obj.value == ''){
		$.messager.alert("编辑提示", "信息不能为空");
		return;
	}
	/*$.post(
		"order/updateOrderInfo.do",
		{'orderNos':orderNos.toString(), 'prepaymentRate':prepaymentRate},
		function(data){
			if("SUCCESS"==data){
				$("#goodsModal").hide();
				$.messager.alert("编辑提示", "保存成功");
				$("#orderTable").datagrid("reload");
			}else{
				$.messager.alert("编辑提示", "保存失败");
			}
		}
	);*/
		
}

var editIndex = undefined;
var editCur = undefined;
var curField = undefined;
var oldAmount = undefined;
function endEditing(index,field) {//该方法用于关闭上一个焦点的editing状态  
    if (editIndex == undefined) {  
        return true;
    }  
    if ($('#orderTable').datagrid('validateRow', editIndex)) {  
        $('#orderTable').datagrid('endEdit', editIndex);  
        editIndex = undefined;  
        editCur = undefined;
        curField = undefined;
        return true;  
    } else {  
        return false;
    }  
}
//点击单元格事件：  
function onClickCell(index,field,value) {  
    if (endEditing(index,field)) { 
        if(field=="salesman"){
        	beginEdit(index,field);
        }    
        if(field=="projectName"){
        	beginEdit(index,field);
        } 
        if(field=="productNo"){
        	beginEdit(index,field);
        } 
        if(field=="unitPrice"){
        	beginEdit(index,field);
        }
        if(field=="amount"){
        	beginEdit(index,field);
        }
        editIndex = index;  
    }  
   
} 

//单元格失去焦点执行的方法  
function onAfterEdit(index, row, changes) {  
    var updated = $('#orderTable').datagrid('getChanges', 'updated');  
    editCur.editor = {};
    if (updated.length < 1) {  
        $('#orderTable').datagrid('unselectAll');  
        return;  
    } else {  
    	if(updated[0].salesman == ''){
    		$('#orderTable').datagrid('rejectChanges'); 
    		$.messager.alert('提醒', '业务员信息编辑不能为空！'); 
    	    return;  
    	}
    	if(updated[0].projectName == ''){
    		$('#orderTable').datagrid('rejectChanges'); 
    		$.messager.alert('提醒', '工程名信息编辑不能为空！'); 
    	    return;  
    	}
    	if(updated[0].productNo == ''){
    		$('#orderTable').datagrid('rejectChanges'); 
    		$.messager.alert('提醒', '品号信息编辑不能为空！'); 
    	    return;  
    	}
    	if(updated[0].unitPrice == ''){
    		$('#orderTable').datagrid('rejectChanges'); 
    		$.messager.alert('提醒', '单价信息编辑不能为空！'); 
    	    return;  
    	}
    	if(updated[0].amount == ''){
    		$('#orderTable').datagrid('rejectChanges'); 
    		$.messager.alert('提醒', '数量信息编辑不能为空！'); 
    	    return;  
    	}
        // 传值  
        editOrderNoData(index, row, changes);
    }  
}  

function editOrderNoData(index, row, changes){
	var orderNo = row.orderNo;//成绩id  
	if(orderNo == ""){  
		$.messager.alert('提醒', '该订单编辑异常！');  
		$("#orderTable").datagrid('reload');  
	    return;  
	}
	var params = [];
	var salesman = undefined;
	if(changes.salesman != undefined && changes.salesman != ''){
		salesman = changes.salesman;
	}else{
		salesman = row.salesman;
	}
	var projectName = undefined;
	if(changes.projectName != undefined && changes.projectName != ''){
		projectName = changes.projectName;
	}else{
		projectName = row.projectName;
	}
	var productNo = undefined;
	if(changes.productNo != undefined && changes.productNo != ''){
		productNo = changes.productNo;
	}else{
		productNo = row.productNo;
	}
	var unitPrice = undefined;
	if(changes.unitPrice != undefined && changes.unitPrice != ''){
		unitPrice = changes.unitPrice;
		if(unitPrice < 0){
			$.messager.alert('提醒', '该订单单价编辑不能是负数！');  
			$('#orderTable').datagrid('rejectChanges'); 
		    return; 
		}
	}else{
		unitPrice = row.unitPrice;
	}
	var amount = undefined;
	var newRemainAmount = undefined;
	if(changes.amount != undefined && changes.amount != ''){
		amount = changes.amount;
		if(amount < 0){
			$.messager.alert('提醒', '该订单数量编辑不能是负数！');  
			$('#orderTable').datagrid('rejectChanges'); 
		    return; 
		}
		newRemainAmount = amount - (oldAmount - row.remainAmount);
	}else{
		amount = row.amount;
		newRemainAmount = row.remainAmount;
	}
	
	$.post(
		"order/editOrderNoData.do",
		{'orderNo':orderNo, 'salesman':salesman,'projectName':projectName,'productNo':productNo,
			'unitPrice':unitPrice,'amount':amount,'newRemainAmount':newRemainAmount},
		function(data){
			if("SUCCESS"==data){
				$.messager.alert("编辑提示", "信息修改成功");
				$("#orderTable").datagrid("reload");
			}else{
				$.messager.alert("编辑提示", "信息修改失败");
			}
		}
	);
}

function beginEdit(index,field){
	 var allRows = $('#orderTable').datagrid('getRows');
	 var curRow = allRows[index];
	    if(curRow.remainAmount < 1){
	    	$.messager.alert('提醒', '该订单不能编辑！'); 
		    return;
	    }
	    oldAmount = curRow.amount;
	curField = field;
	editCur = $('#orderTable').datagrid('getColumnOption', field);
	editCur.editor = {type:'text'};
    $('#orderTable').datagrid('beginEdit', index);  
    var ed = $('#orderTable').datagrid('getEditor', {index:index,field:field}); 
    $(ed.target).focus();  
}

function getBillMoneyValue(){
	var row = $("#orderTable").datagrid("getSelected");
	var singlePrice = row.unitPrice;
	var number = $("#billingNum").val();
	var moneyValue = singlePrice*number;
	$("#billMoney").val(moneyValue);
	
}






