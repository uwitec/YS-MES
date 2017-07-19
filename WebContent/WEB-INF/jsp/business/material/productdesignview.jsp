<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
<title>产品设计明细-查看</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<%@ include file="../../common/common2.jsp"%>
</head>
<body>
<div id="container">
<div id="main">
	
<form:form modelAttribute="formModel" method="POST" 
	id="form" name="form"   autocomplete="off">
	
	<input type="hidden" id="PIId" value="${PIId}" />
	<input type="hidden" id="productDetailId" value="${product.productDetailId}" />
	<form:hidden path="productDesign.ysid"  value="${product.YSId}" />
	<form:hidden path="productDesign.productid"  value="${product.productId}" />
	<form:hidden path="productDesign.productdetailid"  value="${product.productDetailId}" />
	<form:hidden path="productDesign.recordid"  value="${product.recordId}" />
	
<fieldset>
	<legend>做单资料</legend>

	<table class="form" >		
		<tr>
			<td class="label" style="width: 100px;">耀升编号：</td>
			<td style="width: 150px;">${product.YSId}</td>
								
			<td class="label" style="width: 100px;">产品编号：</td>
			<td style="width: 150px;">${product.productId}</td>
			
			<td class="label" style="width: 100px;">产品名称：</td>
			<td>${product.materialName}</td>
		</tr>
		<tr>				
			<td class="label">交货时间：</td>
			<td>${product.deliveryDate}</td>				
								
			<td class="label">交货数量：</td>
			<td>${product.quantity}</td>
			
			<td class="label">封样数量：</td>
			<td>${product.sealedSample}</td>		
		</tr>
		<tr>
			<td class="label">版本类别：</td>
			<td>${product.productClassify}</td>
			<td class="label" >电池包数量：</td>
			<td>${product.batteryPack}</td>
								
			<td class="label">充电器：</td>
			<td>${product.chargerType}</td>
				
		</tr>
		<tr>		
			<td class="label">包装描述：</td>
			<td colspan="5">${product.packageDescription }</td>
		</tr>
	</table>
	
	<div class="action" style="text-align: right;">
		<button type="button" id="doEdit" class="DTTT_button" >编辑</button>
		<button type="button" id="goBack" class="DTTT_button">返回</button>
	</div>
</fieldset>	

