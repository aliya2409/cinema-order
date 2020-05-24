package com.epam.order.entity.command;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    @Bean
    Queue paymentRequestQueue() {
        return new Queue("payment-request", false);
    }

    @Bean
    Queue paymentResultQueue() {
        return new Queue("payment-result", false);
    }

    @Bean
    public FanoutExchange orderFinishedFanout() {
        return new FanoutExchange("order-finished");
    }

    @Bean
    public Queue notificationQueue() {
        return new Queue("order-finished-notification", false);
    }

    @Bean
    public Queue scheduleQueue() {
        return new Queue("order-finished-schedule", false);
    }

    @Bean
    public Binding notificationBinding(FanoutExchange fanout,
                            Queue notificationQueue) {
        return BindingBuilder.bind(notificationQueue).to(fanout);
    }

    @Bean
    public Binding ScheduleBinding(FanoutExchange fanout,
                            Queue scheduleQueue) {
        return BindingBuilder.bind(scheduleQueue).to(fanout);
    }
}
