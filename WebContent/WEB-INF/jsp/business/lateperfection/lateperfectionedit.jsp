<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />

<%@ include file="../../common/common.jsp"%>

<title>后期完善</title>
<script type="text/javascript">

var validator;
var layerHeight = "250";

function ajaxTPFileList() {
	var table = $('#TPFileList').dataTable();
	if(table) {
		table.fnDestroy();
	}

	var t = $('#TPFileList').DataTable({
					"paging": false,
					"lengthMenu":[5],//设置一页展示10条记录
					"processing" : false,
					"serverSide" : true,
					"stateSave" : false,
					"searching" : false,
					"serverSide" : true,
					"retrieve" : true,
					"sAjaxSource" : "${ctx}/business/lateperfection?methodtype=getTPFileList",
					"fnServerData" : function(sSource, aoData, fnCallback) {
						var param = {};
						var formData = $("#processControlInfo").serializeArray();
						formData.forEach(function(e) {
							aoData.push({"name":e.name, "value":e.value});
						});

						$.ajax({
							"url" : sSource,
							"datatype": "json", 
							"contentType": "application/json; charset=utf-8",
							"type" : "POST",
							"data" : JSON.stringify(aoData),
							success: function(data){
								/*
								if (data.message != undefined) {
									alert(data.message);
								}
								*/
								fnCallback(data);
							},
							 error:function(XMLHttpRequest, textStatus, errorThrown){
				                 //alert(XMLHttpRequest.status);
				                 //alert(XMLHttpRequest.readyState);
				                 //alert(textStatus);
				             }
						})
					},
						
					"language": {
		        		"url":"${ctx}/plugins/datatables/chinese.json"
		        	},
					"columns" : [ 
						{"data": null, "defaultContent" : '', "className" : 'td-center'}, 
						{"data" : "filename", "className" : 'td-center'}, 
						{"data" : "path", "className" : 'td-center'},
						{"data" : "memo", "className" : 'td-center'}, 
						{"data": null, "defaultContent" : '', "className" : 'td-center'}
					],
					"columnDefs":[
			    		{"targets":0,"render":function(data, type, row){
							return row["rownum"] + "<input type=checkbox name='numCheckTP' id='numCheckTP' value='" + row["id"] + "' />"
	                    }},
			    		{"targets":4,"render":function(data, type, row){
			    			return "<a href=\"#\" onClick=\"doUpdateTPFile('" + row["id"] + "')\">编辑</a>"
	                    }}
				    ] 						
				});

	t.on('click', 'tr', function() {
		$(this).toggleClass('selected');
	});

	// Add event listener for opening and closing details
	t.on('click', 'td.details-control', function() {

		var tr = $(this).closest('tr');
		t
		var row = t.row(tr);
		t

		if (row.child.isShown()) {
			// This row is already open - close it
			row.child.hide();
			tr.removeClass('shown');
		} else {
			// Open this row
			row.child(format(row.data())).show();
			tr.addClass('shown');
		}
	});

};

function ajaxQuestionList() {
	var table = $('#questionList').dataTable();
	if(table) {
		table.fnDestroy();
	}

	var t = $('#questionList').DataTable({
					"paging": false,
					"lengthMenu":[5],//设置一页展示10条记录
					"processing" : false,
					"serverSide" : true,
					"stateSave" : false,
					"searching" : false,
					"serverSide" : true,
					"retrieve" : true,
					"sAjaxSource" : "${ctx}/business/lateperfection?methodtype=getQuestionList",
					"fnServerData" : function(sSource, aoData, fnCallback) {
						var param = {};
						var formData = $("#processControlInfo").serializeArray();
						formData.forEach(function(e) {
							//aoData.push({"name":e.name, "value":e.value});
						});
						aoData.push({"name":"type", "value":index});
						$.ajax({
							"url" : sSource,
							"datatype": "json", 
							"contentType": "application/json; charset=utf-8",
							"type" : "POST",
							"data" : JSON.stringify(aoData),
							success: function(data){
								/*
								if (data.message != undefined) {
									alert(data.message);
								}
								*/
								fnCallback(data);
							},
							 error:function(XMLHttpRequest, textStatus, errorThrown){
				                 //alert(XMLHttpRequest.status);
				                 //alert(XMLHttpRequest.readyState);
				                 //alert(textStatus);
				             }
						})
					},
						
					"language": {
		        		"url":"${ctx}/plugins/datatables/chinese.json"
		        	},
					"columns" : [ 
						{"data": null, "defaultContent" : '', "className" : 'td-center'},
						{"data" : "createDate", "className" : 'td-center'}, 
						{"data" : "description", "className" : 'td-center'},
						{"data" : "prove", "className" : 'td-center'},
						{"data" : "expectDate", "className" : 'td-center'},
						{"data" : "finishTime", "className" : 'td-center'},
						{"data": null, "defaultContent" : '', "className" : 'td-center'}
					],
					"columnDefs":[
			    		{"targets":0,"render":function(data, type, row){
							return row["rownum"] + "<input type=checkbox name='numCheckQuestion' id='numCheckQuestion' value='" + row["id"] + "' />"
	                    }},
			    		{"targets":6,"render":function(data, type, row){
			    			return "<a href=\"#\" onClick=\"doUpdateQuestion('" + row["id"] + "')\">编辑</a>"
	                    }}
				    ] 						
				});

	t.on('click', 'tr', function() {
		$(this).toggleClass('selected');
	});
	
	// Add event listener for opening and closing details
	t.on('click', 'td.details-control', function() {

		//alert(999);

		var tr = $(this).closest('tr');
		t
		var row = t.row(tr);
		t

		if (row.child.isShown()) {
			// This row is already open - close it
			row.child.hide();
			tr.removeClass('shown');
		} else {
			// Open this row
			row.child(format(row.data())).show();
			tr.addClass('shown');
		}
	});

};

