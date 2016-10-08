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
  								<li>【导入所有】耗时较长,耐心等待</li>
  							</ul>
						 	<div class="" style="text-align:left;">
						 		<input type="submit" value="上传" 
									class="btn btn-primary">
								<a href="nongcun_download" class="btn btn-default">导出所有</a>
						 	</div>
						</div>
					</form>
				</div>
		</div>
	</div>
	
	<table id="nongcunTable">	</table>
	
<script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>

<script src="bootstrap-table-master/dist/bootstrap-table.min.js" type="text/javascript"></script>

<script src="bootstrap-table-master/dist/locale/bootstrap-table-zh-CN.min.js" type="text/javascript"></script>
	
	
	
	<script>
	var $table;
    function queryParams(params) {
        return {
            pageSize: params.pageSize,
            pageIndex: params.pageNumber,
            searchText:params.searchText
        };
    }
initTable();
    function initTable() {
        var queryUrl = 'http://localhost:8080/jjweb/GetNongcunAll';
        $table = $('#nongcunTable').bootstrapTable({
            method: 'get',
            url: queryUrl,
            height: $(window).height() - 290,
            striped: true,//显示条纹
            pagination: true,//底部分页
            singleSelect: true,//复选框只能选一条记录
            pageSize: 10,
            paginationLoop:true,//为true则分页条无限循环
            pageNumber: 1,
            pageList: [10, 50, 100, 500,1000],
            selectItemName:'',
            search: true, //不显示 搜索框
            searchOnEnterKey:true,
            strictSearch:false,
            strictSearch:'',
            searchTimeOut:1000,
            trimOnSearch:true,
            showFooter:true,
            showRefresh:true,
            showToggle:true,
            showPaginationSwitch:true,
            searchAlign:'right',
            buttonsAlign:'right',
            toolbarAlign:'left',
            paginationVAlign:'both',
            paginationPreText:'<',
            paginationNextText:'>',
            clickToSelect:true,
            checkboxHeader:true,
            maintainSelected:false,
            showColumns: true, //显示下拉框（选择显示的列）
            sidePagination: "client", //服务端请求
            queryParams: queryParams,
            minimunCountColumns: 2,
            queryParamsType: "",//分页参数设置
            formatShowingRows: function (pageIndex, totalRows) {
                return '当前页是第:'+pageIndex+'页， 共'+totalRows+' 条记录';
            },
            columns: [
                      {
                    	  checkbox:true,
                    	  align: 'center',
                          valign: 'middle',
                          sortable: false,
                      },
            {
                field: 'dishi',
                title: '地市',
                //width: 20,
                align: 'center',
                valign: 'middle',
                sortable: true,
                searchable:true,
            }, {
                field: 'quxian',
                title: '区县',
               // width: 200,
                align: 'left',
                valign: 'top',
                searchable:true,
                sortable: true,
            }, {
                field: 'xiangzhen',
                title: '乡镇',
               // width: 50,
                align: 'left',
                valign: 'top',
                searchable:true,
                sortable: true
            }, {
                field: 'xiangzhenleixing',
                title: '乡镇类型',
               // width: 110,
                align: 'middle',
                valign: 'top',
                searchable:true,
                sortable: true
            }, {
                field: 'xingzhengcunming',
                title: '行政村名称',
               // width: 180,
                align: 'left',
                valign: 'top',
                searchable:true,
                sortable: true
            }, {
                field: 'shinei2g',
                title: '室内2G',
              //  width: 100,
                align: 'left',
                valign: 'top',
                sortable: true
            }, {
                field: 'shiwai2g',
                title: '室外2G',
               // width: 50,
                align: 'left',
                valign: 'top',
                sortable: true
            }, {
                field: 'shinei3g',
                title: '室内3G',
               // width: 100,
                align: 'center',
                valign: 'middle',
                sortable: true,
            }, {
                field: 'shiwai3g',
                title: '室外3G',
              //  width: 50,
                align: 'left',
                valign: 'top',
                sortable: true,
            }, {
                field: 'shinei4g',
                title: '室内4G',
               // width: 50,
                align: 'left',
                valign: 'top',
                sortable: true
            }, {
                field: 'editor',
                title: '操作账号',
               // width: 50,
                align: 'left',
                valign: 'top',
                sortable: true
            }, {
                field: 'time',
                title: '修改时间',
              //  width: 50,
                align: 'left',
                valign: 'top',
                sortable: true,
                formatter:function(value,row,index){  
                    var nian=value.year+1900;
                    var yue=value.month+1;
                    var ri=value.date;
                    var shi=value.hours;
                    var fen=value.minutes;
                    var miao=value.seconds;
                    var ss=value.nanos;
                         return nian+'年'+yue+'月'+ri+'日'+shi+':'+fen+':'+miao+'.'+ss;  
                     } 
            }, {
                field: 'beizhu',
                title: '备注',
              //  width: 50,
                align: 'left',
                valign: 'top',
                sortable: true
            },
            {
                title: '操作',
                field: 'id',
                align: 'center',
                formatter:function(value,row,index){  
             var e = '<a href="#" mce_href="#" onclick="edit(\''+ row.id + '\')">编辑</a> ';  
             var d = '<a href="#" mce_href="#" onclick="del(\''+ row.id +'\')">删除</a> ';  
                  return e+d;  
              } 
            }],
            onLoadSuccess: function () {
				
            },
            onLoadError: function () {
                mif.showErrorMessageBox("数据加载失败！");
            },
            onRefresh:function(){
            	initTable();
            }
        });
    }
	</script>
	<hr>
	
	
	<jsp:include page="main_foot.jsp"></jsp:include>

</body>

</html>
