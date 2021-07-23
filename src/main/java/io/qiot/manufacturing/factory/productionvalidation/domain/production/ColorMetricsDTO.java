package io.qiot.manufacturing.factory.productionvalidation.domain.production;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class ColorMetricsDTO {
    public int red;
    public int green;
    public int blue;
}
