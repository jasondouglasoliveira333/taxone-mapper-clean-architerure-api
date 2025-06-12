package br.com.jdo.taxone.mapper.domain.entity;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class DATAHolder {
    private Map<String, Field> fieldMappings; 
    private List<DataDomain> list;
    
    public Map<String, Field> getFieldMappings() {
        return fieldMappings;
    }
    public void setFieldMappings(Map<String, Field> fieldMappings) {
        this.fieldMappings = fieldMappings;
    }
    public List<DataDomain> getList() {
        return list;
    }
    public void setList(List<DataDomain> list) {
        this.list = list;
    }
    
}
