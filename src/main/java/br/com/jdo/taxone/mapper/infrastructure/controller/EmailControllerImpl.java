package br.com.jdo.taxone.mapper.infrastructure.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jdo.taxone.mapper.domain.entity.EmailDomain;
import br.com.jdo.taxone.mapper.interfaces.controller.EmailController;
import br.com.jdo.taxone.mapper.usecase.EmailUseCase;


@CrossOrigin
@RestController
@RequestMapping("emails")
public class EmailControllerImpl extends EmailController{
    
    private Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private EmailUseCase emailUseCase;

    public EmailUseCase emailUseCase() {
        return emailUseCase;
    }

    @GetMapping
    public ResponseEntity<?> list(@RequestParam(name="page", defaultValue = "0") Integer page, 
            @RequestParam(name="size", defaultValue = "10") Integer size){
        try {
            Object uPage = super.list(page, size);
            return ResponseEntity.ok(uPage);
        }catch(Exception e) {
            log.error("Erro listando os email", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    @Transactional
    public Object save(@RequestBody ArrayList<EmailDomain> emails){
        try {
            super.save(emails);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            log.error("Erro salvando o email", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{emailId}")
    @Transactional
    public Object delete(@PathVariable("emailId") Integer emailId){
        try {
            super.delete(emailId);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            log.error("Erro delete o email", e);
            return ResponseEntity.badRequest().build();
        }
    }

}
