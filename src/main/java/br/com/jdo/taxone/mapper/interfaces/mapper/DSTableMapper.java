package br.com.jdo.taxone.mapper.interfaces.mapper;

import br.com.jdo.taxone.mapper.domain.entity.DSTableDomain;
import br.com.jdo.taxone.mapper.infrastructure.entity.DSTable;

public class DSTableMapper {

    public static DSTableDomain map(DSTable dsTable) {
        DSTableDomain dDTO = new DSTableDomain();
        dDTO.setId(dsTable.getId());
        dDTO.setName(dsTable.getName());
        return dDTO;
    }
    
    public static DSTable map(DSTableDomain dsTable) {
        DSTable dt = new DSTable();
        dt.setId(dsTable.getId());
        dt.setName(dsTable.getName());
        return dt;
    }
    
    

}
