package com.example.student.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StatsMapper {

    @Select("SELECT COUNT(*) FROM student")
    Long countAll();

    @Select("SELECT AVG(age) FROM student")
    Double avgAge();

    @Select("SELECT COUNT(*) FROM student WHERE age < 18")
    Integer countMinor();

    @Select("SELECT COUNT(*) FROM student WHERE age >= 18")
    Integer countAdult();
}
