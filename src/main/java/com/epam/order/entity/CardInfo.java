package com.epam.order.entity;

import com.epam.order.control.YearMonthDateAttributeConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import java.time.YearMonth;

@Getter
@NoArgsConstructor
@Embeddable
public class CardInfo {
    private Long number;
    @Column(
            name = "expires",
            columnDefinition = "date"
    )
    @Convert(
            converter = YearMonthDateAttributeConverter.class
    )
    private YearMonth expires;
    private String cvv;
}
