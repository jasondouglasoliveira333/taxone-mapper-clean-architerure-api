package br.com.jdo.taxone.mapper.interfaces.repository;

import java.util.List;

import br.com.jdo.taxone.mapper.domain.entity.SAFXColumnDomain;

public interface SAFXColumnRepository {

    List<SAFXColumnDomain> findBysafxTableId(Integer id);

    void updateSAFXColumn(Integer id, Integer dsColumnId);

    SAFXColumnDomain findFirstBysafxTableIdAndName(Integer id, String name);

}
