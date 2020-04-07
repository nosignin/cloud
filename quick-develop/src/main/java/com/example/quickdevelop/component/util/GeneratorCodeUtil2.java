package com.example.quickdevelop.component.util;

import com.example.quickdevelop.component.constant.CodeTypeConstant;
import com.example.quickdevelop.component.dto.Column;
import com.example.quickdevelop.component.dto.Table;
import com.example.quickdevelop.component.enums.CodeTypeEnum;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.*;

public class GeneratorCodeUtil2 {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345666";
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    private static final String MODEL_FTL = "freemarker-template/Model.ftl";
    private static final String CTRL_FTL = "freemarker-template/Ctrl.ftl";
    private static final String SERVICE_FTL = "freemarker-template/Service.ftl";
    private static final String SERVICE_IMPL_FTL = "freemarker-template/ServiceImpl.ftl";
    private static final String MAPPER_FTL = "freemarker-template/Mapper.ftl";
    private static final String DTO_FTL = "freemarker-template/Dto.ftl";

    private static String generatorBasePath = null;

    private static Connection connection = null;

    public static void init(String dbName,String GENERATOR_BASE_PATH) throws Exception{
        String url = "jdbc:mysql://127.0.0.1:3306/"+dbName+"?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false";
        Class.forName(DRIVER_CLASS_NAME);
        connection= DriverManager.getConnection(url, USERNAME, PASSWORD);
        generatorBasePath = GENERATOR_BASE_PATH;
    }

    private static String changeTableName(String table_name){
        String tableNameArr[] = table_name.split("_");
        StringBuilder stringBuilder = new StringBuilder();
        //不获取前缀
//        for(int i=1;i<tableNameArr.length;i++){
//            stringBuilder.append(tableNameArr[i].substring(0,1).toUpperCase()+tableNameArr[i].substring(1));
//        }
        for(int i=0;i<tableNameArr.length;i++){
            stringBuilder.append(tableNameArr[i].substring(0,1).toUpperCase()+tableNameArr[i].substring(1));
        }
        return stringBuilder.toString();
    }

    private static String changeColumnName(String columnName){
        if(columnName.indexOf("_")==-1){
            columnName=columnName.substring(0,1).toLowerCase()+columnName.substring(1);
            return columnName;
        }
        String columnNameArr[] = columnName.toLowerCase().split("_");
        for(int i=0;i<columnNameArr.length;i++){
            if(i>0){
                columnNameArr[i]=columnNameArr[i].substring(0,1).toUpperCase()+columnNameArr[i].substring(1);
            }
        }
        return StringUtils.join(columnNameArr);
    }

    private static String changeColumnType(String columnType){
        if("VARCHAR".equals(columnType)||"CHAR".equals(columnType)||"TEXT".equals(columnType)){
            columnType="String";
        }
        else if("DATETIME".equals(columnType)||"DATE".equals(columnType)){
            columnType="Date";
        }
        else if("BIGINT".equals(columnType)){
            columnType="Long";
        }
        else if("INT".equals(columnType)||"SMALLINT".equals(columnType)){
            columnType="Integer";
        }
        else if("DOUBLE".equals(columnType)||"DECIMAL".equals(columnType)){
            columnType="Double";
        }
        return columnType;
    }

