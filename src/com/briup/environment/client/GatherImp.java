package com.briup.environment.client;
/**采集模块实现类
 * @author yanglei
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import com.briup.environment.bean.Environment;
import com.briup.environment.util.BackUP;
import com.briup.environment.util.BackUPImp;
import com.briup.environment.util.Configuration;
import com.briup.environment.util.ConfigurationAware;
import com.briup.environment.util.Log;
import com.briup.environment.util.LogImp;

public class GatherImp implements Gather,ConfigurationAware{
	private Log logger  = new LogImp("GantherImp");
	private BackUP backUp = new BackUPImp();
	@Override
	public void setConfiguration(Configuration config) throws Exception {
		  logger = config.getLogger();
	}
	@SuppressWarnings("resource")
	@Override
	public Collection<Environment> gather() throws Exception {
		ArrayList<Environment> list = new ArrayList<Environment>();
		FileReader in = null;// 使用节点流，数据源是数据文件
		BufferedReader bfr = null; // 包装流可以读一整行
		File file = new File("src/data");
		in = new FileReader(file);
		bfr = new BufferedReader(in);
		String data = null;
		while ((data = bfr.readLine()) != null) {
			// 读取到的数据用‘|’分割
			String[] str = data.split("\\|");
			// 数据解析判断温度
			if (str[3].equals("16")) {
				int count = Integer.parseInt(str[4]);
				int status = Integer.parseInt(str[7]);
				long time = Long.parseLong(str[8]);
				Timestamp gather_date = new Timestamp(time);
				int pInt = Integer.parseInt(str[6].substring(0, 4), 16);
				int pInt2 = Integer.parseInt(str[6].substring(4, 8), 16);
				float Temperature = (float) (pInt * 0.00268127 - 46.85);
				float Humidity = (float) (pInt2 * 0.00190735 - 6);
				Environment e1 = new Environment("温度", str[0], str[1], str[2],
						str[3], count, str[5], status, Temperature,
						gather_date);
				Environment e2 = new Environment("湿度", str[0], str[1], str[2],
						str[3], count, str[5], status, Humidity, gather_date);
				list.add(e1);
				list.add(e2);
			}

			else if (str[3].equals("256")) {
				int count = Integer.parseInt(str[4]);
				int status = Integer.parseInt(str[7]);
				long time = Long.parseLong(str[8]);
				Timestamp gather_date = new Timestamp(time);
				String str1 = str[6].substring(0, 4);
				int pInt = Integer.parseInt(str1, 16);
				float pfloat = (float) pInt;
				Environment e3 = new Environment("光照强度", str[0], str[1], str[2],
						str[3], count, str[5], status, pfloat, gather_date);
				list.add(e3);
			} else if (str[3].equals("1280")) {
				int count = Integer.parseInt(str[4]);
				int status = Integer.parseInt(str[7]);
				long time = Long.parseLong(str[8]);
				Timestamp gather_date = new Timestamp(time);
				String str1 = str[6].substring(0, 4);
				int pInt = Integer.parseInt(str1, 16);
				float pfloat = (float) pInt;
				Environment e4 = new Environment("二氧化碳浓度", str[0], str[1],
						str[2], str[3], count, str[5], status, pfloat,
						gather_date);
				list.add(e4);
			}
		}
		long backUpData = file.length();//获取文件长度
		backUp.store(null, backUpData, true);
		System.out.println(backUpData);
		return list;

	}
	
}







