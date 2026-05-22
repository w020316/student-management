package com.example.student;

import com.example.student.common.Result;
import com.example.student.controller.StudentController;
import com.example.student.controller.SystemController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("dev")
class StudentManagementApplicationTests {

    @Autowired
    private StudentController studentController;

    @Autowired
    private SystemController systemController;

    @Test
    void contextLoads() {
        assertNotNull(studentController);
        assertNotNull(systemController);
    }

    @Test
    void testHealthEndpoint() {
        Result<?> result = systemController.health();
        assertEquals(200, result.getCode());
        assertNotNull(result.getData());
    }

    @Test
    void testStatsEndpoint() {
        Result<?> result = systemController.stats();
        assertEquals(200, result.getCode());
        assertNotNull(result.getData());
    }

    @Test
    void testListStudents() {
        Result<?> result = studentController.list("", 1, 10);
        assertEquals(200, result.getCode());
        assertNotNull(result.getData());
    }
}
