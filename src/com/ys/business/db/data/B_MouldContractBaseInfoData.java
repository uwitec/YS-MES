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
public class B_MouldContractBaseInfoData 
{

	public B_MouldContractBaseInfoData()
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
	private String contractid;
	public String getContractid()
	{
		return this.contractid;
	}
	public void setContractid(String contractid)
	{
		this.contractid=contractid;
	}

	/**
	*
	*/
	private String contractyear;
	public String getContractyear()
	{
		return this.contractyear;
	}
	public void setContractyear(String contractyear)
	{
		this.contractyear=contractyear;
	}

	/**
	*
	*/
	private String productmodelid;
	public String getProductmodelid()
	{
		return this.productmodelid;
	}
	public void setProductmodelid(String productmodelid)
	{
		this.productmodelid=productmodelid;
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
	private String supplierid;
	public String getSupplierid()
	{
		return this.supplierid;
	}
	public void setSupplierid(String supplierid)
	{
		this.supplierid=supplierid;
	}

	/**
	*
	*/
	private String contractdate;
	public String getContractdate()
	{
		return this.contractdate;
	}
	public void setContractdate(String contractdate)
	{
		this.contractdate=contractdate;
	}

	/**
	*
	*/
	private String deliverdate;
	public String getDeliverdate()
	{
		return this.deliverdate;
	}
	public void setDeliverdate(String deliverdate)
	{
		this.deliverdate=deliverdate;
	}

	/**
	*
	*/
	private String belong;
	public String getBelong()
	{
		return this.belong;
	}
	public void setBelong(String belong)
	{
		this.belong=belong;
	}

	/**
	*
	*/
	private String oursidepay;
	public String getOursidepay()
	{
		return this.oursidepay;
	}
	public void setOursidepay(String oursidepay)
	{
		this.oursidepay=oursidepay;
	}

	/**
	*
	*/
	private String providerpay;
	public String getProviderpay()
	{
		return this.providerpay;
	}
	public void setProviderpay(String providerpay)
	{
		this.providerpay=providerpay;
	}

	/**
	*
	*/
	private String returncase;
	public String getReturncase()
	{
		return this.returncase;
	}
	public void setReturncase(String returncase)
	{
		this.returncase=returncase;
	}

	/**
	*
	*/
	private String status;
	public String getStatus()
	{
		return this.status;
	}
	public void setStatus(String status)
	{
		this.status=status;
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