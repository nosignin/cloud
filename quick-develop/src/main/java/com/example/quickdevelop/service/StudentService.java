package com.example.quickdevelop.service;

import com.github.pagehelper.PageInfo;
import com.example.quickdevelop.dto.StudentDto;
import com.example.quickdevelop.model.Student;
import java.util.List;
import java.util.Set;

public interface StudentService {

    Student add(Student student);

    void deleteById(Long id);

    Student findById(Long id);

    Student updateById(Student student);

    PageInfo<StudentDto> paging(StudentDto studentDto);

    List<StudentDto> find(StudentDto studentDto);

    void deleteByIdSet(Set<Long> idSet);

    List<Student> batchAdd(List<Student> studentList);

    List<Student> batchUpdate(List<Student> studentList);

    Student syncData(Student student);

    List<Student> batchSyncData(List<Student> studentList);

    Long total(StudentDto studentDto);

}
