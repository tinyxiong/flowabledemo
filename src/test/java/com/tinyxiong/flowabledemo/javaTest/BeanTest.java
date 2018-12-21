package com.tinyxiong.flowabledemo.javaTest;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class BeanTest {

    private Map<String, Object> testBean;

    @Test
    public void testBean() {
        HasMapObject object = new HasMapObject(testBean);

        testBean = new HashMap<>();
        testBean.put("xiong", "afterInit");

        System.out.println(object.getBeans());
    }
}

class HasMapObject {

    Map<String, Object> beans;

    public HasMapObject(Map<String, Object> beans) {
        this.beans = beans;
    }

    public Map<String, Object> getBeans() {
        return beans;
    }
}
