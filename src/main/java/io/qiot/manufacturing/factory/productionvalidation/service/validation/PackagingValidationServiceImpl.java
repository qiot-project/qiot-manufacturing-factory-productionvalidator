/**
 * 
 */
package io.qiot.manufacturing.factory.productionvalidation.service.validation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import io.qiot.manufacturing.commons.domain.production.ProductionChainStageEnum;
import io.qiot.manufacturing.commons.domain.productionvalidation.PackagingValidationRequestEvent;

/**
 * @author andreabattaglia
 *
 */
@ApplicationScoped
public class PackagingValidationServiceImpl extends AbstractValidationService<PackagingValidationRequestEvent> {

    @Inject
    Logger LOGGER;

    void validate(@Observes PackagingValidationRequestEvent vrEvent) {
        doValidate(vrEvent);
    }
    
    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
    
    @Override
    protected Class<PackagingValidationRequestEvent> getEventClass() {
        return PackagingValidationRequestEvent.class;
    }
    
    @Override
    protected ProductionChainStageEnum getStage() {
        return ProductionChainStageEnum.PACKAGING;
    }
    
    @Override
    protected boolean validateMetrics(PackagingValidationRequestEvent event) {
        return true;
    }
}
