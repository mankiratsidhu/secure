/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.TreeMap;
public class ServerThread extends Thread {

        ServerSocket sskt=null;
        Socket skt=null;
        DataInputStream dis=null;
        TreeMap<String,Socket> network=null;
        
        public void run()
        {
            try
            {
                sskt=new ServerSocket(1999);
                System.out.println("Server Socket made");
                network=new TreeMap<String,Socket>();
                do
                {
                System.out.println("Waiting for Client");    
                skt=sskt.accept();
                dis=new DataInputStream(skt.getInputStream());
                String username=dis.readUTF();
                network.put(username,skt);
                System.out.println("Connected to client with username "+username);  
                FTPThread objFTP=new FTPThread(network,username);
                objFTP.run();
                }while(true);
            }
            catch(Exception e)
            {
                System.out.println("Exception in ServerThread"+e);
            }
            finally
            {
                try
                {
                    dis.close();
                    skt.close();
                    sskt.close();

                }
                catch(Exception e)
                {   
                    System.out.println("finally"+e);
                }
            }
        }
}
    
