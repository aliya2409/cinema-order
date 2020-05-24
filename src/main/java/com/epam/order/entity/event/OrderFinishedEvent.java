package com.epam.order.entity.event;

import com.epam.order.entity.Order;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderFinishedEvent {
    private final UUID commandId = UUID.randomUUID();
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime creationTime = LocalDateTime.now();
    private final Long orderId;
    private final Long showId;
    private final int numberOfTickets;
    private final String email;

    public OrderFinishedEvent(Order order) {
        this.orderId = order.getId();
        this.showId = order.getShowId();
        this.numberOfTickets = order.getNumberOfTickets();
        this.email = order.getEmail();
    }
}
