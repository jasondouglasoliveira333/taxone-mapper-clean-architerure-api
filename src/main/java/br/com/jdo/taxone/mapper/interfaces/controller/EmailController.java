package br.com.jdo.taxone.mapper.interfaces.controller;

import java.util.ArrayList;

import br.com.jdo.taxone.mapper.domain.entity.EmailDomain;
import br.com.jdo.taxone.mapper.usecase.EmailUseCase;


public class EmailController {
    
    private EmailUseCase emailUseCase;

    public EmailUseCase emailUseCase() {
        return emailUseCase;
    }
    
    public Object list(Integer page, Integer size){
        return emailUseCase().findAll(page, size)    ;
    }

    public Object save(ArrayList<EmailDomain> emails){
        emails.stream().forEach(email -> {
            emailUseCase().save(email);
        });
        return null;
    }

    public Object delete(Integer emailId){
        emailUseCase().delete(emailId);
        return null;
    }

}
