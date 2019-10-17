package ${Table.packageName}.ctrl;

import com.github.pagehelper.PageInfo;
import ${Table.packageName}.common.Result;
import ${Table.packageName}.dto.${Table.tableName}Dto;
import ${Table.packageName}.model.${Table.tableName};
import ${Table.packageName}.service.${Table.tableName}Service;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/${Table.tableNameLowercase}")
public class ${Table.tableName}Ctrl {

    @Resource
    private ${Table.tableName}Service ${Table.tableNameLowercase}Service ;

    /**
    * 增加
    */
    @PostMapping("/add")
    public Result add(@RequestBody ${Table.tableName} ${Table.tableNameLowercase}){
        try{
            ${Table.tableNameLowercase}Service.add(${Table.tableNameLowercase});
            return Result.success("增加成功");
        }catch(Exception e){
            e.printStackTrace();
            return Result.error("增加失败");
        }
    }

    /**
    * 根据id删除
    */
    @PostMapping("/deleteById")
    public Result deleteById(@RequestBody ${Table.tableName} ${Table.tableNameLowercase}){
        try{
            ${Table.tableNameLowercase}Service.deleteById(${Table.tableNameLowercase}.getId());
            return Result.success("删除成功");
        }catch(Exception e){
            e.printStackTrace();
            return Result.error("删除失败");
        }
    }

    /**
    * 根据id查询
    */
    @PostMapping("/findById")
    public Result findById(@RequestBody ${Table.tableName} ${Table.tableNameLowercase}){
        try{
            ${Table.tableNameLowercase} = ${Table.tableNameLowercase}Service.findById(${Table.tableNameLowercase}.getId());
            return Result.success("查询成功",${Table.tableNameLowercase});
        }catch(Exception e){
            e.printStackTrace();
            return Result.error("查询失败");
        }
    }

    /**
    * 根据id修改
    */
    @PostMapping("/updateById")
    public Result updateById(@RequestBody ${Table.tableName} ${Table.tableNameLowercase}){
        try{
            ${Table.tableNameLowercase}Service.updateById(${Table.tableNameLowercase});
            return Result.success("修改成功");
        }catch(Exception e){
            e.printStackTrace();
            return Result.error("修改失败");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/paging")
    public Result paging(@RequestBody ${Table.tableName}Dto ${Table.tableNameLowercase}Dto){
        try{
            ${Table.tableNameLowercase}Dto.initPaging();
            PageInfo<${Table.tableName}Dto> paging = ${Table.tableNameLowercase}Service.paging(${Table.tableNameLowercase}Dto);
            return Result.success("分页查询成功",paging);
        }catch(Exception e){
            e.printStackTrace();
            return Result.error("分页查询失败");
        }
    }

    /**
    * 根据参数查询
    */
    @PostMapping("/find")
    public Result find(@RequestBody ${Table.tableName}Dto ${Table.tableNameLowercase}Dto){
        try{
            List<${Table.tableName}Dto> ${Table.tableNameLowercase}DtoList = ${Table.tableNameLowercase}Service.find(${Table.tableNameLowercase}Dto);
            return Result.success("根据参数查询成功",${Table.tableNameLowercase}DtoList);
        }catch(Exception e){
            e.printStackTrace();
            return Result.error("根据参数查询失败");
        }
    }

    /**
    * 根据idSet批量删除
    */
    @PostMapping("/deleteByIdSet")
    public Result deleteByIdSet(@RequestBody ${Table.tableName}Dto ${Table.tableNameLowercase}Dto){
        try{
            ${Table.tableNameLowercase}Service.deleteByIdSet(${Table.tableNameLowercase}Dto.getIdSet());
            return Result.success("根据idSet批量删除成功");
        }catch(Exception e){
            e.printStackTrace();
            return Result.error("根据idSet批量删除失败");
        }
    }

    /**
    * 批量增加
    */
    @PostMapping("/batchAdd")
    public Result batchAdd(@RequestBody ${Table.tableName}Dto ${Table.tableNameLowercase}Dto){
        try{
            ${Table.tableNameLowercase}Service.batchAdd(${Table.tableNameLowercase}Dto.get${Table.tableName}List());
            return Result.success("批量增加成功");
        }catch(Exception e){
            e.printStackTrace();
            return Result.error("批量增加失败");
        }
    }

    /**
    * 批量修改
    */
    @PostMapping("/batchUpdate")
    public Result batchUpdate(@RequestBody ${Table.tableName}Dto ${Table.tableNameLowercase}Dto){
        try{
            ${Table.tableNameLowercase}Service.batchUpdate(${Table.tableNameLowercase}Dto.get${Table.tableName}List());
            return Result.success("根据idSet批量修改成功");
        }catch(Exception e){
            e.printStackTrace();
            return Result.error("根据idSet批量修改失败");
        }
    }

    /**
    * 同步数据
    */
    @PostMapping("/syncData")
    public Result syncData(@RequestBody ${Table.tableName} ${Table.tableNameLowercase}){
        try{
            ${Table.tableNameLowercase}Service.syncData(${Table.tableNameLowercase});
            return Result.success("同步数据成功");
        }catch(Exception e){
            e.printStackTrace();
            return Result.error("同步数据失败");
        }
    }

    /**
    * 批量同步数据
    */
    @PostMapping("/batchSyncData")
    public Result batchSyncData(@RequestBody ${Table.tableName}Dto ${Table.tableNameLowercase}Dto){
        try{
            ${Table.tableNameLowercase}Service.batchSyncData(${Table.tableNameLowercase}Dto.get${Table.tableName}List());
            return Result.success("批量同步数据成功");
        }catch(Exception e){
            e.printStackTrace();
            return Result.error("批量同步数据失败");
        }
    }

    /**
    * 根据参数查询总数
    */
    @PostMapping("/total")
    public Result total(@RequestBody ${Table.tableName}Dto ${Table.tableNameLowercase}Dto){
        try{
            Long total = ${Table.tableNameLowercase}Service.total(${Table.tableNameLowercase}Dto);
            return Result.success("根据参数查询总数成功",total);
        }catch(Exception e){
            e.printStackTrace();
            return Result.error("根据参数查询总数失败");
        }
    }

}
