package com.example.cmtProject.dto.erp.saleMgt;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PdfFooterDTO {

	private String soQtySum;	//총 수량 갯수
	private String pdtPriceSum; //총 단가 
	private String totalSum;	//총 수량 * 총 단가
}
