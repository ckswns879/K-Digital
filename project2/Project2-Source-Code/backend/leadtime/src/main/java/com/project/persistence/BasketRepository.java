package com.project.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Basket;

public interface BasketRepository extends JpaRepository<Basket, Long> {
	
	List<Basket> findByMember_Id(Long id);
	void deleteByid(Long idNum);

}
