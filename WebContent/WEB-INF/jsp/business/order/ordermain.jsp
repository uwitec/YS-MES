<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />


<%@ include file="../../common/common.jsp"%>

<title>订单基本数据一览页面</title>
<script type="text/javascript">

	function ajax(pageFlg) {
		var table = $('#TMaterial').dataTable();
		if(table) {
			table.fnClearTable();
			table.fnDestroy();
		}
		var t = $('#TMaterial').DataTable({
				"paging": true,
				"lengthChange":false,
				"lengthMenu":[50,100,200],//设置一页展示20条记录
				"processing" : false,
				"serverSide" : false,
				"stateSave" : false,
				"ordering "	:true,
				"searching" : false,
				"pagingType" : "full_numbers",
				//"scrollY":scrollHeight,
				//"scrollCollapse":true,
				"retrieve" : true,
				"sAjaxSource" : "${ctx}/business/order?methodtype=search&keyBackup="+pageFlg,
				"fnServerData" : function(sSource, aoData, fnCallback) {
					var param = {};
					var formData = $("#condition").serializeArray();
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
							fnCallback(data);
						},
						 error:function(XMLHttpRequest, textStatus, errorThrown){
			             }
					})
				},
	        	"language": {
	        		"url":"${ctx}/plugins/datatables/chinese.json"
	        	},
				"columns": [
					{"data": null, "defaultContent" : '',"className" : 'td-center'},
					{"data": "YSId", "defaultContent" : ''},
					// {"data": "orderId", "defaultContent" : ''},
					{"data": "PIId", "defaultContent" : ''},
					{"data": "deliveryDate", "defaultContent" : '', "className" : 'td-left'},
					{"data": "materialId", "defaultContent" : ''},
					{"data": "materialName", "defaultContent" : ''},
					{"data": "quantity", "defaultContent" : '0', "className" : 'td-right'},
					{"data": "price", "defaultContent" : '0', "className" : 'td-right'},
					{"data": null, "defaultContent" : '0', "className" : 'td-right'},
					{"data": "team", "defaultContent" : ''},
				],
				"columnDefs":[
		    		{"targets":0,"render":function(data, type, row){
		    			return row["rownum"] + "<input type=checkbox name='numCheck' id='numCheck' value='" + row["recordId"] + "' />"
		    			 
                    }},
		    		{"targets":1,"render":function(data, type, row){
		    			var rtn = "";
		    			rtn= "<a href=\"###\" onClick=\"doShow('"+ row["PIId"] + "')\">"+row["YSId"]+"</a>";
		    			return rtn;
		    		}},
		    		{"targets":8,"render":function(data, type, row){
		    			
		    			var v = row["totalPrice"],id = row["YSId"];
		    			return YSKcheck(v,id);
		    		}},
		    		{"targets":7,"render":function(data, type, row){
		    			
		    			var v = row["price"],id = row["YSId"];
		    			return YSKcheck(v,id);
		    		}},
		    		{"targets":6,"render":function(data, type, row){
		    			
		    			var v = row["quantity"],id = row["YSId"];
		    			return YSKcheck(v,id);
		    		}},
		    		{"targets":5,"render":function(data, type, row){
		    			var name = row["materialName"],id = row["YSId"], zzFlag = "";
		    			name = jQuery.fixedWidth(name,35,true);//true:两边截取,左边从汉字开始
		    			var zzFlag = "";
		    			//if(id != ''){
		    			//	zzFlag = id.substr(2,3);
		    			//}
		    			//if(zzFlag == 'YSK') name = '库存订单';//库存订单不显示明细内容
		    			
		    			return name;
		    		}},
		    		{"targets":4,"render":function(data, type, row){
		    			
		    			var v = row["materialId"],id = row["YSId"], zzFlag = "";
		    			//if(id != ''){
		    			//	zzFlag = id.substr(2,3);
		    			//}
		    			//if(zzFlag == 'YSK') v = '库存订单';//库存订单不显示明细内容
		    			return v;
		    		}}				           
	         	] 
			}
		);

	}
	
	function YSKcheck(v,id){
		var zzFlag = "";
		if(id != null && id != ''){
			zzFlag = id.substr(2,3);
		}
		if(zzFlag == 'YSK') v = 0;//库存订单不显示明细内容
		return v;
		
	}
	
	function initEvent(){

		ajax("");
	
		$('#TMaterial').DataTable().on('click', 'tr', function() {
			
			if ( $(this).hasClass('selected') ) {
	            $(this).removeClass('selected');
	        }
	        else {
	        	$('#TMaterial').DataTable().$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	        }
		});
	}

	$(document).ready(function() {

		
		initEvent();
		
	})	
	
	function doSearch() {	

		ajax("S");

	}
	
	function doCreate() {
		
		var url = '${ctx}/business/order?methodtype=create';
		location.href = url;
	}
	
	function doCreateZZ() {
		
		var url = '${ctx}/business/zzorder?methodtype=create';
		location.href = url;
	}
	
	function doCreateZP() {
		
		var url = '${ctx}/business/zporder?methodtype=create';
		location.href = url;
	}
	
	function doShow(PIId) {

		var url = '${ctx}/business/order?methodtype=detailView&PIId=' + PIId;

		location.href = url;
	}

	function doEdit(recordId,parentId) {
		var str = '';
		var isFirstRow = true;
		var url = '${ctx}/business/order?methodtype=edit&parentId=' + parentId+'&recordId='+recordId;

		location.href = url;
	}
		
	function doDelete() {

		var str = '';
		$("input[name='numCheck']").each(function(){
			if ($(this).prop('checked')) {
				str += $(this).val() + ",";
			}
		});

		if (str != '') {
			if(confirm("采购方案,采购合同,全部会被删除,\n\n        确定要删除订单吗？")) {
				jQuery.ajax({
					type : 'POST',
					async: false,
					contentType : 'application/json',
					dataType : 'json',
					data : str,
					url : "${ctx}/business/order?methodtype=delete",
					success : function(data) {
						reload();						
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
		             }
				});
			}
		} else {
			alert("请至少选择一条数据");
		}
		
	}

	function reload() {
		
		$('#TMaterial').DataTable().ajax.reload(null,false);
		
		return true;
	}
	
	
