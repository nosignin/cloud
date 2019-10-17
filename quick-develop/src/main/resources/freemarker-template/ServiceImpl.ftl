package ${Table.packageName}.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import ${Table.packageName}.aspect.CachePaging;
import ${Table.packageName}.dto.${Table.tableName}Dto;
import ${Table.packageName}.mapper.${Table.tableName}Mapper;
import ${Table.packageName}.model.${Table.tableName};
import ${Table.packageName}.service.${Table.tableName}Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

//@CacheConfig(cacheNames = "${Table.tableName}Cache")
@Service
public class ${Table.tableName}ServiceImpl implements ${Table.tableName}Service {

    @Resource
    private ${Table.tableName}Mapper ${Table.tableNameLowercase}Mapper;

    private final static SnowflakeShardingKeyGenerator snowflakeShardingKeyGenerator = new SnowflakeShardingKeyGenerator();

    //@Cacheable(key="#${Table.tableNameLowercase}.id",unless="#result == null",condition = "#${Table.tableNameLowercase}.id != null")
    @Transactional
    @Override
    public ${Table.tableName} add(${Table.tableName} ${Table.tableNameLowercase}) {
        Long id = (Long)snowflakeShardingKeyGenerator.generateKey();
        ${Table.tableNameLowercase}.setId(id);
        ${Table.tableNameLowercase}Mapper.add(${Table.tableNameLowercase});
        return ${Table.tableNameLowercase};
    }

    //@CacheEvict(key="#id",condition = "#id != null")
    @Transactional
    @Override
    public void deleteById(Long id) {
        ${Table.tableNameLowercase}Mapper.deleteById(id);
    }

    //@Cacheable(key="#id",unless="#result == null",condition = "#id != null")
    @Override
    public ${Table.tableName} findById(Long id) {
        ${Table.tableName} ${Table.tableNameLowercase} = ${Table.tableNameLowercase}Mapper.findById(id);
        return ${Table.tableNameLowercase};
    }

    //@CachePut(key = "#${Table.tableNameLowercase}.id",unless="#result == null",condition = "#${Table.tableNameLowercase}.id != null")
    @Transactional
    @Override
    public ${Table.tableName} updateById(${Table.tableName} ${Table.tableNameLowercase}) {
        ${Table.tableNameLowercase}Mapper.updateById(${Table.tableNameLowercase});
        return ${Table.tableNameLowercase};
    }

    //@CachePaging(keyPrefix = "${Table.tableName}Cache",clazz = ${Table.tableName}Dto.class,expire = 5)
    @Override
    public PageInfo<${Table.tableName}Dto> paging(${Table.tableName}Dto ${Table.tableNameLowercase}Dto) {
        PageHelper.startPage(${Table.tableNameLowercase}Dto.getPage(), ${Table.tableNameLowercase}Dto.getPageSize());
        List<${Table.tableName}Dto> list = ${Table.tableNameLowercase}Mapper.find(${Table.tableNameLowercase}Dto);
        PageInfo<${Table.tableName}Dto> paging = new PageInfo<${Table.tableName}Dto>(list);
        return paging;
    }

    @Override
    public List<${Table.tableName}Dto> find(${Table.tableName}Dto ${Table.tableNameLowercase}Dto) {
        List<${Table.tableName}Dto> ${Table.tableNameLowercase}DtoList = ${Table.tableNameLowercase}Mapper.find(${Table.tableNameLowercase}Dto);
        return ${Table.tableNameLowercase}DtoList;
    }

    @Transactional
    @Override
    public void deleteByIdSet(Set<Long> idSet) {
        ${Table.tableNameLowercase}Mapper.deleteByIdSet(idSet);
    }

    @Transactional
    @Override
    public List<${Table.tableName}> batchAdd(List<${Table.tableName}> ${Table.tableNameLowercase}List) {
        ${Table.tableNameLowercase}Mapper.batchAdd(${Table.tableNameLowercase}List);
        return ${Table.tableNameLowercase}List;
    }

    @Transactional
    @Override
    public List<${Table.tableName}> batchUpdate(List<${Table.tableName}> ${Table.tableNameLowercase}List) {
        ${Table.tableNameLowercase}Mapper.batchUpdate(${Table.tableNameLowercase}List);
        return ${Table.tableNameLowercase}List;
    }

    @Transactional
    @Override
    public ${Table.tableName} syncData(${Table.tableName} ${Table.tableNameLowercase}) {
        ${Table.tableNameLowercase}Mapper.syncData(${Table.tableNameLowercase});
        return ${Table.tableNameLowercase};
    }

    @Transactional
    @Override
    public List<${Table.tableName}> batchSyncData(List<${Table.tableName}> ${Table.tableNameLowercase}List) {
        ${Table.tableNameLowercase}Mapper.batchSyncData(${Table.tableNameLowercase}List);
        return ${Table.tableNameLowercase}List;
    }

    @Override
    public Long total(${Table.tableName}Dto ${Table.tableNameLowercase}Dto) {
        Long total = ${Table.tableNameLowercase}Mapper.total(${Table.tableNameLowercase}Dto);
        return total;
    }

}
