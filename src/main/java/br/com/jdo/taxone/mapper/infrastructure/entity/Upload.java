package br.com.jdo.taxone.mapper.infrastructure.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.jdo.taxone.mapper.domain.enums.UploadStatus;



@Entity
public class Upload {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String fileName;
    
    private String layoutVersion;
    
    private LocalDateTime creationDate;
    
    @Enumerated(EnumType.STRING)
    private UploadStatus status;
    
    @ManyToOne
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
