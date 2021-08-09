package io.qiot.manufacturing.factory.productionvalidation.service.validation.consumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;

import io.qiot.manufacturing.commons.domain.production.ProductionChainStageEnum;
import io.qiot.manufacturing.commons.domain.productionvalidation.ColoringValidationRequestEventDTO;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class ColoringValidationMessageConsumer
        extends AbstractValidationMessageConsumer<ColoringValidationRequestEventDTO> {
    
    @Inject
    Logger LOGGER;

    @ConfigProperty(name = "qiot.production.chain.validation.coloring.queue")
    String validationQueueName;
    @Inject
    Event<ColoringValidationRequestEventDTO> validationRequestedEvent;

    private final ExecutorService scheduler = Executors
            .newSingleThreadExecutor();

    void init(@Observes StartupEvent ev) {
        doInit();
        scheduler.submit(this);
    }

    @PreDestroy
    void destroy() {
        scheduler.shutdown();
        doDestroy();
    }
    
    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

    
    @Override
    protected Class<ColoringValidationRequestEventDTO> getEventClass() {
        return ColoringValidationRequestEventDTO.class;
    }
    
    @Override
    protected String getValidationQueueName() {
        return validationQueueName;
    }
    
    @Override
    protected ProductionChainStageEnum getStage() {
        return ProductionChainStageEnum.COLORING;
    }
}