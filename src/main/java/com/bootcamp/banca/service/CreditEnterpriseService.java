package com.bootcamp.banca.service;

import com.bootcamp.banca.model.CreditEnterprise;

import reactor.core.publisher.Mono;

public interface CreditEnterpriseService {
	public Mono<CreditEnterprise> createCredit(String ruc, Double amountCredit);

	public Mono<CreditEnterprise> getCreditsRuc(String ruc);
}