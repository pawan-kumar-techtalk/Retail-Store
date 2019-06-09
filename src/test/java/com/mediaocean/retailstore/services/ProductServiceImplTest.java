package com.mediaocean.retailstore.services;

import com.mediaocean.retailstore.dao.CategoryDao;
import com.mediaocean.retailstore.dao.ProductDao;
import com.mediaocean.retailstore.exception.RetailStoreException;
import com.mediaocean.retailstore.util.JdbiDaoUtil;
import com.mediaocean.retailstore.vo.CategoryVo;
import com.mediaocean.retailstore.vo.ProductVo;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductServiceImplTest {

    @Mock
    private ProductDao productDao;
    @Mock
    private CategoryDao categoryDao;
    @Mock
    private JdbiDaoUtil jdbiDaoUtil;

    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(jdbiDaoUtil.getDaoByName(ProductDao.class)).thenReturn(productDao);
        when(jdbiDaoUtil.getDaoByName(CategoryDao.class)).thenReturn(categoryDao);
    }

    @Test
    public void testGetAllProducts() {
        productServiceImpl.getAllProducts();
        verify(productDao).getAllProducts();
    }

    @Test(expectedExceptions = RetailStoreException.class)
    public void testInsertProductWithInvalidCategoryId() {
        int invalidCategoryId = -1;
        ProductVo productVo = new ProductVo(1, "PROD1", invalidCategoryId, 100);
        when(categoryDao.getCategory(invalidCategoryId)).thenReturn(null);
        productServiceImpl.insertProduct(productVo);
    }

    @Test
    public void testInsertProduct() {
        ProductVo productVo = new ProductVo(1, "PROD1", 1, 100);
        when(categoryDao.getCategory(1)).thenReturn(new CategoryVo(1, "CAT1", 10));
        productServiceImpl.insertProduct(productVo);
        verify(productDao).insertProduct(productVo.getName(), productVo.getCategoryId(), productVo.getPrice());
    }

    @Test
    public void testGetProduct() {
        ProductVo productVo = new ProductVo(1, "PROD1", 1, 100);
        when(productDao.getProduct(1)).thenReturn(productVo);

        Assert.assertEquals(productServiceImpl.getProduct(1), productVo);
    }

}
