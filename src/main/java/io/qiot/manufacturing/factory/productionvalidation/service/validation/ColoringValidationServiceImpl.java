/**
 * 
 */
package io.qiot.manufacturing.factory.productionvalidation.service.validation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import io.qiot.manufacturing.factory.productionvalidation.domain.ProductionChainStageEnum;
import io.qiot.manufacturing.factory.productionvalidation.domain.event.ColoringValidationRequestedEvent;

/**
 * @author andreabattaglia
 *
 */
@ApplicationScoped
public class ColoringValidationServiceImpl extends AbstractValidationService<ColoringValidationRequestedEvent> {

    @Inject
    Logger LOGGER;

    void validate(@Observes ColoringValidationRequestedEvent vrEvent) {
        doValidate(vrEvent);
    }
    
    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
    
    @Override
    protected Class<ColoringValidationRequestedEvent> getEventClass() {
        return ColoringValidationRequestedEvent.class;
    }
    
    @Override
    protected ProductionChainStageEnum getStage() {
        return ProductionChainStageEnum.COLORING;
    }
    
    @Override
    protected boolean validateMetrics(ColoringValidationRequestedEvent event) {
        return true;
    }
}
