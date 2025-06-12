package br.com.jdo.taxone.mapper.infrastructure.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;




@Entity
public class DSTable {

    @Id
    @GeneratedValue
    private Integer id;
    
    private String name;
    
    @ManyToOne
    private DataSourceConfiguration dataSourceConfiguration;
    
    @OneToMany(mappedBy = "dsTable")
    private List<DSColumn> dsColumns;

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

    public DataSourceConfiguration getDataSourceConfiguration() {
        return dataSourceConfiguration;
    }

    public void setDataSourceConfiguration(DataSourceConfiguration dataSourceConfiguration) {
        this.dataSourceConfiguration = dataSourceConfiguration;
    }

    public List<DSColumn> getDsColumns() {
        return dsColumns;
    }

    public void setDsColumns(List<DSColumn> dsColumns) {
        this.dsColumns = dsColumns;
    }

    
}
