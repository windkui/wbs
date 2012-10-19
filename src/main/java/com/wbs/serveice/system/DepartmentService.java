package com.wbs.serveice.system;

import org.springframework.stereotype.Service;

import com.wbs.dao.system.DepartmentDao;
import com.wbs.entity.system.Department;
import com.wbs.serveice.StringIdEntityService;

/**
 * 部门Service
 * 
 */
@Service
public class DepartmentService extends
		StringIdEntityService<Department, DepartmentDao> {

}
