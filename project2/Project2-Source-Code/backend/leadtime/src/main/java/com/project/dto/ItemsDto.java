package com.project.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.project.domain.Basket;
import com.project.domain.Items;
import com.project.domain.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ItemsDto {
	
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class Request {
		private Long id;
		private String subjects;
		private String machinery;
		private String assembly;
		private String items;
		private String part1;
		private String part2;	//??
		private String category;
		private String leadtime;
		private String clients;	//발주처
		private String esti_unit_price;	//견적단가
		private String currency;	//견적화폐
		
		public Items toEntity() {
			Items item = Items.builder()
					.id(id)
					.subjects(subjects)
					.machinery(machinery)
					.assembly(assembly)
					.items(items)
					.part1(part1)
					.part2(part2)
					.category(category)
					.leadtime(leadtime)
					.clients(clients)
					.esti_unit_price(esti_unit_price)
					.currency(currency)
					.build();
			return item;
		}
	}
	
	@Getter
    public static class Response {
		private Long id;
		private String subjects;
		private String machinery;
		private String assembly;
		private String items;
		private String part1;
		private String part2;	//??
		private String category;
		private String leadtime;
		private String clients;	//발주처
		private String esti_unit_price;	//견적단가
		private String currency;	//견적화폐

        /* Entity -> Dto*/
        public Response(Items items) {
        	this.id = items.getId();
        	this.subjects = items.getSubjects();
        	this.machinery = items.getMachinery();
        	this.assembly = items.getAssembly();
        	this.items = items.getItems();
        	this.part1 = items.getPart1();
        	this.part2 = items.getPart2();
        	this.category = items.getCategory();
        	this.leadtime = items.getLeadtime();
        	this.clients = items.getClients();
        	this.esti_unit_price = items.getEsti_unit_price();
        	this.currency = items.getCurrency();
        }
    }

}
