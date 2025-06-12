package br.com.jdo.taxone.mapper.domain.entity;

import java.util.List;

public class DSTableDomain {

    private Integer id;
    
    private String name;
    
    private DataSourceConfigurationDomain dataSourceConfiguration;
    
    private List<DSColumnDomain> dsColumns;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataSourceConfigurationDomain getDataSourceConfiguration() {
        return dataSourceConfiguration;
    }

    public void setDataSourceConfiguration(DataSourceConfigurationDomain dataSourceConfiguration) {
        this.dataSourceConfiguration = dataSourceConfiguration;
    }

    public List<DSColumnDomain> getDsColumns() {
        return dsColumns;
    }

    public void setDsColumns(List<DSColumnDomain> dsColumns) {
        this.dsColumns = dsColumns;
    }

    
}
