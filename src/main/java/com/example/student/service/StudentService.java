package com.example.student.service;

import com.example.student.common.PageResult;
import com.example.student.entity.Student;

public interface StudentService {
    void addStudent(Student student);
    void deleteStudent(Long id);
    void updateStudent(Long id, Student student);
    Student getStudentById(Long id);
    PageResult<Student> getStudentList(String name, int page, int pageSize);
}
