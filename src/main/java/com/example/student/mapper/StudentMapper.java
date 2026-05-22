package com.example.student.mapper;

import com.example.student.entity.Student;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface StudentMapper {

    @Insert("INSERT INTO student(name, age, email, create_time) VALUES(#{name}, #{age}, #{email}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Student student);

    @Delete("DELETE FROM student WHERE id = #{id}")
    int deleteById(Long id);

    @Update("UPDATE student SET name=#{name}, age=#{age}, email=#{email} WHERE id=#{id}")
    int update(Student student);

    @Select("SELECT * FROM student WHERE id = #{id}")
    Student selectById(Long id);

    @Select("SELECT * FROM student WHERE name LIKE CONCAT('%',#{name},'%') LIMIT #{offset}, #{pageSize}")
    List<Student> selectByPage(@Param("name") String name, @Param("offset") int offset, @Param("pageSize") int pageSize);

    @Select("SELECT COUNT(*) FROM student WHERE name LIKE CONCAT('%',#{name},'%')")
    int selectCountByName(@Param("name") String name);
}
