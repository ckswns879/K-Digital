package com.p2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.p2.domain.BasketVO;
import com.p2.domain.ItemInfoVO;
import com.p2.repository.BasketRepository;
import com.p2.repository.ItemInfoRepository;


	
@Service
public class BasketService {

//	@Autowired
//	private LeadtimeRepository leadtimerepository;
	@Autowired
	private ItemInfoRepository iteminforepository;
	@Autowired
	private BasketRepository basketrepository;
	
	public List<ItemInfoVO> getSearch() {
		return iteminforepository.getSearch();
	}

	public void addBasket(BasketVO[] basket) {
		basketrepository.addBasket(basket);
		
	}

	public List<BasketVO> getBasket() {
		return basketrepository.getBasket();
	}
}