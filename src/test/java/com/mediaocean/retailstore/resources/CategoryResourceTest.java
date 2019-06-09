package com.mediaocean.retailstore.resources;

import com.mediaocean.retailstore.exception.RetailStoreException;
import com.mediaocean.retailstore.services.CategoryService;
import com.mediaocean.retailstore.util.RestResponse;
import com.mediaocean.retailstore.vo.CategoryVo;
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

public class CategoryResourceTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryResource categoryResource;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListAllCategories() {
        List<CategoryVo> categoryVoList = new ArrayList<CategoryVo>() {
            {
                add(new CategoryVo(1, "CAT1", 10));
                add(new CategoryVo(2, "CAT2", 20));
                add(new CategoryVo(3, "CAT3", 30));
            }
        };
        when(categoryService.getAllCategories()).thenReturn(categoryVoList);

        RestResponse restResponse = categoryResource.listAllCategories();

        Assert.assertEquals(restResponse.getData(), categoryVoList);
        verify(categoryService).getAllCategories();
    }

    @Test(expectedExceptions = RetailStoreException.class)
    public void testCreateCategoryWithNullCategoryVo() {
        categoryResource.createCategory(null);
    }

    @Test
    public void testCreateCategory() {
        CategoryVo categoryVo = new CategoryVo(1, "CAT1", 10);
        categoryResource.createCategory(categoryVo);

        verify(categoryService).insertCategory(categoryVo);
    }

}
