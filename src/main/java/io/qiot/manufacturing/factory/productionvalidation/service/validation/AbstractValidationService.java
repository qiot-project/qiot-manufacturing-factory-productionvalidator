package io.qiot.manufacturing.factory.productionvalidation.service.validation;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.slf4j.Logger;

import io.qiot.manufacturing.factory.productionvalidation.domain.ProductionChainStageEnum;
import io.qiot.manufacturing.factory.productionvalidation.domain.event.AbstractValidationRequestEvent;
import io.qiot.manufacturing.factory.productionvalidation.domain.event.ValidationCompletedEvent;

public abstract class AbstractValidationService<E extends AbstractValidationRequestEvent> {

    @Inject
    Event<ValidationCompletedEvent> event;

    protected void doValidate(E vrEvent) {

        getLogger().info("Received validation request "
                + "for STAGE {} on ITEM {} / PRODUCTLINE {} from MACHINERY {}",
                vrEvent.stage, vrEvent.itemId, vrEvent.productLineId,
                vrEvent.machineryId);
        
        ValidationCompletedEvent vcEvent = new ValidationCompletedEvent();
        vcEvent.machineryId = vrEvent.machineryId;
        vcEvent.itemId = vrEvent.itemId;
        vcEvent.productLineId = vrEvent.productLineId;
        vcEvent.stage = vrEvent.stage;
        vcEvent.valid = validateMetrics(vrEvent);

        /*
         * required in case we want to use JMS replyTo Header
         */
        // vcEvent.replyToQueueName = vrEvent.replyToQueueName;
        getLogger().info(
                "Validation {} for STAGE {} on ITEM {} / PRODUCTLINE {} from MACHINERY {}",
                vcEvent.valid ? "success" : "failure", vrEvent.stage,
                vrEvent.itemId, vrEvent.productLineId, vrEvent.machineryId);
        event.fire(vcEvent);
    }

    protected abstract Logger getLogger();

    protected abstract Class<E> getEventClass();

    protected abstract ProductionChainStageEnum getStage();

    protected abstract boolean validateMetrics(E event);
}
