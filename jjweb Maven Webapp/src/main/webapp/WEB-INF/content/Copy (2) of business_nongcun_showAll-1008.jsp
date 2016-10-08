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
	<div class="panel-body" style="padding-bottom:0px;">
		 <div class="panel panel-default">
			 <div class="panel-heading">I.导入&nbsp;&nbsp;&nbsp;&nbsp;<a href="nongcun_templetDownload" >导入模板下载</a>
			 </div>
				 <div class="panel-body">
					<form action="nongcun_upload_commit" method="post"
					enctype="multipart/form-data">
						<div class="form-group" style="margin-top:15px">
						 	<div class="">
						 		<input type="file" name="myFile" class="btn btn-primary">
						 	</div>
						 	注意：<ul>
  								<li>导入Excel必须为2003格式(即.xls)</li>
  								<li>导入Excel必须符合一定的模板</li>
  							</ul>
						 	<div class="" style="text-align:left;">
						 		<input type="submit" value="上传" 
									class="btn btn-primary">
						 	</div>
						</div>
					</form>
				</div>
		</div>
		
		
		
		<div class="panel panel-default">
		 	<div class="panel-heading">
			II.导出
			</div>
				<div class="panel-body">
				<p>导出所有数据到Excel</p>
				<a href="nongcun_download" class="btn btn-primary">点击</a>
				</div>
		</div>
	</div>
	
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
		</table>
	<!-- <a href="#">新增</a><a href="#">下载</a> -->
	<hr>
	
	
	<jsp:include page="main_foot.jsp"></jsp:include>

</body>

</html>
