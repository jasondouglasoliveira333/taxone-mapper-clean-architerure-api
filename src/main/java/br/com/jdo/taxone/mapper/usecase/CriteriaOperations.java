package br.com.jdo.taxone.mapper.usecase;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import br.com.jdo.taxone.mapper.domain.entity.CriteriaDomain;
import br.com.jdo.taxone.mapper.domain.entity.DataDomain;
import br.com.jdo.taxone.mapper.domain.enums.ColumnType;
import br.com.jdo.taxone.mapper.util.DateUtil;

interface OPERATIONS { boolean test(Field f, DataDomain d, CriteriaDomain c); }

public class CriteriaOperations{
    
    static OPERATIONS EQUALS = (f,d,c) -> { try { return f.get(d).toString().equals(c.getValue()); } catch (Exception e) {e.printStackTrace(); return false;} };
    static OPERATIONS NOT_EQUALS = (f,d,c) -> { try { return !f.get(d).toString().equals(c.getValue()); } catch (Exception e) {e.printStackTrace(); return false;} };
    static OPERATIONS EMPTY = (f,d,c) -> { try { return f.get(d) == null ; } catch (Exception e) {return false;} };
    static OPERATIONS NOT_EMPTY = (f,d,c) -> { try { return f.get(d) != null; } catch (Exception e) {return false;} };

    static OPERATIONS GREATHER_EQUALS = (f,d,c) -> 
        { 
            try {
                if (c.getSafxColumn().getColumnType().equals(ColumnType.DATETIME)) {
                    return DateUtil.parseDateyyyyMMdd(f.get(d).toString()).getTime() >= DateUtil.parseDateyyyyMMdd(c.getValue().toString()).getTime();
                }else {
                    return Long.valueOf(f.get(d).toString()) >= Long.valueOf(c.getValue().toString());
                }
            } catch (Exception e) {
                e.printStackTrace(); 
                return false;
            } 
        };
    static OPERATIONS LESSER_EQUALS = (f,d,c) -> 
        { 
            try {
                if (c.getSafxColumn().getColumnType().equals(ColumnType.DATETIME)) {
                    return DateUtil.parseDateyyyyMMdd(f.get(d).toString()).getTime() <= DateUtil.parseDateyyyyMMdd(c.getValue().toString()).getTime();
                }else {
                    return Long.valueOf(f.get(d).toString()) <= Long.valueOf(c.getValue().toString());
                }
            } catch (Exception e) {
                e.printStackTrace(); 
                return false;
            } 
        };
    
    static OPERATIONS BETWEEN = (f,d,c) -> 
        { 
            try {
                if (c.getSafxColumn().getColumnType().equals(ColumnType.DATETIME)) {
                    return DateUtil.parseDateyyyyMMdd(f.get(d).toString()).getTime() >= DateUtil.parseDateyyyyMMdd(c.getValue().toString()).getTime()    
                            && DateUtil.parseDateyyyyMMdd(f.get(d).toString()).getTime() <= DateUtil.parseDateyyyyMMdd(c.getAdditionalValue().toString()).getTime() ;
                }else {
                    return Long.valueOf(f.get(d).toString()) >= Long.valueOf(c.getValue().toString()) 
                            && Long.valueOf(f.get(d).toString()) <= Long.valueOf(c.getAdditionalValue().toString());
                }
            } catch (Exception e) {
                e.printStackTrace(); 
                return false;
            } 
        };

        public static Map<String, OPERATIONS> OPERATIONS = new HashMap<>();
    
    static {
        OPERATIONS.put("=", EQUALS);
        OPERATIONS.put("!=", NOT_EQUALS);
        OPERATIONS.put("!=", NOT_EQUALS);
        OPERATIONS.put("empty", EMPTY);
        OPERATIONS.put("not empty", NOT_EMPTY);
        OPERATIONS.put(">=", GREATHER_EQUALS);
        OPERATIONS.put("<=", LESSER_EQUALS);
        OPERATIONS.put("between", BETWEEN);
    }
    
}