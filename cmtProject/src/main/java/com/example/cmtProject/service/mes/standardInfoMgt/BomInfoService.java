package com.example.cmtProject.service.mes.standardInfoMgt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cmtProject.dto.mes.standardInfoMgt.BomEditDTO;
import com.example.cmtProject.dto.mes.standardInfoMgt.BomInfoDTO;
import com.example.cmtProject.dto.mes.standardInfoMgt.BomInfoTotalDTO;
import com.example.cmtProject.dto.mes.standardInfoMgt.UnitCountDTO;
import com.example.cmtProject.mapper.mes.standardInfoMgt.BomInfoMapper;

import jakarta.validation.Valid;

@Service
public class BomInfoService {

	@Autowired
	private BomInfoMapper bomInfoMapper;
	
	public List<BomInfoTotalDTO> getBomInfoTotalList(String pdtCode) {
		// TODO Auto-generated method stub
		return bomInfoMapper.getBomInfoTotalList(pdtCode);
	}

	public int bomMainUpdate(BomEditDTO bomEditDto) {
		
		return bomInfoMapper.bomMainUpdate(bomEditDto);
	}

	public List<BomInfoDTO> bomAll() {
		return bomInfoMapper.bomAll();
	}

	public List<UnitCountDTO> selectBomUnitCount() {
		return bomInfoMapper.selectBomUnitCount();
	}

	public int selectCheckPdtList(String value) {
		return bomInfoMapper.selectCheckPdtList(value);
	}

	public void insertBomInfo(@Valid BomInfoDTO bomInfoDto) {
		bomInfoMapper.insertBomInfo(bomInfoDto);
	}

	public void deleteChildItemCode(String pdtCode) {
		bomInfoMapper.deleteChildItemCode(pdtCode);
	}

}
