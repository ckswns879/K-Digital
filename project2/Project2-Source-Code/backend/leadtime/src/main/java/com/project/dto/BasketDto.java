package com.project.dto;

import com.project.domain.Basket;
import com.project.domain.Items;
import com.project.domain.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BasketDto {
	
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class Request {
		private Long id;
		private Items items;
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
	
	@Getter
    public static class Response {
        private final Long id;
        private final Items items;

        /* Entity -> Dto*/
        public Response(Basket basket) {
            this.id = basket.getId();
            this.items = basket.getItems();
        }
    }

}
