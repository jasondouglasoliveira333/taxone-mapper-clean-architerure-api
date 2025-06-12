package br.com.jdo.taxone.mapper.interfaces.mapper;

import br.com.jdo.taxone.mapper.domain.entity.SAFXColumnDomain;
import br.com.jdo.taxone.mapper.domain.entity.SAFXTableDomain;
import br.com.jdo.taxone.mapper.infrastructure.entity.DSColumn;
import br.com.jdo.taxone.mapper.infrastructure.entity.SAFXColumn;
import br.com.jdo.taxone.mapper.infrastructure.entity.SAFXTable;

public class SAFXColumnMapper {

    public static SAFXColumnDomain map(SAFXColumn safxcolumn) {
        SAFXColumnDomain cDTO = new SAFXColumnDomain();
        cDTO.setId(safxcolumn.getId());
        cDTO.setName(safxcolumn.getName());
        cDTO.setColumnType(safxcolumn.getColumnType());
        cDTO.setRequired(safxcolumn.getRequired());
        cDTO.setSize(safxcolumn.getSize());
        cDTO.setPosition(safxcolumn.getPosition());
        
        DSColumn dsColumn = safxcolumn.getDsColumn();
        if (dsColumn != null) {
            cDTO.setDsColumnId(dsColumn.getId());
            cDTO.setDsColumnName(dsColumn.getName());
            cDTO.setDsColumn(DSColumnMapper.map(dsColumn));
        }
        return cDTO;
    }

    public static SAFXColumnDomain mapCriteria(SAFXColumn safxcolumn) {
        SAFXColumnDomain cDTO = new SAFXColumnDomain();
        cDTO.setId(safxcolumn.getId());
        cDTO.setName(safxcolumn.getName());
        SAFXTableDomain safxTableDTO = new SAFXTableDomain();
        SAFXTable safxTable = safxcolumn.getSafxTable();
        safxTableDTO.setId(safxTable.getId());
        safxTableDTO.setName(safxTable.getName());
        cDTO.setSafxTable(safxTableDTO);
        return cDTO;
    }
    
    public static SAFXColumn map(SAFXColumnDomain safxcolumn) {
        SAFXColumn c = new SAFXColumn();
        c.setId(safxcolumn.getId());
        return c; 
    }

}
