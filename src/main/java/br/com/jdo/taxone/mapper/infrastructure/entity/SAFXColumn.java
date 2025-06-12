package br.com.jdo.taxone.mapper.infrastructure.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.jdo.taxone.mapper.domain.enums.ColumnType;



@Entity
public class SAFXColumn {

    @Id
    @GeneratedValue
    private Integer id;
    
    private String name;
    
    @Enumerated(EnumType.STRING)
    private ColumnType columnType;
    
    private Boolean required;
    
    private Integer position; 
    
    private Integer size;
    
    @ManyToOne
    private SAFXTable safxTable;
    
    @ManyToOne
    private DSColumn dsColumn;

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

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public SAFXTable getSafxTable() {
        return safxTable;
    }

    public void setSafxTable(SAFXTable safxTable) {
        this.safxTable = safxTable;
    }

    public DSColumn getDsColumn() {
        return dsColumn;
    }

    public void setDsColumn(DSColumn dsColumn) {
        this.dsColumn = dsColumn;
    }

}
