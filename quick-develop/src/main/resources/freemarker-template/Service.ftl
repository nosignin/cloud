package ${Table.packageName}.service;

import com.github.pagehelper.PageInfo;
import ${Table.packageName}.dto.${Table.tableName}Dto;
import ${Table.packageName}.model.${Table.tableName};
import java.util.List;
import java.util.Set;

public interface ${Table.tableName}Service {

    ${Table.tableName} add(${Table.tableName} ${Table.tableNameLowercase});

    void deleteById(Long id);

    ${Table.tableName} findById(Long id);

    ${Table.tableName} updateById(${Table.tableName} ${Table.tableNameLowercase});

    PageInfo<${Table.tableName}Dto> paging(${Table.tableName}Dto ${Table.tableNameLowercase}Dto);

    List<${Table.tableName}Dto> find(${Table.tableName}Dto ${Table.tableNameLowercase}Dto);

    void deleteByIdSet(Set<Long> idSet);

    List<${Table.tableName}> batchAdd(List<${Table.tableName}> ${Table.tableNameLowercase}List);

    List<${Table.tableName}> batchUpdate(List<${Table.tableName}> ${Table.tableNameLowercase}List);

    ${Table.tableName} syncData(${Table.tableName} ${Table.tableNameLowercase});

    List<${Table.tableName}> batchSyncData(List<${Table.tableName}> ${Table.tableNameLowercase}List);

    Long total(${Table.tableName}Dto ${Table.tableNameLowercase}Dto);

}
