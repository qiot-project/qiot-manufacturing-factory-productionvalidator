/**
 * 
 */
package io.qiot.manufacturing.factory.productionvalidation.service.validation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import io.qiot.manufacturing.commons.domain.production.ProductionChainStageEnum;
import io.qiot.manufacturing.commons.domain.productionvalidation.WeavingValidationRequestEvent;

/**
 * @author andreabattaglia
 *
 */
@ApplicationScoped
public class WeavingValidationServiceImpl extends AbstractValidationService<WeavingValidationRequestEvent> {

    @Inject
    Logger LOGGER;

    void validate(@Observes WeavingValidationRequestEvent vrEvent) {
        doValidate(vrEvent);
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
    protected ProductionChainStageEnum getStage() {
        return ProductionChainStageEnum.WEAVING;
    }
    
    @Override
    protected boolean validateMetrics(WeavingValidationRequestEvent event) {
        return true;
    }
}
