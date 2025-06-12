package br.com.jdo.taxone.mapper.interfaces.repository;

import java.util.List;

import br.com.jdo.taxone.mapper.domain.entity.DSTableDomain;
import br.com.jdo.taxone.mapper.domain.enums.DataSourceType;
import br.com.jdo.taxone.mapper.infrastructure.entity.DSTable;

public interface DSTableRepository {

    List<DSTable> findBydataSourceConfigurationDataSourceType(DataSourceType dataSourceType);

    DSTableDomain findFirstBydataSourceConfigurationIdAndName(Integer dataSourceConfigId, String name);

    List<DSTable> findAll();

    void save(DSTableDomain dst);

}
