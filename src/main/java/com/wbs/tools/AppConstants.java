package com.wbs.tools;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.util.Properties;

import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jacob.com.LibraryLoader;

/**
 * 系统常量定义类
 */
public final class AppConstants {

	private static final Logger logger = LoggerFactory
			.getLogger(AppConstants.class);
	public static final double version = 1.0;
	private static final String ENCODING = "UTF-8";
	private static Properties properties;
	private static final String CONST_FILE = "/appconstants.properties";

	public static Scheduler SCHEDULER;

	static {
		properties = new Properties();
		try {
			loadConstants();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			System.exit(-1);
		}
	}

	public static void loadConstants() throws Exception {
		BufferedInputStream bin = null;
		PrintWriter writer = null;
		try {
			String jacobPath = "/jacob-1.16-M2-x86.dll";
			// 判断是否为 64位 机器
			String bitValue = System.getProperty("sun.arch.data.model");
			if ("64".equals(bitValue)) {
				jacobPath = "/jacob-1.16-M2-x64.dll";
			}
			String jacobDllPath = AppConstants.class.getResource(jacobPath)
					.getPath();
			jacobDllPath = jacobDllPath.substring(1);
			logger.info("Set Jacob Library Path:" + jacobDllPath);
			System.setProperty(LibraryLoader.JACOB_DLL_PATH, jacobDllPath);
			String constFile = AppConstants.class.getResource(CONST_FILE)
					.getFile();
			constFile = URLDecoder.decode(constFile, ENCODING);
			bin = new BufferedInputStream(new FileInputStream(constFile));
			properties.clear();
			properties.load(bin);
			logger.info("Load AppConstants: " + constFile);
			StringWriter swr = new StringWriter();
			writer = new PrintWriter(swr, true);
			properties.list(writer);
			logger.info(swr.getBuffer().toString());
		} catch (Exception e) {
			throw e;
		} finally {
			if (writer != null) {
				writer.flush();
				writer.close();
			}
			if (bin != null) {
				bin.close();
			}
		}
	}

	/**
	 * 根据属性名,获取常量值
	 * 
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static final String getConstant(String name, String defaultValue) {
		return properties.getProperty(name, defaultValue);
	}

	/** 默认资源文件根路径 */
	public static final String RESOURCES_ROOT = properties.getProperty(
			"default.resources.root", "resources/");
	/** 菜单图标上传文件夹 */
	public static final String MENU_ICON_ROOT = properties.getProperty(
			"default.menu.icon.root", "images/icon/menu/");
	/** 系统菜单默认图标 */
	public static final String MENU_ICON = properties.getProperty(
			"default.menu.icon", "images/defaults/menu_def.gif");
	/** 系统角色默认图标 */
	public static final String ROLE_ICON = properties.getProperty(
			"default.role.icon", "images/defaults/role_def.gif");
	/** 状态启用 */
	public static final int STATUS_OK = 1;
	public static final long LSTATUS_OK = 1L;
	/** 状态禁用 */
	public static final int STATUS_NO = 0;
	public static final long LSTATUS_NO = 0L;

	public static final String TASK_INFO_KEY = "taskInfo";
	public static final String FLOW_OPT_KEY = "dutyFlowOperation";

	// 默认FTP配置
	public static final String FTP_HOST = properties.getProperty(
			"default.ftp.host", "127.0.0.1");
	public static final String FTP_PORT = properties.getProperty(
			"default.ftp.port", "21");
	public static final String FTP_USER_NAME = properties.getProperty(
			"default.ftp.username", "");
	public static final String FTP_USER_PASSWORD = properties.getProperty(
			"default.ftp.password", "");
	/** 系统文件中心跟路径 */
	public static final String FILECENTER_ROOT_PATH = properties.getProperty(
			"fileCenter.rootPath", "files/");
	/** 本地数据中心保存根路径 */
	public static final String LOCALDATA_ROOT_PATH = FILECENTER_ROOT_PATH
			+ properties.getProperty("localdata.rootPath", "ftp_down/");
	/** 远程文件浏览器根目录 */
	public static final String FILES_EXPLORER_ROOT = FILECENTER_ROOT_PATH
			+ properties.getProperty("files.explorer.rootPath", "files/");
	/** 气象工具文件夹根路径 */
	public static final String WEATHER_TOOLS_ROOT = FILES_EXPLORER_ROOT
			+ properties.getProperty("tools.rootPath", "tools/");
	/** 服务器上气象工具所属磁盘分区 */
	public static final String TOOLS_SERVER_DRIVER = properties.getProperty(
			"tools.serverDriver", "D:/");
	/** 本地映射气象工具所属分区 */
	public static final String TOOLS_LINK_DRIVER = properties.getProperty(
			"tools.linkDriver", "D:/");
	public static final String PRODUCT_ROOT = FILECENTER_ROOT_PATH
			+ properties.getProperty("product.rootPath", "products/");
	public static final String DATA_GROUP = "data_group";
}