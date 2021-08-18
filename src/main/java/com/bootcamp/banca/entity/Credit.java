package com.bootcamp.banca.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Credit {
	private String numberCredit;
	private Double amountCredit;
	private Double maintenanceFee;
	private Boolean isActive;
}