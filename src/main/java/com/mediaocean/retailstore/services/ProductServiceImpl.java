package com.mediaocean.retailstore.services;

import com.mediaocean.retailstore.dao.CategoryDao;
import com.mediaocean.retailstore.dao.ProductDao;
import com.mediaocean.retailstore.exception.RetailStoreException;
import com.mediaocean.retailstore.util.JdbiDaoUtil;
import com.mediaocean.retailstore.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;
    private CategoryDao categoryDao;

    @Autowired
    private JdbiDaoUtil jdbiDaoUtil;

    @PostConstruct
    public void postConstruct() {
        productDao = jdbiDaoUtil.getDaoByName(ProductDao.class);
        categoryDao = jdbiDaoUtil.getDaoByName(CategoryDao.class);
    }

    @Override
    public List<ProductVo> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public void insertProduct(ProductVo productVo) {

        if (categoryDao.getCategory(productVo.getCategoryId()) == null) {
            throw new RetailStoreException("Invalid Category");
        }

        productDao.insertProduct(productVo.getName(), productVo.getCategoryId(), productVo.getPrice());
    }

    @Override
    public ProductVo getProduct(int id) {
        return productDao.getProduct(id);
    }
}
