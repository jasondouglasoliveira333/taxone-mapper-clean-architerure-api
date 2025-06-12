package br.com.jdo.taxone.mapper.infrastructure.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.jdo.taxone.mapper.infrastructure.entity.SAFXTable;

@Repository
public interface JPASAFXTableRepository extends JpaRepository<SAFXTable, Integer>{

    SAFXTable findByName(String tableName);

    @Query("select s from SAFXTable s where (s.name like :name or :name is null) and (:justAssociated = true and dsTable is not null or :justAssociated = false)") 
    Page<SAFXTable> findByNameAndAssociated(@Param("name") String name, @Param("justAssociated") Boolean justAssociated, Pageable page);

    @Modifying
    @Query("update SAFXTable s set s.dsTable.id = :dsTableId where s.id = :id")
    void updateDSTable(@Param("id") Integer id,@Param("dsTableId") Integer dsTableId);

}
