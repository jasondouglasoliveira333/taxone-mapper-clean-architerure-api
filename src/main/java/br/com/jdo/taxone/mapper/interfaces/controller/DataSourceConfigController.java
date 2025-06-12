package br.com.jdo.taxone.mapper.interfaces.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.jdo.taxone.mapper.domain.entity.DSColumnDomain;
import br.com.jdo.taxone.mapper.domain.entity.DSTableDomain;
import br.com.jdo.taxone.mapper.domain.entity.DataSourceConfigurationDomain;
import br.com.jdo.taxone.mapper.domain.entity.POCUser;
import br.com.jdo.taxone.mapper.domain.entity.PageResponse;
import br.com.jdo.taxone.mapper.domain.enums.DataSourceType;
import br.com.jdo.taxone.mapper.usecase.DataSourceConfigUseCase;
import br.com.jdo.taxone.mapper.usecase.MatcherUseCase;

public class DataSourceConfigController {
    
    private DataSourceConfigUseCase dataSourceConfigUseCase;
    
    private MatcherUseCase matcherUseCase;
    
    private Map<Integer, List<DSTableDomain>> dsTableTemporary = new HashMap<>();
    private Map<Integer, List<DSColumnDomain>> dsColumnsTemporary = new HashMap<>();
    
    public DataSourceConfigUseCase dataSourceConfigUseCase() {
        return this.dataSourceConfigUseCase;
    }

    public MatcherUseCase matcherUseCase() {
        return this.matcherUseCase;
    }

    public Object list(){
        return dataSourceConfigUseCase().list();
    }
    
    public Object get(String dataSourceType){
        clearUserTableAndColumns();//clear the tables and columns of cache
        return dataSourceConfigUseCase().get(dataSourceType);
    }
    
    public Object listDsTables(String dataSourceType){
        return dataSourceConfigUseCase().getDSTables(dataSourceType);
    }
    
    public Object listDsColumns(Integer dsTableId, Integer page, Integer size){
        PageResponse<DSColumnDomain> dsCPage = null; 
        List<DSColumnDomain> dscList = dsColumnsTemporary.get(dsTableId);
        if (dscList != null) {
            dsCPage = new PageResponse<>();
            int lastIdx = page * size + size;
            if (lastIdx > dscList.size()) {
                lastIdx = dscList.size();  
            }
            dsCPage.setContent(dscList.subList(page * size, lastIdx));
            int totalPages = dscList.size() / size + (dscList.size() % size == 0 ? 0 : 1);
            System.out.println("totalPages:" + totalPages);
            dsCPage.setTotalPages(totalPages);
        }else {
            dsCPage = matcherUseCase().getDSColumns(dsTableId, page, size);
        }
        return dsCPage;
    }
    
    public Object getMetadata(String dataSourceType,  @RequestBody DataSourceConfigurationDomain dataSourceDTO) throws Exception{
        dataSourceDTO.setDataSourceType(DataSourceType.valueOf(dataSourceType));
        List<DSColumnDomain> dsList = matcherUseCase().getDSMetadata(dataSourceDTO);
        System.out.println("dsListMetadata.size:" + dsList.size());
        clearUserTableAndColumns();
        loadTableAndColumns(dataSourceDTO.getResourceNames(), dsList);
        return null;
    }
    
    
    public Object saveDataSourrce(String dataSourceType,  @RequestBody DataSourceConfigurationDomain dataSourceDTO){
        POCUser user = (POCUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        dataSourceDTO.setDataSourceType(DataSourceType.valueOf(dataSourceType));
        int dsId = dataSourceConfigUseCase().saveDataSourrce(dataSourceDTO);
        if (dsTableTemporary.get(user.getId()) != null) {
            dsTableTemporary.get(user.getId()).forEach(dsTable -> {
                dataSourceConfigUseCase().saveTablesAndColumns(dsId, dsTable, dsColumnsTemporary.get(dsTable.getId()));
            });
        }
        clearUserTableAndColumns();
        return null;
    }


    private void loadTableAndColumns(String tableNames, List<DSColumnDomain> dsList) {
        POCUser user = (POCUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> tables = Arrays.asList(tableNames.split(","));
        tables.stream().forEach(tableName -> {
            List<DSColumnDomain> dsCList = dsList.stream().filter(dsc -> dsc.getDsTable().getName().equals(tableName)).collect(Collectors.toList());
            int pseudoId = (int)(Math.random() * 100000);
            DSTableDomain dstDTO = new DSTableDomain();
            dstDTO.setId(pseudoId);
            dstDTO.setName(tableName);
            List<DSTableDomain> dstList = dsTableTemporary.get(user.getId());
            if (dstList == null) {
                dstList = new ArrayList<>();
                dsTableTemporary.put(user.getId(), dstList);
            }
            dstList.add(dstDTO);
            dsColumnsTemporary.put(pseudoId, dsCList);
        });

    }

    private void clearUserTableAndColumns() {
        POCUser user = (POCUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (dsTableTemporary.get(user.getId()) != null) {
            dsTableTemporary.get(user.getId()).forEach(dsTable -> {
                dsColumnsTemporary.remove(dsTable.getId());
            });
            dsTableTemporary.remove(user.getId());
        }
    }
    
}
