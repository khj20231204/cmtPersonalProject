package com.example.cmtProject.entity.erp.salesMgt;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "SALES_ORDER")
@NoArgsConstructor
@AllArgsConstructor
public class SalesOrder {

	/*
	 * @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
	 * "SEQ_SALES_ORDER_SO_NO" )
	 * 
	 * @SequenceGenerator(name = "SEQ_SALES_ORDER_SO_NO",
	 * sequenceName="SEQ_SALES_ORDER_SO_NO")
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SALES_ORDER_SO_NO" )
	@SequenceGenerator(name = "SEQ_SALES_ORDER_SO_NO", sequenceName="SEQ_SALES_ORDER_SO_NO", allocationSize = 1)
	@Column(name = "SO_NO")
	private Long soNo; //수주주문번호(pk)
	
	@Column(name = "SO_CODE")
	private String soCode;
	
	@Column(name = "SO_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate soDate;  //수주일자
	
	@Column(name = "SHIP_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate shipDate;  //출하일자
	
	@Column(name = "SO_DUE_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate soDueDate;  //출하일자
	
	/*
	 * @ManyToOne
	 * @JoinColumn(name = "EMP_NO")
	 * private Employees employees;  //사원번호
	 * Join설정을 하는 것 만으로 PK와 FK관계가 자동으로 만들어짐
	 * FK설정을 하지 않기 위해 사용하지 않음
	 * 
	 * 따라서, 
	 * 
	 * empNO를 단순 필드로 사용 
	 * @Column(name = "EMP_NO")  // FK로 사용될 컬럼, 단순한 숫자로 저장
     * private Long empNo; 
     * 
	 * empNo를 직접 이용해서 별도의 findByEmpNo() 같은 메서드를 사용할 수 있음
	 * List<SalesOrder> findByEmpNo(Long empNo);
	 */
	@Column(name = "EMP_NO")
	private Long empNo; //사원번호
	
	@Column(name = "WHS_CODE")
	private String whsCode;  //창고코드
	
	@Column(name = "PDT_CODE")
	private String pdtCode;  //제품코드
	
	/*
	 * @ManyToOne
	 * @JoinColumn(name = "CLT_NO")
	 */
	//private Clients clients;  //거래처NO
	@Column(name = "CLT_CODE")
	private String cltCode;
	
	@Column(name = "SO_QTY")
	private String soQty;  //수량
	
	@Column(name = "PDT_PRICE")
	private String pdtPrice; //출고단가
	
	@Column(name = "SO_VALUE")
	private Integer soValue;  //공급가액
	
	@Column(name = "SO_STATUS")
	private String soStatus; //종결여부
	
	@Column(name = "SO_COMMENTS")
	private String soComments;  //비고
	
	@Column(name = "SO_VISIBLE")
	private String soVisible = "Y";
	
	@Column(name = "SO_USE_YN")
	private String soUseYn = "Y";
	
	@Column(name = "QTY_UNIT")
	private String qtyUnit = "UNC001";
	
}

