package com.p2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.p2.domain.BasketVO;
import com.p2.domain.LeadtimeVO;
import com.p2.repository.BasketRepository;
import com.p2.repository.LeadtimeRepository;



@Service
public class BasketService {

	@Autowired
	private LeadtimeRepository leadtimerepository;
	@Autowired
	private BasketRepository basketrepository;
	
	public List<LeadtimeVO> getSearch() {
		return leadtimerepository.getSearch();
	}

	public void addBasket(BasketVO[] basket) {
		basketrepository.addBasket(basket);
		
	}

	public List<BasketVO> getBasket() {
		return basketrepository.getBasket();
	}
}