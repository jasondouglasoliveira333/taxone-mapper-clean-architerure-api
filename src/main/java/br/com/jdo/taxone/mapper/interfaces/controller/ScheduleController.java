package br.com.jdo.taxone.mapper.interfaces.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.jdo.taxone.mapper.domain.entity.ScheduleDomain;
import br.com.jdo.taxone.mapper.domain.enums.ScheduleStatus;
import br.com.jdo.taxone.mapper.usecase.ScheduleUseCase;

public class ScheduleController {

    private Logger log = LoggerFactory.getLogger(ScheduleController.class);
    
    private ScheduleUseCase scheduleUseCase;
    
    public ScheduleUseCase scheduleUseCase() {
        return scheduleUseCase;
    }

    public Object list(Integer page, Integer size){
        return scheduleUseCase().list(page, size);
    }
    
    public Object get(Integer id){
        return scheduleUseCase().get(id);
    }

    //Will be removed soon
    public Object getPeriodes(Integer id){
        return scheduleUseCase().getPeriode(id);
    }

    public Object save(ScheduleDomain sDTO){
        scheduleUseCase().save(sDTO);
        return null;
    }
    
    public Object delete(Integer scheduleId) throws Exception{
        if (!scheduleUseCase().isWaitingTaxoneResponse(scheduleId)) {
            log.info("can delete scheduleId");
            scheduleUseCase().updateStatus(scheduleId, ScheduleStatus.INACTIVE);
            return null;
        }else {
            throw new Exception("Agendamento com retorno do TaxOne pendente");
        }
    }

//    public void process(Integer scheduleId) {
//        try {
//            OncoClinicasTaxtOneService oncoIntegrationUseCase = oncoIntegrationBuilder.createService(null);
//            log.info("Autenticando no api de integracao OncoClinicas");
//            TaxOneApi taxOneApi = taxOneApiRepository.getOne(1);
//            String token = oncoIntegrationUseCase.authentication(taxOneApi.getUsername(), taxOneApi.getPassword()).execute().body().string();
////            String token = "wot T1RLAQLbA9R0H7vF4vtX2YU-38cYmYCUnhDw56lNkdpb3frQxXCTC3SwAAHQftxXHEY3E0zL8aiEg_UqmtTzDIP4QcsEnVq4nnvHwOyWmBLftx3z6M-HiRkJxF0hdDp4drX4c9XD_exMQzPgHgx0oRv33a6fDC2tsgR3H4PHWMQkPEPJAw4ye_h1iF2WUGWHKWOL3laKXuntjCtU4I3BHfQvl8S6OR8OawVEhKZUmsUyhyPvopcHFHv3Ncbt6NW5Ss9xWr4U2JnnVtNcJylF4PDHJP8hU2vJlVa4rM9NxPdWwB4NyWpf7g0xR8E-jHua-h71D_PJq3Y_UrKjWGm2UjyLqzHDVoZSWMrglGgFC1a6AeRZw8hGOpBS0oJIJuvTN2r5AxTodsulyiq1UnvMBjGUI6RY2KYm4cRf34GLfJnWxDDb2YkazSOXUo-xZyl09bM_iAJp-Sg5BnNaN7M5RiZfMYUL81nmh7YOAIoTmq3LFFQ-6eBXupchsYdyesiGxHRbyiHXKMWqMAzmATRGH6sJa5iFHiiOKM7eWEROqv5q2CEdUlEY8lyVt_IHlzqDchi7VJLuuSMU3pQLUagzLJel_w4YwWd0kN6TG75g-RicWRE2PU0i0PnJzEsR16vErmuyJgwOeAWhSgm8oIlKnp9-_zy5boBiQBVWsc4*";
//            System.out.println("token:" + token);
//            scheduleSenderUseCase.process(scheduleRepository.getOne(scheduleId), token);
//        }catch (Exception e) {
//            log.error("Erro na executao do agendamento", e);
//        }
//    }
    
    
}
