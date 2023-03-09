package com.project.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.domain.Basket;
import com.project.domain.Items;
import com.project.dto.BasketDto;
import com.project.dto.ItemsDto;
import com.project.dto.PaymentDto;
import com.project.dto.ItemsDto.Response;

public interface DataService {

	public List<Items> getData();

	public List<Items> getResult(String[] search);
	
	public void addItem(@RequestBody ItemsDto.Request items);

	public void addBasket(@RequestBody int[][] basket, BasketDto.Request dto);

	public String getBasket(Authentication authentication) throws JsonProcessingException;

	public void delBasket(int[] idNum);

	public void addPayment(int[] idNum, PaymentDto.Request dto);
	
	

}
