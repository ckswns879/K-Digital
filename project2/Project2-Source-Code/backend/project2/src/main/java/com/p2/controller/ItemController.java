package com.p2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.p2.domain.ItemInfoVO;
import com.p2.service.ItemService;


@RestController
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	//검색기능 구현을 위한 분류별 리스트 출력
	@GetMapping("/data/itemlist")
	public List<ItemInfoVO> getItemList(){
		return itemService.getItemList();
	}

}
