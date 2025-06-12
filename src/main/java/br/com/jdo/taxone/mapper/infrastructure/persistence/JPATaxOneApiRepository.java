package br.com.jdo.taxone.mapper.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jdo.taxone.mapper.infrastructure.entity.TaxOneApi;

@Repository
public interface JPATaxOneApiRepository extends JpaRepository<TaxOneApi, Integer>{

}
