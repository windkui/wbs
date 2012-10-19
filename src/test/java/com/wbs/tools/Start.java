package com.wbs.tools;

import org.mortbay.jetty.Server;

import com.springside.zsword.modules.test.utils.JettyTestUtils;

/**
 * 使用Jetty运行调试Web应用, 在Console输入回车停止服务.
 */
public class Start {

	public static final int PORT = 8080;
	public static final String CONTEXT = "/wbs";
	public static final String WEB_ROOT = "WebRoot";
	public static final String BASE_URL = "http://127.0.0.1:" + PORT + CONTEXT;

	public static void main(String[] args) throws Exception {
		System.setProperty("org.mortbay.jetty.Request.maxFormContentSize", "-1");
		Server server = JettyTestUtils.buildNormalServer(PORT, WEB_ROOT,
				CONTEXT);
		server.start();
		System.out.println("Server running at " + BASE_URL);
		System.out.println("Hit Enter in console to stop server");
		if (System.in.read() != 0) {
			server.stop();
			System.out.println("Server stopped");
		}
	}
}
