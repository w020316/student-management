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

    @Select("SELECT COUNT(*) FROM student WHERE email = #{email}")
    int countByEmail(@Param("email") String email);

    @Select("SELECT COUNT(*) FROM student WHERE email = #{email} AND id != #{excludeId}")
    int countByEmailExcludeId(@Param("email") String email, @Param("excludeId") Long excludeId);

    @Delete("<script>DELETE FROM student WHERE id IN <foreach collection='ids' item='id' open='(' separator=',' close=')'>#{id}</foreach></script>")
    int batchDelete(@Param("ids") List<Long> ids);

    @Select("SELECT * FROM student WHERE name LIKE CONCAT('%',#{name},'%') ORDER BY create_time DESC")
    List<Student> selectAllByName(@Param("name") String name);
}
