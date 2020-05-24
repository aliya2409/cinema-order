package com.epam.order.boundary;

import com.epam.order.control.ProcessPaymentResultService;
import com.epam.order.entity.command.PaymentResultCommand;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = "payment-result")
@RequiredArgsConstructor
public class PaymentResultCommandConsumer {
    private final ProcessPaymentResultService processPaymentResultService;
    private final ObjectMapper mapper;

    @RabbitHandler
    public void receive(String in) {
        try {
            val command = mapper.readValue(in, PaymentResultCommand.class);
            processPaymentResultService.process(command);
        } catch (JsonProcessingException e) {
            log.error("Unable to parse line: " + in);
        }
    }
}
