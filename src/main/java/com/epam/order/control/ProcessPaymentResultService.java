package com.epam.order.control;

import com.epam.order.entity.PaymentResult;
import com.epam.order.entity.command.PaymentResultCommand;
import com.epam.order.entity.event.OrderFinishedEvent;
import com.epam.order.entity.event.OrderFinishedEventSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessPaymentResultService {
    private final OrderFinishedEventSender sender;
    private final OrderRepository orderRepository;

    public void process(PaymentResultCommand command) {
        val order = orderRepository.findById(command.getOrderId())
                .orElseThrow(() -> new OrderNotFoundException("Could not find order with id: " + command.getOrderId()));
        if (command.getResult() == PaymentResult.SUCCESS) {
            log.debug("Processing successful payment result");
            order.markAsFinished();
            orderRepository.save(order);
            sender.send(new OrderFinishedEvent(order));
        }
    }
}
