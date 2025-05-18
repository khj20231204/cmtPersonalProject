package com.example.cmtProject.controller.mes.standardInfoMgt;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ProcessHandle.Info;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.cmtProject.controller.mes.standardInfoMgt.commModels.BomInfoModels;
import com.example.cmtProject.dto.mes.standardInfoMgt.BomEditDTO;
import com.example.cmtProject.dto.mes.standardInfoMgt.BomInfoDTO;
import com.example.cmtProject.dto.mes.standardInfoMgt.BomInfoTotalDTO;
import com.example.cmtProject.dto.mes.standardInfoMgt.ProductTotalDTO;
import com.example.cmtProject.dto.mes.standardInfoMgt.ProductsDTO;
import com.example.cmtProject.dto.mes.standardInfoMgt.ProductsEditDTO;
import com.example.cmtProject.dto.mes.standardInfoMgt.UnitCountDTO;
import com.example.cmtProject.entity.erp.salesMgt.SalesOrder;
import com.example.cmtProject.entity.mes.standardInfoMgt.Materials;
import com.example.cmtProject.entity.mes.standardInfoMgt.ProcessInfo;
import com.example.cmtProject.entity.mes.standardInfoMgt.Products;
import com.example.cmtProject.repository.erp.saleMgt.MaterialsOrderRepository;
import com.example.cmtProject.repository.mes.standardInfoMgt.ProcessInfoRepository;
import com.example.cmtProject.repository.mes.standardInfoMgt.ProductsRepository;
import com.example.cmtProject.service.mes.standardInfoMgt.BomInfoService;
import com.example.cmtProject.service.mes.standardInfoMgt.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/bom")
public class BomInfoController {

	@Autowired
	private ProductsRepository productsRepository;
	@Autowired
	private MaterialsOrderRepository materialsOrderRepository;
	@Autowired
	private ProcessInfoRepository processInfoRepository;
	
	@Autowired
	private ProductService productsService;
	@Autowired
	private BomInfoService bomInfoService;
	
	@Autowired 
	private BomInfoModels bomInfoModels;
	
	@GetMapping("/bom-info")
	public String bomInfo(Model model) throws Exception {
		
		//------------ PRODUCTS --------------------
		List<ProductTotalDTO> productList = productsService.getProductTotalList();
		model.addAttribute("productList", productList);
		
		//하단 bom 그리드에 빈 데이터 전송 왜?
		/* 아무것도 모르는 팀원이 환경 설정을 이상하게 했다.
		 * 최초 로딩시에는 컬럼명이 소문자, 
		 * 이후 제품 그리드에서 선택했을 때는 ajax로 받으니깐 대문자로 받게된다.
		 * 그리드를 따로 둬야한다.
		 * */
		List<BomInfoTotalDTO> bomList = new ArrayList<>();
		model.addAttribute("bomList", bomList);
		
		List<Materials> materialsList = materialsOrderRepository.findAll(); 
		model.addAttribute("materialsList", materialsList);
		
		List<ProcessInfo> processList = processInfoRepository.findAll();
		model.addAttribute("processList", processList);
		
		//제품 tooltip에 출력할 FP중 가장 큰 FP와 WIP중 가장 큰 WIP
		String maxFP = productsService.selectMaxFP();
		String maxWIP = productsService.selectMaxWIP();
		
		model.addAttribute("maxFP", maxFP);
		model.addAttribute("maxWIP", maxWIP);
		
		//단위 데이터 models
		bomInfoModels.commonBomInfoModels(model);
	 	
	 	//------------ BOM --------------------
	 	
	 	//투입 단위 가져오기
	 	List<UnitCountDTO> bomUnitCountList = bomInfoService.selectBomUnitCount();
	 	model.addAttribute("bomUnitCountList", bomUnitCountList);
	 	
	 	//등록 modal에서 사용할 pdtCode
	 	//상위 코드는 products에서만 가져온다, 하위 코드는 products와 materials에서 가져온다
	 	List<String> childCodePdtList = productsService.selectChildItemCodePdt();
	 	List<String> parentCodePdtList = productsService.selectParentItemCodePdt();
	 	
	 	model.addAttribute("childCodePdtList", childCodePdtList);
	 	model.addAttribute("parentCodePdtList", parentCodePdtList);
	 	
	 	//th:object에서 사용할 객체 생성
	 	BomInfoDTO bomInfoDto = new BomInfoDTO();
	 	bomInfoDto.setBomNo(000L);
	 	model.addAttribute("bomInfoDto", bomInfoDto);
	 	
	 	//----------------------------------------

	 	
		return "mes/standardInfoMgt/bomInfo";
	}
	
