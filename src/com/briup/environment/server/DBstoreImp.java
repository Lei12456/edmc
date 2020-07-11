package com.briup.environment.server;
/**
 * 入库模块的实现类
 * @author angle
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Collection;
import com.briup.environment.bean.Environment;
import com.briup.environment.util.ConnectonFactory;

public class DBstoreImp implements DBStore {
	public void saveDb(Collection<Environment> coll) {
		Connection  conn  = null;
		PreparedStatement ps = null;
		try {
			// 获取连接
			conn = ConnectonFactory.getConnection();
			/*
			 * 创建每天的数据插入语句
			 * 遍历所有对象，将对象的环境对象保存到表中
			 */
			for (int j = 1; j <= 31; j++) {
				String sql = "insert into e_detail_" + j
						+ " values(?,?,?,?,?,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				for (Environment en : coll) {
					if (en.getGather_date().getDate() == j) {
						ps.setString(1, en.getName());
						ps.setString(2, en.getSrcId());
						ps.setString(3, en.getDstId());
						ps.setString(4, en.getSersorAddress());
						ps.setInt(5, en.getCount());
						ps.setString(6, en.getCmd());
						ps.setInt(7, en.getStatus());
						ps.setFloat(8, en.getData());
						ps.setTimestamp(9, en.getGather_date());
						ps.addBatch();
					}
				}
				ps.executeBatch();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
