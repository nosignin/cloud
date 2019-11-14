package com.example.redis.web;


import com.example.redis.annotation.ExportExcel;
import com.example.redis.chain.Student;
import com.example.redis.util.RandomStringUtil;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/export/excel")
public class ExportExcelController {
    /**
     * 通过浏览器调用http://localhost:8080/export/excel/getStudents,即可得到excel
     * @return
     */
    @GetMapping("/getStudents")
    @ExportExcel(exportClass = Student.class)
    public List<Student> getStudents(){
        List<Student> students = generateStudents(100);
        return students;
    }

    /**
     * 生成student列表
     * @param number
     * @return
     */
    private List<Student> generateStudents(int number) {
        List<Student> students = new ArrayList<>();
        for(int i=1;i<number;i++){
            Long id = Long.valueOf(i);
            Integer age = (int) (Math.random()*100);
            String name = RandomStringUtil.generateStr(12);
            Student student = generateStudent(id,age,name);
            students.add(student);
        }
        return students;
    }

    /**
     * 生成一个student
     * @param id
     * @param age
     * @param name
     * @return
     */
    private Student generateStudent(Long id,Integer age,String name) {
        Student student = new Student();
        student.setId(id)
                .setAge(age)
                .setName(name);
        return student;
    }
}
