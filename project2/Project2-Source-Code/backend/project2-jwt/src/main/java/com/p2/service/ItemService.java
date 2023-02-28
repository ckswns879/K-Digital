package com.p2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.p2.domain.ItemInfoVO;
import com.p2.repository.ItemInfoRepository;



@Service
public class ItemService {
	@Autowired
	private ItemInfoRepository itemRepository;
	
	public List<ItemInfoVO> getItemList(){
		return itemRepository.getItemList();
	}
	

}
