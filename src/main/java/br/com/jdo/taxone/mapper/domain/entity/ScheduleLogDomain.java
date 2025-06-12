package br.com.jdo.taxone.mapper.domain.entity;

import java.time.LocalDateTime;

import br.com.jdo.taxone.mapper.domain.enums.ScheduleLogStatus;

public class ScheduleLogDomain {
    private Integer id;
    private String scheduleName;
    private LocalDateTime executionDate;
    private ScheduleLogStatus status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public LocalDateTime getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(LocalDateTime executionDate) {
        this.executionDate = executionDate;
    }

    public ScheduleLogStatus getStatus() {
        return status;
    }

    public void setStatus(ScheduleLogStatus status) {
        this.status = status;
    }
    
}
