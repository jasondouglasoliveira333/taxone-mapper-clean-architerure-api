package br.com.jdo.taxone.mapper.usecase;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import br.com.jdo.taxone.mapper.domain.entity.ScheduleLogDomain;
import br.com.jdo.taxone.mapper.domain.entity.ScheduleLogIntergrationErrorDomain;
import br.com.jdo.taxone.mapper.domain.entity.TaxOneApiDomain;
import br.com.jdo.taxone.mapper.domain.enums.ScheduleLogStatus;
import br.com.jdo.taxone.mapper.infrastructure.entity.IntegrationStatus;
import br.com.jdo.taxone.mapper.infrastructure.entity.ScheduleLog;
import br.com.jdo.taxone.mapper.integration.OncoClinicasTaxtOneService;
import br.com.jdo.taxone.mapper.integration.OncoClinicasTaxtOneServiceBuilder;
import br.com.jdo.taxone.mapper.integration.dto.ConsultaLoteDTO;
import br.com.jdo.taxone.mapper.integration.dto.ConsultaLoteImportacaoDTO;
import br.com.jdo.taxone.mapper.integration.dto.ImportarDTO;
import br.com.jdo.taxone.mapper.interfaces.repository.ScheduleLogIntergrationErrorRepository;
import br.com.jdo.taxone.mapper.interfaces.repository.ScheduleLogRepository;
import br.com.jdo.taxone.mapper.interfaces.repository.TaxOneApiRepository;

public class UpdateScheduleStatusUseCase {
    
    private Logger log = LoggerFactory.getLogger(getClass());
    
    private OncoClinicasTaxtOneServiceBuilder oncoIntegrationBuilder; 
    
    private ScheduleLogRepository scheduleLogRepository;

    private ScheduleLogIntergrationErrorRepository scheduleLogIntergrationErrorRepository;
    
    private TaxOneApiRepository taxOneApiRepository;

    public UpdateScheduleStatusUseCase(OncoClinicasTaxtOneServiceBuilder oncoIntegrationBuilder, ScheduleLogRepository scheduleLogRepository, 
            ScheduleLogIntergrationErrorRepository scheduleLogIntergrationErrorRepository, TaxOneApiRepository taxOneApiRepository) {
        this.oncoIntegrationBuilder = oncoIntegrationBuilder;
        this.scheduleLogRepository = scheduleLogRepository;
        this.scheduleLogIntergrationErrorRepository = scheduleLogIntergrationErrorRepository;
        this.taxOneApiRepository = taxOneApiRepository;
    }
    
    @Transactional
    public void process() {
        List<ScheduleLogDomain> ss = scheduleLogRepository.findByStatus(ScheduleLogStatus.SENT);
        if (ss.size() > 0) {
            try {
                OncoClinicasTaxtOneService oncoIntegrationAuthService = oncoIntegrationBuilder.createService(null);
                log.info("Autenticando no api de integracao OncoClinicas");
                TaxOneApiDomain taxOneApi = taxOneApiRepository.getOne(1);
                String token = oncoIntegrationAuthService.authentication(taxOneApi.getUsername(), taxOneApi.getPassword()).execute().body().string();
                
                OncoClinicasTaxtOneService oncoIntegrationService = oncoIntegrationBuilder.createService(token);
                for (ScheduleLogDomain s1 : ss) {
                    ScheduleLog s = null;
//Here                    
                    log.info("Iniciando o processamento do agendamento:" + s.getSchedule().getName() + " - created:" + s.getExecutionDate());
                    String numLote = s.getNumLote();
                    if (s.getIntegrationStatus().equals(IntegrationStatus.ENVIADO)) {
                        ConsultaLoteDTO cl = oncoIntegrationService.consultaLote(numLote).execute().body();
                        log.info("cl:" + cl);
                        AtomicBoolean loadedWithError = new AtomicBoolean();
                        cl.getProtocolos().stream().forEach(protocolo -> {
                            if (protocolo.getErros() != null) {
                                protocolo.getErros().stream().forEach(erro -> {
                                    ScheduleLogIntergrationErrorDomain slie = new ScheduleLogIntergrationErrorDomain();
                                    slie.setNumeroReg(erro.getNum_reg());
                                    slie.setCodigoErro(erro.getCod_erro());
                                    slie.setDescricaoErro(erro.getDescricao_erro());
                                    slie.setNomeCampo(erro.getNom_campo());
                                    slie.setChaveRegistro(erro.getChave_registro());
//here                                    slie.setScheduleLog(s);
                                    scheduleLogIntergrationErrorRepository.save(slie);
                                    loadedWithError.set(true);
                                });
                            }
                        });
                        if (!loadedWithError.get()) {
                            s.setIntegrationStatus(IntegrationStatus.CARREGADO);
                        }else {
                            s.setStatus(ScheduleLogStatus.ERROR_TAXONE);
                            s.setIntegrationStatus(IntegrationStatus.CARREGADO_COM_ERRO);
                        }
                    }else if (s.getIntegrationStatus().equals(IntegrationStatus.CARREGADO)) {
                        ImportarDTO importarResponse = oncoIntegrationService.importar(numLote).execute().body();
                        log.info("importarResponse:" + importarResponse);
                        s.setIntegrationStatus(IntegrationStatus.EXECUTADO_IMPORTAR);
                    }else  if (s.getIntegrationStatus().equals(IntegrationStatus.EXECUTADO_IMPORTAR)) {
                        ConsultaLoteImportacaoDTO consultaLoteImportacao = oncoIntegrationService.consultaLoteImportacao(numLote).execute().body();
                        log.info("consultaLoteImportacao:" + consultaLoteImportacao);
                        AtomicBoolean loadedWithError = new AtomicBoolean();
                        consultaLoteImportacao.getCargas().stream().forEach(carga -> {
                            if (carga.getErros() != null) {
                                carga.getErros().stream().forEach(erro -> {
                                    ScheduleLogIntergrationErrorDomain slie = new ScheduleLogIntergrationErrorDomain();
                                    slie.setNumeroReg(erro.getNum_reg());
                                    slie.setCodigoErro(erro.getCod_erro());
                                    slie.setDescricaoErro(erro.getDescricao_erro());
                                    slie.setNomeCampo(erro.getNom_campo());
                                    slie.setChaveRegistro(erro.getChave_registro());
//here                                    slie.setScheduleLog(s);
                                    scheduleLogIntergrationErrorRepository.save(slie);
                                    loadedWithError.set(true);
                                });
                            }
                        });
                        if (!loadedWithError.get()) {
                            if (consultaLoteImportacao.getImportacoes() != null) { //just change the state when the load has been done
                                s.setStatus(ScheduleLogStatus.PROCESSED);
                                s.setIntegrationStatus(IntegrationStatus.FINALIZADO);
                            }
                        }else {
                            s.setStatus(ScheduleLogStatus.ERROR_TAXONE);
                            s.setIntegrationStatus(IntegrationStatus.CARREGADO_COM_ERRO);
                        }
                    }
                    scheduleLogRepository.save(s);
                    log.info("Finalizando o processamento do agendamento:" + s.getSchedule().getName());
                }
            }catch (Exception e) {
                log.error("Erro executando os agendamentos:", e);
            }
        }
    }

}
