package com.dpc.dto;

import lombok.Data;

@Data
public class CodepostaleDto {

	private String nom;
	private long code;
	private VilleDto ville;

}
