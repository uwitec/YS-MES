<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../common/common.jsp"%>
<html>
<head>
	用户管理
</head>

<script>
	$(function(){ 
		setCheckBoxTrue(false);
		//setMenuId("001");
		setMainFrameSrc("${ctx}/user?methodtype=initframe");
		//setNaviObj("selCheck");
		setInitNaviUrl("mainframe/initDept");
		setLaunchNaviUrl("mainframe/launchDept");
		
		//节点上的每个点击动作都通知给父窗口
		setClickNoCheckFlg(true);
		loadData();
	}); 
</script>

<body>
	<%@ include file="../common/navitree.jsp"%>
</body>


</html>