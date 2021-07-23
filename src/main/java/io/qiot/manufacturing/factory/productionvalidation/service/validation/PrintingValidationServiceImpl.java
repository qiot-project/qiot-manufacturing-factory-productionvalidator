/**
 * 
 */
package io.qiot.manufacturing.factory.productionvalidation.service.validation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import io.qiot.manufacturing.commons.domain.production.ProductionChainStageEnum;
import io.qiot.manufacturing.commons.domain.productionvalidation.PrintingValidationRequestEvent;

/**
 * @author andreabattaglia
 *
 */
@ApplicationScoped
public class PrintingValidationServiceImpl extends AbstractValidationService<PrintingValidationRequestEvent> {

    @Inject
    Logger LOGGER;

    void validate(@Observes PrintingValidationRequestEvent vrEvent) {
        doValidate(vrEvent);
    }
    
    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
    
    @Override
    protected Class<PrintingValidationRequestEvent> getEventClass() {
        return PrintingValidationRequestEvent.class;
    }
    
    @Override
    protected ProductionChainStageEnum getStage() {
        return ProductionChainStageEnum.PRINTING;
    }
    
    @Override
    protected boolean validateMetrics(PrintingValidationRequestEvent event) {
        return true;
    }
}
