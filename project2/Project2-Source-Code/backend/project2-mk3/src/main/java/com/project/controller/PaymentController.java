package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.Basket;
import com.project.domain.Payment;
import com.project.service.DataService;

@RestController
public class PaymentController {
	@Autowired
	private DataService dataService;

    @PostMapping("/data/createpayment")
    public Payment createPayment(@RequestBody Basket basket) {
        return dataService.createPayment(basket);
    }
}
