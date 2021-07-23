package io.qiot.manufacturing.factory.productionvalidation.domain.event;

import java.util.UUID;

import io.qiot.manufacturing.factory.productionvalidation.domain.ProductionChainStageEnum;

public abstract class AbstractValidationRequestEvent {
    public UUID productLineId;
    public int itemId;
    public ProductionChainStageEnum stage;
    public String machineryId;
    /*
     * required in case we want to use JMS replyTo Header
     */
//    public String replyToQueueName;

}
