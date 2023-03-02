package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.ItemInfo;
import com.project.dto.ItemDto;
import com.project.service.DataService;

@RestController
public class ItemController {
	@Autowired
	private DataService dataService;
	
	//app.js 데이터 호출
	@GetMapping("/data/get")
	public List<ItemInfo> getData(){
		return dataService.getData();
	}
	
	//검색한 데이터 호출
	@PostMapping("/data/search")
	public List<ItemInfo> getResult(@RequestBody String[] search){
		return dataService.getResult(search);
	}
	
	//아이템 추가(admin 화면)
	@PostMapping("/data/additem")
	public void addItem(@RequestBody ItemDto.Request items) {
		dataService.addItem(items);
	}

}
