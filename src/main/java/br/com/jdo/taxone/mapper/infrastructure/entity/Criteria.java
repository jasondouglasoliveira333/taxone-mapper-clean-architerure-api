package br.com.jdo.taxone.mapper.infrastructure.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;




@Entity
public class Criteria {
    
    @Id
    @GeneratedValue
    private Integer id;
    
    @ManyToOne
    private SAFXColumn safxColumn;
    
    private String operator;
    
    private String value;
    
    private String additionalValue;
    
    @ManyToOne
    private Schedule schedule;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SAFXColumn getSafxColumn() {
        return safxColumn;
    }

    public void setSafxColumn(SAFXColumn safxColumn) {
        this.safxColumn = safxColumn;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAdditionalValue() {
        return additionalValue;
    }

    public void setAdditionalValue(String additionalValue) {
        this.additionalValue = additionalValue;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
