<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + 
					  request.getServerPort() + path + "/";
%>

<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/js/themes/default/easyui.css" />
<link href="resources/dist/remodal-default-theme.css" type="text/css" rel="stylesheet">
<link href="resources/css/index.css" type="text/css" rel="stylesheet">
<link href="resources/css/delivery_list.css" type="text/css" rel="stylesheet">
<link rel="shortcut icon" href="#"/>
<script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.datagrid.js"></script>
<script type="text/javascript" src="resources/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/resources/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="delivery/delivery_list.js"></script>
</head>
<body >

	<div id="right" style="height:100%;width:100%;background-color:#F8F4EB;float:right;">
		<div style="margin:20px;height:92%;background:#F4F4F4;">
			<div class="search">
				<div class="item">
					<label for="orderNo">订单号：</label>
					<input id="orderNo" name="orderNo" type="text">
				</div>
				<div class="item">
					<label for="startDate">时间：</label>
					<input id="startDate" type="text" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'})">
					<label for="endDate">-</label>
					<input id="endDate" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})">
				</div>
				<div class="item">
					<label for="salesman">业务员：</label>
					<input id="salesman" name="salesman" type="text">
				</div>
			<!-- 	<div class="item">
					<label for="projectName">工程名称：</label>
					<input id="projectName" name="projectName" type="text">
				</div> -->
				<div class="item">
					<label for="region">地区：</label>
					<input id="region" name="region" type="text">
				</div>
				<div style="float:right;">
					<button type="button" id="searchBtn" class="setBtn">查询</button>
					<button type="button" id="resetBtn" class="setBtn">重置</button>
				</div>
			</div>
			
			<div style="margin-top:20px;">
				<button id="paymentBtn" class="bestBtn" style="margin-left:20px;">回款</button>
				<button id="addDeliveryBtn" class="bestBtn" onclick="append()">添加</button> 
				<button id="addDeliveryBtn" class="bestBtn" onclick="saveDeliveryInfo()">保存</button> 
				<button id="hhhhhkhkh" class="bestBtn" style="margin-right:20px;float:right;" onclick="clickBilling()">开票</button>
			</div>
			<div style="height:70%;margin:10px 20px 20px;background:white;border-radius:10px;">
				<table id="deliveryTable"></table>
			</div>
		</div>
	</div>
	
	<!-- 开票弹框 -->
	<div class="remodal fade" id="viewBillingModal" role="dialog">
   		<div class="modalContent">
	        <div class="modalHeader">
	            <label>开票信息</label>
			    <button data-remodal-action="close" class="remodal-close closeBillingModal" aria-label="Close"></button>
            </div>
           
            <div class="modalBody">
            	<form id="addBillForm" method="post" style="margin-left:8px;">
					<!-- <div class="modal-item">
						<label style="letter-spacing:0.35em;">订单号：</label><input id="orderSpan"  style="width:130px;" class="hiddenLongInfo" readonly></input>
					</div> -->
					<div class="modal-item">
						<label class="two_word">数量</label><label class="label_size">：</label>
						<input id="number" type="text" 
   						 ><!-- onchange="getmoneyValue()"  onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" -->
					</div>
					<div class="modal-item">
						<label>开票日期：</label>
						<input id="billDate" type="text" onFocus="WdatePicker({lang:'zh-cn'})">
					</div>
					<div class="modal-item">
						<label class="two_word">金额</label><label class="label_size">：</label>
						<input id="billMoney" type="text" readonly>
					</div>
					<div class="modal-item">
						<label>开票单位：</label>
						<input id="billCompanyName" type="text">
					</div>
					<!-- onFocus="WdatePicker({minDate:new Date()})"
					onFocus="WdatePicker({minDate:'#F{$dp.$D(\'deliveryDate\')}'})" -->
					<div class="modal-item">
						<label>开票类型：</label>
						<input id="invoiceType" type="text">
					</div>
					<div class="modal-item">
						<label>发票号码：</label>
						<input id="invoiceNum" type="text">
					</div>
					<div class="modal-item">
						<label>运送方式：</label>
						<input id="deliveryMethod" type="text">
					</div>
					<div class="modal-item">
						<label>邮寄地址：</label>
						<input id="mailAddress" type="text">
					</div>
					<div class="modal-item">
						<label class="three_word">联系人</label><label class="label_size_three">：</label>
						<input id="contacts" type="text">
					</div>
					<div class="modal-item">
						<label class="two_word">电话</label><label class="label_size">：</label>
						<input id="telephone" type="text">
					</div>
					<div class="modal-item">
						<label class="three_word">快递号</label><label class="label_size_three">：</label>
						<input id="expressNo" type="text">
					</div>
					<div class="modal-item">
						<label>邮寄时间：</label>
						<input id="mailDate" type="text" onFocus="WdatePicker({lang:'zh-cn'})">
					</div>
				</form>
            </div>
            <div class="commonBtn">
	            <button data-remodal-action="confirm" class="remodal-confirm" id="saveBillingInfoBtn">保存</button>
				<button data-remodal-action="cancel" class="remodal-cancel closeBillingModal">取消</button>
            </div>
        </div>
	</div>
	
	<div class="remodal fade" id="paymentModal" role="dialog">
   		<div class="modalContent">
	        <div class="modalHeader">
	            <label>回款信息</label>
			    <button data-remodal-action="close" class="remodal-close closePaymentModal" aria-label="Close"></button>
            </div>
           
            <div class="modalBody">
            	<form id="addPaymentForm" method="post" style="margin-left:8px;">
					<div class="modal-item">
						<label>预收回款：</label>
						<input id="advancePayment" type="text">
					</div>
					<div class="modal-item">
						<label>预收回款日期：</label>
						<input id="advancePaymentDate" type="text" onFocus="WdatePicker({lang:'zh-cn'})">
					</div>
					<div class="modal-item">
						<label >回款比例：</label>
						<input id="advancePaymentRate" type="text">
					</div>
				</form>
            </div>
            <div class="commonBtn">
	            <button data-remodal-action="confirm" class="remodal-confirm" id="savePaymentInfoBtn">保存</button>
				<button data-remodal-action="cancel" class="remodal-cancel closePaymentModal">取消</button>
            </div>
        </div>
	</div>
	<!-- 遮罩层 -->
	<div class="dialog_content"></div>
</body>
</html>