	//상품 그리드에서 선택된 제품에 해당하는 BOM 데이터 불러오기
	@ResponseBody
	@GetMapping("/bom-selected")
	//public Map<String, Object> bomSelected(@RequestParam("pdtCode") String pdtCode){
	//public void bomSelected(@RequestParam("pdtCode") String pdtCode){
	public List<BomInfoTotalDTO> bomSelected(@RequestParam("pdtCode") String pdtCode){

		List<BomInfoTotalDTO> bomtotal = bomInfoService.getBomInfoTotalList(pdtCode);
		/*
		// 데이터와 컬럼 분리
		List<Map<String, Object>> bomData = bomtotal.stream()
			    .map(b -> {
			        Map<String, Object> map = new HashMap<>();
			        map.put("BOM_NO", b.getBomNo());
			        map.put("PDT_CODE", b.getPdtCode());
					map.put("PDT_NAME", b.getPdtName()); 
					map.put("MTL_CODE", b.getMtlCode());
					map.put("MTL_NAME", b.getMtlName());  
					map.put("BOM_QTY", b.getBomQty());
					map.put("BOM_UNIT", b.getBomUnit()); 
					map.put("PDT_PRC_TYPE_NAME",b.getPdtPrcTypeName());
			        return map;
			    }).collect(Collectors.toList());
		
		//그리드에 출력시킬 컬럼 생성
		List<Map<String, Object>> columns = List.of(
				Map.of("header", "BOM 고유번호", "name", "BOM_NO"),
				Map.of("header", "제품 코드", "name", "PDT_CODE"),
				Map.of("header", "제품 이름", "name", "PDT_NAME"),
				Map.of("header", "원자재 코드", "name", "MTL_CODE"),
				Map.of("header", "원자재 이름", "name", "MTL_NAME"),
				Map.of("header", "수량", "name", "BOM_QTY"),
				Map.of("header", "단위", "name", "BOM_UNIT"),
				Map.of("header", "공정 단계", "name", "PDT_PRC_TYPE_NAME")
			);
	
		return Map.of(
				"columns", columns,
				"data", bomData
			);*/
		
		return bomtotal;
	}
	
	//----------------------------------------- 카멜 -> 스네이크 자동 적용 되어 일치 시키기 위해서 전부 스네이크로 변경해 줘야 함 ------------------------------------
	//최초 로딩시 그리드에 출력할 제품 목록
	@GetMapping("/bom-info-frgmsVersion")
	public String bomInfoFrgmsVersion(Model model) {
		
		List<Products> pdtList = productsRepository.findAll();
		
		//상단 그리드에 출력하기 위해서 List<Products> => List<Map<...>>형태로 변환
		List<Map<String, Object>> productData = pdtList.stream()
		    .map(p -> {
		        Map<String, Object> map = new HashMap<>();
		        map.put("pdtNo", p.getPdtNo());
		        map.put("pdtCode", p.getPdtCode());
		        map.put("pdtName", p.getPdtName());
		        map.put("pdtShippingPrice", p.getPdtShippingPrice());
		        map.put("pdtComments", p.getPdtComments());
		        return map;
		    }).collect(Collectors.toList());
	
		//그리드에 출력시킬 컬럼 생성
		List<Map<String, Object>> columns = List.of(
				Map.of("header", "제품코드", "name", "pdtCode"),
				Map.of("header", "제품명", "name", "pdtName"),
				Map.of("header", "규격", "name", "pdtSpecification"),
				Map.of("header", "출하단가", "name", "pdtShippingPrice"),
				Map.of("header", "비고", "name", "pdtComments")
			);
		
		model.addAttribute("pdtGridOptions", Map.of(
		    "columns", columns,
		    "data", productData
		));
		
		//BOM그리드에 최초 로딩시 공백으로 나타내기 위해 빈 List전달
		model.addAttribute("bomGridOptions", Map.of(
			    "columns", List.of(),
			    "data", List.of()
			));
		
		return "mes/standardInfoMgt/bomInfoFrgmsVersion";
	}
	
	//상단 제품에서 선택했을 때 가져올 BOM데이터
	
	//상품 그리드 선택했을 때 BOM 그리드에 선택한 상품 보여주기
	@ResponseBody
	@GetMapping ("/bom-selected-frgmsVersion")
	public Map<String, Object> bomSelectedFrgmsVersion(@RequestParam("productCode") String pdtCode){
		
		List<BomInfoTotalDTO> bomtotal = bomInfoService.getBomInfoTotalList(pdtCode);
		
		//log.info("bomtotal"+bomtotal);
		
		
		List<Map<String, Object>> bomData = bomtotal.stream()
			    .map(b -> {
			        Map<String, Object> map = new HashMap<>();
			        map.put("BOM_NO", b.getBomNo());
			        map.put("PARENT_PDT_CODE", b.getParentPdtCode());
			        map.put("CHILD_ITEM_CODE", b.getChildItemCode());
					map.put("PDT_NAME", b.getPdtName()); 
					map.put("MTL_NAME", b.getMtlName());  
					map.put("BOM_QTY", b.getBomQty());
					map.put("BOM_UNIT", b.getBomUnit()); 
					map.put("PRC_TYPE_NAME",b.getPrcTypeName());
			        return map;
			    }).collect(Collectors.toList());
		
		//그리드에 출력시킬 컬럼 생성
		List<Map<String, Object>> columns = List.of(
				Map.of("header", "BOM 고유번호", "name", "BOM_NO"),
				Map.of("header", "제품 코드", "name", "PDT_CODE"),
				Map.of("header", "제품 이름", "name", "PDT_NAME"),
				Map.of("header", "원자재 코드", "name", "MTL_CODE"),
				Map.of("header", "원자재 이름", "name", "MTL_NAME"),
				Map.of("header", "수량", "name", "BOM_QTY"),
				Map.of("header", "단위", "name", "BOM_UNIT"),
				Map.of("header", "공정 단계", "name", "PRC_TYPE_NAME")
			);
			
		return Map.of(
				"columns", columns,
				"data", bomData
			);
	}
	
