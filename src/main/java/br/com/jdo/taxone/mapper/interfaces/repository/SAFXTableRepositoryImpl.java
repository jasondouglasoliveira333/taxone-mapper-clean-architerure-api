package br.com.jdo.taxone.mapper.interfaces.repository;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.jdo.taxone.mapper.domain.entity.PageResponse;
import br.com.jdo.taxone.mapper.domain.entity.SAFXTableDomain;
import br.com.jdo.taxone.mapper.infrastructure.entity.SAFXTable;
import br.com.jdo.taxone.mapper.infrastructure.persistence.JPASAFXTableRepository;
import br.com.jdo.taxone.mapper.interfaces.mapper.SAFXTableMapper;

public class SAFXTableRepositoryImpl implements SAFXTableRepository{
    
    JPASAFXTableRepository safxTableRepository;

    public SAFXTableRepositoryImpl(JPASAFXTableRepository safxTableRepository) {
        this.safxTableRepository = safxTableRepository;
    }

    public SAFXTableDomain findByName(String tableName) {
        return SAFXTableMapper.map(safxTableRepository.findByName(tableName));
    }

    public PageResponse<SAFXTableDomain> findByNameAndAssociated(String name, Boolean justAssociated, Integer page, Integer size){
        Page<SAFXTable> sPage = safxTableRepository.findByNameAndAssociated(name, justAssociated, PageRequest.of(page, size));
        PageResponse<SAFXTableDomain> srResponse = new PageResponse<>();
        srResponse.setContent(sPage.getContent().stream().map(SAFXTableMapper::map).collect(Collectors.toList()));
        srResponse.setTotalPages(sPage.getTotalPages());
        return srResponse;
    }

    public void updateDSTable(Integer id, Integer dsTableId) {
        safxTableRepository.updateDSTable(id, dsTableId);
    }

    public SAFXTableDomain getOne(Integer id) {
        return SAFXTableMapper.map(safxTableRepository.getOne(id));
    }

}
