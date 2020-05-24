package com.epam.order.entity.command;

import com.epam.order.entity.PaymentResult;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentResultCommand {
    private UUID commandId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;
    private Long orderId;
    private PaymentResult result;
}