	/*
	//엑셀 파일 다운로드 - 자바스크립트에서 바로 다운로드한다
	@GetMapping("/excel-file-down")
	public void downloadExcel(HttpServletResponse response) throws IOException {
	    String fileName = "bom_form.xls";
	    String filePath = "/excel/" + fileName;

	    // /static/ 디렉토리 기준으로 파일을 읽어옴
	    //log.info("filePath:"+filePath);
	    InputStream inputStream = new ClassPathResource(filePath).getInputStream();

	    //log.info("inputStream:"+inputStream);
	    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

	    // 파일 내용을 응답 스트림에 복사
	    StreamUtils.copy(inputStream, response.getOutputStream());
	    response.flushBuffer();
	}*/
	
	@ResponseBody
	@GetMapping("/bomAll")
	public List<BomInfoDTO> bomAll(){
		
		List<BomInfoDTO> bomAllList = bomInfoService.bomAll();
		
		return bomAllList;
	}
	
	
	//BOM 그리드에서 바로 수정
	@ResponseBody
	@GetMapping("/bomeditexe")
	public int bomeditexep(@ModelAttribute BomEditDTO bomEditDto) throws JsonMappingException, JsonProcessingException {
		
		//log.info(bomEditDto.toString());
		
		//products에 있는 제품인지 확인
		if(bomEditDto.getColumnName().equals("CHILD_ITEM_CODE") || bomEditDto.getColumnName().equals("PARENT_PDT_CODE")) {
			
			int count = bomInfoService.selectCheckPdtList(bomEditDto.getValue());
			
			if(count <= 0) {
				return 0;
			}
		}
		
		int resultEdit = bomInfoService.bomMainUpdate(bomEditDto); 
		
		return 1;
	}
	
	//public String save(BindingResult result, @Valid SalesOrderRequest req) ❌ 작동하지 않음
	//BindingResult는 반드시 @Valid 다음에 위치해야 함
	//@ModelAttribute("bomInfoDto") : ModelAttribute안에 파라미터 bomInfoDto 명시!
	@PostMapping("/bomRegister")
	public String bomRegister(@ModelAttribute("bomInfoDto") @Valid BomInfoDTO bomInfoDto, BindingResult bindingResult, Model model) {
		
		 if (bindingResult.hasErrors()) {
			 
			//------------ PRODUCTS 정보 넘기기 --------------------
			List<ProductTotalDTO> productList = productsService.getProductTotalList();
			model.addAttribute("productList", productList);
			
			List<Materials> materialsList = materialsOrderRepository.findAll(); 
			model.addAttribute("materialsList", materialsList);
			
			List<ProcessInfo> processList = processInfoRepository.findAll();
			model.addAttribute("processList", processList);
			
			String maxFP = productsService.selectMaxFP();
			String maxWIP = productsService.selectMaxWIP();
			
			model.addAttribute("maxFP", maxFP);
			model.addAttribute("maxWIP", maxWIP);
			
		 	//------------ BOM 정보 넘기기 --------------------
		 	
		 	//투입 단위 가져오기
		 	List<UnitCountDTO> bomUnitCountList = bomInfoService.selectBomUnitCount();
		 	model.addAttribute("bomUnitCountList", bomUnitCountList);
		 	
		 	//등록 modal에서 사용할 pdtCode
		 	//상위 코드는 products에서만 가져온다, 하위 코드는 products와 materials에서 가져온다
		 	List<String> childCodePdtList = productsService.selectChildItemCodePdt();
		 	List<String> parentCodePdtList = productsService.selectParentItemCodePdt();
		 	
		 	model.addAttribute("childCodePdtList", childCodePdtList);
		 	model.addAttribute("parentCodePdtList", parentCodePdtList);
		 	
		 	bomInfoModels.commonBomInfoModels(model);
		 	
		 	//------------- 이전 DTO값 그대로 넘기기 ----------------------------------------
			 
		 	model.addAttribute("bomInfoDto", bomInfoDto);
			 
		 	//--------------------------------------------------------------------------
	    	
			log.error("Validation 오류 발생!"); 	
	    	
			bindingResult.getFieldErrors().forEach(error -> {
				 System.out.println("Field: " + error.getField());
				 System.out.println("Rejected value: " + error.getRejectedValue());
				 System.out.println("Message: " + error.getDefaultMessage());
	        });
	    	
			return "mes/standardInfoMgt/bomInfo";
		 }	
		
		 //데이터 입력
		 bomInfoService.insertBomInfo(bomInfoDto);
		
		 return "redirect:/bom/bom-info";
	}
}
