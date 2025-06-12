package br.com.jdo.taxone.mapper.interfaces.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.jdo.taxone.mapper.domain.entity.POCUser;
import br.com.jdo.taxone.mapper.domain.entity.PageResponse;
import br.com.jdo.taxone.mapper.domain.entity.UploadDomain;
import br.com.jdo.taxone.mapper.usecase.UploadUseCase;

public class UploadController {
    
    private Logger log = LoggerFactory.getLogger(UploadController.class);
    
    private UploadUseCase uploadUseCase;
    
    public UploadUseCase uploadUseCase() {
        return uploadUseCase;
    }
    
    public Object upload(String layoutVersion, String fileName, byte[] data) throws IOException, Exception{
        log.info("In UploadController.upload:" + fileName + " - layoutVersion:" + layoutVersion);
        uploadUseCase().parseFileAndStore(fileName, layoutVersion, data);
        return null;
    }

    public Object list(Integer page, Integer size, POCUser user){
        log.info("user:" + user);
        PageResponse<UploadDomain> uPage = uploadUseCase().findAll(page, size);
        return uPage;
    }
}
