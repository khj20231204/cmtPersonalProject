package com.example.cmtProject.dto.erp.saleMgt;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.cmtProject.entity.erp.salesMgt.SalesOrder;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesOrderDTO {
	
	private Long soNo; //수주주문번호(pk)
	
	@NotBlank(message = "날짜를 선택해서 수주코드를 만드세요")
	private String soCode;	//수주코드
	
	//@NotBlank 는 String 타입만 적용
	@NotNull(message = "수주일자를 입력하세요")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate soDate;  //수주일자
    
	@NotNull(message = "예상 출하일자를 입력하세요")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate shipDate;  //출하일자

	//입력 받지 않음
	private LocalDate soDueDate;
	
	@NotNull(message = "사원코드를 선택하세요")
	private Long empNo; //사원코드
    
    //입력 받지 않는다
    private String whsCode;  //창고코드
    
    @NotBlank(message = "제품코드를 선택하세요")
	private String pdtCode;  //제품코드
    
    @NotBlank(message = "거래처명을 선택하세요")
	private String cltCode;	 //고객코드
    
    @NotBlank(message = "수량은 필수입니다.")
    @Pattern(regexp = "^[0-9]+$", message = "수량은 숫자만 입력 가능합니다.")
	private String soQty;  //수량
    
    @NotBlank(message = "출고단가는 필수입니다.")
    @Pattern(regexp = "^[0-9]+$", message = "출고단가는 숫자만 입력 가능합니다.")
	private String pdtPrice; //출고단가
    
    //입력 받지 않는다
	private Integer soValue;  //공급가액
	
	@NotBlank(message = "진행상태는 필수입니다.")
	private String soStatus; //종결여부
	
	//입력 받지 않는다
	private String soComments;  //비고
	
	private String soVisible = "Y";
	private String soUseYn = "Y";
	private String qtyUnit = "UNC001";
	
	//entity 객체를 생성하여 DTO->ENTITY로 변환
	public SalesOrder toEntity(SalesOrderDTO request) {
	    SalesOrder entity = new SalesOrder();

	    entity.setSoCode(request.getSoCode());
	    entity.setSoDate(request.getSoDate());
	    entity.setShipDate(request.getShipDate());
	    entity.setSoDueDate(request.getSoDueDate());
	    entity.setEmpNo(request.getEmpNo());
	    entity.setWhsCode(request.getWhsCode());
	    entity.setPdtCode(request.getPdtCode());
	    entity.setCltCode(request.getCltCode());
	    entity.setSoQty(request.getSoQty());
	    entity.setPdtPrice(request.getPdtPrice());
	    entity.setSoValue(request.getSoValue());
	    entity.setSoStatus(request.getSoStatus());
	    entity.setSoComments(request.getSoComments());
	    entity.setSoVisible(request.getSoVisible() != null ? request.getSoVisible() : "Y");
	    entity.setSoUseYn(request.getSoUseYn() != null ? request.getSoUseYn() : "Y");
	    entity.setQtyUnit(request.getQtyUnit() != null ? request.getQtyUnit() : "UNC001");

	    return entity;
	}
}