function initEvent(){

	controlButtons($('#keyBackup').val());

}

$(window).load(function(){
	initEvent();
});

$(document).ready(function() {

	ajaxTPFileList();
	ajaxQuestionList();
})

function reloadTPFileList() {
	$('#TPFileList').DataTable().ajax.reload(null,false);
	
	//reloadTabWindow();
}

function reloadQuestionList() {

	$('#questionList').DataTable().ajax.reload(null,false);
	
	//reloadTabWindow();
}

function doDelete() {
	
	if (confirm("${DisplayData.endInfoMap.message}")) {
		//将提交按钮置为【不可用】状态
		//$("#submit").attr("disabled", true); 
		$.ajax({
			type : "POST",
			contentType : 'application/json',
			dataType : 'json',
			url : "${ctx}/business/lateperfection?methodtype=deleteDetail",
			data : $('#keyBackup').val(),// 要提交的表单
			success : function(d) {
				if (d.rtnCd != "000") {
					alert(d.message);	
				} else {
					controlButtons("");
					reloadTPFileList();
					reloadQueestionList();
					//clearProjectTaskInfo();
					//reloadTabWindow();
				}
				/*	
				//不管成功还是失败都刷新父窗口，关闭子窗口
				var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
				//parent.$('#events').DataTable().destroy();/
				parent.reload_contactor();
				parent.layer.close(index); //执行关闭
				*/
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				//alert(XMLHttpRequest.status);					
				//alert(XMLHttpRequest.readyState);					
				//alert(textStatus);					
				//alert(errorThrown);
			}
		});
	}
}



function controlButtons(data) {
	function controlButtons(data) {
		$('#keyBackup').val(data);
		if (data == '') {
			$('#delete').attr("disabled", true);
			$('#addtpfile').attr("disabled", true);
			$('#deletetpfile').attr("disabled", true);
			$('#addquestion').attr("disabled", true);
			$('#deletequestion').attr("disabled", true);
			
		} else {
			$('#delete').attr("disabled", false);
			$('#addtpfile').attr("disabled", false);
			$('#addquestion').attr("disabled", false);
		}
	}
}

function doAddTPFile() {
	var projectId = $('#keyBackup').val();
	var url = "${ctx}/business/esrelationfile?methodtype=addtpfileinit&projectId=" + projectId;
	openLayer(url, $(document).width() - 25, layerHeight, false);	
}

function doUpdateTPFile(key) {
	var projectId = $('#keyBackup').val();
	var url = "${ctx}/business/esrelationfile?methodtype=updatetpfileinit&projectId=" + projectId + "&key=" + key;
	openLayer(url, $(document).width() - 25, layerHeight, false);
}

