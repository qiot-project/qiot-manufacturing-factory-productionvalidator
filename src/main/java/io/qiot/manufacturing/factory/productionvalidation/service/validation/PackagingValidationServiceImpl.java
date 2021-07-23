/**
 * 
 */
package io.qiot.manufacturing.factory.productionvalidation.service.validation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import io.qiot.manufacturing.factory.productionvalidation.domain.ProductionChainStageEnum;
import io.qiot.manufacturing.factory.productionvalidation.domain.event.PackagingValidationRequestedEvent;

/**
 * @author andreabattaglia
 *
 */
@ApplicationScoped
public class PackagingValidationServiceImpl extends AbstractValidationService<PackagingValidationRequestedEvent> {

    @Inject
    Logger LOGGER;

    void validate(@Observes PackagingValidationRequestedEvent vrEvent) {
        doValidate(vrEvent);
    }
    
    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
    
    @Override
    protected Class<PackagingValidationRequestedEvent> getEventClass() {
        return PackagingValidationRequestedEvent.class;
    }
    
    @Override
    protected ProductionChainStageEnum getStage() {
        return ProductionChainStageEnum.PACKAGING;
    }
    
    @Override
    protected boolean validateMetrics(PackagingValidationRequestedEvent event) {
        return true;
    }
}
