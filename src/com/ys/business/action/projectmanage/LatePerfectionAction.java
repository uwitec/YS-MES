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
import com.ys.business.action.model.lateperfection.LatePerfectionModel;
import com.ys.business.action.model.processcontrol.ProcessControlModel;
import com.ys.system.common.BusinessConstants;
import com.ys.business.service.projectmanage.LatePerfectionService;
import com.ys.business.service.projectmanage.ProcessControlService;

@Controller
@RequestMapping("/business")
public class LatePerfectionAction extends BaseAction {
	
	@Autowired
	LatePerfectionService latePerfectionService;
	
	@Autowired
	ProcessControlService processControlService;
	
	
	@RequestMapping(value="lateperfection")
	public String execute(@RequestBody String data, @ModelAttribute("dataModels")LatePerfectionModel dataModel, BindingResult result, Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response){
		
		String type = request.getParameter("methodtype");
		String rtnUrl = "";
		HashMap<String, Object> dataMap = null;
		LatePerfectionModel viewModel = null;
		
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
				rtnUrl = "/business/lateperfection/lateperfectionmain";
				break;
			case "search":
				dataMap = doSearch(data, session, request, response);
				printOutJsonObj(response, dataMap);
				return null;
			case "addinit":
			case "updateinit":
				rtnUrl = doGetProjectBaseInfo(model, session, request, response);
				break;
			case "delete":
				viewModel = doDelete(data, session, request, response);
				printOutJsonObj(response, viewModel.getEndInfoMap());
				return null;
			case "deleteDetail":
				viewModel = doDeleteDetail(data, session, request, response);
				printOutJsonObj(response, viewModel.getEndInfoMap());
				return null;
			case "getTPFileList":
				dataMap = getTPFileList(data, session, request, response);
				printOutJsonObj(response, dataMap);
				return null;
			case "getQuestionList":
				dataMap = getQuestionList(data, session, request, response);
				printOutJsonObj(response, dataMap);
				return null;
			case "addtpfileinit":		
			case "updatetpfileinit":
				rtnUrl = doUpdateTPFileInit(model, session, request, response);
				break;				
			case "deletetpfile":
				viewModel = doDeleteTPFile(data, session, request, response);
				printOutJsonObj(response, viewModel.getEndInfoMap());
				return null;				
			case "updatetpfile":
				viewModel = doUpdateTPFile(data, session, request, response);
				printOutJsonObj(response, viewModel.getEndInfoMap());
				return null;	
			case "addquestioninit":
			case "updatequestioninit":
				rtnUrl = doUpdateQuestionInit(model, session, request, response);
				break;	
			case "deletequestion":
				viewModel = doDeleteQuestion(data, session, request, response);
				printOutJsonObj(response, viewModel.getEndInfoMap());
				return null;	
			case "updatequestion":
				viewModel = doUpdateQuestion(data, session, request, response);
				printOutJsonObj(response, viewModel.getEndInfoMap());
				return null;
			case "openfilebrowser":
				super.openFileBrowser(request, session, model);
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
	
	public String doGetProjectBaseInfo(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response){

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
		
		return "/business/lateperfection/lateperfectionedit";
	}		
	
	public HashMap<String, Object> getTPFileList(@RequestBody String data, HttpSession session, HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		
		try {
			UserInfo userInfo = (UserInfo)session.getAttribute(BusinessConstants.SESSION_USERINFO);
			dataMap = latePerfectionService.doGetTPFileList(request, data, userInfo);
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
	
	public HashMap<String, Object> getQuestionList(@RequestBody String data, HttpSession session, HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		
		try {
			UserInfo userInfo = (UserInfo)session.getAttribute(BusinessConstants.SESSION_USERINFO);
			dataMap = latePerfectionService.doGetQuestionList(request, data, userInfo);
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
	
	public String doUpdateTPFileInit(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response){

		LatePerfectionModel dataModel = new LatePerfectionModel();
		String projectId = request.getParameter("projectId");
		String key = request.getParameter("key");

		try {
			dataModel = latePerfectionService.doUpdateTPFileInit(request, projectId, key);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			dataModel.setMessage("发生错误，请联系系统管理员");
		}
		model.addAttribute("DisplayData", dataModel);
		
		return "/business/lateperfection/lateperfectionrelationfileedit";
	}	
	
	public String doUpdateQuestionInit(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response){

		LatePerfectionModel dataModel = new LatePerfectionModel();
		String projectId = request.getParameter("projectId");
		String key = request.getParameter("key");

		try {
			dataModel = latePerfectionService.doUpdateQuestionInit(request, projectId, key);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			dataModel.setMessage("发生错误，请联系系统管理员");
		}
		model.addAttribute("DisplayData", dataModel);
		
		return "/business/lateperfection/lateperfectionquestionedit";
	}	
	
	
	public LatePerfectionModel doUpdateTPFile(String data, HttpSession session, HttpServletRequest request, HttpServletResponse response){
		
		LatePerfectionModel model = new LatePerfectionModel();
		
		UserInfo userInfo = (UserInfo)session.getAttribute(BusinessConstants.SESSION_USERINFO);
		model = latePerfectionService.doUpdateTPFile(request, data, userInfo);
		
		return model;
	}		
	
	public LatePerfectionModel doUpdateQuestion(String data, HttpSession session, HttpServletRequest request, HttpServletResponse response){
		
		LatePerfectionModel model = new LatePerfectionModel();
		
		UserInfo userInfo = (UserInfo)session.getAttribute(BusinessConstants.SESSION_USERINFO);
		model = latePerfectionService.doUpdateQuestion(request, data, userInfo);
		
		return model;
	}	
	
	public LatePerfectionModel doDeleteTPFile(@RequestBody String data, HttpSession session, HttpServletRequest request, HttpServletResponse response){
		LatePerfectionModel model = new LatePerfectionModel();
		
		UserInfo userInfo = (UserInfo)session.getAttribute(BusinessConstants.SESSION_USERINFO);
		model = latePerfectionService.doDeleteTPFile(request, data, userInfo);

		return model;
	}
	
	public LatePerfectionModel doDeleteQuestion(@RequestBody String data, HttpSession session, HttpServletRequest request, HttpServletResponse response){
		LatePerfectionModel model = new LatePerfectionModel();
		
		UserInfo userInfo = (UserInfo)session.getAttribute(BusinessConstants.SESSION_USERINFO);
		model = latePerfectionService.doDeleteQuestion(request, data, userInfo);

		return model;
	}	
	
	public LatePerfectionModel doDelete(@RequestBody String data, HttpSession session, HttpServletRequest request, HttpServletResponse response){
		LatePerfectionModel model = new LatePerfectionModel();
		
		UserInfo userInfo = (UserInfo)session.getAttribute(BusinessConstants.SESSION_USERINFO);
		model = latePerfectionService.doDelete(request, data, userInfo);

		return model;
	}

	public LatePerfectionModel doDeleteDetail(@RequestBody String data, HttpSession session, HttpServletRequest request, HttpServletResponse response){
		LatePerfectionModel model = new LatePerfectionModel();
		UserInfo userInfo = (UserInfo)session.getAttribute(BusinessConstants.SESSION_USERINFO);
		model = latePerfectionService.doDelete(request, data, userInfo);

		return model;
	}
	
	
	
}
