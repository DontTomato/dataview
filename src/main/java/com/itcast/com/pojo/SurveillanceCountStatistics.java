package com.itcast.com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveillanceCountStatistics {
    private int id;
    private String stationName;
    private int cameraNumber;
    private Date createTime;//创建时间
    private Date updateTime;//更新时间
}
