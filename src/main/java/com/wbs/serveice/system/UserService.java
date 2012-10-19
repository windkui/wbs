package com.wbs.serveice.system;

import org.springframework.stereotype.Service;

import com.wbs.dao.system.UserDao;
import com.wbs.entity.system.User;
import com.wbs.serveice.StringIdEntityService;

/**
 * 系统管理员Service
 * 
 */
@Service
public class UserService extends StringIdEntityService<User, UserDao> {
}
