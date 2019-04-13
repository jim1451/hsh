package com.silence.commonframe.bean;

public class Person1 {
    private String name, age;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Person1(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public Person1(String name, String age, int type) {
        this.name = name;
        this.age = age;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

}
