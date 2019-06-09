package com.mediaocean.retailstore.services;

import static org.mockito.Mockito.when;

import com.google.common.collect.Lists;
import com.mediaocean.retailstore.exception.RetailStoreException;
import com.mediaocean.retailstore.vo.CartVo;
import com.mediaocean.retailstore.vo.CategoryVo;
import com.mediaocean.retailstore.vo.ItemizedBillVo;
import com.mediaocean.retailstore.vo.ProductVo;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class BillingServiceImplTest {

    @Mock
    CategoryService categoryService;
    @Mock
    ProductService productService;

    @InjectMocks
    BillingServiceImpl billingServiceImpl;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expectedExceptions = RetailStoreException.class)
    public void testGenerateItemizedBillWithInvalidProductId() {
        int invalidProductId = -1;
        CartVo cartVo = new CartVo();
        List<Integer> productIdList = Lists.newArrayList();
        productIdList.add(invalidProductId); // Incorrect Product ID
        cartVo.setListProductId(productIdList);
        when(productService.getProduct(invalidProductId)).thenReturn(null);

        billingServiceImpl.generateItemizedBill(cartVo);
    }

    @Test
    public void testGenerateItemizedBill() {
        CartVo cartVo = setupValidCart(1,2,3);
        when(productService.getProduct(1)).thenReturn(new ProductVo(1, "PROD1", 1, 100));
        when(productService.getProduct(2)).thenReturn(new ProductVo(2, "PROD2", 2, 50));
        when(productService.getProduct(3)).thenReturn(new ProductVo(3, "PROD3", 3, 200));

        when(categoryService.getCategory(1)).thenReturn(new CategoryVo(1, "CAT1", 10));
        when(categoryService.getCategory(2)).thenReturn(new CategoryVo(2, "CAT2", 5));
        when(categoryService.getCategory(3)).thenReturn(new CategoryVo(3, "CAT3", 0));


        ItemizedBillVo itemizedBillVo = billingServiceImpl.generateItemizedBill(cartVo);

        Assert.assertEquals(itemizedBillVo.getProductList().size(), 3);
        Assert.assertEquals(itemizedBillVo.getTotalAmount(), 362.5);
    }


    private CartVo setupValidCart (final int ...productIds){
        CartVo cartVo = new CartVo();
        List<Integer> productListId = new ArrayList<Integer>() {
            {
                for(int productId : productIds)
                add(productId);
            }
        };

        cartVo.setListProductId(productListId);
        return cartVo;
    }

}
