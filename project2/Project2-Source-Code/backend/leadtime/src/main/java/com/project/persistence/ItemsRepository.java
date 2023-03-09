package com.project.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Items;

public interface ItemsRepository extends JpaRepository<Items, Long> {
	
	List<Items> findByMachineryContaining(String machinery);
	
	List<Items> findByItemsContaining(String items);
	
	List<Items> findByPart1Containing(String part1);
	
	List<Items> findByClientsContaining(String client);

}
