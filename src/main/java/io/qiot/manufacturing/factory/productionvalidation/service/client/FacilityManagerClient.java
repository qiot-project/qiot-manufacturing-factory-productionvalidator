package io.qiot.manufacturing.factory.productionvalidation.service.client;

import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 * @author andreabattaglia
 *
 */
@Path("/v1/factory")
@RegisterRestClient(configKey = "facility-manager-api")
public interface FacilityManagerClient {

    @GET
    @Path("/id")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public UUID getFactoryId();
}