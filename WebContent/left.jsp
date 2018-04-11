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
<title>Home</title>
<link rel="stylesheet" type="text/css" href="resources/js/themes/default/easyui.css" />
<link href="resources/css/index.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.datagrid.js"></script>
<script type="text/javascript">
$(function(){
	$('li.group_menu > a').on('click', function(){
		$(this).parent().siblings().removeClass('active');
		$(this).parent().addClass('active');
	});
});
</script>
</head>
	<body>
		<div id="left">
			<ul class="menuhome">
				 <li class="active group_menu">
					<!-- <img src="resources/images/order.png"></img> -->
					<a href="order/order_list.jsp" target="mainframe" class="orderTag">订单管理</a>
				</li>
				<li class="group_menu">
					<!-- <img src="resources/images/goods.png"></img> -->
					<a href="delivery/delivery_list.jsp" target="mainframe" class="deliveryTag">发货管理</a>
				</li>
				<li class="group_menu">
				 <!-- <img src="resources/images/billing.png"></img> -->
					<a href="billing/billing_list.jsp" target="mainframe" class="billingTag">开票管理</a>
				</li>
				<li class="group_menu">
					<!-- <img src="resources/images/payment.png"></img> -->
					<a href="payment/payment_list.jsp" target="mainframe" class="payTag">回款管理</a>
				</li>
			</ul>
		</div>
    </body>
</html>