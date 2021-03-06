package com.ys.business.db.dao;

import java.sql.*;
import java.io.InputStream;
import com.ys.business.db.data.*;
import com.ys.util.basedao.BaseAbstractDao;

/**
* <p>Title: </p>
* <p>Description: ��ݱ�  </p>
* <p>Copyright: gentleman</p>
* <p>Company: gentleman</p>
* @author mengfanchang
* @version 1.0
*/
public class B_PurchasePlanDao extends BaseAbstractDao
{
	public B_PurchasePlanData beanData=new B_PurchasePlanData();
	public B_PurchasePlanDao()
	{
	}
	public B_PurchasePlanDao(Object beanData) throws Exception
	{
		try
		{
			this.beanData = (B_PurchasePlanData)FindByPrimaryKey(beanData);
		}
		catch(Exception e)
		{
			throw new Exception("Primary key not already exists");
		
		}
	}


	/**
	 *
	 * @param beanData
	 */
	public String Create(Object beanDataTmp) throws Exception
	{
		B_PurchasePlanData beanData = (B_PurchasePlanData) beanDataTmp; 
		Connection connection = null;
		PreparedStatement statement = null;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("INSERT INTO B_PurchasePlan( recordid,purchaseid,ysid,subid,materialid,unitquantity,supplierid,price,oldsupplierid,oldprice,pricestatus,orderquantity,quantity,totalprice,subbomid,remark,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag,formid)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			statement.setString( 1,beanData.getRecordid());			statement.setString( 2,beanData.getPurchaseid());			statement.setString( 3,beanData.getYsid());			statement.setString( 4,beanData.getSubid());			statement.setString( 5,beanData.getMaterialid());			statement.setString( 6,beanData.getUnitquantity());			statement.setString( 7,beanData.getSupplierid());			statement.setString( 8,beanData.getPrice());			statement.setString( 9,beanData.getOldsupplierid());			statement.setString( 10,beanData.getOldprice());			statement.setString( 11,beanData.getPricestatus());			statement.setString( 12,beanData.getOrderquantity());			statement.setString( 13,beanData.getQuantity());			statement.setString( 14,beanData.getTotalprice());			statement.setString( 15,beanData.getSubbomid());			statement.setString( 16,beanData.getRemark());			statement.setString( 17,beanData.getDeptguid());			statement.setString( 18,beanData.getCreatetime());			statement.setString( 19,beanData.getCreateperson());			statement.setString( 20,beanData.getCreateunitid());			statement.setString( 21,beanData.getModifytime());			statement.setString( 22,beanData.getModifyperson());			statement.setString( 23,beanData.getDeleteflag());			statement.setString( 24,beanData.getFormid());
			if (statement.executeUpdate() < 1)
				throw new Exception(" Can't Insert Row ");
			else
				return "Create success";
		}
		catch(Exception e)
		{
			throw new Exception("INSERT INTO B_PurchasePlan( recordid,purchaseid,ysid,subid,materialid,unitquantity,supplierid,price,oldsupplierid,oldprice,pricestatus,orderquantity,quantity,totalprice,subbomid,remark,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag,formid)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)��"+ e.toString());
		}
		finally
		{
			closeConnection(connection, statement);
		}
	}


