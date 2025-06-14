package br.com.jdo.taxone.mapper.domain.entity;

import java.time.LocalDateTime;

import br.com.jdo.taxone.mapper.domain.enums.UploadStatus;



public class UploadDomain {

    private Long id;
    
    private String fileName;
    
    private String layoutVersion;
    
    private LocalDateTime creationDate;
    
    private UploadStatus status;
    
    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLayoutVersion() {
        return layoutVersion;
    }

    public void setLayoutVersion(String layoutVersion) {
        this.layoutVersion = layoutVersion;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public UploadStatus getStatus() {
        return status;
    }

    public void setStatus(UploadStatus status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
}