function doDeleteTPFile() {
	var str = '';
	$("input[name='numCheckTP']").each(function(){
		if ($(this).prop('checked')) {
			str += $(this).val() + ",";
		}
	});

	if (str != "") {
		if (confirm("您确认执行该操作吗？") == false) {
			return;
		}
		$.ajax({
			contentType : 'application/json',
			dataType : 'json',						
			type : "POST",
			data : str,// 要提交的表单						
			url : "${ctx}/business/esrelationfile?methodtype=deletetpfile",
			success : function(d) {
				if (d.rtnCd != "000") {
					alert(d.message);
				} else {
					reloadTPFileList();
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("发生系统异常，，请再试或者联系管理员。");
			}
		});
		
	} else {
		alert("请先选中要删除的记录。");
	}

}

function doAddQuestion() {
	var projectId = $('#keyBackup').val();
	var url = "${ctx}/business/esrelationfile?methodtype=addquestioninit&projectId=" + projectId;
	openLayer(url, $(document).width() - 25, layerHeight, false);	
}

function doUpdateQuestion(key) {
	var projectId = $('#keyBackup').val();
	var url = "${ctx}/business/esrelationfile?methodtype=updatequestioninit&projectId=" + projectId + "&key=" + key;
	openLayer(url, $(document).width() - 25, layerHeight, false);
}

function doDeleteQuestion() {
	var str = '';
	$("input[name='numCheckQuestion']").each(function(){
		if ($(this).prop('checked')) {
			str += $(this).val() + ",";
		}
	});

	if (str != "") {
		if (confirm("您确认执行该操作吗？") == false) {
			return;
		}
		$.ajax({
			contentType : 'application/json',
			dataType : 'json',						
			type : "POST",
			data : str,// 要提交的表单						
			url : "${ctx}/business/esrelationfile?methodtype=deletequestion",
			success : function(d) {
				if (d.rtnCd != "000") {
					alert(d.message);
				} else {
					reloadQuestionList();
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("发生系统异常，，请再试或者联系管理员。");
			}
		});
		
	} else {
		alert("请先选中要删除的记录。");
	}

}
</script>

</head>

<body>
<div id="container">

		<div id="main">					
			<div  style="height:20px"></div>
				
			<legend>后期完善-基本信息</legend>

			<button type="button" id="delete" class="DTTT_button" onClick="doDelete();"
					style="height:25px;margin:-20px 30px 0px 0px;float:right;">删除</button>
			<table class="form" width="850px">
				<tr>
					<td width="60px">项目编号：</td>
					<td width="240px">
						${DisplayData.projectTaskData.projectid}
					</td>
					<td width="100px">项目名称：</td> 
					<td>
						${DisplayData.projectTaskData.projectname}
					</td>
					<td width="60px">暂定型号：</td> 
					<td>
						${DisplayData.projectTaskData.tempversion}
					</td>
				</tr>
				<tr>
					<td>
						项目经理：
					</td>
					<td>
						${DisplayData.projectTaskData.manager}
					</td>
					<td>
						参考原型：
					</td>
					<td colspan=4>
						${DisplayData.projectTaskData.referprototype}
					</td>
				</tr>
				<tr>
					<td>	
						起始时间：
					</td>
					<td> 
						${DisplayData.projectTaskData.begintime}
					</td>
					<td>	
						预计完成时间：
					</td>
					<td> 
						<label id="projectEndTime">${DisplayData.projectTaskData.endtime}</label>
					</td>							
					<td>
						超期：
					</td>
					<td> 
						${DisplayData.exceedTime}
					</td>
				</tr>

			</table>

			<form:form modelAttribute="dataModels" id="processControlInfo" style='padding: 0px; margin: 10px;' >
				<input type=hidden id="keyBackup" name="keyBackup" value="${DisplayData.keyBackup}"/>
				<input type=hidden id="id" name="id" value=""/>
				
				<div  style="height:20px"></div>
				<legend>试产报告</legend>
				<button type="button" id="deletetpfile" class="DTTT_button" onClick="doDeleteTPFile();"
						style="height:25px;margin:-20px 30px 0px 0px;float:right;" >删除</button>
				<button type="button" id="addtpfile" class="DTTT_button" onClick="doAddTPFile();"
						style="height:25px;margin:-20px 5px 0px 0px;float:right;" >新建</button>
				<table id="TPFileList" class="display" cellspacing="0">
					<thead>
						<tr class="selected">
							<th style="width: 80px;" class="dt-middle">No</th>
							<th style="width: 80px;" class="dt-middle">文件名</th>
							<th style="width: 30px;" class="dt-middle">路径</th>
							<th style="width: 80px;" class="dt-middle">说明</th>
							<th style="width: 80px;" class="dt-middle">操作</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
						</tr>
					</tfoot>
				</table>

				<div  style="height:20px"></div>
				<legend style="display:inline">问题和改善</legend>
				<button type="button" id="deletequestion" class="DTTT_button" onClick="doDeleteQuestion();"
						style="height:25px;margin:-20px 30px 0px 0px;float:right;" >删除</button>
				<button type="button" id="addquestion" class="DTTT_button" onClick="doAddQuestion();"
						style="height:25px;margin:-20px 5px 0px 0px;float:right;" >新建</button>
				<div>
					<table id="questionList" class="display" cellspacing="0">
						<thead>
							<tr class="selected">
								<th style="width: 40px;" class="dt-middle">新建日期</th>
								<th style="width: 180px;" class="dt-middle">问题描述</th>
								<th style="width: 180px;" class="dt-middle">改善方案</th>
								<th style="width: 40px;" class="dt-middle">预期完成时间</th>
								<th style="width: 40px;" class="dt-middle">完成时间</th>
								<th style="width: 40px;" class="dt-middle">操作</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
							</tr>
						</tfoot>
					</table>
				</div>

			</form:form>
		</div>
</html>