    private static String getTemplateContent(String ftlPath){
        Resource resource = new ClassPathResource(ftlPath);
        StringBuilder stringBuilder = new StringBuilder();
        try (
                InputStream inputStream = resource.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))
        ) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private static Template getTemplate(String codeTypeConstant, String templateContent) throws Exception{
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        stringTemplateLoader.putTemplate(codeTypeConstant,templateContent);
        Configuration configuration =new Configuration(Configuration.getVersion());
        configuration.setTemplateLoader(stringTemplateLoader);
        Template template = configuration.getTemplate(codeTypeConstant,"utf-8");
        return template;
    }

    private static Map<String,Object> getReplaceMap(String table_name,String packageName,String tableComment) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();

        Set<String> columnTypeSet = new HashSet<String>();
        List<Column> columnList = new LinkedList<Column>();

        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet resultSet = databaseMetaData.getColumns(connection.getCatalog(),"%", table_name,"%");

        while(resultSet.next()){
            String COLUMN_NAME = resultSet.getString("COLUMN_NAME");
            String TYPE_NAME = resultSet.getString("TYPE_NAME");
            String REMARKS = resultSet.getString("REMARKS");

            String columnName = changeColumnName(COLUMN_NAME);
            String columnType = changeColumnType(TYPE_NAME);

            Column column = new Column();
            column.setColumn_name(COLUMN_NAME);
            column.setColumnName(columnName);
            column.setColumn_type(TYPE_NAME);
            column.setColumnType(columnType);
            column.setColumnComment(REMARKS);

            columnList.add(column);
            columnTypeSet.add(columnType);
        }

        String tableName = changeTableName(table_name);
        Table table = new Table();
        table.setPackageName(packageName);
        table.setTable_name(table_name);
        table.setTableComment(tableComment);

        table.setTableName(tableName);
        String tableNameLowercase = tableName.substring(0,1).toLowerCase()+tableName.substring(1);
        table.setTableNameLowercase(tableNameLowercase);
        table.setColumnList(columnList);
        table.setColumnTypeSet(columnTypeSet);

        map.put("Table",table);

        return map;
    }

    private static void generatorFile(String filePath,String replaceTemplateContent) throws Exception{
        File file = new File(filePath);
        File fileParent = file.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        ByteBuffer byteBuffer = ByteBuffer.allocate(102400);
        byteBuffer.put(replaceTemplateContent.getBytes());
        byteBuffer.flip() ;
        while(byteBuffer.hasRemaining()) {
            fos.write(byteBuffer.get());
        }
        byteBuffer.clear();
    }

    public static void generatorCode(
                                     String table_name,
                                     String tableComment,
                                     String packageName,
                                     CodeTypeEnum codeTypeEnum
                                     ) throws Exception{

        Map<String,Object> map = getReplaceMap(table_name,packageName,tableComment);
        String tableName = ((Table)(map.get("Table"))).getTableName();

        String model_filePath = null;
        String ctrl_filePath = null;
        String service_filePath = null;
        String serviceImpl_filePath = null;
        String mapper_filePath = null;
        String dto_filePath = null;

        String generatorPath = generatorBasePath + "/"+packageName.replace(".","/");
        switch (codeTypeEnum){
            case MODEL:
                model_filePath = generatorPath+"/model/"+tableName+".java";
                execGenerator(MODEL_FTL, CodeTypeConstant.MODEL,map,model_filePath);
                break;
            case CTRL:
                ctrl_filePath = generatorPath+"/ctrl/"+tableName+"Ctrl.java";
                execGenerator(CTRL_FTL, CodeTypeConstant.CTRL,map,ctrl_filePath);
                break;
            case SERVICE:
                service_filePath = generatorPath+"/service/"+tableName+"Service.java";
                execGenerator(SERVICE_FTL, CodeTypeConstant.SERVICE,map,service_filePath);
                break;
            case SERVICE_IMPL:
                serviceImpl_filePath = generatorPath+"/service/impl/"+tableName+"ServiceImpl.java";
                execGenerator(SERVICE_IMPL_FTL, CodeTypeConstant.SERVICE_IMPL,map,serviceImpl_filePath);
                break;
            case MAPPER:
                mapper_filePath = generatorPath+"/mapper/"+tableName+"Mapper.java";
                execGenerator(MAPPER_FTL, CodeTypeConstant.MAPPER,map,mapper_filePath);
                break;
            case DTO:
                dto_filePath = generatorPath+"/dto/"+tableName+"Dto.java";
                execGenerator(DTO_FTL, CodeTypeConstant.DTO,map,dto_filePath);
                break;
            case ALL:
                model_filePath = generatorPath+"/model/"+tableName+".java";
                execGenerator(MODEL_FTL, CodeTypeConstant.MODEL,map,model_filePath);

                ctrl_filePath = generatorPath+"/ctrl/"+tableName+"Ctrl.java";
                execGenerator(CTRL_FTL, CodeTypeConstant.CTRL,map,ctrl_filePath);

                service_filePath = generatorPath+"/service/"+tableName+"Service.java";
                execGenerator(SERVICE_FTL, CodeTypeConstant.SERVICE,map,service_filePath);

                serviceImpl_filePath = generatorPath+"/service/impl/"+tableName+"ServiceImpl.java";
                execGenerator(SERVICE_IMPL_FTL, CodeTypeConstant.SERVICE_IMPL,map,serviceImpl_filePath);

                mapper_filePath = generatorPath+"/mapper/"+tableName+"Mapper.java";
                execGenerator(MAPPER_FTL, CodeTypeConstant.MAPPER,map,mapper_filePath);

                dto_filePath = generatorPath+"/dto/"+tableName+"Dto.java";
                execGenerator(DTO_FTL, CodeTypeConstant.DTO,map,dto_filePath);
                break;
            default:
                break;
        }

    }

    private static void execGenerator(String ftlPath,String codeTypeConstant,Map<String,Object> map,String filePath) throws Exception{
        String templateContent = getTemplateContent(ftlPath);
        Template template = getTemplate(codeTypeConstant,templateContent);
        String replaceTemplateContent = FreeMarkerTemplateUtils.processTemplateIntoString(template,map);
        generatorFile(filePath,replaceTemplateContent);
    }

}
