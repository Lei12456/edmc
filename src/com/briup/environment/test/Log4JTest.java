package com.briup.environment.test;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.briup.environment.client.GatherImp;

public class Log4JTest {
 
    public static void main(String[] args) {   
    	//根记录器和记录器的区别
        Logger logger = Logger.getLogger(GatherImp.class);
        //Logger rootLogger = Logger.getRootLogger();
        //BasicConfigurator.configure();
        //测试配置文件
        PropertyConfigurator.configure("log4j.properties");
        logger.debug("这是debug");
        logger.info("这是info");
        logger.warn("这是warn");
        logger.error("这是error");
        logger.fatal("这是fatal");
    }
 
}