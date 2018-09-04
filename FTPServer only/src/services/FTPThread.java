/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.TreeMap;

/**
 *
 * @author hp1
 */
public class FTPThread extends Thread {
    TreeMap<String,Socket> network=new TreeMap();
    String username="";
    Socket skt=null;
    InputStreamReader isr=null;
    DataInputStream dis=null;
    DataOutputStream dos=null;
    public FTPThread(TreeMap<String,Socket> network,String username)
    {
        this.network=network;
        this.username=username;
    }
    public void run()
    {
        try
        {
            skt=network.get(username);
            dis=new DataInputStream(skt.getInputStream());
            dos=new DataOutputStream(skt.getOutputStream());
            String path="D:\\Server\\"+username;
            String privatepath="D:\\Server\\"+username+"\\private";
            String publicpath="D:\\Server\\"+username+"\\public";
            File f=new File(path);
            if(f.exists())
            {
                f.mkdir();
                File privatef=new File(privatepath);
                File publicf=new File(publicpath);
                //System.out.println(publicpath);
                //System.out.println(privatepath);
                privatef.mkdir();
                publicf.mkdir();
                System.out.println("f.mkdir()");
            }
                int choice=dis.readInt();
                switch(choice)
                {
                    case 1:
                    {
                        String filepath=path+dis.readUTF();
                        System.out.println(filepath);
                        FileInputStream fis=new FileInputStream(filepath);
                        File filename=new File(filepath);
                        long size=filename.length();
                        dos.writeLong(size);
                        System.out.println(size);
                        byte b[]=new byte[(int)size];
                        int c=0;
                        System.out.println("Downloadihg Begins");
                        while((c=fis.read(b))!=-1)
                        {
                            dos.write(b,0,c);
                        }
                        fis.close();
                        System.out.println("File Downloaded ");
                    }
                    break;
                    case 2:
                    {
                        String dirc=path+dis.readUTF();
                        String filename=dis.readUTF();
                        FileOutputStream fos=new FileOutputStream(dirc+"\\"+filename);
                        long size=dis.readLong();
                        System.out.println(size);
                        byte b[]=new byte[(int)size];
                        int c=0;
                        System.out.println("Uploading Begins");
                        while((c=dis.read(b))!=-1)
                        {
                            fos.write(b,0,c);
                        }
                        fos.close();
                        System.out.println("File Revcieved");
                    }
                    break;
                    case 3:
                    {
                        String filepath=dis.readUTF();
                        File fdelete=new File(path+filepath);
                        fdelete.delete();
                        dos.writeBoolean(true);
                        System.out.println("File Deleted");
                    }
                    break;
                    case 4:
                    {
                        String oldfilepath=dis.readUTF();
                        String newfilename=dis.readUTF();
                        System.out.println(path+oldfilepath);
                        String ext=oldfilepath.substring(oldfilepath.lastIndexOf("."));
                        System.out.println(ext);
                        System.out.println(path+newfilename+ext);
                        File oldf=new File(path+oldfilepath);
                        File newf=new File(path+newfilename+ext);
                        oldf.renameTo(newf);
                    }
                    break;
            } 
                    
        }
        catch(Exception e)
        {
            System.out.println("Exception in FTPThread"+e);
        }
        finally
        {
            try
            {
                dos.close();
                dis.close();
            }
            catch(Exception e)
            {
                System.out.println("Exception in fianlly of FTPThread"+e);
            }
        }
    }
}
