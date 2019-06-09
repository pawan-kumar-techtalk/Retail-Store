package com.mediaocean.retailstore.resources;

import com.mediaocean.retailstore.exception.RetailStoreException;
import com.mediaocean.retailstore.services.BillingService;
import com.mediaocean.retailstore.util.Constants;
import com.mediaocean.retailstore.util.RestResponse;
import com.mediaocean.retailstore.vo.CartVo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Controller
@Path(Constants.BILLING)
@Api(Constants.BILLING)
public class BillingResource {

    @Autowired
    private BillingService billingService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Generate itemized bill of given product IDs", httpMethod = "POST", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON, response = RestResponse.class)
    public RestResponse generateItemizedBill(@ApiParam(value = "List of product IDs for billing", required = true) CartVo cartVo) {

        if (cartVo == null || cartVo.getListProductId() == null || cartVo.getListProductId().size() == 0) {
            throw new RetailStoreException("Product list is empty");
        }

        RestResponse restResponse = new RestResponse();
        restResponse.setData(billingService.generateItemizedBill(cartVo));
        return restResponse;
    }

}
