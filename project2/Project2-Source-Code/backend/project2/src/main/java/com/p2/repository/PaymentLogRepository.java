package com.p2.repository;

import java.util.List;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;

import com.p2.domain.PaymentLogVO;

public interface PaymentLogRepository extends JpaAttributeConverter<PaymentLogVO, String> {

	List<PaymentLogVO> getPaymentLogList();

}
