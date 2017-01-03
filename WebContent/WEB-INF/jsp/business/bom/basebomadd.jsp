<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE HTML>
<html>
<head>
<title>新建基础BOM</title>
<%@ include file="../../common/common2.jsp"%>
<script type="text/javascript">

	var counter = 0;
	
	//Form序列化后转为AJAX可提交的JSON格式。
	$.fn.serializeObject = function() {
		var o = {};
		var a = this.serializeArray();
		$.each(a, function() {
			if (o[this.name] !== undefined) {
				if (!o[this.name].push) {
					o[this.name] = [ o[this.name] ];
				}
				o[this.name].push(this.value || '');
			} else {
				o[this.name] = this.value || '';
			}
		});
		return o;
	};
	
	$.fn.dataTable.TableTools.buttons.add_rows = $
	.extend(
			true,
			{},
			$.fn.dataTable.TableTools.buttonBase,
			{
				"fnClick" : function(button) {
					
					var rowIndex = counter;
					
					for (var i=0;i<10;i++){
						
				var rowNode = $('#example')
							.DataTable()
							.row
							.add(
							  [
								'<td></td>',
								'<td><input type="text"   name="attributeList1"  class="attributeList1">'+
									'<input type="hidden" name="bomDetailLines['+rowIndex+'].materialid" id="bomDetailLines'+rowIndex+'.materialid" /></td>',
								'<td><span></span></td>',
								'<td><input type="text"   name="attributeList2"  class="attributeList2" style="width:80px"> '+
									'<input type="hidden" name="bomDetailLines['+rowIndex+'].supplierid" id="bomDetailLines'+rowIndex+'.supplierid" /></td>',
								'<td><input type="text"   name="bomDetailLines['+rowIndex+'].quantity"   id="bomDetailLines'+rowIndex+'.quantity"   class="cash"  style="width:50px"/></td>',
								'<td><input type="text"   name="bomDetailLines['+rowIndex+'].price"      id="bomDetailLines'+rowIndex+'.price"      class="cash mini" /></td>',
								'<td><span></span><input type="hidden"   name="bomDetailLines['+rowIndex+'].totalprice" id="bomDetailLines'+rowIndex+'.totalprice"/><input type="hidden" id="labor"></td>',
								]).draw();
						
						rowIndex ++;						
					}					
					counter += 1;
					
					autocomplete();

					//重设显示窗口(iframe)高度
					iFramAutoSroll();
						
					foucsInit();
				}
			});

	$.fn.dataTable.TableTools.buttons.reset = $.extend(true, {},
		$.fn.dataTable.TableTools.buttonBase, {
		"fnClick" : function(button) {
			
			var t=$('#example').DataTable();
			
			rowIndex = t.row('.selected').index();
			
			var str = true;
			$("input[name='numCheck']").each(function(){
				if ($(this).prop('checked')) {
					var n = $(this).parents("tr").index();  // 获取checkbox所在行的顺序
					$('#example tbody').find("tr:eq("+n+")").remove();
					str = false;
				}
			});
			
			if(str){
				$().toastmessage('showWarningToast', "请选择要删除的数据。");
			}else{
				$().toastmessage('showNoticeToast', "删除成功。");	
				costAcount();				
			}
				
		}
	});
	
	function ajax() {
		var height = $(window).height();
		height = height - 370;

		var t = $('#example').DataTable({
			
			"processing" : false,
			"retrieve"   : true,
			"stateSave"  : true,
	        "paging"    : false,
	        "pageLength": 50,
	        "ordering"  : false,

			dom : 'T<"clear">rt',

			"tableTools" : {

				//"sSwfPath" : "${ctx}/plugins/datatablesTools/swf/copy_csv_xls_pdf.swf",

				"aButtons" : [ {
					"sExtends" : "add_rows",
					"sButtonText" : "追加新行"
				},
				{
					"sExtends" : "reset",
					"sButtonText" : "删除行"
				}],
			},
			
			"columns" : [ 
			        	{"className":"dt-body-center"
					}, {
					}, {								
					}, {				
					}, {"className":"td-right"				
					}, {"className":"td-right"				
					}, {"className":"td-right"				
					}			
				]
			
		}).draw();

		
		t.on('blur', 'tr td:nth-child(2),tr td:nth-child(4)',function() {
			
			var currValue = $(this).find("input:text").val().trim();

            $(this).find("input:text").removeClass('bgwhite');
            
            if(currValue =="" ){
            	
            	 $(this).find("input:text").addClass('error');
            }else{
            	 $(this).find("input:text").addClass('bgnone');
            }
			
		});

		
		t.on('blur', 'tr td:nth-child(5),tr td:nth-child(6)',function() {
			
           $(this).find("input:text").removeClass('bgwhite').addClass('bgnone');

		});
				

		t.on('change', 'tr td:nth-child(5),tr td:nth-child(6)',function() {
			
			/*产品成本 = 各项累计
			人工成本 = H带头的ERP编号下的累加
			材料成本 = 产品成本 - 人工成本
			经管费 = 经管费率 x 产品成本
			核算成本 = 产品成本 + 经管费*/
			
            var $tds = $(this).parent().find("td");
			
            var $oMaterial  = $tds.eq(1).find("input:text");
            var $oQuantity  = $tds.eq(4).find("input");
			var $oThisPrice = $tds.eq(5).find("input");
			//var $oAmount1   = $tds.eq(6).find("input:hidden");
			var $oAmount2   = $tds.eq(6).find("span");
			var $oAmountd   = $tds.eq(6).find("input:last-child");//人工成本
			
			var materialId = $oMaterial.val();
			var fPrice = currencyToFloat($oThisPrice.val());		
			var fQuantity = currencyToFloat($oQuantity.val());	
			var fTotalNew = currencyToFloat(fPrice * fQuantity);
			var fAmountd  = fnLaborCost(materialId,fTotalNew);//人工成本

			var vPrice = float4ToCurrency(fPrice);	
			var vQuantity = floatToCurrency(fQuantity);
			var vTotalNew = float4ToCurrency(fTotalNew);
					
			//详情列表显示新的价格
			$oThisPrice.val(vPrice);					
			$oQuantity.val(vQuantity);	
			//$oAmount1.val(vTotalNew);	
			$oAmount2.html(vTotalNew);
			$oAmountd.val(fAmountd);

			//合计成本
			costAcount();
			
		});
			
		t.on('click', 'tr', function() {
			
			var rowIndex = $(this).context._DT_RowIndex; //行号			
			//alert(rowIndex);

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
				var num   = i + 1;
				var checkBox = "<input type=checkbox name='numCheck' id='numCheck' value='" + num + "' />";
				cell.innerHTML = num + checkBox;
			});
		}).draw();

	};//ajax()

	$(document).ready(function() {
		
		//$("#bomPlan\\.managementcostrate").val($("#bomPlan\\.managementcostrate option:eq(2)").val());
		
		ajax();
		
		autocomplete();
		
		//$('#example').DataTable().columns.adjust().draw();
		
		$("#goBack").click(
				function() {
					var url = '${ctx}/business/order';
					location.href = url;		
				});
		
		$("#update").click(function() {			
			$('#bomForm').attr("action", "${ctx}/business/bom?methodtype=baseBomInsert");
			$('#bomForm').submit();
		});
		

		$("#searchProductModel").click(function() {		
			var model = $('#productModel').val();
			var materialId = '${product.materialId}';
			var url = "${ctx}/business/bom?methodtype=searchProductModel&model="+model+"&materialId="+materialId;
			location.href = url;
		});
		

		//iFramAutoSroll();//重设显示窗口(iframe)高度		
		
		foucsInit();//input获取焦点初始化处理
		
		costAcount();//成本核算

		$(".DTTT_container").css('float','left');

	});	
	
	function fnLaborCost(materialId,cost){
		
		var laborCost = '0';
		
		//判断是否是人工成本
		if(materialId != '' && materialId.substring(0,1) == 'H')
			laborCost = cost;
		
		//alert('materialId:'+materialId+'--laborCost:'+laborCost);
		
		return laborCost;
	}
	
	function costAcount(){
		//计算该客户的销售总价,首先减去旧的		
		//产品成本=各项累计
		//人工成本=H带头的ERP编号下的累加
		//材料成本=产品成本-人工成本
		//经管费=经管费率x产品成本
		//核算成本=产品成本+经管费
			
		var laborCost   = laborCostSum();
		var productCost = productCostSum();
		
		var fmaterialCost,facoutCost;
		
		fmaterialCost = productCost - laborCost;
		facoutCost    = productCost * 1.1 * 1.02;

		$('#bomPlan\\.productcost').val(floatToCurrency(productCost));
		$('#bomPlan\\.laborcost').val(floatToCurrency(laborCost));
		$('#bomPlan\\.materialcost').val(floatToCurrency(fmaterialCost));
		$('#bomPlan\\.totalcost').val(floatToCurrency(facoutCost));
		//alert('labor:'+laborCost+'--product:'+productCost)
		
	}
	
	//列合计:总价
	function productCostSum(){

		var sum = 0;
		$('#example tbody tr').each (function (){
			
			var vtotal = $(this).find("td").eq(6).find("span").text();
			var ftotal = currencyToFloat(vtotal);
			
			sum = currencyToFloat(sum) + ftotal;			
		})
		return sum;
	}
	
	//列合计:人工成本
	function laborCostSum(){

		var sum = 0;
		$('#example tbody tr').each (function (){
			
			var vtotal = $(this).find("td").eq(6).find("input:last-child").val();
			var ftotal = currencyToFloat(vtotal);
			
			sum = currencyToFloat(sum) + ftotal;			
		})		
		return sum;
	}
	
