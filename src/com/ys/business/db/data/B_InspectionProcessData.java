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
public class B_InspectionProcessData implements java.io.Serializable
{

	public B_InspectionProcessData()
	{
	}

	/**
	*
	*/
	private String recordid;
	public String getRecordid()
	{
		return this.recordid;
	}
	public void setRecordid(String recordid)
	{
		this.recordid=recordid;
	}

	/**
	*
	*/
	private String ysid;
	public String getYsid()
	{
		return this.ysid;
	}
	public void setYsid(String ysid)
	{
		this.ysid=ysid;
	}

	/**
	*
	*/
	private String arrivalid;
	public String getArrivalid()
	{
		return this.arrivalid;
	}
	public void setArrivalid(String arrivalid)
	{
		this.arrivalid=arrivalid;
	}

	/**
	*
	*/
	private String materialid;
	public String getMaterialid()
	{
		return this.materialid;
	}
	public void setMaterialid(String materialid)
	{
		this.materialid=materialid;
	}

	/**
	*
	*/
	private String result;
	public String getResult()
	{
		return this.result;
	}
	public void setResult(String result)
	{
		this.result=result;
	}

	/**
	*
	*/
	private String checker;
	public String getChecker()
	{
		return this.checker;
	}
	public void setChecker(String checker)
	{
		this.checker=checker;
	}

	/**
	*
	*/
	private String checkresult;
	public String getCheckresult()
	{
		return this.checkresult;
	}
	public void setCheckresult(String checkresult)
	{
		this.checkresult=checkresult;
	}

	/**
	*
	*/
	private String checkdate;
	public String getCheckdate()
	{
		return this.checkdate;
	}
	public void setCheckdate(String checkdate)
	{
		this.checkdate=checkdate;
	}

	/**
	*
	*/
	private String managerresult;
	public String getManagerresult()
	{
		return this.managerresult;
	}
	public void setManagerresult(String managerresult)
	{
		this.managerresult=managerresult;
	}

	/**
	*
	*/
	private String managerdate;
	public String getManagerdate()
	{
		return this.managerdate;
	}
	public void setManagerdate(String managerdate)
	{
		this.managerdate=managerdate;
	}

	/**
	*
	*/
	private String managerfeedback;
	public String getManagerfeedback()
	{
		return this.managerfeedback;
	}
	public void setManagerfeedback(String managerfeedback)
	{
		this.managerfeedback=managerfeedback;
	}

	/**
	*
	*/
	private String gmdate;
	public String getGmdate()
	{
		return this.gmdate;
	}
	public void setGmdate(String gmdate)
	{
		this.gmdate=gmdate;
	}

	/**
	*
	*/
	private String gmfeedback;
	public String getGmfeedback()
	{
		return this.gmfeedback;
	}
	public void setGmfeedback(String gmfeedback)
	{
		this.gmfeedback=gmfeedback;
	}

	/**
	*
	*/
	private String gmresult;
	public String getGmresult()
	{
		return this.gmresult;
	}
	public void setGmresult(String gmresult)
	{
		this.gmresult=gmresult;
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
	*
	*/
	private String formid;
	public String getFormid()
	{
		return this.formid;
	}
	public void setFormid(String formid)
	{
		this.formid=formid;
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
		recordid= (recordid == null ?null : recordid.trim());
	}

}