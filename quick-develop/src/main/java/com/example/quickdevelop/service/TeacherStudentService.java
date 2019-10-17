package com.example.quickdevelop.service;

import com.github.pagehelper.PageInfo;
import com.example.quickdevelop.dto.TeacherStudentDto;
import com.example.quickdevelop.model.TeacherStudent;
import java.util.List;
import java.util.Set;

public interface TeacherStudentService {

    TeacherStudent add(TeacherStudent teacherStudent);

    void deleteById(Long id);

    TeacherStudent findById(Long id);

    TeacherStudent updateById(TeacherStudent teacherStudent);

    PageInfo<TeacherStudentDto> paging(TeacherStudentDto teacherStudentDto);

    List<TeacherStudentDto> find(TeacherStudentDto teacherStudentDto);

    void deleteByIdSet(Set<Long> idSet);

    List<TeacherStudent> batchAdd(List<TeacherStudent> teacherStudentList);

    List<TeacherStudent> batchUpdate(List<TeacherStudent> teacherStudentList);

    TeacherStudent syncData(TeacherStudent teacherStudent);

    List<TeacherStudent> batchSyncData(List<TeacherStudent> teacherStudentList);

    Long total(TeacherStudentDto teacherStudentDto);

}
