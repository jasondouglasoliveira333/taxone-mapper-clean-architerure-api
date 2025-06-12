package br.com.jdo.taxone.mapper.infrastructure.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jdo.taxone.mapper.domain.entity.SAFXColumnUpdateDomain;
import br.com.jdo.taxone.mapper.interfaces.controller.SAFXTableController;
import br.com.jdo.taxone.mapper.usecase.MatcherUseCase;

@CrossOrigin
@RestController
@RequestMapping("safxTables")
public class SAFXTableControllerImpl extends SAFXTableController{
    
    private Logger log = LoggerFactory.getLogger(SAFXTableControllerImpl.class);
    
    @Autowired
    private MatcherUseCase matcherUseCase; 
    
    public MatcherUseCase matcherUseCase() {
        return matcherUseCase;
    }

    @GetMapping
    public Object list(@RequestParam(name="tableName", required = false) String tableName, 
            @RequestParam(name="justAssociated", defaultValue = "false") Boolean justAssociated,
            @RequestParam(name="page", defaultValue = "0") Integer page, 
            @RequestParam(name="size", defaultValue = "10") Integer size){
        try {
            Object sPage = super.list(tableName, justAssociated, page, size);
            return ResponseEntity.ok(sPage);
        }catch(Exception e) {
            log.error("Erro listando as tablelas safx", e);
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("{id}")
    public Object get(@PathVariable("id") Integer id){
        try {
            Object safxTable = super.get(id);
            return ResponseEntity.ok().body(safxTable);
        }catch (Exception e) {
            log.error("Error obtendo a definicao da tabela", e);
            return ResponseEntity.badRequest().build();
        }
    }
    
            
    @GetMapping("{id}/safxColumns")
    public Object listSAFXColumns(@PathVariable("id") Integer id, @RequestParam(name="associated", defaultValue = "false") Boolean associated) {
        try {
            Object safxColumns = super.listSAFXColumns(id, associated);
            return ResponseEntity.ok().body(safxColumns);
        }catch (Exception e) {
            log.error("Error obtendo a definicao da tabela", e);
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("{id}/safxColumns")
    @Transactional
    public Object updateSAFXColumns(@RequestBody ArrayList<SAFXColumnUpdateDomain> safxColumns){
        try {
            super.updateSAFXColumns(safxColumns);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            log.error("Error atualizando as safx columns", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("{id}/dsTables/{dsTableId}")
    @Transactional
    public Object updateSAFXTable(@PathVariable("id") Integer id, @PathVariable("dsTableId") Integer dsTableId){
        try {
            System.out.println("dsTableId:" + dsTableId);
            super.updateSAFXTable(id, dsTableId);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            log.error("Error atualizando as safx columns", e);
            return ResponseEntity.badRequest().build();
        }
    }
}
