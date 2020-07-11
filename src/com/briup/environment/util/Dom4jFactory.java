package com.briup.environment.util;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;
/**
 * 实现dom解析xml
 * @author Administrator
 *
 */
public class Dom4jFactory {
	private static  Map<String, Object> map = new HashMap<String, Object>();
	//获取document对象
    public static Document getElement(File file) {
    	Document document = null;
    	SAXReader reader = new SAXReader();
    	try {
			document = reader.read(file);			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
	}
    public static Map getV(Document d) {
    	//获取根节点
    	Element root = d.getRootElement();
    	//获取子元素的迭代器
    	Iterator<Element> elementIterator = root.elementIterator();
    	while(elementIterator.hasNext()) {
    		Element childele = elementIterator.next();//获取子节点
    		//获取子元素属性值的迭代器
            String interfacename = childele.getName();
            String  className = childele.attributeValue("class");
    		try {
				Object obj = Class.forName(className).newInstance();
				map.put(interfacename, obj);//将得到的接口名和对象放到键值对的集合中
			} catch (Exception e) {
				e.printStackTrace();
			}
    		//创建一个容器 保存每个类使用的参数 properties
			Properties pro = new Properties();
			//遍历每个子节点的参数值
    		Iterator<Element> childiterator = childele.elementIterator();
    		while(childiterator.hasNext()) {
    			Element child = childiterator.next();
    			String name = child.getName();
    			String value = child.getStringValue();
    			pro.setProperty(name, value);
    		}
    		System.out.println(pro);
		}
    	return map;
    }
    @Test
    public void test() {
    	File file = new File("src/com/briup/environment/util/class.xml");
    	Document doc = Dom4jFactory.getElement(file);
    	Dom4jFactory.getV(doc);
    }
}










