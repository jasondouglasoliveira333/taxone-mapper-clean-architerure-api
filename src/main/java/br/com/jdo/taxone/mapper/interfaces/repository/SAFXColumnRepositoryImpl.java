package br.com.jdo.taxone.mapper.interfaces.repository;

import java.util.List;
import java.util.stream.Collectors;

import br.com.jdo.taxone.mapper.domain.entity.SAFXColumnDomain;
import br.com.jdo.taxone.mapper.infrastructure.persistence.JPASAFXColumnRepository;
import br.com.jdo.taxone.mapper.interfaces.mapper.SAFXColumnMapper;

public class SAFXColumnRepositoryImpl implements SAFXColumnRepository {
    
    JPASAFXColumnRepository safxColumnRepository;

    public SAFXColumnRepositoryImpl(JPASAFXColumnRepository safxColumnRepository) {
        this.safxColumnRepository = safxColumnRepository;
    }

    public List<SAFXColumnDomain> findBysafxTableId(Integer id){
        return safxColumnRepository.findBysafxTableId(id).stream().map(SAFXColumnMapper::map).collect(Collectors.toList()); 
    }

    public void updateSAFXColumn(Integer id, Integer dsColumnId) {
        safxColumnRepository.updateSAFXColumn(id, dsColumnId);
    }

    public SAFXColumnDomain findFirstBysafxTableIdAndName(Integer id, String name) {
        return SAFXColumnMapper.map(safxColumnRepository.findFirstBysafxTableIdAndName(id, name));
    }

}
