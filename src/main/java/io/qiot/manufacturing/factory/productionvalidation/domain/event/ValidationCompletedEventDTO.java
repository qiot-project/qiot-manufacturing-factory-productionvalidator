package io.qiot.manufacturing.factory.productionvalidation.domain.event;

import java.util.UUID;

import io.qiot.manufacturing.all.commons.domain.production.ProductionChainStageEnum;
import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * @author andreabattaglia
 *
 */
@RegisterForReflection
public class ValidationCompletedEventDTO {
    public UUID productLineId;
    public String machineryId;
    public int itemId;
    public ProductionChainStageEnum stage;
    public boolean valid;
    /*
     * required in case we want to use JMS replyTo Header
     */
    // public String replyToQueueName;
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ValidationCompletedEventDTO [productLineId=");
        builder.append(productLineId);
        builder.append(", machineryId=");
        builder.append(machineryId);
        builder.append(", itemId=");
        builder.append(itemId);
        builder.append(", stage=");
        builder.append(stage);
        builder.append(", valid=");
        builder.append(valid);
        builder.append("]");
        return builder.toString();
    }
    
}
