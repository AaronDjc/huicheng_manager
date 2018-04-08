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
<link href="resources/css/index.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.datagrid.js"></script>
<script type="text/javascript" src="resources/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/resources/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="payment/payment_list.js"></script>
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
				<button class="bestBtn" style="margin-left:20px;" onclick="appendPaymentInfo()">添加</button>
				<button id="addDeliveryBtn" class="bestBtn" onclick="savePaymentInfo()">保存</button>
			</div>
			<div style="height:70%;margin:10px 20px 20px;background:white;border-radius:10px;">
				<table id="paymentTable"></table>
			</div>
		</div>
	</div>
</body>
</html>