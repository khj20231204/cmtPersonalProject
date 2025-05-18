package com.example.cmtProject.dto.mes.standardInfoMgt;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BomInfoDTO {

	// BOM
    private Long bomNo; 		// BOM 고유번호
    
	private int bomLevel; 		// 재귀시 순서
	
	@NotBlank(message = "상위 코드를 선택하세요")
	private String childItemCode; 	// 상위 코드
	
	@NotBlank(message = "하위 코드를 선택하세요")
    private String parentPdtCode; 	// 하위 코드
    
	@NotBlank(message = "상위제품 유형을 선택하세요")
    private String itemType; 	//상위 코드 유형(RAW_MATERIAL / SEMI_FINISHED)
    
	@NotBlank(message = "하위제품 유형을 선택하세요")
    private String parentItemType; //하위 코드 유형
    
    @NotBlank(message = "수량은 필수입니다.")
    @Pattern(regexp = "^[0-9]+$", message = "수량은 숫자만 입력 가능합니다.")
    private String bomQty; 		// 투입 수량
    
    @NotBlank(message = "투입 단위를 선택하세요")
    private String bomUnit; 	// 투입 단위 (예: EA, TON)
    
    @NotBlank(message = "공정 유형을 선택하세요")
    private String bomPrcType; 	// 투입 공정 단계 (예: PR, WE, PA, SA)
    
    @NotNull(message = "입력 일자를 입력하세요")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate bomDate;  // 입력 날짜
    
    private String comments; 	// 비고
    
    private String useYN; 		// 사용 여부
    
    private String path;     	//하단 왼쪽에 경로를 보여주기 위해서 path값 따로 추가
		
}

