package io.qiot.manufacturing.factory.productionvalidation.service.validation.consumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;

import io.qiot.manufacturing.commons.domain.production.ProductionChainStageEnum;
import io.qiot.manufacturing.commons.domain.productionvalidation.WeavingValidationRequestEvent;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class WeavingValidationMessageConsumer extends
        AbstractValidationMessageConsumer<WeavingValidationRequestEvent> {

    @Inject
    Logger LOGGER;

    @ConfigProperty(name = "qiot.production.chain.validation.weaving.queue")
    String validationQueueName;
    @Inject
    Event<WeavingValidationRequestEvent> validationRequestedEvent;

    private final ExecutorService scheduler = Executors
            .newSingleThreadExecutor();

    void startup(@Observes StartupEvent ev) {
        scheduler.submit(this);
    }

    @PostConstruct
    void init() {
        doInit();
    }

    @PreDestroy
    void destroy() {
        doDestroy();
    }

    void shutdown(@Observes ShutdownEvent ev) {

        scheduler.shutdown();
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

    @Override
    protected Class<WeavingValidationRequestEvent> getEventClass() {
        return WeavingValidationRequestEvent.class;
    }

    @Override
    protected String getValidationQueueName() {
        return validationQueueName;
    }
    
    @Override
    protected ProductionChainStageEnum getStage() {
        return ProductionChainStageEnum.WEAVING;
    }
}