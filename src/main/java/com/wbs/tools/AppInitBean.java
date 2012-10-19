package com.wbs.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.wbs.serveice.system.AccountManager;

@Component
@Lazy(false)
public class AppInitBean {

	private static final Logger logger = LoggerFactory
			.getLogger(AppInitBean.class);

	public AppInitBean() {
		logger.debug("Application Init...:" + this);
	}

	private AccountManager accountManager;

	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
		AppLoginUtils.setAccountManager(accountManager);
		logger.debug("init " + AppLoginUtils.class.getSimpleName() + "...:"
				+ this.accountManager);
	}
}