</script>

</head>
<body>
<div id="container">
<!--主工作区,编辑页面或查询显示页面-->
<div id="main">

	<form:form modelAttribute="bomForm" method="POST"
		id="bomForm" name="bomForm"  autocomplete="off">
		
		<input type="hidden" id="tmpMaterialId" />	
		
		<fieldset>
			<legend>基础BOM</legend>
			<table class="form" id="table_form" style="margin-top: -4px;">
				<tr>
					<td class="label" width="100px">BOM编号：</td>					
					<td width="150px">${bomForm.bomPlan.bomid}
						<form:hidden path="bomPlan.bomid"/></td>
						
					<td class="label" width="100px">产品编号：</td>				
					<td width="150px">${product.materialId}
						<form:hidden path="bomPlan.materialid" /></td>					
					
					<td class="label" width="100px">产品名称：</td>
					<td>${product.materialName }</td>
				</tr>
				<tr>
					<td class="label">机器型号：</td>
					<td>${product.productModel }</td>
					
					<td class="label">客户简称：</td>
					<td>${product.shortName }</td>
					
					<td class="label">客户名称：</td>
					<td>${product.customerName }</td>						
					
				</tr>
												
			</table>
			
			<table class="form" id="table_form2" style="margin-top: 6px;">
				
				<tr>
					<td class="td-center"><label>材料成本<br>A</label></td>	
					<td class="td-center"><label>人工<br>B</label></td>
					<td class="td-center"><label>基础成本<br>E=A＋B</label></td>
					<td class="td-center"><label>核算成本<br>F=E*1.1*1.02</label></td>
				</tr>	
				<tr>			
					<td class="td-center">
						<form:input path="bomPlan.materialcost" class="read-only cash" /></td>
					<td class="td-center">
						<form:input path="bomPlan.laborcost" class="read-only cash" value="${bomPlan.laborCost}" /></td>
					<td class="td-center">
						<form:input path="bomPlan.productcost" class="read-only cash" value="${bomPlan.productCost}"/></td>
					<td class="td-center">
						<form:input path="bomPlan.totalcost" class="read-only cash" value="${bomPlan.totalCost}"/></td>
				</tr>								
			</table>
	</fieldset>
		
	<div style="margin: 0px 0px 0px 0px; float:left; width:70%;padding-left: 15px;" >
		查找机器型号：
		<input type="text" id="productModel" class="short" style="height: 25px;padding-left: 10px;" value="${productMode }"/>
		<button type="button" id="searchProductModel" class="DTTT_button">查询</button>
	</div>
	<div style="margin: -3px 10px 0px 5px;float:right; padding:0px;">	
			<button type="button" id="update" class="DTTT_button">保存</button>
			<button type="button" id="goBack" class="DTTT_button">返回</button>
	</div>
	<fieldset>
		<div class="list" style="margin-top: -4px;">
		
		<table id="example" class="display">
			<thead>				
			<tr>
				<th width="1px">No</th>
				<th class="dt-center" width="80px">物料编码</th>
				<th class="dt-center" >物料名称</th>
				<th class="dt-center" width="100px">供应商编号</th>
				<th class="dt-center" width="80px">用量</th>
				<th class="dt-center" width="100px">本次单价</th>
				<th class="dt-center" width="100px">总价</th>
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
					<th></th>
				</tr>
			</tfoot>
		<tbody>
		
		<c:if test="${fn:length(materialDetail) eq 0}" >
						
			<c:forEach var="i" begin="0" end="99" step="1">	
				<tr>				
					<td></td>
					<td><input type="text" name="attributeList1" class="attributeList1" />
						<form:hidden path="bomDetailLines[${i}].materialid"/></td>								
					<td><span></span></td>	
					<td><input type="text" name="attributeList2" class="attributeList2" style="width:80px"/>
						<form:hidden path="bomDetailLines[${i}].supplierid"/></td>
					<td><form:input path="bomDetailLines[${i}].quantity" class="cash"  style="width:50px"/></td>							
					<td><form:input path="bomDetailLines[${i}].price" class="cash mini"  /></td>						
					<td><span></span>
						<form:hidden path="bomDetailLines[${i}].totalprice"/></td>					
				</tr>				
				<script type="text/javascript">
					counter++;
				</script>
					
			</c:forEach>
		</c:if>
		
		<c:if test="${fn:length(materialDetail) > 0}" >
						
			<c:forEach var="detail" items="${materialDetail}" varStatus='status' >		

				
