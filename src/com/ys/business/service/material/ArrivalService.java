package com.ys.business.service.material;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.ys.business.action.model.material.ArrivalModel;
import com.ys.business.db.dao.B_ArrivalDao;
import com.ys.business.db.data.B_ArrivalData;
import com.ys.business.db.data.B_CustomerData;
import com.ys.business.db.data.CommFieldsData;
import com.ys.business.service.common.BusinessService;
import com.ys.system.action.model.login.UserInfo;
import com.ys.util.basequery.common.BaseModel;
import com.ys.util.basequery.common.Constants;
import com.ys.system.service.common.BaseService;
import com.ys.util.CalendarUtil;
import com.ys.util.DicUtil;
import com.ys.util.basedao.BaseDAO;
import com.ys.util.basedao.BaseTransaction;
import com.ys.util.basequery.BaseQuery;

import javax.servlet.http.HttpServletRequest;

@Service
public class ArrivalService extends BaseService {
 
	DicUtil util = new DicUtil();
	BaseTransaction ts;

	String guid ="";
	private CommFieldsData commData;
	
	private HttpServletRequest request;
	
	private B_ArrivalDao dao;
	private ArrivalModel reqModel;
	private UserInfo userInfo;
	private BaseModel dataModel;
	private  Model model;
	private HashMap<String, String> userDefinedSearchCase;
	private BaseQuery baseQuery;
	HashMap<String, Object> modelMap = null;	

	public ArrivalService(){
		
	}

	public ArrivalService(Model model,
			HttpServletRequest request,
			ArrivalModel reqModel,
			UserInfo userInfo){
		
		this.dao = new B_ArrivalDao();
		this.model = model;
		this.reqModel = reqModel;
		this.request = request;
		this.userInfo = userInfo;
		dataModel = new BaseModel();
		modelMap = new HashMap<String, Object>();
		userDefinedSearchCase = new HashMap<String, String>();
		dataModel.setQueryFileName("/business/material/inventoryquerydefine");
		
	}
	public HashMap<String, Object> doSearch( String data) throws Exception {

		HashMap<String, Object> modelMap = new HashMap<String, Object>();
		int iStart = 0;
		int iEnd =0;
		String sEcho = "";
		String start = "";
		String length = "";
		String key1 = "";
		String key2 = "";
		
		data = URLDecoder.decode(data, "UTF-8");

		key1 = getJsonData(data, "keyword1").toUpperCase();
		key2 = getJsonData(data, "keyword2").toUpperCase();
		
		sEcho = getJsonData(data, "sEcho");	
		start = getJsonData(data, "iDisplayStart");		
		if (start != null && !start.equals("")){
			iStart = Integer.parseInt(start);			
		}
		
		length = getJsonData(data, "iDisplayLength");
		if (length != null && !length.equals("")){			
			iEnd = iStart + Integer.parseInt(length);			
		}		
		
		dataModel.setQueryName("getArrivaList");
		baseQuery = new BaseQuery(request, dataModel);
		userDefinedSearchCase.put("keyword1", key1);
		userDefinedSearchCase.put("keyword2", key2);
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

	public void addInit() {

		//取得到货编号"yyMMdd01"
		getArriveId();
	
	}

	public void showArrivalDetail() {

		String arrivalId = request.getParameter("arrivalId");
		getArrivaRecord(arrivalId);
	}
	
	public void insertArrival() {

		String arrivalId = insertAndView();
		getArrivaRecord(arrivalId);
	}
	
	private String insertAndView(){
		String arrivalId = "";
		ts = new BaseTransaction();
		
		
		try {
			ts.begin();
			
			B_ArrivalData reqData = (B_ArrivalData)reqModel.getArrival();
			List<B_ArrivalData> reqDataList = reqModel.getArrivalList();
			
			//删除旧数据
			arrivalId = reqData.getArrivalid();
			deleteArrivalById(arrivalId);
			
			for(B_ArrivalData data:reqDataList ){
				String contract = data.getContractid();
				if(contract == null || contract.equals(""))
					continue;
				
				commData = commFiledEdit(Constants.ACCESSTYPE_INS,
						"ArrivalInsert",userInfo);

				copyProperties(data,commData);

				String guid = BaseDAO.getGuId();
				data.setRecordid(guid);
				data.setArrivalid(arrivalId);
				data.setUserid(userInfo.getUserId());
				data.setArrivedate(reqData.getArrivedate());
				data.setStatus(Constants.ARRIVERECORD_0);//未报检
				
				dao.Create(data);			
			
			}
			
			ts.commit();			
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			try {
				ts.rollback();
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
			}
		}
		
		return arrivalId;
	}
	
	private void deleteArrivalById(String arrivalId) {

		String where = " arrivalId = '"+arrivalId+"' AND deleteFlag='0' ";
		try {
			dao.RemoveByWhere(where);
		} catch (Exception e) {
			// nothing
		}
		
	}
	
	private void getArrivaRecord(String arrivalId){
		
		try {
			dataModel.setQueryName("getArrivaRecord");
			userDefinedSearchCase.put("arrivalId", arrivalId);
			baseQuery = new BaseQuery(request, dataModel);
			baseQuery.setUserDefinedSearchCase(userDefinedSearchCase);
			baseQuery.getYsFullData();

			model.addAttribute("arrival",dataModel.getYsViewData().get(0));
			//String userId = dataModel.getYsViewData().get(0).get("userId");
			
			model.addAttribute("arrivalList",dataModel.getYsViewData());
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
	
		
	public void doDelete(String recordId) throws Exception{
		
		B_ArrivalData data = new B_ArrivalData();	
															
		try {
			
			ts = new BaseTransaction();										
			ts.begin();									
			
			String removeData[] = recordId.split(",");									
			for (String key:removeData) {									
												
				data.setRecordid(key);							
				dao.Remove(data);	
				
			}
			
			ts.commit();
		}
		catch(Exception e) {
			ts.rollback();
		}
	}
	
	
	
	
	public void getArriveId() {

		try {
			String key = CalendarUtil.fmtYmdDate();
			dataModel.setQueryName("getMAXArrivalId");
			baseQuery = new BaseQuery(request, dataModel);
			userDefinedSearchCase.put("arriveDate", key);
			baseQuery.setUserDefinedSearchCase(userDefinedSearchCase);			
			baseQuery.getYsFullData();	
			
			String code = dataModel.getYsViewData().get(0).get("MaxSubId");		
			
			model.addAttribute("arrivalId",
					BusinessService.getArriveRecordId(code));
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			reqModel.setEndInfoMap(SYSTEMERROR, "err001", "");
		}
		
	}
	

}