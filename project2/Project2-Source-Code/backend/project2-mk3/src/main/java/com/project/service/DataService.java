package com.project.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.domain.Basket;
import com.project.domain.ItemInfo;
import com.project.domain.Payment;
import com.project.dto.BasketDto;
import com.project.dto.ItemDto;

public interface DataService {

	public List<ItemInfo> getData();

	public List<ItemInfo> getResult(String[] search);
	
	public void addItem(@RequestBody ItemDto.Request items);

	public void addBasket(@RequestBody int[][] basket, BasketDto.Request dto);

	public List<Basket> getBasket(Authentication authentication);

	public void delBasket(int[] idNum);

	public Payment createPayment(Basket basket);
	
	

}
