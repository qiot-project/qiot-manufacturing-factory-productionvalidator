package io.qiot.manufacturing.factory.productionvalidation.service.validation.consumer;

import java.util.Objects;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;

import org.slf4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.qiot.manufacturing.all.commons.domain.production.ProductionChainStageEnum;
import io.qiot.manufacturing.factory.commons.domain.productionvalidation.AbstractValidationRequestEventDTO;

/**
 * @author andreabattaglia
 *
 */
public abstract class AbstractValidationMessageConsumer<E extends AbstractValidationRequestEventDTO>
        implements Runnable {

    @Inject
    ObjectMapper MAPPER;

    @Inject
    ConnectionFactory connectionFactory;

    @Inject
    Event<E> validationRequestedEvent;

    private JMSContext context;

    private JMSConsumer consumer;

    private Queue queue;

    protected void doInit() {
        if (Objects.nonNull(context))
            context.close();
        context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE);

        queue = context.createQueue(getValidationQueueName());

        consumer = context.createConsumer(queue);
    }

    protected void doDestroy() {
        context.close();
    }

    @Override
    public void run() {
        while (true) {
            Message message = consumer.receive();
            try {
                String messagePayload = message.getBody(String.class);
                getLogger().debug(
                        "Received validation request for stage {}:\n\n{}",
                        getStage(), messagePayload);
                E messageDTO = MAPPER.readValue(messagePayload,
                        getEventClass());
                validationRequestedEvent.fire(messageDTO);
            } catch (JMSException e) {
                getLogger().error(
                        "The messaging client returned an error: {} and will be restarted.",
                        e);
                doInit();
            } catch (JsonProcessingException e) {
                getLogger().error(
                        "The message payload is malformed and the validation request will not be sent: {}",
                        e);
            }
        }
    }

    protected abstract Logger getLogger();

    protected abstract Class<E> getEventClass();

    protected abstract String getValidationQueueName();

    protected abstract ProductionChainStageEnum getStage();
}
