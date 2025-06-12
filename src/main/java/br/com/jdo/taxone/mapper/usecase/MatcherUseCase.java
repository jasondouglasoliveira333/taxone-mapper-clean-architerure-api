package br.com.jdo.taxone.mapper.usecase;

import java.util.List;
import java.util.stream.Collectors;

import br.com.jdo.taxone.mapper.domain.entity.DSColumnDomain;
import br.com.jdo.taxone.mapper.domain.entity.DSTableDomain;
import br.com.jdo.taxone.mapper.domain.entity.DataSourceConfigurationDomain;
import br.com.jdo.taxone.mapper.domain.entity.PageResponse;
import br.com.jdo.taxone.mapper.domain.entity.SAFXColumnDomain;
import br.com.jdo.taxone.mapper.domain.entity.SAFXColumnUpdateDomain;
import br.com.jdo.taxone.mapper.domain.entity.SAFXTableDomain;
import br.com.jdo.taxone.mapper.domain.enums.DataSourceType;
import br.com.jdo.taxone.mapper.interfaces.mapper.DSTableMapper;
import br.com.jdo.taxone.mapper.interfaces.repository.DSColumnRepository;
import br.com.jdo.taxone.mapper.interfaces.repository.DSTableRepository;
import br.com.jdo.taxone.mapper.interfaces.repository.SAFXColumnRepository;
import br.com.jdo.taxone.mapper.interfaces.repository.SAFXTableRepository;
import br.com.jdo.taxone.mapper.util.DatabaseHelper;
import br.com.jdo.taxone.mapper.util.FTPHelper;
import br.com.jdo.taxone.mapper.util.FileHelper;
import br.com.jdo.taxone.mapper.util.StringUtil;

public class MatcherUseCase {
    
    private SAFXTableRepository safxTableRepository; 

    private SAFXColumnRepository safxColumnRepository; 
    
    private DSTableRepository dsTableRepository;

    private DSColumnRepository dsColumnRepository;

    public MatcherUseCase(SAFXTableRepository safxTableRepository, SAFXColumnRepository safxColumnRepository,
            DSTableRepository dsTableRepository, DSColumnRepository dsColumnRepository) {
        this.safxTableRepository = safxTableRepository;
        this.safxColumnRepository = safxColumnRepository;
        this.dsTableRepository = dsTableRepository;
        this.dsColumnRepository = dsColumnRepository;
    }

    public PageResponse<SAFXTableDomain> findAllSafx(String name, Boolean justAssociated, Integer page, Integer size) {
        PageResponse<SAFXTableDomain> sfResponse = safxTableRepository.findByNameAndAssociated(StringUtil.putPercent(name), justAssociated, page, size);
        System.out.println("sfResponse.getContent().size:" + sfResponse.getContent().size());
        return sfResponse;
    }

    public SAFXTableDomain getSAFXTable(Integer id) {
        return safxTableRepository.getOne(id);
    }

    public List<SAFXColumnDomain> getSAFXColumns(Integer id, Boolean associated) {
        List<SAFXColumnDomain> scList = safxColumnRepository.findBysafxTableId(id); 
        return scList.stream().filter(sc -> associated ? sc.getDsColumn() != null : true).collect(Collectors.toList());
    }

    public PageResponse<DSColumnDomain> getDSColumns(Integer id, Integer page, Integer size) {
        PageResponse<DSColumnDomain> dcPage = dsColumnRepository.findBydsTableId(id, page, size);
        return dcPage;
    }

    public List<DSTableDomain> getDSTables() {
        return dsTableRepository.findAll().stream().map(DSTableMapper::map).collect(Collectors.toList());
    }

    public void updateSAFXColumns(List<SAFXColumnUpdateDomain> safxColumns) {
        safxColumns.stream().forEach(safxColumn -> {
            safxColumnRepository.updateSAFXColumn(safxColumn.getId(), safxColumn.getDsColumnId());
        });
    }

    public void updateSAFXTable(Integer id, Integer dsTableId) {
        safxTableRepository.updateDSTable(id, dsTableId);
    }

    public List<DSColumnDomain> getDSMetadata(DataSourceConfigurationDomain dataSource) throws Exception{
        List<DSColumnDomain> dsList = null;
        if (dataSource.getDataSourceType().equals(DataSourceType.Database)) {
            dsList = DatabaseHelper.getTableMetadata(dataSource);
        }else if (dataSource.getDataSourceType().equals(DataSourceType.TXT)) {
            dsList = FileHelper.getFileMetadata(dataSource);
        }else {
            dsList = FTPHelper.getFileMetadata(dataSource);
        }
        return dsList;
    }

}
