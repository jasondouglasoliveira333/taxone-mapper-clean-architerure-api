package br.com.jdo.taxone.mapper.interfaces.repository;

import br.com.jdo.taxone.mapper.domain.entity.PageResponse;
import br.com.jdo.taxone.mapper.domain.entity.SAFXTableDomain;

public interface SAFXTableRepository {

    SAFXTableDomain findByName(String tableName);

    PageResponse<SAFXTableDomain> findByNameAndAssociated(String name, Boolean justAssociated, Integer page, Integer size);

    void updateDSTable(Integer id, Integer dsTableId);

    SAFXTableDomain getOne(Integer id);

}
