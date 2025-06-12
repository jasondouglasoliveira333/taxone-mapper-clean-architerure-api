package br.com.jdo.taxone.mapper.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jdo.taxone.mapper.infrastructure.entity.Criteria;

@Repository
public interface JPACriteriaRepository extends JpaRepository<Criteria, Integer>{

}
