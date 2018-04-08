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
<link href="resources/css/index.css" type="text/css" rel="stylesheet">
</head>

<body>
	<iframe class="ifStyle" align="top" width="100%" height="70px" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" border=0 src="top.jsp"></iframe>
	<iframe class="ifStyle" align="left" width="12%" height="90%" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" border=0 src="left.jsp"></iframe>
	<iframe class="ifStyle" align="right" name="mainframe" width="88%" height="90%" marginwidth="0" marginheight="0" scrolling="auto" frameborder="0" border=0 src="<%=request.getContextPath()%>/order/order_list.jsp"></iframe>
</body>

</html>