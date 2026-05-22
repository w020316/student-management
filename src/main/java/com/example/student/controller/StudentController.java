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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

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

    @ApiOperation("批量删除学生")
    @DeleteMapping("/batch")
    public Result<Void> batchDelete(@RequestBody List<Long> ids) {
        studentService.batchDeleteStudents(ids);
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

    @ApiOperation("导出学生数据(CSV)")
    @GetMapping("/export")
    public void exportCsv(
            @RequestParam(defaultValue = "") String name,
            HttpServletResponse response) throws IOException {
        List<Student> students = studentService.exportStudents(name);

        String fileName = URLEncoder.encode("学生数据.csv", StandardCharsets.UTF_8.name());
        response.setContentType("text/csv; charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        PrintWriter writer = response.getWriter();
        writer.write("\uFEFF");
        writer.write("ID,姓名,年龄,邮箱,创建时间\n");
        for (Student s : students) {
            writer.write(String.format("%d,%s,%d,%s,%s\n",
                    s.getId(),
                    s.getName() != null ? s.getName() : "",
                    s.getAge() != null ? s.getAge() : 0,
                    s.getEmail() != null ? s.getEmail() : "",
                    s.getCreateTime() != null ? s.getCreateTime().toString() : ""
            ));
        }
        writer.flush();
    }
}
