/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import beans.UserBean;
import db.DBConnection;
import email.SendMail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author hp1
 */
public class LoginServices {
    public static UserBean authenticateUser(String un,String pwd)
    {
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try
        {
            con=DBConnection.connect();
            pstmt=con.prepareStatement("select username,userid,password,usertype,userstatus from usermaster where username=? ");
            pstmt.setString(1,un);
            rs=pstmt.executeQuery();
            if(rs.next())
            {
                if(rs.getString("password").equals(pwd))
                {
                    UserBean objbean=new UserBean();
                    objbean.setUserId(rs.getInt("userid"));
                    objbean.setUserStatus(rs.getBoolean("userstatus"));
                    objbean.setUserType(rs.getString("usertype"));
                    objbean.setUserName(rs.getString("username"));
                    return objbean;
                }
            } 
        }
        catch(Exception e)
        {
            System.out.println("Exception in LoginServices"+e);
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
                System.out.println("Exception in Finally of LoginServices"+e);
            }
        }
        return null;
    }
    public static boolean forgotPassword(String un)
    {
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try
        {
            con=DBConnection.connect();
            pstmt=con.prepareStatement("select password,email from usermaster where username=?");
            pstmt.setString(1, un);
            rs=pstmt.executeQuery();
            if(rs.next())
            {
                String email=rs.getString("email");
                String pwd=rs.getString("password");
                String result=SendMail.sendMail(email,"Your Password is"+pwd,"Password Recovery");
                if(result.equalsIgnoreCase("sent"))
                {
                    return true;
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Exception in ForgotPassword()"+e);
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
                        System.out.println("Exception in finally of ForgotPassword()"+e);
                    }
                }
        return false;
    }
}
