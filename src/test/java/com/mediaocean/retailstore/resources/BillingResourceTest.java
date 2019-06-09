package com.mediaocean.retailstore.resources;

import com.mediaocean.retailstore.exception.RetailStoreException;
import com.mediaocean.retailstore.services.BillingService;
import com.mediaocean.retailstore.vo.CartVo;
import com.mediaocean.retailstore.vo.ItemizedBillVo;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BillingResourceTest {

    @Mock
    private BillingService billingService;

    @InjectMocks
    private BillingResource billingResource;


    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expectedExceptions = RetailStoreException.class)
    public void testGenerateItemizedBillWithNullCart() {
        billingResource.generateItemizedBill(null);
    }

    @Test(expectedExceptions = RetailStoreException.class)
    public void testGenerateItemizedBillWithProductListNull() {
        CartVo cartVo = new CartVo();
        cartVo.setListProductId(null);
        billingResource.generateItemizedBill(cartVo);
    }

    @Test(expectedExceptions = RetailStoreException.class)
    public void testGenerateItemizedBillWithProductListEmpty() {
        CartVo cartVo = new CartVo();
        cartVo.setListProductId(new ArrayList<Integer>());
        billingResource.generateItemizedBill(cartVo);
    }

    @Test
    public void testGenerateItemizedBill() {
        CartVo cartVo = new CartVo();
        List<Integer> productListId = new ArrayList<Integer>() {
            {
                add(1);
            }
        };
        cartVo.setListProductId(productListId);
        when(billingService.generateItemizedBill(cartVo)).thenReturn(new ItemizedBillVo());

        billingResource.generateItemizedBill(cartVo);

        verify(billingService).generateItemizedBill(cartVo);
    }
}
