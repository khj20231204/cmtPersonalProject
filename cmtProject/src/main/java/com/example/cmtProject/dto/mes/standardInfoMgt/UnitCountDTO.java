package com.example.cmtProject.dto.mes.standardInfoMgt;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitCountDTO {

	private String cmnCode; 
	private String cmnDetailName; 
	private String cmnDetailContent;
}
