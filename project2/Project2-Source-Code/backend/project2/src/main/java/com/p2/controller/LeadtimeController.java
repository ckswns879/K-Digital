package com.p2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.p2.domain.LeadtimeVO;
import com.p2.service.LeadtimeService;



@RestController
public class LeadtimeController {
	
	@Autowired
	private LeadtimeService leadtimeService;
	
	//리드타임 예측 결과 출력(select page)
	@GetMapping("/data/leadtime")
	public List<LeadtimeVO> getLeadtime(LeadtimeVO leadtime){
		return leadtimeService.getLeadtime(leadtime);
	}
	

}