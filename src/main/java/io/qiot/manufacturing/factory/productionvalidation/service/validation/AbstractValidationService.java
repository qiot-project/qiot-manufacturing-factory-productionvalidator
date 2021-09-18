package io.qiot.manufacturing.factory.productionvalidation.service.validation;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.slf4j.Logger;

import io.qiot.manufacturing.all.commons.domain.production.ProductionChainStageEnum;
import io.qiot.manufacturing.all.commons.domain.productline.ProductLineDTO;
import io.qiot.manufacturing.factory.commons.domain.productionvalidation.AbstractValidationRequestEventDTO;
import io.qiot.manufacturing.factory.productionvalidation.domain.event.ValidationCompletedEventDTO;
import io.qiot.manufacturing.factory.productionvalidation.service.productline.ProductLineService;

/**
 * @author andreabattaglia
 *
 */
public abstract class AbstractValidationService<E extends AbstractValidationRequestEventDTO> {

    @Inject
    Event<ValidationCompletedEventDTO> event;

    @Inject
    ProductLineService productLineService;

    protected void doValidate(E vrEvent) {

        getLogger().debug("Received validation request "
                + "for STAGE {} on ITEM {} / PRODUCTLINE {} from MACHINERY {}",
                vrEvent.stage, vrEvent.itemId, vrEvent.productLineId,
                vrEvent.machineryId);

        try {
            ValidationCompletedEventDTO vcEvent = new ValidationCompletedEventDTO();
            vcEvent.machineryId = vrEvent.machineryId;
            vcEvent.itemId = vrEvent.itemId;
            vcEvent.productLineId = vrEvent.productLineId;
            vcEvent.stage = vrEvent.stage;
            ProductLineDTO productLine = productLineService
                    .getProductLine(vcEvent.productLineId);
            vcEvent.valid = validateMetrics(vrEvent, productLine);

            /*
             * required in case we want to use JMS replyTo Header
             */
            // vcEvent.replyToQueueName = vrEvent.replyToQueueName;
            getLogger().debug(
                    "Validation {} for STAGE {} on ITEM {} / PRODUCTLINE {} from MACHINERY {}",
                    vcEvent.valid ? "success" : "failure", vrEvent.stage,
                    vrEvent.itemId, vrEvent.productLineId, vrEvent.machineryId);
            event.fire(vcEvent);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected abstract Logger getLogger();

    protected abstract Class<E> getEventClass();

    protected abstract ProductionChainStageEnum getStage();

    protected abstract boolean validateMetrics(E event,
            ProductLineDTO productLine);
}
