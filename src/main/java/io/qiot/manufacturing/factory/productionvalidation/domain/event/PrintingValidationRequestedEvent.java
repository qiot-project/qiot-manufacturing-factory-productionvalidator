package io.qiot.manufacturing.factory.productionvalidation.domain.event;

import io.qiot.manufacturing.factory.productionvalidation.domain.production.PrintingMetricsDTO;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class PrintingValidationRequestedEvent extends AbstractValidationRequestEvent {
    public PrintingMetricsDTO data;
}
