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
import io.qiot.manufacturing.factory.commons.domain.productionvalidation.WeavingValidationRequestEventDTO;

/**
 * @author andreabattaglia
 *
 */
@ApplicationScoped
public class WeavingValidationServiceImpl
        extends AbstractValidationService<WeavingValidationRequestEventDTO> {

    @Inject
    Logger LOGGER;

    void onValidationRequest(
            @Observes WeavingValidationRequestEventDTO vrEvent) {
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
    protected boolean validateMetrics(WeavingValidationRequestEventDTO event,
            ProductLineDTO productLine) {
        LOGGER.debug(
                "Validating WEAVING production metrics versus ranges:"
                        + "\nMETRICS:\n{}\nRANGES:\n{}",
                event.data, productLine.sizeChart);
        try {
            if (event.data.chest < productLine.sizeChart.chestMin
                    || event.data.chest > productLine.sizeChart.chestMax)
                return false;
            if (event.data.shoulder < productLine.sizeChart.shoulderMin
                    || event.data.shoulder > productLine.sizeChart.shoulderMax)
                return false;
            if (event.data.back < productLine.sizeChart.backMin
                    || event.data.back > productLine.sizeChart.backMax)
                return false;
            if (event.data.waist < productLine.sizeChart.waistMin
                    || event.data.waist > productLine.sizeChart.waistMax)
                return false;
            if (event.data.hip < productLine.sizeChart.hipMin
                    || event.data.hip > productLine.sizeChart.hipMax)
                return false;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }
}
