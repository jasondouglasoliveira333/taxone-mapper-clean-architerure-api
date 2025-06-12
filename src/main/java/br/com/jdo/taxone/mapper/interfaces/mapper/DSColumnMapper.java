package br.com.jdo.taxone.mapper.interfaces.mapper;

import br.com.jdo.taxone.mapper.domain.entity.DSColumnDomain;
import br.com.jdo.taxone.mapper.infrastructure.entity.DSColumn;

public class DSColumnMapper {
    
    public static DSColumnDomain map(DSColumn dsColumn) {
        DSColumnDomain dDTO = new DSColumnDomain();
        dDTO.setId(dsColumn.getId());
        dDTO.setName(dsColumn.getName());
        dDTO.setColumnType(dsColumn.getColumnType());
        dDTO.setSize(dsColumn.getSize());
        dDTO.setDsTable(DSTableMapper.map(dsColumn.getDsTable()));
        return dDTO;
    }

    public static DSColumn map(DSColumnDomain dsd) {
        DSColumn dsc = new DSColumn();
        dsc.setName(dsd.getName());
        dsc.setColumnType(dsd.getColumnType());
        dsc.setSize(dsd.getSize());
        return dsc;
    }

    public static void marge(DSColumnDomain dsd, DSColumnDomain dsc) {
        dsc.setColumnType(dsd.getColumnType());
        dsc.setSize(dsd.getSize());
    }

}
