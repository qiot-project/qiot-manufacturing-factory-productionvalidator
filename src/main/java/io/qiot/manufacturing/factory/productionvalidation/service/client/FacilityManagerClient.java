package io.qiot.manufacturing.factory.productionvalidation.service.client;

import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.plugins.providers.DocumentProvider;
import org.jboss.resteasy.plugins.providers.FormUrlEncodedProvider;
import org.jboss.resteasy.plugins.providers.IIOImageProvider;
import org.jboss.resteasy.plugins.providers.JaxrsFormProvider;
import org.jboss.resteasy.plugins.providers.SourceProvider;
import org.jboss.resteasy.plugins.providers.sse.SseEventProvider;

import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * @author andreabattaglia
 *
 */
@Path("/v1/factory")
@RegisterRestClient(configKey = "facility-manager-api")
@RegisterForReflection(targets = { IIOImageProvider.class,
        SseEventProvider.class, FormUrlEncodedProvider.class,
        SourceProvider.class, DocumentProvider.class, JaxrsFormProvider.class })
public interface FacilityManagerClient {

    @GET
    @Path("/id")
    @Produces(MediaType.APPLICATION_JSON)
    public UUID getFactoryId();
}