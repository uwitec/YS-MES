package com.ys.business.db.data;

import java.sql.*;
import java.io.InputStream;

/**
* <p>Title: </p>
* <p>Description: </p>
* <p>Copyright: gentleman</p>
* <p>Company: gentleman</p>
* @author mengfanchang
* @version 1.0
*/
public class B_ProcessControlData implements java.io.Serializable
{

	public B_ProcessControlData()
	{
	}

	/**
	*
	*/
	private String id;
	public String getId()
	{
		return this.id;
	}
	public void setId(String id)
	{
		this.id=id;
	}

	/**
	*
	*/
	private String projectid;
	public String getProjectid()
	{
		return this.projectid;
	}
	public void setProjectid(String projectid)
	{
		this.projectid=projectid;
	}

	/**
	*
	*/
	private String type;
	public String getType()
	{
		return this.type;
	}
	public void setType(String type)
	{
		this.type=type;
	}

	/**
	*
	*/
	private String createdate;
	public String getCreatedate()
	{
		return this.createdate;
	}
	public void setCreatedate(String createdate)
	{
		this.createdate=createdate;
	}

	/**
	*
	*/
	private String expectdate;
	public String getExpectdate()
	{
		return this.expectdate;
	}
	public void setExpectdate(String expectdate)
	{
		this.expectdate=expectdate;
	}

	/**
	*
	*/
	private String reason;
	public String getReason()
	{
		return this.reason;
	}
	public void setReason(String reason)
	{
		this.reason=reason;
	}

	/**
	*
	*/
	private String finishtime;
	public String getFinishtime()
	{
		return this.finishtime;
	}
	public void setFinishtime(String finishtime)
	{
		this.finishtime=finishtime;
	}

	/**
	*
	*/
	private String confirm;
	public String getConfirm()
	{
		return this.confirm;
	}
	public void setConfirm(String confirm)
	{
		this.confirm=confirm;
	}

	/**
	*
	*/
	private String deptguid;
	public String getDeptguid()
	{
		return this.deptguid;
	}
	public void setDeptguid(String deptguid)
	{
		this.deptguid=deptguid;
	}

	/**
	*
	*/
	private String createtime;
	public String getCreatetime()
	{
		return this.createtime;
	}
	public void setCreatetime(String createtime)
	{
		this.createtime=createtime;
	}

	/**
	*
	*/
	private String createperson;
	public String getCreateperson()
	{
		return this.createperson;
	}
	public void setCreateperson(String createperson)
	{
		this.createperson=createperson;
	}

	/**
	*
	*/
	private String createunitid;
	public String getCreateunitid()
	{
		return this.createunitid;
	}
	public void setCreateunitid(String createunitid)
	{
		this.createunitid=createunitid;
	}

	/**
	*
	*/
	private String modifytime;
	public String getModifytime()
	{
		return this.modifytime;
	}
	public void setModifytime(String modifytime)
	{
		this.modifytime=modifytime;
	}

	/**
	*
	*/
	private String modifyperson;
	public String getModifyperson()
	{
		return this.modifyperson;
	}
	public void setModifyperson(String modifyperson)
	{
		this.modifyperson=modifyperson;
	}

	/**
	*
	*/
	private String deleteflag;
	public String getDeleteflag()
	{
		return this.deleteflag;
	}
	public void setDeleteflag(String deleteflag)
	{
		this.deleteflag=deleteflag;
	}

	/**
	*����ֵ
	*/
	private String returnvalue;
	public String getReturnvalue()
	{
		return this.returnvalue;
	}
	public void setReturnvalue(String returnvalue)
	{
		this.returnvalue=returnvalue;
	}

	/**
	*����ֵ
	*/
	private String returnsql;
	public String getReturnsql()
	{
		return this.returnsql;
	}
	public void setReturnsql(String returnsql)
	{
		this.returnsql=returnsql;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("");
		sb.append("***** DataObject list begin *****\n");
		return sb.toString() ;
	}


	public void toTrim() {
		id= (id == null ?null : id.trim());
	}

}