package com.mediaocean.retailstore.dao;

import com.mediaocean.retailstore.vo.ProductVo;
import com.mediaocean.retailstore.vo.ProductVoMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

public interface ProductDao {

    @SqlQuery("select * from product")
    @Mapper(ProductVoMapper.class)
    List<ProductVo> getAllProducts();

    @SqlUpdate("INSERT INTO PRODUCT (ID, NAME, CATEGORY_ID, PRICE) VALUES (PRODUCT_SEQUENCE.NEXTVAL, :name, :categoryId, :price)")
    int insertProduct(@Bind("name") String name, @Bind("categoryId") int categoryId, @Bind("price") double price);

    @SqlQuery("select * from product where id = :id")
    @Mapper(ProductVoMapper.class)
    ProductVo getProduct(@Bind("id") int id);
}
