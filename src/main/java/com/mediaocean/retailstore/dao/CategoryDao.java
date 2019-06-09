package com.mediaocean.retailstore.dao;

import com.mediaocean.retailstore.vo.CategoryVo;
import com.mediaocean.retailstore.vo.CategoryVoMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

public interface CategoryDao {

    @SqlQuery("select * from category")
    @Mapper(CategoryVoMapper.class)
    List<CategoryVo> getAllCategories();

    @SqlUpdate("INSERT INTO CATEGORY (ID, NAME, SALES_TAX) VALUES (CATEGORY_SEQUENCE.NEXTVAL, :name, :salesTax)")
    int insertCategory(@Bind("name") String name, @Bind("salesTax") double salesTax);


    @SqlQuery("select * from category where id = :id")
    @Mapper(CategoryVoMapper.class)
    CategoryVo getCategory(@Bind("id") int id);

}
