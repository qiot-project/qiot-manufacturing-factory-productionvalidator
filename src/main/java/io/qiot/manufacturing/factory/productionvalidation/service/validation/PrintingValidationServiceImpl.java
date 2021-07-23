/**
 * 
 */
package io.qiot.manufacturing.factory.productionvalidation.service.validation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import io.qiot.manufacturing.factory.productionvalidation.domain.ProductionChainStageEnum;
import io.qiot.manufacturing.factory.productionvalidation.domain.event.PrintingValidationRequestedEvent;

/**
 * @author andreabattaglia
 *
 */
@ApplicationScoped
public class PrintingValidationServiceImpl extends AbstractValidationService<PrintingValidationRequestedEvent> {

    @Inject
    Logger LOGGER;

    void validate(@Observes PrintingValidationRequestedEvent vrEvent) {
        doValidate(vrEvent);
    }
    
    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
    
    @Override
    protected Class<PrintingValidationRequestedEvent> getEventClass() {
        return PrintingValidationRequestedEvent.class;
    }
    
    @Override
    protected ProductionChainStageEnum getStage() {
        return ProductionChainStageEnum.PRINTING;
    }
    
    @Override
    protected boolean validateMetrics(PrintingValidationRequestedEvent event) {
        return true;
    }
}
