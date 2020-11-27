package cn.web.service.impl;

import cn.web.dao.CategoryDao;
import cn.web.dao.impl.CategoryDaoImpl;
import cn.web.entity.Good;
import cn.web.service.CategoryService;

/**
 * @author: huakaimay
 * @since: 2020-11-27
 */
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public String listCategory() {
        return categoryDao.listCategory();
    }


}
