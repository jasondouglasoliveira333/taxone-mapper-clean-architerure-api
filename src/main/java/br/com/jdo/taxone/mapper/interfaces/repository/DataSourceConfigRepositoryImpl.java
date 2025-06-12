package br.com.jdo.taxone.mapper.interfaces.repository;

import java.util.List;
import java.util.stream.Collectors;

import br.com.jdo.taxone.mapper.domain.entity.DataSourceConfigurationDomain;
import br.com.jdo.taxone.mapper.domain.enums.DataSourceType;
import br.com.jdo.taxone.mapper.infrastructure.persistence.JPADataSourceConfigRepository;
import br.com.jdo.taxone.mapper.interfaces.mapper.DataSourceConfigMapper;

public class DataSourceConfigRepositoryImpl implements DataSourceConfigRepository{
    
    JPADataSourceConfigRepository dataSourceConfigRepository;

    public DataSourceConfigRepositoryImpl(JPADataSourceConfigRepository dataSourceConfigRepository) {
        this.dataSourceConfigRepository = dataSourceConfigRepository;
    }

    public DataSourceConfigurationDomain findByDataSourceType(DataSourceType dataSourceType) {
        return DataSourceConfigMapper.map(dataSourceConfigRepository.findByDataSourceType(dataSourceType));
    }

    public List<DataSourceConfigurationDomain> findAll(){
        return dataSourceConfigRepository.findAll().stream().map(DataSourceConfigMapper::map)
                .collect(Collectors.toList());
    }

    public DataSourceConfigurationDomain getOne(Integer dataSourceConfigId) {
        return DataSourceConfigMapper.map(dataSourceConfigRepository.getOne(dataSourceConfigId));
    }

    public DataSourceConfigurationDomain save(DataSourceConfigurationDomain dsc) {
        return DataSourceConfigMapper.map(dataSourceConfigRepository.save(DataSourceConfigMapper.map(dsc)));
    }

}