<tr>
	<td></td>
	<td><input type="text" name="attributeList1" class="attributeList1" value="${detail.materialId}"/>
		<form:hidden path="bomDetailLines[${status.index}].materialid"  value="${detail.materialId}"/></td>								
	<td><span id="name${status.index}">${detail.materialName}</span></td>
	<td><input type="text" name="attributeList2" class="attributeList2"  value="${detail.supplierId}" style="width:80px" />
		<form:hidden path="bomDetailLines[${status.index}].supplierid"  value="${detail.supplierId}" /></td>
	<td><form:input path="bomDetailLines[${status.index}].quantity" value="${detail.quantity}"  class="cash"  style="width:50px"/></td>							
	<td><form:input path="bomDetailLines[${status.index}].price"  value="${detail.price}" class="cash mini"  /></td>						
	<td><span id="total${status.index}">${detail.totalPrice}</span>
		<form:hidden path="bomDetailLines[${status.index}].totalprice"  value="${detail.totalPrice}"/>
		<input type="hidden" id="labor${status.index}"></td>	
	
	<form:hidden path="bomDetailLines[${status.index}].sourceprice"  value="${detail.price}" />	
</tr>

				<script type="text/javascript">
					var index = '${status.index}';
					var cost = '${detail.productcost}';
					var materialId = '${detail.materialId}';
					var materialName = '${detail.materialName}';
					var quantity = currencyToFloat('${detail.quantity}');
					var price =currencyToFloat( '${detail.price}');
					var totalPrice = quantity * price;
					var labor = fnLaborCost( materialId,totalPrice);
					$('#labor'+index).val(labor);
					$('#total'+index).html(totalPrice);
					$('#bomDetailLines'+index+'\\.totalprice').val(totalPrice);
					$('#name'+index).html(jQuery.fixedWidth(materialName,20));
					counter++;
				</script>
				
			</c:forEach>
		</c:if>
		</tbody>
	</table>
	</div>
	</fieldset>
		
