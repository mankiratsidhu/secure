/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author hp1
 */
public class DBConnection {
    public static Connection connect()
	{
		Connection con=null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql:///usermaintenance";
			con=DriverManager.getConnection(url,"root","");
		}
		catch(Exception e)
		{
			System.out.println("Exception in connect()"+e);
		}
		return con;
	}
    
}
