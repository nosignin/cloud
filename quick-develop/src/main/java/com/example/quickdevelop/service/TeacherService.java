package com.example.quickdevelop.service;

import com.github.pagehelper.PageInfo;
import com.example.quickdevelop.dto.TeacherDto;
import com.example.quickdevelop.model.Teacher;
import java.util.List;
import java.util.Set;

public interface TeacherService {

    Teacher add(Teacher teacher);

    void deleteById(Long id);

    Teacher findById(Long id);

    Teacher updateById(Teacher teacher);

    PageInfo<TeacherDto> paging(TeacherDto teacherDto);

    List<TeacherDto> find(TeacherDto teacherDto);

    void deleteByIdSet(Set<Long> idSet);

    List<Teacher> batchAdd(List<Teacher> teacherList);

    List<Teacher> batchUpdate(List<Teacher> teacherList);

    Teacher syncData(Teacher teacher);

    List<Teacher> batchSyncData(List<Teacher> teacherList);

    Long total(TeacherDto teacherDto);

}
