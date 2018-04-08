<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.datagrid.js"></script>
<script type="text/javascript" src="resources/js/easyui-lang-zh_CN.js"></script>
<title>Insert title here</title>

</head>
	<script type="text/javascript">
		$(function() {
			$("#loginOut").click(function(){
				//window.location.href="login.jsp";
				self.location="login.jsp";
				/* $.messager.alert("登录提示", "您确定要退出本次登录吗");
				$("#tishi").css("display","block"); */
				/* $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {
					if (r) {
						$.post("loginOut.do",{},function(result){
							window.location="login.jsp";
						})
					}
				}); */
			});
		});
	</script>
	<body>
		<div id="head" style="height:70px;">
			<div style="height:100%;width:12%;float:left;">
				<!-- <span style="padding-left:30px;">LOGO</span> -->
				<img src="resources/images/logo.jpg" style="margin-top: -41px;margin-left: -44px;"></img>
			</div>
			<div style="height:100%;width:88%;background-color:#98CDE3;float:left;padding-top:20px;">
				<span style="padding-left:20px;" id="loginAccount">${loginAccount.name}</span>
				<span style="padding-left:20px;" id="loginOut">[退出登录]</span>
			</div>
		</div>
    </body>
</html>