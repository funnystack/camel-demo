package com.funny.admin.camel.component.persist;

import com.funny.admin.camelv.constant.Global;

import java.text.SimpleDateFormat;
import java.util.Date;


public class PersistFileComponent {

	/** 日志存储的基目录 */
	private static String logFileBasePath = "";
	/** 文件名前缀 */
	private static final String FILE_PREFFIX = "camelvLog";
	/** 文件名后缀 */
	private static final String FILE_SUFFIX = ".paldb";
	/** 存储器 */
//	private static StoreWriter logWriter = null;

	/**
	 * 初始化日志存储器
	 */
	public static void init() {
		// 日志每天一份
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf.format(new Date());
		logFileBasePath = Global.getConfig("camelv.server.log.base.path");
//		if (logWriter != null) {
//			logWriter.close();
//		}
//		logWriter = PalDB.createWriter(new File(logFileBasePath + File.separator + FILE_PREFFIX + time + FILE_SUFFIX));
	}

	/**
	 * 持久化
	 *
	 * @param key
	 * @param value
	 */
	public static void persist(String key, Object value) {
//		if (logWriter == null) {
//			init();
//		}
//		logWriter.put(key, value);
	}

}
