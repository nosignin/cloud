package com.example.quickdevelop.mapper;

import com.example.quickdevelop.dto.TeacherDto;
import com.example.quickdevelop.model.Teacher;
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Set;

@Mapper
public interface TeacherMapper {

    String columns = 
        "id,"+
        "name,"+
        "age,"+
        "gender";

    String params = 
        "#{id},"+
        "#{name},"+
        "#{age},"+
        "#{gender}";

    String batchParams = 
        "#{teacher.id},"+
        "#{teacher.name},"+
        "#{teacher.age},"+
        "#{teacher.gender}";

    String optionalParams = 
        "<if test='id!=null'> and id = #{id}</if>"+
        "<if test='name!=null and name!=\"\"'> and name = #{name}</if>"+
        "<if test='age!=null'> and age = #{age}</if>"+
        "<if test='gender!=null'> and gender = #{gender}</if>";

    String idSetOptionalParams =
        "<if test='idSet!=null and idSet.size()>0'> and id in "+
            "<foreach collection='idSet' item='id' open='(' close=')' separator=','>"+
                "#{id}"+
            "</foreach>"+
        "</if>";

    // 增加
    @Insert("insert into teacher("+
        columns+
    ")values("+
        params+
    ")")
    void add(Teacher teacher);

    // 根据id删除
    @Delete("delete from teacher where id = #{id}")
    void deleteById(Long id);

    // 根据id查询
    @Select("select * from teacher where id = #{id}")
    Teacher findById(Long id);

    // 根据id修改
    @Update("<script>"+
    "update teacher set "+
    "<trim suffix='' suffixOverrides=','>"+
        "<if test='name!=null and name!= \"\"'>name=#{name},</if>"+
        "<if test='age!=null' >age=#{age},</if>"+
        "<if test='gender!=null' >gender=#{gender}</if>"+
    "</trim>"+
    "where id = #{id}"+
    "</script>")
    void updateById(Teacher teacher);

    // 根据参数查询
    @Select("<script>"+
    "select * from teacher where 1=1"+
        optionalParams+
        idSetOptionalParams+
    "</script>")
    List<TeacherDto> find(TeacherDto teacherDto);

    // 根据idSet批量删除
    @Delete("<script>"+
    "delete from teacher where id in "+
    "<foreach collection='idSet' item='id' open='(' close=')' separator=','>"+
        "#{id}"+
    "</foreach>"+
    "</script>")
    void deleteByIdSet(@Param("idSet") Set<Long> idSet);

    // 批量增加
    @Insert("<script>"+
    "insert into teacher("+
        columns+
    ")values"+
    "<foreach collection='teacherList' item='teacher' separator=','>"+
    "("+
        batchParams+
    ")"+
    "</foreach>"+
    "</script>")
    void batchAdd(@Param("teacherList") List<Teacher> teacherList);

    // 批量修改
    @Update("<script>"+
    "update teacher set "+
    "name=case id"+
    "<foreach collection='teacherList' item='teacher'>"+
        "when #{teacher.id} then "+
        "<choose>"+
            "<when test='teacher.name!=null and teacher.name!=\"\"'>#{teacher.name}</when>"+
            "<otherwise>name</otherwise>"+
        "</choose>"+
    "</foreach>"+
    "end,"+
    "age=case id"+
    "<foreach collection='teacherList' item='teacher'>"+
        "when #{teacher.id} then "+
        "<choose>"+
            "<when test='teacher.age!=null'>#{teacher.age}</when>"+
            "<otherwise>age</otherwise>"+
        "</choose>"+
    "</foreach>"+
    "end,"+
    "gender=case id"+
    "<foreach collection='teacherList' item='teacher'>"+
        "when #{teacher.id} then "+
        "<choose>"+
            "<when test='teacher.gender!=null'>#{teacher.gender}</when>"+
            "<otherwise>gender</otherwise>"+
        "</choose>"+
    "</foreach>"+
    "end"+
    " where id in "+
    "<foreach collection='teacherList' item='teacher' open='(' close=')' separator=','>"+
        "#{teacher.id}"+
    "</foreach>"+
    "</script>")
    void batchUpdate(@Param("teacherList") List<Teacher> teacherList);

    // 同步数据
    @Insert("replace into teacher("+
        columns+
    ")values("+
        params+
    ")")
    void syncData(Teacher teacher);

    // 批量同步数据
    @Insert("<script>"+
    "replace into teacher("+
        columns+
    ")values"+
    "<foreach collection='teacherList' item='teacher' separator=','>"+
    "("+
        batchParams+
    ")"+
    "</foreach>"+
    "</script>")
    void batchSyncData(@Param("teacherList") List<Teacher> teacherList);

    // 根据参数查询总数
    @Select("<script>"+
    "select count(1) from teacher where 1=1"+
        optionalParams+
        idSetOptionalParams+
    "</script>")
    Long total(TeacherDto teacherDto);

}
