package br.com.jdo.taxone.mapper.interfaces.repository;

import br.com.jdo.taxone.mapper.domain.entity.DSColumnDomain;
import br.com.jdo.taxone.mapper.domain.entity.PageResponse;

public interface DSColumnRepository{

    PageResponse<DSColumnDomain> findBydsTableId(Integer id, Integer page, Integer size);

    DSColumnDomain findFirstBydsTableIdAndName(Integer dsTableId, String name);

    void save(DSColumnDomain dsc);

}
