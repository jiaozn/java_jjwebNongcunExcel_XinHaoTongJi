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
User user=new User();
if (session.getAttribute("user")!=null){
	user=(User)session.getAttribute("user");
}else{
	user.setId(0);
	user.setName("游客");
}

%>

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

</head>
<body>

	
	欢迎,<%=user.getName() %>.<a href="user_logout.Action">退出</a>
	<div class="panel-body" style="padding-bottom:0px;">
		 <div class="panel panel-default">
			 <div class="panel-heading">IV.注意
			 </div>
				 <div class="panel-body">
					<form action="nongcun_upload_commit.Action" method="post"
					enctype="multipart/form-data">
						<div class="form-group" style="margin-top:15px">
						 	<ul>
						 		<li>i.导入导出
						 			<ul>
  										<li>导入Excel必须为2003格式(即.xls)</li>
  										<li>导入Excel必须符合一定的模板</li>
  										<li>导入Excel时，将按照乡镇+行政村名进行匹配，若数据库中已有改村的记录，则数据更新进去；若没有该村记录，则添加这条村子的记录进去。</li>
  										<li>【导出所有】耗时较长,耐心等待</li>
  									</ul>
  								</li>
  								<li>ii.数据在线
						 			<ul>
  										<li>取数据库所有内容简单做展现，速度较慢</li>
  										<li>可按列排序、搜索关键字</li>
  										<li>其他功能未实现。如需更新、增加，采用导入excel方式，确保乡镇、行政村名正确，上传覆盖</li>
  									</ul>
  								</li>
  							</ul>
						 	</div>
					</form>
				</div>
		</div>
	</div>
	
	<jsp:include page="main_foot.jsp"></jsp:include>

</body>

</html>
