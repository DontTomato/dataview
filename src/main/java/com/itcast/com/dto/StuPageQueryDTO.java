package com.itcast.com.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class StuPageQueryDTO implements Serializable {

    private int page;

    private int pageSize;

    private String name;

    private Integer age;

    private  Short sex;
}
