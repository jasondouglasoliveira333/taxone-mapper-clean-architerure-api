package br.com.jdo.taxone.mapper.interfaces.mapper;

import br.com.jdo.taxone.mapper.domain.entity.TaxOneApiDomain;
import br.com.jdo.taxone.mapper.infrastructure.entity.TaxOneApi;

public class TaxOneApiMapper {

    public static TaxOneApiDomain map(TaxOneApi to) {
        TaxOneApiDomain toD = new TaxOneApiDomain();
        toD.setId(to.getId());
        toD.setUsername(to.getUsername());
        toD.setPassword(to.getPassword());
        toD.setUrl(to.getUrl());
        return toD;
    }

    public static TaxOneApi map(TaxOneApiDomain toD) {
        TaxOneApi to = new TaxOneApi();
        to.setId(toD.getId());
        to.setUsername(toD.getUsername());
        to.setPassword(toD.getPassword());
        to.setUrl(toD.getUrl());
        return to;
    }

}
