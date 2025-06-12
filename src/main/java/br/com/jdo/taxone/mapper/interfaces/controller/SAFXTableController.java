package br.com.jdo.taxone.mapper.interfaces.controller;

import java.util.ArrayList;

import br.com.jdo.taxone.mapper.domain.entity.SAFXColumnUpdateDomain;
import br.com.jdo.taxone.mapper.usecase.MatcherUseCase;

public class SAFXTableController {
    
    private MatcherUseCase matcherUseCase; 
    
    public MatcherUseCase matcherUseCase() {
        return matcherUseCase;
    }

    public Object list(String tableName, Boolean justAssociated, Integer page, Integer size){
        return matcherUseCase().findAllSafx(tableName, justAssociated, page, size);
    }
    
    public Object get(Integer id){
        return matcherUseCase().getSAFXTable(id);
    }
    
    public Object listSAFXColumns(Integer id, Boolean associated) {
        return matcherUseCase().getSAFXColumns(id, associated);
    }
    
    public Object updateSAFXColumns(ArrayList<SAFXColumnUpdateDomain> safxColumns){
        System.out.println("safxColumns.size():" + safxColumns.size());
        matcherUseCase().updateSAFXColumns(safxColumns);
        return null;
    }

    public Object updateSAFXTable(Integer id, Integer dsTableId){
        System.out.println("dsTableId:" + dsTableId);
        matcherUseCase().updateSAFXTable(id, dsTableId);
        return null;
    }
}
