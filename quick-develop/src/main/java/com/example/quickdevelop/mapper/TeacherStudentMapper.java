package com.example.quickdevelop.mapper;

import com.example.quickdevelop.dto.TeacherStudentDto;
import com.example.quickdevelop.model.TeacherStudent;
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Set;

@Mapper
public interface TeacherStudentMapper {

    String columns = 
        "id,"+
        "teacher_id,"+
        "student_id";

    String params = 
        "#{id},"+
        "#{teacherId},"+
        "#{studentId}";

    String batchParams = 
        "#{teacherStudent.id},"+
        "#{teacherStudent.teacherId},"+
        "#{teacherStudent.studentId}";

    String optionalParams = 
        "<if test='id!=null'> and id = #{id}</if>"+
        "<if test='teacherId!=null'> and teacher_id = #{teacherId}</if>"+
        "<if test='studentId!=null'> and student_id = #{studentId}</if>";

    String idSetOptionalParams =
        "<if test='idSet!=null and idSet.size()>0'> and id in "+
            "<foreach collection='idSet' item='id' open='(' close=')' separator=','>"+
                "#{id}"+
            "</foreach>"+
        "</if>";

    // 增加
    @Insert("insert into teacher_student("+
        columns+
    ")values("+
        params+
    ")")
    void add(TeacherStudent teacherStudent);

    // 根据id删除
    @Delete("delete from teacher_student where id = #{id}")
    void deleteById(Long id);

    // 根据id查询
    @Select("select * from teacher_student where id = #{id}")
    TeacherStudent findById(Long id);

    // 根据id修改
    @Update("<script>"+
    "update teacher_student set "+
    "<trim suffix='' suffixOverrides=','>"+
        "<if test='teacherId!=null' >teacher_id=#{teacherId},</if>"+
        "<if test='studentId!=null' >student_id=#{studentId}</if>"+
    "</trim>"+
    "where id = #{id}"+
    "</script>")
    void updateById(TeacherStudent teacherStudent);

    // 根据参数查询
    @Select("<script>"+
    "select * from teacher_student where 1=1"+
        optionalParams+
        idSetOptionalParams+
    "</script>")
    List<TeacherStudentDto> find(TeacherStudentDto teacherStudentDto);

    // 根据idSet批量删除
    @Delete("<script>"+
    "delete from teacher_student where id in "+
    "<foreach collection='idSet' item='id' open='(' close=')' separator=','>"+
        "#{id}"+
    "</foreach>"+
    "</script>")
    void deleteByIdSet(@Param("idSet") Set<Long> idSet);

    // 批量增加
    @Insert("<script>"+
    "insert into teacher_student("+
        columns+
    ")values"+
    "<foreach collection='teacherStudentList' item='teacherStudent' separator=','>"+
    "("+
        batchParams+
    ")"+
    "</foreach>"+
    "</script>")
    void batchAdd(@Param("teacherStudentList") List<TeacherStudent> teacherStudentList);

    // 批量修改
    @Update("<script>"+
    "update teacher_student set "+
    "teacher_id=case id"+
    "<foreach collection='teacherStudentList' item='teacherStudent'>"+
        "when #{teacherStudent.id} then "+
        "<choose>"+
            "<when test='teacherStudent.teacherId!=null'>#{teacherStudent.teacherId}</when>"+
            "<otherwise>teacher_id</otherwise>"+
        "</choose>"+
    "</foreach>"+
    "end,"+
    "student_id=case id"+
    "<foreach collection='teacherStudentList' item='teacherStudent'>"+
        "when #{teacherStudent.id} then "+
        "<choose>"+
            "<when test='teacherStudent.studentId!=null'>#{teacherStudent.studentId}</when>"+
            "<otherwise>student_id</otherwise>"+
        "</choose>"+
    "</foreach>"+
    "end"+
    " where id in "+
    "<foreach collection='teacherStudentList' item='teacherStudent' open='(' close=')' separator=','>"+
        "#{teacherStudent.id}"+
    "</foreach>"+
    "</script>")
    void batchUpdate(@Param("teacherStudentList") List<TeacherStudent> teacherStudentList);

    // 同步数据
    @Insert("replace into teacher_student("+
        columns+
    ")values("+
        params+
    ")")
    void syncData(TeacherStudent teacherStudent);

    // 批量同步数据
    @Insert("<script>"+
    "replace into teacher_student("+
        columns+
    ")values"+
    "<foreach collection='teacherStudentList' item='teacherStudent' separator=','>"+
    "("+
        batchParams+
    ")"+
    "</foreach>"+
    "</script>")
    void batchSyncData(@Param("teacherStudentList") List<TeacherStudent> teacherStudentList);

    // 根据参数查询总数
    @Select("<script>"+
    "select count(1) from teacher_student where 1=1"+
        optionalParams+
        idSetOptionalParams+
    "</script>")
    Long total(TeacherStudentDto teacherStudentDto);

}
