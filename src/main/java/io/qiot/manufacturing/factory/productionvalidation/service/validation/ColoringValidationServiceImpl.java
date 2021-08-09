/**
 * 
 */
package io.qiot.manufacturing.factory.productionvalidation.service.validation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import io.qiot.manufacturing.commons.domain.production.ProductionChainStageEnum;
import io.qiot.manufacturing.commons.domain.productionvalidation.ColoringValidationRequestEventDTO;

/**
 * @author andreabattaglia
 *
 */
@ApplicationScoped
public class ColoringValidationServiceImpl extends AbstractValidationService<ColoringValidationRequestEventDTO> {

    @Inject
    Logger LOGGER;

    void validate(@Observes ColoringValidationRequestEventDTO vrEvent) {
        doValidate(vrEvent);
    }
    
    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
    
    @Override
    protected Class<ColoringValidationRequestEventDTO> getEventClass() {
        return ColoringValidationRequestEventDTO.class;
    }
    
    @Override
    protected ProductionChainStageEnum getStage() {
        return ProductionChainStageEnum.COLORING;
    }
    
    @Override
    protected boolean validateMetrics(ColoringValidationRequestEventDTO event) {
        return true;
    }
}
