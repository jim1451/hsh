package com.silence.commonframe.bean;

import java.io.Serializable;

/**
 * 实体类
 */
public class TestBean implements Serializable {

    private String name;
    private int imgRes;
    private  String place;
    private  String question;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }
}
