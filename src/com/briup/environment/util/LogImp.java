package com.briup.environment.util;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
/**
 * 日志模块的实现类
* @author lining
 * 类中的方法表示： 不能场景下调用不同的方法。
 * 1.正常信息： info
 * 2.警告信息： warn
 * 3.报错信息：error
 */
public class LogImp implements Log{
	 public  String classname = null;
     public Logger logger = null;
	 public LogImp() {
	 }  
	public LogImp(String classname) {
		this.classname = classname;
		logger = Logger.getLogger(classname);
	}
	@Override
	public void debug(String message) {
		logger.debug(message);
	}
	
	@Override
	public void info(String message) {
		logger.info(message);
		
	}

	@Override
	public void warn(String message) {
		logger.warn(message);
	}

	@Override
	public void error(String message) {
		logger.error(message);
	}

	@Override
	public void fatal(String message) {
		logger.fatal(message);
	}
	
}
