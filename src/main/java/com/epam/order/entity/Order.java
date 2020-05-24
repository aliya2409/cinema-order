package com.epam.order.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "booking")
@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long showId;
    private Status status = Status.CREATED;
    private int numberOfTickets;
    @Embedded
    private CardInfo cardInfo;
    private String email;
    private Source source;
    private Long cashBoxId;

    public void markAsFinished() {
        this.status = Status.FINISHED;
    }
}

