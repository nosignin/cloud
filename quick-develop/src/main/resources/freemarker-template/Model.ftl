package ${Table.packageName}.model;

import lombok.Data;
<#list Table.columnTypeSet as columnType>
    <#if columnType=='String'>
import java.lang.String;
    </#if>
    <#if columnType=='Integer'>
import java.lang.Integer;
    </#if>
    <#if columnType=='Date'>
import java.util.Date;
    </#if>
    <#if columnType=='Long'>
import java.lang.Long;
    </#if>
    <#if columnType=='Double'>
import java.lang.Double;
    </#if>
</#list>

/**
* ${Table.tableComment}实体
*/
@Data
public class ${Table.tableName}{
<#list Table.columnList as Column>
    //${Column.columnComment}
    private ${Column.columnType} ${Column.columnName};
</#list>

}
