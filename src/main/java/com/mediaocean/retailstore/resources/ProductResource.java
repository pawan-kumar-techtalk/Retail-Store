package com.mediaocean.retailstore.resources;

import com.mediaocean.retailstore.exception.RetailStoreException;
import com.mediaocean.retailstore.services.ProductService;
import com.mediaocean.retailstore.util.Constants;
import com.mediaocean.retailstore.util.RestResponse;
import com.mediaocean.retailstore.vo.ProductVo;
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
@Path(Constants.PRODUCTS)
@Api(Constants.PRODUCTS)
public class ProductResource {

    @Autowired
    private ProductService productService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "List all products", httpMethod = "GET", produces = MediaType.APPLICATION_JSON, response = RestResponse.class)
    public RestResponse listAllProducts() {
        RestResponse restResponse = new RestResponse();
        restResponse.setData(productService.getAllProducts());
        return restResponse;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Create a product", httpMethod = "POST", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public Response createProduct(@ApiParam(value = "Product Object", required = true) ProductVo productVo) {

        if (productVo == null) {
            throw new RetailStoreException("Product details is not available");
        }

        productService.insertProduct(productVo);

        return Response.status(Response.Status.OK).build();
    }
}
