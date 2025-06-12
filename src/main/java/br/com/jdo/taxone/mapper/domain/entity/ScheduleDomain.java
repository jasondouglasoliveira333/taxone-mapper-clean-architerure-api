package br.com.jdo.taxone.mapper.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

import br.com.jdo.taxone.mapper.domain.enums.ScheduleStatus;



public class ScheduleDomain {
    private Integer id;
    private String name;
    private String days;
    private String hours;
    private List<SAFXTableDomain> safxTables;
    private List<CriteriaDomain> criterias;
    private String userName;
    private ScheduleStatus status;
    private LocalDateTime lastExecution;

    
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
    public String getDays() {
        return days;
    }
    public void setDays(String days) {
        this.days = days;
    }
    public String getHours() {
        return hours;
    }
    public void setHours(String hours) {
        this.hours = hours;
    }
    public List<SAFXTableDomain> getSafxTables() {
        return safxTables;
    }
    public void setSafxTables(List<SAFXTableDomain> safxTables) {
        this.safxTables = safxTables;
    }
    public List<CriteriaDomain> getCriterias() {
        return criterias;
    }
    public void setCriterias(List<CriteriaDomain> criterias) {
        this.criterias = criterias;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public ScheduleStatus getStatus() {
        return status;
    }
    public void setStatus(ScheduleStatus status) {
        this.status = status;
    }
    public LocalDateTime getLastExecution() {
        return lastExecution;
    }
    public void setLastExecution(LocalDateTime lastExecution) {
        this.lastExecution = lastExecution;
    }

}