</form:form>

</div>
</div>
</body>

<script type="text/javascript">

function autocomplete(){
	
	//物料选择
	$(".attributeList1").autocomplete({
		minLength : 2,
		autoFocus : false,
		source : function(request, response) {
			//alert(888);
			$
			.ajax({
				type : "POST",
				url : "${ctx}/business/bom?methodtype=getMaterialPriceList",
				dataType : "json",
				data : {
					key : request.term
				},
				success : function(data) {
					//alert(777);
					response($
						.map(
							data.data,
							function(item) {

								return {
									label : item.viewList,
									value : item.materialId,
									id : item.materialId,
									name : item.materialName,
									materialId : item.materialId,
									supplierId : item.supplierId,
									  minPrice : item.minPrice,
									 lastPrice : item.lastPrice,
									     price : item.price
								}
							}));
				},
				error : function(XMLHttpRequest,
						textStatus, errorThrown) {
					alert(XMLHttpRequest.status);
					alert(XMLHttpRequest.readyState);
					alert(textStatus);
					alert(errorThrown);
					alert("系统异常，请再试或和系统管理员联系。");
				}
			});
		},

		select : function(event, ui) {
			
			//产品编号
			$(this).parent().find("input:hidden").val(ui.item.materialId);
			
			var $td = $(this).parent().parent().find('td');

			var $oMatName   = $td.eq(2).find("span");
			var $oSupplier  = $td.eq(3).find("input");
			var $oQuantity  = $td.eq(4).find("input");
			var $oThisPrice = $td.eq(5).find("input");
			var $oAmount1   = $td.eq(6).find("input:hidden")
			var $oAmount2   = $td.eq(6).find("span");;
			var $oAmountd   = $td.eq(6).find("input:last-child");//人工成本
			var $oCurrPrice = $td.eq(7).find("span");
			var $oSourPrice = $td.eq(8).find("span");
			var $oMinPrice  = $td.eq(9).find("span");
		
			//开始计算
			var fPrice    = currencyToFloat(ui.item.price);//计算用单价
			var fQuantity = currencyToFloat($oQuantity.val());//计算用数量
			var fTotalNew = currencyToFloat(fPrice * fQuantity);//合计
			var fAmountd  = fnLaborCost(ui.item.materialId,fTotalNew);//人工成本

			//显示到页面
			var vPrice    = floatToCurrency(fPrice);
			var vTotalNew = floatToCurrency(fTotalNew);
			var vMinPrice = floatToCurrency(ui.item.minPrice);

			$oMatName.html(jQuery.fixedWidth(ui.item.name,20));
			$oSupplier.val(ui.item.supplierId);
			$oThisPrice.val(vPrice);
			$oAmount1.val(vTotalNew);
			$oAmount2.html(vTotalNew);
			$oCurrPrice.html(vPrice);
			$oMinPrice.html(vMinPrice);
			$oAmountd.val(fAmountd);//人工成本
			$oSourPrice.html('');//清空"上次BOM价格"

			//合计
			costAcount();
			
			if (ui.item.supplierId !="")
				$oSupplier.removeClass('error');
			
			//光标位置
			$oQuantity.focus();			
		},

		
	});//物料选择
	
	//供应商选择
	$(".attributeList2").autocomplete({
		minLength : 0,
		autoFocus : false,
		width  :1000,
		
		search: function( event, ui ) {
			var $tds = $(this).parent().parent().find('td');
			var material = $tds.eq(1).find("input:text").val();
			$('#tmpMaterialId').val(material);

			 if(material==""){
				$().toastmessage('showWarningToast', "请先输入ERP编号。");	
				 return false;
			 }
			
		},

		source : function(request, response) {
			var url = "${ctx}/business/bom?methodtype=getSupplierPriceList";
			var MaterialId = $('#tmpMaterialId').val();
			$
			.ajax({
				type : "POST",
				url : url,
				dataType : "json",
				data : {
					key1 : request.term,
					key2 : MaterialId,
				},
				success : function(data) {
					//alert(777);
					response($
						.map(
							data.data,
							function(item) {

								return {
									label : item.viewList,
									value : item.supplierId,
									id : item.supplierId,
									supplierId : item.supplierId,
								     price : item.price
								}
							}));
				},
				error : function(XMLHttpRequest,
						textStatus, errorThrown) {
					alert(XMLHttpRequest.status);
					alert(XMLHttpRequest.readyState);
					alert(textStatus);
					alert(errorThrown);
					alert("系统异常，请再试或和系统管理员联系。");
				}
			});
		},

		select : function(event, ui) {

			var $td = $(this).parent().parent().find('td');

			var $oMaterial  = $td.eq(1).find("input:text");
			var $oSupplier  = $td.eq(3).find("input:hidden");
			var $oQuantity  = $td.eq(4).find("input");
			var $oThisPrice = $td.eq(5).find("input");
			var $oAmount1   = $td.eq(6).find("input:hidden");
			var $oAmount2   = $td.eq(6).find("span");
			var $oAmountd   = $td.eq(6).find("input:last-child");//人工成本
			var $oCurrPrice = $td.eq(7).find("span");
			
			//计算
			var materialId = $oMaterial.val();
			var fPrice = currencyToFloat(ui.item.price);//计算用单价
			var fQuantity = currencyToFloat($oQuantity.val());//计算用数量
			var fTotalNew = currencyToFloat(fPrice * fQuantity);//合计
			var fAmountd  = fnLaborCost(materialId,fTotalNew);//人工成本
	
			//显示到页面	
			var vPrice = floatToCurrency(fPrice);
			var vTotalNew = floatToCurrency(fTotalNew);

			$oSupplier.val(ui.item.supplierId);
			$oThisPrice.val(vPrice);
			$oAmount1.val(vTotalNew);
			$oAmount2.html(vTotalNew);
			$oCurrPrice.html(vPrice);
			$oAmountd.val(fAmountd);

			//合计
			costAcount();
			
		},

		
	});//供应商选择
}

</script>
	
</html>
