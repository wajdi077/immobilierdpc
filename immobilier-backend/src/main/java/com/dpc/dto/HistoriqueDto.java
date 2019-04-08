package com.dpc.dto;

import lombok.Data;


@Data

public class HistoriqueDto {
	private String discriptif;
	private String date;
	private String action;
	private UtilisteurDto user;

}
