package br.com.jdo.taxone.mapper.interfaces.repository;

import java.util.List;

import br.com.jdo.taxone.mapper.domain.entity.DataSourceConfigurationDomain;
import br.com.jdo.taxone.mapper.domain.enums.DataSourceType;

public interface DataSourceConfigRepository {

    DataSourceConfigurationDomain findByDataSourceType(DataSourceType dataSourceType);

    List<DataSourceConfigurationDomain> findAll();

    DataSourceConfigurationDomain getOne(Integer dataSourceConfigId);

    DataSourceConfigurationDomain save(DataSourceConfigurationDomain dsc);

}
