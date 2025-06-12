package br.com.jdo.taxone.mapper.interfaces.repository;

import br.com.jdo.taxone.mapper.domain.entity.CriteriaDomain;

public interface CriteriaRepository {

    void save(CriteriaDomain c);

    void deleteById(Integer cId);

}
