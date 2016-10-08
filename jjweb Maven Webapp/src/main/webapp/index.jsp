<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%--<meta http-equiv="Refresh" content="0;url=nongcun_showAll">


--%>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />

  <link href="bootstrap-table-master/dist/bootstrap-table.min.css" rel="stylesheet" type="text/css" />
  
<script src="bootstrap/js/jquery.min.js" type="text/javascript"></script>

</head>
<body>
  <jsp:include page="WEB-INF/content/main_head.jsp"></jsp:include>
	
	
	
	<div class="panel-body" style="padding-bottom:0px;">
		 <div class="panel panel-default">
			 <div class="panel-heading">登陆
			 </div>
				 <div class="panel-body">
					<form action="login" method="post">
						<div class="form-group" style="margin-top:15px">
						 	<div class="" style="text-align:left;">
						 	<p>用户名：<input type="text" name="user.name"></p>
							<p>密&nbsp;&nbsp;码：<input type="password" name="user.password"></p>
						 	
						 		<input type="submit" value="登陆" 
									class="btn btn-primary">
									<input type="reset" value="重置" class="btn btn-default">
						 	</div>
						</div>
					</form>
				</div>
		</div>
	</div>
	
	
	
	
	
	
	<jsp:include page="WEB-INF/content/main_foot.jsp"></jsp:include>
</body>
</html>