<div id="tabs" style="padding: 0px;white-space: nowrap;">
	<ul>
		<li><a href="#tabs-1" class="tabs1">产品信息</a></li>
		<li><a href="#tabs-2" class="tabs2">标贴及文字印刷</a></li>
		<li><a href="#tabs-3" class="tabs3">包装</a></li>
	</ul>

	<div id="tabs-1" style="padding: 5px;">
		<fieldset>
			<legend>机器配置</legend>
			<div class="list">
			<table id="machineConfiguration" class="display" style="width:100%">
				<thead>				
					<tr>
						<th class="dt-center" width="1px">No</th>
						<th class="dt-center" style="width:60px">名称</th>
						<th class="dt-center" style="width:120px">ERP编码</th>
						<th class="dt-center" >产品名称</th>
						<th class="dt-center" style="width:105px">采购方</th>
						<th class="dt-center" style="width:255px">备注</th>
					</tr>
				</thead>
			</table>
			</div>
		</fieldset>
		<fieldset>
			<span class="tablename">产品图片</span>&nbsp;<button type="button" id="addProductPhoto" class="DTTT_button">添加图片</button>
			<div class="list">
				<div class="" id="subidDiv" style="overflow: auto;height: 360px;">
					<table id="productPhoto" style="width:100%">
						<tbody><tr class="photo"><td></td><td></td></tr></tbody>
					</table>
				</div>
			</div>	
		</fieldset>
		<fieldset>
			<legend>塑料制品</legend>
			<div class="list">
			<table id="plastic" class="display" style="width:100%">
				<thead>				
					<tr>
						<th width="1px">No</th>
						<th class="dt-center" style="width:60px">名称</th>
						<th class="dt-center" style="width:120px">ERP编码</th>
						<th class="dt-center" >产品名称</th>
						<th class="dt-center" style="width:40px">材质</th>
						<th class="dt-center" style="width:60px">颜色</th>
						<th class="dt-center" style="width:60px">备注</th>
					</tr>
				</thead>			
			</table>
			</div>
		</fieldset>
		<fieldset>
			<legend>配件清单</legend>
			<div class="list">
			<table id="accessory" class="display"  style="width:100%">
				<thead>				
					<tr>
						<th width="1px">No</th>
						<th class="dt-center" style="width:60px">配件名称</th>
						<th class="dt-center" style="width:40px">材质</th>
						<th class="dt-center" style="width:60px">加工方式</th>
						<th class="dt-center" style="width:60px">规格描述</th>
						<th class="dt-center" style="width:60px">备注</th>
					</tr>
				</thead>			
			</table>
			</div>
		</fieldset>
		<fieldset>
			<span class="tablename">产品收纳</span>&nbsp;<button type="button" id="addStoragePhoto" class="DTTT_button">添加图片</button>
			<div class="list">
				<div class="" id="subidDiv" style="overflow: auto;height: 360px;">
					<table id="storagePhoto" style="width:100%">
						<tbody><tr class="photo"><td></td><td></td></tr></tbody>
					</table>
				</div>
			</div>	
		</fieldset>
	</div>
	
	<div id="tabs-2" style="padding: 5px;">
	<fieldset>
	<legend>标贴-描述</legend>
	<div class="list">
	<table id="labelT" class="display"  style="width:100%">
		<thead>				
			<tr>
				<th width="1px">No</th>
				<th class="dt-center" style="width:60px">配件名称</th>
				<th class="dt-center" style="width:40px">材质及要求</th>
				<th class="dt-center" style="width:60px">尺寸</th>
				<th class="dt-center" style="width:60px">备注</th>
			</tr>
		</thead>			
	</table>
	</div><br/>
	<span class="tablename">标贴-图片</span>&nbsp;<button type="button" id="addLabelPhoto" class="DTTT_button">添加图片</button>
	<div class="list">
		<div class="" id="subidDiv" style="overflow: auto;height: 360px;">
			<table id="labelPhoto" style="width:100%">
				<tbody><tr class="photo"><td></td><td></td></tr></tbody>
			</table>
		</div>
	</div>	
</fieldset>
<fieldset>
	<legend>文字印刷</legend>
	<div class="list">
	<table id="textPrint" class="display" style="width:100%">
		<thead>				
			<tr>
				<th width="1px">No</th>
				<th class="dt-center" style="width:60px">配件名称</th>
				<th class="dt-center" style="width:40px">材质</th>
				<th class="dt-center" style="width:60px">尺寸</th>
				<th class="dt-center" style="width:60px">颜色</th>
				<th class="dt-center" style="width:60px">文件</th>
			</tr>
		</thead>			
	</table>
	</div>
</fieldset>
	</div>
	<div id="tabs-3" style="padding: 5px;">
	<fieldset>
	<legend>包装描述</legend>
	<div class="list">
	<table id="package" class="display" style="width:100%">
		<thead>				
			<tr>
				<th style="width:1px">No</th>
				<th class="dt-center" style="width:100px">名称</th>
				<th class="dt-center" style="width:300px">材质</th>
				<th class="dt-center" style="width:100px">装箱数量</th>
				<th class="dt-center" style="width:200px">尺寸</th>
				<th class="dt-center" style="width:60px">备注</th>
			</tr>
		</thead>		
	</table>
	</div>
	<br/>
	<span class="tablename">包装描述-图片</span>&nbsp;<button type="button" id="addPackagePhoto" class="DTTT_button">添加图片</button>
	<div class="list">
	<table id="packagePhoto" style="width:100%">
		<tbody><tr class="photo"><td></td><td></td></tr></tbody>
	</table>
	</div>	
</fieldset>
	</div>
</div>
		
<div style="clear: both"></div>		
	
</form:form>
</div>
</div>
<script type="text/javascript">

