package com.ys.business.action.order;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ys.business.action.model.order.BomPlanModel;
import com.ys.business.action.model.material.MaterialModel;
import com.ys.business.service.order.BomService;
import com.ys.business.service.order.OrderService;
import com.ys.business.service.order.PurchaseService;
import com.ys.system.action.model.login.UserInfo;
import com.ys.system.common.BusinessConstants;
import com.ys.system.action.common.BaseAction;


@Controller
@RequestMapping("/business")
public class PurchaseAction extends BaseAction {
	
	@Autowired PurchaseService purchaseService;
	@Autowired HttpServletRequest request;
	
	UserInfo userInfo = new UserInfo();
	BomPlanModel reqModel = new BomPlanModel();
	Model model;
	
	@RequestMapping(value="/purchase")
	public String init(
			@RequestBody String data, 
			@ModelAttribute("purchaseForm") BomPlanModel bom, 
			BindingResult result,
			Model model, 
			HttpSession session, 
			HttpServletRequest request,
			HttpServletResponse response ) throws Exception {
		
		String type = request.getParameter("methodtype");
		userInfo = (UserInfo)session.getAttribute(
				BusinessConstants.SESSION_USERINFO);
		
		purchaseService = new PurchaseService(model,request,bom,userInfo);
		reqModel = bom;
		this.model = model;
		
		String rtnUrl = null;
		HashMap<String, Object> dataMap = null;
		
		if (type == null) {
			type = "";
		} else {
			int q = type.indexOf("?");
			if (q >= 0) {
				type = type.substring(0, q);
			}
		}
		
		switch(type) {
			case "":
			case "init":
				rtnUrl = "/business/purchase/purchasemain";
				break;				
			case "search":
				dataMap = doSearch(data);
				printOutJsonObj(response, dataMap);
				break;							
			case "implement":
				rtnUrl = "/business/purchase/purchaseman";
				break;			
			case "searchPurchase":
				dataMap = doSearchPurchase(data);
				printOutJsonObj(response, dataMap);
				break;
			case "create":
				doCreate();
				rtnUrl = "/business/purchase/bomplanadd";
				break;
			case "insert":
				doInsert();
				rtnUrl = "/business/purchase/bomplanview";
				break;							
			case "detail":
				doShowBomDetail();
				rtnUrl = "/business/purchase/purchaseplanview";
				break;								
			case "PurchaseView":
				doShowPurchaseDetail();
				rtnUrl = "/business/purchase/purchaseplan";
				break;	
			//case "copy":
			//	doCopy();
			//	rtnUrl = "/business/purchase/bomplancopy";
			//	break;
			case "edit":
				doEdit();
				rtnUrl = "/business/purchase/purchaseplanedit";
				break;				
			case "update":
				doUpdate();
				rtnUrl = "/business/purchase/bomplanview";
				break;
			case "getSupplierPriceList"://供应商编号查询
				dataMap = doGetSupplierPriceList();
				printOutJsonObj(response, dataMap);
				break;
			case "getMaterialPriceList"://物料编号查询
				dataMap = doGetMaterialList();
				printOutJsonObj(response, dataMap);
				break;
				
		}
		
		return rtnUrl;		
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> doSearch(
			@RequestBody String data){
		
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		ArrayList<HashMap<String, String>> dbData = 
				new ArrayList<HashMap<String, String>>();
		
		try {
			dataMap = purchaseService.getBomList(data);
			
			dbData = (ArrayList<HashMap<String, String>>)dataMap.get("data");
			if (dbData.size() == 0) {
				dataMap.put(INFO, NODATAMSG);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			dataMap.put(INFO, ERRMSG);
		}
		
		return dataMap;
	}


	@SuppressWarnings("unchecked")
	public HashMap<String, Object> doSearchPurchase(
			@RequestBody String data){
		
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		ArrayList<HashMap<String, String>> dbData = 
				new ArrayList<HashMap<String, String>>();
		
		try {
			dataMap = purchaseService.getBomApproveList(data);
			
			dbData = (ArrayList<HashMap<String, String>>)dataMap.get("data");
			if (dbData.size() == 0) {
				dataMap.put(INFO, NODATAMSG);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			dataMap.put(INFO, ERRMSG);
		}
		
		return dataMap;
	}
	
	public void doCopy() throws Exception{
			
		model = purchaseService.copyBomPlan();
			
	}

	public void doCreate() throws Exception{
		
		model = purchaseService.createBomPlan();
		
	}
	
	public void doInsert() throws Exception {

		model = purchaseService.insertAndView();
		
	}		
	
	
	public void doEdit() throws Exception{

		model = purchaseService.editBomPlan();
	}	
	
	public void doUpdate() throws Exception {
		
		purchaseService.updateAndView();			
		
	}

	public void doShowBomDetail() throws Exception{
				
		model = purchaseService.showBomDetail();
			
	}
	
	public void doShowPurchaseDetail() throws Exception{
		
		model = purchaseService.showBomDetail();
			
	}
	
	/*
	 * 
	 */	
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> doGetMaterialList(){
		
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		ArrayList<HashMap<String, String>> dbData = new ArrayList<HashMap<String, String>>();
		
		try {
			dataMap = purchaseService.getMaterialList(request);
			
			dbData = (ArrayList<HashMap<String, String>>)dataMap.get("data");
			if (dbData.size() == 0) {
				dataMap.put(INFO, NODATAMSG);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			dataMap.put(INFO, ERRMSG);
		}
		
		return dataMap;
	}
	
	/*
	 * doSupplierPriceList
	 */	
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> doGetSupplierPriceList(){
		
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		ArrayList<HashMap<String, String>> dbData = new ArrayList<HashMap<String, String>>();
		
		try {
			dataMap = purchaseService.getSupplierPriceList();
			
			dbData = (ArrayList<HashMap<String, String>>)dataMap.get("data");
			if (dbData.size() == 0) {
				dataMap.put(INFO, NODATAMSG);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			dataMap.put(INFO, ERRMSG);
		}
		
		return dataMap;
	}

	
}