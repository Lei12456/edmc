package com.briup.environment.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;

import org.ietf.jgss.Oid;


public class BackUPImp implements BackUP{
	private Log logger = new LogImp("BackUPImp");
    private String backupPath;
    public void inti(Properties pro) {
    	backupPath = pro.getProperty("backupPath");
    }
    //读取备份文件对象
	@Override
	public Object load(String key, boolean flag) {
		ObjectInputStream ois = null;
		try {
			 ois = new ObjectInputStream(
					new FileInputStream(backupPath));
			ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(flag) {
			new File(key).delete();
		}
		return null;
		
	}

	@Override
	public void store(String key, Object data, boolean flag) { 
		ObjectOutputStream oos = null;
		if(key != null || key.trim().equals("")) {
			key = backupPath;
		}
	     File file = new File(key);
	     try {
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(data);
			oos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
