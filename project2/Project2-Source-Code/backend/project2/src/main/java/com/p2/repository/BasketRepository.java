package com.p2.repository;

import java.util.List;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;

import com.p2.domain.BasketVO;

public interface BasketRepository extends JpaAttributeConverter<BasketVO, String> {

	List<BasketVO> getBasket();

	void addBasket(BasketVO[] basket);

}
