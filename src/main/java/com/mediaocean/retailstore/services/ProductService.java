package com.mediaocean.retailstore.services;

import com.mediaocean.retailstore.vo.ProductVo;

import java.util.List;

public interface ProductService {

    List<ProductVo> getAllProducts();
    void insertProduct(ProductVo productVo);
    ProductVo getProduct(int id);

}
