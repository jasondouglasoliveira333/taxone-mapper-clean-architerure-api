package br.com.jdo.taxone.mapper.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.jdo.taxone.mapper.domain.enums.UploadStatus;
import br.com.jdo.taxone.mapper.infrastructure.entity.Upload;

@Repository
public interface JPAUploadRepository extends JpaRepository<Upload, Integer>{

    @Modifying
    @Query("update Upload set status = :status")
    void updateStatus(@Param("status") UploadStatus status);

}
