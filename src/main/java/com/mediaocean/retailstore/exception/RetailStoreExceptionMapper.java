package com.mediaocean.retailstore.exception;

import com.mediaocean.retailstore.util.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class RetailStoreExceptionMapper implements ExceptionMapper<RetailStoreException> {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Override
    public Response toResponse(RetailStoreException exception) {
        RestResponse restResponse = new RestResponse(exception.getMessage());

        LOG.error(restResponse.toString(), exception);

        return Response.status(400)
                .entity(restResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
