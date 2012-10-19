package com.wbs.dao.system;

import org.springframework.stereotype.Repository;

import com.wbs.dao.base.StringIdEntityDao;
import com.wbs.entity.system.Menu;

/**
 * 系统菜单DAO
 */
@Repository
public class MenuDao extends StringIdEntityDao<Menu> {

	@Override
	public void save(Menu entity) {
		super.save(entity);
		this.evictSubMenusCache(entity);
	}

	@Override
	public void delete(Menu entity) {
		super.delete(entity);
		this.evictSubMenusCache(entity);
	}

	/**
	 * 清理工具类别对应的集合属性二级缓存
	 * 
	 * @param entity
	 */
	private void evictSubMenusCache(Menu entity) {
		this.evictRoleCollectionRegion(Menu.class.getName() + ".subMenus");

	}
}
