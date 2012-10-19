package com.wbs.serveice.message;

import org.springframework.stereotype.Service;

import com.wbs.dao.message.BroadcastDao;
import com.wbs.entity.message.Broadcast;
import com.wbs.serveice.StringIdEntityService;

/**
 * 系统广播Service
 */
@Service
public class BroadcastService extends
		StringIdEntityService<Broadcast, BroadcastDao> {

}
