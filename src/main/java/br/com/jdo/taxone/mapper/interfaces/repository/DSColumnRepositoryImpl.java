package br.com.jdo.taxone.mapper.interfaces.repository;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.jdo.taxone.mapper.domain.entity.DSColumnDomain;
import br.com.jdo.taxone.mapper.domain.entity.PageResponse;
import br.com.jdo.taxone.mapper.infrastructure.entity.DSColumn;
import br.com.jdo.taxone.mapper.infrastructure.entity.DSTable;
import br.com.jdo.taxone.mapper.interfaces.mapper.DSColumnMapper;
import br.com.jdo.taxone.mapper.interfaces.mapper.DSTableMapper;

public class DSColumnRepositoryImpl implements DSColumnRepository{
    
    br.com.jdo.taxone.mapper.infrastructure.persistence.JPADSColumnRepository dsColumnRepository;

    public DSColumnRepositoryImpl(br.com.jdo.taxone.mapper.infrastructure.persistence.JPADSColumnRepository dsColumnRepository) {
        this.dsColumnRepository = dsColumnRepository;
    }

    public PageResponse<DSColumnDomain> findBydsTableId(Integer id, Integer page, Integer size){
        PageResponse<DSColumnDomain> sfResponse = new PageResponse<>();
        Page<DSColumn> dcPage = dsColumnRepository.findBydsTableId(id, PageRequest.of(page, size));
        sfResponse.setTotalPages(dcPage.getTotalPages());
        sfResponse.setContent(dcPage.getContent().stream().map(DSColumnMapper::map).collect(Collectors.toList()));
        return sfResponse; 
    }

    public DSColumnDomain findFirstBydsTableIdAndName(Integer dsTableId, String name) {
        DSColumn ds = dsColumnRepository.findFirstBydsTableIdAndName(dsTableId, name);
        return DSColumnMapper.map(ds);
    }

    public void save(DSColumnDomain dsc) {
        DSColumn dscEntity = DSColumnMapper.map(dsc);
        DSTable dstEntity = DSTableMapper.map(dsc.getDsTable());
        dscEntity.setDsTable(dstEntity);
        dsColumnRepository.save(dscEntity);
    }

}
