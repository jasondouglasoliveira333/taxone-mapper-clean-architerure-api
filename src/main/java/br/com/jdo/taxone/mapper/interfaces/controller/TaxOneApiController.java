package br.com.jdo.taxone.mapper.interfaces.controller;

import br.com.jdo.taxone.mapper.domain.entity.TaxOneApiDomain;
import br.com.jdo.taxone.mapper.usecase.TaxOneApiUseCase;

public class TaxOneApiController {
    
    private TaxOneApiUseCase taxOneApiUseCase; 

    public Object get(Integer id){
        return taxOneApiUseCase.getOne(id);
    }

    public Object save(TaxOneApiDomain toDTO){
        taxOneApiUseCase.save(toDTO);
        return null;
    }

}
