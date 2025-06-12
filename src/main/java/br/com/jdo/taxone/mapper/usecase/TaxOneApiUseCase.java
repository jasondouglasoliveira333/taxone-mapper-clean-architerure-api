package br.com.jdo.taxone.mapper.usecase;

import br.com.jdo.taxone.mapper.domain.entity.TaxOneApiDomain;
import br.com.jdo.taxone.mapper.interfaces.repository.TaxOneApiRepository;

public class TaxOneApiUseCase {

    private TaxOneApiRepository taxOneApiRepository;
    
    public TaxOneApiUseCase(TaxOneApiRepository taxOneApiRepository) {
        this.taxOneApiRepository = taxOneApiRepository;
    }

    public TaxOneApiDomain getOne(Integer id) {
        TaxOneApiDomain toDTO =taxOneApiRepository.getOne(id);
        return toDTO;
    }

    public void save(TaxOneApiDomain toDTO) {
        taxOneApiRepository.save(toDTO);
    }
}
