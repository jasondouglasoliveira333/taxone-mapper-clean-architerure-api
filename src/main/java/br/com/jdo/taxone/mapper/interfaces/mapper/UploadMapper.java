package br.com.jdo.taxone.mapper.interfaces.mapper;

import br.com.jdo.taxone.mapper.domain.entity.UploadDomain;
import br.com.jdo.taxone.mapper.infrastructure.entity.Upload;

public class UploadMapper {

    public static UploadDomain map(Upload upload) {
        UploadDomain uDTO = new UploadDomain();
        uDTO.setId(upload.getId());
        uDTO.setFileName(upload.getFileName());
        uDTO.setLayoutVersion(upload.getLayoutVersion());
        uDTO.setCreationDate(upload.getCreationDate());
        uDTO.setStatus(upload.getStatus());
        uDTO.setUserName(upload.getUser().getName());
        return uDTO;
    }
}
