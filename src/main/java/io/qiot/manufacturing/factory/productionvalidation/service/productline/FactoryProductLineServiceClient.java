package io.qiot.manufacturing.factory.productionvalidation.service.productline;

import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.qiot.manufacturing.commons.domain.productline.ProductLineDTO;

/**
 * @author andreabattaglia
 *
 */
@Path("/v1/productline/service")
@RegisterRestClient(configKey = "factoryproductline-api")
public interface FactoryProductLineServiceClient {
    //
    // @GET
    // @Path("/")
    // public List<ProductLineDTO> getAllProductLines();

    @GET
    @Path("/id/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public ProductLineDTO getProductLineById(@PathParam("id") UUID id);

    // @GET
    // @Path("/last")
    // public ProductLineDTO getLastProductLine();
    //
    // @GET
    // @Path("/active")
    // public List<ProductLineDTO> getActiveProductLines();
}
