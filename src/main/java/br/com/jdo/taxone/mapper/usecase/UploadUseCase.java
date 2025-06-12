package br.com.jdo.taxone.mapper.usecase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import br.com.jdo.taxone.mapper.domain.entity.PageResponse;
import br.com.jdo.taxone.mapper.domain.entity.UploadDomain;
import br.com.jdo.taxone.mapper.domain.enums.ColumnType;
import br.com.jdo.taxone.mapper.domain.enums.UploadStatus;
import br.com.jdo.taxone.mapper.infrastructure.entity.SAFXColumn;
import br.com.jdo.taxone.mapper.infrastructure.entity.SAFXTable;
import br.com.jdo.taxone.mapper.infrastructure.entity.Upload;
import br.com.jdo.taxone.mapper.interfaces.repository.SAFXColumnRepository;
import br.com.jdo.taxone.mapper.interfaces.repository.SAFXTableRepository;
import br.com.jdo.taxone.mapper.interfaces.repository.UploadRepository;
import br.com.jdo.taxone.mapper.util.JExcelHelper;
import br.com.jdo.taxone.mapper.util.XLSField;
import br.com.jdo.taxone.mapper.util.XLSTable;

public class UploadUseCase {
    
    @SuppressWarnings("unused")
    private SAFXTableRepository safxTableRepository; 
    
    @SuppressWarnings("unused")
    private SAFXColumnRepository safxColumnRepository;

    private UploadRepository uploadReponsitory;
    
    public UploadUseCase(SAFXTableRepository safxTableRepository, SAFXColumnRepository safxColumnRepository, 
            UploadRepository uploadReponsitory) {
        this.safxTableRepository = safxTableRepository;
        this.safxColumnRepository = safxColumnRepository;
        this.uploadReponsitory = uploadReponsitory;
    }

    public static void main(String[] args) throws IOException, Exception {
        String file = "C:\\jason\\generic_workspace_new\\quarkus\\Manual_Layout_MastersafDW_down.xls";
        new UploadUseCase(null, null, null).parseFileAndStore("Manual_Layout_MastersafDW.xls", "256.1.0", Files.readAllBytes(Paths.get(file)));
    }
    

    @Transactional
    public void parseFileAndStore(String fileName, String layoutVersion, byte[] data) throws Exception {
        List<XLSTable> xlsTables = JExcelHelper.readSAFXINfo(data);

        xlsTables.stream().forEach(table -> {
//        XLSTable table = xlsTables.get(0);
            //Verify if table exists
            System.out.println("tName:" + table.getName());// + " - desc:" + table.getDescription() + " - fields:" + table.getFields());
            SAFXTable t = null;//safxTableRepository.findByName(table.getName());
            boolean newTable = false;
            if (t == null) {
                newTable = true;
                t = new SAFXTable();
            }
            t.setName(table.getName());
            t.setDescription(table.getDescription());
            //safxTableRepository.save(t);
                
            for (XLSField field : table.getFields()) {
                SAFXColumn c = null;
                if (newTable) {
                    c = new SAFXColumn();
                }else {
                    c = new SAFXColumn();//safxColumnRepository.findFirstBysafxTableIdAndName(t.getId(), field.getColumnName());
                }
                c.setName(field.getColumnName());
                c.setColumnType(getColumnType(field));
                c.setRequired(field.getRequired());
                try {
                    c.setSize(Integer.parseInt(field.getSize()));
                }catch (Exception e) {
                    c.setSize(3);
                }
                c.setPosition(field.getIndex());
                c.setSafxTable(t);
                //safxColumnRepository.save(c);
            }
        });
        
        //uploadReponsitory.updateStatus(UploadStatus.CANCELED);
        
        //POCUser user = (POCUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        Upload u = new Upload();
        u.setFileName(fileName);
        u.setLayoutVersion(layoutVersion);
        u.setCreationDate(LocalDateTime.now());
        u.setStatus(UploadStatus.ACTIVE);
        //u.setUser(new User(user.getId()));
        //uploadReponsitory.save(u);
    }

    private ColumnType getColumnType(XLSField field) {
        if (field.getType().equals("A")){
            return ColumnType.VARCHAR;
        }else if(field.getColumnName().startsWith("DT_") || field.getColumnName().startsWith("DAT_") 
                || field.getColumnName().startsWith("DATA_") || field.getDescription().contains("Data")) {
            return ColumnType.DATETIME;
        }else {
            return ColumnType.NUMERIC;
        }
    }

    public PageResponse<UploadDomain> findAll(Integer page, Integer size) {
        PageResponse<UploadDomain> uPage = uploadReponsitory.findAll(page, size );
        System.out.println("uPage:" + uPage.getTotalPages());
        return uPage;
    }

    
}