</script>
</head>

<body>
	<div id="container">

		<div id="main">
		
			<div id="search">

				<form id="condition"  style='padding: 0px; margin: 10px;' >

					<table>
						<tr>
							<td width="10%"></td> 
							<td class="label">关键字1：</td>
							<td class="condition">
								<input type="text" id="keyword1" name="keyword1" class="middle"/>
							</td>
							<td class="label">关键字2：</td> 
							<td class="condition">
								<input type="text" id="keyword2" name="keyword2" class="middle"/>
							</td>
							<td>
								<button type="button" id="retrieve" class="DTTT_button" 
									style="width:50px" value="查询" onclick="doSearch();"/>查询
							</td>
							<td width="10%"></td> 
						</tr>
					</table>

				</form>
			</div>
			<div  style="height:10px"></div>
		
			<div class="list">

				<div id="TSupplier_wrapper" class="dataTables_wrapper">
					<div id="DTTT_container" align="right" style="height:40px">
						<a  title="新建常规订单"
							class="DTTT_button " onclick="doCreate();"><span>常规订单</span></a>
						<a  title="新建自制品库存订单"
							class="DTTT_button " onclick="doCreateZZ();"><span>自制品库存订单</span></a>
						<a  title="新建装配品及非完全成品库存订单"
							class="DTTT_button " onclick="doCreateZP();"><span>装配品库存订单</span></a>
						<a  class="DTTT_button " onclick="doDelete();"><span>删除</span></a>
					</div>
					<div id="clear"></div>
					<table id="TMaterial" class="display dataTable" cellspacing="0">
						<thead>						
							<tr>
								<th style="width: 10px;" class="dt-middle ">No</th>
								<th style="width: 70px;" class="dt-middle ">耀升编号</th>
								<!-- th style="width: 100px;" class="dt-middle ">订单号</th> -->
								<th style="width: 70px;" class="dt-middle ">PI编号</th>
								<th style="width: 60px;" class="dt-middle ">订单交期</th>
								<th style="width: 120px;" class="dt-middle ">产品编号</th>
								<th class="dt-middle ">产品名称</th>
								<th style="width: 40px;" class="dt-middle ">数量</th>
								<th style="width: 50px;" class="dt-middle ">单价</th>
								<th style="width: 80px;" class="dt-middle ">销售总价</th>
								<th style="width: 60px;" class="dt-middle ">业务组</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
