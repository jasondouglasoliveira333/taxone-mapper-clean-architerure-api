package br.com.jdo.taxone.mapper.infrastructure.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jdo.taxone.mapper.infrastructure.entity.DSColumn;

@Repository
public interface JPADSColumnRepository extends JpaRepository<DSColumn, Integer>{

    Page<DSColumn> findBydsTableId(Integer id, Pageable page);

    DSColumn findFirstBydsTableIdAndName(Integer dsTableId, String name);

}
