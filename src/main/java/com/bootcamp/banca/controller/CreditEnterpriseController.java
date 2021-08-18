package com.bootcamp.banca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bootcamp.banca.service.CreditEnterpriseService;

import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/credit/e-customer")
public class CreditEnterpriseController
{
    @Autowired
    private CreditEnterpriseService creditEnterpriseService;

    @PostMapping("")
    public Mono<ResponseEntity<Map<String, Object>>> creteCreditEnterprise(@RequestParam String ruc,
                                                                           @RequestParam Double amountCredit){
        return creditEnterpriseService.createCredit(ruc, amountCredit)
                .flatMap(creditEnterpriseEntity -> {
                    Map<String, Object> response = new HashMap<>();

                    response.put("message", "Se registro el nuevo credito a la empresa " + ruc);
                    response.put("status", HttpStatus.OK.value());
                    response.put("body", creditEnterpriseEntity);

                    return Mono.just(ResponseEntity.ok().body(response));
                })
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping("")
    public Mono<ResponseEntity<Map<String, Object>>> getCredits(@RequestParam String ruc){
        return creditEnterpriseService.getCreditsRuc(ruc)
                .flatMap(creditEnterpriseEntity -> {
                    Map<String, Object> response = new HashMap<>();

                    response.put("message", "Se obtiene el cliente " + ruc);
                    response.put("status", HttpStatus.OK.value());
                    response.put("body", creditEnterpriseEntity);

                    return Mono.just(ResponseEntity.ok().body(response));
                });
    }
}