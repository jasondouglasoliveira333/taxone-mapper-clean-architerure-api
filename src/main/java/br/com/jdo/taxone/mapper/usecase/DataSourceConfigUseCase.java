package br.com.jdo.taxone.mapper.usecase;

import java.util.List;
import java.util.stream.Collectors;

import br.com.jdo.taxone.mapper.domain.entity.DSColumnDomain;
import br.com.jdo.taxone.mapper.domain.entity.DSTableDomain;
import br.com.jdo.taxone.mapper.domain.entity.DataSourceConfigurationDomain;
import br.com.jdo.taxone.mapper.domain.enums.DataSourceType;
import br.com.jdo.taxone.mapper.interfaces.mapper.DSColumnMapper;
import br.com.jdo.taxone.mapper.interfaces.mapper.DSTableMapper;
import br.com.jdo.taxone.mapper.interfaces.repository.DSColumnRepository;
import br.com.jdo.taxone.mapper.interfaces.repository.DSTableRepository;
import br.com.jdo.taxone.mapper.interfaces.repository.DataSourceConfigRepository;

public class DataSourceConfigUseCase {

    private DataSourceConfigRepository dataSourceConfigRepository;
    
    private DSTableRepository dsTableRepository;

    private DSColumnRepository dsColumnRepository;
    
    public DataSourceConfigUseCase(DataSourceConfigRepository dataSourceConfigRepository, 
        DSTableRepository dsTableRepository, DSColumnRepository dsColumnRepository){
        this.dataSourceConfigRepository = dataSourceConfigRepository;
        this.dsTableRepository = dsTableRepository;
        this.dsColumnRepository = dsColumnRepository;
    }
    

    public List<DataSourceConfigurationDomain> list() {
        return dataSourceConfigRepository.findAll();
    }

    public DataSourceConfigurationDomain get(String dataSourceType) {
        return dataSourceConfigRepository.findByDataSourceType(DataSourceType.valueOf(dataSourceType));
    }

    public List<DSTableDomain> getDSTables(String dataSourceType) {
        return dsTableRepository.findBydataSourceConfigurationDataSourceType(DataSourceType.valueOf(dataSourceType)).
                stream().map(DSTableMapper::map).collect(Collectors.toList());
    }

    public int saveDataSourrce(DataSourceConfigurationDomain dsDomain) {
        return dataSourceConfigRepository.save(dsDomain).getId();
    }

    public void saveTablesAndColumns(Integer dataSourceConfigId, DSTableDomain dsTable, List<DSColumnDomain> dsColumnsList) {
        //@TODO Atualizar
        DSTableDomain dst = dsTableRepository.findFirstBydataSourceConfigurationIdAndName(dataSourceConfigId, dsTable.getName());
        if (dst == null) {
            dst = new DSTableDomain();
            dst.setDataSourceConfiguration(dataSourceConfigRepository.getOne(dataSourceConfigId));
            dst.setName(dsTable.getName());
            dsTableRepository.save(dst);
        }
//        System.out.println("dst:" + dst);
        
        for (DSColumnDomain dsColumnDTO : dsColumnsList)  {
            DSColumnDomain dsc = dsColumnRepository.findFirstBydsTableIdAndName(dst.getId(), dsColumnDTO.getName());
            if (dsc == null) {
                dsc = new DSColumnDomain();
                dsc.setDsTable(dst);
            }else {
                DSColumnMapper.marge(dsColumnDTO, dsc);
            }
            dsColumnRepository.save(dsc);
        }
    }





}
