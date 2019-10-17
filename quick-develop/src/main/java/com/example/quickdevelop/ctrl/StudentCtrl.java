package com.example.quickdevelop.ctrl;

import com.example.quickdevelop.component.common.Result;
import com.github.pagehelper.PageInfo;
import com.example.quickdevelop.dto.StudentDto;
import com.example.quickdevelop.model.Student;
import com.example.quickdevelop.service.StudentService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentCtrl {

    @Resource
    private StudentService studentService ;

    /**
    * 增加
    */
    @PostMapping("/add")
    public Result add(@RequestBody Student student){
        try{
            studentService.add(student);
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
    public Result deleteById(@RequestBody Student student){
        try{
            studentService.deleteById(student.getId());
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
    public Result findById(@RequestBody Student student){
        try{
            student = studentService.findById(student.getId());
            return Result.success("查询成功",student);
        }catch(Exception e){
            e.printStackTrace();
            return Result.error("查询失败");
        }
    }

    /**
    * 根据id修改
    */
    @PostMapping("/updateById")
    public Result updateById(@RequestBody Student student){
        try{
            studentService.updateById(student);
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
    public Result paging(@RequestBody StudentDto studentDto){
        try{
            studentDto.initPaging();
            PageInfo<StudentDto> paging = studentService.paging(studentDto);
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
    public Result find(@RequestBody StudentDto studentDto){
        try{
            List<StudentDto> studentDtoList = studentService.find(studentDto);
            return Result.success("根据参数查询成功",studentDtoList);
        }catch(Exception e){
            e.printStackTrace();
            return Result.error("根据参数查询失败");
        }
    }

    /**
    * 根据idSet批量删除
    */
    @PostMapping("/deleteByIdSet")
    public Result deleteByIdSet(@RequestBody StudentDto studentDto){
        try{
            studentService.deleteByIdSet(studentDto.getIdSet());
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
    public Result batchAdd(@RequestBody StudentDto studentDto){
        try{
            studentService.batchAdd(studentDto.getStudentList());
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
    public Result batchUpdate(@RequestBody StudentDto studentDto){
        try{
            studentService.batchUpdate(studentDto.getStudentList());
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
    public Result syncData(@RequestBody Student student){
        try{
            studentService.syncData(student);
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
    public Result batchSyncData(@RequestBody StudentDto studentDto){
        try{
            studentService.batchSyncData(studentDto.getStudentList());
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
    public Result total(@RequestBody StudentDto studentDto){
        try{
            Long total = studentService.total(studentDto);
            return Result.success("根据参数查询总数成功",total);
        }catch(Exception e){
            e.printStackTrace();
            return Result.error("根据参数查询总数失败");
        }
    }

}
