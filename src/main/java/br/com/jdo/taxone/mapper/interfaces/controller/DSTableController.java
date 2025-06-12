package br.com.jdo.taxone.mapper.interfaces.controller;

import br.com.jdo.taxone.mapper.usecase.MatcherUseCase;

public class DSTableController {
    
    private MatcherUseCase matcherUseCase; 

    public MatcherUseCase matcherUseCase() {
        return matcherUseCase;
    }
    
    public Object list(){
        return  matcherUseCase().getDSTables();
    }
    
            
    public Object listDSColumns(Integer id, Integer page, Integer size) {
        return matcherUseCase().getDSColumns(id, page, size);
    }
}
