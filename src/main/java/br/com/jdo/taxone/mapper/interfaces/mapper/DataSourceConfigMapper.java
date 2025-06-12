package br.com.jdo.taxone.mapper.interfaces.mapper;

import br.com.jdo.taxone.mapper.domain.entity.DataSourceConfigurationDomain;
import br.com.jdo.taxone.mapper.infrastructure.entity.DataSourceConfiguration;

public class DataSourceConfigMapper {

    public static DataSourceConfigurationDomain map(DataSourceConfiguration dsc) {
        DataSourceConfigurationDomain dsd = new DataSourceConfigurationDomain();
        dsd.setId(dsc.getId());
        dsd.setUrl(dsc.getUrl());
        dsd.setUsername(dsc.getUsername());
        dsd.setPassword(dsc.getPassword());
        dsd.setResourceNames(dsc.getResourceNames());
        dsd.setDataSourceType(dsc.getDataSourceType());
        return dsd;
    }

    public static DataSourceConfiguration map(DataSourceConfigurationDomain dsd) {
        DataSourceConfiguration dsc = new DataSourceConfiguration();
        dsc.setId(dsd.getId());
        dsc.setUrl(dsd.getUrl());
        dsc.setUsername(dsd.getUsername());
        dsc.setPassword(dsd.getPassword());
        dsc.setResourceNames(dsd.getResourceNames());
        dsc.setDataSourceType(dsd.getDataSourceType());
        return dsc;
    }
}
