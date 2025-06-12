package br.com.jdo.taxone.mapper.interfaces.repository;

import br.com.jdo.taxone.mapper.domain.entity.PageResponse;
import br.com.jdo.taxone.mapper.domain.entity.UploadDomain;
import br.com.jdo.taxone.mapper.domain.enums.UploadStatus;

public interface UploadRepository {

    void updateStatus(UploadStatus status);

    PageResponse<UploadDomain> findAll(Integer page, Integer size);

}
