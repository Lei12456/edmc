package com.briup.environment.client;

import java.util.Collection;

import com.briup.environment.bean.Environment;

/**
 * Simple to Introduction
 * @ProjectName:  物联网环境监控数据中心系统
 * @Package: com.briup.environment.client
 * @InterfaceName:  Client
 * @Description:  Client接口是采集系统网络模块客户端的规范.<br/>
 * 				Client的作用就是与服务器端进行通信,传递数据
 * @CreateDate:   2019-11-24
 * @author briup
 * @Version: 1.0
 */
public interface Client{
	/**
	 * send方法用于与服务器端进行交互，发送Environment集合至服务器端。
	 * @param coll
	 * @throws Exception
	 */
	public void send(Collection<Environment> coll)throws Exception;
}