$(document).ready(function() {
			
	$( "#tabs" ).tabs();	
	//$("#tabs").tabs({ active: 2 });  //选择第二个tab为默认显示。

	productPhotoView();//产品图片
	productStoragePhotoView();//产品收纳
	machineConfigurationView();//机器配置
	plasticView();//塑料制品
	accessoryView();//配件清单
	labelView();//标贴
	textPrintView();//文字印刷
	packageView();//包装描述	

	$("#doEdit").click(function() {
		var PIId=$('#PIId').val();
		var YSId=$('#productDesign\\.ysid').val();
		var productDetailId=$('#productDesign\\.productdetailid').val();
		var url = '${ctx}/business/productDesign?methodtype=edit&YSId=' + YSId+"&productDetailId="+productDetailId+"&PIId="+PIId;
		location.href = url;	 
	});
	
	$("#goBack").click(function() {
		var PIId=$('#PIId').val();
		var url = '${ctx}/business/order?methodtype=detailView&PIId=' + PIId;
		location.href = url;	
	});

	//产品图片添加位置,                                                                                                                                                                                        
	var productIndex = 1;
	$("#addProductPhoto").click(function() {
		
		var row = $("#productPhoto tbody tr.photo").length - 1;

		//从 1 开始
		var trHtml = addPhotoRow('uploadProductPhoto',productIndex);		

		$('#productPhoto tr.photo:eq('+row+')').after(trHtml);	
		productIndex++;	
		productIndex++;	
		alert("row:"+row+"-----"+"::productIndex:"+productIndex)	
						
		
	});
	
	//产品收纳图片添加位置,
	var storageIndex = 1;
	$("#addStoragePhoto").click(function() {
		
		var row = $("#storagePhoto tbody tr.photo").length - 1;

		var trHtml = addPhotoRow('uploadStoragePhoto',storageIndex);		

		$('#storagePhoto tr.photo:eq('+row+')').after(trHtml);	
		storageIndex++;
		storageIndex++;
						
		
	});
	
	//包装图片添加位置,
	var packageIndex = 1;
	$("#addPackagePhoto").click(function() {
		
		var row = $("#packagePhoto tbody tr.photo").length - 1;

		var trHtml = addPhotoRow('uploadPackagePhoto',packageIndex);	
		
		$('#packagePhoto tr.photo:eq('+row+')').after(trHtml);	
		packageIndex++;	
		packageIndex++;	
		
	});
	
	//标贴图片添加位置,
	var labelIndex = 0;
	$("#addLabelPhoto").click(function() {
		
		var row = $("#labelPhoto tbody tr.photo").length - 1;

		var trHtml = addPhotoRow('uploadLabelPhoto',labelIndex);		

		$('#labelPhoto tr.photo:eq('+row+')').after(trHtml);	
		labelIndex++;
		labelIndex++;
						
		
	});
	
	/*
	$(".tabs1").click(function(){
		
	});
	$(".tabs2").click(function(){
		
	});
	$(".tabs3").click(function(){

		//packageView();//包装描述	
		//$('#package').DataTable().ajax.reload(null,false);
	});
	*/
    	
});


function productPhotoView() {

	var productDetailId = $("#productDetailId").val();
	var YSId = $("#productDesign\\.ysid").val();
	var productId = $("#productDesign\\.productid").val();

	$.ajax({
		"url" :"${ctx}/business/productDesign?methodtype=getProductPhoto&productDetailId="+productDetailId+"&YSId="+YSId+"&productId="+productId,	
		"datatype": "json", 
		"contentType": "application/json; charset=utf-8",
		"type" : "POST",
		"data" : null,
		success: function(data){
				
			var countData = data["productFileCount"];
			//alert(countData)
			photoView('productPhoto','uploadProductPhoto',countData,data['productFileList'])		
		},
		 error:function(XMLHttpRequest, textStatus, errorThrown){
            }
	});
	
}//产品图片


function productStoragePhotoView() {

	var productDetailId = $("#productDetailId").val();
	var YSId = $("#productDesign\\.ysid").val();
	var productId = $("#productDesign\\.productid").val();

	$.ajax({
		"url" :"${ctx}/business/productDesign?methodtype=getProductStoragePhoto&productDetailId="+productDetailId+"&YSId="+YSId+"&productId="+productId,	
		"datatype": "json", 
		"contentType": "application/json; charset=utf-8",
		"type" : "POST",
		"data" : null,
		success: function(data){
				
			var countData = data["storageFileCount"];
			//alert(countData)
			photoView('storagePhoto','uploadStoragePhoto',countData,data['storageFileList'])		
		},
		 error:function(XMLHttpRequest, textStatus, errorThrown){
            }
	});
	
}//产品收纳

