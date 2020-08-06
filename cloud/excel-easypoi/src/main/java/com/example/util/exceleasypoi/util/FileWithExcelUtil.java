package com.example.util.exceleasypoi.util;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 石佳
 * @since 2020/08/06
 */
@Slf4j
public class FileWithExcelUtil {
    /**
    *  导入excel
    * @param file 文件
    * @param headerRows 表头行数
    * @param pojoClass 类对象
    * @return java.util.List<T>
    * @author 石佳
    * @since 2020/8/6
    */
    public static <T> List<T> importExcel(MultipartFile file,  int headerRows, Class<T> pojoClass) {
        if(file==null){
            return null;
        }
        ImportParams importParams = new ImportParams();
        importParams.setHeadRows(headerRows);
        List<T> list;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(),pojoClass,importParams);
        }catch (Exception e){
            e.printStackTrace();
            log.error("[monitor][表单功能]", e);
            return null;
        }
        return list;
    }
}
