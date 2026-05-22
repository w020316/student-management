package com.example.student.controller;

import com.example.student.common.DashboardStats;
import com.example.student.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.student.mapper.StatsMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "系统监控")
@RestController
@RequestMapping("/api")
public class SystemController {

    @Autowired
    private StatsMapper statsMapper;

    @ApiOperation("仪表盘统计数据")
    @GetMapping("/stats")
    public Result<DashboardStats> stats() {
        DashboardStats stats = new DashboardStats();
        stats.setTotalStudents(statsMapper.countAll());
        stats.setAverageAge(statsMapper.avgAge());
        stats.setMinorCount(statsMapper.countMinor());
        stats.setAdultCount(statsMapper.countAdult());
        return Result.success(stats);
    }

    @ApiOperation("健康检查")
    @GetMapping("/health")
    public Result<Map<String, String>> health() {
        Map<String, String> info = new HashMap<>();
        info.put("status", "UP");
        info.put("service", "student-management");
        info.put("version", "1.0.0");
        return Result.success(info);
    }
}
