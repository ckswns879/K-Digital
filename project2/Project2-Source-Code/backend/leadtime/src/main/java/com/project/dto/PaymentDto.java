package com.project.dto;

import com.project.domain.Basket;
import com.project.domain.Items;
import com.project.domain.Member;
import com.project.domain.Payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class PaymentDto {
	
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class Request {
		private Long id;
		private Items items;
		private Member member;
		
		public Payment toEntity() {
			Payment payment = Payment.builder()
					.id(id)
					.items(items)
					.member(member)
					.build();
			return payment;
		}
	}

}
