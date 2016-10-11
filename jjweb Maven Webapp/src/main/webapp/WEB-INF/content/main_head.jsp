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


<div class="container"> 
    <div class="row"> 
        <div class="span8"> 
            <ul class="nav nav-pills"> 
                <li class="active"><a href="nongcun_showAll">i.导入导出 </a></li> 
                <li><a href="nongcun_showAll2">ii.数据在线</a></li> 
                <li><a href="nongcun_showAll3">ii.数据在线[通用]</a></li> 
                <li><a href="nongcun_introduction">iii.说明</a></li> 
                <li><a href="user_logout">iv.退出</a></li> 
                <li><a href="#">欢迎,<%=user.getName()%></a></li> 
            </ul> 
        </div> 
    </div> 
</div>
</div>
</body>
</html>
