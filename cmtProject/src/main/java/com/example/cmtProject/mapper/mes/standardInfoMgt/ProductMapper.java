package com.example.cmtProject.mapper.mes.standardInfoMgt;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.cmtProject.dto.mes.standardInfoMgt.BomInfoDTO;
import com.example.cmtProject.dto.mes.standardInfoMgt.ProductTotalDTO;
import com.example.cmtProject.dto.mes.standardInfoMgt.ProductsDTO;
import com.example.cmtProject.dto.mes.standardInfoMgt.ProductsEditDTO;
import com.example.cmtProject.entity.mes.standardInfoMgt.Products;

@Mapper
public interface ProductMapper {
	
	// 전체 조회
    public List<Products> list() throws Exception;
    
    // 선택 조회
    public Products select(String pdtCode) throws Exception;
    
    // 등록
    public int insert(Products product) throws Exception;
    
    // 수정
    public int update(Products product) throws Exception;
    
    // 삭제
    public int delete(int pdtCode) throws Exception;

    //기준 정보 상품/BOM 페이지에서 더블 클릭으로 그리드 바로 수정
	public int pdtMainUpdate(ProductsEditDTO pdtEditDto);

	//기준 정보 상품/BOM 페이지에서 상품 메인 그리드에 출력할 데이터
	public List<ProductTotalDTO> getProductTotalList();

	//FP의 현재 입력된 최대값
	public String selectMaxFP();

	//WIP의 현재 입력된 최대값
	public String selectMaxWIP();

	//Excel파일 DB에 저장
	public void insertPdtExcel(ProductsDTO pdtDto);

	//BOM 등록시 상위코드 목록
	public List<String> selectChildItemCodePdt();

	//BOM 등록시 아위코드 목록
	public List<String> selectParentItemCodePdt();

	public List<String> selectCheckBomChildCode(String pdtCode);

	public void deletePdtCode(String pdtCode);
}
