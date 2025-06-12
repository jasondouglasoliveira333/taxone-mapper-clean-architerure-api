package br.com.jdo.taxone.mapper.infrastructure.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.jdo.taxone.mapper.domain.entity.POCUser;
import br.com.jdo.taxone.mapper.interfaces.controller.UploadController;
import br.com.jdo.taxone.mapper.usecase.UploadUseCase;

@CrossOrigin
@RestController
@RequestMapping("uploads")
public class UploadControllerImpl extends UploadController{
    
    private Logger log = LoggerFactory.getLogger(UploadControllerImpl.class);
    
    @Autowired 
    private UploadUseCase uploadUseCase;
    
    public UploadUseCase uploadUseCase() {
        return uploadUseCase;
    }

    @PostMapping
    public Object upload(@RequestParam(name="layoutVersion") String layoutVersion,  
            @RequestParam(name="file") MultipartFile file){
        try {
            log.info("In UploadController.upload:" + file.getOriginalFilename() + " - layoutVersion:" + layoutVersion);
            super.upload(layoutVersion, file.getOriginalFilename(), file.getBytes());
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            log.error("Erro efetuando parser do arquivo", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public Object list(@RequestParam(name="page", defaultValue = "0") Integer page, 
            @RequestParam(name="size", defaultValue = "10") Integer size){
        try {
            POCUser user = (POCUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Object uPage = super.list(page, size, user);
            return ResponseEntity.ok(uPage);
        }catch(Exception e) {
            log.error("Erro listando os uploads", e);
            return ResponseEntity.badRequest().build();
        }
    }
}
