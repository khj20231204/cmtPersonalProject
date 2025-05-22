package com.example.cmtProject.dto.erp.saleMgt;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PdfMainDTO {

	private String pdtName;	//제품명
	private String soDate;  //수주일자
	private String pdtPrice; //단가
	private String soQty; //수량
	private String priceQtySum; //수량단가 합계
	
}
