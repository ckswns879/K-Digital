package com.p2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.p2.domain.PaymentLogVO;
import com.p2.repository.PaymentLogRepository;

@Service
public class PaymentLogService {
	
	@Autowired
	private PaymentLogRepository paymentlogrepository;
	
	public List<PaymentLogVO> getPaymentLogList(){
		return paymentlogrepository.getPaymentLogList();
	}
	

}
