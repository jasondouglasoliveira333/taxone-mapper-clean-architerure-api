package br.com.jdo.taxone.mapper.interfaces.repository;

import br.com.jdo.taxone.mapper.domain.entity.TaxOneApiDomain;
import br.com.jdo.taxone.mapper.infrastructure.persistence.JPATaxOneApiRepository;
import br.com.jdo.taxone.mapper.interfaces.mapper.TaxOneApiMapper;

public class TaxOneApiRepositoryImpl implements TaxOneApiRepository {
    
    JPATaxOneApiRepository taxOneApiRepository;

    public TaxOneApiRepositoryImpl(JPATaxOneApiRepository taxOneApiRepository) {
        this.taxOneApiRepository = taxOneApiRepository;
    }

    public TaxOneApiDomain getOne(int i) {
        return TaxOneApiMapper.map(taxOneApiRepository.getOne(i));
    }

    public void save(TaxOneApiDomain to) {
        taxOneApiRepository.save(TaxOneApiMapper.map(to));
    }

}
