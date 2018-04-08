<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head id="Head1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>惠程管理系统</title>
    <link href="resources/css/default.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="resources/js/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="resources/js/themes/icon.css" />
    
    <link rel="stylesheet" type="text/css" href="resources/js/themes/default/easyui.css" />
<link href="resources/dist/remodal-default-theme.css" type="text/css" rel="stylesheet">
<link href="resources/css/index.css" type="text/css" rel="stylesheet">
<link href="<%=basePath%>/resources/css/order_list.css" type="text/css" rel="stylesheet">
<link href="resources/css/style_log.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.datagrid.js"></script>
<script type="text/javascript" src="resources/js/easyui-lang-zh_CN.js"></script>
</head>
	<script type="text/javascript">
		$(function() {
			$("#btnLogin").click(function() {
				$.post("loginOn.do",{
					account:$("#account").val(),
					password: $("#password").val()
					
				},function(result){
					if(result.code==1){
						window.location=result.href;
						var account = result.loginAccount;
					}
					if(result.code==0){
						$.messager.show({
							title:"错误提示",
							msg:result.msg
						})
					}
				});
			});
		});
	</script>
<body>
	<!--登录窗口 background: url(resources/images/login.jpg) no-repeat;-->
	<div class="login_m" style="background: url(resources/images/parallax-4.jpg) no-repeat;">
		<div class="login_boder">
			<div class="login_padding" id="login_model">
			  <h2>用户名</h2>
			  <label>
			    <input type="text" id="account" class="txt_input txt_input2">
			  </label>
			  <h2>密码</h2>
			  <label>
			    <input type="password" name="textfield2" id="password" class="txt_input">
			  </label>
			  <button id="btnLogin" style="width:295px; height:36px; border:1px solid #cad2db; 
			  padding:0 5px; -moz-border-radius:5px; 
			  -webkit-border-radius:5px; 
			  background-color: #009fde;
			  border-radius:5px; margin-bottom:10px; font-size:14px; color:white; font-family:Arial;">登录</button>
			</div>
		</div>
	</div>
</body>
</html>
