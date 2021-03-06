package com.ys.system.service.role;

import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.stereotype.Service;

import com.ys.system.action.model.login.UserInfo;
import com.ys.system.action.model.role.RoleModel;
import com.ys.system.common.BusinessConstants;
import com.ys.util.basequery.common.BaseModel;
import com.ys.util.basequery.common.Constants;
import com.ys.system.db.dao.S_ROLEMENUDao;
import com.ys.system.db.data.S_ROLEMENUData;
import com.ys.system.ejb.DbUpdateEjb;
import com.ys.system.interceptor.MenuInfo;
import com.ys.system.service.common.BaseService;
import com.ys.util.CalendarUtil;
import com.ys.util.basequery.BaseQuery;

import javax.naming.Context;
import javax.servlet.http.HttpServletRequest;

@Service
public class RoleMenuService extends BaseService {
 
	public HashMap<String, Object> doSearch(HttpServletRequest request, String data, UserInfo userInfo) throws Exception {

		int iStart = 0;
		int iEnd =0;
		String sEcho = "";
		String start = "";
		String length = "";
		HashMap<String, Object> modelMap = new HashMap<String, Object>();
		HashMap<String, String> userDefinedSearchCase = new HashMap<String, String>();
		RoleModel dataModel = new RoleModel();
		
		sEcho = getJsonData(data, "sEcho");	
		start = getJsonData(data, "iDisplayStart");		
		if (start != null && !start.equals("")){
			iStart = Integer.parseInt(start);			
		}
		
		length = getJsonData(data, "iDisplayLength");
		if (length != null && !length.equals("")){			
			iEnd = iStart + Integer.parseInt(length);			
		}		
		
		dataModel.setQueryFileName("/role/rolequerydefine");
		dataModel.setQueryName("rolemenuquerydefine_search");
		
		String key1 = getJsonData(data, "roleIdName");
		String key2 = getJsonData(data, "menuIdName");
		String key3 = getJsonData(data, "userIdName");
		String key4 = getJsonData(data, "unitId");
		BaseQuery baseQuery = new BaseQuery(request, dataModel);

		userDefinedSearchCase.put("roleIdName", key1);
		userDefinedSearchCase.put("menuIdName", key2);
		userDefinedSearchCase.put("userIdName", key3);
		userDefinedSearchCase.put("unitId", key4);

		if (!userInfo.getUserType().equals(Constants.USER_SA)) {
			userDefinedSearchCase.put("unitId", userInfo.getUnitId());
		}
		baseQuery.setUserDefinedSearchCase(userDefinedSearchCase);
		
		baseQuery.getYsQueryData(iStart, iEnd);	
		
		if ( iEnd > dataModel.getYsViewData().size()){
			iEnd = dataModel.getYsViewData().size();
		}
		
		modelMap.put("sEcho", sEcho); 
		
		modelMap.put("recordsTotal", dataModel.getRecordCount()); 
		
		modelMap.put("recordsFiltered", dataModel.getRecordCount());
		
		modelMap.put("data", dataModel.getYsViewData());
		
		return modelMap;
	}
	
	public RoleModel doSearch(HttpServletRequest request, RoleModel dataModel, UserInfo userInfo) throws Exception {

		HashMap<String, String> userDefinedSearchCase = new HashMap<String, String>();
		dataModel.setQueryFileName("/role/rolequerydefine");
		dataModel.setQueryName("rolemenuquerydefine_search");
		
		BaseQuery baseQuery = new BaseQuery(request, dataModel);
		if (!userInfo.getUserType().equals(Constants.USER_SA)) {
			userDefinedSearchCase.put("unitId", userInfo.getUnitId());
			baseQuery.setUserDefinedSearchCase(userDefinedSearchCase);
		}

		baseQuery.getQueryData();

		return dataModel;
	}
	
	public void doUpdate(HttpServletRequest request, String roleIdName, ArrayList<String> menuIdList, UserInfo userInfo) throws Exception {
		new S_ROLEMENUDao();
		
		RoleModel dataModel = new RoleModel();
		dataModel.setQueryFileName("/role/rolequerydefine");
		dataModel.setQueryName("rolemenuquerydefine_checkrole");
		HashMap<String, String> userDefinedSearchCase = new HashMap<String, String>();
		userDefinedSearchCase.put("roleIdName", roleIdName);
		if (!userInfo.getUserType().equals(Constants.USER_SA)) {
			userDefinedSearchCase.put("unitId", userInfo.getUnitId());
		}		
		
		BaseQuery baseQuery = new BaseQuery(request, dataModel);
		baseQuery.setUserDefinedSearchCase(userDefinedSearchCase);
		ArrayList<ArrayList<String>> result = baseQuery.getFullData();
		if (result.size() > 0) {
			//取得roleid
			String roleId = result.get(0).get(0);
			
			DbUpdateEjb bean = new DbUpdateEjb();

	        bean.executeRoleMenuUpdate(roleId, menuIdList, userInfo);

		} else {
			throw new Exception("角色不正确，无法更新数据库。");
		}

	}
	
