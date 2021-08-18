package com.bootcamp.banca.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bootcamp.banca.model.CreditEnterprise;

import reactor.core.publisher.Mono;

public interface CreditEnterpriseRepository extends ReactiveMongoRepository<CreditEnterprise, String> {
	public Mono<CreditEnterprise> findByEnterpriseRuc(String ruc);
}