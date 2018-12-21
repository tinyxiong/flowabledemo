package com.tinyxiong.flowabledemo.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.tinyxiong.flowabledemo.demo.domain.TestUser;

import java.util.List;

@Repository
@Mapper
public interface TestUserMapper {

    void insertTestUser(TestUser testUser);

    List<TestUser> selectTestUser();

    TestUser selectOne(int id);

    @Select("select * from TEST_USER where id = #{id}")
    TestUser selectTestUserById(int id);
}
