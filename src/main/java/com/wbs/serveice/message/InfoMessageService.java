package com.wbs.serveice.message;

import org.springframework.stereotype.Service;

import com.wbs.dao.message.InfoMessageDao;
import com.wbs.entity.message.InfoMessage;
import com.wbs.serveice.StringIdEntityService;

/**
 * 个人消息Service
 */
@Service
public class InfoMessageService extends
		StringIdEntityService<InfoMessage, InfoMessageDao> {

}
