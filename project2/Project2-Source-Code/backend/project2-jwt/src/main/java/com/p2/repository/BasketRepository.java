package com.p2.repository;

import java.util.List;
import java.util.Optional;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;

import com.p2.domain.BasketVO;
import com.p2.domain.PaymentLogVO;

public interface BasketRepository extends JpaAttributeConverter<BasketVO, Long> {

	List<BasketVO> getBasket();

	void addBasket(BasketVO[] basket);

	Optional<PaymentLogVO> findById(long id);


}
