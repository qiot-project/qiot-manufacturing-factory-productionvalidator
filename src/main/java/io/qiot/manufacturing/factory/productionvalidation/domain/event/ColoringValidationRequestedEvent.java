package io.qiot.manufacturing.factory.productionvalidation.domain.event;

import io.qiot.manufacturing.factory.productionvalidation.domain.production.ColorMetricsDTO;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class ColoringValidationRequestedEvent extends AbstractValidationRequestEvent {
    public ColorMetricsDTO data;
}
