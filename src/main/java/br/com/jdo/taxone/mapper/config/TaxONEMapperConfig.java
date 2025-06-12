package br.com.jdo.taxone.mapper.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.jdo.taxone.mapper.infrastructure.integration.AuthenticationManagerWrapper;
import br.com.jdo.taxone.mapper.infrastructure.integration.JwtTokenUtilWrapper;
import br.com.jdo.taxone.mapper.infrastructure.integration.JwtUserDetailsUseCaseWrapper;
import br.com.jdo.taxone.mapper.infrastructure.persistence.JPACriteriaRepository;
import br.com.jdo.taxone.mapper.infrastructure.persistence.JPADSColumnRepository;
import br.com.jdo.taxone.mapper.infrastructure.persistence.JPADSTableRepository;
import br.com.jdo.taxone.mapper.infrastructure.persistence.JPADataSourceConfigRepository;
import br.com.jdo.taxone.mapper.infrastructure.persistence.JPAEmailRepository;
import br.com.jdo.taxone.mapper.infrastructure.persistence.JPASAFXColumnRepository;
import br.com.jdo.taxone.mapper.infrastructure.persistence.JPASAFXTableRepository;
import br.com.jdo.taxone.mapper.infrastructure.persistence.JPAScheduleLogIntergrationErrorRepository;
import br.com.jdo.taxone.mapper.infrastructure.persistence.JPAScheduleLogRepository;
import br.com.jdo.taxone.mapper.infrastructure.persistence.JPAScheduleRepository;
import br.com.jdo.taxone.mapper.infrastructure.persistence.JPATaxOneApiRepository;
import br.com.jdo.taxone.mapper.infrastructure.persistence.JPAUploadRepository;
import br.com.jdo.taxone.mapper.infrastructure.persistence.JPAUserRepository;
import br.com.jdo.taxone.mapper.integration.OncoClinicasTaxtOneServiceBuilder;
import br.com.jdo.taxone.mapper.interfaces.repository.CriteriaRepository;
import br.com.jdo.taxone.mapper.interfaces.repository.CriteriaRepositoryImpl;
import br.com.jdo.taxone.mapper.interfaces.repository.DSColumnRepository;
import br.com.jdo.taxone.mapper.interfaces.repository.DSColumnRepositoryImpl;
import br.com.jdo.taxone.mapper.interfaces.repository.DSTableRepository;
import br.com.jdo.taxone.mapper.interfaces.repository.DSTableRepositoryImpl;
import br.com.jdo.taxone.mapper.interfaces.repository.DataSourceConfigRepository;
import br.com.jdo.taxone.mapper.interfaces.repository.DataSourceConfigRepositoryImpl;
import br.com.jdo.taxone.mapper.interfaces.repository.EmailRepository;
import br.com.jdo.taxone.mapper.interfaces.repository.EmailRepositoryImpl;
import br.com.jdo.taxone.mapper.interfaces.repository.SAFXColumnRepository;
import br.com.jdo.taxone.mapper.interfaces.repository.SAFXColumnRepositoryImpl;
import br.com.jdo.taxone.mapper.interfaces.repository.SAFXTableRepository;
import br.com.jdo.taxone.mapper.interfaces.repository.SAFXTableRepositoryImpl;
import br.com.jdo.taxone.mapper.interfaces.repository.ScheduleLogIntergrationErrorRepository;
import br.com.jdo.taxone.mapper.interfaces.repository.ScheduleLogIntergrationErrorRepositoryImpl;
import br.com.jdo.taxone.mapper.interfaces.repository.ScheduleLogRepository;
import br.com.jdo.taxone.mapper.interfaces.repository.ScheduleLogRepositoryImpl;
import br.com.jdo.taxone.mapper.interfaces.repository.ScheduleRepository;
import br.com.jdo.taxone.mapper.interfaces.repository.ScheduleRepositoryImpl;
import br.com.jdo.taxone.mapper.interfaces.repository.TaxOneApiRepository;
import br.com.jdo.taxone.mapper.interfaces.repository.TaxOneApiRepositoryImpl;
import br.com.jdo.taxone.mapper.interfaces.repository.UploadRepository;
import br.com.jdo.taxone.mapper.interfaces.repository.UploadRepositoryImpl;
import br.com.jdo.taxone.mapper.interfaces.repository.UserRepository;
import br.com.jdo.taxone.mapper.interfaces.repository.UserRepositoryImpl;
import br.com.jdo.taxone.mapper.usecase.DataSourceConfigUseCase;
import br.com.jdo.taxone.mapper.usecase.EmailUseCase;
import br.com.jdo.taxone.mapper.usecase.JwtUserDetailsUseCase;
import br.com.jdo.taxone.mapper.usecase.MatcherUseCase;
import br.com.jdo.taxone.mapper.usecase.ScheduleLogUseCase;
import br.com.jdo.taxone.mapper.usecase.ScheduleSenderUseCase;
import br.com.jdo.taxone.mapper.usecase.ScheduleUseCase;
import br.com.jdo.taxone.mapper.usecase.SmtpEmailUseCase;
import br.com.jdo.taxone.mapper.usecase.TaxOneApiUseCase;
import br.com.jdo.taxone.mapper.usecase.UpdateScheduleStatusUseCase;
import br.com.jdo.taxone.mapper.usecase.UploadUseCase;
import br.com.jdo.taxone.mapper.usecase.UserUseCase;
import br.com.jdo.taxone.mapper.util.JwtTokenUtil;


