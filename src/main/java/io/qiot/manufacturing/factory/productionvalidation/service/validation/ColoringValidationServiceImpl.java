/**
 * 
 */
package io.qiot.manufacturing.factory.productionvalidation.service.validation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import io.qiot.manufacturing.all.commons.domain.production.ProductionChainStageEnum;
import io.qiot.manufacturing.all.commons.domain.productline.ProductLineDTO;
import io.qiot.manufacturing.factory.commons.domain.productionvalidation.ColoringValidationRequestEventDTO;

/**
 * @author andreabattaglia
 *
 */
@ApplicationScoped
public class ColoringValidationServiceImpl
        extends AbstractValidationService<ColoringValidationRequestEventDTO> {

    @Inject
    Logger LOGGER;

    void onValidationRequest(
            @Observes ColoringValidationRequestEventDTO vrEvent) {
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
    protected boolean validateMetrics(ColoringValidationRequestEventDTO event,
            ProductLineDTO productLine) {
        if (event.data.red < productLine.color.redMin
                || event.data.red > productLine.color.redMax)
            return false;
        if (event.data.green < productLine.color.greenMin
                || event.data.green > productLine.color.greenMax)
            return false;
        if (event.data.blue < productLine.color.blueMin
                || event.data.blue > productLine.color.blueMax)
            return false;
        return true;
    }
}
