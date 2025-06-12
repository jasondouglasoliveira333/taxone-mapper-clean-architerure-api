package br.com.jdo.taxone.mapper.domain.entity;

import br.com.jdo.taxone.mapper.domain.enums.ScheduleLogStatus;

public class ScheduleLogStatisticDomain {
    private ScheduleLogStatus status;
    private long quantity;
    
    public ScheduleLogStatisticDomain() {}

    public ScheduleLogStatisticDomain(ScheduleLogStatus status, long quantity) {
        this.status = status;
        this.quantity = quantity;
    }
    
    public ScheduleLogStatus getStatus() {
        return status;
    }
    public void setStatus(ScheduleLogStatus status) {
        this.status = status;
    }
    public long getQuantity() {
        return quantity;
    }
    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
    
}
