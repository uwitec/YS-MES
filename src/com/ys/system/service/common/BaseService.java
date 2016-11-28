package com.ys.system.service.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ys.util.basedao.BaseTransaction;
import com.ys.util.basequery.BaseQuery;

/**
 * @author 
 *
 */
public class BaseService {
	
	protected final String DUMMYKEY = "-1";
	protected final String SYSTEMERROR = "-1";
	protected final String NORMAL = "000";
	
	/*
	public BaseTransaction baseTransaction;
	
	public void begin() throws Exception {
		baseTransaction = new BaseTransaction();
		baseTransaction.begin();
	}
	
	public void commit() throws Exception {
		baseTransaction.commit();
	}
	
	public void rollback() throws Exception {
		baseTransaction.rollback();
	}
	*/

	public String getJsonData(String formData, String key){
	    String dataValue = new String();
	
	    try
	    {
	    	
	        JSONArray jArray = new JSONArray(formData);
	        
	        for (int i = 0; i < jArray.length(); i++)
	        {
	            JSONObject resultJsnObj = jArray.getJSONObject(i);
	            if (resultJsnObj.get("name").equals(key))
	            {
	            	dataValue = String.valueOf(resultJsnObj.get("value"));
	            	break;
	                //dataValue.add((String) resultJsnObj.get("value"));
	                //if (isOnlyOne)
	                //{
	                //    break;
	                //}
	            }
	        }
	        
	    }
	    catch(Exception e)
	    {
	        System.out.println(e.getMessage());
	    }
	
	    return dataValue;
	}
	
	protected String getMsg(String key) {
		String fileName = "/setting/messages_zh_CN";
		return BaseQuery.getContent(fileName, key);
	}
	

	
	/*
	 * mapToBean
	 * parm:map
	 */
	public Object mapToObject(
			Map<String, Object> map, 
			Class<?> beanClass)  throws Exception {    
        
		if (map == null || beanClass == null) { 
            return null;  
		}		
        Object obj = beanClass.newInstance();  
  
        BeanUtils.populate(obj, map);  
  
        return obj;  
	}  
	

	/*
	 * mapToBean
	 * parm:map
	 */
	public Object AaaryListMapToObject(
			ArrayList<HashMap<String, String>> ArrayMap, 
			Class<?> beanClass)  throws Exception {    
		
		ArrayList rtnList = new ArrayList();
        		
		if (ArrayMap == null || beanClass == null) { 
            return null;  
		}	
		int i = 0;
		for(HashMap<String, String> map:ArrayMap){
			rtnList.add(i,mapToObject((Map) map,beanClass));
			i++;
		}
         
  
        return rtnList;  
	} 
}
