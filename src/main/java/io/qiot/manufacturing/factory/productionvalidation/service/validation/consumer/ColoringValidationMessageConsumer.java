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

import io.qiot.manufacturing.factory.productionvalidation.domain.ProductionChainStageEnum;
import io.qiot.manufacturing.factory.productionvalidation.domain.event.ColoringValidationRequestedEvent;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class ColoringValidationMessageConsumer
        extends AbstractValidationMessageConsumer<ColoringValidationRequestedEvent> {
    
    @Inject
    Logger LOGGER;

    @ConfigProperty(name = "qiot.production.chain.validation.coloring.queue")
    String validationQueueName;
    @Inject
    Event<ColoringValidationRequestedEvent> validationRequestedEvent;

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
    protected Class<ColoringValidationRequestedEvent> getEventClass() {
        return ColoringValidationRequestedEvent.class;
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