package com.tinyxiong.flowabledemo.demo.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class TestUser implements Serializable {

    private int id;
    private String name;
    private String password;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;

    public TestUser() {
        this.createTime = LocalDateTime.now();
        this.modifyTime = LocalDateTime.now();
    }

    public TestUser(String name, String password) {
        this();
        this.name = name;
        this.password = password;
    }
}

