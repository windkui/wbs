package com.wbs.unit;

import javax.sql.DataSource;

import org.junit.AfterClass;
import org.junit.Before;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.test.spring.SpringTxTestCase;
import org.springside.modules.test.utils.DbUnitUtils;

/**
 * 支持事务和注解的Spring单元测试基类
 * 
 */
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class BaseSpringTest extends SpringTxTestCase {
	private static DataSource dataSourceHolder = null;

	protected boolean loadDefaultData = false;

	@Before
	public void loadDefaultData() throws Exception {
		if (dataSourceHolder == null) {
			if (loadDefaultData) {
				DbUnitUtils.loadData(dataSource, "/data/default-data.xml");
			}
			dataSourceHolder = dataSource;
		}
	}

	@AfterClass
	public static void cleanDefaultData() throws Exception {
		// DbUnitUtils.removeData(dataSourceHolder, "/data/default-data.xml");
	}

}