@Configuration
public class TaxONEMapperConfig {
    
    @Autowired
    private OncoClinicasTaxtOneServiceBuilder oncoIntegrationBuilder; 
    
    @Autowired
    JPACriteriaRepository criteriaRepository;
    
    @Autowired
    JPADataSourceConfigRepository dataSourceConfigRepository;
    
    @Autowired
    JPADSTableRepository dsTableRepository;

    @Autowired
    JPADSColumnRepository dsColumnRepository;

    @Autowired
    JPAEmailRepository emailRepository;
    
    @Autowired
    JPAUserRepository userRepository;
    
    @Autowired
    JPASAFXTableRepository safxTableRepository; 

    @Autowired
    JPASAFXColumnRepository safxColumnRepository; 

    @Autowired
    JPAScheduleLogRepository scheduleLogRepository;

    @Autowired
    JPAScheduleLogIntergrationErrorRepository scheduleLogIntergrationErrorRepository;

    @Autowired
    JPAScheduleRepository scheduleRepository;

    @Autowired
    JPATaxOneApiRepository taxOneApiRepository;
    
    @Autowired
    JPAUploadRepository uploadRepository;

    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    
    @Autowired
    JwtUserDetailsUseCase userDetailsService;

    @Value("${lkm.taxonemapper.integration.codEmpresa}")
    private String codEmpresa;
    
    @Value("${lkm.taxonemapper.integration.codEstab}")
    private String codEstab;
    
    @Value("${lkm.taxonemapper.datasource.dateTimeFieldName}")
    private String dateTimeFieldName;

    @Value("${lkm.taxonemapper.email.smtp.host}")
    private String host;
    
    @Value("${lkm.taxonemapper.email.smtp.username}")
    private String username;

    @Value("${lkm.taxonemapper.email.smtp.password}")
    private String password;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    
    @Bean 
    public CriteriaRepository criteriaRepository() {
        return new CriteriaRepositoryImpl(criteriaRepository);
    }

    @Bean
    public DataSourceConfigRepository dataSourceConfigRepository() {
        return new DataSourceConfigRepositoryImpl(dataSourceConfigRepository);
    }
    
    @Bean
    public DSTableRepository dsTableRepository() {
        return new DSTableRepositoryImpl(dsTableRepository);
    }

    @Bean 
    public DSColumnRepository dsColumnRepository() {
        return new DSColumnRepositoryImpl(dsColumnRepository);
    }
    
    @Bean
    public EmailRepository emailRepository() {
        return new EmailRepositoryImpl(emailRepository);
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryImpl(userRepository);
    }
    
