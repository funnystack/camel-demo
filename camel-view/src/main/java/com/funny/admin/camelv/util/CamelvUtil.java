package com.funny.admin.camelv.util;

import com.funny.admin.camelv.entity.vo.ComponentOption;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 常用工具类<br/>
 * 
 * @author xiaoka
 *
 */
public class CamelvUtil {

	public static Logger logger = LoggerFactory.getLogger(CamelvUtil.class);

	/**
	 * 将给定的组件参数解析为list集合
	 * 
	 * @param option
	 * @return
	 */
	public static List<ComponentOption> parseOption(String option) {
		List<ComponentOption> optionList = new ArrayList<ComponentOption>();
		if (!StringUtils.isBlank(option)) {
			// 此处的拆分和前台对应,可自行修改
			String[] arr = option.split(",");
			for (String line : arr) {
				String[] params = line.split("->");
				ComponentOption co = new ComponentOption();
				if (params.length > 0) {
					co.setName(params[0]);
				}
				if (params.length > 1) {
					co.setValue(params[1]);
				}
				optionList.add(co);
			}
		}
		return optionList;
	}

	/**
	 * 在原来的参数上添加新的参数，同时指定分隔符
	 * 
	 * @param oldParam
	 * @param newParam
	 * @param split
	 * @return
	 */
	public static String addParam(String oldParam, String newParam, String split) {

		// 新参数为空，默认返回之前
		if (StringUtils.isBlank(newParam)) {
			return oldParam;
		}

		// 之前为空，默认返回新参数
		if (StringUtils.isBlank(oldParam)) {
			return newParam;
		}
		String[] oldArr = oldParam.split(split);
		// 之前存在该参数，默认返回之前的参数
		for (String pm : oldArr) {
			if (pm.equals(newParam)) {
				return oldParam;
			}
		}
		// 之前参数不为空且不存在新添加的参数，那么添加
		return oldParam + split + newParam;
	}

	/**
	 * 解析tomcat端口
	 * 
	 * @param filePath
	 * @return
	 */
	public static String parseTomcatPort(String filePath) {
		String port = "";
		try {
			File serverXmlFile = new File(filePath);
			if (!serverXmlFile.exists()) {
				// 解析端口失败，以默认配置为主
				logger.error("解析tomcat端口失败， " + filePath + " 文件不存在");
				return port;
			}
			// 解析server.xml文件
			port = parseServerXml(serverXmlFile);
			if ("".equals(port)) {
				// 解析失败，使用手动配置的
				logger.error("解析 " + filePath + " 失败，无法获取端口信息");
			}
		} catch (Exception e) {
			logger.error("解析tomcat端口失败!,路径:" + filePath);
			e.printStackTrace();
		}
		return port;
	}

	@SuppressWarnings("unchecked")
	private static String parseServerXml(File file) {
		String port = "";
		// 解析books.xml文件
		// 创建SAXReader的对象reader
		SAXReader reader = new SAXReader();
		try {
			// 通过reader对象的read方法加载books.xml文件,获取docuemnt对象。
			Document document = reader.read(file);
			// 通过document对象获取根节点bookstore
			Element bookStore = document.getRootElement();
			// 通过element对象的elementIterator方法获取迭代器
			Iterator<Element> it = bookStore.elementIterator();
			// 遍历迭代器，获取根节点中的信息（书籍）
			while (it.hasNext()) {
				Element book = it.next();
				Iterator<Element> itt = book.elementIterator();
				while (itt.hasNext()) {
					Element bookChild = itt.next();
					if ("Connector".equals(bookChild.getName())) {
						String protocol = bookChild.attributeValue("protocol");
						if (protocol != null && protocol.trim().toLowerCase().contains("http")) {
							port = bookChild.attributeValue("port");
						}
						break;
					}
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return port;
	}

	/**
	 * 获取ip地址
	 * 
	 * @return
	 */
	public static String getLocalIp() {
		String localIp = "127.0.0.1";
		try {
			localIp = InetAddress.getLocalHost().getHostAddress();
		} catch (Exception e) {
			logger.error("获取本机IP失败");
			e.printStackTrace();
		}
		return localIp;
	}

	public static void main(String[] args) {
		System.out.println(getLocalIp());
	}

}