	/**
	 *
	 * @param beanData
	 */
	public String getInsertSQL()
	{
		StringBuffer bufSQL = new StringBuffer();
		try
		{
			bufSQL.append("INSERT INTO B_PurchasePlan( recordid,purchaseid,ysid,subid,materialid,unitquantity,supplierid,price,oldsupplierid,oldprice,pricestatus,orderquantity,quantity,totalprice,subbomid,remark,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag,formid)VALUES(");
			bufSQL.append("'" + nullString(beanData.getRecordid()) + "',");			bufSQL.append("'" + nullString(beanData.getPurchaseid()) + "',");			bufSQL.append("'" + nullString(beanData.getYsid()) + "',");			bufSQL.append("'" + nullString(beanData.getSubid()) + "',");			bufSQL.append("'" + nullString(beanData.getMaterialid()) + "',");			bufSQL.append("'" + nullString(beanData.getUnitquantity()) + "',");			bufSQL.append("'" + nullString(beanData.getSupplierid()) + "',");			bufSQL.append("'" + nullString(beanData.getPrice()) + "',");			bufSQL.append("'" + nullString(beanData.getOldsupplierid()) + "',");			bufSQL.append("'" + nullString(beanData.getOldprice()) + "',");			bufSQL.append("'" + nullString(beanData.getPricestatus()) + "',");			bufSQL.append("'" + nullString(beanData.getOrderquantity()) + "',");			bufSQL.append("'" + nullString(beanData.getQuantity()) + "',");			bufSQL.append("'" + nullString(beanData.getTotalprice()) + "',");			bufSQL.append("'" + nullString(beanData.getSubbomid()) + "',");			bufSQL.append("'" + nullString(beanData.getRemark()) + "',");			bufSQL.append("'" + nullString(beanData.getDeptguid()) + "',");			bufSQL.append("'" + nullString(beanData.getCreatetime()) + "',");			bufSQL.append("'" + nullString(beanData.getCreateperson()) + "',");			bufSQL.append("'" + nullString(beanData.getCreateunitid()) + "',");			bufSQL.append("'" + nullString(beanData.getModifytime()) + "',");			bufSQL.append("'" + nullString(beanData.getModifyperson()) + "',");			bufSQL.append("'" + nullString(beanData.getDeleteflag()) + "',");			bufSQL.append("'" + nullString(beanData.getFormid()) + "'");
			bufSQL.append(")");

			beanData.setReturnsql(bufSQL.toString()); 
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			return bufSQL.toString();
		}
	}


	/**
	 *
	 * @param beanData
	 */
	public String Insert(Object beanDataTmp) throws Exception
	{
		B_PurchasePlanData beanData = (B_PurchasePlanData) beanDataTmp; 
		Connection connection = null;
		PreparedStatement statement = null;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("INSERT INTO B_PurchasePlan( recordid,purchaseid,ysid,subid,materialid,unitquantity,supplierid,price,oldsupplierid,oldprice,pricestatus,orderquantity,quantity,totalprice,subbomid,remark,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag,formid)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			statement.setString( 1,beanData.getRecordid());			statement.setString( 2,beanData.getPurchaseid());			statement.setString( 3,beanData.getYsid());			statement.setString( 4,beanData.getSubid());			statement.setString( 5,beanData.getMaterialid());			statement.setString( 6,beanData.getUnitquantity());			statement.setString( 7,beanData.getSupplierid());			statement.setString( 8,beanData.getPrice());			statement.setString( 9,beanData.getOldsupplierid());			statement.setString( 10,beanData.getOldprice());			statement.setString( 11,beanData.getPricestatus());			statement.setString( 12,beanData.getOrderquantity());			statement.setString( 13,beanData.getQuantity());			statement.setString( 14,beanData.getTotalprice());			statement.setString( 15,beanData.getSubbomid());			statement.setString( 16,beanData.getRemark());			statement.setString( 17,beanData.getDeptguid());			statement.setString( 18,beanData.getCreatetime());			statement.setString( 19,beanData.getCreateperson());			statement.setString( 20,beanData.getCreateunitid());			statement.setString( 21,beanData.getModifytime());			statement.setString( 22,beanData.getModifyperson());			statement.setString( 23,beanData.getDeleteflag());			statement.setString( 24,beanData.getFormid());
			if (statement.executeUpdate() < 1)
				throw new Exception(" Can't Insert Row ");
			else
				return "Create success";
		}
		catch(Exception e)
		{
			throw new Exception("INSERT INTO B_PurchasePlan( recordid,purchaseid,ysid,subid,materialid,unitquantity,supplierid,price,oldsupplierid,oldprice,pricestatus,orderquantity,quantity,totalprice,subbomid,remark,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag,formid)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)��"+ e.toString());
		}
		finally
		{
			closeConnection(connection, statement);
		}
	}

