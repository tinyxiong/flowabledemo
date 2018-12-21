package com.tinyxiong.flowabledemo.demo;

import com.tinyxiong.flowabledemo.base.BaseTest;
import com.tinyxiong.flowabledemo.demo.domain.TestUser;
import com.tinyxiong.flowabledemo.demo.mapper.TestUserMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@DisplayName("TestUser测试mybatis是否可用")
class UserMapperTest extends BaseTest {

    @Autowired
    private TestUserMapper testUserMapper;

    @Test
    @Rollback
    @DisplayName("保存用户")
    void saveUser() {
        testUserMapper.insertTestUser(new TestUser("test", "test"));
    }

    @Test
    @DisplayName("根据id=1查询用户")
    void findOne() {
        TestUser one = testUserMapper.selectOne(1);
        assertNotNull(one);
        assertEquals("test", one.getName());
    }

    @Test
    @DisplayName("assumeTrue")
    void findOneButNotRun() {
        TestUser one = testUserMapper.selectOne(1);
        assumeTrue(one == null);
    }

    @Test
    @DisplayName("注解的方式实现根据id查找TestUser")
    void selectTestUserById() {
        TestUser testUser = testUserMapper.selectTestUserById(1);
        assertEquals(1, testUser.getId());
    }
}