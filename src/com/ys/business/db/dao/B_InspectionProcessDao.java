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
public class B_InspectionProcessDao extends BaseAbstractDao
{
	public B_InspectionProcessData beanData=new B_InspectionProcessData();
	public B_InspectionProcessDao()
	{
	}
	public B_InspectionProcessDao(Object beanData) throws Exception
	{
		try
		{
			this.beanData = (B_InspectionProcessData)FindByPrimaryKey(beanData);
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
		B_InspectionProcessData beanData = (B_InspectionProcessData) beanDataTmp; 
		Connection connection = null;
		PreparedStatement statement = null;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("INSERT INTO B_InspectionProcess( recordid,ysid,arrivalid,materialid,result,checkerid,checkresult,checkdate,managerresult,managerdate,managerfeedback,managerid,gmdate,gmfeedback,gmresult,gmid,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag,formid)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			statement.setString( 1,beanData.getRecordid());
			if (statement.executeUpdate() < 1)
				throw new Exception(" Can't Insert Row ");
			else
				return "Create success";
		}
		catch(Exception e)
		{
			throw new Exception("INSERT INTO B_InspectionProcess( recordid,ysid,arrivalid,materialid,result,checkerid,checkresult,checkdate,managerresult,managerdate,managerfeedback,managerid,gmdate,gmfeedback,gmresult,gmid,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag,formid)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)��"+ e.toString());
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
			bufSQL.append("INSERT INTO B_InspectionProcess( recordid,ysid,arrivalid,materialid,result,checkerid,checkresult,checkdate,managerresult,managerdate,managerfeedback,managerid,gmdate,gmfeedback,gmresult,gmid,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag,formid)VALUES(");
			bufSQL.append("'" + nullString(beanData.getRecordid()) + "',");
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
		B_InspectionProcessData beanData = (B_InspectionProcessData) beanDataTmp; 
		Connection connection = null;
		PreparedStatement statement = null;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("INSERT INTO B_InspectionProcess( recordid,ysid,arrivalid,materialid,result,checkerid,checkresult,checkdate,managerresult,managerdate,managerfeedback,managerid,gmdate,gmfeedback,gmresult,gmid,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag,formid)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			statement.setString( 1,beanData.getRecordid());
			if (statement.executeUpdate() < 1)
				throw new Exception(" Can't Insert Row ");
			else
				return "Create success";
		}
		catch(Exception e)
		{
			throw new Exception("INSERT INTO B_InspectionProcess( recordid,ysid,arrivalid,materialid,result,checkerid,checkresult,checkdate,managerresult,managerdate,managerfeedback,managerid,gmdate,gmfeedback,gmresult,gmid,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag,formid)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)��"+ e.toString());
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
		B_InspectionProcessData beanData = (B_InspectionProcessData) beanDataTmp; 
		Connection connection = null;
		PreparedStatement statement = null;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("DELETE FROM B_InspectionProcess WHERE  recordid =?");
			statement.setString( 1,beanData.getRecordid());
			if (statement.executeUpdate() < 1)
				throw new Exception("Error deleting row");
		}
		catch(Exception e)
		{
			throw new Exception("Error executing SQL DELETE FROM B_InspectionProcess: "+ e.toString());
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
		B_InspectionProcessData beanData = (B_InspectionProcessData) beanDataTmp; 
		StringBuffer bufSQL = new StringBuffer();
		try
		{
			bufSQL.append("DELETE FROM B_InspectionProcess WHERE ");
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
			statement = connection.prepareStatement("DELETE FROM B_InspectionProcess"+ str_Where);
			if (statement.executeUpdate() < 1)
				throw new Exception("Error deleting row");
		}
		catch(Exception e)
		{
			throw new Exception("Error executing SQL DELETE FROM B_InspectionProcess: "+ e.toString());
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
		B_InspectionProcessData beanData = (B_InspectionProcessData) beanDataTmp; 
		B_InspectionProcessData returnData=new B_InspectionProcessData();
		Connection connection = null;
		PreparedStatement statement = null;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("SELECT recordid,ysid,arrivalid,materialid,result,checkerid,checkresult,checkdate,managerresult,managerdate,managerfeedback,managerid,gmdate,gmfeedback,gmresult,gmid,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag,formid FROM B_InspectionProcess WHERE  recordid =?");
			statement.setString( 1,beanData.getRecordid());
			ResultSet resultSet = statement.executeQuery();
			if (!resultSet.next())
			{
				throw new Exception(" Row Not does;");
			}
			returnData.setRecordid( resultSet.getString( 1));
			return returnData;
		}
		catch(Exception e)
		{
			throw new Exception("Error executing SQL SELECT recordid,ysid,arrivalid,materialid,result,checkerid,checkresult,checkdate,managerresult,managerdate,managerfeedback,managerid,gmdate,gmfeedback,gmresult,gmid,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag,formid FROM B_InspectionProcess  WHERE  "+e.toString());
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
			statement = connection.prepareStatement("SELECT recordid,ysid,arrivalid,materialid,result,checkerid,checkresult,checkdate,managerresult,managerdate,managerfeedback,managerid,gmdate,gmfeedback,gmresult,gmid,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag,formid FROM B_InspectionProcess"+str_Where);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next())
			{
				B_InspectionProcessData returnData=new B_InspectionProcessData();
				returnData.setRecordid( resultSet.getString( 1));
				v_1.add(returnData);
			}
			return v_1;
		}
		catch(Exception e)
		{
			throw new Exception("Error executing SQL SELECT recordid,ysid,arrivalid,materialid,result,checkerid,checkresult,checkdate,managerresult,managerdate,managerfeedback,managerid,gmdate,gmfeedback,gmresult,gmid,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag,formid FROM B_InspectionProcess" + astr_Where +e.toString());
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
			statement = connection.prepareStatement("UPDATE B_InspectionProcess SET recordid= ? , ysid= ? , arrivalid= ? , materialid= ? , result= ? , checkerid= ? , checkresult= ? , checkdate= ? , managerresult= ? , managerdate= ? , managerfeedback= ? , managerid= ? , gmdate= ? , gmfeedback= ? , gmresult= ? , gmid= ? , deptguid= ? , createtime= ? , createperson= ? , createunitid= ? , modifytime= ? , modifyperson= ? , deleteflag= ? , formid=? WHERE  recordid  = ?");
			statement.setString( 1,beanData.getRecordid());
			statement.setString( 25,beanData.getRecordid());
			if (statement.executeUpdate() < 1)
				throw new Exception(" Row Not does; ");
		}
		catch(Exception e)
		{
			throw new Exception("UPDATE B_InspectionProcess SET recordid= ? , ysid= ? , arrivalid= ? , materialid= ? , result= ? , checkerid= ? , checkresult= ? , checkdate= ? , managerresult= ? , managerdate= ? , managerfeedback= ? , managerid= ? , gmdate= ? , gmfeedback= ? , gmresult= ? , gmid= ? , deptguid= ? , createtime= ? , createperson= ? , createunitid= ? , modifytime= ? , modifyperson= ? , deleteflag= ? , formid=? WHERE  recordid  = ?"+ e.toString());
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
			bufSQL.append("UPDATE B_InspectionProcess SET ");
			bufSQL.append("Recordid = " + "'" + nullString(beanData.getRecordid()) + "',");
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
		B_InspectionProcessData beanData = (B_InspectionProcessData)data;
		Connection connection = null;
		PreparedStatement statement = null;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("UPDATE B_InspectionProcess SET recordid= ? , ysid= ? , arrivalid= ? , materialid= ? , result= ? , checkerid= ? , checkresult= ? , checkdate= ? , managerresult= ? , managerdate= ? , managerfeedback= ? , managerid= ? , gmdate= ? , gmfeedback= ? , gmresult= ? , gmid= ? , deptguid= ? , createtime= ? , createperson= ? , createunitid= ? , modifytime= ? , modifyperson= ? , deleteflag= ? , formid=? WHERE  recordid  = ?");
			statement.setString( 1,beanData.getRecordid());
			statement.setString( 25,beanData.getRecordid());
			if (statement.executeUpdate() < 1)
				throw new Exception(" Row Not does; ");
		}
		catch(Exception e)
		{
			throw new Exception("UPDATE B_InspectionProcess SET recordid= ? , ysid= ? , arrivalid= ? , materialid= ? , result= ? , checkerid= ? , checkresult= ? , checkdate= ? , managerresult= ? , managerdate= ? , managerfeedback= ? , managerid= ? , gmdate= ? , gmfeedback= ? , gmresult= ? , gmid= ? , deptguid= ? , createtime= ? , createperson= ? , createunitid= ? , modifytime= ? , modifyperson= ? , deleteflag= ? , formid=? WHERE  recordid  = ?"+ e.toString());
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
		B_InspectionProcessData beanData = (B_InspectionProcessData) beanDataTmp; 
			connection = getConnection();
			statement = connection.prepareStatement("SELECT  recordid  FROM B_InspectionProcess WHERE  recordid =?");
			statement.setString( 1,beanData.getRecordid());
			ResultSet resultSet = statement.executeQuery();
			if (!resultSet.next())
			{
				throw new Exception(" Primary Key Not does");
			}
		}
		catch(Exception e)
		{
			throw new Exception("Error executing SQL  SELECT  recordid  FROM B_InspectionProcess WHERE  recordid =?");
		}
		finally
		{
			closeConnection(connection, statement);
		}
	}

}