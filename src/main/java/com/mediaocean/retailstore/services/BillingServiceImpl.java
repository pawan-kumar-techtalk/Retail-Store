package com.mediaocean.retailstore.services;


import com.google.common.collect.Lists;
import com.mediaocean.retailstore.exception.RetailStoreException;
import com.mediaocean.retailstore.vo.CartVo;
import com.mediaocean.retailstore.vo.CategoryVo;
import com.mediaocean.retailstore.vo.ItemizedBillVo;
import com.mediaocean.retailstore.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillingServiceImpl implements BillingService {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @Override
    public ItemizedBillVo generateItemizedBill(CartVo cartVo) {

        ItemizedBillVo itemizedBillVo = new ItemizedBillVo();
        List<ProductVo> productVoList = Lists.newArrayList();
        double totalCartAmount = 0.0;

        for (Integer productId : cartVo.getListProductId()) {
            ProductVo productVo = productService.getProduct(productId);

            if (productVo == null) {
                throw new RetailStoreException("Invalid Product ID in the Cart [" + productId + "]. " +
                        "Resend request with correct product Id in the list");
            }

            updateProductPriceWithSalesTax(productVo, categoryService.getCategory(productVo.getCategoryId()));
            totalCartAmount += productVo.getPrice();
            productVoList.add(productVo);
        }
        itemizedBillVo.setProductList(productVoList);
        itemizedBillVo.setTotalAmount(totalCartAmount);
        return itemizedBillVo;
    }


    private void updateProductPriceWithSalesTax(ProductVo productVo, CategoryVo categoryVo) {
        double priceWithSalesTax = productVo.getPrice() + ((categoryVo.getSalesTax() / 100) * productVo.getPrice());
        productVo.setPrice(priceWithSalesTax);
    }

}
