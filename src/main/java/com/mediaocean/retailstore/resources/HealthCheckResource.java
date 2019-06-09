package com.mediaocean.retailstore.resources;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Controller
@Path("/healthcheck")
@Produces(MediaType.APPLICATION_JSON)
public class HealthCheckResource {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GET
    public String get() {
        return "OK";
    }

}
