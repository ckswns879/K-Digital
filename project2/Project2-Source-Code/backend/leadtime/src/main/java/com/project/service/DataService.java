package com.project.service;

import java.util.List;

import com.project.domain.Basket;
import com.project.domain.leadtime;

public interface DataService {

	public List<leadtime> getData();

	public List<leadtime> getResult(String[] search);

	public void addBasket(Basket[] basket);

	public List<Basket> getBasket();

	public void delBasket(int[] idNum);
	
	

}
