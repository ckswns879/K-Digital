package com.p2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.p2.domain.LeadtimeVO;
import com.p2.repository.LeadtimeRepository;


@Service
public class LeadtimeService {
	
	@Autowired
	private LeadtimeRepository leadtimerepository;

	
	public List<LeadtimeVO> getLeadtime(LeadtimeVO leadtime){
		return leadtimerepository.getLeadtime(leadtime);		
	}

}
