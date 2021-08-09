/**
 * 
 */
package io.qiot.manufacturing.factory.productionvalidation.service.validation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import io.qiot.manufacturing.commons.domain.production.ProductionChainStageEnum;
import io.qiot.manufacturing.commons.domain.productionvalidation.WeavingValidationRequestEventDTO;

/**
 * @author andreabattaglia
 *
 */
@ApplicationScoped
public class WeavingValidationServiceImpl extends AbstractValidationService<WeavingValidationRequestEventDTO> {

    @Inject
    Logger LOGGER;

    void validate(@Observes WeavingValidationRequestEventDTO vrEvent) {
        doValidate(vrEvent);
    }
    
    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
    
    @Override
    protected Class<WeavingValidationRequestEventDTO> getEventClass() {
        return WeavingValidationRequestEventDTO.class;
    }
    
    @Override
    protected ProductionChainStageEnum getStage() {
        return ProductionChainStageEnum.WEAVING;
    }
    
    @Override
    protected boolean validateMetrics(WeavingValidationRequestEventDTO event) {
        return true;
    }
}
