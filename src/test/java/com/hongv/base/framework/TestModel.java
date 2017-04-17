package com.hongv.base.framework;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by hongwei on 2017/4/18.
 */
public class TestModel {

    private int age;

    private String name;

    @JsonCreator
    public TestModel(@JsonProperty(value = "age") int age, @JsonProperty(value = "name") String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
}
