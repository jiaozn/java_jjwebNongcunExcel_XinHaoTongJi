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

	
	欢迎,<%=user.getName() %>.<a href="user_logout">退出</a>
	<div class="panel-body" style="padding-bottom:0px;">
		 <div class="panel panel-default">
			 <div class="panel-heading">IV.注意
			 </div>
				 <div class="panel-body">
					<form action="nongcun_upload_commit" method="post"
					enctype="multipart/form-data">
						<div class="form-group" style="margin-top:15px">
						 	<ul>
						 		<li>i.导入导出
						 			<ul>
  										<li>导入Excel必须为2003格式(即.xls)</li>
  										<li>导入Excel必须符合一定的模板&nbsp;&nbsp;&nbsp;&nbsp;<a href="nongcun_templetDownload" class="btn btn-primary	">导入模板下载</a></li>
  										<li>导入Excel时，系统会按照乡镇+村名进行匹配。若系统中没有该村记录，则自动执行新增操作。若已有该村记录，则自动采取更新操作。</li>
  										<li>导入数据时较慢，耐心等待成功提示，不要刷新。</li>
  										<li>【导出所有】耗时较长,点击后等待提示，期间不要刷新。</li>
  									</ul>
  								</li>
  								<li>ii.数据在线
						 			<ul>
  										<li>搜索的区域为除了Id的所有列</li>
  										<li>表格显示内容是搜索关键字的结果。默认搜索框置空，显示的是所有记录。</li>
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
