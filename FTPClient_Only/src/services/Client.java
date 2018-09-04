/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author hp1
 */
public class Client {
    public static void Cl(Socket skt,int choice,String select,String filepath)
    {
	InputStreamReader isr=null;
	BufferedReader br=null;
	DataOutputStream dos=null;//important classes
	DataInputStream dis=null;
	try
        {
		dos=new DataOutputStream(skt.getOutputStream());
		dis=new DataInputStream(skt.getInputStream());
                            dos.writeInt(choice);
                            switch(choice)
                            {
				case 1:
				{
                                    System.out.println("Enter File Name");
                                    String filename=br.readLine();
                                    dos.writeUTF(filename);//Unicode Methods	
                                    if(dis.readBoolean())
                                    {
					long size=dis.readLong();
                                        System.out.println("Downloading Begins......");
                                        FileOutputStream fos=new FileOutputStream(filename);
                                        byte b[]=new byte[(int)size];
					int c=0;
					while((c=dis.read(b))!=-1)
                                        {
                                            fos.write(b,0,c);
					}
                                            fos.close();
                                        System.out.println("File Downloaded Successfully");
                                    }
                                    else
                                    {
					System.out.println("File Not Found");
                                    }
                                    break;
				}
				case 2:
                                {
                                }
                                break;
                            }
            }
	catch(Exception e)
	{
            System.out.println("Exception in client"+e);
	}
	finally
        {
            try
            {	
		dis.close();
		dos.close();
		br.close();
		isr.close();
            }
            catch(Exception e)
            {
		System.out.println("Exception in finally in client"+e);
            }
	}
    }
}    


