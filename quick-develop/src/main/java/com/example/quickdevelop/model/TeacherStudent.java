package com.example.quickdevelop.model;

import lombok.Data;
import java.lang.Long;

/**
* 老师学生表实体
*/
@Data
public class TeacherStudent{
    //
    private Long id;
    //老师id
    private Long teacherId;
    //学生id
    private Long studentId;

}
