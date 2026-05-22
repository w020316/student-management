package com.example.student.service.impl;

import com.example.student.common.BusinessException;
import com.example.student.common.PageResult;
import com.example.student.entity.Student;
import com.example.student.mapper.StudentMapper;
import com.example.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void addStudent(Student student) {
        student.setCreateTime(LocalDateTime.now());
        studentMapper.insert(student);
    }

    @Override
    public void deleteStudent(Long id) {
        Student existing = studentMapper.selectById(id);
        if (existing == null) {
            throw new BusinessException("学生不存在，无法删除");
        }
        studentMapper.deleteById(id);
    }

    @Override
    public void updateStudent(Long id, Student student) {
        Student existing = studentMapper.selectById(id);
        if (existing == null) {
            throw new BusinessException("学生不存在，无法修改");
        }
        student.setId(id);
        student.setCreateTime(existing.getCreateTime());
        studentMapper.update(student);
    }

    @Override
    public Student getStudentById(Long id) {
        Student student = studentMapper.selectById(id);
        if (student == null) {
            throw new BusinessException("学生不存在");
        }
        return student;
    }

    @Override
    public PageResult<Student> getStudentList(String name, int page, int pageSize) {
        if (name == null) {
            name = "";
        }
        int offset = (page - 1) * pageSize;
        List<Student> list = studentMapper.selectByPage(name, offset, pageSize);
        int total = studentMapper.selectCountByName(name);
        return new PageResult<>(list, total, page, pageSize);
    }
}
