package com.ys.business.db.dao;

import java.sql.*;
import java.io.InputStream;
import com.ys.util.basedao.BaseAbstractDao;
import com.ys.business.db.data.*;

/**
* <p>Title: </p>
* <p>Description: ���ݱ�  </p>
* <p>Copyright: gentleman</p>
* <p>Company: gentleman</p>
* @author mengfanchang
* @version 1.0
*/
public class B_MouldContractDetailDao extends BaseAbstractDao
{
	public B_MouldContractDetailData beanData=new B_MouldContractDetailData();
	public B_MouldContractDetailDao()
	{
	}
	public B_MouldContractDetailDao(Object beanData) throws Exception
	{
		try
		{
			this.beanData = (B_MouldContractDetailData)FindByPrimaryKey(beanData);
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
		B_MouldContractDetailData beanData = (B_MouldContractDetailData) beanDataTmp; 
		Connection connection = null;
		PreparedStatement statement = null;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("INSERT INTO B_MouldContractDetail( id,mouldcontractbaseid,mouldid,mouldfactoryid,number,totalprice,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			statement.setString( 1,beanData.getId());			statement.setString( 2,beanData.getMouldcontractbaseid());			statement.setString( 3,beanData.getMouldid());			statement.setString( 4,beanData.getMouldfactoryid());			statement.setString( 5,beanData.getNumber());			statement.setString( 6,beanData.getTotalprice());			statement.setString( 7,beanData.getDeptguid());			statement.setString( 8,beanData.getCreatetime());			statement.setString( 9,beanData.getCreateperson());			statement.setString( 10,beanData.getCreateunitid());			statement.setString( 11,beanData.getModifytime());			statement.setString( 12,beanData.getModifyperson());			statement.setString( 13,beanData.getDeleteflag());
			if (statement.executeUpdate() < 1)
				throw new Exception(" Can't Insert Row ");
			else
				return "Create success";
		}
		catch(Exception e)
		{
			throw new Exception("INSERT INTO B_MouldContractDetail( id,mouldcontractbaseid,mouldid,mouldfactoryid,number,totalprice,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)��"+ e.toString());
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
			bufSQL.append("INSERT INTO B_MouldContractDetail( id,mouldcontractbaseid,mouldid,mouldfactoryid,number,totalprice,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag)VALUES(");
			bufSQL.append("'" + nullString(beanData.getId()) + "',");			bufSQL.append("'" + nullString(beanData.getMouldcontractbaseid()) + "',");			bufSQL.append("'" + nullString(beanData.getMouldid()) + "',");			bufSQL.append("'" + nullString(beanData.getMouldfactoryid()) + "',");			bufSQL.append("'" + nullString(beanData.getNumber()) + "',");			bufSQL.append("'" + nullString(beanData.getTotalprice()) + "',");			bufSQL.append("'" + nullString(beanData.getDeptguid()) + "',");			bufSQL.append("'" + nullString(beanData.getCreatetime()) + "',");			bufSQL.append("'" + nullString(beanData.getCreateperson()) + "',");			bufSQL.append("'" + nullString(beanData.getCreateunitid()) + "',");			bufSQL.append("'" + nullString(beanData.getModifytime()) + "',");			bufSQL.append("'" + nullString(beanData.getModifyperson()) + "',");			bufSQL.append("'" + nullString(beanData.getDeleteflag()) + "'");
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
		B_MouldContractDetailData beanData = (B_MouldContractDetailData) beanDataTmp; 
		Connection connection = null;
		PreparedStatement statement = null;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("INSERT INTO B_MouldContractDetail( id,mouldcontractbaseid,mouldid,mouldfactoryid,number,totalprice,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			statement.setString( 1,beanData.getId());			statement.setString( 2,beanData.getMouldcontractbaseid());			statement.setString( 3,beanData.getMouldid());			statement.setString( 4,beanData.getMouldfactoryid());			statement.setString( 5,beanData.getNumber());			statement.setString( 6,beanData.getTotalprice());			statement.setString( 7,beanData.getDeptguid());			statement.setString( 8,beanData.getCreatetime());			statement.setString( 9,beanData.getCreateperson());			statement.setString( 10,beanData.getCreateunitid());			statement.setString( 11,beanData.getModifytime());			statement.setString( 12,beanData.getModifyperson());			statement.setString( 13,beanData.getDeleteflag());
			if (statement.executeUpdate() < 1)
				throw new Exception(" Can't Insert Row ");
			else
				return "Create success";
		}
		catch(Exception e)
		{
			throw new Exception("INSERT INTO B_MouldContractDetail( id,mouldcontractbaseid,mouldid,mouldfactoryid,number,totalprice,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)��"+ e.toString());
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
		B_MouldContractDetailData beanData = (B_MouldContractDetailData) beanDataTmp; 
		Connection connection = null;
		PreparedStatement statement = null;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("DELETE FROM B_MouldContractDetail WHERE  id =?");
			statement.setString( 1,beanData.getId());
			if (statement.executeUpdate() < 1)
				throw new Exception("Error deleting row");
		}
		catch(Exception e)
		{
			throw new Exception("Error executing SQL DELETE FROM B_MouldContractDetail: "+ e.toString());
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
		B_MouldContractDetailData beanData = (B_MouldContractDetailData) beanDataTmp; 
		StringBuffer bufSQL = new StringBuffer();
		try
		{
			bufSQL.append("DELETE FROM B_MouldContractDetail WHERE ");
			bufSQL.append("Id = " + "'" + nullString(beanData.getId()) + "'");
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
			statement = connection.prepareStatement("DELETE FROM B_MouldContractDetail"+ str_Where);
			if (statement.executeUpdate() < 1)
				throw new Exception("Error deleting row");
		}
		catch(Exception e)
		{
			throw new Exception("Error executing SQL DELETE FROM B_MouldContractDetail: "+ e.toString());
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
		B_MouldContractDetailData beanData = (B_MouldContractDetailData) beanDataTmp; 
		B_MouldContractDetailData returnData=new B_MouldContractDetailData();
		Connection connection = null;
		PreparedStatement statement = null;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("SELECT id,mouldcontractbaseid,mouldid,mouldfactoryid,number,totalprice,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag FROM B_MouldContractDetail WHERE  id =?");
			statement.setString( 1,beanData.getId());
			ResultSet resultSet = statement.executeQuery();
			if (!resultSet.next())
			{
				throw new Exception(" Row Not does;");
			}
			returnData.setId( resultSet.getString( 1));			returnData.setMouldcontractbaseid( resultSet.getString( 2));			returnData.setMouldid( resultSet.getString( 3));			returnData.setMouldfactoryid( resultSet.getString( 4));			returnData.setNumber( resultSet.getString( 5));			returnData.setTotalprice( resultSet.getString( 6));			returnData.setDeptguid( resultSet.getString( 7));			returnData.setCreatetime( resultSet.getString( 8));			returnData.setCreateperson( resultSet.getString( 9));			returnData.setCreateunitid( resultSet.getString( 10));			returnData.setModifytime( resultSet.getString( 11));			returnData.setModifyperson( resultSet.getString( 12));			returnData.setDeleteflag( resultSet.getString( 13));
			return returnData;
		}
		catch(Exception e)
		{
			throw new Exception("Error executing SQL SELECT id,mouldcontractbaseid,mouldid,mouldfactoryid,number,totalprice,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag FROM B_MouldContractDetail  WHERE  "+e.toString());
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
			statement = connection.prepareStatement("SELECT id,mouldcontractbaseid,mouldid,mouldfactoryid,number,totalprice,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag FROM B_MouldContractDetail"+str_Where);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next())
			{
				B_MouldContractDetailData returnData=new B_MouldContractDetailData();
				returnData.setId( resultSet.getString( 1));				returnData.setMouldcontractbaseid( resultSet.getString( 2));				returnData.setMouldid( resultSet.getString( 3));				returnData.setMouldfactoryid( resultSet.getString( 4));				returnData.setNumber( resultSet.getString( 5));				returnData.setTotalprice( resultSet.getString( 6));				returnData.setDeptguid( resultSet.getString( 7));				returnData.setCreatetime( resultSet.getString( 8));				returnData.setCreateperson( resultSet.getString( 9));				returnData.setCreateunitid( resultSet.getString( 10));				returnData.setModifytime( resultSet.getString( 11));				returnData.setModifyperson( resultSet.getString( 12));				returnData.setDeleteflag( resultSet.getString( 13));
				v_1.add(returnData);
			}
			return v_1;
		}
		catch(Exception e)
		{
			throw new Exception("Error executing SQL SELECT id,mouldcontractbaseid,mouldid,mouldfactoryid,number,totalprice,deptguid,createtime,createperson,createunitid,modifytime,modifyperson,deleteflag FROM B_MouldContractDetail" + astr_Where +e.toString());
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
			statement = connection.prepareStatement("UPDATE B_MouldContractDetail SET id= ? , mouldcontractbaseid= ? , mouldid= ? , mouldfactoryid= ? , number= ? , totalprice= ? , deptguid= ? , createtime= ? , createperson= ? , createunitid= ? , modifytime= ? , modifyperson= ? , deleteflag=? WHERE  id  = ?");
			statement.setString( 1,beanData.getId());			statement.setString( 2,beanData.getMouldcontractbaseid());			statement.setString( 3,beanData.getMouldid());			statement.setString( 4,beanData.getMouldfactoryid());			statement.setString( 5,beanData.getNumber());			statement.setString( 6,beanData.getTotalprice());			statement.setString( 7,beanData.getDeptguid());			statement.setString( 8,beanData.getCreatetime());			statement.setString( 9,beanData.getCreateperson());			statement.setString( 10,beanData.getCreateunitid());			statement.setString( 11,beanData.getModifytime());			statement.setString( 12,beanData.getModifyperson());			statement.setString( 13,beanData.getDeleteflag());
			statement.setString( 14,beanData.getId());
			if (statement.executeUpdate() < 1)
				throw new Exception(" Row Not does; ");
		}
		catch(Exception e)
		{
			throw new Exception("UPDATE B_MouldContractDetail SET id= ? , mouldcontractbaseid= ? , mouldid= ? , mouldfactoryid= ? , number= ? , totalprice= ? , deptguid= ? , createtime= ? , createperson= ? , createunitid= ? , modifytime= ? , modifyperson= ? , deleteflag=? WHERE  id  = ?"+ e.toString());
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
			bufSQL.append("UPDATE B_MouldContractDetail SET ");
			bufSQL.append("Id = " + "'" + nullString(beanData.getId()) + "',");			bufSQL.append("Mouldcontractbaseid = " + "'" + nullString(beanData.getMouldcontractbaseid()) + "',");			bufSQL.append("Mouldid = " + "'" + nullString(beanData.getMouldid()) + "',");			bufSQL.append("Mouldfactoryid = " + "'" + nullString(beanData.getMouldfactoryid()) + "',");			bufSQL.append("Number = " + "'" + nullString(beanData.getNumber()) + "',");			bufSQL.append("Totalprice = " + "'" + nullString(beanData.getTotalprice()) + "',");			bufSQL.append("Deptguid = " + "'" + nullString(beanData.getDeptguid()) + "',");			bufSQL.append("Createtime = " + "'" + nullString(beanData.getCreatetime()) + "',");			bufSQL.append("Createperson = " + "'" + nullString(beanData.getCreateperson()) + "',");			bufSQL.append("Createunitid = " + "'" + nullString(beanData.getCreateunitid()) + "',");			bufSQL.append("Modifytime = " + "'" + nullString(beanData.getModifytime()) + "',");			bufSQL.append("Modifyperson = " + "'" + nullString(beanData.getModifyperson()) + "',");			bufSQL.append("Deleteflag = " + "'" + nullString(beanData.getDeleteflag()) + "'");
			bufSQL.append(" WHERE ");
			bufSQL.append("Id = " + "'" + nullString(beanData.getId()) + "'");
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
		B_MouldContractDetailData beanData = (B_MouldContractDetailData)data;
		Connection connection = null;
		PreparedStatement statement = null;
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("UPDATE B_MouldContractDetail SET id= ? , mouldcontractbaseid= ? , mouldid= ? , mouldfactoryid= ? , number= ? , totalprice= ? , deptguid= ? , createtime= ? , createperson= ? , createunitid= ? , modifytime= ? , modifyperson= ? , deleteflag=? WHERE  id  = ?");
			statement.setString( 1,beanData.getId());			statement.setString( 2,beanData.getMouldcontractbaseid());			statement.setString( 3,beanData.getMouldid());			statement.setString( 4,beanData.getMouldfactoryid());			statement.setString( 5,beanData.getNumber());			statement.setString( 6,beanData.getTotalprice());			statement.setString( 7,beanData.getDeptguid());			statement.setString( 8,beanData.getCreatetime());			statement.setString( 9,beanData.getCreateperson());			statement.setString( 10,beanData.getCreateunitid());			statement.setString( 11,beanData.getModifytime());			statement.setString( 12,beanData.getModifyperson());			statement.setString( 13,beanData.getDeleteflag());
			statement.setString( 14,beanData.getId());
			if (statement.executeUpdate() < 1)
				throw new Exception(" Row Not does; ");
		}
		catch(Exception e)
		{
			throw new Exception("UPDATE B_MouldContractDetail SET id= ? , mouldcontractbaseid= ? , mouldid= ? , mouldfactoryid= ? , number= ? , totalprice= ? , deptguid= ? , createtime= ? , createperson= ? , createunitid= ? , modifytime= ? , modifyperson= ? , deleteflag=? WHERE  id  = ?"+ e.toString());
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
		B_MouldContractDetailData beanData = (B_MouldContractDetailData) beanDataTmp; 
			connection = getConnection();
			statement = connection.prepareStatement("SELECT  id  FROM B_MouldContractDetail WHERE  id =?");
			statement.setString( 1,beanData.getId());
			ResultSet resultSet = statement.executeQuery();
			if (!resultSet.next())
			{
				throw new Exception(" Primary Key Not does");
			}
		}
		catch(Exception e)
		{
			throw new Exception("Error executing SQL  SELECT  id  FROM B_MouldContractDetail WHERE  id =?");
		}
		finally
		{
			closeConnection(connection, statement);
		}
	}

}
