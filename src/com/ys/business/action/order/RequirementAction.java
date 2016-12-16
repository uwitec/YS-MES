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
import com.ys.business.action.model.order.RequirementModel;
import com.ys.business.service.order.RequirementService;
import com.ys.system.action.model.login.UserInfo;
import com.ys.system.common.BusinessConstants;

@Controller
@RequestMapping("/business")
public class RequirementAction extends BaseAction {
	
	@Autowired RequirementService service;
	@Autowired HttpServletRequest request;
	
	UserInfo userInfo = new UserInfo();
	RequirementModel reqModel = new RequirementModel();
	Model model;
	
	@RequestMapping(value="/requirement")
	public String init(
			@RequestBody String data, 
			@ModelAttribute("attrForm") RequirementModel form, 
			BindingResult result,
			Model model, 
			HttpSession session, 
			HttpServletRequest request,
			HttpServletResponse response ) throws Exception {
		
		String type = request.getParameter("methodtype");
		userInfo = (UserInfo)session.getAttribute(
				BusinessConstants.SESSION_USERINFO);
		
		this.service = new RequirementService(model,request,form,userInfo);
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
				rtnUrl = "/business/requirement/requirementmain";
				break;				
			case "search":
				dataMap = doSearch(data);
				printOutJsonObj(response, dataMap);
				break;	
			case "insert":
				doInsert();
				rtnUrl = "/business/requirement/requirementview";
				break;					
			case "purchasePlanView":
				//doShowBomDetail();
				rtnUrl = "/business/bom/bomselectlist";
				break;
			case "editZZ":
				doEditZZ();
				rtnUrl = "/business/requirement/rawrequirementedit";
				break;	
			case "editRaw":
				doEdit();
				rtnUrl = "/business/requirement/rawrequirementedit";
				break;
			case "editPart":
				doEditPart();
				rtnUrl = "/business/requirement/requirementedit";
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
			//dataMap = service.getBomList(data);
			
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


	
	
	public void doInsert() throws Exception {

		service.insertAndView();
		
	}		
	
	
	public void doEditZZ() throws Exception{

		service.editZZorder();
	}
	
	public void doEdit() throws Exception{

		service.editorder();
	}
	
	public void doEditPart() throws Exception{

		service.editorderPart();
	}	
	
	public void doApprove() throws Exception {
		
		service.approveAndView();			
		
	}
	
}
