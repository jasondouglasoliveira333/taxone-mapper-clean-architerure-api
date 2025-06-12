package br.com.jdo.taxone.mapper.infrastructure.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jdo.taxone.mapper.domain.entity.TaxOneApiDomain;
import br.com.jdo.taxone.mapper.interfaces.controller.TaxOneApiController;
import br.com.jdo.taxone.mapper.usecase.TaxOneApiUseCase;

@CrossOrigin
@RestController
@RequestMapping("taxoneapis")
public class TaxOneApiControllerImpl extends TaxOneApiController{
    
    private static final Logger log = LoggerFactory.getLogger(TaxOneApiControllerImpl.class);
    
    @Autowired
    private TaxOneApiUseCase taxOneApiUseCase;
    
    public TaxOneApiUseCase taxOneApiUseCase() {
        return taxOneApiUseCase;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id){
        try {
            Object toDTO = super.get(id);
            return ResponseEntity.ok(toDTO);
        }catch (Exception e) {
            log.error("Erro obtendo o taxone api config", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public Object save(@RequestBody TaxOneApiDomain toDTO){
        try {
            super.save(toDTO);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            log.error("Erro obtendo o taxone api config", e);
            return ResponseEntity.badRequest().build();
        }
    }

}
