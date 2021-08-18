package com.bootcamp.banca.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.banca.entity.Credit;
import com.bootcamp.banca.entity.Enterprise;
import com.bootcamp.banca.model.CreditEnterprise;
import com.bootcamp.banca.repository.CreditEnterpriseRepository;
import com.bootcamp.banca.service.CreditEnterpriseService;
import com.bootcamp.banca.util.Util;
import com.bootcamp.banca.util.Webclients;

import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Date;

@Service
public class CreditEnterpriseServiceImpl implements CreditEnterpriseService {

    @Autowired
    private CreditEnterpriseRepository creditEnterpriseRepository;

    @Autowired
    private Webclients webclients;

    @Override
    public Mono<CreditEnterprise> createCredit(String ruc, Double amountCredit) {
        return webclients.getClientEnterprise(ruc)
                .flatMap(stringObjectMap -> {
                    Enterprise enterprise = new Enterprise( (String) stringObjectMap.get("ruc"),
                            (String)stringObjectMap.get("profile"));
                    Credit credits = new Credit(Util.generateCreditNumber(), amountCredit,
                            0.3, true);

                    return creditEnterpriseRepository.findByEnterpriseRuc(ruc)
                            .flatMap(creditEnterpriseEntity -> {
                                creditEnterpriseEntity.getCredits().add(credits);
                                creditEnterpriseEntity.setModifiedAt(new Date());

                                return creditEnterpriseRepository.save(creditEnterpriseEntity);
                            })
                            .switchIfEmpty(creditEnterpriseRepository.save(new CreditEnterprise(
                                    enterprise, Arrays.asList(credits), new Date())));
                });
    }

    @Override
    public Mono<CreditEnterprise> getCreditsRuc(String ruc) {
        return creditEnterpriseRepository.findByEnterpriseRuc(ruc);
    }
}