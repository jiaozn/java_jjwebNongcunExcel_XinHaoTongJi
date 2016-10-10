<%@ page language="java" import="java.util.*,com.jjweb.model.User" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<base href="<%=basePath%>">

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>
<%
User user=new User();
if (session.getAttribute("user")!=null){
	user=(User)session.getAttribute("user");
}else{
	user.setId(0);
	user.setName("游客");
}

%>
<body>
<div class="page-header">
	<h2>农村信号覆盖统计</h2>
</div>
<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
				<li><a href="nongcun_showAll.Action">i.导入导出 </a>
					</li>
					<!--  
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">导入导出<span class="caret"></span>
					</a>
						<ul class="dropdown-menu">
						<li><a href="nongcun_templetDownload">导入模板下载 </a>
					</li>
						<li><a href="nongcun_upload_input">导入... </a>
					</li>
							<li><a href="nongcun_download">导出数据</a>
							</li>
						</ul></li>
					-->	
					<li><a href="nongcun_showAll2.Action">ii.数据在线</a>
					</li>	
					<li><a href="nongcun_introduction.Action">iii.说明</a>
					</li>
					
					
					
						<li><a href="user_logout.Action">iv.退出</a>
						</li>
						<li class="dropdown"><a href="user_logout.Action" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">欢迎,<%=user.getName()%><span class="caret"></span>
					</a>
					<%--	
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">日志分类<span class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<li><a href="category_showAll">显示所有</a>
							</li>
							
							<li><a href="category_add">新增分类</a>
							</li>
						</ul></li>
						
						
						
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">文件管理<span class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<li><a href="upload!listAll">显示所有</a>
							</li>
							<li><a href="upload">新增</a>
							</li>
						</ul></li>

					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">视频<span class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<li><a href="movie!listAll">显示所有</a>
							</li>
							<li><a href="movie">新增</a>
							</li>
						</ul></li>
				
					
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">欢迎，<s:property value="#session.userx.name" /><span class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<li><a href="user_edit?user.id=<s:property value="#session.userx.id"/>">编辑</a>
							</li>
							<li><a href="user_logout?user.id=<s:property value="#session.userx.id"/>">注销</a>
							</li>
						</ul></li>
								<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">用戶管理<span class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<li><a href="user_showAll">用戶清單</a>
							</li>
							<li><a href="counterSession_showAll">网站访问统计</a>
							</li>
							<li><a href="accessRecord_showAll">文章访问统计</a>
							</li>
							<li><a href="comments_showAll">管理所有评论</a>
							</li>
						</ul></li>
						
						
					<li><a href="user_login">登陆 </a>
					</li>
					<li><a href="user_add">注册 </a>
					</li>
						--%>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
</body>
</html>
