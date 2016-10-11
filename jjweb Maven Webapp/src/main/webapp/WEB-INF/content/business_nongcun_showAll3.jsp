<%@ page language="java" import="java.util.*,com.jjweb.model.User,com.jjweb.model.Nongcun"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%User user=new User();
if (session.getAttribute("user")!=null){
	user=(User)session.getAttribute("user");
}else{
	user.setId(0);
	user.setName("游客");
}%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<jsp:include page="main_head.jsp"></jsp:include>
 <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script src="bootstrap/js/jquery.min.js" type="text/javascript"></script>
<script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<title>Hello,world</title>
</head>
<body>

	<form action="nongcun_search" name="form1">
	<input id="searchText" type="text" name="key1" value="<s:property value="key1"/>" >
	<input type="submit" value="搜索" >
	</form>
	<table class="table">
	
	<tr>
			<td>id  </td>
			<td>地市</td>
			<td>区县</td>
			<td>乡镇*</td>
			<td>乡镇类型</td>
			<td>村名*</td>
			<td>2G室内</td>
			<td>2G室外</td>
			<td>3G室内</td>
			<td>3G室外</td>
			<td>4G室内</td>
			<td>4G室外</td>
			<td>操作</td>
		</tr>	
	
	<s:if test="listNongcun == null">
<tr>
<td>
你要查询的农村为空，请
<a href="#">添加</a>
</td>
</tr>
</s:if>
<s:else>
<s:iterator value="listNongcun" var="n">
<tr>
<td height="30">
<s:property value="#n.id"/>
</td>

<td>
<s:property value="#n.dishi"/>
</td>
<td><s:property value="#n.quxian"/>
</td>
<td><s:property value="#n.xiangzhen"/></td>
<td><s:property value="#n.xiangzhenleixing"/></td>
<td><s:property value="#n.xingzhengcunming"/></td>
<td><s:property value="#n.shinei2g"/>
</td>
<td><s:property value="#n.shiwai2g"/>
</td>
<td><s:property value="#n.shinei3g"/>
</td>
<td><s:property value="#n.shiwai3g"/>
</td>
<td><s:property value="#n.shinei4g"/>
</td>
<td><s:property value="#n.shiwai4g"/>
</td>
<td><a href="javascript:setvalue(this)" onclick="javascript:setvalue(this)" >编辑</a>&nbsp;&nbsp;
<a href="nongcun_del?id=<s:property value="#n.id"/>" onclick="javascript:return predel()">删除</a>

</td>
</tr>
</s:iterator>

<tr></tr>
<tr></tr>
<form action="nongcun_saveorupdate" name="form2">
		<tr>
			<td><input type="text" id="nongid" style="width:100%" disabled="disabled"/> </td>
			<td><input type="text" name="nongcun.dishi" id="nongdishi" style="width:100%"/></td>
			<td><input type="text" name="nongcun.quxian" id="nongquxian" style="width:100%"/></td>
			<td><input type="text" name="nongcun.xiangzhen" id="nongxiangzhen" style="width:100%" /></td>
			<td><input type="text" name="nongcun.xiangzhenleixing" id="nongxiangzhenleixing" style="width:100%" /></td>
			<td><input type="text" name="nongcun.xingzhengcunming" id="nongxingzhengcunming" style="width:100%"/></td>
			<td><input type="text" name="nongcun.shinei2g" id="nongshinei2g" style="width:100%"/></td>
			<td><input type="text" name="nongcun.shiwai2g" id="nongshiwai2g" style="width:100%"/></td>
			<td><input type="text" name="nongcun.shinei3g" id="nongshinei3g" style="width:100%"/></td>
			<td><input type="text" name="nongcun.shiwai3g" id="nongshiwai3g" style="width:100%"/></td>
			<td><input type="text" name="nongcun.shinei4g" id="nongshinei4g" style="width:100%"/></td>
			<td><input type="text" name="nongcun.shiwai4g" id="nongshiwai4g" style="width:100%"/></td>
			<td><input type="submit" value="更新/新增" id="nongdishi" style="width:100%"/></td>
		</tr>
</form>

</table>

<table class="table">
<tr>
<td
style="text-align: center">

当前页数<s:property value="pageBean.currentPage"/>/<s:property value="pageBean.totalPage" />页&nbsp;&nbsp;共有<s:property value="pageBean.allRow"/>条&nbsp;&nbsp;&nbsp;&nbsp;每页显示<s:property value="pageBean.pageSize"/>条&nbsp;&nbsp;
<s:if test="pageBean.firstPage">
<a href="nongcun_search?pagenum=0" onclick="javascript:changURL(this)">[首页]</a>
</s:if>
<s:else><span style="color:#CCC">首页</span></s:else>
<s:if test="pageBean.hasPreviousPage">
<a href="nongcun_search?pagenum=<s:property value='pageBean.currentPage-1' />" onclick="javascript:changURL(this)">[前一页]</a>
</s:if>
<s:else><span style="color:#CCC">前一页</span></s:else>
<s:if test="pageBean.hasNextPage">
<a href="nongcun_search?pagenum=<s:property value='pageBean.currentPage+1' />" onclick="javascript:changURL(this)">[后一页]</a>
</s:if>
<s:else><span style="color:#CCC">后一页</span></s:else>
<s:if test="pageBean.lastPage">
<a href="nongcun_search?pagenum=<s:property value='pageBean.totalPage' />" onclick="javascript:changURL(this)">[尾页]</a>
</s:if>
<s:else><span style="color:#CCC">尾页</span></s:else>
	<form action="nongcun_search" onclick="javascript:changAction(this)">
	<input type="text" name="pagenum">
	<input class="btn btn-primary" type="submit" value="跳到">
</form>




</td>
</tr>
</s:else>

</table>

	
	<jsp:include page="main_foot.jsp"></jsp:include>

</body>

<script type="text/javascript">
function setvalue(aaa){
	
	//var nongid=aaa.parentNode.parentNode.rowIndex;
	//alert(nongid);
	//document.getElementById("nongid").value=nongid;
	
	var tr=aaa.parentNode.parentNode.getElementsByTagName("td");
		//alert(tr[1].innerHTML);
	document.getElementById("nongid").value=tr[0].innerHTML;
	document.getElementById("nongdishi").value=tr[1].innerHTML;
	document.getElementById("nongquxian").value=tr[2].innerHTML;
	document.getElementById("nongxiangzhen").value=tr[3].innerHTML;
	document.getElementById("nongxiangzhenleixing").value=tr[4].innerHTML;
	document.getElementById("nongxingzhengcunming").value=tr[5].innerHTML;
	document.getElementById("nongshinei2g").value=tr[6].innerHTML;
	document.getElementById("nongshiwai2g").value=tr[7].innerHTML;
	document.getElementById("nongshinei3g").value=tr[8].innerHTML;
	document.getElementById("nongshiwai3g").value=tr[9].innerHTML;
	document.getElementById("nongshinei4g").value=tr[10].innerHTML;
	document.getElementById("nongshiwai4g").value=tr[11].innerHTML;
}


function predel() { 
	var msg = "确定删除？\n\n请确认！"; 
	if (confirm(msg)==true){ 
	return true; 
	}else{ 
	return false; 
	} 
	} 	
	
	function changeURL(aa){
		aa.href+="&key="+document.getElementById("searchText").value;
	}
	function changeAction(aa){
		aa.action+="&key="+document.getElementById("searchText").value;
	}
</script>

</html>
