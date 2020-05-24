package com.epam.order.entity.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentRequestCommandSender {
    private final RabbitTemplate template;
    private final Queue paymentRequestQueue;
    private final ObjectMapper objectMapper;

    public void send(PaymentRequestCommand command) {
        try {
            val message = objectMapper.writeValueAsString(command);
            this.template.convertAndSend(paymentRequestQueue.getName(), message);
            log.debug(" [x] Sent '" + message + "'");
        }
        catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }
}
