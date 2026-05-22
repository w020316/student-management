package com.example.student.service;

import com.example.student.common.PageResult;
import com.example.student.entity.Student;

import java.util.List;

public interface StudentService {
    void addStudent(Student student);
    void deleteStudent(Long id);
    void batchDeleteStudents(List<Long> ids);
    void updateStudent(Long id, Student student);
    Student getStudentById(Long id);
    PageResult<Student> getStudentList(String name, int page, int pageSize);
    List<Student> exportStudents(String name);
}
