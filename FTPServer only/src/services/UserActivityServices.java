/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author hp1
 */
public class UserActivityServices {
    
    private static String getDateTime()
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d=new Date();
        String date=sdf.format(d);
        System.out.println(date);
        return date;
    }
    public static int addLoginTime(int userid)
    {
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try
        {
            con=DBConnection.connect();
            pstmt=con.prepareStatement("insert into activitymaster(userid,logintime) values(?,?)");
            pstmt.setInt(1,userid);
            pstmt.setString(2,getDateTime());
            int i=pstmt.executeUpdate();
            if(i>0)
            {
                pstmt=con.prepareStatement("select max(activityid) from activitymaster where userid=?");
                pstmt.setInt(1,userid);
                rs=pstmt.executeQuery();
                if(rs.next())
                {
                    return rs.getInt("max(activityid)");
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Exception in addLoginTime()"+e);
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
                System.out.println("Exception in finally of addLoginTime()"+e);
            }
        }
        return 0;
    }

    public static void updateLogoutTime(int acitivityid)
    {
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
                try
                {
                    con=DBConnection.connect();
                    pstmt=con.prepareStatement("update activitymaster set logouttime=? where activityid=?");
                    pstmt.setString(1,getDateTime());
                    pstmt.setInt(2, acitivityid);
                   // System.out.println(""+pstmt);
                    int i=pstmt.executeUpdate();
                }
                catch(Exception e)
                {
                    System.out.println("Exception in updateLogoutTime"+e);
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
                        System.out.println("Exception in finally of upDateLogoutTime()"+e);
                    }
                }
    }
}