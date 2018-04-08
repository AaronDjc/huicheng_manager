<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  	<title>欢迎来到书店!</title>
  	
  	
  	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <script type="text/javascript" src="easyui/jquery.min.js"></script>
     <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
  	
  	
  	<style>
  		#content td{
  			border:1px solid gray;
  			border-bottom:0px;
  		}
  		.type{
  			margin-top:10px;
  			font-size:10pt;
  		}
  		.table{
  			border:1px solid gray;
  			border-bottom:0px;
  			width:1020px;
  			height:200%;
  			border-collapse:collapse;
  		}
  		.menu{
  			background:#CECEFF;
  			margin:5px;
  			width:100%;
  			height:20px;
  			vertical-align:middle;
  			text-align: center;
  		}
  		.on{
  			background:#AAAAFF;
  			margin:5px;
  			width:100%;
  			height:20px;
  			vertical-align:middle;
  			text-align: center;
  		}
  		.menu a{
  			text-decoration:none;
  		}
  		.btn{
  			border:0px;
  			background:transparent;
  			width:100px;
  		}
  		.txt{
  			border:1px solid blue;
  		}
  		td a{
  			font-size:10pt;
  		}
  	</style>
 </head>
  <body style="text-align:center;margin-bottom:0px; margin-top:0px">
     <table class="table">
     	<tr style="height:60px;background:#fffff">
     		<td colspan="2">
     			<table style="border:0px;width:100%;height:80px;">
     				<tr>
     					<td width="60%" align="center">
						<img src="../../imgs/logo.gif"/>
     						欢迎来到主页!
     					</td>
     					<td align="right" style="padding-right:10px;font-size:10pt;font:bold;">
     						<c:choose>
     							<c:when test="${empty sessionScope.user}">
     								<a target="blank" href="jsp/reg.jsp">注册</a>
     								<a target="blank" href="${pageContext.request.contextPath }/jsp/login.jsp">登录</a>
     							</c:when>
     							<c:otherwise>
     								欢迎你：
     								<a href="/jsp/showInfor.jsp">${user.userName}</a>
     								|
     								<a target="_content" 
     								href="${pageContext.request.contextPath }/OrderServlet?action=queryOrders&phoneNum=${user.phoneNum}">
     								订单管理</a>
     								|
     								<a target="_content" href="${pageContext.request.contextPath }/CarServlet?action=queryAllCars">查看购物车</a>
     								<a href="${pageContext.request.contextPath }/logout">|退出登录</a>
     							</c:otherwise>
     						</c:choose>
     					</td>
     				</tr>
     			</table>
     		</td>
     	</tr>
     	<tr id="content">
     		<td style="width:120px;height:150px;vertical-align:top;padding:10px;">
     			<div >
     				<a target="_content" href="${pageContext.request.contextPath }/IndexServlet?action=QueryAllBooks" 
     				class="easyui-linkbutton" 
     				iconCls="icon-search"
     				data-options="width:120"
     				>全部</a>
     			</div>
     			<c:forEach items="${types}" var="type">
     				<div>
     				 <a target="_content" 
     				 href="${pageContext.request.contextPath }/IndexServlet?action=QueryBooksByType&typeId=${type.tId}" 
     				 class="easyui-linkbutton" iconCls="icon-search" data-options="width:120">
     				 ${type.tName }</a>
     				</div>
				</c:forEach>
     		</td>
     		<td>
     			<table style="width:100%;">
     			<tr>
	     				<td style="text-align:center;background:#E0E0E0;height:50px;" colspan="2">
		     				 <form method="get" target="_content" action="${pageContext.request.contextPath}/IndexServlet">
		     					<select name="condition" class="txt">
		     						<option value="name">书名</option>
		     						<option value="auth">作者</option>
		     					</select>
		     					<input type="hidden" name="action" value="QueryBook"/>
		     			　		<input class="txt" type="text" name="name"/>
		     			        <input class="easyui-linkbutton" iconCls="icon-search" data-options="width:100" type="submit" value="查询"  />
		     			      </form>
	     				</td>
     			</tr>
     			</table>
     			
     			<iframe id="_content" scrolling="no" name="_content" src="${pageContext.request.contextPath }/IndexServlet?action=QueryAllBooks" frameborder="0" width="800px" height="600px">
     			</iframe>
     		</td>
     	</tr>
     </table>
  </body>
  <script type="text/javascript">
  	
  </script>
</html>
     					
     					