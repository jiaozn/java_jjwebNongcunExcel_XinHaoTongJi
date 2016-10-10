<%@ page language="java" import="java.util.*,com.jjweb.model.User"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	User userx = new User();
	userx.setName("西区");
	userx.setPassword("password");
	session.setAttribute("userx", userx);
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

<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="bootstrap-table/bootstrap-table.css">
<script src="bootstrap/js/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap-table/bootstrap-table.js"></script>
<!-- put your locale files after bootstrap-table.js -->
<script src="bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>

<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<title>Hello,world</title>
</head>

<body>
	<jsp:include page="main_head.jsp"></jsp:include>

	<hr>
	<form action="nongcun_upload" method="post"
		enctype="multipart/form-data" role="form">

		<div class="form-group">
			<h4>I.导入</h4>
			<input type="file" name="myFile">
		</div>
		<div class="form-group">
			<input type="hidden" value="<s:property value="#session.userx"/>"
				name="user" /> <input type="submit" value="上传"
				class="btn btn-default">
		</div>
	</form>
	<br>
	<br>
	<h4>
		II.导出全部<a href="nongcun_download">>>></a>
	</h4>
	
	
	
	
	
	
	<div class="panel-body" style="padding-bottom:0px;">
        <div class="panel panel-default">
            <div class="panel-heading">查询条件</div>
            <div class="panel-body">
                <form id="formSearch" class="form-horizontal">
                    <div class="form-group" style="margin-top:15px">
                        <label class="control-label col-sm-1" for="txt_search_departmentname">部门名称</label>
                        <div class="col-sm-2">
                            <input type="text" class="form-control" id="txt_search_departmentname">
                        </div>
                        <label class="control-label col-sm-1" for="txt_search_statu">状态</label>
                        <div class="col-sm-2">
                            <input type="text" class="form-control" id="txt_search_statu">
                        </div>
                        <div class="col-sm-4" style="text-align:left;">
                            <button type="button" style="margin-left:50px" id="btn_query" class="btn btn-primary">查询</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>       

        <div id="toolbar" class="btn-group">
            <button id="btn_add" type="button" class="btn btn-default">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
            </button>
            <button id="btn_edit" type="button" class="btn btn-default">
                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
            </button>
            <button id="btn_delete" type="button" class="btn btn-default">
                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
            </button>
        </div>
        <table id="tb_departments"></table>
    </div>
	
	
	<script type="text/javascript">
	$(function () {

	    //1.初始化Table
	    var oTable = new TableInit();
	    oTable.Init();

	    //2.初始化Button的点击事件
	    var oButtonInit = new ButtonInit();
	    oButtonInit.Init();

	});


	var TableInit = function () {
	    var oTableInit = new Object();
	    //初始化Table
	    oTableInit.Init = function () {
	        $('#tb_departments').bootstrapTable({
	            url: '/Home/GetDepartment',         //请求后台的URL（*）
	            method: 'get',                      //请求方式（*）
	            toolbar: '#toolbar',                //工具按钮用哪个容器
	            striped: true,                      //是否显示行间隔色
	            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	            pagination: true,                   //是否显示分页（*）
	            sortable: false,                     //是否启用排序
	            sortOrder: "asc",                   //排序方式
	            queryParams: oTableInit.queryParams,//传递参数（*）
	            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
	            pageNumber:1,                       //初始化加载第一页，默认第一页
	            pageSize: 10,                       //每页的记录行数（*）
	            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
	            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
	            strictSearch: true,
	            showColumns: true,                  //是否显示所有的列
	            showRefresh: true,                  //是否显示刷新按钮
	            minimumCountColumns: 2,             //最少允许的列数
	            clickToSelect: true,                //是否启用点击选中行
	            height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
	            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
	            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
	            cardView: false,                    //是否显示详细视图
	            detailView: false,                   //是否显示父子表
	            columns: [{
	                checkbox: true
	            }, {
	                field: 'Name',
	                title: '部门名称'
	            }, {
	                field: 'ParentName',
	                title: '上级部门'
	            }, {
	                field: 'Level',
	                title: '部门级别'
	            }, {
	                field: 'Desc',
	                title: '描述'
	            }, ]
	        });
	    };

	    //得到查询的参数
	    oTableInit.queryParams = function (params) {
	        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
	            limit: params.limit,   //页面大小
	            offset: params.offset,  //页码
	            departmentname: $("#txt_search_departmentname").val(),
	            statu: $("#txt_search_statu").val()
	        };
	        return temp;
	    };
	    return oTableInit;
	};


	var ButtonInit = function () {
	    var oInit = new Object();
	    var postdata = {};

	    oInit.Init = function () {
	        //初始化页面上面的按钮事件
	    };

	    return oInit;
	};
	
	</script>
	
	
	
	
	
	
	
	<br>
	<br>
	<table class="table" data-toggle="table" data-url="data1.json">
		<thead><tr>
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
		</tr></thead>
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
				<td><a
					href="nongcun_edit?nongcun.id=<s:property value="#art.id"/>">修改
				</a> <a href="nongcun_delete?nongcun.id=<s:property value="#art.id"/>">删除
				</a>
				</td>
			</tr>
		</s:iterator>
	</table>
	<!-- <a href="#">新增</a><a href="#">下载</a> -->
	<jsp:include page="main_foot.jsp"></jsp:include>
</body>
</html>
