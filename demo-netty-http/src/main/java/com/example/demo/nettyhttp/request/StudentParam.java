package com.example.demo.nettyhttp.request;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

public class StudentParam implements Serializable {

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        StudentParam studentParam = new StudentParam();
        studentParam.setId(11);
        studentParam.setName("张飞大神");
        System.out.println(JSON.toJSONString(studentParam));

    }
}
