package com.briup.environment.util;

import java.io.File;
import java.util.Map;

import org.dom4j.Document;

import com.briup.environment.client.Client;
import com.briup.environment.client.Gather;
import com.briup.environment.server.DBStore;
import com.briup.environment.server.Server;
/**
 * 配置模块的实现类
 * @author Administrator
 *
 */

public class ConfigurationImp implements Configuration{
    private File file = new File("src/com/briup/environment/util/class.xml");
	Document doc = Dom4jFactory.getElement(file);
	Map map = Dom4jFactory.getV(doc);	
	@Override  
	public Log getLogger() throws Exception {
		return (Log) map.get("Log");
	}

	@Override
	public Server getServer() throws Exception {
		return (Server) map.get("Server");
	}

	@Override
	public Client getClient() throws Exception {
		return (Client) map.get("Client");
	}

	@Override
	public DBStore getDbStore() throws Exception {
		
		return (DBStore) map.get("DBStore");
	}
	@Override
	public Gather getGather() throws Exception { 
		return (Gather) map.get("Gather");
	}
}
