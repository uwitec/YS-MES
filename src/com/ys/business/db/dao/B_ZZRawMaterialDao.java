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
public class B_ZZRawMaterialDao extends BaseAbstractDao
{
	public B_ZZRawMaterialData beanData=new B_ZZRawMaterialData();
	public B_ZZRawMaterialDao()
	{
	}
	public B_ZZRawMaterialDao(Object beanData) throws Exception
	{
		try
		{
			this.beanData = (B_ZZRawMaterialData)FindByPrimaryKey(beanData);
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
		B_ZZRawMaterialData beanData = (B_ZZRawMaterialData) beanDataTmp; 
		Connection connection = null;
		PreparedStatement statement = null;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("INSERT INTO B_ZZRawMaterial( recordid,materialid,rawmaterialid,unit,netweight,wastage,weight,kgprice,materialprice,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag,formid)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			statement.setString( 1,beanData.getRecordid());			statement.setString( 2,beanData.getMaterialid());			statement.setString( 3,beanData.getRawmaterialid());			statement.setString( 4,beanData.getUnit());			statement.setString( 5,beanData.getNetweight());			statement.setString( 6,beanData.getWastage());			statement.setString( 7,beanData.getWeight());			statement.setString( 8,beanData.getKgprice());			statement.setString( 9,beanData.getMaterialprice());			statement.setString( 10,beanData.getDeptguid());			statement.setString( 11,beanData.getCreatetime());			statement.setString( 12,beanData.getCreateperson());			statement.setString( 13,beanData.getCreateunitid());			statement.setString( 14,beanData.getModifytime());			statement.setString( 15,beanData.getModifyperson());			statement.setString( 16,beanData.getDeleteflag());			statement.setString( 17,beanData.getFormid());
			if (statement.executeUpdate() < 1)
				throw new Exception(" Can't Insert Row ");
			else
				return "Create success";
		}
		catch(Exception e)
		{
			throw new Exception("INSERT INTO B_ZZRawMaterial( recordid,materialid,rawmaterialid,unit,netweight,wastage,weight,kgprice,materialprice,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag,formid)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)��"+ e.toString());
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
			bufSQL.append("INSERT INTO B_ZZRawMaterial( recordid,materialid,rawmaterialid,unit,netweight,wastage,weight,kgprice,materialprice,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag,formid)VALUES(");
			bufSQL.append("'" + nullString(beanData.getRecordid()) + "',");			bufSQL.append("'" + nullString(beanData.getMaterialid()) + "',");			bufSQL.append("'" + nullString(beanData.getRawmaterialid()) + "',");			bufSQL.append("'" + nullString(beanData.getUnit()) + "',");			bufSQL.append("'" + nullString(beanData.getNetweight()) + "',");			bufSQL.append("'" + nullString(beanData.getWastage()) + "',");			bufSQL.append("'" + nullString(beanData.getWeight()) + "',");			bufSQL.append("'" + nullString(beanData.getKgprice()) + "',");			bufSQL.append("'" + nullString(beanData.getMaterialprice()) + "',");			bufSQL.append("'" + nullString(beanData.getDeptguid()) + "',");			bufSQL.append("'" + nullString(beanData.getCreatetime()) + "',");			bufSQL.append("'" + nullString(beanData.getCreateperson()) + "',");			bufSQL.append("'" + nullString(beanData.getCreateunitid()) + "',");			bufSQL.append("'" + nullString(beanData.getModifytime()) + "',");			bufSQL.append("'" + nullString(beanData.getModifyperson()) + "',");			bufSQL.append("'" + nullString(beanData.getDeleteflag()) + "',");			bufSQL.append("'" + nullString(beanData.getFormid()) + "'");
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
		B_ZZRawMaterialData beanData = (B_ZZRawMaterialData) beanDataTmp; 
		Connection connection = null;
		PreparedStatement statement = null;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("INSERT INTO B_ZZRawMaterial( recordid,materialid,rawmaterialid,unit,netweight,wastage,weight,kgprice,materialprice,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag,formid)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			statement.setString( 1,beanData.getRecordid());			statement.setString( 2,beanData.getMaterialid());			statement.setString( 3,beanData.getRawmaterialid());			statement.setString( 4,beanData.getUnit());			statement.setString( 5,beanData.getNetweight());			statement.setString( 6,beanData.getWastage());			statement.setString( 7,beanData.getWeight());			statement.setString( 8,beanData.getKgprice());			statement.setString( 9,beanData.getMaterialprice());			statement.setString( 10,beanData.getDeptguid());			statement.setString( 11,beanData.getCreatetime());			statement.setString( 12,beanData.getCreateperson());			statement.setString( 13,beanData.getCreateunitid());			statement.setString( 14,beanData.getModifytime());			statement.setString( 15,beanData.getModifyperson());			statement.setString( 16,beanData.getDeleteflag());			statement.setString( 17,beanData.getFormid());
			if (statement.executeUpdate() < 1)
				throw new Exception(" Can't Insert Row ");
			else
				return "Create success";
		}
		catch(Exception e)
		{
			throw new Exception("INSERT INTO B_ZZRawMaterial( recordid,materialid,rawmaterialid,unit,netweight,wastage,weight,kgprice,materialprice,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag,formid)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)��"+ e.toString());
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
		B_ZZRawMaterialData beanData = (B_ZZRawMaterialData) beanDataTmp; 
		Connection connection = null;
		PreparedStatement statement = null;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("DELETE FROM B_ZZRawMaterial WHERE  recordid =?");
			statement.setString( 1,beanData.getRecordid());
			if (statement.executeUpdate() < 1)
				throw new Exception("Error deleting row");
		}
		catch(Exception e)
		{
			throw new Exception("Error executing SQL DELETE FROM B_ZZRawMaterial: "+ e.toString());
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
		B_ZZRawMaterialData beanData = (B_ZZRawMaterialData) beanDataTmp; 
		StringBuffer bufSQL = new StringBuffer();
		try
		{
			bufSQL.append("DELETE FROM B_ZZRawMaterial WHERE ");
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
			statement = connection.prepareStatement("DELETE FROM B_ZZRawMaterial"+ str_Where);
			if (statement.executeUpdate() < 1)
				throw new Exception("Error deleting row");
		}
		catch(Exception e)
		{
			throw new Exception("Error executing SQL DELETE FROM B_ZZRawMaterial: "+ e.toString());
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
		B_ZZRawMaterialData beanData = (B_ZZRawMaterialData) beanDataTmp; 
		B_ZZRawMaterialData returnData=new B_ZZRawMaterialData();
		Connection connection = null;
		PreparedStatement statement = null;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("SELECT recordid,materialid,rawmaterialid,unit,netweight,wastage,weight,kgprice,materialprice,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag,formid FROM B_ZZRawMaterial WHERE  recordid =?");
			statement.setString( 1,beanData.getRecordid());
			ResultSet resultSet = statement.executeQuery();
			if (!resultSet.next())
			{
				throw new Exception(" Row Not does;");
			}
			returnData.setRecordid( resultSet.getString( 1));			returnData.setMaterialid( resultSet.getString( 2));			returnData.setRawmaterialid( resultSet.getString( 3));			returnData.setUnit( resultSet.getString( 4));			returnData.setNetweight( resultSet.getString( 5));			returnData.setWastage( resultSet.getString( 6));			returnData.setWeight( resultSet.getString( 7));			returnData.setKgprice( resultSet.getString( 8));			returnData.setMaterialprice( resultSet.getString( 9));			returnData.setDeptguid( resultSet.getString( 10));			returnData.setCreatetime( resultSet.getString( 11));			returnData.setCreateperson( resultSet.getString( 12));			returnData.setCreateunitid( resultSet.getString( 13));			returnData.setModifytime( resultSet.getString( 14));			returnData.setModifyperson( resultSet.getString( 15));			returnData.setDeleteflag( resultSet.getString( 16));			returnData.setFormid( resultSet.getString( 17));
			return returnData;
		}
		catch(Exception e)
		{
			throw new Exception("Error executing SQL SELECT recordid,materialid,rawmaterialid,unit,netweight,wastage,weight,kgprice,materialprice,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag,formid FROM B_ZZRawMaterial  WHERE  "+e.toString());
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
			statement = connection.prepareStatement("SELECT recordid,materialid,rawmaterialid,unit,netweight,wastage,weight,kgprice,materialprice,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag,formid FROM B_ZZRawMaterial"+str_Where);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next())
			{
				B_ZZRawMaterialData returnData=new B_ZZRawMaterialData();
				returnData.setRecordid( resultSet.getString( 1));				returnData.setMaterialid( resultSet.getString( 2));				returnData.setRawmaterialid( resultSet.getString( 3));				returnData.setUnit( resultSet.getString( 4));				returnData.setNetweight( resultSet.getString( 5));				returnData.setWastage( resultSet.getString( 6));				returnData.setWeight( resultSet.getString( 7));				returnData.setKgprice( resultSet.getString( 8));				returnData.setMaterialprice( resultSet.getString( 9));				returnData.setDeptguid( resultSet.getString( 10));				returnData.setCreatetime( resultSet.getString( 11));				returnData.setCreateperson( resultSet.getString( 12));				returnData.setCreateunitid( resultSet.getString( 13));				returnData.setModifytime( resultSet.getString( 14));				returnData.setModifyperson( resultSet.getString( 15));				returnData.setDeleteflag( resultSet.getString( 16));				returnData.setFormid( resultSet.getString( 17));
				v_1.add(returnData);
			}
			return v_1;
		}
		catch(Exception e)
		{
			throw new Exception("Error executing SQL SELECT recordid,materialid,rawmaterialid,unit,netweight,wastage,weight,kgprice,materialprice,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag,formid FROM B_ZZRawMaterial" + astr_Where +e.toString());
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
			statement = connection.prepareStatement("UPDATE B_ZZRawMaterial SET recordid= ? , materialid= ? , rawmaterialid= ? , unit= ? , netweight= ? , wastage= ? , weight= ? , kgprice= ? , materialprice= ? , deptguid= ? , createtime= ? , createperson= ? , createunitid= ? , modifytime= ? , modifyperson= ? , deleteflag= ? , formid=? WHERE  recordid  = ?");
			statement.setString( 1,beanData.getRecordid());			statement.setString( 2,beanData.getMaterialid());			statement.setString( 3,beanData.getRawmaterialid());			statement.setString( 4,beanData.getUnit());			statement.setString( 5,beanData.getNetweight());			statement.setString( 6,beanData.getWastage());			statement.setString( 7,beanData.getWeight());			statement.setString( 8,beanData.getKgprice());			statement.setString( 9,beanData.getMaterialprice());			statement.setString( 10,beanData.getDeptguid());			statement.setString( 11,beanData.getCreatetime());			statement.setString( 12,beanData.getCreateperson());			statement.setString( 13,beanData.getCreateunitid());			statement.setString( 14,beanData.getModifytime());			statement.setString( 15,beanData.getModifyperson());			statement.setString( 16,beanData.getDeleteflag());			statement.setString( 17,beanData.getFormid());
			statement.setString( 18,beanData.getRecordid());
			if (statement.executeUpdate() < 1)
				throw new Exception(" Row Not does; ");
		}
		catch(Exception e)
		{
			throw new Exception("UPDATE B_ZZRawMaterial SET recordid= ? , materialid= ? , rawmaterialid= ? , unit= ? , netweight= ? , wastage= ? , weight= ? , kgprice= ? , materialprice= ? , deptguid= ? , createtime= ? , createperson= ? , createunitid= ? , modifytime= ? , modifyperson= ? , deleteflag= ? , formid=? WHERE  recordid  = ?"+ e.toString());
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
			bufSQL.append("UPDATE B_ZZRawMaterial SET ");
			bufSQL.append("Recordid = " + "'" + nullString(beanData.getRecordid()) + "',");			bufSQL.append("Materialid = " + "'" + nullString(beanData.getMaterialid()) + "',");			bufSQL.append("Rawmaterialid = " + "'" + nullString(beanData.getRawmaterialid()) + "',");			bufSQL.append("Unit = " + "'" + nullString(beanData.getUnit()) + "',");			bufSQL.append("Netweight = " + "'" + nullString(beanData.getNetweight()) + "',");			bufSQL.append("Wastage = " + "'" + nullString(beanData.getWastage()) + "',");			bufSQL.append("Weight = " + "'" + nullString(beanData.getWeight()) + "',");			bufSQL.append("Kgprice = " + "'" + nullString(beanData.getKgprice()) + "',");			bufSQL.append("Materialprice = " + "'" + nullString(beanData.getMaterialprice()) + "',");			bufSQL.append("Deptguid = " + "'" + nullString(beanData.getDeptguid()) + "',");			bufSQL.append("Createtime = " + "'" + nullString(beanData.getCreatetime()) + "',");			bufSQL.append("Createperson = " + "'" + nullString(beanData.getCreateperson()) + "',");			bufSQL.append("Createunitid = " + "'" + nullString(beanData.getCreateunitid()) + "',");			bufSQL.append("Modifytime = " + "'" + nullString(beanData.getModifytime()) + "',");			bufSQL.append("Modifyperson = " + "'" + nullString(beanData.getModifyperson()) + "',");			bufSQL.append("Deleteflag = " + "'" + nullString(beanData.getDeleteflag()) + "',");			bufSQL.append("Formid = " + "'" + nullString(beanData.getFormid()) + "'");
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
		B_ZZRawMaterialData beanData = (B_ZZRawMaterialData)data;
		Connection connection = null;
		PreparedStatement statement = null;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("UPDATE B_ZZRawMaterial SET recordid= ? , materialid= ? , rawmaterialid= ? , unit= ? , netweight= ? , wastage= ? , weight= ? , kgprice= ? , materialprice= ? , deptguid= ? , createtime= ? , createperson= ? , createunitid= ? , modifytime= ? , modifyperson= ? , deleteflag= ? , formid=? WHERE  recordid  = ?");
			statement.setString( 1,beanData.getRecordid());			statement.setString( 2,beanData.getMaterialid());			statement.setString( 3,beanData.getRawmaterialid());			statement.setString( 4,beanData.getUnit());			statement.setString( 5,beanData.getNetweight());			statement.setString( 6,beanData.getWastage());			statement.setString( 7,beanData.getWeight());			statement.setString( 8,beanData.getKgprice());			statement.setString( 9,beanData.getMaterialprice());			statement.setString( 10,beanData.getDeptguid());			statement.setString( 11,beanData.getCreatetime());			statement.setString( 12,beanData.getCreateperson());			statement.setString( 13,beanData.getCreateunitid());			statement.setString( 14,beanData.getModifytime());			statement.setString( 15,beanData.getModifyperson());			statement.setString( 16,beanData.getDeleteflag());			statement.setString( 17,beanData.getFormid());
			statement.setString( 18,beanData.getRecordid());
			if (statement.executeUpdate() < 1)
				throw new Exception(" Row Not does; ");
		}
		catch(Exception e)
		{
			throw new Exception("UPDATE B_ZZRawMaterial SET recordid= ? , materialid= ? , rawmaterialid= ? , unit= ? , netweight= ? , wastage= ? , weight= ? , kgprice= ? , materialprice= ? , deptguid= ? , createtime= ? , createperson= ? , createunitid= ? , modifytime= ? , modifyperson= ? , deleteflag= ? , formid=? WHERE  recordid  = ?"+ e.toString());
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
		B_ZZRawMaterialData beanData = (B_ZZRawMaterialData) beanDataTmp; 
			connection = getConnection();
			statement = connection.prepareStatement("SELECT  recordid  FROM B_ZZRawMaterial WHERE  recordid =?");
			statement.setString( 1,beanData.getRecordid());
			ResultSet resultSet = statement.executeQuery();
			if (!resultSet.next())
			{
				throw new Exception(" Primary Key Not does");
			}
		}
		catch(Exception e)
		{
			throw new Exception("Error executing SQL  SELECT  recordid  FROM B_ZZRawMaterial WHERE  recordid =?");
		}
		finally
		{
			closeConnection(connection, statement);
		}
	}

}
