package br.com.jdo.taxone.mapper.interfaces.controller;

import br.com.jdo.taxone.mapper.domain.entity.UserDomain;
import br.com.jdo.taxone.mapper.usecase.UserUseCase;

public class UserController {

    private UserUseCase userUseCase;
    
    public UserUseCase userUseCase() {
        return userUseCase;
    }

    public Object list(Integer page, Integer size){
        return userUseCase().findAll(page, size);
    }
    
    public Object get(Integer id){
        return userUseCase().getOne(id);
    }
    
    public Object save(UserDomain user){
        userUseCase().save(user);
        return null;
    }

    public Object deleteById(Integer id){
        userUseCase().deleteById(id);
        return null;
    }
    
}
