package com.example.quickdevelop.service.impl;

import com.example.quickdevelop.component.util.SnowflakeIdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.example.quickdevelop.dto.TeacherDto;
import com.example.quickdevelop.mapper.TeacherMapper;
import com.example.quickdevelop.model.Teacher;
import com.example.quickdevelop.service.TeacherService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

//@CacheConfig(cacheNames = "TeacherCache")
@Service
public class TeacherServiceImpl implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;
    private SnowflakeIdUtil snowflakeIdUtil = new SnowflakeIdUtil(0,0);


    //@Cacheable(key="#teacher.id",unless="#result == null",condition = "#teacher.id != null")
    @Transactional
    @Override
    public Teacher add(Teacher teacher) {
        Long id = snowflakeIdUtil.nextId();
        teacher.setId(id);
        teacherMapper.add(teacher);
        return teacher;
    }

    //@CacheEvict(key="#id",condition = "#id != null")
    @Transactional
    @Override
    public void deleteById(Long id) {
        teacherMapper.deleteById(id);
    }

    //@Cacheable(key="#id",unless="#result == null",condition = "#id != null")
    @Override
    public Teacher findById(Long id) {
        Teacher teacher = teacherMapper.findById(id);
        return teacher;
    }

    //@CachePut(key = "#teacher.id",unless="#result == null",condition = "#teacher.id != null")
    @Transactional
    @Override
    public Teacher updateById(Teacher teacher) {
        teacherMapper.updateById(teacher);
        return teacher;
    }

    //@CachePaging(keyPrefix = "TeacherCache",clazz = TeacherDto.class,expire = 5)
    @Override
    public PageInfo<TeacherDto> paging(TeacherDto teacherDto) {
        PageHelper.startPage(teacherDto.getPage(), teacherDto.getPageSize());
        List<TeacherDto> list = teacherMapper.find(teacherDto);
        PageInfo<TeacherDto> paging = new PageInfo<TeacherDto>(list);
        return paging;
    }

    @Override
    public List<TeacherDto> find(TeacherDto teacherDto) {
        List<TeacherDto> teacherDtoList = teacherMapper.find(teacherDto);
        return teacherDtoList;
    }

    @Transactional
    @Override
    public void deleteByIdSet(Set<Long> idSet) {
        teacherMapper.deleteByIdSet(idSet);
    }

    @Transactional
    @Override
    public List<Teacher> batchAdd(List<Teacher> teacherList) {
        teacherMapper.batchAdd(teacherList);
        return teacherList;
    }

    @Transactional
    @Override
    public List<Teacher> batchUpdate(List<Teacher> teacherList) {
        teacherMapper.batchUpdate(teacherList);
        return teacherList;
    }

    @Transactional
    @Override
    public Teacher syncData(Teacher teacher) {
        teacherMapper.syncData(teacher);
        return teacher;
    }

    @Transactional
    @Override
    public List<Teacher> batchSyncData(List<Teacher> teacherList) {
        teacherMapper.batchSyncData(teacherList);
        return teacherList;
    }

    @Override
    public Long total(TeacherDto teacherDto) {
        Long total = teacherMapper.total(teacherDto);
        return total;
    }

}
