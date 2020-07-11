package com.briup.environment.server;

import java.util.Collection;
import com.briup.environment.bean.Environment;
/**
 * Simple to Introduction
 * @ProjectName:  物联网环境监控数据中心
 * @Package: com.briup.environment.server
 * @InterfaceName:  DBStore
 * @Description:  DBStore提供了入库模块的规范。<br/>
 * 				 该接口的实现类将Environment集合持久化。
 * @author briup
 * @Version: 1.0
 */
public interface DBStore{
	/**
	 *   将环境数据集合进行持久化 。
	 * @param coll 需要储存的Environment集合
	 * @throws Exception
	 */
	public void saveDb(Collection<Environment> coll);
}