	/**
	 *
	 * @param statement
	 */
	public void Remove(Object beanDataTmp)  throws Exception
	{
		B_PurchasePlanData beanData = (B_PurchasePlanData) beanDataTmp; 
		Connection connection = null;
		PreparedStatement statement = null;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("DELETE FROM B_PurchasePlan WHERE  recordid =?");
			statement.setString( 1,beanData.getRecordid());
			if (statement.executeUpdate() < 1)
				throw new Exception("Error deleting row");
		}
		catch(Exception e)
		{
			throw new Exception("Error executing SQL DELETE FROM B_PurchasePlan: "+ e.toString());
		}
		finally
		{
			closeConnection(connection, statement);
		}
	}

	/**
	 *
	 * @param statement
	 */
	public String getDeleteSQL(Object beanDataTmp) 
	{
		B_PurchasePlanData beanData = (B_PurchasePlanData) beanDataTmp; 
		StringBuffer bufSQL = new StringBuffer();
		try
		{
			bufSQL.append("DELETE FROM B_PurchasePlan WHERE ");
			bufSQL.append("Recordid = " + "'" + nullString(beanData.getRecordid()) + "'");
			beanData.setReturnsql(bufSQL.toString()); 
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			return bufSQL.toString();
		}
	}

	/**
	 *
	 * @param statement
	 */
	public void RemoveByWhere(String astr_Where)  throws Exception
	{
		Connection connection = null;
		PreparedStatement statement = null;
		try
		{
			connection = getConnection();
			String str_Where=astr_Where;
			if(str_Where==null)
				str_Where="";
			str_Where=str_Where.trim(); 
			if(!str_Where.equals(""))
				str_Where=" WHERE " + str_Where ; 
			statement = connection.prepareStatement("DELETE FROM B_PurchasePlan"+ str_Where);
			if (statement.executeUpdate() < 1)
				throw new Exception("Error deleting row");
		}
		catch(Exception e)
		{
			throw new Exception("Error executing SQL DELETE FROM B_PurchasePlan: "+ e.toString());
		}
		finally
		{
			closeConnection(connection, statement);
		}
	}

	/**
	 *
	 * @param statement
	 */
	public Object FindByPrimaryKey(Object beanDataTmp)  throws Exception
	{
		B_PurchasePlanData beanData = (B_PurchasePlanData) beanDataTmp; 
		B_PurchasePlanData returnData=new B_PurchasePlanData();
		Connection connection = null;
		PreparedStatement statement = null;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("SELECT recordid,purchaseid,ysid,subid,materialid,unitquantity,supplierid,price,oldsupplierid,oldprice,pricestatus,orderquantity,quantity,totalprice,subbomid,remark,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag,formid FROM B_PurchasePlan WHERE  recordid =?");
			statement.setString( 1,beanData.getRecordid());
			ResultSet resultSet = statement.executeQuery();
			if (!resultSet.next())
			{
				throw new Exception(" Row Not does;");
			}
			returnData.setRecordid( resultSet.getString( 1));			returnData.setPurchaseid( resultSet.getString( 2));			returnData.setYsid( resultSet.getString( 3));			returnData.setSubid( resultSet.getString( 4));			returnData.setMaterialid( resultSet.getString( 5));			returnData.setUnitquantity( resultSet.getString( 6));			returnData.setSupplierid( resultSet.getString( 7));			returnData.setPrice( resultSet.getString( 8));			returnData.setOldsupplierid( resultSet.getString( 9));			returnData.setOldprice( resultSet.getString( 10));			returnData.setPricestatus( resultSet.getString( 11));			returnData.setOrderquantity( resultSet.getString( 12));			returnData.setQuantity( resultSet.getString( 13));			returnData.setTotalprice( resultSet.getString( 14));			returnData.setSubbomid( resultSet.getString( 15));			returnData.setRemark( resultSet.getString( 16));			returnData.setDeptguid( resultSet.getString( 17));			returnData.setCreatetime( resultSet.getString( 18));			returnData.setCreateperson( resultSet.getString( 19));			returnData.setCreateunitid( resultSet.getString( 20));			returnData.setModifytime( resultSet.getString( 21));			returnData.setModifyperson( resultSet.getString( 22));			returnData.setDeleteflag( resultSet.getString( 23));			returnData.setFormid( resultSet.getString( 24));
			return returnData;
		}
		catch(Exception e)
		{
			throw new Exception("Error executing SQL SELECT recordid,purchaseid,ysid,subid,materialid,unitquantity,supplierid,price,oldsupplierid,oldprice,pricestatus,orderquantity,quantity,totalprice,subbomid,remark,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag,formid FROM B_PurchasePlan  WHERE  "+e.toString());
		}
		finally
		{
			closeConnection(connection, statement);
		}
	}

	/**
	 *
	 * @param statement
	 */
	public java.util.Vector Find(String astr_Where)  throws Exception
	{
		Connection connection = null;
		PreparedStatement statement = null;
		java.util.Vector v_1 = new java.util.Vector();
		try
		{
			connection = getConnection();
			String str_Where=astr_Where;
			if(str_Where==null)
				str_Where="";
			str_Where=str_Where.trim(); 
			if(!str_Where.equals(""))
				str_Where=" WHERE " + str_Where ; 
			statement = connection.prepareStatement("SELECT recordid,purchaseid,ysid,subid,materialid,unitquantity,supplierid,price,oldsupplierid,oldprice,pricestatus,orderquantity,quantity,totalprice,subbomid,remark,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag,formid FROM B_PurchasePlan"+str_Where);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next())
			{
				B_PurchasePlanData returnData=new B_PurchasePlanData();
				returnData.setRecordid( resultSet.getString( 1));				returnData.setPurchaseid( resultSet.getString( 2));				returnData.setYsid( resultSet.getString( 3));				returnData.setSubid( resultSet.getString( 4));				returnData.setMaterialid( resultSet.getString( 5));				returnData.setUnitquantity( resultSet.getString( 6));				returnData.setSupplierid( resultSet.getString( 7));				returnData.setPrice( resultSet.getString( 8));				returnData.setOldsupplierid( resultSet.getString( 9));				returnData.setOldprice( resultSet.getString( 10));				returnData.setPricestatus( resultSet.getString( 11));				returnData.setOrderquantity( resultSet.getString( 12));				returnData.setQuantity( resultSet.getString( 13));				returnData.setTotalprice( resultSet.getString( 14));				returnData.setSubbomid( resultSet.getString( 15));				returnData.setRemark( resultSet.getString( 16));				returnData.setDeptguid( resultSet.getString( 17));				returnData.setCreatetime( resultSet.getString( 18));				returnData.setCreateperson( resultSet.getString( 19));				returnData.setCreateunitid( resultSet.getString( 20));				returnData.setModifytime( resultSet.getString( 21));				returnData.setModifyperson( resultSet.getString( 22));				returnData.setDeleteflag( resultSet.getString( 23));				returnData.setFormid( resultSet.getString( 24));
				v_1.add(returnData);
			}
			return v_1;
		}
		catch(Exception e)
		{
			throw new Exception("Error executing SQL SELECT recordid,purchaseid,ysid,subid,materialid,unitquantity,supplierid,price,oldsupplierid,oldprice,pricestatus,orderquantity,quantity,totalprice,subbomid,remark,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag,formid FROM B_PurchasePlan" + astr_Where +e.toString());
		}
		finally
		{
			closeConnection(connection, statement);
		}
	}

	/**
	 *
	 * @param statement
	 */
	public void Store()  throws Exception
	{
		Connection connection = null;
		PreparedStatement statement = null;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("UPDATE B_PurchasePlan SET recordid= ? , purchaseid= ? , ysid= ? , subid= ? , materialid= ? , unitquantity= ? , supplierid= ? , price= ? , oldsupplierid= ? , oldprice= ? , pricestatus= ? , orderquantity= ? , quantity= ? , totalprice= ? , subbomid= ? , remark= ? , deptguid= ? , createtime= ? , createperson= ? , createunitid= ? , modifytime= ? , modifyperson= ? , deleteflag= ? , formid=? WHERE  recordid  = ?");
			statement.setString( 1,beanData.getRecordid());			statement.setString( 2,beanData.getPurchaseid());			statement.setString( 3,beanData.getYsid());			statement.setString( 4,beanData.getSubid());			statement.setString( 5,beanData.getMaterialid());			statement.setString( 6,beanData.getUnitquantity());			statement.setString( 7,beanData.getSupplierid());			statement.setString( 8,beanData.getPrice());			statement.setString( 9,beanData.getOldsupplierid());			statement.setString( 10,beanData.getOldprice());			statement.setString( 11,beanData.getPricestatus());			statement.setString( 12,beanData.getOrderquantity());			statement.setString( 13,beanData.getQuantity());			statement.setString( 14,beanData.getTotalprice());			statement.setString( 15,beanData.getSubbomid());			statement.setString( 16,beanData.getRemark());			statement.setString( 17,beanData.getDeptguid());			statement.setString( 18,beanData.getCreatetime());			statement.setString( 19,beanData.getCreateperson());			statement.setString( 20,beanData.getCreateunitid());			statement.setString( 21,beanData.getModifytime());			statement.setString( 22,beanData.getModifyperson());			statement.setString( 23,beanData.getDeleteflag());			statement.setString( 24,beanData.getFormid());
			statement.setString( 25,beanData.getRecordid());
			if (statement.executeUpdate() < 1)
				throw new Exception(" Row Not does; ");
		}
		catch(Exception e)
		{
			throw new Exception("UPDATE B_PurchasePlan SET recordid= ? , purchaseid= ? , ysid= ? , subid= ? , materialid= ? , unitquantity= ? , supplierid= ? , price= ? , oldsupplierid= ? , oldprice= ? , pricestatus= ? , orderquantity= ? , quantity= ? , totalprice= ? , subbomid= ? , remark= ? , deptguid= ? , createtime= ? , createperson= ? , createunitid= ? , modifytime= ? , modifyperson= ? , deleteflag= ? , formid=? WHERE  recordid  = ?"+ e.toString());
		}
		finally
		{
			closeConnection(connection, statement);
		}
	}

	/**
	 *
	 * @param statement
	 */
	public String getUpdateSQL()
	{
		StringBuffer bufSQL = new StringBuffer();
		try
		{
			bufSQL.append("UPDATE B_PurchasePlan SET ");
			bufSQL.append("Recordid = " + "'" + nullString(beanData.getRecordid()) + "',");			bufSQL.append("Purchaseid = " + "'" + nullString(beanData.getPurchaseid()) + "',");			bufSQL.append("Ysid = " + "'" + nullString(beanData.getYsid()) + "',");			bufSQL.append("Subid = " + "'" + nullString(beanData.getSubid()) + "',");			bufSQL.append("Materialid = " + "'" + nullString(beanData.getMaterialid()) + "',");			bufSQL.append("Unitquantity = " + "'" + nullString(beanData.getUnitquantity()) + "',");			bufSQL.append("Supplierid = " + "'" + nullString(beanData.getSupplierid()) + "',");			bufSQL.append("Price = " + "'" + nullString(beanData.getPrice()) + "',");			bufSQL.append("Oldsupplierid = " + "'" + nullString(beanData.getOldsupplierid()) + "',");			bufSQL.append("Oldprice = " + "'" + nullString(beanData.getOldprice()) + "',");			bufSQL.append("Pricestatus = " + "'" + nullString(beanData.getPricestatus()) + "',");			bufSQL.append("Orderquantity = " + "'" + nullString(beanData.getOrderquantity()) + "',");			bufSQL.append("Quantity = " + "'" + nullString(beanData.getQuantity()) + "',");			bufSQL.append("Totalprice = " + "'" + nullString(beanData.getTotalprice()) + "',");			bufSQL.append("Subbomid = " + "'" + nullString(beanData.getSubbomid()) + "',");			bufSQL.append("Remark = " + "'" + nullString(beanData.getRemark()) + "',");			bufSQL.append("Deptguid = " + "'" + nullString(beanData.getDeptguid()) + "',");			bufSQL.append("Createtime = " + "'" + nullString(beanData.getCreatetime()) + "',");			bufSQL.append("Createperson = " + "'" + nullString(beanData.getCreateperson()) + "',");			bufSQL.append("Createunitid = " + "'" + nullString(beanData.getCreateunitid()) + "',");			bufSQL.append("Modifytime = " + "'" + nullString(beanData.getModifytime()) + "',");			bufSQL.append("Modifyperson = " + "'" + nullString(beanData.getModifyperson()) + "',");			bufSQL.append("Deleteflag = " + "'" + nullString(beanData.getDeleteflag()) + "',");			bufSQL.append("Formid = " + "'" + nullString(beanData.getFormid()) + "'");
			bufSQL.append(" WHERE ");
			bufSQL.append("Recordid = " + "'" + nullString(beanData.getRecordid()) + "'");
			beanData.setReturnsql(bufSQL.toString()); 
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			return bufSQL.toString();
		}
	}

	/**
	 *
	 * @param statement
	 */
	public void Store(Object data)  throws Exception
	{
		B_PurchasePlanData beanData = (B_PurchasePlanData)data;
		Connection connection = null;
		PreparedStatement statement = null;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("UPDATE B_PurchasePlan SET recordid= ? , purchaseid= ? , ysid= ? , subid= ? , materialid= ? , unitquantity= ? , supplierid= ? , price= ? , oldsupplierid= ? , oldprice= ? , pricestatus= ? , orderquantity= ? , quantity= ? , totalprice= ? , subbomid= ? , remark= ? , deptguid= ? , createtime= ? , createperson= ? , createunitid= ? , modifytime= ? , modifyperson= ? , deleteflag= ? , formid=? WHERE  recordid  = ?");
			statement.setString( 1,beanData.getRecordid());			statement.setString( 2,beanData.getPurchaseid());			statement.setString( 3,beanData.getYsid());			statement.setString( 4,beanData.getSubid());			statement.setString( 5,beanData.getMaterialid());			statement.setString( 6,beanData.getUnitquantity());			statement.setString( 7,beanData.getSupplierid());			statement.setString( 8,beanData.getPrice());			statement.setString( 9,beanData.getOldsupplierid());			statement.setString( 10,beanData.getOldprice());			statement.setString( 11,beanData.getPricestatus());			statement.setString( 12,beanData.getOrderquantity());			statement.setString( 13,beanData.getQuantity());			statement.setString( 14,beanData.getTotalprice());			statement.setString( 15,beanData.getSubbomid());			statement.setString( 16,beanData.getRemark());			statement.setString( 17,beanData.getDeptguid());			statement.setString( 18,beanData.getCreatetime());			statement.setString( 19,beanData.getCreateperson());			statement.setString( 20,beanData.getCreateunitid());			statement.setString( 21,beanData.getModifytime());			statement.setString( 22,beanData.getModifyperson());			statement.setString( 23,beanData.getDeleteflag());			statement.setString( 24,beanData.getFormid());
			statement.setString( 25,beanData.getRecordid());
			if (statement.executeUpdate() < 1)
				throw new Exception(" Row Not does; ");
		}
		catch(Exception e)
		{
			throw new Exception("UPDATE B_PurchasePlan SET recordid= ? , purchaseid= ? , ysid= ? , subid= ? , materialid= ? , unitquantity= ? , supplierid= ? , price= ? , oldsupplierid= ? , oldprice= ? , pricestatus= ? , orderquantity= ? , quantity= ? , totalprice= ? , subbomid= ? , remark= ? , deptguid= ? , createtime= ? , createperson= ? , createunitid= ? , modifytime= ? , modifyperson= ? , deleteflag= ? , formid=? WHERE  recordid  = ?"+ e.toString());
		}
		finally
		{
			closeConnection(connection, statement);
		}
	}

	/**
	 *
	 * @param beanData:����
	 */
	public void FindPrimaryKey(Object beanDataTmp) throws Exception
	{
		Connection connection = null;
		PreparedStatement statement = null;
		try
		{
		B_PurchasePlanData beanData = (B_PurchasePlanData) beanDataTmp; 
			connection = getConnection();
			statement = connection.prepareStatement("SELECT  recordid  FROM B_PurchasePlan WHERE  recordid =?");
			statement.setString( 1,beanData.getRecordid());
			ResultSet resultSet = statement.executeQuery();
			if (!resultSet.next())
			{
				throw new Exception(" Primary Key Not does");
			}
		}
		catch(Exception e)
		{
			throw new Exception("Error executing SQL  SELECT  recordid  FROM B_PurchasePlan WHERE  recordid =?");
		}
		finally
		{
			closeConnection(connection, statement);
		}
	}

}
