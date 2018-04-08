<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String path=request.getContextPath();
String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>您访问的页面不存在</title>
</head>
<body>
	<h1>
		<font color="red">服务器故障，请联系程序猿</font>
	</h1>
</body>
</html>