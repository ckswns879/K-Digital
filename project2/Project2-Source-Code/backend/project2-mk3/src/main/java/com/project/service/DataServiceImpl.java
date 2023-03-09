package com.project.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.domain.Basket;
import com.project.domain.ItemInfo;
import com.project.domain.Member;
import com.project.domain.Payment;
import com.project.dto.BasketDto;
import com.project.dto.ItemDto;
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
	
	
	public List<ItemInfo> getData(){
		return itemsRepo.findAll();
	}
	
	public List<ItemInfo> getResult(String[] search){
		if (search[0].equals("부품대분류")) return itemsRepo.findByMachineryContaining(search[1]);
		else if (search[0].equals("부품명")) return itemsRepo.findByItemsContaining(search[1]);
		else if (search[0].equals("부품번호")) return itemsRepo.findByPart1Containing(search[1]);
		else return itemsRepo.findByClientsContaining(search[1]);		
	}
	//String끼리 비교는 equals 제발 기억하자
	
	//itemsDto를 이용해서 저장함
	public void addItem(@RequestBody ItemDto.Request items) {
		ItemInfo item = items.toEntity();
		itemsRepo.save(item);
	}
	
	//BasketDto로 저장(프론트에서 입력한 아이템id 와 memberid로 각각 검색해서 dto를 활용해서 저장
	@Transactional
	public void addBasket(@RequestBody int[][] basket, BasketDto.Request dto) {
		for (int[] e: basket) {
			ItemInfo items = itemsRepo.findById((long)e[0]).get();
			dto.setItems(items);
			Member member = memberRepo.findById((long)e[1]).get();
			dto.setMember(member);
			Basket baskets = dto.toEntity();
			basketRepo.save(baskets);			
		}		
	}
	
	//현재 로그인 한 id의 장바구니만 뽑아 옴
	public List<Basket> getBasket(Authentication authentication){
		return basketRepo.findByMember_Id(Long.parseLong(authentication.getName()));
	}
	
	//장바구니 삭제
	@Transactional
	public void delBasket(int[] idNum) {
		for (int e: idNum) {
			System.out.println(e);
			basketRepo.deleteById((long)e);
		}	
	}
	
	
	//basket의 청구량을 patment의 발주수량으로 바꾸기
	public Payment createPayment(Basket basket) {
        Payment payment = new Payment();
        payment.setItems(basket.getItems());
        payment.setLeadtime(basket.getLeadtime());
        payment.setClients(basket.getClients());
        payment.setOrder_qty(basket.getBilling_amount());
        payment.setOrder_price(basket.getEsti_unit_price() * basket.getBilling_amount());
        payment = paymentRepo.save(payment);
        basketRepo.delete(basket);
        return payment;
        
    }
		
}
	



