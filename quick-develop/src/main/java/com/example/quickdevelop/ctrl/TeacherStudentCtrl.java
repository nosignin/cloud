package com.example.quickdevelop.ctrl;

import com.example.quickdevelop.component.common.Result;
import com.github.pagehelper.PageInfo;
import com.example.quickdevelop.dto.TeacherStudentDto;
import com.example.quickdevelop.model.TeacherStudent;
import com.example.quickdevelop.service.TeacherStudentService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/teacherStudent")
public class TeacherStudentCtrl {

    @Resource
    private TeacherStudentService teacherStudentService ;

    /**
    * 增加
    */
    @PostMapping("/add")
    public Result add(@RequestBody TeacherStudent teacherStudent){
        try{
            teacherStudentService.add(teacherStudent);
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
    public Result deleteById(@RequestBody TeacherStudent teacherStudent){
        try{
            teacherStudentService.deleteById(teacherStudent.getId());
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
    public Result findById(@RequestBody TeacherStudent teacherStudent){
        try{
            teacherStudent = teacherStudentService.findById(teacherStudent.getId());
            return Result.success("查询成功",teacherStudent);
        }catch(Exception e){
            e.printStackTrace();
            return Result.error("查询失败");
        }
    }

    /**
    * 根据id修改
    */
    @PostMapping("/updateById")
    public Result updateById(@RequestBody TeacherStudent teacherStudent){
        try{
            teacherStudentService.updateById(teacherStudent);
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
    public Result paging(@RequestBody TeacherStudentDto teacherStudentDto){
        try{
            teacherStudentDto.initPaging();
            PageInfo<TeacherStudentDto> paging = teacherStudentService.paging(teacherStudentDto);
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
    public Result find(@RequestBody TeacherStudentDto teacherStudentDto){
        try{
            List<TeacherStudentDto> teacherStudentDtoList = teacherStudentService.find(teacherStudentDto);
            return Result.success("根据参数查询成功",teacherStudentDtoList);
        }catch(Exception e){
            e.printStackTrace();
            return Result.error("根据参数查询失败");
        }
    }

    /**
    * 根据idSet批量删除
    */
    @PostMapping("/deleteByIdSet")
    public Result deleteByIdSet(@RequestBody TeacherStudentDto teacherStudentDto){
        try{
            teacherStudentService.deleteByIdSet(teacherStudentDto.getIdSet());
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
    public Result batchAdd(@RequestBody TeacherStudentDto teacherStudentDto){
        try{
            teacherStudentService.batchAdd(teacherStudentDto.getTeacherStudentList());
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
    public Result batchUpdate(@RequestBody TeacherStudentDto teacherStudentDto){
        try{
            teacherStudentService.batchUpdate(teacherStudentDto.getTeacherStudentList());
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
    public Result syncData(@RequestBody TeacherStudent teacherStudent){
        try{
            teacherStudentService.syncData(teacherStudent);
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
    public Result batchSyncData(@RequestBody TeacherStudentDto teacherStudentDto){
        try{
            teacherStudentService.batchSyncData(teacherStudentDto.getTeacherStudentList());
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
    public Result total(@RequestBody TeacherStudentDto teacherStudentDto){
        try{
            Long total = teacherStudentService.total(teacherStudentDto);
            return Result.success("根据参数查询总数成功",total);
        }catch(Exception e){
            e.printStackTrace();
            return Result.error("根据参数查询总数失败");
        }
    }

}
