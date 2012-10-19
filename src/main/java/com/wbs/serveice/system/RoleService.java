package com.wbs.serveice.system;

import org.springframework.stereotype.Service;

import com.wbs.dao.system.RoleDao;
import com.wbs.entity.system.Role;
import com.wbs.serveice.StringIdEntityService;

/**
 * 系统角色Service
 */
@Service
public class RoleService extends StringIdEntityService<Role, RoleDao> {
}
