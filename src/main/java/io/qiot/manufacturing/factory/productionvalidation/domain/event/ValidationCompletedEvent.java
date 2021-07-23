package io.qiot.manufacturing.factory.productionvalidation.domain.event;

import java.util.UUID;

import io.qiot.manufacturing.factory.productionvalidation.domain.ProductionChainStageEnum;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class ValidationCompletedEvent {
    public UUID productLineId;
    public String machineryId;
    public int itemId;
    public ProductionChainStageEnum stage;
    public boolean valid;
    /*
     * required in case we want to use JMS replyTo Header
     */
//    public String replyToQueueName;
}
