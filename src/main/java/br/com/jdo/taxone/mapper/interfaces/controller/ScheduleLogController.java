package br.com.jdo.taxone.mapper.interfaces.controller;

import br.com.jdo.taxone.mapper.domain.enums.ScheduleLogStatus;
import br.com.jdo.taxone.mapper.usecase.ScheduleLogUseCase;

public class ScheduleLogController {
    
    private ScheduleLogUseCase scheduleLogUseCase;
    
    public ScheduleLogUseCase scheduleLogUseCase() {
        return scheduleLogUseCase;
    }

    public Object list(ScheduleLogStatus status,  Integer page, Integer size){
        return scheduleLogUseCase().findAll(status, page, size);
    }

    public Object generateStatitics(){
        return scheduleLogUseCase().groupByStatus();
    }
    
    public Object get(Integer id){
        return scheduleLogUseCase().get(id);
    }

    public Object getTaxOneErrors(Integer id, Integer page, Integer size){
        return scheduleLogUseCase().getTaxtOneErrors(id, page, size);
    }


}
