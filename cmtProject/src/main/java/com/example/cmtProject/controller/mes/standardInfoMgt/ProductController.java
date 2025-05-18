package com.example.cmtProject.controller.mes.standardInfoMgt;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.cmtProject.controller.mes.standardInfoMgt.commModels.BomInfoModels;
import com.example.cmtProject.dto.mes.standardInfoMgt.BomInfoDTO;
import com.example.cmtProject.dto.mes.standardInfoMgt.ProductsDTO;
import com.example.cmtProject.dto.mes.standardInfoMgt.ProductsEditDTO;
import com.example.cmtProject.entity.mes.standardInfoMgt.Products;
import com.example.cmtProject.repository.erp.saleMgt.MaterialsOrderRepository;
import com.example.cmtProject.repository.mes.standardInfoMgt.ProcessInfoRepository;
import com.example.cmtProject.repository.mes.standardInfoMgt.ProductsRepository;
import com.example.cmtProject.service.mes.standardInfoMgt.BomInfoService;
import com.example.cmtProject.service.mes.standardInfoMgt.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/pdt")
public class ProductController {

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
	
	@GetMapping("")
	public String main() {
		return "mes/standardInfoMgt/product/pdt";
	}
	
//	@GetMapping("/list")
//    public String list(Model model) throws Exception {
//		
//        List<Products> productList = productService.list();
//        System.out.println("productList:" + productList);
//        model.addAttribute("productList", productList);
//        
//        return "mes/standardInfoMgt/product/list";      
//    }
	
	//상품 그리드에서 바로 수정
	@ResponseBody
	@GetMapping("/pdteditexe")
	public int pdteditexep(@ModelAttribute ProductsEditDTO pdtEditDto) throws JsonMappingException, JsonProcessingException {
		
		log.info(pdtEditDto.toString());
		
		int resultEdit = productsService.pdtMainUpdate(pdtEditDto); 
		
		return resultEdit;
	}
	
	//제품 등록 
	@Transactional
	@ResponseBody
	@PostMapping("/pdtRegister")
	public String pdtRegister(@RequestBody Map<String, List<ProductsDTO>> productsDTO) {
		
		try {
			
			for(Map.Entry<String, List<ProductsDTO>> m : productsDTO.entrySet()) {
				
				for(ProductsDTO pdtDto : m.getValue()) {
					
					pdtDto.setPdtNo(null);
					pdtDto.setPdtUseyn("Y");
					ProductsDTO dto = pdtDto;
					
					//DTO를 builder를 이용해서 entity로 변환
					Products entity = dto.toEntity();
					
					try {
						
						productsRepository.save(entity);
						
					} catch (Exception e) {
						log.error(e.toString());
						return "FAIL";
					}
				}
			}

			return "SUCCESS";
			
		}catch (Exception e) {
			log.error(e.toString());
			
			return "FAIL";
		}
	}
	
	//엑셀 파일 저장
	@Transactional
	@ResponseBody
	@PostMapping("/pdtExcelRegister")
	public String pdtExcelRegister(@RequestBody Map<String, List<ProductsDTO>> productsDTO) {
		
		try {
			
			for(Map.Entry<String, List<ProductsDTO>> m : productsDTO.entrySet()) {
				
				for(ProductsDTO pdtDto : m.getValue()) {
					pdtDto.setPdtUseyn("Y");
					
					productsService.insertPdtExcel(pdtDto);
				}
			}
			
			return "SUCCESS";
			
		}catch (Exception e) {
			log.error(e.toString());
			
			return "FAIL";
		}
	}
	
	@Transactional
	@ResponseBody
	@PostMapping("/delItems")
	public String pdtDelete(@RequestParam("sendData") String pdtCode) {
		
		//products에서 삭제
		productsService.deletePdtCode(pdtCode);
		
		//bom에서 삭제
		bomInfoService.deleteChildItemCode(pdtCode);
		
		return "SUCCESS";
	}
	
	//삭제 전 BOM의 상위 코드가 있는지 체크
	@ResponseBody
	@GetMapping("/checkBomChildCode")
	public List<String> checkBomChildCode(@RequestParam("sendData") String pdtCode) {
		
		List<String> bomChildCodeCheck = productsService.selectCheckBomChildCode(pdtCode);

		return bomChildCodeCheck;
	}
}
