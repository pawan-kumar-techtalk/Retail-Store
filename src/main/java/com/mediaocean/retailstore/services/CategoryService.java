package com.mediaocean.retailstore.services;

import com.mediaocean.retailstore.vo.CategoryVo;

import java.util.List;

public interface CategoryService {

    List<CategoryVo> getAllCategories();
    void insertCategory(CategoryVo categoryVo);
    CategoryVo getCategory(int id);

}
