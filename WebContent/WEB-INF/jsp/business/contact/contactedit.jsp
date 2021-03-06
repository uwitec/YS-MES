<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />

<%@ include file="../../common/common.jsp"%>

<title>联系人</title>
<script type="text/javascript">

var validator;

$(document).ready(function() {
	
	controlButtons($('#keyBackup').val());
	
	addValidator();
	
	validator = $("#contactInfo").validate({
		rules: {
			userName: {
				required: true,
				maxlength: 20,
			},
			sex: {
				maxlength: 2,
			},
			position: {
				maxlength: 20,
			},
			department: {
				maxlength: 20,
			},
			mobile: {
				maxlength: 20,
				mobile: true,
			},
			phone: {
				maxlength: 20,
				phone: true,
			},
			fax: {
				maxlength: 20,
			},
			mail: {
				maxlength: 20,
				email: true,
			},
			skype: {
				maxlength: 20,
			},
			mark: {
				maxlength: 1,
			},
			QQ: {
				maxlength: 20,
				number: true,
			},			
		},
		errorPlacement: function(error, element) {
		    if (element.is(":radio"))
		        error.appendTo(element.parent().next().next());
		    else if (element.is(":checkbox"))											    	
		    	error.insertAfter(element.parent().parent());
		    else
		    	error.insertAfter(element);
		}
	});
	
	$("#sex").val("${DisplayData.contactData.sex}")
})


function doSave() {

	if (validator.form()) {
		
		var message = "${DisplayData.endInfoMap.message}";
		
		if ($('#keyBackup').val() == "") {				
			//新建
			actionUrl = "${ctx}/business/contact?methodtype=add";
			$('#companyCode').val(parent.$('#keyBackup').val());
			
		} else {
			//修正
			actionUrl = "${ctx}/business/contact?methodtype=update";
		}

		//将提交按钮置为【不可用】状态
		//$("#submit").attr("disabled", true); 
		$.ajax({
			type : "POST",
			contentType : 'application/json',
			dataType : 'json',
			url : actionUrl,
			data : JSON.stringify($('#contactInfo').serializeArray()),// 要提交的表单
			success : function(d) {
			
				parent.reloadContact();
			
				
				//不管成功还是失败都刷新父窗口，关闭子窗口
				//var index = parent.layer.getFrameIndex(wind$("#mainfrm")[0].contentWindow.ow.name); //获取当前窗体索引

				var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
				//parent.$('#events').DataTable().destroy();
				parent.layer.close(index); //执行关闭
				
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

function doDelete() {
	
	if (confirm("${DisplayData.endInfoMap.message}")) {
		//将提交按钮置为【不可用】状态
		//$("#submit").attr("disabled", true); 
		$.ajax({
			type : "POST",
			contentType : 'application/json',
			dataType : 'json',
			url : "${ctx}/business/contact?methodtype=deleteDetail",
			data : $('#keyBackup').val(),// 要提交的表单
			success : function(d) {
				if (d.rtnCd != "000") {
					alert(d.message);	
				} else {
					//$('#keyBackup').val("");
					//$('#companyCode').val("");
					controlButtons("");
					clearContactInfo();
					parent.reloadContact();
				}
				
					
				//不管成功还是失败都刷新父窗口，关闭子窗口
				var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
				//parent.$('#events').DataTable().destroy();/
				//parent.reload_contactor();
				parent.layer.close(index); //执行关闭
				
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

function clearContactInfo() {
	$('#userName').val('');
	$('#sex').val('');
	$('#position').val('');
	$('#mobile').val('');
	$('#phone').val('');
	$('#fax').val('');
	$('#mail').val('');
	$('#QQ').val('');
}

function controlButtons(data) {
	var valueArray = data.split("|");
	if (valueArray.length > 1) {
		$('#keyBackup').val(valueArray[0]);
		$('#companyCode').val(valueArray[1]);
	} else {
		$('#keyBackup').val(data);
	}
	if (data == '') {
		$('#delete').attr("disabled",true);
	} else {
		$('#delete').attr("disabled",false);
	}
}
</script>

</head>

<body class="noscroll">

<div id="layer_main">	

	<form:form modelAttribute="dataModels" id="contactInfo" style='padding: 0px; margin: 10px;' >
	
		<input type="hidden" id="keyBackup" name="keyBackup" value="${DisplayData.keyBackup}"/>
		<input type="hidden" id="companyCode" name="companyCode" value="${DisplayData.companyCode}"/>
				
		<fieldset>
			<legend>联系人信息</legend>
				
				<table class="form">
					<tr>
						<td width="100px" class="label">姓名：</td>
						<td width="200px">
							<input type="text" id="userName" name="userName" class="short" value="${DisplayData.contactData.username}"/>
						</td>
						<td width="100px" class="label">性别：</td> 
						<td width="200px">
							<form:select path="sex">
								<form:options items="${DisplayData.sexList}" itemValue="key"
									itemLabel="value" />
							</form:select>
						</td>
					</tr>
					<tr>
						<td width="100px" class="label">职务：</td> 
						<td>
							<input type="text" id="position" name="position" class="short" value="${DisplayData.contactData.position}"/>
						</td>	
						<td width="100px" class="label">手机：</td> 
						<td>
							<input type="text" id="mobile" name="mobile" class="short" value="${DisplayData.contactData.mobile}"/>
						</td>
					</tr>
					<tr>
						<td width="100px" class="label">电话：</td> 
						<td>
							<input type="text" id="phone" name="phone" class="short" value="${DisplayData.contactData.phone}"/>
						</td>
						<td width="100px" class="label">
							传真：
						</td>
						<td>
							<input type="text" id="fax" name="fax" class="short" value="${DisplayData.contactData.fax}"/>
						</td>
					</tr>
					<tr>
						<td width="100px" class="label">
							邮箱：
						</td>
						<td>
							<input type="text" id="mail" name="mail" class="middle" value="${DisplayData.contactData.mail}"/>
						</td>
						<td width="100px" class="label">
							QQ：
						</td>
						<td>
							<input type="text" id="QQ" name="QQ" class="short" value="${DisplayData.contactData.qq}"/>
						</td>
					</tr>
					<tr>
						<td width="100px" class="label">
							Skype：
						</td>
						<td>
							<input type="text" id="skype" name="skype" class="short" value="${DisplayData.contactData.skype}"/>
						</td>							
					</tr>
				</table>
		</fieldset>
		<fieldset  style="text-align:right">
			<button type="button" class="DTTT_button" onclick="doSave()">保存</button>
		</fieldset>
	</form:form>
</div>
</body>
</html>
