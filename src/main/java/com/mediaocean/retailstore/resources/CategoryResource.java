package com.mediaocean.retailstore.resources;

import com.mediaocean.retailstore.exception.RetailStoreException;
import com.mediaocean.retailstore.services.CategoryService;
import com.mediaocean.retailstore.util.Constants;
import com.mediaocean.retailstore.util.RestResponse;
import com.mediaocean.retailstore.vo.CategoryVo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
@Path(Constants.CATEGORIES)
@Api(Constants.CATEGORIES)
public class CategoryResource {

    @Autowired private CategoryService categoryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "List all categories", httpMethod = "GET", produces = MediaType.APPLICATION_JSON, response = RestResponse.class)
    public RestResponse listAllCategories() {
        RestResponse restResponse = new RestResponse();
        restResponse.setData(categoryService.getAllCategories());
        return restResponse;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Create a category", httpMethod = "POST", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public Response createCategory(@ApiParam(value = "Category Object", required = true) CategoryVo categoryVo) {

        if (categoryVo == null) {
            throw new RetailStoreException("Category details is not available");
        }

        categoryService.insertCategory(categoryVo);

        return Response.status(Response.Status.OK).build();
    }


}
