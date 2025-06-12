package br.com.jdo.taxone.mapper.infrastructure.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jdo.taxone.mapper.domain.entity.UserDomain;
import br.com.jdo.taxone.mapper.interfaces.controller.UserController;
import br.com.jdo.taxone.mapper.usecase.UserUseCase;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserControllerImpl extends UserController{

    private static final Logger log = LoggerFactory.getLogger(UserControllerImpl.class);
    
    @Autowired
    private UserUseCase userUseCase;
    
    public UserUseCase userUseCase() {
        return userUseCase;
    }

    @GetMapping
    public Object list(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
        try {
            Object prUser = super.list(page, size);
            return ResponseEntity.ok(prUser);
        }catch (Exception e) {
            log.error("Erro listando os usuarios", e);
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("{id}")
    public Object get(@PathVariable("id") Integer id){
        try {
            UserDomain uDTO = userUseCase.getOne(id);
            return ResponseEntity.ok(uDTO);
        }catch (Exception e) {
            log.error("Erro listando os usuarios", e);
            return ResponseEntity.badRequest().build();
        }
    }
    
    
    @PostMapping
    public Object save(@RequestBody UserDomain user){
        try {
            userUseCase.save(user);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            log.error("Erro salvando o usuario", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{id}")
    public Object deleteById(@PathVariable("id") Integer id){
        try {
            userUseCase.deleteById(id);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            log.error("Erro excluindo o usuario", e);
            return ResponseEntity.badRequest().build();
        }
    }
    
}
