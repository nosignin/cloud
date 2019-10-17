package com.example.quickdevelop.ctrl;

import com.example.quickdevelop.component.common.Result;
import com.github.pagehelper.PageInfo;
import com.example.quickdevelop.dto.TeacherDto;
import com.example.quickdevelop.model.Teacher;
import com.example.quickdevelop.service.TeacherService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherCtrl {

    @Resource
    private TeacherService teacherService ;

    /**
    * 增加
    */
    @PostMapping("/add")
    public Result add(@RequestBody Teacher teacher){
        try{
            teacherService.add(teacher);
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
    public Result deleteById(@RequestBody Teacher teacher){
        try{
            teacherService.deleteById(teacher.getId());
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
    public Result findById(@RequestBody Teacher teacher){
        try{
            teacher = teacherService.findById(teacher.getId());
            return Result.success("查询成功",teacher);
        }catch(Exception e){
            e.printStackTrace();
            return Result.error("查询失败");
        }
    }

    /**
    * 根据id修改
    */
    @PostMapping("/updateById")
    public Result updateById(@RequestBody Teacher teacher){
        try{
            teacherService.updateById(teacher);
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
    public Result paging(@RequestBody TeacherDto teacherDto){
        try{
            teacherDto.initPaging();
            PageInfo<TeacherDto> paging = teacherService.paging(teacherDto);
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
    public Result find(@RequestBody TeacherDto teacherDto){
        try{
            List<TeacherDto> teacherDtoList = teacherService.find(teacherDto);
            return Result.success("根据参数查询成功",teacherDtoList);
        }catch(Exception e){
            e.printStackTrace();
            return Result.error("根据参数查询失败");
        }
    }

    /**
    * 根据idSet批量删除
    */
    @PostMapping("/deleteByIdSet")
    public Result deleteByIdSet(@RequestBody TeacherDto teacherDto){
        try{
            teacherService.deleteByIdSet(teacherDto.getIdSet());
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
    public Result batchAdd(@RequestBody TeacherDto teacherDto){
        try{
            teacherService.batchAdd(teacherDto.getTeacherList());
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
    public Result batchUpdate(@RequestBody TeacherDto teacherDto){
        try{
            teacherService.batchUpdate(teacherDto.getTeacherList());
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
    public Result syncData(@RequestBody Teacher teacher){
        try{
            teacherService.syncData(teacher);
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
    public Result batchSyncData(@RequestBody TeacherDto teacherDto){
        try{
            teacherService.batchSyncData(teacherDto.getTeacherList());
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
    public Result total(@RequestBody TeacherDto teacherDto){
        try{
            Long total = teacherService.total(teacherDto);
            return Result.success("根据参数查询总数成功",total);
        }catch(Exception e){
            e.printStackTrace();
            return Result.error("根据参数查询总数失败");
        }
    }

}
