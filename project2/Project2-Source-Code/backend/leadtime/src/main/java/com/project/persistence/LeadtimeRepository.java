package com.project.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.leadtime;

public interface LeadtimeRepository extends JpaRepository<leadtime, Long> {
	
	List<leadtime> findByMachineryContaining(String machinery);
	
	List<leadtime> findByItemsContaining(String items);
	
	List<leadtime> findByPart1Containing(String part1);
	
	List<leadtime> findByBaljucheoContaining(String baljucheo);

}
