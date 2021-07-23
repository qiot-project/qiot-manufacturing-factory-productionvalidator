/**
 * 
 */
package io.qiot.manufacturing.factory.productionvalidation.service.validation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import io.qiot.manufacturing.commons.domain.production.ProductionChainStageEnum;
import io.qiot.manufacturing.commons.domain.productionvalidation.ColoringValidationRequestEvent;

/**
 * @author andreabattaglia
 *
 */
@ApplicationScoped
public class ColoringValidationServiceImpl extends AbstractValidationService<ColoringValidationRequestEvent> {

    @Inject
    Logger LOGGER;

    void validate(@Observes ColoringValidationRequestEvent vrEvent) {
        doValidate(vrEvent);
    }
    
    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
    
    @Override
    protected Class<ColoringValidationRequestEvent> getEventClass() {
        return ColoringValidationRequestEvent.class;
    }
    
    @Override
    protected ProductionChainStageEnum getStage() {
        return ProductionChainStageEnum.COLORING;
    }
    
    @Override
    protected boolean validateMetrics(ColoringValidationRequestEvent event) {
        return true;
    }
}
