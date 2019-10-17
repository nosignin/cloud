package ${Table.packageName}.mapper;

import ${Table.packageName}.dto.${Table.tableName}Dto;
import ${Table.packageName}.model.${Table.tableName};
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Set;

@Mapper
public interface ${Table.tableName}Mapper {

    String columns = <#list Table.columnList as Column>
        "${Column.column_name}<#if Column_has_next>,</#if>"<#if Column_has_next>+<#else>;</#if></#list>

    String params = <#list Table.columnList as Column>
        "<#noparse>#{</#noparse>${Column.columnName}<#noparse>}</#noparse><#if Column_has_next>,</#if>"<#if Column_has_next>+<#else>;</#if></#list>

    String batchParams = <#list Table.columnList as Column>
        "<#noparse>#{</#noparse>${Table.tableNameLowercase}.${Column.columnName}<#noparse>}</#noparse><#if Column_has_next>,</#if>"<#if Column_has_next>+<#else>;</#if></#list>

    String optionalParams = <#list Table.columnList as Column>
                                <#if Column.columnType == "Date" || Column.columnType == "Long" || Column.columnType == "Integer" || Column.columnType == "Double">
        "<if test='${Column.columnName}!=null'> and ${Column.column_name} = <#noparse>#{</#noparse>${Column.columnName}<#noparse>}</#noparse></if>"<#if Column_has_next>+<#else>;</#if><#else>        "<if test='${Column.columnName}!=null and ${Column.columnName}!=\"\"'> and ${Column.column_name} = <#noparse>#{</#noparse>${Column.columnName}<#noparse>}</#noparse></if>"<#if Column_has_next>+<#else>;</#if></#if></#list>

    String idSetOptionalParams =
        "<if test='idSet!=null and idSet.size()>0'> and id in "+
            "<foreach collection='idSet' item='id' open='(' close=')' separator=','>"+
                "<#noparse>#{</#noparse>id<#noparse>}</#noparse>"+
            "</foreach>"+
        "</if>";

    // 增加
    @Insert("insert into ${Table.table_name}("+
        columns+
    ")values("+
        params+
    ")")
    void add(${Table.tableName} ${Table.tableNameLowercase});

    // 根据id删除
    @Delete("delete from ${Table.table_name} where id = <#noparse>#{</#noparse>id<#noparse>}</#noparse>")
    void deleteById(Long id);

    // 根据id查询
    @Select("select * from ${Table.table_name} where id = <#noparse>#{</#noparse>id<#noparse>}</#noparse>")
    ${Table.tableName} findById(Long id);

    // 根据id修改
    @Update("<script>"+
    "update ${Table.table_name} set "+
    "<trim suffix='' suffixOverrides=','>"+
    <#list Table.columnList as Column>
        <#if Column.column_name != "id">
            <#if Column.columnType == "Date" || Column.columnType == "Long" || Column.columnType == "Integer" || Column.columnType == "Double">
        "<if test='${Column.columnName}!=null' >${Column.column_name}=<#noparse>#{</#noparse>${Column.columnName}<#noparse>}</#noparse><#if Column_has_next>,</#if></if>"+
            <#else>
        "<if test='${Column.columnName}!=null and ${Column.columnName}!= \"\"'>${Column.column_name}=<#noparse>#{</#noparse>${Column.columnName}<#noparse>}</#noparse><#if Column_has_next>,</#if></if>"+
            </#if>
        </#if>
    </#list>
    "</trim>"+
    "where id = <#noparse>#{</#noparse>id<#noparse>}</#noparse>"+
    "</script>")
    void updateById(${Table.tableName} ${Table.tableNameLowercase});

    // 根据参数查询
    @Select("<script>"+
    "select * from ${Table.table_name} where 1=1"+
        optionalParams+
        idSetOptionalParams+
    "</script>")
    List<${Table.tableName}Dto> find(${Table.tableName}Dto ${Table.tableNameLowercase}Dto);

    // 根据idSet批量删除
    @Delete("<script>"+
    "delete from ${Table.table_name} where id in "+
    "<foreach collection='idSet' item='id' open='(' close=')' separator=','>"+
        "<#noparse>#{</#noparse>id<#noparse>}</#noparse>"+
    "</foreach>"+
    "</script>")
    void deleteByIdSet(@Param("idSet") Set<Long> idSet);

    // 批量增加
    @Insert("<script>"+
    "insert into ${Table.table_name}("+
        columns+
    ")values"+
    "<foreach collection='${Table.tableNameLowercase}List' item='${Table.tableNameLowercase}' separator=','>"+
    "("+
        batchParams+
    ")"+
    "</foreach>"+
    "</script>")
    void batchAdd(@Param("${Table.tableNameLowercase}List") List<${Table.tableName}> ${Table.tableNameLowercase}List);

    // 批量修改
    @Update("<script>"+
    "update ${Table.table_name} set "+
    <#list Table.columnList as Column>
    <#if Column.column_name != "id">
    "${Column.column_name}=case id"+
    "<foreach collection='${Table.tableNameLowercase}List' item='${Table.tableNameLowercase}'>"+
        "when <#noparse>#{</#noparse>${Table.tableNameLowercase}.id<#noparse>}</#noparse> then "+
        <#if Column.columnType == "Date" || Column.columnType == "Long" || Column.columnType == "Integer" || Column.columnType == "Double">
        "<choose>"+
            "<when test='${Table.tableNameLowercase}.${Column.columnName}!=null'><#noparse>#{</#noparse>${Table.tableNameLowercase}.${Column.columnName}<#noparse>}</#noparse></when>"+
            "<otherwise>${Column.column_name}</otherwise>"+
        "</choose>"+
        <#else>
        "<choose>"+
            "<when test='${Table.tableNameLowercase}.${Column.columnName}!=null and ${Table.tableNameLowercase}.${Column.columnName}!=\"\"'><#noparse>#{</#noparse>${Table.tableNameLowercase}.${Column.columnName}<#noparse>}</#noparse></when>"+
            "<otherwise>${Column.column_name}</otherwise>"+
        "</choose>"+
        </#if>
    "</foreach>"+
    "end<#if Column_has_next>,</#if>"+
    </#if>
    </#list>
    " where id in "+
    "<foreach collection='${Table.tableNameLowercase}List' item='${Table.tableNameLowercase}' open='(' close=')' separator=','>"+
        "<#noparse>#{</#noparse>${Table.tableNameLowercase}.id<#noparse>}</#noparse>"+
    "</foreach>"+
    "</script>")
    void batchUpdate(@Param("${Table.tableNameLowercase}List") List<${Table.tableName}> ${Table.tableNameLowercase}List);

    // 同步数据
    @Insert("replace into ${Table.table_name}("+
        columns+
    ")values("+
        params+
    ")")
    void syncData(${Table.tableName} ${Table.tableNameLowercase});

    // 批量同步数据
    @Insert("<script>"+
    "replace into ${Table.table_name}("+
        columns+
    ")values"+
    "<foreach collection='${Table.tableNameLowercase}List' item='${Table.tableNameLowercase}' separator=','>"+
    "("+
        batchParams+
    ")"+
    "</foreach>"+
    "</script>")
    void batchSyncData(@Param("${Table.tableNameLowercase}List") List<${Table.tableName}> ${Table.tableNameLowercase}List);

    // 根据参数查询总数
    @Select("<script>"+
    "select count(1) from ${Table.table_name} where 1=1"+
        optionalParams+
        idSetOptionalParams+
    "</script>")
    Long total(${Table.tableName}Dto ${Table.tableNameLowercase}Dto);

}
