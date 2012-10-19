package com.wbs.serveice.system;

import org.springframework.stereotype.Service;

import com.wbs.dao.system.AuthorityDao;
import com.wbs.entity.system.Authority;
import com.wbs.serveice.StringIdEntityService;

/**
 * 系统权限Service
 * 
 */
@Service
public class AuthorityService extends
		StringIdEntityService<Authority, AuthorityDao> {

}
