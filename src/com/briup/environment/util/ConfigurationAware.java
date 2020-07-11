package com.briup.environment.util;
/**
 * 各个模块实现该接口。实现传递配置对象 
 * @author lining
 *
 */
public interface ConfigurationAware {
	
	void  setConfiguration (Configuration config) throws Exception;
}
