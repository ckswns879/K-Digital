package com.project.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.project.domain.Member;

import lombok.Getter;

//현재 로그인한 아이디로 저장되어있는 장바구니 목록을 모두 출력하기 위한 dto
public class MemberDto {
	
	@Getter
    public static class Response {
        private final Long id;
        private final List<BasketDto.Response> basket;

        /* Entity -> Dto*/
        public Response(Member member) {
            this.id = member.getId();
            this.basket = member.getBasket().stream().map(BasketDto.Response::new).collect(Collectors.toList());
        }
    }

}
