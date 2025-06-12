package br.com.jdo.taxone.mapper.interfaces.repository;

import br.com.jdo.taxone.mapper.domain.entity.TaxOneApiDomain;

public interface TaxOneApiRepository {

    TaxOneApiDomain getOne(int i);

    void save(TaxOneApiDomain to);

}
