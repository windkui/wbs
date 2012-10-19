package com.wbs.serveice;

import org.springframework.stereotype.Service;

import com.wbs.dao.LogDao;
import com.wbs.entity.Log;

@Service
public class LogService extends StringIdEntityService<Log, LogDao> {

	public void addLog(Log log) {
		this.entityDao.save(log);
	}
	
}
