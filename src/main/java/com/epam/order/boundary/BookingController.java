package com.epam.order.boundary;

import com.epam.order.control.ProcessOrderService;
import com.epam.order.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class BookingController {
    private final ProcessOrderService processOrderService;

    @PostMapping("/book")
    public ResponseEntity createOrder(@RequestBody Order order) {
        processOrderService.process(order);
        return new ResponseEntity(HttpStatus.OK);
    }
}
