package com.example.quickdevelop.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.example.quickdevelop.model.Teacher;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
* 老师表实体扩展类
*/
@Data
@EqualsAndHashCode(callSuper=false)
public class TeacherDto extends Teacher{
    //===分页查询参数===
    //当前页
    private Integer page;
    //每页显示条数
    private Integer pageSize;
    public void initPaging(){
        if(this.page==null||this.page<1){
            this.page=1;//默认显示第1页
        }
        if(this.pageSize==null||this.pageSize<1||this.pageSize>100){
            this.pageSize=20;//默认显示20行，显示行数[1,100]
        }
    }
    //===扩展参数===
    private Date beginTime;
    private Date endTime;
    private String[] beginTime_endTime;
    private Set<Long> idSet;
    private List<Teacher> teacherList;

}
