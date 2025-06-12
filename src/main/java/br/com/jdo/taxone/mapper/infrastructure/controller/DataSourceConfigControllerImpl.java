package br.com.jdo.taxone.mapper.infrastructure.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jdo.taxone.mapper.domain.entity.DataSourceConfigurationDomain;
import br.com.jdo.taxone.mapper.interfaces.controller.DataSourceConfigController;
import br.com.jdo.taxone.mapper.usecase.DataSourceConfigUseCase;
import br.com.jdo.taxone.mapper.usecase.MatcherUseCase;

@CrossOrigin
@RestController
@RequestMapping("dataSourceConfigs")
public class DataSourceConfigControllerImpl extends DataSourceConfigController{

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DataSourceConfigUseCase dataSourceConfigUseCase;
    
    @Autowired
    private MatcherUseCase matcherUseCase;
    
    public DataSourceConfigUseCase dataSourceConfigUseCase() {
        return this.dataSourceConfigUseCase;
    }
    
    public MatcherUseCase matcherUseCase() {
        return this.matcherUseCase;
    }


    @GetMapping
    public Object list(){
        try {
            Object dssDomain = super.list();
            return ResponseEntity.ok().body(dssDomain);
        }catch (Exception e) {
            log.error("Error obtendo a definicao do data source", e);
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("{dataSourceType}")
    public Object get(@PathVariable("dataSourceType") String dataSourceType){
        try {
            Object ds = super.get(dataSourceType);
            return ResponseEntity.ok().body(ds);
        }catch (Exception e) {
            log.error("Error obtendo a definicao do data source", e);
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("{dataSourceType}/dsTables")
    public Object listDsTables(@PathVariable("dataSourceType") String dataSourceType){
        try {
            Object dsTs = super.listDsTables(dataSourceType);
            return ResponseEntity.ok().body(dsTs);
        }catch (Exception e) {
            log.error("Error obtendo a definicao da tabela do data source", e);
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("{dataSourceType}/dsTables/{dsTableId}/dsColumns")
    public Object listDsColumns(@PathVariable("dsTableId") Integer dsTableId,
            @RequestParam(name="page", defaultValue = "0") Integer page, 
            @RequestParam(name="size", defaultValue = "10") Integer size){
        try {
            Object dsCPage = super.listDsColumns(dsTableId, page, size);
            return ResponseEntity.ok().body(dsCPage);
        }catch (Exception e) {
            log.error("Error obtendo a definicao das colunas da tabela do data source", e);
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("{dataSourceType}/metadata")
    public Object getMetadata(@PathVariable("dataSourceType") String dataSourceType,  @RequestBody DataSourceConfigurationDomain dataSourceDTO){
        try {
            super.getMetadata(dataSourceType, dataSourceDTO);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            log.error("Error obtendo a definicao das colunas da tabela do data source", e);
            return ResponseEntity.badRequest().build();
        }

    }
    
    
    @PostMapping("{dataSourceType}")
    @Transactional
    public Object saveDataSourrce(@PathVariable("dataSourceType") String dataSourceType,  @RequestBody DataSourceConfigurationDomain dataSourceDTO){
        try {
            super.saveDataSourrce(dataSourceType, dataSourceDTO);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            log.error("Error obtendo a definicao das colunas da tabela do data source", e);
            return ResponseEntity.badRequest().build();
        }

    }

}
