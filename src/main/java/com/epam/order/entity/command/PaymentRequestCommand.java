package com.epam.order.entity.command;

import com.epam.order.entity.CardInfo;
import com.epam.order.entity.Order;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentRequestCommand {
    private final UUID commandId = UUID.randomUUID();
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime creationTime = LocalDateTime.now();
    private final Long orderId;
    private final Long showId;
    private final CardInfo cardInfo;
    private final int numberOfTickets;

    public PaymentRequestCommand(Order order) {
        this.orderId = order.getId();
        this.showId = order.getShowId();
        this.numberOfTickets = order.getNumberOfTickets();
        this.cardInfo = order.getCardInfo();
    }
}
