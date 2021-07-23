package io.qiot.manufacturing.factory.productionvalidation.domain.event;

import io.qiot.manufacturing.factory.productionvalidation.domain.production.SizeMetricsDTO;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class WeavingValidationRequestedEvent extends AbstractValidationRequestEvent {
    public SizeMetricsDTO data;
}
