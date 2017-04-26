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

import com.ys.system.action.common.BaseAction;
import com.ys.business.action.model.order.PurchaseOrderModel;
import com.ys.business.action.model.order.RequirementModel;
import com.ys.business.service.order.PurchaseOrderService;
import com.ys.business.service.order.RequirementService;
import com.ys.system.action.model.login.UserInfo;
import com.ys.system.common.BusinessConstants;

@Controller
@RequestMapping("/business")
public class PurchaseOrderAction extends BaseAction {
	
	@Autowired PurchaseOrderService service;
	@Autowired HttpServletRequest request;
	
	UserInfo userInfo = new UserInfo();
	PurchaseOrderModel reqModel = new PurchaseOrderModel();
	Model model;
	
	@RequestMapping(value="/contract")
	public String init(
			@RequestBody String data, 
			@ModelAttribute("attrForm") PurchaseOrderModel form, 
			BindingResult result,
			Model model, 
			HttpSession session, 
			HttpServletRequest request,
			HttpServletResponse response ) throws Exception {
		
		String type = request.getParameter("methodtype");
		userInfo = (UserInfo)session.getAttribute(
				BusinessConstants.SESSION_USERINFO);
		
		this.service = new PurchaseOrderService(model,request,form,userInfo);
		this.reqModel = form;
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
				rtnUrl = "/business/purchase/purchaseordermain";
				break;				
			case "search":
				dataMap = doSearch(data);
				printOutJsonObj(response, dataMap);
				break;
			case "creatPurchaseOrder":
				creatPurchaseOrder();
				printOutJsonObj(response, dataMap);
				break;					
			case "getContract":
				dataMap = getContract();
				printOutJsonObj(response, dataMap);
				break;					
			case "getContractDetail":
				dataMap = getContractDetail();
				printOutJsonObj(response, dataMap);
				break;
			case "createZZ":
				creatPurchaseOrderZZ();
				rtnUrl = "/business/purchase/purchaseordermain";
				break;					
			case "detailView":
				doDetailView();
				rtnUrl = "/business/purchase/purchaseorderview";
				break;
			case "edit":
				editPurchaseOrder();
				rtnUrl = "/business/purchase/purchaseorderedit";
				break;				
			case "update":
				doUpdate();
				rtnUrl = "/business/purchase/purchaseorderview";
				break;				
			case "approve":
				doApprove();
				rtnUrl = "/business/order/ordermain";
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
			dataMap = service.getContractList(data);
			
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
	
	public void creatPurchaseOrderZZ() throws Exception{

		service.creatPurchaseOrderZZ();
	}
	
	public HashMap<String, Object> getContract() throws Exception{

		return service.getContractList();
	}
	public HashMap<String, Object> getContractDetail() throws Exception{

		return service.getContractDetail();
	}
	public void creatPurchaseOrder() throws Exception{

		service.creatPurchaseOrder();
	}	

	public void doDetailView() throws Exception{

		String contractId = request.getParameter("contractId");

		service.getContractDetailList(contractId);
	}
	
	public void doUpdate() throws Exception {
		
		service.updateAndView();			
		
	}
	
	public void editPurchaseOrder() throws Exception {
		
		String contractId = reqModel.getContract().getContractid();
		
		//service.getContractBySupplierId(contractId);			
		
	}
	
	public void doApprove() throws Exception {
		
		service.approveAndView();			
		
	}	
}
