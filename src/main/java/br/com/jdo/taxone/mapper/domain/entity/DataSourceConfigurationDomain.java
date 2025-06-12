package br.com.jdo.taxone.mapper.domain.entity;

import java.util.List;

import br.com.jdo.taxone.mapper.domain.enums.DataSourceType;


public class DataSourceConfigurationDomain {
    
    private Integer id;
    
    private String url;
    
    private String username;
    
    private String password;
    
    private String resourceNames;
    
    private DataSourceType dataSourceType; 
    
    private List<DSTableDomain> dsTables;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResourceNames() {
        return resourceNames;
    }

    public void setResourceNames(String resourceNames) {
        this.resourceNames = resourceNames;
    }

    public DataSourceType getDataSourceType() {
        return dataSourceType;
    }

    public void setDataSourceType(DataSourceType dataSourceType) {
        this.dataSourceType = dataSourceType;
    }

    public List<DSTableDomain> getDsTables() {
        return dsTables;
    }

    public void setDsTables(List<DSTableDomain> dsTables) {
        this.dsTables = dsTables;
    }

    
}
