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
import io.qiot.manufacturing.commons.domain.productionvalidation.PackagingValidationRequestEventDTO;

/**
 * @author andreabattaglia
 *
 */
@ApplicationScoped
public class PackagingValidationServiceImpl
        extends AbstractValidationService<PackagingValidationRequestEventDTO> {

    @Inject
    Logger LOGGER;

    void onValidationRequest(
            @Observes PackagingValidationRequestEventDTO vrEvent) {
        doValidate(vrEvent);
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

    @Override
    protected Class<PackagingValidationRequestEventDTO> getEventClass() {
        return PackagingValidationRequestEventDTO.class;
    }

    @Override
    protected ProductionChainStageEnum getStage() {
        return ProductionChainStageEnum.PACKAGING;
    }

    @Override
    protected boolean validateMetrics(PackagingValidationRequestEventDTO event,
            ProductLineDTO productLine) {
        if (event.data.packaging < productLine.packaging.min
                || event.data.packaging > productLine.packaging.max)
            return false;
        return true;
    }
}
