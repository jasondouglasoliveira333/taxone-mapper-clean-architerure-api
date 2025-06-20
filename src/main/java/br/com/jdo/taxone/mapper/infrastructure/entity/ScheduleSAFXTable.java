package br.com.jdo.taxone.mapper.infrastructure.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ScheduleSAFXTable {

    //Resolve this id
    @Id
    @GeneratedValue
    private Integer id;
    
    @ManyToOne
    private Schedule schedule;
    
    @ManyToOne
    private SAFXTable safxTable;
    
    private LocalDateTime lastExecution;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public SAFXTable getSafxTable() {
        return safxTable;
    }

    public void setSafxTable(SAFXTable safxTable) {
        this.safxTable = safxTable;
    }

    public LocalDateTime getLastExecution() {
        return lastExecution;
    }

    public void setLastExecution(LocalDateTime lastExecution) {
        this.lastExecution = lastExecution;
    }

}
