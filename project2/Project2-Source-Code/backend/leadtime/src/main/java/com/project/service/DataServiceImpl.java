package com.project.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.domain.Basket;
import com.project.domain.Items;
import com.project.domain.Member;
import com.project.domain.Payment;
import com.project.dto.BasketDto;
import com.project.dto.ItemsDto;
import com.project.dto.MemberDto;
import com.project.dto.PaymentDto;
import com.project.persistence.BasketRepository;
import com.project.persistence.ItemsRepository;
import com.project.persistence.MemberRepository;
import com.project.persistence.PaymentRepository;

@Service
public class DataServiceImpl implements DataService{
	
	@Autowired
	private ItemsRepository itemsRepo;
	
	@Autowired
	private BasketRepository basketRepo;
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private PaymentRepository paymentRepo;
	
	public List<Items> getData(){
		return itemsRepo.findAll();
	}
//	public List<ItemsDto.Response> getData(){
//		List<Items> items = itemsRepo.findAll();
//		return items.stream().map(ItemsDto.Response::new).collect(Collectors.toList());
//	}
	
	public List<Items> getResult(String[] search){
		if (search[0].equals("부품대분류")) return itemsRepo.findByMachineryContaining(search[1]);
		else if (search[0].equals("부품명")) return itemsRepo.findByItemsContaining(search[1]);
		else if (search[0].equals("부품번호")) return itemsRepo.findByPart1Containing(search[1]);
		else return itemsRepo.findByClientsContaining(search[1]);		
	}
	//String끼리 비교는 equals 제발 기억하자
	
	//itemsDto를 이용해서 저장함
	public void addItem(@RequestBody ItemsDto.Request items) {
		Items item = items.toEntity();
		itemsRepo.save(item);
	}
	
	//BasketDto로 저장(프론트에서 입력한 아이템id 와 memberid로 각각 검색해서 dto를 활용해서 저장
	@Transactional
	public void addBasket(@RequestBody int[][] basket, BasketDto.Request dto) {
		for (int[] e: basket) {
			Items items = itemsRepo.findById((long)e[0]).get();
			dto.setItems(items);
			Member member = memberRepo.findById((long)e[1]).get();
			dto.setMember(member);
			Basket baskets = dto.toEntity();
			basketRepo.save(baskets);			
		}		
	}
	
	//현재 로그인 한 id의 장바구니만 뽑아 옴
	public String getBasket(Authentication authentication) throws JsonProcessingException{
		//플라스크에 요청 보낼 때 body 전처리(list를 string(json)으로 변경)
		Member member = memberRepo.findById(Long.parseLong(authentication.getName())).get();
		MemberDto.Response json = new MemberDto.Response(member);
				
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(json);
		System.out.println(jsonString);
		//플라스크로 요청 보내기
		String url = "http://10.125.121.177:5000/data/predictAll";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(jsonString, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
		
		return response.getBody();
	}
	
	//장바구니 삭제
	@Transactional
	public void delBasket(int[] idNum) {
		for (int e: idNum) {
			basketRepo.deleteById((long)e);
		}	
	}
	
	//주문하기
	public void addPayment(int[] idNum, PaymentDto.Request dto) {
		for (int e: idNum) {
			Basket basket = basketRepo.findById((long)e).get();
			Items items = itemsRepo.findById(basket.getItems().getId()).get();
			dto.setItems(items);
			Member member = memberRepo.findById(basket.getMember().getId()).get();
			dto.setMember(member);
			Payment payment = dto.toEntity();
			paymentRepo.save(payment);
			basketRepo.deleteById((long)e);
		}
	}
}
