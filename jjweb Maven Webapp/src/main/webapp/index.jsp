<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Refresh" content="0;url=nongcun_showAll">
</head>
<body>
  <jsp:include page="WEB-INF/content/main_head.jsp"></jsp:include>
	<h2>Hello World!</h2>
	<s:form action="login">
		<s:textfield name="name" value="steve"></s:textfield>
		<s:textfield name="password" value="123"></s:textfield>
		<s:submit></s:submit>
	</s:form>
</body>
</html>
