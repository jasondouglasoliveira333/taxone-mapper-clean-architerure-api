package br.com.jdo.taxone.mapper.interfaces.repository;

import br.com.jdo.taxone.mapper.domain.entity.CriteriaDomain;
import br.com.jdo.taxone.mapper.infrastructure.entity.Criteria;
import br.com.jdo.taxone.mapper.infrastructure.persistence.JPACriteriaRepository;
import br.com.jdo.taxone.mapper.interfaces.mapper.CriteriaMapper;

public class CriteriaRepositoryImpl implements CriteriaRepository{
    
    JPACriteriaRepository criteriaRepository;
    
    public CriteriaRepositoryImpl(JPACriteriaRepository criteriaRepository) {
        this.criteriaRepository = criteriaRepository;
    }
    
    public void save(CriteriaDomain cDomain) {
        Criteria c = CriteriaMapper.map(cDomain);
        criteriaRepository.save(c);
    }

    public void deleteById(Integer cId) {
        criteriaRepository.deleteById(cId);
    }

}
