package com.project.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.ItemInfo;

public interface ItemsRepository extends JpaRepository<ItemInfo, Long> {
	
	List<ItemInfo> findByMachineryContaining(String machinery);
	
	List<ItemInfo> findByItemsContaining(String items);
	
	List<ItemInfo> findByPart1Containing(String part1);
	
	List<ItemInfo> findByClientContaining(String client);

}
