package com.mediaocean.retailstore.services;

import com.mediaocean.retailstore.dao.CategoryDao;
import com.mediaocean.retailstore.exception.RetailStoreException;
import com.mediaocean.retailstore.util.JdbiDaoUtil;
import com.mediaocean.retailstore.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao;
    @Autowired private JdbiDaoUtil jdbiDaoUtil;

    @PostConstruct
    public void postConstruct() {
        categoryDao = jdbiDaoUtil.getDaoByName(CategoryDao.class);
    }

    @Override
    public List<CategoryVo> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    @Override
    public void insertCategory(CategoryVo categoryVo) {

        if (categoryVo.getSalesTax() > 100) {
            throw new RetailStoreException("Sales Tax not allowed more than 100%");
        }

        categoryDao.insertCategory(categoryVo.getName(), categoryVo.getSalesTax());
    }

    @Override
    public CategoryVo getCategory(int id) {
        return categoryDao.getCategory(id);
    }
}
