package org.example.bootsecurity.model.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.bootsecurity.model.domain.Memo;
import org.springframework.stereotype.Repository;

import java.util.List;

// Spring
@Repository
// MyBatis
@Mapper
public interface MemoMapper {

    @Select("SELECT * FROM memo")
    List<Memo> findAll();

    @Insert("INSERT INTO memo (text) VALUES (#{text})")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Memo memo);

    @Delete("DELETE FROM memo")
    void deleteAll();
}
