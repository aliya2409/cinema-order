package com.epam.order.entity.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderFinishedEventSender {

    private final RabbitTemplate template;
    private final FanoutExchange fanout;
    private final ObjectMapper mapper;

    public void send(OrderFinishedEvent event) {
        try {
            val message = mapper.writeValueAsString(event);
            this.template.convertAndSend(fanout.getName(), "", message);
            log.debug(" [x] Sent '" + message + "'");
        }
        catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }
}
