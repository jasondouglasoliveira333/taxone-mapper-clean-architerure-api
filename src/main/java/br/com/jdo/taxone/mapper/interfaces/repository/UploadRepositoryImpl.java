package br.com.jdo.taxone.mapper.interfaces.repository;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.jdo.taxone.mapper.domain.entity.PageResponse;
import br.com.jdo.taxone.mapper.domain.entity.UploadDomain;
import br.com.jdo.taxone.mapper.domain.enums.UploadStatus;
import br.com.jdo.taxone.mapper.infrastructure.entity.Upload;
import br.com.jdo.taxone.mapper.infrastructure.persistence.JPAUploadRepository;
import br.com.jdo.taxone.mapper.interfaces.mapper.UploadMapper;

public class UploadRepositoryImpl implements UploadRepository{
    
    JPAUploadRepository uploadRepository;

    public UploadRepositoryImpl(JPAUploadRepository uploadRepository) {
        this.uploadRepository = uploadRepository;
    }

    public void updateStatus(UploadStatus status) {
        uploadRepository.updateStatus(status);
    }

    public PageResponse<UploadDomain> findAll(Integer page, Integer size){
        Page<Upload> uPage = uploadRepository.findAll(PageRequest.of(page, size));
        PageResponse<UploadDomain> udResponse = new PageResponse<>();
        udResponse.setContent(uPage.getContent().stream().map(UploadMapper::map).collect(Collectors.toList()));
        return udResponse;
    }

}
