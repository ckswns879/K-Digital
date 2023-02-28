package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.Basket;
import com.project.domain.leadtime;
import com.project.service.DataService;

@RestController
public class DataController {
	
	@Autowired
	private DataService dataService;
	
	@GetMapping("/data/get")
	public List<leadtime> getData(){
		return dataService.getData();
	}
	
	@PostMapping("/data/search")
	public List<leadtime> getResult(@RequestBody String[] search){
		return dataService.getResult(search);
	}
	
	@PostMapping("/data/basket")
	public void addBasket(@RequestBody Basket[] basket) {
		System.out.println(basket);
		dataService.addBasket(basket);
	}
	
	@GetMapping("/data/getbasket")
	public List<Basket> getBasket(){
		return dataService.getBasket();
	}
	//회원가입을 한다면 회원정보가 파라미터로 들어가야됨
	
	@PostMapping("/data/delbasket")
	public void delBasket(@RequestBody int[] idNum) {
		dataService.delBasket(idNum);
	}
}
