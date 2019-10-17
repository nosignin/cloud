package com.example.quickdevelop.mapper;

import com.example.quickdevelop.dto.StudentDto;
import com.example.quickdevelop.model.Student;
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Set;

@Mapper
public interface StudentMapper {

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
        "#{student.id},"+
        "#{student.name},"+
        "#{student.age},"+
        "#{student.gender}";

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
    @Insert("insert into student("+
        columns+
    ")values("+
        params+
    ")")
    void add(Student student);

    // 根据id删除
    @Delete("delete from student where id = #{id}")
    void deleteById(Long id);

    // 根据id查询
    @Select("select * from student where id = #{id}")
    Student findById(Long id);

    // 根据id修改
    @Update("<script>"+
    "update student set "+
    "<trim suffix='' suffixOverrides=','>"+
        "<if test='name!=null and name!= \"\"'>name=#{name},</if>"+
        "<if test='age!=null' >age=#{age},</if>"+
        "<if test='gender!=null' >gender=#{gender}</if>"+
    "</trim>"+
    "where id = #{id}"+
    "</script>")
    void updateById(Student student);

    // 根据参数查询
    @Select("<script>"+
    "select * from student where 1=1"+
        optionalParams+
        idSetOptionalParams+
    "</script>")
    List<StudentDto> find(StudentDto studentDto);

    // 根据idSet批量删除
    @Delete("<script>"+
    "delete from student where id in "+
    "<foreach collection='idSet' item='id' open='(' close=')' separator=','>"+
        "#{id}"+
    "</foreach>"+
    "</script>")
    void deleteByIdSet(@Param("idSet") Set<Long> idSet);

    // 批量增加
    @Insert("<script>"+
    "insert into student("+
        columns+
    ")values"+
    "<foreach collection='studentList' item='student' separator=','>"+
    "("+
        batchParams+
    ")"+
    "</foreach>"+
    "</script>")
    void batchAdd(@Param("studentList") List<Student> studentList);

    // 批量修改
    @Update("<script>"+
    "update student set "+
    "name=case id"+
    "<foreach collection='studentList' item='student'>"+
        "when #{student.id} then "+
        "<choose>"+
            "<when test='student.name!=null and student.name!=\"\"'>#{student.name}</when>"+
            "<otherwise>name</otherwise>"+
        "</choose>"+
    "</foreach>"+
    "end,"+
    "age=case id"+
    "<foreach collection='studentList' item='student'>"+
        "when #{student.id} then "+
        "<choose>"+
            "<when test='student.age!=null'>#{student.age}</when>"+
            "<otherwise>age</otherwise>"+
        "</choose>"+
    "</foreach>"+
    "end,"+
    "gender=case id"+
    "<foreach collection='studentList' item='student'>"+
        "when #{student.id} then "+
        "<choose>"+
            "<when test='student.gender!=null'>#{student.gender}</when>"+
            "<otherwise>gender</otherwise>"+
        "</choose>"+
    "</foreach>"+
    "end"+
    " where id in "+
    "<foreach collection='studentList' item='student' open='(' close=')' separator=','>"+
        "#{student.id}"+
    "</foreach>"+
    "</script>")
    void batchUpdate(@Param("studentList") List<Student> studentList);

    // 同步数据
    @Insert("replace into student("+
        columns+
    ")values("+
        params+
    ")")
    void syncData(Student student);

    // 批量同步数据
    @Insert("<script>"+
    "replace into student("+
        columns+
    ")values"+
    "<foreach collection='studentList' item='student' separator=','>"+
    "("+
        batchParams+
    ")"+
    "</foreach>"+
    "</script>")
    void batchSyncData(@Param("studentList") List<Student> studentList);

    // 根据参数查询总数
    @Select("<script>"+
    "select count(1) from student where 1=1"+
        optionalParams+
        idSetOptionalParams+
    "</script>")
    Long total(StudentDto studentDto);

}
