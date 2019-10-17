package com.example.quickdevelop.service.impl;

import com.example.quickdevelop.component.util.SnowflakeIdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.example.quickdevelop.dto.StudentDto;
import com.example.quickdevelop.mapper.StudentMapper;
import com.example.quickdevelop.model.Student;
import com.example.quickdevelop.service.StudentService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

//@CacheConfig(cacheNames = "StudentCache")
@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    private SnowflakeIdUtil snowflakeIdUtil = new SnowflakeIdUtil(0,0);

    //@Cacheable(key="#student.id",unless="#result == null",condition = "#student.id != null")
    @Transactional
    @Override
    public Student add(Student student) {
        Long id = snowflakeIdUtil.nextId();
        student.setId(id);
        studentMapper.add(student);
        return student;
    }

    //@CacheEvict(key="#id",condition = "#id != null")
    @Transactional
    @Override
    public void deleteById(Long id) {
        studentMapper.deleteById(id);
    }

    //@Cacheable(key="#id",unless="#result == null",condition = "#id != null")
    @Override
    public Student findById(Long id) {
        Student student = studentMapper.findById(id);
        return student;
    }

    //@CachePut(key = "#student.id",unless="#result == null",condition = "#student.id != null")
    @Transactional
    @Override
    public Student updateById(Student student) {
        studentMapper.updateById(student);
        return student;
    }

    //@CachePaging(keyPrefix = "StudentCache",clazz = StudentDto.class,expire = 5)
    @Override
    public PageInfo<StudentDto> paging(StudentDto studentDto) {
        PageHelper.startPage(studentDto.getPage(), studentDto.getPageSize());
        List<StudentDto> list = studentMapper.find(studentDto);
        PageInfo<StudentDto> paging = new PageInfo<StudentDto>(list);
        return paging;
    }

    @Override
    public List<StudentDto> find(StudentDto studentDto) {
        List<StudentDto> studentDtoList = studentMapper.find(studentDto);
        return studentDtoList;
    }

    @Transactional
    @Override
    public void deleteByIdSet(Set<Long> idSet) {
        studentMapper.deleteByIdSet(idSet);
    }

    @Transactional
    @Override
    public List<Student> batchAdd(List<Student> studentList) {
        studentMapper.batchAdd(studentList);
        return studentList;
    }

    @Transactional
    @Override
    public List<Student> batchUpdate(List<Student> studentList) {
        studentMapper.batchUpdate(studentList);
        return studentList;
    }

    @Transactional
    @Override
    public Student syncData(Student student) {
        studentMapper.syncData(student);
        return student;
    }

    @Transactional
    @Override
    public List<Student> batchSyncData(List<Student> studentList) {
        studentMapper.batchSyncData(studentList);
        return studentList;
    }

    @Override
    public Long total(StudentDto studentDto) {
        Long total = studentMapper.total(studentDto);
        return total;
    }

}
