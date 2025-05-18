package com.example.cmtProject.mapper.mes.standardInfoMgt;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.cmtProject.dto.mes.standardInfoMgt.BomEditDTO;
import com.example.cmtProject.dto.mes.standardInfoMgt.BomInfoDTO;
import com.example.cmtProject.dto.mes.standardInfoMgt.BomInfoTotalDTO;
import com.example.cmtProject.dto.mes.standardInfoMgt.UnitCountDTO;

import jakarta.validation.Valid;

@Mapper
public interface BomInfoMapper {

	public List<BomInfoTotalDTO> getBomInfoTotalList(String pdtCode);

	public int bomMainUpdate(BomEditDTO bomEditDto);

	public List<BomInfoDTO> bomAll();

	public List<UnitCountDTO> selectBomUnitCount();

	public int selectCheckPdtList(String value);

	public void insertBomInfo(@Valid BomInfoDTO bomInfoDto);

	public void deleteChildItemCode(String pdtCode);

}
