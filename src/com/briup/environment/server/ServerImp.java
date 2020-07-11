package com.briup.environment.server;

/**
 * 网络模块服务端实现类
 * @author yanglei
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.Properties;

import javax.sound.sampled.Port;

import com.briup.environment.bean.Environment;
import com.briup.environment.util.Configuration;
import com.briup.environment.util.ConfigurationAware;
import com.briup.environment.util.ConfigurationImp;
import com.briup.environment.util.Log;
import com.briup.environment.util.LogImp;
import com.briup.environment.util.ModuleInit;

public class ServerImp implements Server,ConfigurationAware,ModuleInit {
	private ServerSocket serverSocket;
	private InputStream is;
	private ObjectInputStream ois;
	private Log logger = new LogImp("ServerImp");
	private DBStore dbStore;
	private int port;
    
	@Override
	public void init(Properties pro) {
		   //得到端口号
            port = Integer.valueOf(pro.getProperty("port"));		
	}
	
	@Override
	public void setConfiguration(Configuration config) throws Exception {
		   logger = config.getLogger();
		   dbStore = config.getDbStore();
	}
	@Override
	public void reciver() throws Exception {
		logger.info("启动服务端");
		serverSocket = new ServerSocket(port);
		// 建立与客户端的连接
		Socket socket = serverSocket.accept();
		is = socket.getInputStream();
		ois = new ObjectInputStream(is);
		@SuppressWarnings("unchecked")
		Collection<Environment> coll = (Collection<Environment>) ois
				.readObject();
		logger.info("接收成功");
		logger.info("将集合保存到数据库中");
		dbStore.saveDb(coll);
		logger.info("保存成功");
	}

	@Override
	public void shutdown() {
		try {
			logger.info("关闭服务端");
			if (serverSocket != null)
				serverSocket.close();
			if (is != null)
				is.close();
			if (ois != null)
				ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception {
		ConfigurationImp config = new ConfigurationImp();
		Collection<Environment> coll = config.getGather().gather();
		config.getServer().reciver();
		config.getServer().shutdown();
	}

}
