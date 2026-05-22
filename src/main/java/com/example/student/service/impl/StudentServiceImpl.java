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
        if (student.getEmail() != null && !student.getEmail().isEmpty()) {
            int count = studentMapper.countByEmail(student.getEmail());
            if (count > 0) {
                throw new BusinessException("邮箱已被使用，请更换");
            }
        }
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
    public void batchDeleteStudents(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new BusinessException("请选择要删除的学生");
        }
        studentMapper.batchDelete(ids);
    }

    @Override
    public void updateStudent(Long id, Student student) {
        Student existing = studentMapper.selectById(id);
        if (existing == null) {
            throw new BusinessException("学生不存在，无法修改");
        }
        if (student.getEmail() != null && !student.getEmail().isEmpty()) {
            int count = studentMapper.countByEmailExcludeId(student.getEmail(), id);
            if (count > 0) {
                throw new BusinessException("邮箱已被使用，请更换");
            }
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

    @Override
    public List<Student> exportStudents(String name) {
        if (name == null) {
            name = "";
        }
        return studentMapper.selectAllByName(name);
    }
}
