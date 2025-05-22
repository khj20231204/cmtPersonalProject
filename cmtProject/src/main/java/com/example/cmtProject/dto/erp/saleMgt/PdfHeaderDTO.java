package com.example.cmtProject.dto.erp.saleMgt;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PdfHeaderDTO {

	private String cltName;	//거래처명
	private String cltAddress; //주소 
	private String cltManagerName;	//책임자
	private String cltEmail;//아매알
	private String cltPhone;//전화번호
	
}
