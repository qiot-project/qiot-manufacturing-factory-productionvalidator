/**
 * 
 */
package io.qiot.manufacturing.factory.productionvalidation.service.validation.producer;

import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.qiot.manufacturing.factory.productionvalidation.domain.ValidationResponseDTO;
import io.qiot.manufacturing.factory.productionvalidation.domain.event.ValidationCompletedEvent;
import io.qiot.manufacturing.factory.productionvalidation.util.producer.ReplyToQueueNameProducer;
import io.quarkus.runtime.StartupEvent;

/**
 * @author andreabattaglia
 *
 */
@ApplicationScoped
public class ValidationMessageProducer {

    @Inject
    Logger LOGGER;

    @Inject
    ConnectionFactory connectionFactory;

    @Inject
    ObjectMapper MAPPER;

    @Inject
    ReplyToQueueNameProducer replyToQueueNameProducer;

    private JMSContext context;

    private JMSProducer producer;

    // @PostConstruct
    // void init() {
    void init(@Observes StartupEvent ev) {
        LOGGER.info("Bootstrapping validation event producer...");
        if (Objects.nonNull(context))
            context.close();
        context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE);

        producer = context.createProducer();
        LOGGER.info("Bootstrap completed");

    }

    void produce(@Observes ValidationCompletedEvent event) {
        LOGGER.info("Sending out validation results "
                + "for STAGE {} on ITEM {} / PRODUCTLINE {} from MACHINERY {}",
                event.stage, event.itemId, event.productLineId,
                event.machineryId);
        Queue replyToQueue = context.createQueue(replyToQueueNameProducer
                .getReplyToQueueName(event.machineryId));
        ValidationResponseDTO messagePayloadDTO = new ValidationResponseDTO();
        messagePayloadDTO.productLineId = event.productLineId;
        messagePayloadDTO.itemId = event.itemId;
        messagePayloadDTO.stage = event.stage;
        messagePayloadDTO.valid = event.valid;

        try {
            String messagePayload = MAPPER
                    .writeValueAsString(messagePayloadDTO);

            producer.send(replyToQueue, messagePayload);
        } catch (JsonProcessingException e) {
            LOGGER.error(
                    "The message payload is malformed and the validation request will not be sent: {}",
                    e);
        }

    }
}
