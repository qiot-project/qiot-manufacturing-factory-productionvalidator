package io.qiot.manufacturing.factory.productionvalidation.domain.event;

import io.qiot.manufacturing.factory.productionvalidation.domain.production.PackagingMetricsDTO;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class PackagingValidationRequestedEvent extends AbstractValidationRequestEvent {
    public PackagingMetricsDTO data;
}
