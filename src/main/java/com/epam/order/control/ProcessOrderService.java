package com.epam.order.control;

import com.epam.order.entity.Order;
import com.epam.order.entity.Source;
import com.epam.order.entity.command.PaymentRequestCommand;
import com.epam.order.entity.command.PaymentRequestCommandSender;
import com.epam.order.entity.event.OrderFinishedEvent;
import com.epam.order.entity.event.OrderFinishedEventSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcessOrderService {
    private final OrderRepository repository;
    private final PaymentRequestCommandSender paymentRequestCommandSender;
    private final OrderFinishedEventSender orderFinishedEventSender;

    public void process(Order order) {
        if (order.getSource() == Source.CASH_BOX) {
            order.markAsFinished();
            repository.save(order);
            orderFinishedEventSender.send(new OrderFinishedEvent(order));
        } else if (order.getSource() == Source.CUSTOMER) {
            repository.save(order);
            paymentRequestCommandSender.send(new PaymentRequestCommand(order));
        }
    }
}
