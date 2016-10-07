<%@ page language="java" import="java.util.*,com.jjweb.model.User,com.jjweb.model.Nongcun"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	User user = new User();
	user.setName("西区");
	user.setPassword("password");
	session.setAttribute("user", user);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<title>Hello,world</title>


</head>

<body>
	<jsp:include page="main_head.jsp"></jsp:include>

	<hr>
	<form action="nongcun_upload_commit" method="post"
		enctype="multipart/form-data" role="form">

		<div class="form-group">
			<h4>I.导入</h4>
			<input type="file" name="myFile">
		</div>
		<div class="form-group">
			<!-- <input type="hidden" value="<s:property value="#session.userx"/>" 
				name="user" /> -->
				<a href="nongcun_templetDownload">导入模板下载</a>
				<input type="submit" value="上传"
				class="btn btn-default">
		</div>
	</form>
	<br>
	<br>
	<h4>
		II.导出全部<a href="nongcun_download">>>></a>
	</h4>
	<br>
	<br>
	<table class="table">
		<tr>
			<td>记录id</td>
			<td>地市</td>
			<td>区县</td>
			<td>乡镇</td>
			<td>乡镇类型</td>
			<td>行政村名称</td>
			<td>2G室内</td>
			<td>2G室外</td>
			<td>3G室内</td>
			<td>3G室外</td>
			<td>4G室内</td>
			<td>4G室外</td>
			<td>编辑人</td>
			<td>编辑时间</td>
			<td>备注</td>
			<td>操作</td>
		</tr>
			<s:iterator value="listNongcun" var="art">
			<tr>
				<td><s:property value="#art.id" />
				</td>
				<td><s:property value="#art.dishi" />
				</td>
				<td><s:property value="#art.quxian" />
				</td>
				<td><s:property value="#art.xiangzhen" />
				</td>
				<td><s:property value="#art.xiangzhenleixing" />
				</td>
				<td><s:property value="#art.xingzhengcunming" />
				</td>
				<td><s:property value="#art.shinei2g" />
				</td>
				<td><s:property value="#art.shiwai2g" />
				</td>
				<td><s:property value="#art.shinei3g" />
				</td>
				<td><s:property value="#art.shiwai3g" />
				</td>
				<td><s:property value="#art.shinei4g" />
				</td>
				<td><s:property value="#art.shiwai4g" />
				</td>
				<td><s:property value="#art.eidtor" />
				</td>
				<td><s:property value="#art.time" />
				</td>
				<td><s:property value="#art.beizhu" />
				</td>
				<td>
				
				<%--<a
					href="nongcun_edit?nongcun.id=<s:property value="#art.id"/>">修改
				</a> <a href="nongcun_delete?nongcun.id=<s:property value="#art.id"/>">删除
				</a>
				--%></td>
			</tr>
		</s:iterator>
		</table>
	<!-- <a href="#">新增</a><a href="#">下载</a> -->
	
	<jsp:include page="main_foot.jsp"></jsp:include>

</body>

</html>
