/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import beans.UserBean;
import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author hp1
 */
public class ManageUserServices {
    public static boolean addUser(UserBean objbean)
    {
        Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			con=DBConnection.connect();
			pstmt=con.prepareStatement("insert into usermaster (userid,username,password,usertype,userstatus,name,contact,dob,email,address) values(?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1,objbean.getUserId());
			pstmt.setString(2,objbean.getUserName());
			pstmt.setString(3,objbean.getPassword());
                        pstmt.setString(4,objbean.getUserType());
                        pstmt.setBoolean(5,objbean.isUserStatus());
                        pstmt.setString(6,objbean.getName());
                        pstmt.setString(7,objbean.getContact());
                        pstmt.setString(8,objbean.getDob());
                        pstmt.setString(9,objbean.getEmailId());
                        pstmt.setString(10,objbean.getAddress());
			int i=pstmt.executeUpdate();
			if(i>0)
			{
				return true;
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in addEmployee()"+e);
		}
		finally
		{
			try
			{
				pstmt.close();
				con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in finally of addEmployee()"+e);
			}
		}
		return false;
    }
    public static ArrayList getAllUsers()
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<UserBean>al=null;
		try
		{
			con=DBConnection.connect();
			pstmt=con.prepareStatement("select * from usermaster");
			rs=pstmt.executeQuery();
			al=new ArrayList<UserBean>();
			while(rs.next())
			{
				UserBean objbean=new UserBean();
				objbean.setUserId(rs.getInt("userid"));
                                objbean.setUserName(rs.getString("username"));
				objbean.setPassword(rs.getString("password"));
                                objbean.setUserType(rs.getString("usertype"));
                                objbean.setUserStatus(rs.getBoolean("userstatus"));
                                objbean.setName(rs.getString("name"));
                                objbean.setContact(rs.getString("contact"));
                                objbean.setDob(rs.getString("dob"));
                                objbean.setEmailId(rs.getString("email"));
                                objbean.setAddress(rs.getString("address"));
                                al.add(objbean);
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in getAllEmployees()"+e);
		}
		finally
		{
			try
			{
				pstmt.close();
				con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in Finally of getAllEmployees()"+e);
			}
		}
		return al;
	}
    public static boolean updateUser(UserBean objbean)
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{
			con=DBConnection.connect();
			pstmt=con.prepareStatement("update usermaster set username=?,password=?,usertype=?,userstatus=?,name=?,contact=?,dob=?,email=?,address=? where userid=?");
			pstmt.setString(1,objbean.getUserName());
			pstmt.setString(2,objbean.getPassword());
                        pstmt.setString(3,objbean.getUserType());
                        pstmt.setBoolean(4,objbean.isUserStatus());
                        pstmt.setString(5,objbean.getName());
                        pstmt.setString(6,objbean.getContact());
                        pstmt.setString(7,objbean.getDob());
                        pstmt.setString(8,objbean.getEmailId());
                        pstmt.setString(9,objbean.getAddress());
                        pstmt.setInt(10,objbean.getUserId());
			int i=pstmt.executeUpdate();
			if(i>0)
			{
				return true;
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in updateById()"+e);
		}
		finally
		{
			try
			{
				pstmt.close();
				con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in finally of updateById()");
			}
		}
		return false;
	}
    public static int getNextId()
    {
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try
        {
            con=DBConnection.connect();
            pstmt=con.prepareStatement("select max(Userid) from usermaster");
            rs=pstmt.executeQuery();
            if(rs.next())
            {
                return (rs.getInt("max(userid)") +1);//temp. the column name changes to max(userid) so we are doing this.
            }
        }
        catch(Exception e)
        {
            System.out.println("Exception in getNextId()"+e);
        }
        finally
        {
            try
            {
                rs.close();
                pstmt.close();
                con.close();
            }
            catch(Exception e)
            {
                System.out.println("Exception in fianlly of getNextId()"+e);
            }
        }
        return 0;
    }
}
