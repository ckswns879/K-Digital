package com.p2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import com.p2.domain.PaymentLogVO;
import com.p2.service.PaymentLogService;

@RestController
public class PaymentLogController {
	
	@Autowired
	private PaymentLogService paymentlogService;
	
	@GetMapping("/data/loglist")
	public List<PaymentLogVO> getPaymentLogList(){
		return paymentlogService.getPaymentLogList();
	}
	
	

}
