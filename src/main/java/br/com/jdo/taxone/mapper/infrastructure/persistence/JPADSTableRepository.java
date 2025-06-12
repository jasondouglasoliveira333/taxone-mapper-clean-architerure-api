package br.com.jdo.taxone.mapper.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jdo.taxone.mapper.domain.enums.DataSourceType;
import br.com.jdo.taxone.mapper.infrastructure.entity.DSTable;

@Repository
public interface JPADSTableRepository extends JpaRepository<DSTable, Integer>{

    List<DSTable> findBydataSourceConfigurationDataSourceType(DataSourceType dataSourceType);

    DSTable findFirstBydataSourceConfigurationIdAndName(Integer dataSourceConfigId, String name);

}
