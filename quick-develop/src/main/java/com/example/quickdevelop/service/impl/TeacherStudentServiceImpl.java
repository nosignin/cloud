package com.example.quickdevelop.service.impl;

import com.example.quickdevelop.component.util.SnowflakeIdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.example.quickdevelop.dto.TeacherStudentDto;
import com.example.quickdevelop.mapper.TeacherStudentMapper;
import com.example.quickdevelop.model.TeacherStudent;
import com.example.quickdevelop.service.TeacherStudentService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

//@CacheConfig(cacheNames = "TeacherStudentCache")
@Service
public class TeacherStudentServiceImpl implements TeacherStudentService {

    @Resource
    private TeacherStudentMapper teacherStudentMapper;

    private SnowflakeIdUtil snowflakeIdUtil = new SnowflakeIdUtil(0,0);
    //@Cacheable(key="#teacherStudent.id",unless="#result == null",condition = "#teacherStudent.id != null")
    @Transactional
    @Override
    public TeacherStudent add(TeacherStudent teacherStudent) {
        Long id = snowflakeIdUtil.nextId();
        teacherStudent.setId(id);
        teacherStudentMapper.add(teacherStudent);
        return teacherStudent;
    }

    //@CacheEvict(key="#id",condition = "#id != null")
    @Transactional
    @Override
    public void deleteById(Long id) {
        teacherStudentMapper.deleteById(id);
    }

    //@Cacheable(key="#id",unless="#result == null",condition = "#id != null")
    @Override
    public TeacherStudent findById(Long id) {
        TeacherStudent teacherStudent = teacherStudentMapper.findById(id);
        return teacherStudent;
    }

    //@CachePut(key = "#teacherStudent.id",unless="#result == null",condition = "#teacherStudent.id != null")
    @Transactional
    @Override
    public TeacherStudent updateById(TeacherStudent teacherStudent) {
        teacherStudentMapper.updateById(teacherStudent);
        return teacherStudent;
    }

    //@CachePaging(keyPrefix = "TeacherStudentCache",clazz = TeacherStudentDto.class,expire = 5)
    @Override
    public PageInfo<TeacherStudentDto> paging(TeacherStudentDto teacherStudentDto) {
        PageHelper.startPage(teacherStudentDto.getPage(), teacherStudentDto.getPageSize());
        List<TeacherStudentDto> list = teacherStudentMapper.find(teacherStudentDto);
        PageInfo<TeacherStudentDto> paging = new PageInfo<TeacherStudentDto>(list);
        return paging;
    }

    @Override
    public List<TeacherStudentDto> find(TeacherStudentDto teacherStudentDto) {
        List<TeacherStudentDto> teacherStudentDtoList = teacherStudentMapper.find(teacherStudentDto);
        return teacherStudentDtoList;
    }

    @Transactional
    @Override
    public void deleteByIdSet(Set<Long> idSet) {
        teacherStudentMapper.deleteByIdSet(idSet);
    }

    @Transactional
    @Override
    public List<TeacherStudent> batchAdd(List<TeacherStudent> teacherStudentList) {
        teacherStudentMapper.batchAdd(teacherStudentList);
        return teacherStudentList;
    }

    @Transactional
    @Override
    public List<TeacherStudent> batchUpdate(List<TeacherStudent> teacherStudentList) {
        teacherStudentMapper.batchUpdate(teacherStudentList);
        return teacherStudentList;
    }

    @Transactional
    @Override
    public TeacherStudent syncData(TeacherStudent teacherStudent) {
        teacherStudentMapper.syncData(teacherStudent);
        return teacherStudent;
    }

    @Transactional
    @Override
    public List<TeacherStudent> batchSyncData(List<TeacherStudent> teacherStudentList) {
        teacherStudentMapper.batchSyncData(teacherStudentList);
        return teacherStudentList;
    }

    @Override
    public Long total(TeacherStudentDto teacherStudentDto) {
        Long total = teacherStudentMapper.total(teacherStudentDto);
        return total;
    }

}
