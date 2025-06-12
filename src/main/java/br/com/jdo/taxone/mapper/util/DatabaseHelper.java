package br.com.jdo.taxone.mapper.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.jdo.taxone.mapper.domain.entity.DSColumnDomain;
import br.com.jdo.taxone.mapper.domain.entity.DSTableDomain;
import br.com.jdo.taxone.mapper.domain.entity.DataSourceConfigurationDomain;
import br.com.jdo.taxone.mapper.domain.enums.ColumnType;

public class DatabaseHelper {

    private static List<String> STRING_COLUMN_NAMES = Arrays.asList("varchar", "char", "clob");
    private static List<String> NUMBER_COLUMN_NAMES = Arrays.asList("int", "serial", "number", "long");
    private static List<String> DATE_COLUMN_NAMES = Arrays.asList("datetime", "date", "time", "timestamp");
    
    public static List<DSColumnDomain> getTableMetadata(DataSourceConfigurationDomain dsDTO) throws Exception{
        List<DSColumnDomain> dsList = new ArrayList<>();
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        try {
            c = DriverManager.getConnection(dsDTO.getUrl(), dsDTO.getUsername(), dsDTO.getPassword());
            List<String> tables = Arrays.asList(dsDTO.getResourceNames().split(","));
            for (String table : tables) {
                s = c.createStatement();
                rs = s.executeQuery("select * from " + table);
                rsmd = rs.getMetaData();
                for (int x=1; x <= rsmd.getColumnCount(); x++) {
                    DSColumnDomain dsCDTO = new DSColumnDomain();
                    dsCDTO.setName(rsmd.getColumnName(x).toUpperCase());
                    dsCDTO.setColumnType(getColumnType(rsmd.getColumnTypeName(x)));
                    dsCDTO.setSize(rsmd.getColumnDisplaySize(x));
                    DSTableDomain dsTable = new DSTableDomain();
                    dsTable.setName(table);
                    dsCDTO.setDsTable(dsTable);
                    dsList.add(dsCDTO);
                }
                rs.close();
                s.close();
            }
        } finally {
            if (rs != null) { try {rs.close();} catch (Exception e) {}}
            if (s != null) { try {s.close();} catch (Exception e) {}}
            if (c != null) { try {c.close();} catch (Exception e) {}}
        }
        
        return dsList;
    }

    private static ColumnType getColumnType(String columnTypeName) {
        if (STRING_COLUMN_NAMES.contains(columnTypeName.toLowerCase())) {
            return ColumnType.VARCHAR;
        }else if (NUMBER_COLUMN_NAMES.contains(columnTypeName.toLowerCase())) {
            return ColumnType.NUMERIC;
        }else if (DATE_COLUMN_NAMES.contains(columnTypeName.toLowerCase())) {
            return ColumnType.DATETIME;
        }else {
            return ColumnType.VARCHAR;
        }
    }

}
