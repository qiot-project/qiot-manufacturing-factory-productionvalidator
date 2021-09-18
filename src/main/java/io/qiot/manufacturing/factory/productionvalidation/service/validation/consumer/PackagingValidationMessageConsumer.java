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

import io.qiot.manufacturing.all.commons.domain.production.ProductionChainStageEnum;
import io.qiot.manufacturing.factory.commons.domain.productionvalidation.PackagingValidationRequestEventDTO;
import io.quarkus.runtime.StartupEvent;

/**
 * @author andreabattaglia
 *
 */
@ApplicationScoped
public class PackagingValidationMessageConsumer extends
        AbstractValidationMessageConsumer<PackagingValidationRequestEventDTO> {

    @Inject
    Logger LOGGER;

    @ConfigProperty(name = "qiot.production.chain.validation.packaging.queue")
    String validationQueueName;
    @Inject
    Event<PackagingValidationRequestEventDTO> validationRequestedEvent;

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
    protected Class<PackagingValidationRequestEventDTO> getEventClass() {
        return PackagingValidationRequestEventDTO.class;
    }

    @Override
    protected String getValidationQueueName() {
        return validationQueueName;
    }

    @Override
    protected ProductionChainStageEnum getStage() {
        return ProductionChainStageEnum.PACKAGING;
    }
}