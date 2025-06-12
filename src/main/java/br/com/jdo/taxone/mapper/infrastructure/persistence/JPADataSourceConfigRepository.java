package br.com.jdo.taxone.mapper.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jdo.taxone.mapper.domain.enums.DataSourceType;
import br.com.jdo.taxone.mapper.infrastructure.entity.DataSourceConfiguration;

@Repository
public interface JPADataSourceConfigRepository extends JpaRepository<DataSourceConfiguration, Integer>{

    DataSourceConfiguration findByDataSourceType(DataSourceType dataSourceType);

}
