package com.wbs.dao.system;

import org.springframework.stereotype.Repository;

import com.wbs.dao.base.StringIdEntityDao;
import com.wbs.entity.system.User;

/**
 * 用户对象DAO
 */
@Repository
public class UserDao extends StringIdEntityDao<User> {
}
