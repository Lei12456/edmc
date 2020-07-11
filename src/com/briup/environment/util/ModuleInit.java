package com.briup.environment.util;

import java.util.Properties;

/**
 * 模块初始化接口
 * @author lining
 * 将xml中配置信息传递到 各个模块中
 */
public interface ModuleInit {
	
	void init(Properties pro);
}
