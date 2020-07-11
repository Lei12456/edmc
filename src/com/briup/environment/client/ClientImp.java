package com.briup.environment.client;
/**
 * 网络模块客户端实现类
 * @author yanglei
 * 
 */
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.Properties;

import com.briup.environment.bean.Environment;
import com.briup.environment.util.Configuration;
import com.briup.environment.util.ConfigurationAware;
import com.briup.environment.util.ConfigurationImp;
import com.briup.environment.util.Log;
import com.briup.environment.util.LogImp;
import com.briup.environment.util.ModuleInit;

public class ClientImp implements Client,ConfigurationAware,ModuleInit {
	private Log logger  = new LogImp("ClientImp");
	private int port;
	private String host;
	@Override
	public void init(Properties pro) {
           port = Integer.valueOf(pro.getProperty("port"));
           String host = pro.getProperty("host");
	}	
	@Override
	public void setConfiguration(Configuration config) throws Exception {
		logger = config.getLogger();
	}
	@Override
	public void send(Collection<Environment> coll) throws Exception {
		logger.info("启动客户端");
		Socket socket = new Socket(host,port);
		OutputStream os = socket.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(coll);
		logger.info("发送成功");
		socket.close();
		os.close();
		oos.close();
	}
	
}
