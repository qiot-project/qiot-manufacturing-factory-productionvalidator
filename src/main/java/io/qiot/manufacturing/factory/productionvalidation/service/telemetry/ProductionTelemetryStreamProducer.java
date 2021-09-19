package io.qiot.manufacturing.factory.productionvalidation.service.telemetry;

import java.time.Instant;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.qiot.manufacturing.datacenter.commons.domain.telemetry.production.StageProductionValidationTelemetryDTO;
import io.qiot.manufacturing.datacenter.commons.exception.telemetry.TelemetryDataValidationException;
import io.qiot.manufacturing.datacenter.commons.exception.telemetry.TelemetryTransformationException;
import io.qiot.manufacturing.datacenter.commons.service.telemetry.AbstractTelemetryStreamProducer;
import io.qiot.manufacturing.factory.productionvalidation.domain.event.ValidationCompletedEventDTO;
import io.qiot.manufacturing.factory.productionvalidation.service.client.FacilityManagerClient;

/**
 * @author andreabattaglia
 *
 */
@ApplicationScoped
public class ProductionTelemetryStreamProducer extends
        AbstractTelemetryStreamProducer<StageProductionValidationTelemetryDTO> {

    @Inject
    Logger LOGGER;

    @Inject
    ObjectMapper MAPPER;

    @Inject
    @RestClient
    FacilityManagerClient facilityManagerClient;

    @Inject
    @Channel("telemetryproduction")
    Emitter<String> telemetryEmitter;

    private UUID factoryId;

    @Inject
    Event<StageProductionValidationTelemetryDTO> measurementReceivedEvent;

    // @Incoming("pollution")
    // public void process(String data) throws TelemetryDataValidationException
    // {
    // LOGGER.info("Consumed message {} from the POLLUTION Stream", data);
    // // PollutionTelemetry gm = converter.jsonToMeasurement(data);
    // PollutionTelemetry pm;
    // try {
    // pm = MAPPER.readValue(data, PollutionTelemetry.class);
    // } catch (Exception e) {
    // throw new TelemetryDataValidationException(e);
    // }
    // measurementReceivedEvent.fire(pm);
    // }

    @PostConstruct
    void init() {
        if (factoryId == null)
            factoryId = facilityManagerClient.getFactoryId();
    }

    public void process(@Observes ValidationCompletedEventDTO event)
            throws TelemetryDataValidationException,
            TelemetryTransformationException {
        LOGGER.debug(
                "Received production stage validation. Streaming to the datacenter. {}",
                event);
        if (factoryId == null)
            factoryId = facilityManagerClient.getFactoryId();
        StageProductionValidationTelemetryDTO telemetry = new StageProductionValidationTelemetryDTO();
        telemetry.factoryId = factoryId;
        telemetry.machineryId = UUID.fromString(event.machineryId);
        telemetry.productLineId = event.productLineId;
        telemetry.itemId = event.itemId;
        telemetry.stage = event.stage;
        telemetry.success = event.valid;
        telemetry.time = Instant.now();

        LOGGER.info("Sending outcome telemetry to the stream service: {}",
                telemetry);

        telemetryEmitter.send(serialize(telemetry));
    }
}
