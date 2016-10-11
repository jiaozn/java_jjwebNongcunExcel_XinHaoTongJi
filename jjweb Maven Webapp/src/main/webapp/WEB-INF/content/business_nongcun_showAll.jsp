<%@ page language="java" import="java.util.*,com.jjweb.model.User,com.jjweb.model.Nongcun"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
	
	<jsp:include page="main_head.jsp"></jsp:include>
 <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />

  <link href="bootstrap-table-master/dist/bootstrap-table.min.css" rel="stylesheet" type="text/css" />
  
<script src="bootstrap/js/jquery.min.js" type="text/javascript"></script>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<title>Hello,world</title>
<%
User user=new User();
if (session.getAttribute("user")!=null){
	user=(User)session.getAttribute("user");
}else{
	user.setId(0);
	user.setName("游客");
}

%>
</head>
<body>
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
  								<li>【导入所有】耗时较长,耐心等待</li>
  							</ul>
						 	<div class="" style="text-align:left;">
						 		<input type="submit" value="上传" 
									class="btn btn-primary">
									<input type="hidden" name="user.name" value="<%=user.getName()%>">
								<a href="nongcun_download" class="btn btn-default">导出所有</a>
						 	</div>
						</div>
					</form>
				</div>
		</div>
	</div>
	<img src="img/ok.jpg">
	<hr>
	<p>&nbsp;&nbsp;如无法正常显示，请更换浏览器。附内网下载链接</p>
	<ul>
		<li><a href="download/Firefox.exe">FireFox浏览器</a></li>
	</ul><br>
	<jsp:include page="main_foot.jsp"></jsp:include>

</body>

</html>
