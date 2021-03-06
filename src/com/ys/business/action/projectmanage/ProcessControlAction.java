package com.ys.business.action.projectmanage;

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
import com.ys.system.action.model.login.UserInfo;
import com.ys.business.action.model.processcontrol.ProcessControlModel;
import com.ys.system.common.BusinessConstants;
import com.ys.business.service.projectmanage.ProcessControlService;

@Controller
@RequestMapping("/business")
public class ProcessControlAction extends BaseAction {
	
	@Autowired
	ProcessControlService processControlService;
	
	@RequestMapping(value="processcontrol")
	public String execute(@RequestBody String data, @ModelAttribute("dataModels")ProcessControlModel dataModel, BindingResult result, Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response){
		
		String type = request.getParameter("methodtype");
		String rtnUrl = "";
		HashMap<String, Object> dataMap = null;
		ProcessControlModel viewModel = null;
		
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
				rtnUrl = "/business/processcontrol/processcontrolmain";
				break;
			case "search":
				dataMap = doSearch(data, session, request, response);
				printOutJsonObj(response, dataMap);
				return null;
			case "getProcessExpectCollect":
				dataMap = doGetProcessExpectCollect(data, session, request, response);
				printOutJsonObj(response, dataMap);
				return null;
			case "getProcessCheckPointCollect":
				dataMap = doGetProcessCheckPointCollect(data, session, request, response);
				printOutJsonObj(response, dataMap);
				return null;
			case "getProcessDetail":
				dataMap = doGetProcessDetail(data, session, request, response);
				printOutJsonObj(response, dataMap);
				return null;				
			case "updateinit":
				rtnUrl = doUpdateInit(model, session, request, response);
				break;
			case "update":
				viewModel = doUpdate(data, session, request);
				printOutJsonObj(response, viewModel.getEndInfoMap());
				return null;
			case "delete":
				viewModel = doDelete(data, session, request, response);
				printOutJsonObj(response, viewModel.getEndInfoMap());
				return null;
			case "deleteDetail":
				viewModel = doDeleteDetail(data, session, request, response);
				printOutJsonObj(response, viewModel.getEndInfoMap());
				return null;
			case "addexpectcollectinit":
				rtnUrl = doUpdateProcessControlDetailInit(model, session, request, response, 0);
				break;
			case "addnewexpectinit":
				rtnUrl = doUpdateProcessControlDetailInit(model, session, request, response, 1);
				break;
			case "addnewcheckpointinit":
				rtnUrl = doUpdateProcessControlDetailInit(model, session, request, response, 2);
				break;
			case "clearcheckpoint":
				viewModel = doClearCheckPoint(data, session, request, response);
				printOutJsonObj(response, viewModel.getEndInfoMap());				
				return null;
		}
		
		return rtnUrl;
	}	
	
	public HashMap<String, Object> doSearch(@RequestBody String data, HttpSession session, HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		
		try {
			UserInfo userInfo = (UserInfo)session.getAttribute(BusinessConstants.SESSION_USERINFO);
			dataMap = processControlService.doSearch(request, data, userInfo);
			ArrayList<HashMap<String, String>> dbData = (ArrayList<HashMap<String, String>>)dataMap.get("data");
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
	
	public HashMap<String, Object> doGetProcessExpectCollect(@RequestBody String data, HttpSession session, HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		
		try {
			UserInfo userInfo = (UserInfo)session.getAttribute(BusinessConstants.SESSION_USERINFO);
			dataMap = processControlService.doGetProcessExpectCollect(request, data, userInfo);
			ArrayList<HashMap<String, String>> dbData = (ArrayList<HashMap<String, String>>)dataMap.get("data");
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
	
	public HashMap<String, Object> doGetProcessCheckPointCollect(@RequestBody String data, HttpSession session, HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		
		try {
			UserInfo userInfo = (UserInfo)session.getAttribute(BusinessConstants.SESSION_USERINFO);
			dataMap = processControlService.doGetProcessCheckPointCollect(request, data, userInfo);
			ArrayList<HashMap<String, String>> dbData = (ArrayList<HashMap<String, String>>)dataMap.get("data");
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
	
	
	public HashMap<String, Object> doGetProcessDetail(@RequestBody String data, HttpSession session, HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		
		try {
			UserInfo userInfo = (UserInfo)session.getAttribute(BusinessConstants.SESSION_USERINFO);
			dataMap = processControlService.doGetProcessDetail(request, data, userInfo);
			ArrayList<HashMap<String, String>> dbData = (ArrayList<HashMap<String, String>>)dataMap.get("data");
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
	
	public String doUpdateInit(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response){

		ProcessControlModel dataModel = new ProcessControlModel();
		String key = request.getParameter("key");
		try {
			dataModel = processControlService.getProjectBaseInfo(request, key);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			dataModel.setMessage("发生错误，请联系系统管理员");
		}
		model.addAttribute("DisplayData", dataModel);
		
		return "/business/processcontrol/processcontroledit";
	}	
	
	
	public ProcessControlModel doUpdate(String data, HttpSession session, HttpServletRequest request){
		
		ProcessControlModel model = new ProcessControlModel();
		
		UserInfo userInfo = (UserInfo)session.getAttribute(BusinessConstants.SESSION_USERINFO);
		model = processControlService.doUpdate(request, data, userInfo);
		
		return model;
	}	
	
	public ProcessControlModel doDelete(@RequestBody String data, HttpSession session, HttpServletRequest request, HttpServletResponse response){
		ProcessControlModel model = new ProcessControlModel();
		
		UserInfo userInfo = (UserInfo)session.getAttribute(BusinessConstants.SESSION_USERINFO);
		model = processControlService.doDelete(request, data, userInfo);

		return model;
	}

	public ProcessControlModel doDeleteDetail(@RequestBody String data, HttpSession session, HttpServletRequest request, HttpServletResponse response){
		ProcessControlModel model = new ProcessControlModel();
		UserInfo userInfo = (UserInfo)session.getAttribute(BusinessConstants.SESSION_USERINFO);
		model = processControlService.doDelete(request, data, userInfo);

		return model;
	}	

	public String doUpdateProcessControlDetailInit(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response, int type){

		ProcessControlModel dataModel = new ProcessControlModel();
		
		String rtnUrl = "";
		
		if (type == 0) {
			rtnUrl = "/business/processcontrol/processcontrolcollectedit";
		}
		if (type == 1) {
			rtnUrl = "/business/processcontrol/processcontrolnewexpectedit";
		}
		if (type == 2) {
			rtnUrl = "/business/processcontrol/processcontrolnewcheckpoint";
		}
		
		String projectId = request.getParameter("key");
		String dataType = request.getParameter("type");
		String id = request.getParameter("id");
		try {
			dataModel = processControlService.doUpdateProcessControlDetailInit(request, projectId, id, dataType);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			dataModel.setMessage("发生错误，请联系系统管理员");
		}
		model.addAttribute("DisplayData", dataModel);
		
		return rtnUrl;
	}
	
	public ProcessControlModel doClearCheckPoint(@RequestBody String data, HttpSession session, HttpServletRequest request, HttpServletResponse response){
		ProcessControlModel model = new ProcessControlModel();
		UserInfo userInfo = (UserInfo)session.getAttribute(BusinessConstants.SESSION_USERINFO);
		model = processControlService.doClearCheckPoint(request, data, userInfo);

		return model;
	}	
}
