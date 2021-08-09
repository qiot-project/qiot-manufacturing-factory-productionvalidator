/**
 * 
 */
package io.qiot.manufacturing.factory.productionvalidation.service.validation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import io.qiot.manufacturing.commons.domain.production.ProductionChainStageEnum;
import io.qiot.manufacturing.commons.domain.productionvalidation.PackagingValidationRequestEventDTO;

/**
 * @author andreabattaglia
 *
 */
@ApplicationScoped
public class PackagingValidationServiceImpl extends AbstractValidationService<PackagingValidationRequestEventDTO> {

    @Inject
    Logger LOGGER;

    void validate(@Observes PackagingValidationRequestEventDTO vrEvent) {
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
    protected boolean validateMetrics(PackagingValidationRequestEventDTO event) {
        return true;
    }
}