	public RoleModel doDelete(HttpServletRequest request, String formData, UserInfo userInfo) {
		
		DbUpdateEjb bean = new DbUpdateEjb();
		RoleModel model = new RoleModel();
		
		try {
			bean.executeRoleMenuDelete(formData, userInfo);
			model.setEndInfoMap(BaseService.NORMAL, "", "");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			model.setEndInfoMap(BaseService.SYSTEMERROR, "", "");
		}
		
		return model;

	}
	
	public boolean isRightRole(HttpServletRequest request, String roleIdName, UserInfo userInfo) throws Exception {
		boolean result = false;
		ArrayList<ArrayList<String>> roleDataList;
		RoleModel dataModel = new RoleModel();
		HashMap<String, String> userDefinedSearchCase = new HashMap<String, String>();
		
		dataModel.setQueryFileName("/role/rolequerydefine");
		dataModel.setQueryName("rolemenuquerydefine_checkrole");
		
		BaseQuery baseQuery = new BaseQuery(request, dataModel);
		String caseIdRoleName = request.getParameter("roleId");
		if (!roleIdName.equals("")) {
			caseIdRoleName = roleIdName;
		}
		userDefinedSearchCase.put("roleIdName", caseIdRoleName);
		
		if (!userInfo.getUserType().equals(Constants.USER_SA)) {
			userDefinedSearchCase.put("unitId", userInfo.getUnitId());
		}
		
		baseQuery.setUserDefinedSearchCase(userDefinedSearchCase);
		roleDataList = baseQuery.getQueryData();
		
		if (roleDataList.size() > 0) {
			result = true;
		}
		
		return result;
	}
	
	public ArrayList<MenuInfo> getRoleIdMenu(HttpServletRequest request, UserInfo userInfo) throws Exception {
		ArrayList<ArrayList<String>> roleMenuList;
		ArrayList<ArrayList<String>> roleDataList;
		HashMap<String, String> userDefinedSearchCase = new HashMap<String, String>();
		ArrayList<MenuInfo> menuInfoLIst = new ArrayList<MenuInfo>();
		RoleModel dataModel = new RoleModel();
		
		dataModel.setQueryFileName("/role/rolequerydefine");
		dataModel.setQueryName("rolemenuquerydefine_checkrole");
		
		BaseQuery baseQuery = new BaseQuery(request, dataModel);
		String caseIdRoleName = request.getParameter("roleId");
		userDefinedSearchCase.put("roleIdName", caseIdRoleName);
		
		if (!userInfo.getUserType().equals(Constants.USER_SA)) {
			userDefinedSearchCase.put("unitId", userInfo.getUnitId());
		}
		
		baseQuery.setUserDefinedSearchCase(userDefinedSearchCase);
		roleDataList = baseQuery.getQueryData();
		
		dataModel.setQueryFileName("/role/rolequerydefine");
		dataModel.setQueryName("rolemenuquerydefine_getrolemenu");
		userDefinedSearchCase.clear();
		userDefinedSearchCase.put("roleId", roleDataList.get(0).get(1));
		
		baseQuery = new BaseQuery(request, dataModel);
		if (!userInfo.getUserType().equals(Constants.USER_SA)) {
			userDefinedSearchCase.put("unitId", userInfo.getUnitId());
		}
		baseQuery.setUserDefinedSearchCase(userDefinedSearchCase);
		roleMenuList = baseQuery.getFullData();
		
		for(ArrayList<String> rowData:roleMenuList) {
			MenuInfo menuData = new MenuInfo();
			menuData.setMenuId(rowData.get(0));
			menuData.setMenuName(rowData.get(1));
			menuData.setMenuIcon1(rowData.get(2));
			menuData.setMenuParentId(rowData.get(3));
			menuData.setSortNo(rowData.get(4));
			menuInfoLIst.add(menuData);
		}
		
		return menuInfoLIst;
		
	}
	
	public static S_ROLEMENUData updateModifyInfo(S_ROLEMENUData data, UserInfo userInfo) {
		String createUserId = data.getCreateperson();
		if ( createUserId == null || createUserId.equals("")) {
			data.setCreateperson(userInfo.getUserId());
			data.setCreatetime(CalendarUtil.fmtDate());
			data.setCreateunitid(userInfo.getUnitId());
			data.setDeptguid(userInfo.getDeptGuid());
		}
		data.setModifyperson(userInfo.getUserId());
		data.setModifytime(CalendarUtil.fmtDate());
		data.setDeleteflag(BusinessConstants.DELETEFLG_UNDELETE);
		
		return data;
	}
	
	public HashMap<String, Object> doRoleIdNameSearch(HttpServletRequest request) throws Exception {
		
		HashMap<String, Object> modelMap = new HashMap<String, Object>();	
		BaseModel dataModel = new BaseModel();	
		BaseQuery baseQuery = null;	
		HashMap<String, String> userDefinedSearchCase = new HashMap<String, String>();
		String key = request.getParameter("key");	
		if (key.equals("*")) {
			key = "";
		}
		dataModel.setQueryFileName("/common/selectrolequery");
		dataModel.setQueryName("selectrolequery_search");
		userDefinedSearchCase.put("roleIdName", key);

		baseQuery = new BaseQuery(request, dataModel);
		baseQuery.setUserDefinedSearchCase(userDefinedSearchCase);
		baseQuery.getYsQueryData(0,0);	
			
		modelMap.put("data", dataModel.getYsViewData());	
			
		return modelMap;	
	}
}