function machineConfigurationView() {

	var productDetailId = $("#productDetailId").val();
	var table = $('#machineConfiguration').dataTable();
	if(table) {
		table.fnDestroy();
	}
	
	var t = $('#machineConfiguration').DataTable({
		"paging": false,
		"processing" : false,
		"serverSide" : true,
		"stateSave" : false,
		"searching" : false,
		//"pagingType" : "full_numbers",
		"retrieve" : false,
		"bSort":false,
		"async" : false,
		"sAjaxSource" : "${ctx}/business/productDesign?methodtype=getMachineConfiguration&productDetailId="+productDetailId,	
		"fnServerData" : function(sSource, aoData, fnCallback) {
			$.ajax({
				"url" : sSource,
				"datatype": "json", 
				"contentType": "application/json; charset=utf-8",
				"type" : "POST",
				"data" : null,
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
			{"data": null,"className" : 'td-center'},
			{"data": "componentName"},
			{"data": "materialId"},
			{"data": "materialName"},
			{"data": "purchaser","className" : 'td-center'},
			{"data": "remark"},
		 ],
		
	});

			
	t.on('click', 'tr', function() {

		if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            t.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
		
	});
	
	t.on('order.dt search.dt draw.dt', function() {
		t.column(0, {
			search : 'applied',
			order : 'applied'
		}).nodes().each(function(cell, i) {
			cell.innerHTML = i + 1;
		});
	}).draw();
	
}//机器配置


function plasticView() {

	var productDetailId = $("#productDetailId").val();
	var table = $('#plastic').dataTable();
	if(table) {
		//table.fnClearTable();
		table.fnDestroy();
	}
	var t = $('#plastic').DataTable({
		"paging": false,
		"processing" : false,
		"serverSide" : true,
		"stateSave" : false,
		"searching" : false,
		//"pagingType" : "full_numbers",
		"retrieve" : false,
		"bSort":false,
		"async" : false,
		"sAjaxSource" : "${ctx}/business/productDesign?methodtype=getPlastic&productDetailId="+productDetailId,	
		"fnServerData" : function(sSource, aoData, fnCallback) {
			$.ajax({
				"url" : sSource,
				"datatype": "json", 
				"contentType": "application/json; charset=utf-8",
				"type" : "POST",
				"data" : null,
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
			{"data": null,"className" : 'td-center'},
			{"data": "componentName"},
			{"data": "materialId"},
			{"data": "materialName"},
			{"data": "materialQuality"},
			{"data": "color"},
			{"data": "remark"},
		 ],
		
	});

			
	t.on('click', 'tr', function() {

		if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            t.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
		
	});
	
	t.on('order.dt search.dt draw.dt', function() {
		t.column(0, {
			search : 'applied',
			order : 'applied'
		}).nodes().each(function(cell, i) {
			cell.innerHTML = i + 1;
		});
	}).draw();
	
}//塑料制品

function accessoryView() {

	var productDetailId = $("#productDetailId").val();

	var table = $('#accessory').dataTable();
	if(table) {
		//table.fnClearTable();
		table.fnDestroy();
	}
	var t = $('#accessory').DataTable({
		"paging": false,
		"processing" : false,
		"serverSide" : true,
		"stateSave" : false,
		"searching" : false,
		//"pagingType" : "full_numbers",
		"retrieve" : false,
		"bSort":false,
		"async" : false,
		"sAjaxSource" : "${ctx}/business/productDesign?methodtype=getAccessory&productDetailId="+productDetailId,	
		"fnServerData" : function(sSource, aoData, fnCallback) {
			$.ajax({
				"url" : sSource,
				"datatype": "json", 
				"contentType": "application/json; charset=utf-8",
				"type" : "POST",
				"data" : null,
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
			{"data": null,"className" : 'td-center'},
			{"data": "componentName"},
			{"data": "materialQuality"},
			{"data": "process"},
			{"data": "specification"},
			{"data": "remark"},
		 ],
		
	});

			
	t.on('click', 'tr', function() {

		if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            t.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
		
	});
	
	t.on('order.dt search.dt draw.dt', function() {
		t.column(0, {
			search : 'applied',
			order : 'applied'
		}).nodes().each(function(cell, i) {
			cell.innerHTML = i + 1;
		});
	}).draw();
	
}//配件清单


function labelView() {

	var productDetailId = $("#productDetailId").val();
	var YSId = $("#productDesign\\.ysid").val();
	var productId = $("#productDesign\\.productid").val();

	var table = $('#labelT').dataTable();
	if(table) {
		//table.fnClearTable();
		table.fnDestroy();
	}
	var t = $('#labelT').DataTable({
		"paging": false,
		"processing" : false,
		"serverSide" : false,
		"stateSave" : false,
		"searching" : false,
		//"pagingType" : "full_numbers",
		"retrieve" : false,
		"bSort":false,
		"async" : false,
		"bAutoWidth":false,
		"sAjaxSource" : "${ctx}/business/productDesign?methodtype=getLabel&productDetailId="+productDetailId+"&YSId="+YSId+"&productId="+productId,	
		"fnServerData" : function(sSource, aoData, fnCallback) {
			$.ajax({
				"url" : sSource,
				"datatype": "json", 
				"contentType": "application/json; charset=utf-8",
				"type" : "POST",
				"data" : null,
				success: function(data){
					fnCallback(data);			
					var countData = data["labelFileCount"];
					//alert(countData)
					photoView('labelPhoto','uploadLabelPhoto',countData,data['labelFileList'])		
				},
				 error:function(XMLHttpRequest, textStatus, errorThrown){
	             }
			})
		},
       	"language": {
       		"url":"${ctx}/plugins/datatables/chinese.json"
       	},
		"columns": [
			{"data": null,"className" : 'td-center'},
			{"data": "componentName"},
			{"data": "materialQuality"},
			{"data": "size"},
			{"data": "remark"},
		 ],
		
	});

			
	t.on('click', 'tr', function() {

		if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            t.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
		
	});
	
	t.on('order.dt search.dt draw.dt', function() {
		t.column(0, {
			search : 'applied',
			order : 'applied'
		}).nodes().each(function(cell, i) {
			cell.innerHTML = i + 1;
		});
	}).draw();
	
}//标贴

function textPrintView() {

	var productDetailId = $("#productDetailId").val();
	var table = $('#textPrint').dataTable();
	if(table) {
		//table.fnClearTable();
		table.fnDestroy();
	}
	var text = $('#textPrint').DataTable({
		"paging": false,
		"processing" : false,
		"serverSide" : true,
		"stateSave" : false,
		"searching" : false,
		//"pagingType" : "full_numbers",
		"retrieve" : false,
		"bSort":false,
		"async" : false,
		"sAjaxSource" : "${ctx}/business/productDesign?methodtype=getTextPrint&productDetailId="+productDetailId,	
		"fnServerData" : function(sSource, aoData, fnCallback) {
			$.ajax({
				"url" : sSource,
				"datatype": "json", 
				"contentType": "application/json; charset=utf-8",
				"type" : "POST",
				"data" : null,
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
			{"data": null,"className" : 'td-center'},
			{"data": "componentName"},
			{"data": "materialQuality"},
			{"data": "size"},
			{"data": "color"},
			{"data": null},
		 ],
		
	});

			
	text.on('click', 'tr', function() {

		if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            text.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
		
	});
	
	text.on('order.dt search.dt draw.dt', function() {
		text.column(0, {
			search : 'applied',
			order : 'applied'
		}).nodes().each(function(cell, i) {
			cell.innerHTML = i + 1;
		});
	}).draw();
	
}//文字印刷


function packageView() {
	var productDetailId = $("#productDetailId").val();
	var YSId = $("#productDesign\\.ysid").val();
	var productId = $("#productDesign\\.productid").val();
	
	var table = $('#package').dataTable();
	if(table) {
		//table.fnClearTable(false);
		table.fnDestroy();
	}
	var t = $('#package').DataTable({
		"paging": false,
		"processing" : false,
		"serverSide" : false,
		"stateSave" : false,
		"searching" : false,
		//"pagingType" : "full_numbers",
		"retrieve" : false,
		"bSort":false,
		"async" : false,
		"bAutoWidth":false,
		//"sAjaxSource" : "${ctx}/business/productDesign?methodtype=getPackage",	
		"sAjaxSource" : "${ctx}/business/productDesign?methodtype=getPackage&productDetailId="+productDetailId+"&YSId="+YSId+"&productId="+productId,
		"fnServerData" : function(sSource, aoData, fnCallback) {
			$.ajax({
				"url" : sSource,
				"datatype": "json", 
				"contentType": "application/json; charset=utf-8",
				"type" : "POST",
				"data" : null,
				success: function(data){
					fnCallback(data);	

					var countData = data["packageFileCount"];
					photoView('packagePhoto','uploadPackagePhoto',countData,data['packageFileList'])
					
				},
				 error:function(XMLHttpRequest, textStatus, errorThrown){
	             }
			})
		},
       	"language": {
       		"url":"${ctx}/plugins/datatables/chinese.json"
       	},
		"columns": [
			{"data": null,"className" : 'td-center'},
			{"data": "componentName"},
			{"data": "materialQuality"},
			{"data": "packingQty"},
			{"data": "size"},
			{"data": "remark"},
		 ],
		
	});

			
	t.on('click', 'tr', function() {

		if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            t.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
		
	});
	
	t.on('order.dt search.dt draw.dt', function() {
		t.column(0, {
			search : 'applied',
			order : 'applied'
		}).nodes().each(function(cell, i) {
			cell.innerHTML = i + 1;
		});
	}).draw();
	
}//包装描述


</script>
<script type="text/javascript">

function showUploadItem(item) {
	$("#uploadFile"+item).show();
	$("#uploadFile"+item).show();
	
	
}

function uploadPhoto(tdTable,id) {
		
    var url = '${ctx}/business/productDesignPhotoUpload'+id+'?methodtype='+tdTable+'&id='+id;
    //alert(url)
    $("#form").ajaxSubmit({
		type: "POST",
		url:url,	
		data:$('#form').serialize(),// 你的formid
		dataType: 'json',
	    success: function(data){
	     	if(data.result == '0'){
				$('#imgFile'+tdTable+id).attr('src', '${ctx}' + data.path);	
				$('#uploadFile'+tdTable+id).remove();	
				$('#deleteFile'+tdTable+id).show();
				
	   		 }
	    	else{
	    		alert(data.message);
	    	}
		},
        error : function(XMLHttpRequest, textStatus, errorThrown) {  
			
        } 		
	});	
}



function photoView(id,tdTable,count,data){
	var countView = (count%2 ==0) ?count:count+1;  //判断是否能整除2
	//alert("id:"+id+"--count:"+count+"--countView:"+countView)
	var row =0;
	
	for(var index=0;index<count;index++){
		
		var path = '${ctx}'+data[index];
		//alert(index+"::::::::::::"+path)
		var trHtml = '';
		
		trHtml+='<tr style="text-align: center;" class="photo">';	
		
		trHtml+='<td>';
		trHtml+='<table style="width:400px;margin: auto;" class="form" id="tb'+index+'">';
		trHtml+='<tr style="background: #d4d0d0;height: 35px;">';
		trHtml+='<td></td>';
		trHtml+='<td width="50px"><a id="uploadFile'+index+'" href="###" onclick="showUploadItem('+index+');">删除</a></td>';
		trHtml+="</tr>";
		trHtml+='<tr><td colspan="2"  style="height:300px;"><img id="imgFile'+index+'" src="'+path+'" style="max-width: 400px;max-height:300px"  /></td>';
		trHtml+='</tr>';
		trHtml+='</table>';
		trHtml+='</td>';
		
		index++;
		if(((index+1) == countView) && (count < countView)){
			//因为是偶数循环,所以奇数张图片的最后一张为空
			path= '${ctx}'+"/images/blankDemo.png";

			var trHtmlOdd='<td><div id="uploadFile'+tdTable+index+'" ><input type="file"  id="photoFile" name="photoFile" onchange="uploadPhoto('+'\''+tdTable+'\''+','+index+');" accept="image/*" style="max-width: 250px;" /></div></td>';
			trHtmlOdd+='<td width="50px"><div id="deleteFile'+tdTable+index+'" style="display:none"><a href="###" onclick=\"deletePhoto()\">删除</a></div></td>';
			trHtmlOdd+="</tr>";
			trHtmlOdd+='<tr><td colspan="2"  style="height:300px;"><img id="imgFile'+tdTable+index+'" src="'+path+'" style="max-width: 400px;max-height:300px"  /></td>';			
		}else{
			path = '${ctx}'+data[index];
			var trHtmlOdd='<td></td>';
			trHtmlOdd+='<td width="50px"><a id="uploadFile1'+index+'" href="###" onclick="showUploadItem('+index+');">删除</a></td>';
			trHtmlOdd+="</tr>";
			trHtmlOdd+='<tr><td colspan="2"  style="height:300px;"><img id="imgFile'+index+'" src="'+path+'" style="max-width: 400px;max-height:300px"  /></td>';
		}			
			
		trHtml+='<td>';
		trHtml+='<table style="width:400px;margin: auto;" class="form">';
		trHtml+='<tr style="background: #d4d0d0;height: 35px;">';
		trHtml+=trHtmlOdd;
		trHtml+='</tr>';
		trHtml+='</table>';
		trHtml+='</td>';		
		trHtml+="</tr>";

		$('#'+id+' tr.photo:eq('+row+')').after(trHtml);	
		row++;
		
	}
	
}

function addPhotoRow(tdTable,index){
	
	for(var i=0;i<1;i++){
		
		var path = '${ctx}'+"/images/blankDemo.png";
		var trHtml = '';
		
		trHtml+='<tr style="text-align: center;" class="photo">';	
		
		trHtml+='<td>';
		trHtml+='<table style="width:400px;margin: auto;" class="form" id="tb'+index+'">';
		trHtml+='<tr style="background: #d4d0d0;height: 35px;">';
		trHtml+='<td><div id="uploadFile'+tdTable+index+'" ><input type="file"  id="photoFile" name="photoFile" onchange="uploadPhoto('+'\''+tdTable+'\''+','+index+');" accept="image/*" style="max-width: 250px;" /></div></td>';
		//trHtml+='<td><div id="uploadFile'+index+'" ></div></td>';
		trHtml+='<td width="50px"><div id="deleteFile'+tdTable+index+'" style="display:none"><a href="###" onclick=\"deletePhoto()\">删除</a></div></td>';
		trHtml+="</tr>";
		trHtml+='<tr><td colspan="2"  style="height:300px;"><img id="imgFile'+tdTable+index+'" src="'+path+'" style="max-width: 400px;max-height:300px"  /></td>';
		trHtml+='</tr>';
		trHtml+='</table>';
		trHtml+='</td>';
		
		index++;
						
		trHtml+='<td>';
		trHtml+='<table style="width:400px;margin: auto;" class="form">';
		trHtml+='<tr style="background: #d4d0d0;height: 35px;">';
		trHtml+='<td><div id="uploadFile'+tdTable+index+'" ><input type="file"  id="photoFile" name="photoFile" onchange="uploadPhoto('+'\''+tdTable+'\''+','+index+');" accept="image/*" style="max-width: 250px;" /></div></td>';
		trHtml+='<td width="50px"><div id="deleteFile'+tdTable+index+'" style="display:none"><a href="###" onclick=\"deletePhoto()\">删除</a></div></td>';
		trHtml+="</tr>";
		trHtml+='<tr><td colspan="2"  style="height:300px;"><img id="imgFile'+tdTable+index+'" src="'+path+'" style="max-width: 400px;max-height:300px"  /></td>';
		trHtml+='</tr>';
		trHtml+='</table>';
		trHtml+='</td>';		
		trHtml+="</tr>";
		
		//index++;		
	}//for		
	
	return trHtml;
}
</script>
	</body>
</html>