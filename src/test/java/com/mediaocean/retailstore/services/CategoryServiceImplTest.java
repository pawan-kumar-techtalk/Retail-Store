package com.mediaocean.retailstore.services;

import com.mediaocean.retailstore.dao.CategoryDao;
import com.mediaocean.retailstore.exception.RetailStoreException;
import com.mediaocean.retailstore.util.JdbiDaoUtil;
import com.mediaocean.retailstore.vo.CategoryVo;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CategoryServiceImplTest {

    @Mock
    private CategoryDao categoryDao;
    @Mock
    private JdbiDaoUtil jdbiDaoUtil;

    @InjectMocks
    private CategoryServiceImpl categoryServiceImpl;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(jdbiDaoUtil.getDaoByName(CategoryDao.class)).thenReturn(categoryDao);
    }

    @Test
    public void testGetAllCategories() {
        categoryServiceImpl.getAllCategories();
        verify(categoryDao).getAllCategories();
    }

    @Test(expectedExceptions = RetailStoreException.class)
    public void testInsertCategoryWithInvalidSalesTax() {
        CategoryVo categoryVo = new CategoryVo(1, "CAT1", 150);

        categoryServiceImpl.insertCategory(categoryVo);
    }

    @Test
    public void testInsertCategory() {
        CategoryVo categoryVo = new CategoryVo(1, "CAT1", 10);

        categoryServiceImpl.insertCategory(categoryVo);
        verify(categoryDao).insertCategory(categoryVo.getName(), categoryVo.getSalesTax());
    }

    @Test
    public void testGetCategory() {
        CategoryVo categoryVo = new CategoryVo(1, "CAT1", 10);
        when(categoryDao.getCategory(1)).thenReturn(categoryVo);

        Assert.assertEquals(categoryServiceImpl.getCategory(1), categoryVo);
    }
}
