package br.com.jdo.taxone.mapper.domain.entity;

import java.util.List;




public class SAFXTableDetailtDomain {
    private Integer id;
    private String name;
    
    private List<SAFXColumnDomain> safxColumns;

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

    public List<SAFXColumnDomain> getSafxColumns() {
        return safxColumns;
    }

    public void setSafxColumns(List<SAFXColumnDomain> safxColumns) {
        this.safxColumns = safxColumns;
    }
    
    

}
