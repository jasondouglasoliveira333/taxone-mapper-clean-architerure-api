package br.com.jdo.taxone.mapper.infrastructure.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jdo.taxone.mapper.interfaces.controller.DSTableController;
import br.com.jdo.taxone.mapper.usecase.MatcherUseCase;

@CrossOrigin
@RestController
@RequestMapping("dsTables")
public class DSTableControllerImpl extends DSTableController{
    
    private Logger log = LoggerFactory.getLogger(DSTableControllerImpl.class);
    
    @Autowired
    private MatcherUseCase matcherUseCase; 

    public MatcherUseCase matcherUseCase() {
        return matcherUseCase;
    }
    
    @GetMapping
    public ResponseEntity<?> list(){
        try {
            Object dsTables = super.list();
            return ResponseEntity.ok().body(dsTables);
        }catch (Exception e) {
            log.error("Error obtendo a definicao da tabela", e);
            return ResponseEntity.badRequest().build();
        }
    }
    
            
    @GetMapping("{id}/dsColumns")
    public ResponseEntity<?> listDSColumns(@PathVariable("id") Integer id, 
            @RequestParam(name="page", defaultValue = "0") Integer page, 
            @RequestParam(name="size", defaultValue = "10") Integer size) {
        try {
            Object dsColumns = super.listDSColumns(id, page, size);
            return ResponseEntity.ok().body(dsColumns);
        }catch (Exception e) {
            log.error("Error obtendo a definicao da tabela", e);
            return ResponseEntity.badRequest().build();
        }
    }
}
