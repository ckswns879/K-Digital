package com.p2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.p2.domain.BasketVO;
import com.p2.domain.PaymentLogVO;
import com.p2.repository.BasketRepository;
import com.p2.repository.PaymentLogRepository;

@Service
public class PaymentLogService {

	@Autowired
	private PaymentLogRepository paymentlogrepository;
	private BasketRepository basketrepository;

	public List<PaymentLogVO> getPaymentLogList() {
		return paymentlogrepository.getPaymentLogList();
	}

//	public void updatePayment(BasketVO basket) {
//		PaymentLogVO payment = basketrepository.findById(basket.getId()).get();
//		payment.setOrder_amount(basket.getBilling_amount());
//		paymentlogrepository.save(payment);
//	}

	public void updatePayment(BasketVO basket) {
		Optional<PaymentLogVO> optionalPayment = basketrepository.findById(basket.getId());
		if (optionalPayment.isPresent()) {
			PaymentLogVO payment = optionalPayment.get();
			payment.setOrder_amount(basket.getBilling_amount());
			paymentlogrepository.save(payment);
		}

	}
}
