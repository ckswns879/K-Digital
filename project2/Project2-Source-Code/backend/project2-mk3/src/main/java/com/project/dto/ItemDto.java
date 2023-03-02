package com.project.dto;

import com.project.domain.ItemInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ItemDto {
	
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class Request {
		private Long id;
		private String subject;
		private String machinery;
		private String assembly;
		private String items;
		private String part1;
		private String part2;	//??
		private String key2;
		private String leadtime;
		private String client;	//발주처
		private String esti_unit_price;	//견적단가
		private String currency;	//견적화폐
		
		public ItemInfo toEntity() {
			ItemInfo iteminfo = ItemInfo.builder()
					.id(id)
					.subjects(subject)
					.machinery(machinery)
					.assembly(assembly)
					.items(items)
					.part1(part1)
					.part2(part2)
					.key2(key2)
					.leadtime(leadtime)
					.clients(client)
					.esti_unit_price(esti_unit_price)
					.currency(currency)
					.build();
			return iteminfo;
		}
	}

}
