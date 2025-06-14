package br.com.jdo.taxone.mapper.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.jdo.taxone.mapper.infrastructure.entity.SAFXColumn;

@Repository
public interface JPASAFXColumnRepository extends JpaRepository<SAFXColumn, Integer>{

    List<SAFXColumn> findBysafxTableId(Integer id);

    @Modifying
    @Query("update SAFXColumn s set s.dsColumn.id = :dsColumnId where s.id = :id")
    void updateSAFXColumn(@Param("id") Integer id, @Param("dsColumnId") Integer dsColumnId);

    SAFXColumn findFirstBysafxTableIdAndName(Integer id, String name);

}
