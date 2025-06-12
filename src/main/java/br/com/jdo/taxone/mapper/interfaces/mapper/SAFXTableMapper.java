package br.com.jdo.taxone.mapper.interfaces.mapper;

import java.util.stream.Collectors;

import br.com.jdo.taxone.mapper.domain.entity.SAFXTableDetailtDomain;
import br.com.jdo.taxone.mapper.domain.entity.SAFXTableDomain;
import br.com.jdo.taxone.mapper.infrastructure.entity.DSTable;
import br.com.jdo.taxone.mapper.infrastructure.entity.SAFXTable;

public class SAFXTableMapper {

    public static SAFXTableDomain map(SAFXTable safxTable) {
        SAFXTableDomain tDTO = new SAFXTableDomain();
        tDTO.setId(safxTable.getId());
        tDTO.setName(safxTable.getName());
        tDTO.setDescription(safxTable.getDescription());
        DSTable dsTable = safxTable.getDsTable(); 
        if (dsTable != null) {
            tDTO.setDsTableId(dsTable.getId());
            tDTO.setDsTableName(dsTable.getName());
        }
        return tDTO;
    }

    public static SAFXTableDetailtDomain mapWithColumns(SAFXTable safxTable) {
        SAFXTableDetailtDomain tDTO = new SAFXTableDetailtDomain();
        tDTO.setId(safxTable.getId());
        tDTO.setName(safxTable.getName());
        tDTO.setSafxColumns(safxTable.getSafxColumns().stream().map(SAFXColumnMapper::map).collect(Collectors.toList()));
        return tDTO;
    }

    public static SAFXTableDomain mapIdName(SAFXTable safxTable) {
        SAFXTableDomain tDTO = new SAFXTableDomain();
        tDTO.setId(safxTable.getId());
        tDTO.setName(safxTable.getName());
        return tDTO;
    }

    public static SAFXTable mapIdName(SAFXTableDomain safxTable) {
        SAFXTable tDTO = new SAFXTable();
        tDTO.setId(safxTable.getId());
        tDTO.setName(safxTable.getName());
        return tDTO;
    }

}
