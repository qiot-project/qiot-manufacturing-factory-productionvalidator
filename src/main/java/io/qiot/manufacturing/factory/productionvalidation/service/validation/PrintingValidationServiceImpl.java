/**
 * 
 */
package io.qiot.manufacturing.factory.productionvalidation.service.validation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import io.qiot.manufacturing.commons.domain.production.ProductionChainStageEnum;
import io.qiot.manufacturing.commons.domain.productionvalidation.PrintingValidationRequestEventDTO;

/**
 * @author andreabattaglia
 *
 */
@ApplicationScoped
public class PrintingValidationServiceImpl extends AbstractValidationService<PrintingValidationRequestEventDTO> {

    @Inject
    Logger LOGGER;

    void validate(@Observes PrintingValidationRequestEventDTO vrEvent) {
        doValidate(vrEvent);
    }
    
    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
    
    @Override
    protected Class<PrintingValidationRequestEventDTO> getEventClass() {
        return PrintingValidationRequestEventDTO.class;
    }
    
    @Override
    protected ProductionChainStageEnum getStage() {
        return ProductionChainStageEnum.PRINTING;
    }
    
    @Override
    protected boolean validateMetrics(PrintingValidationRequestEventDTO event) {
        return true;
    }
}
