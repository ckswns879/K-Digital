package com.p2.repository;

import java.util.List;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;

import com.p2.domain.LeadtimeVO;

public interface LeadtimeRepository extends JpaAttributeConverter<LeadtimeVO, String> {

	List<LeadtimeVO> getSearch();

	List<LeadtimeVO> getLeadtime(LeadtimeVO leadtime);

}
