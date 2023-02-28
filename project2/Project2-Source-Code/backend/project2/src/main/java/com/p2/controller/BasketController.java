package com.p2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.p2.domain.BasketVO;
import com.p2.domain.LeadtimeVO;
import com.p2.service.BasketService;


@Controller
public class BasketController {
	@Autowired
	private BasketService basketService;
	
	//자동완성 검색결과 출력(Order page)
	@GetMapping("/data/search")
	public List<LeadtimeVO> getSearch(){
		return basketService.getSearch();
	}
	
	//겸색결과중 선택된 리스트를 저장
	@PostMapping("/data/basket")
	public void addBasket(@RequestBody BasketVO[] basket) {
		basketService.addBasket(basket);
	}
	
	//저장된 리스트 출력
	@GetMapping("/data/basketlist")
	public List<BasketVO> getBasket(){
		return basketService.getBasket();
	}

}