    @Bean
    public SAFXTableRepository safxTableRepository() {
        return new SAFXTableRepositoryImpl(safxTableRepository); 
    }
    
    @Bean
    public SAFXColumnRepository safxColumnRepository() {
        return new SAFXColumnRepositoryImpl(safxColumnRepository);
    }
    
    @Bean
    public ScheduleLogRepository scheduleLogRepository() {
        return new ScheduleLogRepositoryImpl(scheduleLogRepository);
    }
    
    @Bean
    public ScheduleLogIntergrationErrorRepository scheduleLogIntergrationErrorRepository() {
        return new ScheduleLogIntergrationErrorRepositoryImpl(scheduleLogIntergrationErrorRepository);
    }
    
    @Bean
    public ScheduleRepository scheduleRepository(){
        return new ScheduleRepositoryImpl(scheduleRepository);
    }
    
    @Bean
    public TaxOneApiRepository taxOneApiRepository() {
        return new TaxOneApiRepositoryImpl(taxOneApiRepository);
    }
    
    @Bean
    public UploadRepository uploadRepository() {
        return new UploadRepositoryImpl(uploadRepository);
    }
    
    @Bean
    public br.com.jdo.taxone.mapper.interfaces.integration.AuthenticationManager authenticationManager() {
        return new AuthenticationManagerWrapper(authenticationManager);
    }
    
    @Bean
    public br.com.jdo.taxone.mapper.interfaces.integration.JwtTokenUtil jwtTokenUtil1() {
        return new JwtTokenUtilWrapper(jwtTokenUtil);
    }
    
    @Bean
    public JwtUserDetailsUseCaseWrapper userDetailsService() {
        return new JwtUserDetailsUseCaseWrapper(userDetailsService);
    }
    
    @Bean
    public DataSourceConfigUseCase dataSourceConfigUseCase(){
        return new DataSourceConfigUseCase(dataSourceConfigRepository(), dsTableRepository(), dsColumnRepository());
    }
    
    @Bean 
    public EmailUseCase emailUseCase() {
        return new EmailUseCase(emailRepository(), smtpEmailUseCase());
    }
    
    @Bean
    public JwtUserDetailsUseCase jwtUserDetailsUseCase() {
        return new JwtUserDetailsUseCase(userRepository());
    }
    
    @Bean
    public MatcherUseCase matcherUseCase() {
        return new MatcherUseCase(safxTableRepository(), safxColumnRepository(), dsTableRepository(), dsColumnRepository());
    }
    
    @Bean
    public ScheduleLogUseCase scheduleLogUseCase() {
        return new ScheduleLogUseCase(scheduleLogRepository(), scheduleLogIntergrationErrorRepository());
    }

    @Bean
    public ScheduleSenderUseCase scheduleSenderUseCase() {
        return new ScheduleSenderUseCase(oncoIntegrationBuilder, scheduleRepository(), scheduleLogUseCase(), emailUseCase(), taxOneApiRepository(),
                codEmpresa, codEstab, dateTimeFieldName);
    }
    
    @Bean
    public ScheduleUseCase scheduleUseCase() {
        return new ScheduleUseCase(scheduleRepository(), criteriaRepository(), scheduleLogRepository());
    }
    
    @Bean
    public TaxOneApiUseCase taxOneApiUseCase() {
        return new TaxOneApiUseCase(taxOneApiRepository()); 
    }
    
    @Bean
    public UpdateScheduleStatusUseCase updateScheduleStatusUseCase() {
        return new UpdateScheduleStatusUseCase(oncoIntegrationBuilder, scheduleLogRepository(), scheduleLogIntergrationErrorRepository(), taxOneApiRepository()); 
    }

    @Bean
    public UploadUseCase uploadUseCase() {
        return new UploadUseCase(safxTableRepository(), safxColumnRepository(), uploadRepository());
    }

    @Bean
    public UserUseCase userUseCase() {
        return new UserUseCase(userRepository(), bCryptPasswordEncoder);
    }
    
    @Bean
    public SmtpEmailUseCase smtpEmailUseCase() {
        return new SmtpEmailUseCase(host, username, password);
    }

}
