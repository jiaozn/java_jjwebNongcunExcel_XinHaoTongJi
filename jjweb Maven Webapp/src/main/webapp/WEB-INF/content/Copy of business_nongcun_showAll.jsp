<%@ page language="java" import="java.util.*,com.jjweb.model.User,com.jjweb.model.Nongcun"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<% User user=(User)session.getAttribute("user");
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

<script type="text/javascript">
	var total=<%=((List)request.getAttribute("listNongcun")).size()%>;
	var pageSize=5;
	var pages=0;
	if(total%pageSize==0){
		pages=total/pageSize;
	}else{
		pages=Math.floor(total/pageSize+1);
	}
	document.getElementById("sumSpan").innerHTML=pages;
	for(var i=0;i<2;i++){
		if(i<total){
			document.getElementById("tr_"+(i+1)).style.display="";
		}
	}
	
	
	function first(){
		showPage(0);
	}
	
	function back(){
		var page=document.getElementById("pageId").value;
		var currentPage=parseInt(page)-1;
		if(page=0){
			alert("已经是第一页了");
		}else{
			showPage(currentPage);
		}
	}
	
	function next(){
		var page=document.getElementById("pageId").value;
		var currentPage=parseInt(page)+1;
		if(page==parseInt(pages)-1){
			alert("已经是最后一页了!");
		}else{
			showPage(currentPage);
		}
	}
	
	function last(){
		showPage(parseInt(pages)-1);
	}
	
	function hide(){
		for(var i=0;i<total;i++){
			document.getElementById("tr_"+(i+1)).style.display="none";
		}
	}
	function showPage(currentPage){
		hide();
		var start=currentPage*pageSize;
		var end=(currentPage+1)*pageSize;
		for(var i=start;i<end;i++){
			if(i<total){
				document.getElementById("tr_"+(i+1)).style.display="";
			}
		}
		document.getElementById("pageId").value=currentPage;
		document.getElementById("pageSpan").innerHTML=currentPage+1;
	}
</script>


</head>

<body>
	<jsp:include page="main_head.jsp"></jsp:include>

	<hr>
	<form action="nongcun_upload_commit" method="post"
		enctype="multipart/form-data" role="form">

		<div class="form-group">
			<h4>I.导入</h4>
			<input type="file" name="myFile">
			<input type="hidden" name="" value="<%=user.getName()%>">
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
		<% 
		List listT=(List)request.getAttribute("listNongcun");
		if(listT.size()>0){
			for(int i=0;i<listT.size();i++){
			Nongcun	art=(Nongcun)listT.get(i);
			%>
		<!--<s:iterator value="listNongcun" var="art">-->
			<tr id="tr_<%=i+1 %>" >
			
			<td><%=art.getId() %></td>
			<td><%=art.getDishi() %></td>
			<td><%=art.getQuxian() %></td>
			<td><%=art.getXiangzhen() %></td>
			<td><%=art.getXiangzhenleixing() %></td>
			<td><%=art.getXingzhengcunming() %></td>
			<td><%=art.getShinei2g() %></td>
			<td><%=art.getShiwai2g() %></td>
			<td><%=art.getShinei3g() %></td>
			<td><%=art.getShiwai3g() %></td>
			<td><%=art.getShinei4g() %></td>
			<td><%=art.getShiwai4g() %></td>
			<td><%=art.getEditor() %></td>
			<td><%=art.getTime() %></td>
			<td><%=art.getBeizhu() %></td>
			<!--  	<td><s:property value="#art.id" />
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
				</td>-->
				<td>
				
				<%--<a
					href="nongcun_edit?nongcun.id=<s:property value="#art.id"/>">修改
				</a> <a href="nongcun_delete?nongcun.id=<s:property value="#art.id"/>">删除
				</a>
				--%></td>
			</tr>
		<!--</s:iterator>-->
	
	<%
	}
		}
			else{%>没有数据<%
		
		}
		%>
		</table>
	<input type="button" value="首页" onclick="first()"/>
	<input type="button" value="上一页" onclick="back()"/>
	<input type="button" value="下一页" onclick="next()"/>
	<input type="button" value="末页" onclick="last()"/>
	<input type="hidden" value="0" id="pageId"/>
第<span id="pageSpan">1</span>页&nbsp;|&nbsp;共<span id="sumSpan"></span>页&nbsp;&nbsp;&nbsp;&nbsp;条记录
	<!-- <a href="#">新增</a><a href="#">下载</a> -->
	
	<jsp:include page="main_foot.jsp"></jsp:include>

</body>

</html>
