package com.itcast.com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Captures {
    private int id;
    private String stationName;
    private String captureName;
    private Date creatTime;//创建时间
    private Date updatTime;//更新时间
}
