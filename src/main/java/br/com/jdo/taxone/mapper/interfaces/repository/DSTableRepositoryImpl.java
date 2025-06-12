package br.com.jdo.taxone.mapper.interfaces.repository;

import java.util.List;

import br.com.jdo.taxone.mapper.domain.entity.DSTableDomain;
import br.com.jdo.taxone.mapper.domain.enums.DataSourceType;
import br.com.jdo.taxone.mapper.infrastructure.entity.DSTable;
import br.com.jdo.taxone.mapper.interfaces.mapper.DSTableMapper;

public class DSTableRepositoryImpl implements DSTableRepository{
    
    br.com.jdo.taxone.mapper.infrastructure.persistence.JPADSTableRepository dsTableRepository;

    public DSTableRepositoryImpl(br.com.jdo.taxone.mapper.infrastructure.persistence.JPADSTableRepository dsTableRepository) {
        this.dsTableRepository = dsTableRepository;
    }

    public List<DSTable> findBydataSourceConfigurationDataSourceType(DataSourceType dataSourceType){
        return dsTableRepository.findBydataSourceConfigurationDataSourceType(dataSourceType);
    }

    public DSTableDomain findFirstBydataSourceConfigurationIdAndName(Integer dataSourceConfigId, String name) {
        return DSTableMapper.map(dsTableRepository.findFirstBydataSourceConfigurationIdAndName(dataSourceConfigId, name));
    }

    public List<DSTable> findAll(){
        return dsTableRepository.findAll();
    }

    public void save(DSTableDomain dst) {
        DSTable dsTable = dsTableRepository.save(DSTableMapper.map(dst));
        dst.setId(dsTable.getId());
    }

}
