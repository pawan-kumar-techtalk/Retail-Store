package com.mediaocean.retailstore.resources;

import com.mediaocean.retailstore.exception.RetailStoreException;
import com.mediaocean.retailstore.services.ProductService;
import com.mediaocean.retailstore.util.RestResponse;
import com.mediaocean.retailstore.vo.ProductVo;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductResourceTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductResource productResource;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListAllProducts() {
        List<ProductVo> productVoList = new ArrayList<ProductVo>() {
            {
                add(new ProductVo(1, "PROD1", 1, 100));
                add(new ProductVo(2, "PROD2", 2, 200));
                add(new ProductVo(3, "PROD3", 3, 300));
            }
        };
        when(productService.getAllProducts()).thenReturn(productVoList);

        RestResponse restResponse = productResource.listAllProducts();

        Assert.assertEquals(restResponse.getData(), productVoList);
        verify(productService).getAllProducts();
    }

    @Test(expectedExceptions = RetailStoreException.class)
    public void testCreateProductWithNullCategoryVo() {
        productResource.createProduct(null);
    }

    @Test
    public void testCreateProduct() {
        ProductVo productVo = new ProductVo(1, "PROD1", 1, 100);
        productResource.createProduct(productVo);

        verify(productService).insertProduct(productVo);
    }

}
