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
public class ManageProfileServices {
    
    public static UserBean getById(int userid)
    {
        Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try
		{
			con=DBConnection.connect();
			pstmt=con.prepareStatement("select * from usermaster where userid=?");
                        pstmt.setInt(1,userid);
			rs=pstmt.executeQuery();
			if(rs.next())
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
                                return objbean;
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
                            rs.close();
				pstmt.close();
				con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in Finally of getAllEmployees()"+e);
			}
                }
                return null;
}
    
}
