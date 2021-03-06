package com.ys.system.service.unit;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.ys.system.action.model.login.UserInfo;
import com.ys.system.action.model.unit.UnitModel;
import com.ys.system.common.BusinessConstants;
import com.ys.util.basequery.common.BaseModel;
import com.ys.util.basequery.common.Constants;
import com.ys.system.db.dao.S_DEPTDao;
import com.ys.system.db.data.S_DEPTData;
import com.ys.system.ejb.DbUpdateEjb;
import com.ys.system.service.common.BaseService;
import com.ys.util.CalendarUtil;
import com.ys.util.DicUtil;
import com.ys.util.basedao.BaseDAO;
import com.ys.util.basequery.BaseQuery;
import javax.servlet.http.HttpServletRequest;

@Service
public class UnitService extends BaseService {
 
	public HashMap<String, Object> doSearch(HttpServletRequest request, String data, UserInfo userInfo) throws Exception {
		
		int iStart = 0;
		int iEnd =0;
		String sEcho = "";
		String start = "";
		String length = "";
		HashMap<String, Object> modelMap = new HashMap<String, Object>();
		HashMap<String, String> userDefinedSearchCase = new HashMap<String, String>();
		UnitModel dataModel = new UnitModel();
		
		sEcho = getJsonData(data, "sEcho");	
		start = getJsonData(data, "iDisplayStart");		
		if (start != null && !start.equals("")){
			iStart = Integer.parseInt(start);			
		}
		
		length = getJsonData(data, "iDisplayLength");
		if (length != null && !length.equals("")){			
			iEnd = iStart + Integer.parseInt(length);			
		}		
		
		dataModel.setQueryFileName("/unit/unitquerydefine");
		dataModel.setQueryName("unitquerydefine_search");
		
		String key1 = getJsonData(data, "unitIdName");
		
		BaseQuery baseQuery = new BaseQuery(request, dataModel);
		if (userInfo.isSA()) {
			userDefinedSearchCase.put("userUnitId", "");
		} else {
			userDefinedSearchCase.put("userUnitId", userInfo.getUnitId());
		}
		userDefinedSearchCase.put("unitIdName", key1);
		
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

	public UnitModel getDetail(HttpServletRequest request) throws Exception {
		String unitId = request.getParameter("unitId");
		UnitModel unitModel = new UnitModel();
		
		unitModel.setUnitPropertyList(DicUtil.getGroupValue(DicUtil.UNITPROPERTY));
		unitModel.setUnitTypeList(DicUtil.getGroupValue(DicUtil.UNITTYPE));
		
		S_DEPTDao dao = new S_DEPTDao();
		S_DEPTData data = new S_DEPTData();
		data.setUnitid(unitId);
		data = (S_DEPTData)dao.FindByPrimaryKey(data);
		
		unitModel.setunitData(data);
		
		if (data.getParentid() != null && !data.getParentid().equals("")) {
			S_DEPTData parentData = new S_DEPTData();
			parentData.setUnitid(data.getParentid());
			parentData = (S_DEPTData)dao.FindByPrimaryKey(parentData);
			
			unitModel.setParentUnitName(parentData.getUnitname());
			unitModel.setParentUnitId(data.getParentid());
		}
		String addressCode[] = data.getAddresscode().split("-");
		String address = "";
		for(int i = 0; i < addressCode.length; i++) {
			if (i == 0) {
				address += DicUtil.getCodeValue(DicUtil.ADDRESS + addressCode[i]);
			} else {
				address += DicUtil.getCodeValue(DicUtil.ADDRESS + addressCode[i]);
			}
		}
		unitModel.setAddress(address);
		unitModel.setIsOnlyView("T");
		
		return unitModel;
	
	}

	public UnitModel getParentDeptName(HttpServletRequest request) throws Exception {
		
		String unitId = request.getParameter("unitId");
		
		UnitModel unitModel = new UnitModel();
		S_DEPTData parentData = new S_DEPTData();
		S_DEPTDao dao = new S_DEPTDao();
		
		parentData.setUnitid(unitId);
		parentData = (S_DEPTData)dao.FindByPrimaryKey(parentData);
		unitModel.setParentUnitId(unitId);
		unitModel.setParentUnitName(parentData.getUnitname());
		
		return unitModel;
	
	}
	
	
	public UnitModel doUpdate(HttpServletRequest request, String formData, UserInfo userInfo, boolean isAdd) {
		S_DEPTDao dao = new S_DEPTDao();
		S_DEPTData data = new S_DEPTData();
		UnitModel model = new UnitModel();
		
		String unitid = getJsonData(formData, "unitid");
		String parentUnitId = getJsonData(formData, "parentUnitId");
		String unitname = getJsonData(formData, "unitname");
		String unitsimpledes = getJsonData(formData, "unitsimpledes");
		String jianpin = getJsonData(formData, "jianpin");
		String orgid = getJsonData(formData, "orgid");
		String unitproperty = getJsonData(formData, "unitproperty");
		String unittype = getJsonData(formData, "unittype");
		String addresscode = getJsonData(formData, "addresscode");
		String addressuser = getJsonData(formData, "addressuser");
		String postcode = getJsonData(formData, "postcode");
		String telphone = getJsonData(formData, "telphone");
		String email = getJsonData(formData, "email");
		String mgrperson = getJsonData(formData, "mgrperson");
		String officeid = getJsonData(formData, "officeid");
		String areaid = getJsonData(formData, "areaid");
		String sortno = getJsonData(formData, "sortno");
		
		try {

			if (isAdd) {
				unitid = getNewUnitId(parentUnitId, userInfo);
				data.setUnitid(unitid);
			} else {
				data.setUnitid(unitid);
				data = (S_DEPTData)dao.FindByPrimaryKey(data);
			}
			
			data = storeDeptGuid(request, parentUnitId, data);
			
			data.setUnitname(unitname);
			data.setUnitsimpledes(unitsimpledes);
			data.setJianpin(jianpin);
			data.setOrgid(orgid);
			data.setAddresscode(addresscode);
			data.setAddressuser(addressuser);
			data.setPostcode(postcode);
			data.setTelphone(telphone);
			data.setEmail(email);
			data.setUnitproperty(unitproperty);
			data.setOfficeid(officeid);
			data.setAreaid(areaid);
			data.setUnittype(unittype);
			data.setMgrperson(mgrperson);
			if (sortno != null && !sortno.equals("")) {
				data.setSortno(Integer.parseInt(sortno));
			} else {
				data.setSortno(0);
			}

			data = updateModifyInfo(data, userInfo);

			if (!isAdd) {
				dao.Store(data);
			} else {
				dao.Create(data);
			}
			
			model.setEndInfoMap(BaseService.NORMAL, "", unitid);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			model.setEndInfoMap(BaseService.SYSTEMERROR, "", "");
		}
		
		return model;
	}
	
	public UnitModel doDelete(HttpServletRequest request, String formData, UserInfo userInfo) {
		
		DbUpdateEjb bean = new DbUpdateEjb();
		UnitModel model = new UnitModel();
		
		try {
			bean.executeUnitDelete(formData, userInfo);
			model.setEndInfoMap(BaseService.NORMAL, "", "");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			model.setEndInfoMap(BaseService.SYSTEMERROR, "", "");
		}
		
		return model;
        
	}
	
	public int preCheck(HttpServletRequest request, String operType, String data, String userUnitId, String userType) throws Exception {
		
		int result = 0;
		
		UnitModel unitModel = new UnitModel();
		
		unitModel.setQueryFileName("/unit/unitquerydefine");
		unitModel.setQueryName("unitquerydefine_getparentdeptguid");
		String parentUnitId = getJsonData(data, "parentUnitId");
		BaseQuery baseQuery = new BaseQuery(request, unitModel);
		ArrayList<ArrayList<String>> unitDataList = baseQuery.getQueryData();
		//父单位是否存在
		if (unitDataList.size() == 0) {
			if (!parentUnitId.equals("")) {
				result = 1;
			}
		} else {
			if (!userType.equals(Constants.USER_SA)) {
				String parentId = unitDataList.get(0).get(1);
				if (parentId.length() >= userUnitId.length()) {
					if (!parentId.substring(0, userUnitId.length()).equals(userUnitId)) {
						//父单位无权限
						result = 2;						
					}
				} else {
					//父单位无权限
					result = 2;
				}
			}
			
			//单位名称是否存在
			HashMap<String, String> userDefinedSearchCase = new HashMap<String, String>();
			userDefinedSearchCase.put("unitIdName", getJsonData(data, "unitname"));
			userDefinedSearchCase.put("userUnitId", userUnitId);
			unitModel.setQueryFileName("/unit/unitquerydefine");
			unitModel.setQueryName("unitquerydefine_confirmunitname");
			baseQuery = new BaseQuery(request, unitModel);
			baseQuery.setUserDefinedSearchCase(userDefinedSearchCase);
			unitDataList = baseQuery.getQueryData();
			if (operType.equals("add") || operType.equals("addsub")) {
				if (unitDataList.size() > 0) {
					//单位名称已存在
					result = 3;
				}
			} else {
				if (unitDataList.size() == 0) {
					//单位名称不存在
					//result = 4;
				} else {
					String updateSrcUnitId = getJsonData(data, "unitid");
					if (!unitDataList.get(0).get(1).equals(updateSrcUnitId)) {
						//单位名称已存在
						result = 3;
					}
				}
			}
		}
		
		return result;
	}
	
	public static S_DEPTData updateModifyInfo(S_DEPTData data, UserInfo userInfo) {
		String createUserId = data.getCreateperson();
		if ( createUserId == null || createUserId.equals("")) {
			data.setCreateperson(userInfo.getUserId());
			data.setCreatetime(CalendarUtil.fmtDate());
			data.setCreateunitid(userInfo.getUnitId());
			
		}
		data.setModifyperson(userInfo.getUserId());
		data.setModifytime(CalendarUtil.fmtDate());
		data.setDeleteflag(BusinessConstants.DELETEFLG_UNDELETE);
		
		return data;
	}
	
	private S_DEPTData storeDeptGuid(HttpServletRequest request, String parentUnitId, S_DEPTData data) throws Exception {
		UnitModel unitModel = new UnitModel();
		if (parentUnitId.equals("")) {
			data.setDeptguid(BaseDAO.getGuId());
			data.setParentid("");
		} else {
			unitModel.setQueryFileName("/unit/unitquerydefine");
			unitModel.setQueryName("unitquerydefine_getparentdeptguid");
			
			BaseQuery baseQuery = new BaseQuery(request, unitModel);
			ArrayList<ArrayList<String>> unitDataList = baseQuery.getQueryData();
			data.setParentid(unitDataList.get(0).get(1));
			data.setDeptguid(unitDataList.get(0).get(2));
		}
		
		return data;
	}
	
	private String getNewUnitId(String parentUnitId, UserInfo userInfo) throws Exception {

		String newDeptId = "";
		
		if (parentUnitId.equals("")) {
			if (!userInfo.getUserType().equals(Constants.USER_SA)) {
				parentUnitId = userInfo.getUnitId();
			}
		}
		
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT CONCAT('" + parentUnitId + "', LPAD(A.numUnit + 1, " + BusinessConstants.UNITIDLENGTH + ", '0')) as newunitid ");
		sql.append("FROM ");
		sql.append("(select unitid, cast(substr(unitid, if(length('" + parentUnitId + "')=0, 1, length('" + parentUnitId + "') + 1), if(length('" + parentUnitId + "')=0, " + BusinessConstants.UNITIDLENGTH + ", length('" + parentUnitId + "'))) as SIGNED) as numUnit ");
		sql.append("from s_dept as a ");
		sql.append("where a.unitid like '%') as A ");
		sql.append("where (A.numUnit + 1) NOT IN ");
		sql.append("(select cast(substr(unitid, if(length('" + parentUnitId + "')=0, 1, length('" + parentUnitId + "') + 1), if(length('" + parentUnitId + "')=0, " + BusinessConstants.UNITIDLENGTH + ", length('" + parentUnitId + "'))) as SIGNED) as numUnit ");
		sql.append("from s_dept as a ");
		sql.append("where a.unitid like '%') ");
		sql.append("order by newunitid");
		ArrayList<ArrayList<String>> result = BaseDAO.execSQL(sql.toString());
		
		if (result.size() == 0) {
			newDeptId = String.format("%0" + BusinessConstants.UNITIDLENGTH + "d", 1);
		} else {
			newDeptId = result.get(0).get(0);
		}
		
		return newDeptId;
	}
	
	public HashMap<String, Object> doUnitSearch(HttpServletRequest request, String data) throws Exception {

		HashMap<String, Object> modelMap = new HashMap<String, Object>();
		HashMap<String, String> userDefinedSearchCase = new HashMap<String, String>();
		BaseModel dataModel = new BaseModel();
		String key = "";
		
		data = URLDecoder.decode(data, "UTF-8");

		key = request.getParameter("key");	
		
		dataModel.setQueryFileName("/unit/unitquerydefine");	
		dataModel.setQueryName("unitSearch");	
		BaseQuery baseQuery = new BaseQuery(request, dataModel);	
		
		userDefinedSearchCase.put("key", key);
		baseQuery.setUserDefinedSearchCase(userDefinedSearchCase);

		baseQuery.getYsQueryData(0,0);	
			
		modelMap.put("data", dataModel.getYsViewData());
		
		return modelMap;		

	}
	
	public HashMap<String, Object> doAddressSearch(HttpServletRequest request, String data) throws Exception {

		HashMap<String, Object> modelMap = new HashMap<String, Object>();
		HashMap<String, String> userDefinedSearchCase = new HashMap<String, String>();
		BaseModel dataModel = new BaseModel();
		String key = "";
		
		data = URLDecoder.decode(data, "UTF-8");

		key = request.getParameter("key");	
		
		dataModel.setQueryFileName("/unit/unitquerydefine");	
		dataModel.setQueryName("dicSearch");
		userDefinedSearchCase.put("typeid", DicUtil.ADDRESS);
		BaseQuery baseQuery = new BaseQuery(request, dataModel);	
		
		userDefinedSearchCase.put("key", key);
		baseQuery.setUserDefinedSearchCase(userDefinedSearchCase);

		baseQuery.getYsQueryData(0,0);	
			
		modelMap.put("data", dataModel.getYsViewData());
		
		return modelMap;		

	}
}
