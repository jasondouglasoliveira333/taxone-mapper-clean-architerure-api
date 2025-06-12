package br.com.jdo.taxone.mapper.domain.entity;

import br.com.jdo.taxone.mapper.domain.enums.ColumnType;



public class DSColumnDomain {
    
    private Integer id;
    
    private String name;

    private ColumnType columnType;
    
    private Integer size;

    private DSTableDomain dsTable;

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

    public ColumnType getColumnType() {
        return columnType;
    }

    public void setColumnType(ColumnType columnType) {
        this.columnType = columnType;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public DSTableDomain getDsTable() {
        return dsTable;
    }

    public void setDsTable(DSTableDomain dsTable) {
        this.dsTable = dsTable;
    } 

    
}
