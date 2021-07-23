/**
 * 
 */
package io.qiot.manufacturing.factory.productionvalidation.service.validation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import io.qiot.manufacturing.factory.productionvalidation.domain.ProductionChainStageEnum;
import io.qiot.manufacturing.factory.productionvalidation.domain.event.WeavingValidationRequestedEvent;

/**
 * @author andreabattaglia
 *
 */
@ApplicationScoped
public class WeavingValidationServiceImpl extends AbstractValidationService<WeavingValidationRequestedEvent> {

    @Inject
    Logger LOGGER;

    void validate(@Observes WeavingValidationRequestedEvent vrEvent) {
        doValidate(vrEvent);
    }
    
    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
    
    @Override
    protected Class<WeavingValidationRequestedEvent> getEventClass() {
        return WeavingValidationRequestedEvent.class;
    }
    
    @Override
    protected ProductionChainStageEnum getStage() {
        return ProductionChainStageEnum.WEAVING;
    }
    
    @Override
    protected boolean validateMetrics(WeavingValidationRequestedEvent event) {
        return true;
    }
}
