package com.p2.repository;

import java.util.List;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;

import com.p2.domain.ItemInfoVO;

public interface ItemRepository extends JpaAttributeConverter<ItemInfoVO, String> {

	List<ItemInfoVO> getItemList();

}
