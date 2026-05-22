package com.example.student.controller;

import com.example.student.common.PageResult;
import com.example.student.common.Result;
import com.example.student.entity.Student;
import com.example.student.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "学生管理")
@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ApiOperation("新增学生")
    @PostMapping
    public Result<Void> add(@Validated @RequestBody Student student) {
        studentService.addStudent(student);
        return Result.success();
    }

    @ApiOperation("删除学生")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return Result.success();
    }

    @ApiOperation("修改学生")
    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id, @Validated @RequestBody Student student) {
        studentService.updateStudent(id, student);
        return Result.success();
    }

    @ApiOperation("查询学生详情")
    @GetMapping("/{id}")
    public Result<Student> getById(@PathVariable Long id) {
        return Result.success(studentService.getStudentById(id));
    }

    @ApiOperation("分页查询学生列表")
    @GetMapping
    public Result<PageResult<Student>> list(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return Result.success(studentService.getStudentList(name, page, pageSize));
    }
}
