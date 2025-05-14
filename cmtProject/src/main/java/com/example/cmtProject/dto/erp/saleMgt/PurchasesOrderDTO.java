package com.example.cmtProject.dto.erp.saleMgt;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.cmtProject.entity.erp.salesMgt.PurchasesOrder;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchasesOrderDTO {

	
	private Long poNo; //발주주문번호(pk)

	@NotBlank(message = "날짜를 선택해서 발주코드를 만드세요")
	private String poCode;
	
	@NotNull(message = "발주일자를 입력하세요")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate poDate;
	
	@NotNull(message = "예상 입고일자를 입력하세요")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate mtlRcvDate;  
	
	@NotNull(message = "사원코드를 선택하세요")
	private Long empNo;  //사원번호
	
	//입력 받지 않는다
	@Column(name = "WHS_CODE")
	private String whsCode; //창고 코드
	
	@NotBlank(message = "원자재 코드를 선택하세요")
	private String mtlCode; //원자재 코드
	
	@NotBlank(message = "거래처명을 선택하세요")
	private String cltCode;  //공급업체 코드
	
    @NotBlank(message = "수량은 필수입니다.")
    @Pattern(regexp = "^[0-9]+$", message = "수량은 숫자만 입력 가능합니다.")
	private String poQty;  //수량
	
    @NotBlank(message = "입고단가는 필수입니다.")
    @Pattern(regexp = "^[0-9]+$", message = "입고단가는 숫자만 입력 가능합니다.")
	private String mtlRcvPrice;  //입고단가
	
	//입력 받지 않는다
	private Integer poValue;  //공급가액
	
	@NotBlank(message = "진행상태는 필수입니다.")
	private String poStatus;  //종결여부
	
	//입력 받지 않는다
	private String poComments;  //비고
	
	private String poUseYn = "Y";  //사용여부
	private String qtyUnit = "UNC001";  //단위
	
	//입력 받지 않는다
	private String mtlRcvQty;  //발주 입고량
	
	//builder를 이용하여 DTO->ENTITY로 변환
	public PurchasesOrder toEntity() {
	    return PurchasesOrder.builder()
	            .poNo(this.poNo)
	            .poCode(this.poCode)
	            .poDate(this.poDate)
	            .mtlRcvDate(this.mtlRcvDate)
	            .empNo(this.empNo)
	            .whsCode(this.whsCode)
	            .mtlCode(this.mtlCode)
	            .cltCode(this.cltCode)
	            .poQty(this.poQty)
	            .mtlRcvPrice(this.mtlRcvPrice)
	            .poValue(this.poValue)
	            .poStatus(this.poStatus)
	            .poComments(this.poComments)
	            .poUseYn(this.poUseYn)
	            .qtyUnit(this.qtyUnit)
	            .mtlRcvQty(this.mtlRcvQty != null ? this.mtlRcvQty : null)
	            .build();
	}
	
}