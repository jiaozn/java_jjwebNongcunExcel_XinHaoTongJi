<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="Refresh" content="1;url=nongcun_showAll">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<title>Hello,world</title>
<style type="text/css">
<!--
.STYLE1 {
	color: #FF0000
}
-->
</style>

<script language="javascript">
	var times = 2;
	clock();
	function clock() {
		window.setTimeout('clock()', 1000);
		times = times - 1;
		time.innerHTML = times;
	}
</script>
</head>

<body>
	<jsp:include page="main_head.jsp"></jsp:include>
	导入成功...
	<table>
		<tr>
			<td class="FontBlack STYLE1">该页面将在</td>
			<td class="FontBlack">
				<div class="STYLE1" id="time">2</div></td>
			<td class="FontBlack STYLE1">秒后自动跳转</td>
		</tr>
	</table>

	<jsp:include page="main_foot.jsp"></jsp:include>
</body>
</html>
