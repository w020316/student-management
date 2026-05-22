package com.example.student.common;

import lombok.Data;

@Data
public class DashboardStats {
    private Long totalStudents;
    private Double averageAge;
    private Integer minorCount;
    private Integer adultCount;
}
