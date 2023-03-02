package com.project.dto;

import com.project.domain.Basket;
import com.project.domain.ItemInfo;
import com.project.domain.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class BasketDto {
	
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class Request {
		private Long id;
		private ItemInfo items;
		private Member member;
		
		public Basket toEntity() {
			Basket basket = Basket.builder()
					.id(id)
					.items(items)
					.member(member)
					.build();
			return basket;
		}
	}

}
