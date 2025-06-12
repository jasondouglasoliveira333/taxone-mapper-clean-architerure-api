package br.com.jdo.taxone.mapper.domain.entity;

public class CriteriaDomain {
    
    private Integer id;
    private SAFXColumnDomain safxColumn;
    private String operator;
    private String value;
    private String additionalValue;
    private ScheduleDomain schedule;

    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public SAFXColumnDomain getSafxColumn() {
        return safxColumn;
    }
    public void setSafxColumn(SAFXColumnDomain safxColumn) {
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
    public ScheduleDomain getSchedule() {
        return schedule;
    }
    public void setSchedule(ScheduleDomain schedule) {
        this.schedule = schedule;
    }
    
    
}
